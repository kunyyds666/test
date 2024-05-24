package TCPThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException {

        //create a SERVER-SOCKET object && bing port
        ServerSocket ss = new ServerSocket(10086);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,//核心线程数量
                16,//最大线程数量
                60,//最大等待时间值
                TimeUnit.SECONDS,//最大等待时间单位
                new ArrayBlockingQueue<>(2),//队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.AbortPolicy()//阻塞队列
        );



        while (true){
            //waiting CLIENT connecting
            Socket socket = ss.accept();

            //new  Thread(new MyRunnable(socket)).start();
            pool.submit(new MyRunnable(socket));

        }


    }
}
