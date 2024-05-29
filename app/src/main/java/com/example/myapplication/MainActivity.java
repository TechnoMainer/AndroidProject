package com.example.myapplication;


import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.math.BigDecimal;
import java.math.BigInteger;


public class MainActivity extends AppCompatActivity {

    static TextView mon;
    ImageView achivebutton;
    FragmentManager fm;
    public static BigDecimal prevmoney;
    public static BigDecimal money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mon = findViewById(R.id.money);
        fm = getSupportFragmentManager();
        achivebutton = findViewById(R.id.achivment);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navHostFragment.getNavController());

        money = BigDecimal.valueOf(100000);
        prevmoney = money;
        mon.setText(textshow(money));

        ChangeThread changeThread = new ChangeThread();
        changeThread.start();
        AchiveFrag af = new AchiveFrag();



        achivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fm.beginTransaction();
                Log.d("DD", "opened");
                ft.replace(R.id.achivments2, af);
                ft.commit();
            }});
    }

    static void change(){
        prevmoney = money;
        mon.setText(textshow(money));
    }

    static String textshow(BigDecimal val1){
        BigInteger val = val1.toBigInteger();
        if(val1.compareTo(BigDecimal.valueOf(1000))>=0) {
            char[] chars = val.toString().toCharArray();
            int len = chars.length % 3;
            String res = "";
            String fs = "";
            if (len==0){
                len = 3;
            }
            double f;
            for (int i = 0; i < 3; i++) {
                fs += chars[i];
            }
            f = Double.parseDouble(fs);
            f /= Math.pow(10, 3 - len);
            res = f + "e" + (chars.length - len);
            return res;
        }
        else{
            return val.toString();
        }
    }
}

class ChangeThread extends Thread implements Runnable {

    @Override
    public void run(){
        while (true){
            if(!MainActivity.prevmoney.equals(MainActivity.money)){
                MainActivity.change();
            }
        }
    }
}

