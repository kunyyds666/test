package MoreThread._5Eating;

public class Foodie extends Thread{
    @Override
    public void run() {

        while (true){
            synchronized (Desk.lock){
                if (Desk.count == Desk.MAX_COUNT){
                    break;
                }else {
                    if(Desk.foodFlag == 0){
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        Desk.count++;
                        System.out.println( "开吃吃了" + Desk.count + "碗");
                        Desk.lock.notifyAll();
                        Desk.foodFlag = 0;
                    }

                }
            }
        }



    }
}
