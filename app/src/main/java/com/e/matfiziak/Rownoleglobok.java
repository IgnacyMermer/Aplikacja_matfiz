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

public class Rownoleglobok extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private NavigationManager navigationManager;
    private Map<String, List<String>> lstChild;
    CheckBox checkBoxPole;
    CheckBox checkBoxBokA, checkBoxBokB;
    CheckBox checkBoxObwod;
    CheckBox checkBoxWysokoscA, checkBoxWysokoscB;
    CheckBox checkBoxKatA;
    CheckBox checkBoxKatB;
    CheckBox checkBoxPrzekatnaA;
    CheckBox checkBoxPrzekatnaB;
    EditText wysokoscA, obwod, przekatnaA, przekatnaB, bokA, bokB, wysokoscB, pole, katA, katB,katC;
    TextView jednostkaWysokoscA, jednostkaObwod, jednostkaPrzekatnaA, jednostkaPrzekatnaB, jednostkaBokA, jednostkaPole, jednostkaWynik;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik, jednostkaWysokoscB, jednostkaBokB;
    ArrayList<String> doWyslania;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rownoleglobok);
        final ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        final AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        checkBoxBokA = findViewById(R.id.checkboxBokARownolegloboku);
        checkBoxBokB = findViewById(R.id.checkboxBokBRownolegloboku);
        checkBoxKatA = findViewById(R.id.checkboxKatARownolegloboku);
        checkBoxKatB = findViewById(R.id.checkboxKatBRownolegloboku);
        checkBoxObwod = findViewById(R.id.checkboxObwodRownolegloboku);
        checkBoxPole = findViewById(R.id.checkboxPoleRownolegloboku);
        checkBoxPrzekatnaA = findViewById(R.id.checkboxPrzekatnaARownolegloboku);
        checkBoxPrzekatnaB = findViewById(R.id.checkboxPrzekatnaBRownolegloboku);
        checkBoxWysokoscA = findViewById(R.id.checkboxWysokoscARownolegloboku);
        checkBoxWysokoscB = findViewById(R.id.checkboxWysokoscBRownolegloboku);
        wysokoscA = findViewById(R.id.wysokosc1Rownoleglobok);
        wysokoscB = findViewById(R.id.wysokosc2Rownoleglobok);
        obwod = findViewById(R.id.obwodRownoleglobok);
        przekatnaA = findViewById(R.id.przekatnaARownoleglobok);
        przekatnaB = findViewById(R.id.przekatnaBRownoleglobok);
        bokA = findViewById(R.id.bokARownoleglobok);
        bokB = findViewById(R.id.bokBRownoleglobok);
        pole = findViewById(R.id.poleRownoleglobok);
        katA = findViewById(R.id.katARownoleglobok);
        katB = findViewById(R.id.katBRownoleglobok);
        katC = findViewById(R.id.katCRownoleglobok);
        jednostkaBokA = findViewById(R.id.jednostkaBokARownoleglobok);
        jednostkaBokB = findViewById(R.id.jednostkaBokBRownoleglobok);
        jednostkaObwod = findViewById(R.id.jednostkaObwodRownoleglobok);
        jednostkaPole = findViewById(R.id.jednostkaPoleRownoleglobok);
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        jednostkaPrzekatnaA = findViewById(R.id.jednostkaPrzekatnaARownoleglobok);
        jednostkaPrzekatnaB = findViewById(R.id.jednostkaPrzekatnaBRownoleglobok);
        jednostkaWynik = findViewById(R.id.jednostkaWynikRownoleglobok);
        jednostkaWysokoscA = findViewById(R.id.jednostkaWysokosc1Rownoleglobok);
        jednostkaWysokoscB = findViewById(R.id.jednostkaWysokosc2Rownoleglobok);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaRownoleglobok);
        drugaLinia = findViewById(R.id.drugaLiniaRownoleglobok);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaRownoleglobok);
        czwartaLinia = findViewById(R.id.czwartaLiniaRownoleglobok);
        wynik = findViewById(R.id.wynikRownoleglobok);
        doWyslania = new ArrayList<>();
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<>();
        Intent incomingIntent = getIntent();
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        checkbox = incomingIntent.getStringExtra("checkbox");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        final String nick = incomingIntent.getStringExtra("nick");
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();
        if(checkbox.equals("pole")){
            checkBoxPole.setChecked(true);
        }
        else if(checkbox.equals("bokA")){
            checkBoxBokA.setChecked(true);
        }
        else if(checkbox.equals("bokB")){
            checkBoxBokB.setChecked(true);
        }
        else if(checkbox.equals("wysokoscA")){
            checkBoxWysokoscA.setChecked(true);
        }
        else if(checkbox.equals("wysokoscB")){
            checkBoxWysokoscB.setChecked(true);
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
            bokA.setText(incomingList.get(0));
            jednostkaBokA.setText(incomingList.get(1));
            wysokoscA.setText(incomingList.get(2));
            jednostkaWysokoscA.setText(incomingList.get(3));
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
            bokB.setText(incomingList.get(20));
            jednostkaBokB.setText(incomingList.get(21));
            wysokoscB.setText(incomingList.get(22));
            jednostkaWysokoscB.setText(incomingList.get(23));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(bokA.getText().toString());
                doWyslania.add(jednostkaBokA.getText().toString());
                doWyslania.add(wysokoscA.getText().toString());
                doWyslania.add(jednostkaWysokoscA.getText().toString());
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
                doWyslania.add(bokB.getText().toString());
                doWyslania.add(jednostkaBokB.getText().toString());
                doWyslania.add(wysokoscB.getText().toString());
                doWyslania.add(jednostkaWysokoscB.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxPole.isChecked()){
                    ktoryCheckbox="pole";
                }
                else if(checkBoxBokA.isChecked()){
                    ktoryCheckbox="bokA";
                }
                else if(checkBoxBokB.isChecked()){
                    ktoryCheckbox="bokB";
                }
                else if(checkBoxWysokoscA.isChecked()){
                    ktoryCheckbox="wysokoscA";
                }
                else if(checkBoxWysokoscB.isChecked()){
                    ktoryCheckbox="wysokoscB";
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
                        Intent i = new Intent(Rownoleglobok.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Rownoleglobok");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        i.putExtra("imageUrl", imageUr);
                        i.putExtra("nick", nick);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Rownoleglobok.this, Szkola.class);
                        i1.putExtra("imageUrl", imageUr);
                        i1.putExtra("nick", nick);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Rownoleglobok.this, Czat.class);
                        i2.putExtra("miejsce", "Rownoleglobok");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.putExtra("imageUrl", imageUr);
                        i2.putExtra("nick", nick);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Rownoleglobok.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Rownoleglobok");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        i3.putExtra("imageUrl", imageUr);
                        i3.putExtra("nick", nick);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Rownoleglobok.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Rownoleglobok");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox", ktoryCheckbox);
                        i4.putExtra("imageUrl", imageUr);
                        i4.putExtra("nick", nick);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        jednostkaBokA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaBokA);
                openContextMenu(v);
            }
        });
        jednostkaBokB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaBokB);
                openContextMenu(v);
            }
        });
        jednostkaWysokoscA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokoscA);
                openContextMenu(v);
            }
        });
        jednostkaWysokoscB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokoscB);
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
        Button buttonCzysc = findViewById(R.id.buttonCzyscRownoleglobok);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wynik.setText("");
                jednostkaWynik.setText("");
                jednostkaBokA.setText("cm");
                jednostkaBokB.setText("cm");
                jednostkaObwod.setText("cm");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaWysokoscA.setText("cm");
                jednostkaWysokoscB.setText("cm");
                jednostkaPrzekatnaA.setText("cm");
                jednostkaPrzekatnaB.setText("cm");
                wysokoscA.setText("");
                wysokoscB.setText("");
                przekatnaA.setText("");
                przekatnaB.setText("");
                bokA.setText("");
                bokB.setText("");
                pole.setText("");
                obwod.setText("");
                katA.setText("");
                katB.setText("");
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        Button buttonOblicz = findViewById(R.id.buttonObliczRownoleglobok);
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxPole.isChecked()){
                    Double poleD = null;
                    if((!bokA.getText().toString().equals(""))&&(!wysokoscB.getText().toString().equals(""))){
                        Double a = Double.parseDouble(bokA.getText().toString());
                        a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                        Double h2 = Double.parseDouble(wysokoscB.getText().toString());
                        h2 = funkcjePrzelicznikowe.dlugosc(h2, jednostkaWysokoscB.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=a*h<sub><small><small>a</small></small></sub>"));
                        drugaLinia.setText("P="+a+"*"+h2);
                        trzeciaLinia.setText("");
                        czwartaLinia.setText("");
                        poleD=a*h2;
                        poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(poleD);
                        wynik.setText(x);
                    }
                    else if((!bokB.getText().toString().equals(""))&&(!wysokoscA.getText().toString().equals(""))){
                        Double b = Double.parseDouble(bokB.getText().toString());
                        b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                        Double h1 = Double.parseDouble(wysokoscA.getText().toString());
                        h1 = funkcjePrzelicznikowe.dlugosc(h1, jednostkaWysokoscA.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=b*h<sub><small><small>b</small></small></sub>"));
                        drugaLinia.setText("P="+b+"*"+h1);
                        trzeciaLinia.setText("");
                        czwartaLinia.setText("");
                        poleD = b*h1;
                        poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(poleD);
                        wynik.setText(x);
                    }
                    else if((!bokB.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                        Double b = Double.parseDouble(bokB.getText().toString());
                        b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                        Double a = Double.parseDouble(bokA.getText().toString());
                        a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                        Double kat = Double.parseDouble(katA.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=a*b*sin&#945;"));
                        kat = Math.toRadians(kat);
                        Double sin = Math.sin(kat);
                        drugaLinia.setText("P="+a+"*"+b+"*"+sin);
                        trzeciaLinia.setText("");
                        czwartaLinia.setText("");
                        poleD = a*b*sin;
                        poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(poleD);
                        wynik.setText(x);
                    }
                    else if((!bokB.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                        Double b = Double.parseDouble(bokB.getText().toString());
                        b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                        Double a = Double.parseDouble(bokA.getText().toString());
                        a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                        Double kat2 = Double.parseDouble(katA.getText().toString());
                        Double kat = 180-kat2;
                        pierwszaLinia.setText(Html.fromHtml("P=a*b*sin&#945;"));
                        kat = Math.toRadians(kat);
                        Double sin = Math.sin(kat);
                        drugaLinia.setText("P="+a+"*"+b+"*"+sin);
                        trzeciaLinia.setText("");
                        czwartaLinia.setText("");
                        poleD = a*b*sin;
                        poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(poleD);
                        wynik.setText(x);
                    }
                    else if((!przekatnaA.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))&&(!katC.getText().toString().equals(""))){
                        Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                        d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                        Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                        d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                        Double kat = Double.parseDouble(katC.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=&frac12;*d<sub><small><small>1</small></small></sub>*d<sub><small><small>2</small></small></sub>*sin&#947;"));
                        drugaLinia.setText(Html.fromHtml("P=&frac12;*"+d1+"*"+d2+"*sin"+kat));
                        trzeciaLinia.setText("");
                        czwartaLinia.setText("");
                        kat = Math.toRadians(kat);
                        Double sin = Math.sin(kat);
                        poleD = d1*d2*sin/2;
                        poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(poleD);
                        wynik.setText(x);
                    }
                }
                else if(checkBoxBokA.isChecked()){
                    Double a = null;
                    if((!obwod.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                        Double b = Double.parseDouble(bokB.getText().toString());
                        b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                        Double Ob = Double.parseDouble(obwod.getText().toString());
                        Ob = funkcjePrzelicznikowe.dlugosc(Ob,jednostkaObwod.getText().toString());
                        pierwszaLinia.setText("Ob=2a+2b");
                        drugaLinia.setText("2a=Ob-2b");
                        trzeciaLinia.setText("a=(Ob-2b)/2");
                        Ob=Ob-(2*b);
                        czwartaLinia.setText("a="+Ob+"/2");
                        a=Ob/2;
                        a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(a);
                        wynik.setText(x);
                    }
                    else if((!pole.getText().toString().equals(""))&&(!wysokoscB.getText().toString().equals(""))){
                        Double poleD = Double.parseDouble(pole.getText().toString());
                        poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                        Double h2 = Double.parseDouble(wysokoscB.getText().toString());
                        h2 = funkcjePrzelicznikowe.dlugosc(h2, jednostkaWysokoscB.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=a*h<sub><small><small>a</small></small></sub>"));
                        drugaLinia.setText(Html.fromHtml("a=P/h<sub><small><small>a</small></small></sub>"));
                        trzeciaLinia.setText("a="+poleD+"/"+h2);
                        czwartaLinia.setText("");
                        a = poleD/h2;
                        a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(a);
                        wynik.setText(x);
                    }
                }
                else if(checkBoxBokB.isChecked()){
                    Double b = null;
                    if((!obwod.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                        Double a = Double.parseDouble(bokA.getText().toString());
                        a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                        Double Ob = Double.parseDouble(obwod.getText().toString());
                        Ob = funkcjePrzelicznikowe.dlugosc(Ob,jednostkaObwod.getText().toString());
                        pierwszaLinia.setText("Ob=2a+2b");
                        drugaLinia.setText("2b=Ob-2a");
                        trzeciaLinia.setText("b=(Ob-2a)/2");
                        Ob=Ob-(2*a);
                        czwartaLinia.setText("b="+Ob+"/2");
                        b=Ob/2;
                        b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(b);
                        wynik.setText(x);
                    }
                    else if((!pole.getText().toString().equals(""))&&(!wysokoscA.getText().toString().equals(""))){
                        Double poleD = Double.parseDouble(pole.getText().toString());
                        poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                        Double h1 = Double.parseDouble(wysokoscA.getText().toString());
                        h1 = funkcjePrzelicznikowe.dlugosc(h1, jednostkaWysokoscA.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=b*h<sub><small><small>b</small></small></sub>"));
                        drugaLinia.setText(Html.fromHtml("b=P/h<sub><small><small>b</small></small></sub>"));
                        trzeciaLinia.setText("b="+poleD+"/"+h1);
                        czwartaLinia.setText("");
                        b = poleD/h1;
                        b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(b);
                        wynik.setText(x);
                    }

                }
                else if(checkBoxWysokoscA.isChecked()){
                    Double h1=null;
                    if((!pole.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                        Double poleD = Double.parseDouble(pole.getText().toString());
                        poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                        Double b = Double.parseDouble(bokB.getText().toString());
                        b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=b*h<sub><small><small>b</small></small></sub>"));
                        drugaLinia.setText(Html.fromHtml("h<sub><small><small>b</small></small></sub>=P/b"));
                        trzeciaLinia.setText(Html.fromHtml("h<sub><small><small>b</small></small></sub>="+poleD+"/"+b));
                        czwartaLinia.setText("");
                        h1 = poleD/b;
                        h1 = funkcjePrzelicznikowe.dlugoscWynik(h1, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(h1);
                        wynik.setText(x);
                    }
                }
                else if(checkBoxWysokoscB.isChecked()){
                    Double h2 = null;
                    if((!pole.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                        Double poleD = Double.parseDouble(pole.getText().toString());
                        poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                        Double a = Double.parseDouble(bokA.getText().toString());
                        a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                        pierwszaLinia.setText(Html.fromHtml("P=a*h<sub><small><small>a</small></small></sub>"));
                        drugaLinia.setText(Html.fromHtml("h<sub><small><small>a</small></small></sub>=P/a"));
                        trzeciaLinia.setText(Html.fromHtml("h<sub><small><small>a</small></small></sub>="+poleD+"/"+a));
                        czwartaLinia.setText("");
                        h2 = poleD/a;
                        h2 = funkcjePrzelicznikowe.dlugoscWynik(h2, jednostkaWynik.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(h2);
                        wynik.setText(x);
                    }
                }
                else if(checkBoxObwod.isChecked()){
                    Double Ob=null;
                    if((!bokB.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                        Double b = Double.parseDouble(bokB.getText().toString());
                        b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                        Double a = Double.parseDouble(bokA.getText().toString());
                        a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                        pierwszaLinia.setText("Ob=2a+2b");
                        drugaLinia.setText("Ob=2*"+a+"+2*"+b);
                        trzeciaLinia.setText("");
                        czwartaLinia.setText("");
                        Ob = (2*a)+(2*b);
                        Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaObwod.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(Ob);
                        wynik.setText(x);
                    }
                }
            }
        });
        checkBoxBokA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBokB.setChecked(false);
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxBokA.isChecked()){
                    jednostkaWynik.setText(bokAWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        bokAWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxBokB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBokA.setChecked(false);
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxBokB.isChecked()){
                    jednostkaWynik.setText(bokBWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        bokBWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzekatnaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
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
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
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
                checkBoxBokA.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
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
        checkBoxWysokoscA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxWysokoscA.isChecked()){
                    jednostkaWynik.setText(wysokoscAWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wysokoscAWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWysokoscB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxWysokoscA.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxWysokoscB.isChecked()){
                    jednostkaWynik.setText(wysokoscBWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wysokoscBWynik = jednostkaWynik.getText().toString();
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
                checkBoxWysokoscA.setChecked(false);
                checkBoxBokA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
                checkBoxBokB.setChecked(false);
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
                checkBoxWysokoscA.setChecked(false);
                checkBoxBokA.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
                checkBoxBokB.setChecked(false);
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
                checkBoxWysokoscA.setChecked(false);
                checkBoxBokA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxWysokoscB.setChecked(false);
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
        mdrawerLayout = findViewById(R.id.drawerRownoleglobok_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Rownoleglobok.this, Konto.class);
                i.putExtra("miejsce", "Romb");
                startActivity(i);
                Animatoo.animateFade(Rownoleglobok.this);
                return false;
            }
        });
        expandableListView.addHeaderView(listHeaderView);
        final CircleImageView imageView = listHeaderView.findViewById(R.id.avatar);
        final TextView nickTe = listHeaderView.findViewById(R.id.name);
        dane dane1 = new dane();
        String nick2 = dane1.nick;
        String imageUrl = dane1.imageUrl;
        if(nick2!=null){
            nickTe.setText(nick2);
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
        if(v.getId()==R.id.jednostkaBokARownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bokA";
        }
        else if(v.getId()==R.id.jednostkaBokBRownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bokB";
        }
        else if(v.getId()==R.id.jednostkaWysokosc1Rownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wysokoscA";
        }
        else if(v.getId()==R.id.jednostkaWysokosc2Rownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wysokoscB";
        }
        else if(v.getId()==R.id.jednostkaObwodRownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="obwod";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaARownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaA";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaBRownoleglobok){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaB";
        }
        else if(v.getId()==R.id.jednostkaPoleRownoleglobok){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if((v.getId()==R.id.jednostkaWynikRownoleglobok)&&(checkBoxPole.isChecked())){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikRownoleglobok)&&((!checkBoxKatA.isChecked())&&(!checkBoxKatB.isChecked()))){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
    }
    String poleWynik="cm", bokAWynik="cm", wysokoscAWynik="cm", przekatnaAWynik="cm", obwodWynik="cm", przekatnaBWynik="cm";
    String ktory2="", ktory3="", bokBWynik="cm", wysokoscBWynik="cm";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    if(checkBoxBokA.isChecked()){
                        bokAWynik="cm";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokBWynik="cm";
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
                    else if(checkBoxWysokoscA.isChecked()){
                        wysokoscAWynik="cm";
                    }
                    else if(checkBoxWysokoscB.isChecked()){
                        wysokoscBWynik="cm";
                    }
                }
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("cm");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("cm");
                }
                else if(ktory.equals("wysokoscA")){
                    jednostkaWysokoscA.setText("cm");
                }
                else if(ktory.equals("wysokoscB")){
                    jednostkaWysokoscB.setText("cm");
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
                    if(checkBoxBokA.isChecked()){
                        bokAWynik="dm";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokBWynik="dm";
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
                    else if(checkBoxWysokoscA.isChecked()){
                        wysokoscAWynik="dm";
                    }
                    else if(checkBoxWysokoscB.isChecked()){
                        wysokoscBWynik="dm";
                    }
                }
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("dm");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("dm");
                }
                else if(ktory.equals("wysokoscA")){
                    jednostkaWysokoscA.setText("dm");
                }
                else if(ktory.equals("wysokoscB")){
                    jednostkaWysokoscB.setText("dm");
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
                    if(checkBoxBokA.isChecked()){
                        bokAWynik="m";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokBWynik="m";
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
                    else if(checkBoxWysokoscA.isChecked()){
                        wysokoscAWynik="m";
                    }
                    else if(checkBoxWysokoscB.isChecked()){
                        wysokoscBWynik="m";
                    }
                }
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("m");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("m");
                }
                else if(ktory.equals("wysokoscA")){
                    jednostkaWysokoscA.setText("m");
                }
                else if(ktory.equals("wysokoscB")){
                    jednostkaWysokoscB.setText("m");
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
                    if(checkBoxBokA.isChecked()){
                        bokAWynik="km";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokBWynik="km";
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
                    else if(checkBoxWysokoscA.isChecked()){
                        wysokoscAWynik="km";
                    }
                    else if(checkBoxWysokoscB.isChecked()){
                        wysokoscBWynik="km";
                    }
                }
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("km");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("km");
                }
                else if(ktory.equals("wysokoscA")){
                    jednostkaWysokoscA.setText("km");
                }
                else if(ktory.equals("wysokoscB")){
                    jednostkaWysokoscB.setText("km");
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
        adapter = new CustomExpandableListAdapter(Rownoleglobok.this, lstTitle, lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // getSupportActionBar().setTitle(lstTitle.get(groupPosition).toString());
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                // getSupportActionBar().setTitle("EDMTDev");
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition)))).get(childPosition).toString();
                if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(Rownoleglobok.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(Rownoleglobok.this);
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
            Intent i = new Intent(Rownoleglobok.this, Czworokaty.class);
            startActivity(i);
            Animatoo.animateFade(Rownoleglobok.this);
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