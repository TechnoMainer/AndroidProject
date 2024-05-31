package com.example.myapplication;

import android.util.Log;

import java.math.BigDecimal;

public class Factory {

    BigDecimal income;
    BigDecimal previncome;
    String name = "None";
    int upglv = 0;
    BigDecimal upgcost;
    int ascendlv;
    int time;
    Gen gen;
    boolean genStarted;

    public Factory(BigDecimal income, int upglv, int ascendlv, boolean genStarted, String name) {
        this.upgcost = income.multiply(BigDecimal.valueOf(Math.pow(1.2, upglv)));
        this.income = income;
        previncome = income;
        this.upglv = upglv;
        this.income = income.multiply(BigDecimal.valueOf(upglv));
        this.ascendlv = ascendlv;
        this.time = 10000-90*upglv;
        this.genStarted = genStarted;
        this.name = name;
        gen = new Gen(this);
        if(upglv>=1&&!gen.isAlive()&&!genStarted){
            gen.start();
        }
    }

    public void upgrade(){
        if (upglv<100&&MainActivity.money.compareTo(upgcost)>=0) {
            MainActivity.money = MainActivity.money.subtract(upgcost);
            upglv += 1;
            if(upglv==1){
                upgcost = previncome.multiply(BigDecimal.valueOf(upglv)).multiply(BigDecimal.valueOf(3));
            }
            else{
                upgcost = upgcost.multiply(BigDecimal.valueOf(1.2));
            }
            time = 10000-90*upglv;
            Automation.adapter.notifyDataSetChanged();
            if(upglv>=1&&!gen.isAlive()){
                gen.start();
            }
        }
    }

    public BigDecimal getIncome(){
        return previncome.multiply(BigDecimal.valueOf(upglv)).multiply(BigDecimal.valueOf(Math.pow(Automation.ascendmulty+1, ascendlv)));
    }

    public void ascending(){
        if (upglv==100) {
            ascendlv += 1;
            upglv = 1;
            upgcost = previncome.multiply(BigDecimal.valueOf(10));
            time = 10000;
            Automation.adapter.notifyDataSetChanged();
            Automation.asccount +=1;
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
                try {
                    sleep(factory.time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                MainActivity.money = MainActivity.money.add(factory.getIncome());
            }
        }
    }
}
