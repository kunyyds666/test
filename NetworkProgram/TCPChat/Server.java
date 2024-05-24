package TCPChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, // Core thread number
                16, // Maximum thread number
                60, // Maximum wait time value
                TimeUnit.SECONDS, // Maximum wait time unit
                new ArrayBlockingQueue<>(2), // Queue
                Executors.defaultThreadFactory(), // Thread factory
                new ThreadPoolExecutor.AbortPolicy() // Rejection policy
        );

        while (true) {
            // Wait for client connection
            Socket socket = serverSocket.accept();

            // Use thread pool to handle the connection
            pool.submit(new MyRunnable(socket));
        }

    }
}
