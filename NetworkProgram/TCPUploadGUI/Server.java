package TCPUploadGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException {

        // Create a ServerSocket object and bind port
        ServerSocket ss = new ServerSocket(10086);

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
            Socket socket = ss.accept();

            // Use thread pool to handle the connection
            pool.submit(new MyRunnable(socket));
        }
    }
}
