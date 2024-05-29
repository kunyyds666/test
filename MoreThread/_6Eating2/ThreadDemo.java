package MoreThread._6Eating2;

import java.util.concurrent.ArrayBlockingQueue;

public class ThreadDemo {
    public static void main(String[] args) {

        //创建阻塞队列的的对象
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

        Cook cook = new Cook(queue);
        Foodie foodie = new Foodie(queue);

        cook.start();
        foodie.start();



    }


}
