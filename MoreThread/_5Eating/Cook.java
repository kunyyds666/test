package MoreThread._5Eating;

public class Cook extends Thread{
    @Override
    public void run() {
        while (true){
            synchronized (Desk.lock){
                if(Desk.count == Desk.MAX_COUNT){
                    break;
                }else {
                    if(Desk.foodFlag == 1){
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        Desk.lock.notifyAll();
                        System.out.println("开做");
                        Desk.foodFlag = 1;
                    }
                }
            }
        }

    }
}
