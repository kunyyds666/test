package TCPUploadGUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame {
    private final JTextField filePathField;
    private final JTextArea statusArea;

    public Client() {
        setTitle("File Uploader");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        filePathField = new JTextField(20);
        filePathField.setEditable(false);
        JButton browseButton = new JButton("Browse");
        JButton uploadButton = new JButton("Upload");
        statusArea = new JTextArea(5, 30);
        statusArea.setEditable(false);

        panel.add(filePathField);
        panel.add(browseButton);
        panel.add(uploadButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        uploadButton.addActionListener(e -> {
            String filePath = filePathField.getText();
            if (!filePath.isEmpty()) {
                uploadFile(filePath);
            } else {
                statusArea.append("No file selected.\n");
            }
        });
    }

    private void uploadFile(String filePath) {
        try (Socket socket = new Socket("127.0.0.1", 10086);
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
             BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream())) {

            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
            socket.shutdownOutput();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = br.readLine();
            statusArea.append("Server: " + response + "\n");//todo

        } catch (IOException ex) {
            statusArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
    }
}
