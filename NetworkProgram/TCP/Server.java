package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket对象
        ServerSocket ss = new ServerSocket(10086);
        //监听客户端的链接
        Socket socket = ss.accept();
        //从连接通道中获取输入流获取数据   字节流
        InputStream is = socket.getInputStream();
        //将字节流转化成字符流
        InputStreamReader isr = new InputStreamReader(is);
        //将字符流转换成缓冲流
        BufferedReader br = new BufferedReader(isr);

        //链式编码
        //BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        int b;
        while ((b = br.read()) != -1){
            System.out.print((char) b);
        }

        ss.close();
        socket.close();



    }
}
