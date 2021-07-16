package com.e.matfiziak.Fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.R;

public class Trygonometryczny2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trygonometryczny2, container, false);
        ScrollView scrollView = view.findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animation = (AnimationDrawable) scrollView.getBackground();
        animation.setEnterFadeDuration(2000);
        animation.setExitFadeDuration(4000);
        animation.start();
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        final EditText katA = view.findViewById(R.id.katATrygonometryczny2);
        final EditText sinusA = view.findViewById(R.id.sinusATrygonometryczny2);
        final EditText cosinusA = view.findViewById(R.id.cosinusATrygonometryczny2);
        final EditText tangensA = view.findViewById(R.id.TangensATrygonometryczny2);
        final EditText cotangensA = view.findViewById(R.id.CotangensATrygonometryczny2);
        Button czysc = view.findViewById(R.id.buttonCzyscTrygonometryczny2);
        Button oblicz = view.findViewById(R.id.buttonObliczTrygonometryczny2);
        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!katA.getText().toString().equals("")){
                    Double a = Double.parseDouble(katA.getText().toString());
                    a=Math.toRadians(a);
                    Double b = Math.sin(a);
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    sinusA.setText(x5);
                    b=Math.cos(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cosinusA.setText(x5);
                    b=Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    tangensA.setText(x5);
                    b=1.0/Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cotangensA.setText(x5);
                }
                else if(!sinusA.getText().toString().equals("")){
                    Double a = Double.parseDouble(sinusA.getText().toString());
                    a = Math.asin(a);
                    Double b = Math.toDegrees(a);
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    katA.setText(x5);
                    b=Math.cos(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cosinusA.setText(x5);
                    b = Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    tangensA.setText(x5);
                    b=1.0/Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cotangensA.setText(x5);
                }
                else if(!cosinusA.getText().toString().equals("")){
                    Double a = Double.parseDouble(cosinusA.getText().toString());
                    a = Math.acos(a);
                    Double b = Math.toDegrees(a);
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    katA.setText(x5);
                    b=Math.sin(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    sinusA.setText(x5);
                    b = Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    tangensA.setText(x5);
                    b=1.0/Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cotangensA.setText(x5);
                }
                else if(!tangensA.getText().toString().equals("")){
                    Double a = Double.parseDouble(tangensA.getText().toString());
                    a = Math.atan(a);
                    Double b = Math.toDegrees(a);
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    katA.setText(x5);
                    b=Math.sin(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    sinusA.setText(x5);
                    b = Math.cos(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cosinusA.setText(x5);
                    b=1.0/Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cotangensA.setText(x5);
                }
                else if(!cotangensA.getText().toString().equals("")){
                    Double a = Double.parseDouble(cotangensA.getText().toString());
                    a = 1/a;
                    a=Math.atan(a);
                    Double b = Math.toDegrees(a);
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    katA.setText(x5);
                    b=Math.sin(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    sinusA.setText(x5);
                    b = Math.cos(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    cosinusA.setText(x5);
                    b=Math.tan(a);
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    tangensA.setText(x5);
                }
            }
        });
        czysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                katA.setText("");
                sinusA.setText("");
                cosinusA.setText("");
                tangensA.setText("");
                cotangensA.setText("");
            }
        });
        return view;
    }
}