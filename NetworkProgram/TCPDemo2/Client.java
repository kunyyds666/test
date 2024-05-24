package TCPDemo2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        //create a Socket object && connect server
        Socket socket = new Socket("127.0.0.1",10086);

        //读取本地数据,并用缓冲流包裹
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\28997\\OneDrive\\图片\\米兰.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        //往服务器发送结束标志 send a end tag for server
        socket.shutdownOutput();

        //receive SERVER returned data && out
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();
        System.out.println(line);

        socket.close();




    }
}
