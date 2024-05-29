package MoreThread._5Eating;

public class ThreadDemo {
    public static void main(String[] args) {
        Cook cook = new Cook();
        Foodie foodie = new Foodie();

        Desk.foodFlag = 0;


        cook.start();
        foodie.start();
    }
}
