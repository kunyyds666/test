package MoreThread._7method;

public class ThreadDemo {
    public static void main(String[] args) {
        /*
        String getName();           返回线程名字
        void setName(String name)   设置线程的名字
            如果线程没用设置名字，线程有默认名字 “Thread-X” X从0开始
            可以通过setName设置名字，也可以在线程创建的时候设置名字

        static Thread currentThread
            JVM虚拟机启动会自动设置多条线程，其中有一条线程是main，作用是调用main（）方法、

        static void sleep(long time)
            哪个线程执行到这个方法就会停留 time 时间（毫秒），之后继续执行下面其他代码

        setPriority（int newPriority）    设置线程的优先级
        final int getPriority（）         获取线程的优先级

        final void setDaemon             设置守护线程，当其他非守护线程结束之后，守护线程会陆续结束

        public static void yield         出让线程，出让当前cpu的执行权

        public final void join（）       插入线程


         */
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        t1.setName("1");
        t2.setName("2");

//        t1.setName("！！！");
//        t2.setName("###");

        t2.setDaemon(true);

        t1.start();
        t2.start();



    }
}
