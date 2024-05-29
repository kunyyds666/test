package MoreThread._3Callable_Future;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {

        int sum = 0;
        for (int i = 1; i < 100; i++) {
            sum = sum + i;
        }

        return sum;
    }
}
