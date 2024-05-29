package MoreThread._11DiyThreadPool;

import java.lang.management.ThreadInfo;
import java.util.concurrent.*;

public class DiyThreadPool {
    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, //核心线程树，不能小于0
                6, //最大线程数，不能小于0，且大于核心线程数
                1,//空闲线程最大存活时间，值
                TimeUnit.MINUTES,//空闲线程最大存活时间，单位
                new ArrayBlockingQueue<>(3),//new LinkedBlockingQueue<>()。任务队列，最大等待数
                Executors.defaultThreadFactory(),//创建线程工厂
                new ThreadPoolExecutor.AbortPolicy()//任务的拒绝策略
        );

        //向java虚拟机返回可用处理器的数目
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);





    }
}
