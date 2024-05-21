package com.example.myapplication;

import java.math.BigDecimal;
import java.util.Map;

public class Achievment {

    String name;
    String desc;
    String rewtype;
    BigDecimal rew;
    String reqtype;
    BigDecimal req;
    boolean completed;

    public Achievment(String name, String desc, String rewtype, BigDecimal rew, String reqtype, BigDecimal req) {
        this.name = name;
        this.desc = desc;
        this.rewtype = rewtype;
        this.rew = rew;
        this.reqtype = reqtype;
        this.req = req;
        completed = false;
    }

    public void check(){
        if (!completed){
            switch (reqtype){
                case "money":
                    if(Automation.money.compareTo(req)>0){
                        completed = true;
                    }
                default:
                    return;
            }
        }
    }
}
