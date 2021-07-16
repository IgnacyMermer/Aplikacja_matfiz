package com.e.matfiziak.Fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

public class Tab2Fragment extends Fragment {
    private Button btnNavFrag1;
    TextView predkosc1;
    TextView predkosc2;
    TextView praca1;
    TextView praca2;
    TextView sila1;
    TextView sila2;
    TextView cisnienie1;
    TextView cisnienie2;
    TextView moc1;
    TextView moc2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        ScrollView scrollView = view.findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        predkosc1 = view.findViewById(R.id.predkosc1);
        predkosc2 = view.findViewById(R.id.predkosc2);
        final EditText predkosc1Pole = view.findViewById(R.id.predkosc1Pole);
        final TextView wynikPredkosc = view.findViewById(R.id.predkosc2Pole);
        Button buttonPredkosc = view.findViewById(R.id.buttonPredkosc);
        predkosc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(predkosc1);
                ktory="pierwszyPredkosc";
                v.showContextMenu();
            }
        });
        predkosc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(predkosc2);
                ktory="drugiPredkosc";
                v.showContextMenu();
            }
        });
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        buttonPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double b=0.0,c=0.0,e=null,d;
                String a=predkosc1Pole.getText().toString();
                Double predkosc1Int = Double.parseDouble(a);
                if(co3.equals("metrynas")){
                    b=1.0*predkosc1Int;
                }
                else if(co3.equals("metrynamin")){
                    b=predkosc1Int/60;
                }
                else if(co3.equals("kilometrynas")){
                    b=predkosc1Int*1000;
                }
                else if(co3.equals("kilometrynamin")){
                    b=predkosc1Int*1000/60;
                }
                else if(co3.equals("kilometrynah")){
                    b=predkosc1Int*1000/3600;
                }
                if(co2.equals("metrynamin")){
                    b=b*60;
                }
                else if(co2.equals("kilometrynas")){
                    b=b/1000;
                }
                else if(co2.equals("kilometrynamin")){
                    b=b/1000;
                    b=b*60;
                }
                else if(co2.equals("kilometrynah")){
                    b=b/1000;
                    b=b*3600;
                }
                String x = funkcjePrzelicznikowe.intowanie(b);
                wynikPredkosc.setText(x);
            }
        });
        sila1 =  view.findViewById(R.id.sila1);
        sila2 = view.findViewById(R.id.sila2);
        final TextView wynikSila = view.findViewById(R.id.sila2Pole);
        final EditText sila1Pole = view.findViewById(R.id.sila1Pole);
        Button buttonSila = view.findViewById(R.id.buttonSila);
        sila1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(sila1);
                ktory="pierwszySila";
                v.showContextMenu();
            }
        });
        sila2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(sila2);
                ktory="drugiSila";
                v.showContextMenu();
            }
        });
        buttonSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double b=0.0,c=0.0,e=null,d;
                String a=sila1Pole.getText().toString();
                Double sila1Int = Double.parseDouble(a);
                if(co01.equals("Kiloniuton")){
                    b=1000*sila1Int;
                }
                else if(co01.equals("Niuton")){
                    b=sila1Int;
                }
                else if(co01.equals("Meganiuton")){
                    b=1000000*sila1Int;
                }
                else if(co01.equals("Miliniuton")){
                    b=0.001*sila1Int;
                }
                if(co02.equals("Kiloniuton")){
                    c=1000.0;
                }
                else if(co02.equals("Niuton")){
                    c=1.0;
                }
                else if(co02.equals("Miliniuton")){
                    c=0.001;
                }
                else if(co02.equals("Meganiuton")){
                    c=1000000.0;
                }
                e=b/c;
                String x = funkcjePrzelicznikowe.intowanie(e);
                wynikSila.setText(x);
            }
        });
        praca1 = view.findViewById(R.id.praca1);
        praca2 = view.findViewById(R.id.praca2);
        final TextView wynikPraca = view.findViewById(R.id.praca2Pole);
        final EditText praca1Pole = view.findViewById(R.id.praca1Pole);
        Button buttonPraca = view.findViewById(R.id.buttonPraca);
        praca1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktory="pierwszyPraca";
                registerForContextMenu(praca1);
                v.showContextMenu();
            }
        });
        praca2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktory="drugiPraca";
                registerForContextMenu(praca2);
                v.showContextMenu();
            }
        });
        buttonPraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double b=0.0,c=0.0,e=null,d;
                String a=praca1Pole.getText().toString();
                Double praca1Int = Double.parseDouble(a);
                if(co03.equals("Dżule")){
                    b=1*praca1Int;
                }
                else if(co03.equals("Kilodżule")){
                    b=1000*praca1Int;
                }
                else if(co03.equals("Kalorie")){
                    b=4.184*praca1Int;
                }
                else if(co03.equals("Kilokalorie")){
                    b=4184*praca1Int;
                }
                if(co04.equals("Dżule")){
                    c=1.0;
                }
                else if(co04.equals("Kilodżule")){
                    c=1000.0;
                }
                else if(co04.equals("Kalorie")){
                    c=4.184;
                }
                else if(co04.equals("Kilokalorie")){
                    c=4184.0;
                }
                e=b/c;
                String x = funkcjePrzelicznikowe.intowanie(e);
                wynikPraca.setText(x);
            }
        });
        cisnienie1 = view.findViewById(R.id.cisnienie1);
        cisnienie2 = view.findViewById(R.id.cisnienie2);
        final TextView wynikCisnienie = view.findViewById(R.id.cisnienie2Pole);
        final EditText cisnienie1Pole = view.findViewById(R.id.cisnienie1Pole);
        Button buttonCisnienie = view.findViewById(R.id.buttonCiśnienie);
        cisnienie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(cisnienie1);
                ktory="pierwszyCisnienie";
                v.showContextMenu();
            }
        });
        cisnienie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(cisnienie2);
                ktory="drugiCisnienie";
                v.showContextMenu();
            }
        });
        buttonCisnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double b=0.0,c=0.0,e=null,d;
                String a=cisnienie1Pole.getText().toString();
                Double cisnienie1Int = Double.parseDouble(a);
                if(co05.equals("Hektopaskal")){
                    co05="hPa";
                }
                else if(co05.equals("Paskal")){
                    co05="Pa";
                }
                else if(co05.equals("Kilopaskal")){
                    co05="kPa";
                }
                else if(co05.equals("Atmosfera")){
                    co05="atm";
                }
                else if(co05.equals("Megapaskal")){
                    co05="MPa";
                }
                else if(co05.equals("Bar")){
                    co05="b";
                }
                cisnienie1Int = funkcjePrzelicznikowe.cisnienie(cisnienie1Int, co05);
                if(co06.equals("Hektopaskal")){
                    co06="hPa";
                }
                else if(co06.equals("Paskal")){
                    co06="Pa";
                }
                else if(co06.equals("Kilopaskal")){
                    co06="kPa";
                }
                else if(co06.equals("Atmosfera")){
                    co06="atm";
                }
                else if(co06.equals("Megapaskal")){
                    co06="MPa";
                }
                else if(co06.equals("Bar")){
                    co06="b";
                }
                cisnienie1Int = funkcjePrzelicznikowe.cisnienieWynik(cisnienie1Int, co06);
                String x = funkcjePrzelicznikowe.intowanie(cisnienie1Int);
                wynikCisnienie.setText(x);
            }
        });
        moc1 = view.findViewById(R.id.moc1);
        moc2 = view.findViewById(R.id.moc2);
        final TextView wynikMoc = view.findViewById(R.id.moc2Pole);
        final EditText moc1Pole = view.findViewById(R.id.moc1Pole);
        Button buttonMoc = view.findViewById(R.id.buttonMoc);
        moc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(moc1);
                ktory="pierwszyMoc";
                v.showContextMenu();
            }
        });
        moc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(moc2);
                ktory="drugiMoc";
                v.showContextMenu();
            }
        });
        buttonMoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double b=0.0,c=0.0,e=null,d;
                String a=moc1Pole.getText().toString();
                Double moc1Int = Double.parseDouble(a);
                if(co07.equals("Wat")){
                    b=moc1Int;
                }
                else if(co07.equals("Kilowat")){
                    b=1000*moc1Int;
                }
                else if(co07.equals("Megawat")){
                    b=1000000*moc1Int;
                }
                else if(co07.equals("Konie mechaniczne")){
                    b=736*moc1Int;
                }
                if(co08.equals("Wat")){
                    c=1.0;
                }
                else if(co08.equals("Kilowat")){
                    c=1000.0;
                }
                else if(co08.equals("Megawat")){
                    c=1000000.0;
                }
                else if(co08.equals("Konie mechaniczne")){
                    c=736.0;
                }
                e=b/c;
                String x = funkcjePrzelicznikowe.intowanie(e);
                wynikMoc.setText(x);
            }
        });
        return view;
    }
    public void zmiana(String co){
        if(ktory.equals("pierwszyPredkosc")){
            if(co.equals("kilometrynah")){
                predkosc1.setText(Html.fromHtml("<sup><small>km</small></sup>/<sub><small>h</small></sub>"));
            }
            else if(co.equals("kilometrynamin")){
                predkosc1.setText(Html.fromHtml("<sup><small>km</small></sup>/<sub><small>min</small></sub>"));
            }
            else if(co.equals("kilometrynas")){
                predkosc1.setText(Html.fromHtml("<sup><small>km</small></sup>/<sub><small>s</small></sub>"));
            }
            else if(co.equals("metrynas")){
                predkosc1.setText(Html.fromHtml("<sup><small>m</small></sup>/<sub><small>s</small></sub>"));
            }
            else if(co.equals("metrynamin")){
                predkosc1.setText(Html.fromHtml("<sup><small>m</small></sup>/<sub><small>min</small></sub>"));
            }
            co3=co;
        }
        else if(ktory.equals("drugiPredkosc")){
            if(co.equals("kilometrynah")){
                predkosc2.setText(Html.fromHtml("<sup><small>km</small></sup>/<sub><small>h</small></sub>"));
            }
            else if(co.equals("kilometrynamin")){
                predkosc2.setText(Html.fromHtml("<sup><small>km</small></sup>/<sub><small>min</small></sub>"));
            }
            else if(co.equals("kilometrynas")){
                predkosc2.setText(Html.fromHtml("<sup><small>km</small></sup>/<sub><small>s</small></sub>"));
            }
            else if(co.equals("metrynas")){
                predkosc2.setText(Html.fromHtml("<sup><small>m</small></sup>/<sub><small>s</small></sub>"));
            }
            else if(co.equals("metrynamin")){
                predkosc2.setText(Html.fromHtml("<sup><small>m</small></sup>/<sub><small>min</small></sub>"));
            }
            co2=co;
        }
        else if(ktory.equals("pierwszySila")){
            if(co.equals("Meganiuton")){
                co01=co;
                co="Miliniuton";
            }
            else{
                co01=co;
            }
            sila1.setText(co);
        }
        else if(ktory.equals("drugiSila")){
            sila2.setText(co);
            co02=co;
        }
        else if(ktory.equals("pierwszyPraca")){
            praca1.setText(co);
            co03=co;
        }
        else if(ktory.equals("drugiPraca")){
            praca2.setText(co);
            co04=co;
        }
        else if(ktory.equals("pierwszyCisnienie")){
            cisnienie1.setText(co);
            co05=co;
        }
        else if(ktory.equals("drugiCisnienie")){
            cisnienie2.setText(co);
            co06=co;
        }
        else if(ktory.equals("pierwszyMoc")){
            if(co.equals("Konie mechaniczne")){
                moc1.setText("KM");
            }
            else {
                moc1.setText(co);
            }
            co07=co;
        }
        else if(ktory.equals("drugiMoc")){
            if(co.equals("Konie mechaniczne")){
                moc2.setText("KM");
            }
            else {
                moc2.setText(co);
            }
            co08=co;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.predkosc1||v.getId()==R.id.predkosc2){
            getActivity().getMenuInflater().inflate(R.menu.predkosc_menu,menu);
        }
        else if(v.getId()==R.id.sila1||v.getId()==R.id.sila2){
            getActivity().getMenuInflater().inflate(R.menu.sila_menu,menu);
        }
        else if(v.getId()==R.id.praca1||v.getId()==R.id.praca2){
            getActivity().getMenuInflater().inflate(R.menu.praca_menu,menu);
        }
        else if (v.getId()==R.id.cisnienie1||v.getId()==R.id.cisnienie2){
            getActivity().getMenuInflater().inflate(R.menu.cisnienie_menu,menu);
        }
        else if(v.getId()==R.id.moc1||v.getId()==R.id.moc2){
            getActivity().getMenuInflater().inflate(R.menu.moc_menu,menu);
        }
    }
    String ktory;
    String co,co2,co3,co0,co01,co02,co03,co04,co05,co06,co07,co08,co09,co11,co12;
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MnaM:
                co="metrynamin";
                zmiana(co);
                return true;
            case R.id.MnaS:
                co="metrynas";
                zmiana(co);
                return true;
            case R.id.KMnaS:
                co="kilometrynas";
                zmiana(co);
                return true;
            case R.id.KMnaM:
                co="kilometrynamin";
                zmiana(co);
                return true;
            case R.id.KMnaH:
                co="kilometrynah";
                zmiana(co);
                return true;
            case R.id.kiloniuton:
                co="Kiloniuton";
                zmiana(co);
                return true;
            case R.id.niuton:
                co="Niuton";
                zmiana(co);
                return true;
            case R.id.meganiuton2:
                co="Meganiuton";
                zmiana(co);
                return true;
            case R.id.miliniuton:
                co="Miliniuton";
                zmiana(co);
                return true;
            case R.id.jule:
                co="Dżule";
                zmiana(co);
                return true;
            case R.id.kalorie:
                co="Kalorie";
                zmiana(co);
                return true;
            case R.id.kilojule:
                co="Kilodżule";
                zmiana(co);
                return true;
            case R.id.kilokalorie:
                co="Kilokalorie";
                zmiana(co);
                return true;
            case R.id.paskal:
                co="Paskal";
                zmiana(co);
                return true;
            case R.id.hektopaskal:
                co="Hektopaskal";
                zmiana(co);
                return true;
            case R.id.kilopaskal:
                co="Kilopaskal";
                zmiana(co);
                return true;
            case R.id.atmosfera:
                co="Atmosfera";
                zmiana(co);
                return true;
            case R.id.bar:
                co="Bar";
                zmiana(co);
                return true;
            case R.id.megapaskal:
                co="Megapaskal";
                zmiana(co);
                return true;
            case R.id.Wat:
                co="Wat";
                zmiana(co);
                return true;
            case R.id.Kilowat:
                co="Kilowat";
                zmiana(co);
                return true;
            case R.id.Megawat:
                co="Megawat";
                zmiana(co);
                return true;
            case R.id.Konie:
                co="Konie mechaniczne";
                zmiana(co);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}