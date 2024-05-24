package TCPDemo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("127.0.0.1",10086);

        Scanner sc = new Scanner(System.in);

        OutputStream os = s.getOutputStream();

        while (true){
            //String str = sc.nextLine();
            //byte[] bytes = str.getBytes();
            byte[] bytes = sc.nextLine().getBytes();
            os.write(bytes);

        }

        //s.shutdownOutput();结束标记





    }
}
