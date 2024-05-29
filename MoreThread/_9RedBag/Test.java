package MoreThread._9RedBag;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("n人抢sum元m个包");

        int n = sc.nextInt();
        int m = sc.nextInt();
        double sum = sc.nextDouble();

        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread();
            threads[i].start();
        }


    }
}
