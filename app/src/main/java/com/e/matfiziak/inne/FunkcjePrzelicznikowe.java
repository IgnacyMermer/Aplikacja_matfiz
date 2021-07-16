package com.e.matfiziak.inne;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.e.matfiziak.Czworokaty;
import com.e.matfiziak.FizykaKalkulator;
import com.e.matfiziak.Grawitacja;
import com.e.matfiziak.Keppler;
import com.e.matfiziak.Kwadrat;
import com.e.matfiziak.MatematykaKalkulator;
import com.e.matfiziak.ModelBohra;
import com.e.matfiziak.Pole_trojkata_rownoramiennego;
import com.e.matfiziak.Pole_trojkata_roznobocznego;
import com.e.matfiziak.Predkosc;
import com.e.matfiziak.Prostokat;
import com.e.matfiziak.Przyszpieszenie;
import com.e.matfiziak.PrzyszpieszenieZwykle;
import com.e.matfiziak.Romb;
import com.e.matfiziak.Rownoleglobok;
import com.e.matfiziak.RuchPoOkregu;
import com.e.matfiziak.Szkola;
import com.e.matfiziak.Trapezy;
import com.e.matfiziak.Trojkaty;
import com.e.matfiziak.ZasadyDynamiki;
import com.e.matfiziak.pole_trojkata;
import com.e.matfiziak.trapez_prostokatny;

import java.util.ArrayList;

import static java.lang.StrictMath.sqrt;

