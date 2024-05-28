package com.example.myapplication;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Map;

public class Achievment {

    String name;
    String desc;
    String rewtype;
    BigDecimal rew;
    String reqtype;
    BigDecimal req;
    Factory fac;
    boolean completed;

    public Achievment(String name, String desc, String rewtype, BigDecimal rew, String reqtype, BigDecimal req, @Nullable Factory fac) {
        this.name = name;
        this.desc = desc;
        this.rewtype = rewtype;
        this.rew = rew;
        this.reqtype = reqtype;
        this.req = req;
        completed = false;
        this.fac = fac;
    }

    public void check(){
        if (!completed){
            switch (reqtype){
                case "money":
                    if(MainActivity.money.compareTo(req)>=0){
                        completed = true;
                    }
                    return;
                case "rebirth":
                    return;
                default:
                    return;
            }
        }
    }
}
