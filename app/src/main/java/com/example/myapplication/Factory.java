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
    Gen gen;

    public Factory(BigDecimal income, int time, double ascmult) {
        this.income = income;
        this.upgcost = income;
        this.startincome = income;
        this.ascendmulty = ascmult;
        this.upglv = 0;
        gen = new Gen(this);
    }

    public void upgrade(){

        if (upglv<100&&Automation.money.compareTo(upgcost)>=0) {
            Log.d("MYLOG", "UPGRADED");
            Automation.money = Automation.money.subtract(upgcost);
            upglv += 1;
            if(upglv==1){
                upgcost = upgcost.multiply(BigDecimal.valueOf(3));
            }
            else{
                income = income.add(startincome);
                upgcost = upgcost.multiply(BigDecimal.valueOf(1.2));
            }
            time -= 90;
            Automation.adapter.notifyDataSetChanged();
            if(upglv==1&&!gen.isAlive()){
                gen.start();
            }
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
            upgcost = income.multiply(BigDecimal.valueOf(1));
            time = 9910;
            Automation.adapter.notifyDataSetChanged();
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
        if (factory.upglv>0){
            while (true){
                Log.d("MYLOG", String.valueOf(factory.income));
                try {
                    sleep(factory.time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Automation.money = Automation.money.add(factory.income);
            }
        }
    }
}