public class FunkcjePrzelicznikowe {
    public Double dlugosc(Double a, String x){
        Double b;
        b=a;
        if(x.equals("dm")){
            b=b*10;
        }
        else if(x.equals("m")){
            b=b*100;
        }
        else if(x.equals("km")){
            b=b*100000;
        }
        return b;
    }
    public Double dlugoscWynik(Double a, String x){
        Double b;
        b=a;
        if(x.equals("dm")){
            b=b/10;
        }
        else if(x.equals("m")){
            b=b/100;
        }
        else if(x.equals("km")){
            b=b/100000;
        }
        return b;
    }
    public Double dlugoscPred(Double a, String x){
        Double b;
        b=a;
        if(x.equals("dm")){
            b=b/10;
        }
        else if(x.equals("cm")){
            b=b/100;
        }
        else if(x.equals("km")){
            b=b*1000;
        }
        return b;
    }
    public Double dlugoscPredWynik(Double a, String x){
        Double b;
        b=a;
        if(x.equals("dm")){
            b=b*10;
        }
        else if(x.equals("cm")){
            b=b*100;
        }
        else if(x.equals("km")){
            b=b/1000;
        }
        return b;
    }
    public Double cisnienie(Double a, String x){
        Double b=a;
        if(x.equals("hPa")){
            b=b*100;
        }
        else if(x.equals("kPa")){
            b=b*1000;
        }
        else if(x.equals("atm")){
            b=b*98066.5;
        }
        else if(x.equals("b")){
            b=b*100000;
        }
        else if(x.equals("MPa")){
            b=b*1000000;
        }
        return b;
    }
    public Double cisnienieWynik(Double a, String x){
        Double b=a;
        if(x.equals("hPa")){
            b=b/100;
        }
        else if(x.equals("kPa")){
            b=b/1000;
        }
        else if(x.equals("atm")){
            b=b/98066.5;
        }
        else if(x.equals("b")){
            b=b/100000;
        }
        else if(x.equals("MPa")){
            b=b/1000000;
        }
        return b;
    }
    public Double poleWynik(Double a , String x){
        Double b=a;
        if(x.equals("mm2")){
            b=b*1000000;
        }
        else if(x.equals("dm2")){
            b=b*100;
        }
        else if(x.equals("cm2")){
            b=b*10000;
        }
        else if(x.equals("a")){
            b=b/100;
        }
        else if(x.equals("ha")){
            b=b/10000;
        }
        return b;
    }
    public Double pole(Double a, String x){
        Double b=a;
        if(x.equals("mm2")){
            b=b/1000000;
        }
        else if(x.equals("dm2")){
            b=b/100;
        }
        else if(x.equals("cm2")){
            b=b/10000;
        }
        else if(x.equals("a")){
            b=b*100;
        }
        else if(x.equals("ha")){
            b=b*10000;
        }
        return b;
    }
    public Double predkosc(Double a, String x){
        Double b=a;
        if(x.equals("m/min")){
            b=b/60;
        }
        else if(x.equals("km/s")){
            b=b*1000;
        }
        else if(x.equals("km/m")){
            b=b*1000/60;
        }
        else if(x.equals("km/h")){
            b=b/3.6;
        }
        return b;
    }
    public Double predkoscWynik(Double a, String x){
        Double b=a;
        if(x.equals("m/min")){
            b=b*60;
        }
        else if(x.equals("km/s")){
            b=b/1000;
        }
        else if(x.equals("km/m")){
            b=b/1000*60;
        }
        else if(x.equals("km/h")){
            b=b*3.6;
        }
        return b;
    }
    public Double czas(Double a, String x){
        if(x.equals("min")){
            a=a*60;
        }
        else if(x.equals("h")){
            a=a*3600;
        }
        return a;
    }
    public Double czasWynik(Double a, String x){
        if(x.equals("min")){
            a=a/60;
        }
        else if(x.equals("h")){
            a=a/3600;
        }
        return a;
    }
    public Double droga(Double a, String x){
        if(x.equals("km")){
            a=a*1000;
        }
        else if(x.equals("dm")){
            a=a/10;
        }
        else if(x.equals("cm")){
            a=a/100;
        }
        return a;
    }
    public Double drogaWynik(Double a, String x){
        if(x.equals("km")){
            a=a/1000;
        }
        else if(x.equals("dm")){
            a=a*10;
        }
        else if(x.equals("cm")){
            a=a*100;
        }
        return a;
    }
    public Double powierzchnia(Double a, String x){
        if (x.equals("dm3")) {
            a = a / 1000;
        }
        else if (x.equals("cm3")) {
            a = a / 1000000;
        }
        return a;
    }
    public Double masaWynik(Double a, String x){
        if (x.equals("dag")) {
            a = a * 100;
        }
        else if (x.equals("g")) {
            a = a * 1000;
        }
        else if (x.equals("t")) {
            a = a / 1000;
        }
        return a;
    }
    public Double gestosc(Double a, String x){
        if (x.equals("g/cm3")) {
            a = a * 1000;
        }
        else if (x.equals("kg/dm3")) {
            a = a * 1000;
        }
        else if (x.equals("kg/cm3")) {
            a = a * 1000000;
        }
        return a;
    }
    public Double gestoscWynik(Double a, String x){
        if (x.equals("g/cm3")) {
            a = a / 1000;
        }
        else if (x.equals("kg/dm3")) {
            a = a / 1000;
        }
        else if (x.equals("kg/cm3")) {
            a = a / 1000000;
        }
        return a;
    }
    public Double praca(Double a, String x){
        if(x.equals("kJ")){
            a=a*1000;
        }
        else if(x.equals("cal")){
            a=a*4.186;
        }
        else if(x.equals("kcal")){
            a=a*4185.5;
        }
        return a;
    }
    public Double pracaWynik(Double a, String x){
        if(x.equals("kJ")){
            a=a/1000;
        }
        else if(x.equals("cal")){
            a=a/4.186;
        }
        else if(x.equals("kcal")){
            a=a/4185.5;
        }
        return a;
    }
    public Double powierzchniaWynik(Double a, String x){
        if (x.equals("dm3")) {
            a = a * 1000;
        }
        else if (x.equals("cm3")) {
            a = a * 1000000;
        }
        return a;
    }
    public Double masa(Double a, String x){
        if (x.equals("dag")) {
            a = a / 100;
        }
        else if (x.equals("g")) {
            a = a / 1000;
        }
        else if (x.equals("t")) {
            a = a * 1000;
        }
        return a;
    }
    public Double moc(Double a, String x){
        if(x.equals("mW")){
            a = a/1000;
        }
        else if(x.equals("kM")){
            a = a*735.5;
        }
        else if(x.equals("MW")){
            a = a*1000000;
        }
        else if(x.equals("kW")){
            a = a*1000;
        }
        return a;
    }
    public Double mocWynik(Double a, String x){
        if(x.equals("mW")){
            a=a*1000;
        }
        else if(x.equals("kM")){
            a = a/735.5;
        }
        else if(x.equals("MW")){
            a = a/1000000;
        }
        else if(x.equals("kW")){
            a = a/1000;
        }
        return a;
    }
    public Double sila(Double a, String x){
        if(x.equals("mN")){
            a=a/1000;
        }
        else if(x.equals("kN")){
            a=a*1000;
        }
        else if(x.equals("MN")){
            a=a*1000000;
        }
        return a;
    }
    public Double silaWynik(Double a, String x){
        if(x.equals("mN")){
            a=a*1000;
        }
        else if(x.equals("kN")){
            a=a/1000;
        }
        else if(x.equals("MN")){
            a=a/1000000;
        }
        return a;
    }
    public String intowanie(Double x){
        Double a = x;
        int d=0;
        double b = a/sqrt(3);
        while(b<100){
            b=b*10;
        }
        int c=(int)(b);
        if(c%100==0||c%100==1||c%100==99||c%100==98){
            a=a/sqrt(3);
            d++;
        }
        else{
            b=a/sqrt(2);
            while(b<100){
                b=b*10;
            }
            c=(int)(b);
            if(c%100==0||c%100==1||c%100==99||c%100==98){
                a=a/sqrt(2);
                d=d+2;
            }
        }
        String y = a.toString();
        int j=0;
        for(int i=0;i<y.length()-1;i++){
            if(y.charAt(i)=='.'){
                j=i;
            }
        }
        if(j==0){
            j=y.length();
        }
        if((y.charAt(y.length()-2)=='E')||(y.charAt(y.length()-3))=='E'){
            if((y.charAt(j+1)=='0')&&(y.charAt(j)=='.')) {
                int u = y.length() - j-3;
                if (u > 3) {
                    if (y.charAt(j + 2) == '0' && y.charAt(j + 3) == '0') {
                        y = y.substring(0, j) + y.substring(y.length() - 3, y.length());
                    }
                    else if (y.charAt(j + 2) == '0' ) {
                        y = y.substring(0, j+5) + y.substring(y.length() - 3, y.length());
                    }
                }
                else if (u == 3) {
                    if (y.charAt(j + 2) == '0') {
                        y = y.substring(0, j) + y.substring(y.length() - 3, y.length());
                    }
                }
                else if (u == 2) {
                    y = y.substring(0, j) + y.substring(y.length() - 3, y.length());
                }
            }
            else if(y.length()>7){
                y=y.substring(0,5)+y.substring(y.length()-3, y.length());
            }
        }
        else if(y.charAt(y.length()-3)=='9'&&(y.charAt(y.length()-4)=='9')){
            double g = Double.parseDouble(y);
            int g2=0,g3;
            while(g<1000000){
                g=g*10;
                g2++;
            }
            g3=(int)(g);
            if(g3%1000>980){
                g3=(int)(g);
                int g5 = (1000-(g3%1000));
                g3+=g5;
                if(g2>=1){
                    g=g3;
                }
                for(int i=0;i<g2;i++){
                    g=g/10;
                }
                if((g*10)%10==0){
                    g3=(int)(g);
                    y=Integer.toString(g3);
                }
                else{
                    y = Double.toString(g);
                }
            }
            else{
                int y3 = y.length();
                if(y3>8){
                    y3=8;
                }
                y=y.substring(0,y3);
            }
        }
        else if((y.charAt(j+1)=='0')&&(y.charAt(j)=='.')){
            int u = y.length()-j;
            if(u>3){
                if(y.charAt(j+2)=='0'&&y.charAt(j+3)=='0'&&y.charAt(j+4)=='0'){
                    y=y.substring(0,j);
                }
                else{
                    if(y.length()>8){
                        y=y.substring(0,8);
                    }
                }
            }
            else if(u==3){
                if(y.charAt(j+2)=='0'){
                    y=y.substring(0,j);
                }
            }
            else if(u==2){
                y=y.substring(0,j);
            }
        }
        else{
            Log.i("wynik", y);
            int y3 = y.length();
            if(y3>8){
                y3=8;
            }
            y=y.substring(0,y3);

        }
        if(y.charAt(0)=='-'){
            y=y.substring(1);
        }
        if(d==1){
            y+=" 3";
        }
        else if(d==2){
            y+=" 2";
        }
        return y;
    }
    public Intent dolneMenu(String incomingName, Context mContext, ArrayList incomingList, String incomingBoolean, String incomingCheckboxPredkosc) {
        Intent i1 = null;
        if(incomingName==null){
            incomingName="Szkola";
        }
        if (incomingName.equals("ZasadyDynamiki")) {
            i1 = new Intent(mContext, ZasadyDynamiki.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Grawitacja")) {
            i1 = new Intent(mContext, Grawitacja.class);
        } else if (incomingName.equals("Szkola")) {
            i1 = new Intent(mContext, Szkola.class);
        } else if (incomingName.equals("Keppler")) {
            i1 = new Intent(mContext, Keppler.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("FizykaKalkulator")) {
            i1 = new Intent(mContext, FizykaKalkulator.class);
        } else if (incomingName.equals("MatematykaKalkulator")) {
            i1 = new Intent(mContext, MatematykaKalkulator.class);
        } else if (incomingName.equals("ModelBohra")) {
            i1 = new Intent(mContext, ModelBohra.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("poleTrojkata")) {
            i1 = new Intent(mContext, pole_trojkata.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Predkosc")) {
            i1 = new Intent(mContext, Predkosc.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
            i1.putExtra("checkbox2", incomingCheckboxPredkosc);
        } else if (incomingName.equals("Przyszpieszenie")) {
            i1 = new Intent(mContext, Przyszpieszenie.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("PrzyszpieszenieZwykle")) {
            i1 = new Intent(mContext, PrzyszpieszenieZwykle.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("RuchPoOkregu")) {
            i1 = new Intent(mContext, RuchPoOkregu.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Pole_trojkata_rownoramiennego")) {
            i1 = new Intent(mContext, Pole_trojkata_rownoramiennego.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Pole_trojkata_roznobocznego")) {
            i1 = new Intent(mContext, Pole_trojkata_roznobocznego.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Trojkaty")) {
            i1 = new Intent(mContext, Trojkaty.class);
        } else if (incomingName.equals("Kwadrat")) {
            i1 = new Intent(mContext, Kwadrat.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Prostokat")) {
            i1 = new Intent(mContext, Prostokat.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Romb")) {
            i1 = new Intent(mContext, Romb.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Rownoleglobok")) {
            i1 = new Intent(mContext, Rownoleglobok.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Czworokaty")) {
            i1 = new Intent(mContext, Czworokaty.class);
        } else if (incomingName.equals("trapezProstokatny")) {
            i1 = new Intent(mContext, trapez_prostokatny.class);
            i1.putExtra("lista", incomingList);
            i1.putExtra("checkbox", incomingBoolean);
        } else if (incomingName.equals("Trapezy")) {
            i1 = new Intent(mContext, Trapezy.class);
        }
        return i1;
    }
}