package TCPDemo2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        //create a SERVER-SOCKET object && bing port
        ServerSocket ss = new ServerSocket(10086);
        //waiting CLIENT connecting
        Socket socket = ss.accept();

        //read and save data && using character io packaging data
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\Users\\28997\\OneDrive\\图片\\Feedback\\a.jpg"));

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("上传成功");
        bw.newLine();
        bw.flush();

        socket.close();
        ss.close();


    }
}
