package MoreThread._2Runnable;
//实现Runnable接口的方式进行是实现
public class ThreadDemo {
    public static void main(String[] args) {

        MyRun myRun = new MyRun();

        Thread t1 = new Thread(myRun);
        Thread t2 = new Thread(myRun);

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();
        t2.start();
    }
}
