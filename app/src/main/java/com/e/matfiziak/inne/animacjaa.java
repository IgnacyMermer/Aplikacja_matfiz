package com.e.matfiziak.inne;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.matfiziak.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class animacjaa extends AppCompatActivity {
ProgressBar progressBar;
        TextView textView;
        BottomNavigationView bottomNavigationView;
        TextView pole;
        TextView pole2;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#2B2A2A"), PorterDuff.Mode.SRC_IN);
        textView = findViewById(R.id.pole_tekstowe_progress);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();
final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        pole = findViewById(R.id.tekstDoAnimacji);
        pole2 = findViewById(R.id.tekstDoAnimacji2);
        Runnable r = new Runnable() {
@Override
public void run() {
        long futureTime = System.currentTimeMillis()+1000;
        int kat = -90;
synchronized (this) {
        try {
        rotate.setDuration(6000);
        pole.startAnimation(rotate);
        }
        catch (Exception ex) {
        Log.i("wynik2", ex.getMessage().toString());
        }
        }
        }
        };
final Thread myThread = new Thread(r);
        myThread.start();
        Runnable r2 = new Runnable() {
@Override
public void run() {
synchronized (this){
        try {
        TextView pole11 = findViewById(R.id.tekstDoAnimacji2);
        Thread.sleep(1275);
        pole11.setText("MathMate");
        Thread.sleep(600);
        TextView pole12 = findViewById(R.id.tekstDoAnimacji3);
        pole12.setText("MathMate");
        Thread.sleep(500);
        TextView pole13 = findViewById(R.id.tekstDoAnimacji4);
        pole13.setText("MathMate");
        Thread.sleep(475);
        TextView pole14 = findViewById(R.id.tekstDoAnimacji5);
        pole14.setText("MathMate");
        Thread.sleep(450);
        TextView pole15 = findViewById(R.id.tekstDoAnimacji6);
        pole15.setText("MathMate");
        Thread.sleep(600);
        TextView pole16 = findViewById(R.id.tekstDoAnimacji7);
        pole16.setText("MathMate");
        Thread.sleep(750);
        TextView pole17 = findViewById(R.id.tekstDoAnimacji8);
        pole17.setText("MathMate");
        }
        catch (Exception ex){

        }
        }
        }
        };
        Thread myThread2 = new Thread(r2);
        myThread2.start();
        }
public void progressAnimation(){
        progress_bar_anim anim = new progress_bar_anim(this, progressBar, textView,pole2, 0f,100f);
        anim.setDuration(5000);
        progressBar.setAnimation(anim);
        }
}
