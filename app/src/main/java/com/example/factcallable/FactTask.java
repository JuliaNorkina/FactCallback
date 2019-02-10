package com.example.factcallable;

import com.example.factcallable.Utils.MathUtil;

import java.util.concurrent.Callable;

class FactTask implements Callable<Integer> {
    private static final int DELAY = 2000;
    private int number;

    FactTask(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(DELAY);
        return MathUtil.calculateFactorial(number);
    }

}
