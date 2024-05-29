package MoreThread._6Eating2;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook extends Thread{

    ArrayBlockingQueue<String> queue;

    public Cook(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            //不断将面条放进阻塞队列中
            try {
                queue.put("面条");
                System.out.println("放了一碗");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
