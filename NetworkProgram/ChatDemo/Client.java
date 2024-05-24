package ChatDemo;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame {
    private JTextField messageField;
    private JButton sendButton;
    private JTextPane chatPane;
    private Socket socket;
    private PrintWriter out;
    private JLabel serverInfoLabel;
    private JButton connectButton;
    private JButton disconnectButton;
    private JPanel chatPanel;
    private String clientName;
    private JButton changeNameButton;

    public Client() {
        setTitle("Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the top panel with server info label and disconnect button
        serverInfoLabel = new JLabel("Not connected");
        disconnectButton = new JButton("X");
        disconnectButton.setForeground(Color.RED);
        disconnectButton.setBackground(Color.WHITE);
        disconnectButton.setBorder(BorderFactory.createEmptyBorder());
        disconnectButton.setFocusPainted(false);
        disconnectButton.setVisible(false); // Initially hidden

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectFromServer();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(serverInfoLabel, BorderLayout.CENTER);
        topPanel.add(disconnectButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Create the chat panel but do not add it initially
        chatPanel = new JPanel(new BorderLayout());

        JPanel messagePanel = new JPanel(new FlowLayout());
        messageField = new JTextField(15);
        sendButton = new JButton("Send");
        changeNameButton = new JButton("Change Name");
        chatPane = new JTextPane();
        chatPane.setEditable(false);

        messagePanel.add(changeNameButton);
        messagePanel.add(messageField);
        messagePanel.add(sendButton);

        chatPanel.add(new JScrollPane(chatPane), BorderLayout.CENTER);
        chatPanel.add(messagePanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    displayMessage(clientName + ": " + message, true);
                    out.println(clientName + ": " + message);
                    messageField.setText("");
                }
            }
        });

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog(Client.this, "Enter new name:", clientName);
                if (newName != null && !newName.trim().isEmpty()) {
                    clientName = newName.trim();
                }
            }
        });

        // Create the connect button
        createConnectButton();
    }

    private void createConnectButton() {
        connectButton = new JButton("+") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(getBackground());
                g.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }
        };
        connectButton.setFont(new Font("Arial", Font.PLAIN, 24));
        connectButton.setFocusPainted(false);
        connectButton.setContentAreaFilled(false);
        connectButton.setBorderPainted(false);
        connectButton.setOpaque(false);
        connectButton.setBackground(Color.LIGHT_GRAY);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(3, 2));
                JTextField serverAddressField = new JTextField("127.0.0.1");
                JTextField portField = new JTextField("10086");
                JTextField nameField = new JTextField("Client");

                panel.add(new JLabel("Server IP:"));
                panel.add(serverAddressField);
                panel.add(new JLabel("Port:"));
                panel.add(portField);
                panel.add(new JLabel("Name:"));
                panel.add(nameField);

                int result = JOptionPane.showConfirmDialog(Client.this, panel, "Enter Server Details", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String serverAddress = serverAddressField.getText();
                    String name = nameField.getText().trim();
                    if (!name.isEmpty()) {
                        clientName = name;
                    } else {
                        clientName = "Client";
                    }
                    try {
                        int port = Integer.parseInt(portField.getText());
                        connectToServer(serverAddress, port);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Client.this, "Invalid port number", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(connectButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void connectToServer(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);

            serverInfoLabel.setText("Connected to " + serverAddress + ":" + port);
            disconnectButton.setVisible(true);

            new Thread(new IncomingReader()).start();

            // Remove the connect button and show the chat panel
            remove(connectButton.getParent());
            add(chatPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to connect to server", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void disconnectFromServer() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dispose();
        }
    }

    private void displayMessage(String message, boolean isSelf) {
        StyledDocument doc = chatPane.getStyledDocument();
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setFontSize(style, 14);
        if (isSelf) {
            StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
            StyleConstants.setForeground(style, Color.BLUE);
        } else {
            StyleConstants.setAlignment(style, StyleConstants.ALIGN_LEFT);
            StyleConstants.setForeground(style, Color.BLACK);
        }

        try {
            int length = doc.getLength();
            doc.insertString(length, message + "\n", style);
            doc.setParagraphAttributes(length, message.length(), style, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private class IncomingReader implements Runnable {
        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    displayMessage(message, false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
}
