package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;

import java.math.BigDecimal;

public class Factory {

    BigDecimal income;
    String name = "None";
    BigDecimal startincome;
    int upglv;
    BigDecimal upgcost;
    int ascendlv;
    int time = 9910;

    public Factory(BigDecimal income, int time) {
        this.income = income;
        this.upgcost = income.multiply(BigDecimal.valueOf(3));
        this.startincome = income;
        Gen gen = new Gen(income, time);
        gen.start();
    }

    public void upgrade(BigDecimal money){
        income = income.add(startincome);
        money = money.subtract(upgcost);
        upglv+=1;
        upgcost = upgcost.multiply(BigDecimal.valueOf(1.1));
        time -= 90;
    }

    public void ascending(int ascendmulty){
        income = income.multiply(BigDecimal.valueOf(ascendmulty));
        ascendlv+=1;
        upglv=0;
        startincome = income;
        upgcost = income.multiply(BigDecimal.valueOf(3));
        time = 9910;
    }
}

class Gen extends Thread{

    BigDecimal income = BigDecimal.valueOf(0);
    int time;
    public Gen(BigDecimal income, int time){
       this.income = income;
       this.time = time;
    }

    @Override
    public void run() {
        while (true){
            Log.d("MYLOG", String.valueOf(MainActivity.money));
            MainActivity.money = MainActivity.money.add(income);
            try {
                sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
