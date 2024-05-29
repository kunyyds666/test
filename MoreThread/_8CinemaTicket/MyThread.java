package MoreThread._8CinemaTicket;

public class MyThread extends Thread{

    static int ticket = 1;
    static int MAXTICKET = 1000;



    @Override
    public void run() {
        while (true){
            synchronized (MyThread.class){
                if (ticket == MAXTICKET + 1){
                    break;
                }else {
                    System.out.println(getName() + "," + ticket);
                    ticket++;
                }
            }
        }
    }
}
