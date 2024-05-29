package MoreThread._5Eating;

public class Desk {

    /**
     * 控制消费者和生产者的执行
     */
    // 是否有面条 0：没有 1：有
    public static int foodFlag = 0;

    //总数，达到这个之后停止
    public static int count = 0;
    public static int MAX_COUNT = 10;
    //创建唯一锁对象
    public static Object lock = new Object();
}
