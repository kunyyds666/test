package MoreThread._4Cinema;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread{

    //共享 ticket数据
    static int ticket = 0;
    //锁对象，一定是唯一
    static final Object obj = new Object();

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            lock.lock();
            //同步代码块，锁对象对象很随意，但一定是唯一
            //synchronized (MyThread.class){
            try {
                if (ticket < 100) {
                    Thread.sleep(100);

                    ticket++;
                    System.out.println(getName()+","+ticket);
                }else{
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            //}
        }
    }
}
