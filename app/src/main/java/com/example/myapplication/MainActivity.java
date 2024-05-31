package com.example.myapplication;


import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
    SharedPreferences data;

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = data.edit();
        editor.putString("money", money.toString());
        editor.clear();
        editor.apply();
    }

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

        init();
        prevmoney = money;
        mon.setText(textshow(money));

        Handler handler = new Handler();
        final Runnable r = new Runnable(){
            public void run(){
                if(!MainActivity.prevmoney.equals(MainActivity.money)){
                    MainActivity.prevmoney = MainActivity.money;
                    MainActivity.mon.setText(MainActivity.textshow(MainActivity.money));
                }
                handler.postDelayed(this, 10);
            }
        };
        handler.postDelayed(r, 10);
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

    private void init(){
        data = getSharedPreferences("MainData", MODE_PRIVATE);
        money = BigDecimal.valueOf(Long.parseLong(data.getString("money", "0")));
    }
}

//class ChangeThread implements Runnable {
//
//    @Override
//    public void run(){
//
//        if(!MainActivity.prevmoney.equals(MainActivity.money)){
//                MainActivity.prevmoney = MainActivity.money;
//                MainActivity.mon.setText(MainActivity.textshow(MainActivity.money));
//        }
//
//    }
//}

