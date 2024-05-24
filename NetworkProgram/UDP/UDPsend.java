package UDP;

import java.io.IOException;
import java.net.*;

public class UDPsend {
    public static void main(String[] args) throws IOException {

        //发送数据udp协议
        //创建DatagramSocket(快递公司)
        DatagramSocket ds = new DatagramSocket();
        //打包数据(快递员)
        String str = "hello world";
        byte[] bytes = str.getBytes();

        InetAddress address = InetAddress.getByName("127.0.0.1");
        //端口号
        int port = 10086;

        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);

        //发送数据(派送员)
        ds.send(dp);

        //释放资源
        ds.close();


    }
}
