package MoreThread._10ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolDemo {
    public static void main(String[] args) {

        //获取线程池对象
        //无限（int值的上限）
        ExecutorService pool1 = Executors.newCachedThreadPool();
        //有限（int值）
        ExecutorService pool2 = Executors.newFixedThreadPool(3);

        //提交任务
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());





    }
}
