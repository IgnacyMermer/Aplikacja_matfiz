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

public class trapez_rownoramienny extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;
    CheckBox checkBoxPole, checkBoxObwod, checkBoxWysokosc, checkBoxKatA, checkBoxKatB, checkBoxPrzekatnaA;
    CheckBox checkBoxKatC, checkBoxDolnaPodstawa, checkBoxGornaPodstawa, checkBoxRamie;
    EditText wysokosc, obwod, przekatnaA, dolnaPodstawa, gornaPodstawa, ramie, pole, katA, katB, katC, katD;
    TextView jednostkaWysokosc, jednostkaObwod, jednostkaPrzekatnaA, jednostkaRamie, jednostkaPole, jednostkaWynik;
    TextView jednostkaDolnaPodstawa, jednostkaGornaPodstawa;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik;
    ArrayList<String> doWyslania;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trapez_rownoramienny);
        final ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        final AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        checkBoxPrzekatnaA = findViewById(R.id.checkboxPrzekatnaARownoramienny);
        checkBoxObwod = findViewById(R.id.checkboxObwodTrRownoramienny);
        checkBoxKatB = findViewById(R.id.checkboxKatBTrRownoramienny);
        checkBoxKatA = findViewById(R.id.checkboxKatATrRownoramienny);
        checkBoxKatC = findViewById(R.id.checkboxKatCTrRownoramienny);
        checkBoxWysokosc = findViewById(R.id.checkboxWysokoscRownoramienny);
        checkBoxDolnaPodstawa = findViewById(R.id.checkboxDolnaPodstawaRownoramienny);
        checkBoxGornaPodstawa = findViewById(R.id.checkboxGornaPodstawaRownoramienny);
        checkBoxRamie = findViewById(R.id.checkboxRamieTrRownoramienny);
        checkBoxPole = findViewById(R.id.checkboxPoleTrRownoramienny);
        wynik = findViewById(R.id.wynikTrRownoramienny);
        wysokosc = findViewById(R.id.wysokoscRownoramienny);
        obwod = findViewById(R.id.obwodTrRownoramienny);
        przekatnaA = findViewById(R.id.przekatnaARownoramienny);
        ramie = findViewById(R.id.ramieTrRownoramienny);
        dolnaPodstawa = findViewById(R.id.dolnaRownoramienny);
        gornaPodstawa = findViewById(R.id.gornaRownoramienny);
        pole = findViewById(R.id.poleTrRownoramienny);
        katA = findViewById(R.id.katARownoramienny);
        katB = findViewById(R.id.katBRownoramienny);
        katC = findViewById(R.id.katCTrRownoramienny);
        katD = findViewById(R.id.katDTrRownoramienny);
        jednostkaWynik = findViewById(R.id.jednostkaWynikTrRownoramienny);
        jednostkaPrzekatnaA = findViewById(R.id.jednostkaPrzekatnaARownoramienny);
        jednostkaPole = findViewById(R.id.jednostkaPoleRownoramienny);
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        jednostkaObwod = findViewById(R.id.jednostkaObwodTrRownoramienny);
        jednostkaWysokosc = findViewById(R.id.jednostkaWysokoscRownoramienny);
        jednostkaRamie = findViewById(R.id.jednostkaRamieTrRownoramienny);
        jednostkaDolnaPodstawa = findViewById(R.id.jednostkadolnaRownoramienny);
        jednostkaGornaPodstawa = findViewById(R.id.jednostkaGornaRownoramienny);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaTrRownoramienny);
        drugaLinia = findViewById(R.id.drugaLiniaTrRownoramienny);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaTrRownoramienny);
        czwartaLinia = findViewById(R.id.czwartaLiniaTrRownoramienny);
        wynik = findViewById(R.id.wynikTrRownoramienny);
        Button buttonWynik = findViewById(R.id.buttonObliczTrRownoramienny);
        Button buttonCzysc = findViewById(R.id.buttonCzyscTrRownoramienny);
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
        else if(checkbox.equals("ramie")){
            checkBoxRamie.setChecked(true);
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
        else if(checkbox.equals("katC")){
            checkBoxKatC.setChecked(true);
        }
        else if(checkbox.equals("obwod")){
            checkBoxObwod.setChecked(true);
        }
        else if(checkbox.equals("przekatnaA")) {
            checkBoxPrzekatnaA.setChecked(true);
        }
        if(incomingList!=null){
            ramie.setText(incomingList.get(0));
            jednostkaRamie.setText(incomingList.get(1));
            wysokosc.setText(incomingList.get(2));
            jednostkaWysokosc.setText(incomingList.get(3));
            obwod.setText(incomingList.get(4));
            jednostkaObwod.setText(incomingList.get(5));
            pole.setText(incomingList.get(6));
            jednostkaPole.setText(incomingList.get(7));
            przekatnaA.setText(incomingList.get(8));
            jednostkaPrzekatnaA.setText(incomingList.get(9));
            katA.setText(incomingList.get(10));
            katB.setText(incomingList.get(11));
            katC.setText(incomingList.get(12));
            pierwszaLinia.setText(incomingList.get(13));
            drugaLinia.setText(incomingList.get(14));
            trzeciaLinia.setText(incomingList.get(15));
            czwartaLinia.setText(incomingList.get(16));
            wynik.setText(incomingList.get(17));
            jednostkaWynik.setText(incomingList.get(18));
            dolnaPodstawa.setText(incomingList.get(19));
            jednostkaDolnaPodstawa.setText(incomingList.get(20));
            gornaPodstawa.setText(incomingList.get(21));
            jednostkaGornaPodstawa.setText(incomingList.get(22));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(ramie.getText().toString());
                doWyslania.add(jednostkaRamie.getText().toString());
                doWyslania.add(wysokosc.getText().toString());
                doWyslania.add(jednostkaWysokosc.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(jednostkaObwod.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(jednostkaPole.getText().toString());
                doWyslania.add(przekatnaA.getText().toString());
                doWyslania.add(jednostkaPrzekatnaA.getText().toString());
                doWyslania.add(katA.getText().toString());
                doWyslania.add(katB.getText().toString());
                doWyslania.add(katC.getText().toString());
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
                else if(checkBoxRamie.isChecked()){
                    ktoryCheckbox="ramie";
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
                else if(checkBoxKatC.isChecked()){
                    ktoryCheckbox="katC";
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
                        Intent i = new Intent(trapez_rownoramienny.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "trapezRownoramienny");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(trapez_rownoramienny.this, Czat.class);
                        i2.putExtra("miejsce", "trapezRownoramienny");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(trapez_rownoramienny.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "trapezRownoramienny");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(trapez_rownoramienny.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "trapezRownoramienny");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        jednostkaRamie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaRamie);
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
                jednostkaRamie.setText("cm");
                jednostkaObwod.setText("cm");
                jednostkaDolnaPodstawa.setText("cm");
                jednostkaGornaPodstawa.setText("cm");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaWysokosc.setText("cm");
                jednostkaPrzekatnaA.setText("cm");
                wysokosc.setText("");
                przekatnaA.setText("");
                katC.setText("");
                katD.setText("");
                dolnaPodstawa.setText("");
                gornaPodstawa.setText("");
                ramie.setText("");
                pole.setText("");
                obwod.setText("");
                katA.setText("");
                katB.setText("");
                checkBoxRamie.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxKatC.setChecked(false);
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
                        if((!ramie.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=(a+b)*h/2"));
                            drugaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            podstawa3 = podstawa3*podstawa3;
                            ram = ram*ram;
                            Double wys = ram-podstawa3;
                            wys = sqrt(wys);
                            czwartaLinia.setText(Html.fromHtml("P=(a+b)*h/2"));
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            ram = ram*ram;
                            wys =wys*wys;
                            Double podstawa3 = ram+wys;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText("P=(a+b)*h/2");
                            Double podstawa2 = podstawa3+podstawa1;
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if(!dolnaPodstawa.getText().toString().equals("")&&(!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("P=("+podstawa2+"+"+podstawa1+")*"+wys+"/2");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText(Html.fromHtml("tg&#946;=h/((a-b)/2)"));
                            trzeciaLinia.setText(Html.fromHtml("h=tg&#946;*((a-b)/2)"));
                            czwartaLinia.setText("P=(a+b)*h/2");
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double wys = tan*((podstawa2-podstawa1)/2);
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double przekatna = Double.parseDouble(przekatnaA.getText().toString());
                            przekatna = funkcjePrzelicznikowe.dlugosc(przekatna, jednostkaPrzekatnaA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=d<sup><small><small>2</small></small></sup>-(b+((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            przekatna = przekatna*przekatna;
                            Double podstawa3 = (podstawa1+((podstawa2-podstawa1)/2));
                            podstawa3=podstawa3*podstawa3;
                            Double wys = przekatna-podstawa3;
                            wys = sqrt(wys);
                            drugaLinia.setText("P=(a+b)*h/2");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            Double kat = 180-kat2;
                            drugaLinia.setText(Html.fromHtml("tg&#946;=h/((a-b)/2)"));
                            trzeciaLinia.setText(Html.fromHtml("h=tg&#946;*((a-b)/2)"));
                            czwartaLinia.setText("P=(a+b)*h/2");
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double wys = tan*tan*((podstawa2-podstawa1)/2);
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/((a-b)/2)"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wys/tan;
                            podstawa3= podstawa3*2;
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/((a-b)/2)"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wys/tan;
                            podstawa3= podstawa3*2;
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/((a-b)/2)"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wys/tan;
                            podstawa3= podstawa3*2;
                            trzeciaLinia.setText("a=b+(a-b)");
                            Double podstawa2 = podstawa1+podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("tg&#946;=h/((a-b)/2)"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wys/tan;
                            podstawa3= podstawa3*2;
                            trzeciaLinia.setText("a=b+(a-b)");
                            Double podstawa2 = podstawa1+podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("a=b+(a-b)");
                            Double podstawa2 = podstawa1+podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("a=b+(a-b)");
                            Double podstawa2 = podstawa1+podstawa3;
                            czwartaLinia.setText("P=(a+b)*h/2");
                            poleD = (podstawa1+podstawa2)*wys/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katC.getText().toString().equals(""))){
                            Double przekatna = Double.parseDouble(przekatnaA.getText().toString());
                            przekatna = funkcjePrzelicznikowe.dlugosc(przekatna, jednostkaPrzekatnaA.getText().toString());
                            Double kat = Double.parseDouble(katC.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("P=d<sup><small><small>2</small></small></sup>*sin&#947;/2"));
                            Double sin = Math.sin(kat);
                            przekatna = przekatna*przekatna;
                            drugaLinia.setText("P="+przekatna+"*"+sin+"/2");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = przekatna*sin/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katD.getText().toString().equals(""))){
                            Double przekatna = Double.parseDouble(przekatnaA.getText().toString());
                            przekatna = funkcjePrzelicznikowe.dlugosc(przekatna, jednostkaPrzekatnaA.getText().toString());
                            Double kat2 = Double.parseDouble(katD.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#947=180<sup><small><small>2</small></small></sup>-"+kat2+"<sup><small><small>2</small></small></sup>"));
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            drugaLinia.setText(Html.fromHtml("P=d<sup><small><small>2</small></small></sup>*sin&#947;/2"));
                            Double sin = Math.sin(kat);
                            przekatna = przekatna*przekatna;
                            trzeciaLinia.setText("P="+przekatna+"*"+sin+"/2");
                            czwartaLinia.setText("");
                            poleD = przekatna*sin/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxRamie.isChecked()){
                        Double ram=null;
                        if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            drugaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wys+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa3=podstawa3*podstawa3;
                            wys = wys*wys;
                            ram = podstawa3+wys;
                            ram = sqrt(ram);
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("h=2P/(a+b)");
                            Double wys = (2*poleD)/(podstawa1+podstawa2);
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            czwartaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wys+"<sup><small><small>2</small></small></sup>"));
                            podstawa3=podstawa3*podstawa3;
                            wys = wys*wys;
                            ram = podstawa3+wys;
                            ram = sqrt(ram);
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("a=(2P/h)-b");
                            Double podstawa2 = (2*poleD/wys)-podstawa1;
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            czwartaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wys+"<sup><small><small>2</small></small></sup>"));
                            podstawa3=podstawa3*podstawa3;
                            wys = wys*wys;
                            ram = podstawa3+wys;
                            ram = sqrt(ram);
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("b=(2P/h)-a");
                            Double podstawa1 = (2*poleD/wys)-podstawa2;
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            czwartaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>="+podstawa3+"<sup><small><small>2</small></small></sup>+"+wys+"<sup><small><small>2</small></small></sup>"));
                            podstawa3=podstawa3*podstawa3;
                            wys = wys*wys;
                            ram = podstawa3+wys;
                            ram = sqrt(ram);
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!katA.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double kat = Double.parseDouble(katA.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            drugaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            ram = wys/sin;
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!katB.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            Double kat = 180-kat2;
                            drugaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            trzeciaLinia.setText(Html.fromHtml("c=h/sin&#946;"));
                            czwartaLinia.setText("");
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            ram = wys/sin;
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!katA.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            drugaLinia.setText(Html.fromHtml("c=((a-b)/2)/cos&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double cos = Math.cos(kat);
                            ram = podstawa3/cos;
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!katB.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            trzeciaLinia.setText(Html.fromHtml("c=((a-b)/2)/cos&#946;"));
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double cos = Math.cos(kat);
                            ram = podstawa3/cos;
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+2c");
                            drugaLinia.setText("2c=Ob-(a+b)");
                            trzeciaLinia.setText("c=(Ob-(a+b))/2");
                            czwartaLinia.setText("");
                            ram = (ob-podstawa1-podstawa2)/2;
                            ram = funkcjePrzelicznikowe.dlugoscWynik(ram, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ram);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        Double wys=null;
                        if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("h=2P/(a+b)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wys = (2*poleD)/(podstawa1+podstawa2);
                            wys = funkcjePrzelicznikowe.dlugoscWynik(wys, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wys);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            podstawa3 = podstawa3*podstawa3;
                            ram = ram*ram;
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wys = ram-podstawa3;
                            wys = sqrt(wys);
                            wys = funkcjePrzelicznikowe.dlugoscWynik(wys, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wys);
                            wynik.setText(x);
                        }
                        else if((!katA.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tan&#946;=((a-b)/2)/h"));
                            drugaLinia.setText(Html.fromHtml("h=((a-b)/2)/tan&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double tan = Math.tan(kat);
                            wys = podstawa3/tan;
                            wys = funkcjePrzelicznikowe.dlugoscWynik(wys, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wys);
                            wynik.setText(x);
                        }
                        else if((!katB.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            drugaLinia.setText(Html.fromHtml("tan&#946;=((a-b)/2)/h"));
                            trzeciaLinia.setText(Html.fromHtml("h=((a-b)/2)/tan&#946;"));
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double tan = Math.tan(kat);
                            wys = podstawa3/tan;
                            wys = funkcjePrzelicznikowe.dlugoscWynik(wys, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wys);
                            wynik.setText(x);
                        }
                        else if((!katA.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double kat = Double.parseDouble(katA.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            drugaLinia.setText(Html.fromHtml("h=sin&#946;*c"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            wys = sin*ram;
                            wys = funkcjePrzelicznikowe.dlugoscWynik(wys, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wys);
                            wynik.setText(x);
                        }
                        else if((!katB.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            trzeciaLinia.setText(Html.fromHtml("h=sin&#946;*c"));
                            czwartaLinia.setText("");
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            wys = sin*ram;
                            wys = funkcjePrzelicznikowe.dlugoscWynik(wys, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wys);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxDolnaPodstawa.isChecked()){
                        Double podstawa2 = null;
                        if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("a=(2P/h)-b");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa2 = (2*poleD/wys)-podstawa1;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+2c");
                            drugaLinia.setText("a=Ob-(b+2c)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa2 = ob-podstawa1-(2*ram);
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            ram = ram*ram;
                            wys =wys*wys;
                            Double podstawa3 = ram+wys;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText("");
                            podstawa2 = podstawa3+podstawa1;
                            podstawa2 = funkcjePrzelicznikowe.dlugoscWynik(podstawa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa2);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxGornaPodstawa.isChecked()){
                        Double podstawa1 = null;
                        if((!pole.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("b=(2P/h)-a");
                            podstawa1 = (2*poleD/wys)-podstawa2;
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+2c");
                            drugaLinia.setText("b=Ob-(a+2c)");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa1 = ob-podstawa2-(2*ram);
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            ram = ram*ram;
                            wys =wys*wys;
                            Double podstawa3 = ram+wys;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("b=a-(a-b)");
                            czwartaLinia.setText("");
                            podstawa1 = podstawa2-podstawa3;
                            podstawa1 = funkcjePrzelicznikowe.dlugoscWynik(podstawa1, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(podstawa1);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxObwod.isChecked()){
                        Double Ob = null;
                        if((!pole.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            pierwszaLinia.setText("P=(a+b)*h/2");
                            drugaLinia.setText("h=2P/(a+b)");
                            Double wys = (2*poleD)/(podstawa1+podstawa2);
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            czwartaLinia.setText(Html.fromHtml("Ob=a+b+2c"));
                            podstawa3=podstawa3*podstawa3;
                            wys = wys*wys;
                            Double ram = podstawa3+wys;
                            ram = sqrt(ram);
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+((a-b)/2)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("((a-b)/2)<sup><small><small>2</small></small></sup>=c<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            ram = ram*ram;
                            wys =wys*wys;
                            Double podstawa3 = ram+wys;
                            podstawa3 = sqrt(podstawa3);
                            trzeciaLinia.setText("a=b+(a-b)");
                            czwartaLinia.setText("Ob=a+b+2c");
                            Double podstawa2 = podstawa3+podstawa1;
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText("Ob=a+b+2c");
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            czwartaLinia.setText("Ob=a+b+2c");
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("a=b+(a-b)");
                            Double podstawa2 = podstawa1+podstawa3;
                            czwartaLinia.setText("Ob=a+b+2c");
                            Ob = podstawa2+podstawa1+(2*ram);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!gornaPodstawa.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double wys = sin*ram;
                            drugaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            Double cos = Math.cos(kat);
                            Double podstawa3 = cos*ram;
                            trzeciaLinia.setText("a=b+(a-b)");
                            Double podstawa2 = podstawa1+podstawa3;
                            czwartaLinia.setText("Ob=a+b+2c");
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("((a-b)/2)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wys/tan;
                            podstawa3= podstawa3*2;
                            drugaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            podstawa3/=2;
                            podstawa3 = podstawa3*podstawa3;
                            wys = wys*wys;
                            Double ram = podstawa3+wys;
                            ram = sqrt(ram);
                            czwartaLinia.setText("Ob=a+b+2c");
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.poleWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("((a-b)/2)=h/tg&#946;"));
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            Double podstawa3 = wys/tan;
                            podstawa3= podstawa3*2;
                            drugaLinia.setText("b=a-(a-b)");
                            Double podstawa1 = podstawa2-podstawa3;
                            trzeciaLinia.setText(Html.fromHtml("c<sup><small><small>2</small></small></sup>=((a-b)/2)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            podstawa3/=2;
                            podstawa3=podstawa3*podstawa3;
                            wys = wys*wys;
                            Double ram = podstawa3+wys;
                            ram = sqrt(ram);
                            czwartaLinia.setText("Ob=a+b+2c");
                            Ob = podstawa1+podstawa2+(2*ram);
                            Ob = funkcjePrzelicznikowe.poleWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxKatA.isChecked()){
                        Double kat = null;
                        if(!katB.getText().toString().equals("")){
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#946;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kat=180-kat2;
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#946;=h/c"));
                            Double sin = wys/ram;
                            drugaLinia.setText(Html.fromHtml("&#946;=sin&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kat = Math.asin(sin);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=((a-b)/2)/c"));
                            drugaLinia.setText(Html.fromHtml("&#946=cos&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double cos = podstawa3/ram;
                            kat = Math.acos(cos);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tan&#946;=h/((a-b)/2)"));
                            drugaLinia.setText(Html.fromHtml("&#946;=tan&#946;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double tan = wys/podstawa3;
                            kat = Math.atan(tan);
                            kat = Math.toDegrees(kat);
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxKatB.isChecked()){
                        Double kat = null;
                        if(!katA.getText().toString().equals("")){
                            Double kat2 = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#945;=180<sup><small><small>o</small></small></sup>-"+kat2+"<sup><small><small>o</small></small></sup>"));
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kat=180-kat2;
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=h/c"));
                            Double sin = wys/ram;
                            drugaLinia.setText(Html.fromHtml("&#945;=cos&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kat = Math.asin(sin);
                            kat = Math.toDegrees(kat);
                            kat = 180-kat;
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double ram = Double.parseDouble(ramie.getText().toString());
                            ram = funkcjePrzelicznikowe.dlugosc(ram, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#945;=((a-b)/2)/c"));
                            drugaLinia.setText(Html.fromHtml("&#945;=sin&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double cos = podstawa3/ram;
                            kat = Math.acos(cos);
                            kat = Math.toDegrees(kat);
                            kat=180-kat;
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                        else if((!gornaPodstawa.getText().toString().equals(""))&&(!dolnaPodstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double podstawa1 = Double.parseDouble(gornaPodstawa.getText().toString());
                            podstawa1 = funkcjePrzelicznikowe.dlugosc(podstawa1, jednostkaGornaPodstawa.getText().toString());
                            Double podstawa2 = Double.parseDouble(dolnaPodstawa.getText().toString());
                            podstawa2 = funkcjePrzelicznikowe.dlugosc(podstawa2, jednostkaDolnaPodstawa.getText().toString());
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugosc(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("ctan&#945;=h/((a-b)/2)"));
                            drugaLinia.setText(Html.fromHtml("&#945=ctan&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double podstawa3 = (podstawa2-podstawa1)/2;
                            Double tan = wys/podstawa3;
                            kat = Math.atan(tan);
                            kat = Math.toDegrees(kat);
                            kat = 180-kat;
                            String x = funkcjePrzelicznikowe.intowanie(kat);
                            wynik.setText(x);
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        checkBoxRamie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxRamie.isChecked()){
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
                checkBoxRamie.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatC.setChecked(false);
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
                checkBoxRamie.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatC.setChecked(false);
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
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxKatC.setChecked(false);
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
                checkBoxKatC.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxRamie.setChecked(false);
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
                checkBoxKatC.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxGornaPodstawa.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxObwod.setChecked(false);
                jednostkaWynik.setText(R.string.stopnie);
            }
        });
        checkBoxKatC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxRamie.setChecked(false);
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
                checkBoxKatC.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxRamie.setChecked(false);
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
                checkBoxRamie.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxDolnaPodstawa.setChecked(false);
                checkBoxKatC.setChecked(false);
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
                checkBoxKatC.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxRamie.setChecked(false);
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
        mdrawerLayout = findViewById(R.id.drawerTrapez_rownoramienny_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(trapez_rownoramienny.this, Konto.class);
                i.putExtra("miejsce", "Romb");
                startActivity(i);
                Animatoo.animateFade(trapez_rownoramienny.this);
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
        if(v.getId()==R.id.jednostkaRamieTrRownoramienny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bok";
        }
        else if(v.getId()==R.id.jednostkaWysokoscRownoramienny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wysokosc";
        }
        else if(v.getId()==R.id.jednostkaObwodTrRownoramienny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="obwod";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaARownoramienny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaA";
        }
        else if(v.getId()==R.id.jednostkaPoleRownoramienny){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if(v.getId()==R.id.jednostkadolnaRownoramienny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="dolnaPodstawa";
        }
        else if(v.getId()==R.id.jednostkaGornaRownoramienny){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="gornaPodstawa";
        }
        else if((v.getId()==R.id.jednostkaWynikTrRownoramienny)&&(checkBoxPole.isChecked())){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikTrRownoramienny)&&((!checkBoxKatA.isChecked())&&(!checkBoxKatB.isChecked()))){
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
                    if(checkBoxRamie.isChecked()){
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
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="cm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaRamie.setText("cm");
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
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                    if(checkBoxRamie.isChecked()){
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
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="dm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaRamie.setText("dm");
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
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                    if(checkBoxRamie.isChecked()){
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
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="m";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaRamie.setText("m");
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
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                    if(checkBoxRamie.isChecked()){
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
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="km";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaRamie.setText("km");
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
        adapter = new CustomExpandableListAdapter(trapez_rownoramienny.this, lstTitle, lstChild);
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
                    Intent i = new Intent(trapez_rownoramienny.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(trapez_rownoramienny.this);
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
            Intent i = new Intent(trapez_rownoramienny.this, Trapezy.class);
            startActivity(i);
            Animatoo.animateFade(trapez_rownoramienny.this);
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