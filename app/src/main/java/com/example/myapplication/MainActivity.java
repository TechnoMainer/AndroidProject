package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.math.BigDecimal;


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

        money = BigDecimal.valueOf(1000);
        prevmoney = money;

        ChangeThread changeThread = new ChangeThread();
        changeThread.start();
        AchiveFrag af = new AchiveFrag();



        achivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fm.beginTransaction();
                if(!af.isAdded()) {
                    Log.d("DD", "opened");
                    ft.replace(R.id.achivments2, af);

                }
                else{
                    Log.d("DD", "closed");
                    ft.hide(af);

                }
                ft.commit();
            }});
    }
}

class ChangeThread extends Thread{

    @Override
    public void run(){
        while (true){
            if(!MainActivity.prevmoney.equals(MainActivity.money)){
                MainActivity.prevmoney = MainActivity.money;
                MainActivity.mon.setText(String.valueOf(MainActivity.money.toBigInteger()));
            }
        }
    }
}

