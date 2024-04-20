package com.example.myapplication;

import android.util.Log;

import java.math.BigDecimal;

public class Factory {

    BigDecimal income;
    BigDecimal startincome;
    int upglv;
    BigDecimal upgcost;
    int ascendlv;
    int time = 9910;

    public Factory(BigDecimal income, int time) {
        this.income = income;
        this.upgcost = income.multiply(BigDecimal.valueOf(3));
        this.startincome = income;
        Gen gen = new Gen();
        gen.run(income, time);
    }

    public void upgrade(BigDecimal money){
        income.add(startincome);
        money.subtract(upgcost);
        upglv+=1;
        upgcost.multiply(BigDecimal.valueOf(1.1));
        time -= 90;
    }

    public void ascending(int ascendmulty){
        income.multiply(BigDecimal.valueOf(ascendmulty));
        ascendlv+=1;
        upglv=0;
        startincome = income;
        upgcost = income.multiply(BigDecimal.valueOf(3));
        time = 9910;
    }
}

class Gen extends Thread{
    public void run(BigDecimal income, int time){
        while (true){
            MainActivity.money.add(income);
            MainActivity.mon.setText(String.valueOf(MainActivity.money));
            Log.d("MYLOG", String.valueOf(MainActivity.money));
            try {
                sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
