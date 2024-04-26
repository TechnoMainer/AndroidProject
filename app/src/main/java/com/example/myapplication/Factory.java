package com.example.myapplication;

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
    double ascendmulty;

    public Factory(BigDecimal income, int time, double ascmult) {
        this.income = income;
        this.upgcost = income.multiply(BigDecimal.valueOf(3));
        this.startincome = income;
        this.ascendmulty = ascmult;
        Gen gen = new Gen(this);
        gen.start();
    }

    public void upgrade(){
        if (upglv<100&&MainActivity.money.compareTo(upgcost)>0) {
            Log.d("MYLOG", "UPGRADED");
            income = income.add(startincome);
            MainActivity.money = MainActivity.money.subtract(upgcost);
            upglv += 1;
            upgcost = upgcost.multiply(BigDecimal.valueOf(1.2));
            time -= 90;
            MainActivity.adapter.notifyDataSetChanged();
        }
    }

    public void ascending(){
        if (upglv==100) {
            Log.d("MYLOG", "ASCENDED");
            ascendmulty+=0.4;
            income = income.multiply(BigDecimal.valueOf(ascendmulty));
            ascendlv += 1;
            upglv = 1;
            startincome = income;
            upgcost = income.multiply(BigDecimal.valueOf(3));
            time = 9910;
            MainActivity.adapter.notifyDataSetChanged();
        }
    }
}

class Gen extends Thread{

    Factory factory;
    public Gen(Factory factory){
       this.factory = factory;
    }

    @Override
    public void run() {
        while (true){
            MainActivity.money = MainActivity.money.add(factory.income);
            try {
                sleep(factory.time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
