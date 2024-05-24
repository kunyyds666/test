package ChatDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Server {
    private static Set<Socket> clientSockets = new HashSet<>();
    private static final Object lock = new Object();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, // 核心线程数
                16, // 最大线程数
                60, // 空闲线程最大存活时间
                TimeUnit.SECONDS, // 空闲线程存活时间单位
                new ArrayBlockingQueue<>(2), // 阻塞队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        while (true) {
            Socket socket = serverSocket.accept();
            synchronized (lock) {
                clientSockets.add(socket);
            }
            pool.submit(new ClientHandler(socket));
        }
    }

    public static void broadcast(String message, Socket excludeSocket) throws IOException {
        synchronized (lock) {
            for (Socket socket : clientSockets) {
                if (socket != excludeSocket) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);
                }
            }
        }
    }

    public static void removeClient(Socket socket) {
        synchronized (lock) {
            clientSockets.remove(socket);
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    Server.broadcast("Client " + socket.getPort() + ": " + message, socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Server.removeClient(socket);
            }
        }
    }
}
