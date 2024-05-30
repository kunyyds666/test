import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IODemo {
    public static void main(String[] args) throws IOException {
        String filePath = "https://www.gushicimingju.com/dianji/baijiaxing/";

        //通过字节流读取数据
        FileInputStream fis = new FileInputStream(filePath);
        //创建缓冲区
        Byte[] bytes = new Byte[1024];





        }
}
    //爬取页面
