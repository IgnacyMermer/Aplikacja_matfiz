package com.e.matfiziak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.matfiziak.inne.User;
import com.e.matfiziak.inne.dane;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
        String imageUrl, username;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.animacjaaa);
            final Animation animationTr = new TranslateAnimation(0,0,0,-200);
            animationTr.setDuration(500);
            animationTr.setFillAfter(true);
            final Animation animationTr2 = new TranslateAnimation(0,0,-200,0);
            animationTr2.setDuration(1200);
            animationTr2.setFillAfter(true);
            final TextView textView = findViewById(R.id.pierwszaLitera);
            final TextView textView1 = findViewById(R.id.drugaLitera);
            final TextView textView2 = findViewById(R.id.trzeciaLitera);
            final TextView textView3 = findViewById(R.id.czwartaLitera);
            final TextView textView4 = findViewById(R.id.piataLitera);
            final TextView textView5 = findViewById(R.id.szostaLitera);
            final TextView textView6 = findViewById(R.id.siodmaLitera);
            final TextView textView7 = findViewById(R.id.osmaLitera);
            final Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
            final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.fade1);
            final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.fade2);
            final Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.fade3);
            final Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.fade4);
            final Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.fade5);
            final Animation animation6 = AnimationUtils.loadAnimation(this, R.anim.fade6);
            final Animation animation7 = AnimationUtils.loadAnimation(this, R.anim.fade7);
            final Animation animation10 = AnimationUtils.loadAnimation(this, R.anim.fade10);
            final Animation animation11 = AnimationUtils.loadAnimation(this, R.anim.fade11);
            final Animation animation12 = AnimationUtils.loadAnimation(this, R.anim.fade12);
            final Animation animation13 = AnimationUtils.loadAnimation(this, R.anim.fade13);
            final Animation animation14 = AnimationUtils.loadAnimation(this, R.anim.fade14);
            final Animation animation15 = AnimationUtils.loadAnimation(this, R.anim.fade15);
            final Animation animation16 = AnimationUtils.loadAnimation(this, R.anim.fade16);
            final Animation animation17 = AnimationUtils.loadAnimation(this, R.anim.fade17);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    synchronized (this){
                        try {
                            textView.startAnimation(animation);
                            textView1.startAnimation(animation1);
                            textView2.startAnimation(animation2);
                            textView3.startAnimation(animation3);
                            textView4.startAnimation(animation4);
                            textView5.startAnimation(animation5);
                            textView6.startAnimation(animation6);
                            textView7.startAnimation(animation7);
                            Thread.sleep(1500);
                            textView7.startAnimation(animation10);
                            textView6.startAnimation(animation11);
                            textView5.startAnimation(animation12);
                            textView4.startAnimation(animation13);
                            textView3.startAnimation(animation14);
                            textView2.startAnimation(animation15);
                            textView1.startAnimation(animation16);
                            textView.startAnimation(animation17);
                            Thread.sleep(2000);
                            Intent intent = new Intent(MainActivity.this, StronaGlowna.class);
                            intent.putExtra("imageUrl", imageUrl);
                            intent.putExtra("nick",username);
                            startActivity(intent);
                        }
                        catch (Exception ex){
                            Log.i("wynik", ex.getMessage().toString());
                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                    if(fUser!=null){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(fUser.getUid());
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                username = user.getUsername();
                                imageUrl = user.getImageUrl();
                                dane dane1 = new dane();
                                dane1.nick = username;
                                dane1.imageUrl = imageUrl;
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                    }
                }
            };
            Thread thread1 = new Thread(runnable1);
            thread1.start();
            /*Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    try{
                        textView.startAnimation(animationTr);
                        animationTr.setDuration(600);
                        textView1.startAnimation(animationTr);
                        animationTr.setDuration(700);
                        textView2.startAnimation(animationTr);
                        animationTr.setDuration(800);
                        textView3.startAnimation(animationTr);
                        animationTr.setDuration(900);
                        textView4.startAnimation(animationTr);
                        animationTr.setDuration(1000);
                        textView5.startAnimation(animationTr);
                        animationTr.setDuration(1100);
                        textView6.startAnimation(animationTr);
                        animationTr.setDuration(1200);
                        textView7.startAnimation(animationTr);
                        Thread.sleep(1500);
                        textView7.startAnimation(animationTr2);
                        animationTr2.setDuration(1100);
                        textView6.startAnimation(animationTr2);
                        animationTr2.setDuration(1000);
                        textView5.startAnimation(animationTr2);
                        animationTr2.setDuration(900);
                        textView4.startAnimation(animationTr2);
                        animationTr2.setDuration(800);
                        textView3.startAnimation(animationTr2);
                        animationTr2.setDuration(700);
                        textView2.startAnimation(animationTr2);
                        animationTr2.setDuration(600);
                        textView1.startAnimation(animationTr2);
                        animationTr2.setDuration(500);
                        textView.startAnimation(animationTr2);
                    }
                    catch (Exception ex){
                        Log.i("wynik",ex.getMessage().toString());
                    }
                }
            };
            Thread thread1 = new Thread(runnable1);
            thread1.start();*/
        }
}