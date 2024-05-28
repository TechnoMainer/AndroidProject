package com.example.myapplication;

import android.util.Log;

import java.math.BigDecimal;

public class Factory {

    BigDecimal income;
    String name = "None";
    BigDecimal startincome;
    int upglv = 0;
    BigDecimal upgcost;
    int ascendlv;
    int time = 9910;
    Gen gen;

    public Factory(BigDecimal income, int time) {
        this.income = income;
        this.upgcost = income;
        this.startincome = income;
        gen = new Gen(this);
    }

    public void upgrade(){

        if (upglv<100&&MainActivity.money.compareTo(upgcost)>=0) {
            Log.d("MYLOG", "UPGRADED");
            MainActivity.money = MainActivity.money.subtract(upgcost);
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
            Automation.ascendmulty+=0.4;
            income = income.multiply(BigDecimal.valueOf(Automation.ascendmulty));
            ascendlv += 1;
            upglv = 1;
            startincome = income;
            upgcost = income.multiply(BigDecimal.valueOf(1));
            time = 9910;
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
                MainActivity.money = MainActivity.money.add(factory.income.multiply(Prestige.prestigemulti));
            }
        }
    }
}
