package MoreThread._3Callable_Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //callable 返回线程运行结果




        //future 对返回的结果进行处理

        //创建MyCallable的对象，，表示多线程要执行的任务
        MyCallable mc = new MyCallable();
        //创建FutureTask的对象，，作用管理多线程运行的结果
        FutureTask<Integer> ft = new FutureTask<>(mc);
        //创建线程的对象
        Thread t1 = new Thread(ft);

        t1.start();

        System.out.println(ft.get());
    }




}
