package com.e.matfiziak.inne;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class progress_bar_anim extends Animation {
    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;
    private TextView pole;
    public progress_bar_anim(Context context, ProgressBar progressBar, TextView textView, TextView pole, float from, float to){
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
        this.pole = pole;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from+(to-from)*interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value+" %");
        if(value==to){
            Intent i = new Intent(context, animacjaa.class);
            context.startActivity(i);
            Animatoo.animateInAndOut(context);
        }
    }
}