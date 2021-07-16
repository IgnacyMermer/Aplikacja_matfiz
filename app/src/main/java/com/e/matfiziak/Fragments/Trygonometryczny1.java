package com.e.matfiziak.Fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.matfiziak.R;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;

import static java.lang.StrictMath.sqrt;

public class Trygonometryczny1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trygonometryczny1, container,false);
        ScrollView relativeLayout = view.findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        final EditText bokA = view.findViewById(R.id.bokAtrygonometrycznyPole);
        final EditText bokB = view.findViewById(R.id.bokBtrygonometrycznyPole);
        final EditText bokC = view.findViewById(R.id.bokCtrygonometrycznyPole);
        final EditText katA = view.findViewById(R.id.katAtrygonometrycznyPole);
        final EditText katB = view.findViewById(R.id.katBtrygonometrycznyPole);
        final EditText bokA2 = view.findViewById(R.id.bokAtrygonometryczny);
        final EditText bokB2 = view.findViewById(R.id.bokBtrygonometryczny);
        final EditText bokC2 = view.findViewById(R.id.bokCtrygonometryczny);
        final TextView pole = view.findViewById(R.id.poleTrygonometryczny);
        Button buttonCzysc = view.findViewById(R.id.czyscicielTrygonometryczny);
        Button buttonWynik = view.findViewById(R.id.buttonObliczTrygonometryczny);
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bokA3 = "";
                if(!bokA.getText().toString().equals("")){
                    bokA3 = bokA.getText().toString();
                }
                else if(!bokA2.getText().toString().equals("")){
                    bokA3 = bokA2.getText().toString();
                }
                String bokB3 = "";
                if(!bokB.getText().toString().equals("")){
                    bokB3 = bokB.getText().toString();
                }
                else if(!bokB2.getText().toString().equals("")){
                    bokB3 = bokB2.getText().toString();
                }
                String bokC3 = "";
                if(!bokC.getText().toString().equals("")){
                    bokC3 = bokC.getText().toString();
                }
                else if(!bokC2.getText().toString().equals("")){
                    bokC3 = bokC2.getText().toString();
                }
                String katA3 = "";
                if(!katA.getText().toString().equals("")){
                    katA3 = katA.getText().toString();
                }
                String katB3 = "";
                if(!katB.getText().toString().equals("")){
                    katB3 = katB.getText().toString();
                }
                Double pole2=null;
                if((!bokA3.equals(""))&&(!bokB3.equals(""))){
                    bokA.setText(bokA3);
                    bokA2.setText(bokA3);
                    bokB.setText(bokB3);
                    bokB2.setText(bokB3);
                    Double a = Double.parseDouble(bokA3);
                    Double b = Double.parseDouble(bokB3);
                    Double a2=a*a;
                    Double b2=b*b;
                    Double c = a2+b2;
                    c=sqrt(c);
                    bokC.setText(""+c);
                    bokC2.setText(""+c);
                    Double sinA = b/c;
                    Double stopnie = Math.asin(sinA);
                    stopnie = Math.toDegrees(stopnie);
                    String x3 = funkcjePrzelicznikowe.intowanie(stopnie);
                    katA.setText(x3);
                    Double sinB = a/c;
                    sinB = Math.asin(sinB);
                    sinB = Math.toDegrees(sinB);
                    String x4 = funkcjePrzelicznikowe.intowanie(sinB);
                    katB.setText(x4);
                    pole2 = a*b/2;
                    String x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokA3.equals(""))&&(!bokC3.equals(""))){
                    bokA.setText(bokA3);
                    bokA2.setText(bokA3);
                    bokC.setText(bokC3);
                    bokC2.setText(bokC3);
                    Double a = Double.parseDouble(bokA3);
                    Double c = Double.parseDouble(bokC3);
                    Double a2=a*a;
                    Double c2=c*c;
                    Double b = c2-a2;
                    b=sqrt(b);
                    bokB.setText(""+b);
                    bokB2.setText(""+b);
                    Double sinA = b/c;
                    Double stopnie = Math.asin(sinA);
                    stopnie = Math.toDegrees(stopnie);
                    String x3 = funkcjePrzelicznikowe.intowanie(stopnie);
                    katA.setText(x3);
                    Double sinB = a/c;
                    sinB = Math.asin(sinB);
                    sinB = Math.toDegrees(sinB);
                    String x4 = funkcjePrzelicznikowe.intowanie(sinB);
                    katB.setText(x4);
                    pole2 = a*b/2;
                    String x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokB3.equals(""))&&(!bokC3.equals(""))){
                    bokB.setText(bokB3);
                    bokB2.setText(bokB3);
                    bokC.setText(bokC3);
                    bokC2.setText(bokC3);
                    Double b = Double.parseDouble(bokB3);
                    Double c = Double.parseDouble(bokC3);
                    Double b2=b*b;
                    Double c2=c*c;
                    Double a = c2-b2;
                    a=sqrt(a);
                    bokA.setText(""+a);
                    bokA2.setText(""+a);
                    Double sinA = b/c;
                    Double stopnie = Math.asin(sinA);
                    stopnie = Math.toDegrees(stopnie);
                    String x3 = funkcjePrzelicznikowe.intowanie(stopnie);
                    katA.setText(x3);
                    Double sinB = a/c;
                    sinB = Math.asin(sinB);
                    sinB = Math.toDegrees(sinB);
                    String x4 = funkcjePrzelicznikowe.intowanie(sinB);
                    katB.setText(x4);
                    pole2 = a*b/2;
                    String x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokA3.equals(""))&&(!katA3.equals(""))){
                    bokA.setText(bokA3);
                    bokA2.setText(bokA3);
                    Double a = Double.parseDouble(bokA3);
                    Double kat = Double.parseDouble(katA3);
                    Double b,c,kat2;
                    kat2 = 90-kat;
                    String x6 = funkcjePrzelicznikowe.intowanie(kat2);
                    katB.setText(x6);
                    kat = Math.toRadians(kat);
                    Double d = Math.tan(kat);
                    b=a*d;
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    bokB.setText(x5);
                    bokB2.setText(x5);
                    d=Math.cos(kat);
                    c=a/d;
                    x5 = funkcjePrzelicznikowe.intowanie(c);
                    bokC.setText(x5);
                    bokC2.setText(x5);
                    pole2 = a*b/2;
                    x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokB3.equals(""))&&(!katB3.equals(""))){
                    bokB.setText(bokB3);
                    bokB2.setText(bokB3);
                    Double b = Double.parseDouble(bokB3);
                    Double kat = Double.parseDouble(katB3);
                    Double a,c,kat2;
                    kat2 = 90-kat;
                    String x6 = funkcjePrzelicznikowe.intowanie(kat2);
                    katA.setText(x6);
                    kat = Math.toRadians(kat);
                    Double d = Math.tan(kat);
                    a=b*d;
                    String x5 = funkcjePrzelicznikowe.intowanie(a);
                    bokA.setText(x5);
                    bokA2.setText(x5);
                    d=Math.cos(kat);
                    c=b/d;
                    x5 = funkcjePrzelicznikowe.intowanie(c);
                    bokC.setText(x5);
                    bokC2.setText(x5);
                    pole2 = b*a/2;
                    x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokC3.equals(""))&&(!katA3.equals(""))){
                    bokC.setText(bokC3);
                    bokC2.setText(bokC3);
                    Double c = Double.parseDouble(bokC3);
                    Double kat = Double.parseDouble(katA3);
                    Double a,b,kat2;
                    kat2 = 90-kat;
                    String x6 = funkcjePrzelicznikowe.intowanie(kat2);
                    katB.setText(x6);
                    kat = Math.toRadians(kat);
                    b = Math.sin(kat);
                    b=b*c;
                    String x5 = funkcjePrzelicznikowe.intowanie(b);
                    bokB.setText(x5);
                    bokB2.setText(x5);
                    a=Math.cos(kat);
                    a=a*c;
                    x5 = funkcjePrzelicznikowe.intowanie(a);
                    bokA.setText(x5);
                    bokA2.setText(x5);
                    pole2 = a*b/2;
                    x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokC3.equals(""))&&(!katB3.equals(""))){
                    bokC.setText(bokC3);
                    bokC2.setText(bokC3);
                    Double c = Double.parseDouble(bokC3);
                    Double kat = Double.parseDouble(katB3);
                    Double a,b,kat2;
                    kat2 = 90-kat;
                    String x6 = funkcjePrzelicznikowe.intowanie(kat2);
                    katA.setText(x6);
                    kat = Math.toRadians(kat);
                    a = Math.sin(kat);
                    a=a*c;
                    String x5 = funkcjePrzelicznikowe.intowanie(a);
                    bokA.setText(x5);
                    bokA2.setText(x5);
                    b=Math.cos(kat);
                    b=b*c;
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    bokB.setText(x5);
                    bokB2.setText(x5);
                    pole2 = a*b/2;
                    x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!katA3.equals(""))&&(!bokB3.equals(""))){
                    bokB.setText(bokB3);
                    bokB2.setText(bokB3);
                    Double b = Double.parseDouble(bokB3);
                    Double kat = Double.parseDouble(katA3);
                    Double kat2, a,c;
                    kat2 = 90-kat;
                    String x5 = funkcjePrzelicznikowe.intowanie(kat2);
                    katB.setText(x5);
                    kat = Math.toRadians(kat);
                    a=Math.tan(kat);
                    a=b/a;
                    x5 = funkcjePrzelicznikowe.intowanie(a);
                    bokA.setText(x5);
                    bokA2.setText(x5);
                    c=Math.sin(kat);
                    c=b/c;
                    x5 = funkcjePrzelicznikowe.intowanie(c);
                    bokC.setText(x5);
                    bokC2.setText(x5);
                    pole2 = a*b/2;
                    x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
                else if((!bokA3.equals(""))&&(!katB3.equals(""))){
                    bokA.setText(bokA3);
                    bokA2.setText(bokA3);
                    Double a = Double.parseDouble(bokA3);
                    Double kat = Double.parseDouble(katB3);
                    Double kat2, b,c;
                    kat2 = 90-kat;
                    String x5 = funkcjePrzelicznikowe.intowanie(kat2);
                    katA.setText(x5);
                    kat = Math.toRadians(kat);
                    b=Math.tan(kat);
                    b=a/b;
                    x5 = funkcjePrzelicznikowe.intowanie(b);
                    bokB.setText(x5);
                    bokB2.setText(x5);
                    c=Math.sin(kat);
                    c=a/c;
                    x5 = funkcjePrzelicznikowe.intowanie(c);
                    bokC.setText(x5);
                    bokC2.setText(x5);
                    pole2 = a*b/2;
                    x5 = funkcjePrzelicznikowe.intowanie(pole2);
                    pole.setText(x5);
                }
            }
        });
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bokA.setText("");
                bokA2.setText("");
                bokB.setText("");
                bokB2.setText("");
                bokC.setText("");
                bokC2.setText("");
                katA.setText("");
                katB.setText("");
                pole.setText("Pole");
            }
        });
        return view;
    }
}