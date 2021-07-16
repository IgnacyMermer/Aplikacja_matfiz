package com.e.matfiziak;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.e.matfiziak.Adaptery.CustomExpandableListAdapter;
import com.e.matfiziak.Adaptery.NavigationManager;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.inne.dane;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.StrictMath.sqrt;

public class trapez_prostokatny extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;
    CheckBox checkBoxPole;
    CheckBox checkBoxObwod;
    CheckBox checkBoxWysokosc;
    CheckBox checkBoxKatA;
    CheckBox checkBoxKatB;
    CheckBox checkBoxPrzekatnaA;
    CheckBox checkBoxPrzekatnaB, checkBoxDolnaPodstawa, checkBoxGornaPodstawa, checkBoxCzwartyBok;
    EditText wysokosc, obwod, przekatnaA, przekatnaB, dolnaPodstawa, gornaPodstawa, czwartyBok, pole, katA, katB;
    TextView jednostkaWysokosc, jednostkaObwod, jednostkaPrzekatnaA, jednostkaPrzekatnaB, jednostkaCzwartyBok, jednostkaPole, jednostkaWynik;
    TextView jednostkaDolnaPodstawa, jednostkaGornaPodstawa;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik;
    ArrayList<String> doWyslania;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trapez_prostokatny);
        final ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        final AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        checkBoxPrzekatnaB = findViewById(R.id.checkboxPrzekatnaBProstokatny);
        checkBoxPrzekatnaA = findViewById(R.id.checkboxPrzekatnaAProstokatny);
        checkBoxObwod = findViewById(R.id.checkboxObwodProstokatny);
        checkBoxKatB = findViewById(R.id.checkboxKatBProstokatny);
        checkBoxKatA = findViewById(R.id.checkboxKatAProstokatny);
        checkBoxWysokosc = findViewById(R.id.checkboxWysokoscProstokatny);
        checkBoxDolnaPodstawa = findViewById(R.id.checkboxDolnaPodstawaProstokatny);
        checkBoxGornaPodstawa = findViewById(R.id.checkboxGornaPodstawaProstokatny);
        checkBoxCzwartyBok = findViewById(R.id.checkboxCzwartyBokProstokatny);
        checkBoxPole = findViewById(R.id.checkboxPoleProstokatny);
        wynik = findViewById(R.id.wynikProstokatny);
        wysokosc = findViewById(R.id.wysokoscProstokatny);
        obwod = findViewById(R.id.obwodProstokatny);
        przekatnaB = findViewById(R.id.przekatnaBProstokatny);
        przekatnaA = findViewById(R.id.przekatnaAProstokatny);
        czwartyBok = findViewById(R.id.czwartyBokProstokatny);
        dolnaPodstawa = findViewById(R.id.dolnaProstokatny);
        gornaPodstawa = findViewById(R.id.gornaProstokatny);
        pole = findViewById(R.id.poleProstokatny);
        katA = findViewById(R.id.katAProstokatny);
        katB = findViewById(R.id.katBProstokatny);
        jednostkaWynik = findViewById(R.id.jednostkaWynikProstokatny);
        jednostkaPrzekatnaB = findViewById(R.id.jednostkaPrzekatnaBProstokatny);
        jednostkaPrzekatnaA = findViewById(R.id.jednostkaPrzekatnaAProstokatny);
        jednostkaPole = findViewById(R.id.jednostkaPoleProstokatny);
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        jednostkaObwod = findViewById(R.id.jednostkaObwodProstokatny);
        jednostkaWysokosc = findViewById(R.id.jednostkaWysokoscProstokatny);
        jednostkaCzwartyBok = findViewById(R.id.jednostkaCzwartyBokProstokatny);
        jednostkaDolnaPodstawa = findViewById(R.id.jednostkadolnaProstokatny);
        jednostkaGornaPodstawa = findViewById(R.id.jednostkaGornaProstokatny);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaProstokatny);
        drugaLinia = findViewById(R.id.drugaLiniaProstokatny);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaProstokatny);
        czwartaLinia = findViewById(R.id.czwartaLiniaProstokatny);
        wynik = findViewById(R.id.wynikProstokatny);
        Button buttonWynik = findViewById(R.id.buttonObliczProstokatny);
        Button buttonCzysc = findViewById(R.id.buttonCzyscProstokatny);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<String>();
        try {
            Intent incomingIntent = getIntent();
            incomingList = incomingIntent.getStringArrayListExtra("lista");
            checkbox = incomingIntent.getStringExtra("checkbox");
        }
        catch (Exception ex){}
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();
        if(checkbox.equals("pole")){
            checkBoxPole.setChecked(true);
        }
        else if(checkbox.equals("czwartyBok")){
            checkBoxCzwartyBok.setChecked(true);
        }
        else if(checkbox.equals("dolnaPodstawa")){
            checkBoxDolnaPodstawa.setChecked(true);
        }
        else if(checkbox.equals("gornaPodstawa")){
            checkBoxGornaPodstawa.setChecked(true);
        }
        else if(checkbox.equals("wysokosc")){
            checkBoxWysokosc.setChecked(true);
        }
        else if(checkbox.equals("katA")){
            checkBoxKatA.setChecked(true);
        }
        else if(checkbox.equals("katB")){
            checkBoxKatB.setChecked(true);
        }
        else if(checkbox.equals("obwod")){
            checkBoxObwod.setChecked(true);
        }
        else if(checkbox.equals("przekatnaA")){
            checkBoxPrzekatnaA.setChecked(true);
        }
        else if(checkbox.equals("przekatnaB")){
            checkBoxPrzekatnaB.setChecked(true);
        }

        if(incomingList!=null){
            czwartyBok.setText(incomingList.get(0));
            jednostkaCzwartyBok.setText(incomingList.get(1));
            wysokosc.setText(incomingList.get(2));
            jednostkaWysokosc.setText(incomingList.get(3));
            obwod.setText(incomingList.get(4));
            jednostkaObwod.setText(incomingList.get(5));
            pole.setText(incomingList.get(6));
            jednostkaPole.setText(incomingList.get(7));
            przekatnaA.setText(incomingList.get(8));
            jednostkaPrzekatnaA.setText(incomingList.get(9));
            przekatnaB.setText(incomingList.get(10));
            jednostkaPrzekatnaB.setText(incomingList.get(11));
            katA.setText(incomingList.get(12));
            katB.setText(incomingList.get(13));
            pierwszaLinia.setText(incomingList.get(14));
            drugaLinia.setText(incomingList.get(15));
            trzeciaLinia.setText(incomingList.get(16));
            czwartaLinia.setText(incomingList.get(17));
            wynik.setText(incomingList.get(18));
            jednostkaWynik.setText(incomingList.get(19));
            dolnaPodstawa.setText(incomingList.get(20));
            jednostkaDolnaPodstawa.setText(incomingList.get(21));
            gornaPodstawa.setText(incomingList.get(22));
            jednostkaGornaPodstawa.setText(incomingList.get(23));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(czwartyBok.getText().toString());
                doWyslania.add(jednostkaCzwartyBok.getText().toString());
                doWyslania.add(wysokosc.getText().toString());
                doWyslania.add(jednostkaWysokosc.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(jednostkaObwod.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(jednostkaPole.getText().toString());
                doWyslania.add(przekatnaA.getText().toString());
                doWyslania.add(jednostkaPrzekatnaA.getText().toString());
                doWyslania.add(przekatnaB.getText().toString());
                doWyslania.add(jednostkaPrzekatnaB.getText().toString());
                doWyslania.add(katA.getText().toString());
                doWyslania.add(katB.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                doWyslania.add(dolnaPodstawa.getText().toString());
                doWyslania.add(jednostkaDolnaPodstawa.getText().toString());
                doWyslania.add(gornaPodstawa.getText().toString());
                doWyslania.add(jednostkaGornaPodstawa.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxPole.isChecked()){
                    ktoryCheckbox="pole";
                }
                else if(checkBoxCzwartyBok.isChecked()){
                    ktoryCheckbox="czwartyBok";
                }
                else if(checkBoxDolnaPodstawa.isChecked()){
                    ktoryCheckbox="dolnaPodstawa";
                }
                else if(checkBoxGornaPodstawa.isChecked()){
                    ktoryCheckbox="gornaPodstawa";
                }
                else if(checkBoxWysokosc.isChecked()){
                    ktoryCheckbox="wysokosc";
                }
                else if(checkBoxPrzekatnaA.isChecked()){
                    ktoryCheckbox="przekatnaA";
                }
                else if(checkBoxPrzekatnaB.isChecked()){
                    ktoryCheckbox="przekatnaB";
                }
                else if(checkBoxObwod.isChecked()){
                    ktoryCheckbox="obwod";
                }
                else if(checkBoxKatA.isChecked()){
                    ktoryCheckbox="katA";
                }
                else if(checkBoxKatB.isChecked()){
                    ktoryCheckbox="katB";
                }
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(trapez_prostokatny.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "trapezProstokatny");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(trapez_prostokatny.this, Czat.class);
                        i2.putExtra("miejsce", "trapezProstokatny");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(trapez_prostokatny.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "trapezProstokatny");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(trapez_prostokatny.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "trapezProstokatny");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        jednostkaCzwartyBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCzwartyBok);
                openContextMenu(v);
            }
        });
        jednostkaWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokosc);
                openContextMenu(v);
            }
        });
        jednostkaObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaObwod);
                openContextMenu(v);
            }
        });
        jednostkaPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPole);
                openContextMenu(v);
            }
        });
        jednostkaPrzekatnaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzekatnaA);
                openContextMenu(v);
            }
        });
        jednostkaPrzekatnaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzekatnaB);
                openContextMenu(v);
            }
        });
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        jednostkaGornaPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaGornaPodstawa);
                openContextMenu(v);
            }
        });
        jednostkaDolnaPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaDolnaPodstawa);
                openContextMenu(v);
            }
        });

        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wynik.setText("");
                jednostkaWynik.setText("");
                jednostkaCzwartyBok.setText("cm");
                jednostkaObwod.setText("cm");
                jednostkaDolnaPodstawa.setText("cm");
                jednostkaGornaPodstawa.setText("cm");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaWysokosc.setText("cm");
                jednostkaPrzekatnaA.setText("cm");
                jednostkaPrzekatnaB.setText("cm");
                wysokosc.setText("");
                przekatnaA.setText("");
                przekatnaB.setText("");
                dolnaPodstawa.setText("");
                gornaPodstawa.setText("");
                czwartyBok.setText("");
                pole.setText("");
                obwod.setText("");
                katA.setText("");
                katB.setText("");
                checkBoxCzwartyBok.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxPole.isChecked()){
                        Double poleD = null;
                        if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                             Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                             podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                             Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                             podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2,jednostkaDolnaPodstawa.getText().toString());
                             Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                             wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                             pierwszaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                             drugaLinia.setText(Html.fromHtml("P=&frac12;("+podstawa1+"+"+podstawa2+")*"+wysokosc1));
                             trzeciaLinia.setText("");
                             czwartaLinia.setText("");
                             poleD = (podstawa1+podstawa2)*wysokosc1/2;
                             poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                             String x = funkcjePrzelicznikowe.intowanie(poleD);
                             wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2,jednostkaDolnaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(tan+"=h/("+podstawa2+"-"+podstawa1+")");
                            Double wysokosc1=podstawa2-podstawa1;
                            trzeciaLinia.setText("h="+tan+"*"+wysokosc1);
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            wysokosc1 = tan*wysokosc1;
                            poleD = (podstawa1+podstawa2)*wysokosc1/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))) {
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-= 90;
                            Double kat = 90 - kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(tan + "=h/(" + podstawa2 + "-" + podstawa1 + ")");
                            Double wysokosc1 = podstawa2 - podstawa1;
                            trzeciaLinia.setText("h=" + tan + "*" + wysokosc1);
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            wysokosc1 = tan * wysokosc1;
                            poleD = (podstawa1 + podstawa2) * wysokosc1 / 2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1,jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tan&#946;"));
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            Double podstawa2 = wysokosc1/tan;
                            podstawa2+=podstawa1;
                            poleD = (podstawa1+podstawa2)*wysokosc1/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1,jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katA.getText().toString());
                            kat2-=90;
                            Double kat = 90-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tan&#946;"));
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            Double podstawa2 = wysokosc1/tan;
                            podstawa2+=podstawa1;
                            poleD = (podstawa1+podstawa2)*wysokosc1/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1,jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tan&#946;"));
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            Double podstawa3 = wysokosc1/tan;
                            Double podstawa1 = podstawa2-podstawa3;
                            Double y = podstawa1+podstawa2;
                            y=y * wysokosc1;
                            poleD = y/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1,jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-=90;
                            Double kat = 90-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tan&#946;"));
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            Double podstawa1 = wysokosc1/tan;
                            podstawa1 = podstawa2-podstawa1;
                            poleD = (podstawa1+podstawa2)*wysokosc1/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double czwarty = Double.parseDouble(czwartyBok.getText().toString());
                            czwarty = funkcjePrzelicznikowe.dlugosc(czwarty, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            Double podstawa21=podstawa2-podstawa1;
                            drugaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>="+czwarty+"<sup><small><small>2</small></small></sup>-"+podstawa21+"<sup><small><small>2</small></small></sup>"));
                            Double czwarty2 = czwarty*czwarty;
                            Double podstawa22 = podstawa21*podstawa21;
                            trzeciaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>="+czwarty2+"-"+podstawa22));
                            Double wysokosc1 = czwarty2+podstawa22;
                            wysokosc1 = sqrt(wysokosc1);
                            poleD = (podstawa1+podstawa2)*wysokosc1/2;
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        Double wysokosc1 = null;
                        if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2,jednostkaDolnaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(tan+"=h/("+podstawa2+"-"+podstawa1+")");
                            wysokosc1=podstawa2-podstawa1;
                            trzeciaLinia.setText("h="+tan+"*"+wysokosc1);
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            wysokosc1 = tan*wysokosc1;
                            wysokosc1 = funkcjePrzelicznikowe.dlugoscWynik(wysokosc1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokosc1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))) {
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-= 90;
                            Double kat = 90 - kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(tan + "=h/(" + podstawa2 + "-" + podstawa1 + ")");
                            wysokosc1 = podstawa2 - podstawa1;
                            trzeciaLinia.setText("h=" + tan + "*" + wysokosc1);
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            wysokosc1 = tan * wysokosc1;
                            wysokosc1 = funkcjePrzelicznikowe.dlugoscWynik(wysokosc1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokosc1);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double czwarty = Double.parseDouble(czwartyBok.getText().toString());
                            czwarty = funkcjePrzelicznikowe.dlugosc(czwarty, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            podstawa2-=podstawa1;
                            drugaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>="+czwarty+"<sup><small><small>2</small></small></sup>-"+podstawa2+"<sup><small><small>2</small></small></sup>"));
                            czwarty = czwarty*czwarty;
                            podstawa2 = podstawa2*podstawa2;
                            trzeciaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>="+czwarty+"-"+podstawa2));
                            czwartaLinia.setText("");
                            wysokosc1 = czwarty+podstawa2;
                            wysokosc1 = sqrt(wysokosc1);
                            wysokosc1 = funkcjePrzelicznikowe.dlugoscWynik(wysokosc1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokosc1);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            drugaLinia.setText("h=2P/(a+b)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokosc1 = 2*poleD/(podstawa1+podstawa2);
                            wysokosc1 = funkcjePrzelicznikowe.dlugoscWynik(wysokosc1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokosc1);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))){
                            Double obwod1 = Double.parseDouble(obwod.getText().toString());
                            obwod1 = funkcjePrzelicznikowe.dlugosc(obwod1, jednostkaObwod.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c+h");
                            drugaLinia.setText("h=Ob-(a+b+c)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokosc1 = obwod1-c-podstawa1-podstawa2;
                            wysokosc1 = funkcjePrzelicznikowe.dlugoscWynik(wysokosc1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxDolnaPodstawa.isChecked()){
                        Double podstawa2 = null;
                        if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double ramie = Double.parseDouble(czwartyBok.getText().toString());
                            ramie = funkcjePrzelicznikowe.dlugosc(ramie, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=(a-b)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>="+ramie+"<sup><small><small>2</small></small></sup>-"+wysokosc1+"<sup><small><small>2</small></small></sup>"));
                            ramie = ramie*ramie;
                            czwartaLinia.setText("a=(a-b)+b");
                            wysokosc1 = wysokosc1*wysokosc1;
                            podstawa2 = ramie+wysokosc1;
                            podstawa2 = sqrt(podstawa2);
                            podstawa2+=podstawa1;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            podstawa2 = wysokosc1/tan;
                            trzeciaLinia.setText("a=(a-b)+b");
                            czwartaLinia.setText("");
                            podstawa2+=podstawa1;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180-"+kat2));
                            kat2-=90;
                            Double kat = 90-kat2;
                            drugaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            trzeciaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            trzeciaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            podstawa2 = wysokosc1/tan;
                            czwartaLinia.setText("a=(a-b)+b");
                            podstawa2+=podstawa1;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=c*cos&#946;"));
                            kat = Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            podstawa2 = cos*c;
                            podstawa2+=podstawa1;
                            trzeciaLinia.setText("a=(a-b)+b");
                            czwartaLinia.setText("");
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180-"+kat2));
                            kat2-=90;
                            Double kat = 90-kat2;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            trzeciaLinia.setText(Html.fromHtml("(a-b)=c*cos&#946;"));
                            kat = Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            podstawa2 = c*cos;
                            czwartaLinia.setText("a=(a-b)+b");
                            podstawa2+=podstawa1;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))){
                            Double obwod1 = Double.parseDouble(obwod.getText().toString());
                            obwod1 = funkcjePrzelicznikowe.dlugosc(obwod1, jednostkaObwod.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c+h");
                            drugaLinia.setText("a=Ob-(b+c+h)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa2 = obwod1 - wysokosc1-podstawa1-c;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxGornaPodstawa.isChecked()){
                        Double podstawa1 = null;
                        if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double ramie = Double.parseDouble(czwartyBok.getText().toString());
                            ramie = funkcjePrzelicznikowe.dlugosc(ramie, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            ramie = ramie*ramie;
                            wysokosc1 = wysokosc1*wysokosc1;
                            podstawa1 = ramie+wysokosc1;
                            podstawa1 = sqrt(podstawa1);
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("");
                            podstawa1 = podstawa2-podstawa1;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double tan = Math.tan(kat);
                            podstawa1 = wysokosc1/tan;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("");
                            podstawa1 = podstawa2-podstawa1;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180-"+kat2));
                            kat2-=90;
                            Double kat = 90-kat2;
                            kat = Math.toRadians(kat);
                            drugaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            trzeciaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double tan = Math.tan(kat);
                            podstawa1 = wysokosc1/tan;
                            czwartaLinia.setText("b=a-(a-b)");
                            podstawa1 = podstawa2-podstawa1;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=c*cos&#946;"));
                            Double cos = Math.cos(kat);
                            podstawa1 = cos*c;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("");
                            podstawa1 = podstawa2-podstawa1;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180-"+kat2));
                            kat2-=90;
                            Double kat = 90-kat2;
                            kat = Math.toRadians(kat);
                            drugaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            trzeciaLinia.setText(Html.fromHtml("(a-b)=c*cos&#946;"));
                            Double cos = Math.cos(kat);
                            podstawa1 = cos*c;
                            czwartaLinia.setText("b=a-(a-b)");
                            podstawa1 = podstawa2-podstawa1;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wysokosc1/tan;
                            podstawa3 = wysokosc1/tan;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("");
                            podstawa1 = podstawa2-podstawa3;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180-"+kat2));
                            kat2-=90;
                            Double kat=90-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wysokosc1/tan;
                            podstawa3 = wysokosc1/tan;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("");
                            podstawa1 = podstawa2-podstawa3;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double obwod1 = Double.parseDouble(obwod.getText().toString());
                            obwod1 = funkcjePrzelicznikowe.dlugosc(obwod1, jednostkaObwod.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c+h");
                            drugaLinia.setText("b=Ob-(a+c+h)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa1 = obwod1 - wysokosc1-c-podstawa2;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxObwod.isChecked()){
                        Double obwod = null;
                        if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c+h");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            obwod = podstawa1 + podstawa2+wysokosc1+c;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa2= Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = podstawa2-podstawa1;
                            Double c = (podstawa3*podstawa3)+(wysokosc1*wysokosc1);
                            c = sqrt(c);
                            drugaLinia.setText("Ob=a+b+c+h");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            obwod = c+podstawa1+podstawa2+wysokosc1;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            Double c2=c*c;
                            Double wys2 = wys*wys;
                            Double podstawa3 = c2-wys2;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("Ob=a+b+c+h");
                            Double podstawa1 = podstawa2-podstawa3;
                            obwod=podstawa1+podstawa2+wys+c;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            Double c2=c*c;
                            Double wys2 = wys*wys;
                            Double podstawa3 = c2-wys2;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText("Ob=a+b+c+h");
                            Double podstawa2 = podstawa1+podstawa3;
                            obwod=podstawa1+podstawa2+c+wys;
                            obwod= funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("h=tg&#946;*(a-b)"));
                            Double podstawa3 = podstawa2 - podstawa1;
                            Double wysokosc1 = tan*podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            czwartaLinia.setText(Html.fromHtml("c=(a-b)/cos&#946;"));
                            Double cos = Math.cos(kat);
                            Double c = podstawa3 / cos;
                            obwod = c+wysokosc1+podstawa1+podstawa2;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-=90;
                            Double kat = 90-kat2;
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("h=tg&#946;*(a-b)"));
                            Double podstawa3 = podstawa2 - podstawa1;
                            Double wysokosc1 = tan*podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            czwartaLinia.setText(Html.fromHtml("c=(a-b)/cos&#946;"));
                            Double cos = Math.cos(kat);
                            Double c = podstawa3 / cos;
                            obwod = c+wysokosc1+podstawa1+podstawa2;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wysokosc1/tan;
                            Double podstawa2 = podstawa1+podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            czwartaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            Double c = wysokosc1/sin;
                            obwod = c+podstawa1+podstawa2+wysokosc1;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-=90;
                            Double kat = 90-kat2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wysokosc1/tan;
                            Double podstawa2 = podstawa1+podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            czwartaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            Double c = wysokosc1/sin;
                            obwod = c+podstawa1+podstawa2+wysokosc1;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wysokosc1/tan;
                            Double podstawa1 = podstawa2-podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            czwartaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            Double c = wysokosc1/sin;
                            obwod = c+podstawa1+podstawa2+wysokosc1;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-=90;
                            Double kat = 90-kat2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wysokosc1/tan;
                            Double podstawa1 = podstawa2-podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            czwartaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            Double c = wysokosc1/sin;
                            obwod = c+podstawa1+podstawa2+wysokosc1;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=cos&#946;*c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*c;
                            Double podstawa2 = podstawa1+podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            czwartaLinia.setText(Html.fromHtml("h=sin&#946;*c"));
                            Double sin = Math.sin(kat);
                            Double wysokosc1 = sin*c;
                            obwod = wysokosc1+podstawa1+podstawa2+c;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            kat2-=90;
                            Double kat = 90-kat2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("(a-b)=cos&#946;*c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*c;
                            Double podstawa2 = podstawa1+podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            czwartaLinia.setText(Html.fromHtml("h=sin&#946;*c"));
                            Double sin = Math.sin(kat);
                            Double wysokosc1 = sin*c;
                            obwod = wysokosc1+podstawa1+podstawa2+c;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            Double wysokosc2 = wysokosc1*wysokosc1;
                            Double c2 = c*c;
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText("");
                            Double podstawa2 = c2-wysokosc2;
                            podstawa2=sqrt(podstawa2);
                            podstawa2+=podstawa1;
                            obwod = podstawa1+podstawa2+wysokosc1+c;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxKatA.isChecked()){
                        Double kat=null;
                        if((!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            drugaLinia.setText(Html.fromHtml("&#946;=sin&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double sin = wysokosc1/c;
                            kat = Math.asin(sin);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!czwartyBok.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double przekatna = Double.parseDouble(przekatnaB.getText().toString());
                            przekatna = funkcjePrzelicznikowe.dlugosc(przekatna, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>-2c*b*cos&#947;"));
                            drugaLinia.setText(Html.fromHtml("cos&#947;=(c<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>-d<sup><small><small>2</small></small></sup>)/(2c*b)"));
                            trzeciaLinia.setText(Html.fromHtml("cos&#947;=&#947;"));
                            Double cos = ((c*c)+(podstawa1*podstawa1)-(przekatna*przekatna))/(2*c*podstawa1);
                            kat = Math.acos(cos);
                            kat = Math.toDegrees(kat);
                            czwartaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat+"<sup><small><small>o</small></small></sup>"));
                            kat = 180-kat;
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa3 = podstawa2-podstawa1;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            drugaLinia.setText(Html.fromHtml("&#946;=tg&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double tan = wysokosc1/podstawa3;
                            kat = Math.atan(tan);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!czwartyBok.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa3 = podstawa2-podstawa1;
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("&#946;=cos&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double cos = podstawa3/c;
                            kat = Math.acos(cos);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxKatB.isChecked()){
                        Double kat=null;
                        if((!wysokosc.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#947;="));
                            drugaLinia.setText(Html.fromHtml("&#945;=cos&#947;+90<sup><small><small>o</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double cos = wysokosc1/c;
                            kat = Math.acos(cos);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa3 = podstawa2-podstawa1;
                            pierwszaLinia.setText(Html.fromHtml("tg&#947;=(a-b)/h"));
                            drugaLinia.setText(Html.fromHtml("&#945;=tg&#947;+90<sup><small><small>o</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double tan = podstawa3/wysokosc1;
                            kat = Math.atan(tan);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!czwartyBok.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double przekatna = Double.parseDouble(przekatnaB.getText().toString());
                            przekatna = funkcjePrzelicznikowe.dlugosc(przekatna, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>-2c*b*cos&#947;"));
                            drugaLinia.setText(Html.fromHtml("cos&#947;=(c<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>-d<sup><small><small>2</small></small></sup>)/(2c*b)"));
                            trzeciaLinia.setText(Html.fromHtml("cos&#947;=&#947;"));
                            Double cos = ((c*c)+(podstawa1*podstawa1)-(przekatna*przekatna))/(2*c*podstawa1);
                            kat = Math.acos(cos);
                            kat = Math.toDegrees(kat);
                            czwartaLinia.setText("");
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!czwartyBok.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa3 = podstawa2-podstawa1;
                            pierwszaLinia.setText(Html.fromHtml("sin&#947;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("&#945;=sin&#947;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double sin = podstawa3/c;
                            kat = Math.asin(sin);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxCzwartyBok.isChecked()){
                        Double c = null;
                        if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=(a-b)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = podstawa2-podstawa1;
                            drugaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wysokosc1+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa3 = podstawa3*podstawa3;
                            wysokosc1=wysokosc1*wysokosc1;
                            c=podstawa3+wysokosc1;
                            c=sqrt(c);
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals("")&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals("")))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2,jednostkaDolnaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            kat = Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            drugaLinia.setText(Html.fromHtml("c=(a-b)/cos&#946;"));
                            Double podstawa3 = podstawa2-podstawa1;
                            trzeciaLinia.setText("c="+podstawa3+"/"+cos);
                            czwartaLinia.setText("");
                            c = podstawa3/cos;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2,jednostkaDolnaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-&#945;"));
                            Double kat = 180-kat2;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            kat = Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            trzeciaLinia.setText(Html.fromHtml("c=(a-b)/cos&#946;"));
                            Double podstawa3 = podstawa2-podstawa1;
                            czwartaLinia.setText("c="+podstawa3+"/"+cos);
                            c = podstawa3/cos;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            drugaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            trzeciaLinia.setText("c="+wysokosc1+"/"+sin);
                            czwartaLinia.setText("");
                            c = sin*wysokosc1;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-&#945;"));
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            drugaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            trzeciaLinia.setText("c="+wysokosc1+"/"+sin);
                            czwartaLinia.setText("");
                            c = sin*wysokosc1;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            drugaLinia.setText("h=2P/(a+b)");
                            Double wysokosc1 = 2*poleD/(podstawa1+podstawa2);
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=(a-b)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = podstawa2-podstawa1;
                            czwartaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wysokosc1+"<sup><small><small>2</small></small></sup>"));
                            podstawa3 = podstawa3*podstawa3;
                            wysokosc1=wysokosc1*wysokosc1;
                            c=podstawa3+wysokosc1;
                            c=sqrt(c);
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            drugaLinia.setText("(a+b)=2P/h");
                            Double podstawa4 = 2*poleD/wysokosc1;
                            Double podstawa2 = podstawa4-podstawa1;
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=(a-b)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = podstawa2-podstawa1;
                            czwartaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wysokosc1+"<sup><small><small>2</small></small></sup>"));
                            podstawa3 = podstawa3*podstawa3;
                            wysokosc1=wysokosc1*wysokosc1;
                            c=podstawa3+wysokosc1;
                            c=sqrt(c);
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;(a+b)*h"));
                            drugaLinia.setText("(a+b)=2P/h");
                            Double podstawa4 = 2*poleD/wysokosc1;
                            Double podstawa1 = podstawa4-podstawa2;
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=(a-b)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = podstawa2-podstawa1;
                            czwartaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wysokosc1+"<sup><small><small>2</small></small></sup>"));
                            podstawa3 = podstawa3*podstawa3;
                            wysokosc1=wysokosc1*wysokosc1;
                            c=podstawa3+wysokosc1;
                            c=sqrt(c);
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))){
                            Double obwod1 = Double.parseDouble(obwod.getText().toString());
                            obwod1 = funkcjePrzelicznikowe.dlugosc(obwod1, jednostkaObwod.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c+h");
                            drugaLinia.setText("c=Ob-(a+b+h)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            c = obwod1 - wysokosc1-podstawa1-podstawa2;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxPrzekatnaA.isChecked()) {
                        Double przekatna1 = null;
                        if ((!gornaPodstawa.getText().toString().equals("")) && (!wysokosc.getText().toString().equals(""))) {
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub>=&#8730;(" + podstawa1 + "<sup><small><small>2</small></small></sup>+" + wysokosc1 + "<sup><small><small>2</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokosc1 = wysokosc1 * wysokosc1;
                            podstawa1 = podstawa1 * podstawa1;
                            przekatna1 = wysokosc1 + podstawa1;
                            przekatna1 = sqrt(przekatna1);
                            przekatna1 = funkcjePrzelicznikowe.dlugoscWynik(przekatna1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(a-b)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(a-b)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            wysokosc1 = wysokosc1*wysokosc1;
                            Double podstawa3 = (c*c)-wysokosc1;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            podstawa1 = podstawa1*podstawa1;
                            przekatna1 = podstawa1+wysokosc1;
                            przekatna1 = sqrt(przekatna1);
                            przekatna1 = funkcjePrzelicznikowe.dlugoscWynik(przekatna1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat=Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            Double cos = Math.cos(kat);
                            Double podstawa3 = c*cos;
                            Double wysokosc1 = sin*c;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa1 = podstawa2-podstawa3;
                            podstawa1 = podstawa1 * podstawa1;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna1 = podstawa1+wysokosc1;
                            przekatna1 = sqrt(przekatna1);
                            przekatna1 = funkcjePrzelicznikowe.dlugoscWynik(przekatna1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat=Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            Double cos = Math.cos(kat);
                            Double podstawa3 = c*cos;
                            Double wysokosc1 = sin*c;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa1 = podstawa2-podstawa3;
                            podstawa1 = podstawa1 * podstawa1;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna1 = podstawa1+wysokosc1;
                            przekatna1 = sqrt(przekatna1);
                            przekatna1 = funkcjePrzelicznikowe.dlugoscWynik(przekatna1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double podstawa3 = wysokosc1/tan;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa1 = podstawa2-podstawa3;
                            podstawa1 = podstawa1 *podstawa1;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna1 = podstawa1+wysokosc1;
                            przekatna1 = sqrt(przekatna1);
                            przekatna1 = funkcjePrzelicznikowe.dlugoscWynik(przekatna1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double podstawa3 = wysokosc1/tan;
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa1 = podstawa2-podstawa3;
                            podstawa1 = podstawa1 *podstawa1;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna1 = podstawa1+wysokosc1;
                            przekatna1 = sqrt(przekatna1);
                            przekatna1 = funkcjePrzelicznikowe.dlugoscWynik(przekatna1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna1);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        Double przekatna2 = null;
                        if((!wysokosc.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+a<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml(""));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokosc1 = wysokosc1*wysokosc1;
                            podstawa2 = podstawa2*podstawa2;
                            przekatna2 = wysokosc1+podstawa2;
                            przekatna2 = sqrt(przekatna2);
                            przekatna2 = funkcjePrzelicznikowe.dlugoscWynik(przekatna2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat=Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            Double cos = Math.cos(kat);
                            Double podstawa3 = c*cos;
                            Double wysokosc1 = sin*c;
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub><sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa2 = podstawa1+podstawa3;
                            podstawa2 = podstawa2 * podstawa2;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna2 = podstawa2+wysokosc1;
                            przekatna2 = sqrt(przekatna2);
                            przekatna2 = funkcjePrzelicznikowe.dlugoscWynik(przekatna2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!czwartyBok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double c = Double.parseDouble(czwartyBok.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaCzwartyBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat=Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=(a-b)/c"));
                            drugaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = Math.sin(kat);
                            Double cos = Math.cos(kat);
                            Double podstawa3 = c*cos;
                            Double wysokosc1 = sin*c;
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa2 = podstawa1+podstawa3;
                            podstawa2 = podstawa2 * podstawa2;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna2 = podstawa2+wysokosc1;
                            przekatna2 = sqrt(przekatna2);
                            przekatna2 = funkcjePrzelicznikowe.dlugoscWynik(przekatna2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double podstawa3 = wysokosc1/tan;
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa2 = podstawa1+podstawa3;
                            podstawa2 = podstawa2 *podstawa2;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna2 = podstawa2+wysokosc1;
                            przekatna2 = sqrt(przekatna2);
                            przekatna2 = funkcjePrzelicznikowe.dlugoscWynik(przekatna2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wysokosc1 = Double.parseDouble(wysokosc.getText().toString());
                            wysokosc1 = funkcjePrzelicznikowe.dlugosc(wysokosc1, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/(a-b)"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            drugaLinia.setText(Html.fromHtml("(a-b)=h/tg&#946;"));
                            Double podstawa3 = wysokosc1/tan;
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa2 = podstawa1-podstawa3;
                            podstawa2 = podstawa2 *podstawa2;
                            wysokosc1 = wysokosc1*wysokosc1;
                            przekatna2 = podstawa2+wysokosc1;
                            przekatna2 = sqrt(przekatna2);
                            przekatna2 = funkcjePrzelicznikowe.dlugoscWynik(przekatna2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna2);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double przekatna1 = Double.parseDouble(przekatnaA.getText().toString());
                            przekatna1 = funkcjePrzelicznikowe.dlugosc(przekatna1, jednostkaPrzekatnaA.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>-b<sup><small><small>2</small></small></sup>"));
                            przekatna1 = przekatna1 * przekatna1;
                            podstawa1 = podstawa1*podstawa1;
                            Double wysokosc1 = przekatna1-podstawa1;
                            trzeciaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub><sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub>=&#8730;(a<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>)"));
                            podstawa2 = podstawa2*podstawa2;
                            przekatna2 = wysokosc1 + podstawa2;
                            przekatna2 = sqrt(przekatna2);
                            przekatna2 = funkcjePrzelicznikowe.dlugoscWynik(przekatna2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przekatna2);
                            wynik.setText(x);
                        }
                    }
                    if(wynik.getText().toString().charAt(wynik.getText().toString().length()-2)==' '){
                        if(wynik.getText().toString().charAt(wynik.getText().toString().length()-1)=='2'){
                            String y = wynik.getText().toString();
                            y = y.substring(0,y.length()-2);
                            wynik.setText(Html.fromHtml(y+"&#8730;2"));
                        }
                        else if(wynik.getText().toString().charAt(wynik.getText().toString().length()-1)=='3'){
                            String y = wynik.getText().toString();
                            y = y.substring(0,y.length()-2);
                            wynik.setText(Html.fromHtml(y+"&#8730;3"));
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        checkBoxCzwartyBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxCzwartyBok.isChecked()){
                    jednostkaWynik.setText(bokWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        bokWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzekatnaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxPrzekatnaA.isChecked()){
                    jednostkaWynik.setText(przekatnaAWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        przekatnaAWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxPole.isChecked()){
                    if((!poleWynik.equals("a"))&&(!poleWynik.equals("ha"))){
                        jednostkaWynik.setText(Html.fromHtml(poleWynik+"<sup><small><small>2</small></small></sup"));
                    }
                    else{
                        jednostkaWynik.setText(poleWynik);
                    }
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        poleWynik=jednostkaWynik.getText().toString();
                        if(poleWynik.charAt(poleWynik.length()-1)=='2'){
                            poleWynik=poleWynik.substring(0,poleWynik.length()-1);
                        }
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzekatnaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxPrzekatnaB.isChecked()){
                    jednostkaWynik.setText(przekatnaBWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        przekatnaBWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxWysokosc.isChecked()){
                    jednostkaWynik.setText(wysokoscWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wysokoscWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxKatA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                jednostkaWynik.setText(R.string.stopnie);
            }
        });
        checkBoxKatB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxObwod.setChecked(false);
                jednostkaWynik.setText(R.string.stopnie);
            }
        });
        checkBoxObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatB.setChecked(false);
                if(checkBoxObwod.isChecked()){
                    jednostkaWynik.setText(obwodWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        obwodWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxGornaPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatB.setChecked(false);
                if(checkBoxGornaPodstawa.isChecked()){
                    jednostkaWynik.setText(gornaPodstawaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        gornaPodstawaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxDolnaPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxCzwartyBok.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatB.setChecked(false);
                if(checkBoxDolnaPodstawa.isChecked()){
                    jednostkaWynik.setText(dolnaPodstawaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        dolnaPodstawaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        mdrawerLayout = findViewById(R.id.drawerTrapez_prostokatny_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(trapez_prostokatny.this, Konto.class);
                i.putExtra("miejsce", "Romb");
                startActivity(i);
                Animatoo.animateFade(trapez_prostokatny.this);
                return false;
            }
        });
        expandableListView.addHeaderView(listHeaderView);
        final CircleImageView imageView = listHeaderView.findViewById(R.id.avatar);
        final TextView nick = listHeaderView.findViewById(R.id.name);
        dane dane1 = new dane();
        String imageUrl = dane1.imageUrl;
        String nick2 = dane1.nick;
        if(nick2!=null){
            nick.setText(nick2);
        }
        if(imageUrl==null){
            imageUrl="default";
        }
        if(imageUrl.equals("default")){
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            if(getApplicationContext()!=null){
                Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
            }
        }
        genData();
        addDrawersItem();
        setupDrawer();
        if(savedInstanceState==null){
            selectFirstItemDefault();
        }
        ImageButton buttonBar = findViewById(R.id.buttonBar);
        buttonBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
    public void wyczyscLinie(){
        pierwszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            savedInstanceState.putString("pierwszaLinia", pierwszaLinia.getText().toString());
            savedInstanceState.putString("drugaLinia", drugaLinia.getText().toString());
            savedInstanceState.putString("trzeciaLinia", trzeciaLinia.getText().toString());
            savedInstanceState.putString("czwartaLinia", czwartaLinia.getText().toString());
            savedInstanceState.putString("wynik", wynik.getText().toString());
            savedInstanceState.putString("jednostkaWynik", jednostkaWynik.getText().toString());
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            String pierwszaLiniaStr = savedInstanceState.getString("pierwszaLinia");
            String drugaLiniaStr = savedInstanceState.getString("drugaLinia");
            String trzeciaLiniaStr = savedInstanceState.getString("trzeciaLinia");
            String czwartaLiniaStr = savedInstanceState.getString("czwartaLinia");
            String wynikStr = savedInstanceState.getString("wynik");
            String jednostkaWynikStr = savedInstanceState.getString("jednostkaWynik");
            pierwszaLinia.setText(pierwszaLiniaStr);
            drugaLinia.setText(drugaLiniaStr);
            trzeciaLinia.setText(trzeciaLiniaStr);
            czwartaLinia.setText(czwartaLiniaStr);
            wynik.setText(wynikStr);
            jednostkaWynik.setText(jednostkaWynikStr);
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }
    String ktory="";
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.jednostkaCzwartyBokProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bok";
        }
        else if(v.getId()==R.id.jednostkaWysokoscProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wysokosc";
        }
        else if(v.getId()==R.id.jednostkaObwodProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="obwod";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaAProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaA";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaBProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaB";
        }
        else if(v.getId()==R.id.jednostkaPoleProstokatny){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if(v.getId()==R.id.jednostkadolnaProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="dolnaPodstawa";
        }
        else if(v.getId()==R.id.jednostkaGornaProstokatny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="gornaPodstawa";
        }
        else if((v.getId()==R.id.jednostkaWynikProstokatny)&&(checkBoxPole.isChecked())){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikProstokatny)&&((!checkBoxKatA.isChecked())&&(!checkBoxKatB.isChecked()))){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
    }
    String poleWynik="cm", bokWynik="cm", wysokoscWynik="cm", przekatnaAWynik="cm", obwodWynik="cm", przekatnaBWynik="cm", gornaPodstawaWynik="cm", dolnaPodstawaWynik="cm";
    String ktory2="", ktory3="";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    if(checkBoxCzwartyBok.isChecked()){
                        bokWynik="cm";
                    }
                    else if(checkBoxDolnaPodstawa.isChecked()){
                        dolnaPodstawaWynik = "cm";
                    }
                    else if(checkBoxGornaPodstawa.isChecked()){
                        gornaPodstawaWynik="cm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="cm";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="cm";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="cm";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="cm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaCzwartyBok.setText("cm");
                }
                else if(ktory.equals("dolnaPodstawa")){
                    jednostkaDolnaPodstawa.setText("cm");
                }
                else if(ktory.equals("gornaPodstawa")){
                    jednostkaGornaPodstawa.setText("cm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("cm");
                }
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("cm");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("cm");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                    if(checkBoxCzwartyBok.isChecked()){
                        bokWynik="dm";
                    }
                    else if(checkBoxGornaPodstawa.isChecked()){
                        gornaPodstawaWynik = "dm";
                    }
                    else if(checkBoxDolnaPodstawa.isChecked()){
                        dolnaPodstawaWynik = "dm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="dm";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="dm";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="dm";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="dm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaCzwartyBok.setText("dm");
                }
                else if(ktory.equals("dolnaPodstawa")){
                    jednostkaDolnaPodstawa.setText("dm");
                }
                else if(ktory.equals("gornaPodstawa")){
                    jednostkaGornaPodstawa.setText("dm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("dm");
                }
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("dm");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("dm");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                    if(checkBoxCzwartyBok.isChecked()){
                        bokWynik="m";
                    }
                    else if(checkBoxDolnaPodstawa.isChecked()){
                        dolnaPodstawaWynik = "m";
                    }
                    else if(checkBoxGornaPodstawa.isChecked()){
                        gornaPodstawaWynik="m";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="m";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="m";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="m";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="m";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaCzwartyBok.setText("m");
                }
                else if(ktory.equals("dolnaPodstawa")){
                    jednostkaDolnaPodstawa.setText("m");
                }
                else if(ktory.equals("gornaPodstawa")){
                    jednostkaGornaPodstawa.setText("m");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("m");
                }
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("m");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("m");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                    if(checkBoxCzwartyBok.isChecked()){
                        bokWynik="km";
                    }
                    else if(checkBoxGornaPodstawa.isChecked()){
                        gornaPodstawaWynik="km";
                    }
                    else if(checkBoxDolnaPodstawa.isChecked()){
                        dolnaPodstawaWynik="km";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="km";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="km";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="km";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="km";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaCzwartyBok.setText("km");
                }
                else if(ktory.equals("dolnaPodstawa")){
                    jednostkaDolnaPodstawa.setText("km");
                }
                else if(ktory.equals("gornaPodstawa")){
                    jednostkaGornaPodstawa.setText("km");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("km");
                }
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("km");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("km");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                    poleWynik="mm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                    poleWynik="cm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                    poleWynik="dm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                    poleWynik="m";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("a");
                    poleWynik="a";
                }
                else{
                    jednostkaPole.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("ha");
                    poleWynik="ha";
                }
                else{
                    jednostkaPole.setText("ha");
                }
                Toast.makeText(this, "Hektar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToogle.syncState();
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToogle.onConfigurationChanged(newConfig);
    }
    private void selectFirstItemDefault() {
        if(navigationManager!=null){
            String firstItem = lstTitle.get(0);
            navigationManager.showFragment(firstItem);
        }
    }
    private void setupDrawer() {
        mDrawerToogle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerToogle.setDrawerIndicatorEnabled(true);
        mdrawerLayout.setDrawerListener(mDrawerToogle);
    }

    private void addDrawersItem() {
        adapter = new CustomExpandableListAdapter(trapez_prostokatny.this, lstTitle, lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {}
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {}
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition)))).get(childPosition).toString();
                if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(trapez_prostokatny.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(trapez_prostokatny.this);
                }
                mdrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent i = new Intent(trapez_prostokatny.this, Trapezy.class);
            startActivity(i);
            Animatoo.animateFade(trapez_prostokatny.this);
        }
    }
    private void genData() {
        List<String> title = Arrays.asList("Fizyka teoria", "Matematyka teoria", "Fizyka kalkulator", "Matematyka kalkulator", "Informatyka algorytmy");
        List<String> childitem = Arrays.asList("Kinematyka", "Dynamika", "Hydrostatyka", "Aerostatyka", "Termodynamika");
        List<String> childitem2 = Arrays.asList("Trójkąty", "Czworokąty", "Figury przestrzenne", "Algebra", "lalala" );
        lstChild = new TreeMap<>();
        lstChild.put(title.get(0), childitem);
        lstChild.put(title.get(1), childitem2);
        lstChild.put(title.get(2), childitem2);
        lstChild.put(title.get(3), childitem);
        lstChild.put(title.get(4), childitem);
        lstTitle = new ArrayList<>(lstChild.keySet());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(mDrawerToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}