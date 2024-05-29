package MoreThread._1Thread;

public class MyThread extends Thread {

    public void run(){
        //书写线程执行的代码
        for (int i = 0;i < 100;i++){
            System.out.println(getName() + "hello");
        }
    }
}
