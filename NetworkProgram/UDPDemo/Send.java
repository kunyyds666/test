package UDPDemo;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Send {
    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket();

        Scanner sc = new Scanner(System.in);


        while (true) {
            String str  = sc.nextLine();

            byte[] bytes = str.getBytes();

            InetAddress address = InetAddress.getByName("127.0.0.1");

            int port = 10086;

            DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);

            ds.send(dp);
        }


    }
}
