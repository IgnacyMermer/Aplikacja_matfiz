package com.e.matfiziak;

import static java.lang.Math.sqrt;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class Pole_trojkata_rownoramiennego extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;
    TextView jednostkaRamie;
    TextView jednostkaObwod;
    TextView jednostkaPodstawa;
    TextView jednostkaPole;
    TextView jednostkaWysokosc;
    TextView jednostkaOpisany;
    TextView jednostkaWpisany;
    TextView jednostkaWynik;
    ArrayList<String> doWyslania;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pole_trojkata_rownoramiennego);
        ScrollView relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        final Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_enter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<String>();
        Intent incomingIntent = getIntent();
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        checkbox = incomingIntent.getStringExtra("checkbox");
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        /**/
        final EditText podstawa = findViewById(R.id.podstawaRownoramienny);
        final EditText ramie = findViewById(R.id.ramieRownoramienny);
        final EditText obwod = findViewById(R.id.obwodRownoramienny);
        final EditText pole = findViewById(R.id.poleRownoramienny);
        final EditText wysokosc = findViewById(R.id.wysokoscRownoramienny);
        final EditText wpisany = findViewById(R.id.wpisanyRownoramienny);
        final EditText opisany = findViewById(R.id.opisanyRownoramienny);
        final EditText katA = findViewById(R.id.katCRownoramienny);
        final EditText katC = findViewById(R.id.katCRownoramienny);
        jednostkaPodstawa = findViewById(R.id.jednostkaPodstawa);
        jednostkaPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPodstawa);
                openContextMenu(v);
            }
        });
        jednostkaRamie = findViewById(R.id.jednostkaRamie);
        jednostkaRamie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaRamie);
                openContextMenu(v);
            }
        });
        jednostkaObwod = findViewById(R.id.jednostkaObwod);
        jednostkaObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaObwod);
                openContextMenu(v);
            }
        });
        jednostkaPole = findViewById(R.id.jednostkaPole);
        jednostkaPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPole);
                openContextMenu(v);
            }
        });
        jednostkaWysokosc = findViewById(R.id.jednostkaWysokosc);
        jednostkaWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokosc);
                openContextMenu(v);
            }
        });
        jednostkaOpisany = findViewById(R.id.jednostkaOpisany);
        jednostkaOpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaOpisany);
                openContextMenu(v);
            }
        });
        jednostkaWpisany = findViewById(R.id.jednostkaWpisany);
        jednostkaWpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWpisany);
                openContextMenu(v);
            }
        });
        jednostkaWynik = findViewById(R.id.jednostkaWynik);
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        final CheckBox checkBoxPodstawa = findViewById(R.id.podstawaWybor);
        final CheckBox checkBoxRamie = findViewById(R.id.ramieWybor);
        final CheckBox checkBoxObwod = findViewById(R.id.obwodWybor);
        final CheckBox checkBoxPole = findViewById(R.id.poleWybor);
        final CheckBox checkBoxWysokosc = findViewById(R.id.wysokoscWybor);
        final CheckBox checkBoxWpisany = findViewById(R.id.wpisanyWybor);
        final CheckBox checkBoxOpisany = findViewById(R.id.opisanyWybor);
        final CheckBox checkBoxKatA = findViewById(R.id.katAWybor);
        final CheckBox checkBoxKatC = findViewById(R.id.katCWybor);
        final TextView wynik = findViewById(R.id.wynikRownoramienny);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRownoramienny);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaRownoramienny);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRownoramienny);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaRownoramienny);
        if(checkbox.equals("pole")){
            checkBoxPole.setChecked(true);
        }
        else if(checkbox.equals("podstawa")){
            checkBoxPodstawa.setChecked(true);
        }
        else if(checkbox.equals("wysokosc")){
            checkBoxWysokosc.setChecked(true);
        }
        else if(checkbox.equals("wpisany")){
            checkBoxWpisany.setChecked(true);
        }
        else if(checkbox.equals("opisany")){
            checkBoxOpisany.setChecked(true);
        }
        else if(checkbox.equals("ramie")){
            checkBoxRamie.setChecked(true);
        }
        else if(checkbox.equals("obwod")){
            checkBoxObwod.setChecked(true);
        }
        else if(checkbox.equals("katA")){
            checkBoxKatA.setChecked(true);
        }
        else if(checkbox.equals("katC")){
            checkBoxKatC.setChecked(true);
        }
        if(incomingList!=null){
            podstawa.setText(incomingList.get(0));
            ramie.setText(incomingList.get(1));
            obwod.setText(incomingList.get(2));
            pole.setText(incomingList.get(3));
            wysokosc.setText(incomingList.get(4));
            wpisany.setText(incomingList.get(5));
            opisany.setText(incomingList.get(6));
            katA.setText(incomingList.get(7));
            katC.setText(incomingList.get(8));
            pierwszaLinia.setText(incomingList.get(9));
            drugaLinia.setText(incomingList.get(10));
            trzeciaLinia.setText(incomingList.get(11));
            czwartaLinia.setText(incomingList.get(12));
            wynik.setText(incomingList.get(13));
            jednostkaWynik.setText(incomingList.get(14));
            jednostkaPodstawa.setText(incomingList.get(15));
            jednostkaRamie.setText(incomingList.get(16));
            jednostkaObwod.setText(incomingList.get(17));
            if(incomingList.get(18).equals("mm2")){
                jednostkaPole.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
            }
            else if(incomingList.get(18).equals("cm2")){
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
            }
            else if(incomingList.get(18).equals("dm2")){
                jednostkaPole.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
            }
            else if(incomingList.get(18).equals("m2")){
                jednostkaPole.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
            }
            else{
                jednostkaPole.setText(incomingList.get(18));
            }
            jednostkaWysokosc.setText(incomingList.get(19));
            jednostkaWpisany.setText(incomingList.get(20));
            jednostkaOpisany.setText(incomingList.get(21));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(podstawa.getText().toString());
                doWyslania.add(ramie.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(wysokosc.getText().toString());
                doWyslania.add(wpisany.getText().toString());
                doWyslania.add(opisany.getText().toString());
                doWyslania.add(katA.getText().toString());
                doWyslania.add(katC.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                doWyslania.add(jednostkaPodstawa.getText().toString());
                doWyslania.add(jednostkaRamie.getText().toString());
                doWyslania.add(jednostkaObwod.getText().toString());
                doWyslania.add(jednostkaPole.getText().toString());
                doWyslania.add(jednostkaWysokosc.getText().toString());
                doWyslania.add(jednostkaWpisany.getText().toString());
                doWyslania.add(jednostkaOpisany.getText().toString());

                String ktoryCheckbox="";
                if(checkBoxPole.isChecked()){
                    ktoryCheckbox="pole";
                }
                else if(checkBoxRamie.isChecked()){
                    ktoryCheckbox="ramie";
                }
                else if(checkBoxWysokosc.isChecked()){
                    ktoryCheckbox="wysokosc";
                }
                else if(checkBoxWpisany.isChecked()){
                    ktoryCheckbox="wpisany";
                }
                else if(checkBoxOpisany.isChecked()){
                    ktoryCheckbox="opisany";
                }
                else if(checkBoxKatA.isChecked()){
                    ktoryCheckbox="katA";
                }
                else if(checkBoxKatC.isChecked()){
                    ktoryCheckbox="katC";
                }
                else if(checkBoxPodstawa.isChecked()){
                    ktoryCheckbox="podstawa";
                }
                else if(checkBoxObwod.isChecked()){
                    ktoryCheckbox="obwod";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(Pole_trojkata_rownoramiennego.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Pole_trojkata_rownoramiennego");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Pole_trojkata_rownoramiennego.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Pole_trojkata_rownoramiennego.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Pole_trojkata_rownoramiennego");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Pole_trojkata_rownoramiennego.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Pole_trojkata_rownoramiennego");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Pole_trojkata_rownoramiennego.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Pole_trojkata_rownoramiennego");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox",ktoryCheckbox);
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        Button buttonOblicz = findViewById(R.id.buttonRownoramienny);
        Button buttonCzysc = findViewById(R.id.buttonCzyscRownoramienny);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                poleJ = "cm2";
                obwodJ = "cm";
                opisanyJ = "cm";
                wpisanyJ = "cm";
                ramieJ = "cm";
                wysokoscJ = "cm";
                podstawaJ = "cm";
                podstawa.setText("");
                ramie.setText("");
                wysokosc.setText("");
                wpisany.setText("");
                opisany.setText("");
                obwod.setText("");
                pole.setText("");
                katA.setText("");
                katC.setText("");
                jednostkaWynik.setText("");
                jednostkaObwod.setText("cm");
                jednostkaOpisany.setText("cm");
                jednostkaRamie.setText("cm");
                jednostkaWysokosc.setText("cm");
                jednostkaPodstawa.setText("cm");
                jednostkaWpisany.setText("cm");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                checkBoxKatA.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
            }
        });
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkBoxPole.isChecked()) {
                        Double pole = null;
                        if ((!wysokosc.getText().toString().equals("")) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;" + a + "*" + b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            pole = a * b / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if ((!ramie.getText().toString().equals("")) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("(&frac14;a<sup><small><small>2</small></small></sup>)=b<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(4*(" + a + "<sup><small><small>2</small></small></sup>-" + b + "<sup><small><small>2</small></small></sup>))"));
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            Double c = ((a * a) - (b * b)) * 4;
                            c = sqrt(c);
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;*" + c + "*" + b));
                            pole = c * b / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        } else if ((!obwod.getText().toString().equals("")) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(obwod.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a,jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(wpisany.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*Ob*r"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;*" + a + "*" + b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            pole = a * b / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole,jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        } else if ((!podstawa.getText().toString().equals("")) && (!ramie.getText().toString().equals("")) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(wpisany.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWpisany.getText().toString());
                            Double d = Double.parseDouble(ramie.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("Ob=a+b");
                            drugaLinia.setText("Ob=" + a + "+" + d);
                            a = a + d;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*Ob*r"));
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;*" + a + "*" + b));
                            pole = a * b / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        } else if ((!ramie.getText().toString().equals("")) && (!podstawa.getText().toString().equals("")) && (!opisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a,jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b,jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(opisany.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b<sup><small><small>2</small></small></sup>/(4*R)"));
                            drugaLinia.setText(Html.fromHtml("P=" + b + "*" + a + "<sup><small><small>2</small></small></sup>/(4*" + c + ")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double d = b * a * a / 4 / c;
                            pole = d;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if (((katC.getText().toString().equals("120")) || (katA.getText().toString().equals("30"))) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("b=2x");
                            drugaLinia.setText("h=x");
                            trzeciaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            pole = a * a / 4;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;3"));
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=b/&#8730;2"));
                            drugaLinia.setText(Html.fromHtml("&frac12;a=b/&#8730;2"));
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            czwartaLinia.setText(Html.fromHtml("P=b<sup><small><small>2</small></small></sup>/2"));
                            pole = a * a / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if (((katC.getText().toString().equals("120")) || (katA.getText().toString().equals("30"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            drugaLinia.setText("h=x");
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*h*a"));
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;*(a*&#8730;3/6)*a"));
                            pole = a * a / 12;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;3"));
                        }
                        else if (((katC.getText().toString().equals("90")) || (katA.getText().toString().equals("45"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=&frac12;a"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*a*(&frac12;*a)"));
                            czwartaLinia.setText(Html.fromHtml("P=&frac14*a<sup><small><small>2</small></small></sup>"));
                            pole = a * a / 4;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("h=x");
                            drugaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12*a*h"));
                            czwartaLinia.setText(Html.fromHtml("P=h&#8730;3*h"));
                            pole = a * a;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;3"));
                        }
                        else if (((katC.getText().toString().equals("90")) || (katA.getText().toString().equals("45"))) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=h"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;a*h"));
                            trzeciaLinia.setText(Html.fromHtml("P=h*h"));
                            czwartaLinia.setText("");
                            pole = a * a;
                            pole = funkcjePrzelicznikowe.poleWynik(pole,jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if (((katC.getText().toString().equals("120")) || (katA.getText().toString().equals("30"))) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wpisany.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=r/&#8730;3*2+r"));
                            Double y = a / sqrt(3) * 2 + a;
                            drugaLinia.setText(Html.fromHtml("&frac12;a=h*&#8730;3"));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;a=" + y + "*&#8730;3"));
                            Double y2 = y * sqrt(3);
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;a*h"));
                            pole = y * y2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if (((katC.getText().toString().equals("90")) || (katA.getText().toString().equals("45"))) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wpisany.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=a+a*&#8730;2"));
                            Double y = a + (a * sqrt(2));
                            drugaLinia.setText(Html.fromHtml("&frac12;a=h"));
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            czwartaLinia.setText("P=" + y + "*" + y);
                            pole = y * y;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if ((!podstawa.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>-&frac14*a<sup><small><small>2</small></small></sup>"));
                            Double c = b * b;
                            c = c - (a * a / 4);
                            drugaLinia.setText(Html.fromHtml("h=&#8730;" + c));
                            c = sqrt(c);
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            czwartaLinia.setText(Html.fromHtml("P=&frac12;" + a + "*" + c));
                            pole = a * c / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                        else if ((!obwod.getText().toString().equals("")) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(obwod.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText("b=(Ob-a)/2");
                            Double c = a - b;
                            c = c / 2;
                            drugaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=(&frac12;a)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText(Html.fromHtml("h=&#8730;(b<sup><small><small>2</small></small></sup>-&frac14;a<sup><small><small>2</small></small></sup>)"));
                            Double h = c * c - (b * b / 4);
                            h = sqrt(h);
                            czwartaLinia.setText(Html.fromHtml("&frac12;*a*h"));
                            pole = b * h / 2;
                            pole = funkcjePrzelicznikowe.poleWynik(pole, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x3);
                        }
                    }
                    else if (checkBoxRamie.isChecked()) {
                        Double ramie = null;
                        if ((!podstawa.getText().toString().equals("")) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=(&frac12;*a)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=&frac14;*" + a + "<sup><small><small>2</small></small></sup>+" + b + "<sup><small><small>2</small></small></sup>"));
                            Double c = a * a / 4 + (b * b);
                            trzeciaLinia.setText(Html.fromHtml("b=&#8730;" + c));
                            ramie = sqrt(c);
                            czwartaLinia.setText("");
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x3);
                        }
                        else if ((!obwod.getText().toString().equals("")) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(obwod.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText("b=(Ob-a)/2");
                            Double c = a - b;
                            drugaLinia.setText("b=" + c + "/2");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            ramie = c / 2;
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x3);
                        }
                        else if ((!pole.getText().toString().equals("")) && (!podstawa.getText().toString().equals("")) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(wpisany.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*Ob*r"));
                            drugaLinia.setText("Ob=2*P/r");
                            Double d = 2 * a / c;
                            trzeciaLinia.setText("Ob=a+2*b");
                            czwartaLinia.setText("b=(Ob-a)/2");
                            ramie = d - b;
                            ramie = ramie / 2;
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || katC.getText().toString().equals("120")) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            drugaLinia.setText(Html.fromHtml("b=2x"));
                            trzeciaLinia.setText(Html.fromHtml("b=a/&#8730;3"));
                            czwartaLinia.setText("");
                            ramie = a / 3;
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;3"));
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a,jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=x"));
                            drugaLinia.setText(Html.fromHtml("b=x&#8730;2"));
                            trzeciaLinia.setText(Html.fromHtml("b=a/2*&#8730;2"));
                            czwartaLinia.setText("");
                            ramie = a / 2;
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;2"));
                        }
                        else if ((!pole.getText().toString().equals("")) && (!podstawa.getText().toString().equals("")) && (!opisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(opisany.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b<sup><small><small>2</small></small></sup>/(4*R)"));
                            drugaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=P*4*R/a"));
                            Double d = a * 4 * c / b;
                            trzeciaLinia.setText(Html.fromHtml("b=&#8730;" + d));
                            czwartaLinia.setText("");
                            ramie = sqrt(d);
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x3);
                        }
                        else if (((katC.getText().toString().equals("120")) || (katA.getText().toString().equals("30"))) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wpisany.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=r+(2*r/&#8730;3)"));
                            Double b = a + (2 * a / sqrt(3));
                            String x3 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText("b=2*h");
                            trzeciaLinia.setText("b=2*" + x3);
                            czwartaLinia.setText("");
                            ramie = 2 * b;
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x4 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x4);
                        }
                        else if (((katC.getText().toString().equals("90")) || (katA.getText().toString().equals("45"))) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wpisany.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=r+r&#8730;2"));
                            drugaLinia.setText(Html.fromHtml("b=h&#8730;2"));
                            trzeciaLinia.setText(Html.fromHtml("b=(r+r&#8730;2)*&#8730;2"));
                            czwartaLinia.setText(Html.fromHtml("b=r&3#8730;+2r"));
                            ramie = a * sqrt(2);
                            ramie += 2 * a;
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x3);
                        }
                        else if ((!podstawa.getText().toString().equals("")) && (!pole.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a,jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(pole.getText().toString());
                            b = funkcjePrzelicznikowe.pole(b,jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            drugaLinia.setText("h=2*P/a");
                            Double c = 2 * b / a;
                            trzeciaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=(&frac12;a)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("b=&#8730;(&frac14;*a<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>)"));
                            ramie = a * a / 4;
                            ramie += c * c;
                            ramie = sqrt(ramie);
                            ramie = funkcjePrzelicznikowe.dlugoscWynik(ramie,jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(ramie);
                            wynik.setText("" + x3);
                        }
                    }
                    else if (checkBoxPodstawa.isChecked()) {
                        Double podstawa = null;
                        if ((!wysokosc.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=h<sup><small><small>2</small></small></sup>+(&frac12;a)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("&frac14;*a<sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText(Html.fromHtml("&frac14;*a<sup><small><small>2</small></small></sup>=" + b + "<sup><small><small>2</small></small></sup>-" + a + "<sup><small><small>2</small></small></sup>"));
                            Double c = b * b - (a * a);
                            czwartaLinia.setText(Html.fromHtml("&frac12;a=&#8730;" + c));
                            podstawa = sqrt(c);
                            podstawa = podstawa * 2;
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa,jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText("" + x3);
                        }
                        else if((!wpisany.getText().toString().equals(""))&&((katC.getText().toString().equals("120"))||(katA.getText().toString().equals("30")))){
                            Double a = Double.parseDouble(wpisany.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a,jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=r+(2*r/&#8730;3)"));
                            Double b = a + (2 * a / sqrt(3));
                            String x3 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText("b=2*h");
                            trzeciaLinia.setText("b=2*" + x3);
                            czwartaLinia.setText(Html.fromHtml("a=b&#8730;3"));
                            Double ramie = 2 * b;
                            ramie=ramie*sqrt(3);
                            podstawa = ramie;
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x2 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText("" + x2);
                        }
                        else if ((!obwod.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(obwod.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("a=Ob-2*b");
                            drugaLinia.setText("a=" + a + "*" + b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa = a - (b + b);
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText("" + x3);
                        }
                        else if ((!ramie.getText().toString().equals("")) && (!opisany.getText().toString().equals("")) && (!pole.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(opisany.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaOpisany.getText().toString());
                            Double c = Double.parseDouble(pole.getText().toString());
                            c = funkcjePrzelicznikowe.pole(c, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b<sup><small><small>2</small></small></sup>/(4*R)"));
                            drugaLinia.setText(Html.fromHtml("a=P*4*R/b<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText(Html.fromHtml("a=" + c + "*4*" + b + "/" + a + "<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText("");
                            podstawa = c * 4 * b / a / a;
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText("" + x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;*a=h&#8730;3"));
                            drugaLinia.setText(Html.fromHtml("a=2*" + a + "&#8730;3"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa = 2 * a;
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText(Html.fromHtml("" + x3 + "&#8730;3"));
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12*a=h"));
                            drugaLinia.setText("a=2*" + a);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            podstawa = 2 * a;
                            funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText("" + x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("b=2*x");
                            drugaLinia.setText(Html.fromHtml("&frac12;*a=x&#8730;3"));
                            trzeciaLinia.setText(Html.fromHtml("a=b&#8730;3"));
                            czwartaLinia.setText("");
                            podstawa = a;
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText(Html.fromHtml("" + x3 + "&#8730;3"));
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("b=x&#8730;2"));
                            drugaLinia.setText(Html.fromHtml("&frac12;a=x"));
                            trzeciaLinia.setText(Html.fromHtml("a=2*b/&#8730;2"));
                            czwartaLinia.setText(Html.fromHtml("a=b&#8730;2"));
                            podstawa = a;
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText(Html.fromHtml("" + x3 + "&#8730;2"));
                        }
                        else if ((!wpisany.getText().toString().equals("")) && (!pole.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wpisany.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            Double c = Double.parseDouble(pole.getText().toString());
                            c = funkcjePrzelicznikowe.pole(c, jednostkaPole.getText().toString());
                            pierwszaLinia.setText("P=Ob/r");
                            drugaLinia.setText("P=(a+b+b)/r");
                            trzeciaLinia.setText("a=P*r-2*b");
                            czwartaLinia.setText("a=" + c + "*" + a + "-2*" + b);
                            podstawa = c * a - (2 * b);
                            podstawa = funkcjePrzelicznikowe.dlugoscWynik(podstawa, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(podstawa);
                            wynik.setText(x3);
                        }
                    }
                    else if (checkBoxObwod.isChecked()) {
                        Double obwod = null;
                        if ((!podstawa.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText("Ob=a+2*b");
                            drugaLinia.setText("Ob=" + a + "+2*" + b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            obwod = a + b + b;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if ((!podstawa.getText().toString().equals("")) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=(&frac12;*a)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=&frac14;*" + a + "<sup><small><small>2</small></small></sup>+" + b + "<sup><small><small>2</small></small></sup>"));
                            Double c = a * a / 4;
                            Double d = b * b;
                            trzeciaLinia.setText(Html.fromHtml("b=&#8730;(" + c + "+" + d + ")"));
                            czwartaLinia.setText("Ob=a+2*b");
                            Double e = c + d;
                            e = sqrt(e);
                            obwod = a + e + e;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod,jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if ((!ramie.getText().toString().equals("")) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("(&frac12;a)<sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>-h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(4*b<sup><small><small>2</small></small></sup>-4*h<sup><small><small>2</small></small></sup>)"));
                            Double c = 4 * a * a;
                            Double d = 4 * b * b;
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;(" + c + "-" + d + ")"));
                            Double e = c - d;
                            e = sqrt(e);
                            czwartaLinia.setText("Ob=a+2*b");
                            obwod = e + a + a;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if ((!pole.getText().toString().equals("")) && (!wpisany.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(wpisany.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*Ob*r"));
                            drugaLinia.setText("Ob=2*P/r");
                            trzeciaLinia.setText("Ob=2*" + a + "/" + b);
                            czwartaLinia.setText("");
                            obwod = 2 * a / b;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || katC.getText().toString().equals("120")) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("h=x");
                            drugaLinia.setText("b=2x");
                            trzeciaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            czwartaLinia.setText(Html.fromHtml("Ob=2*x&#8730;3+2*2x"));
                            obwod = 2 * a * sqrt(3) + (2 * 2 * a);
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!wysokosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=h"));
                            drugaLinia.setText(Html.fromHtml("b=h&#8730;2"));
                            trzeciaLinia.setText("Ob=a+2*b");
                            czwartaLinia.setText(Html.fromHtml("Ob=2*h+2*h&#8730;2"));
                            obwod = 2 * a + 2 * a * sqrt(2);
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            drugaLinia.setText("b=2x");
                            trzeciaLinia.setText(Html.fromHtml("b=a/&#8730;3"));
                            czwartaLinia.setText("Ob=a+2*b");
                            Double b = a / sqrt(3);
                            obwod = a + b + b;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("b=&frac12;a&#8730;2"));
                            Double b = a / 2 * sqrt(2);
                            drugaLinia.setText("Ob=a+2*b");
                            trzeciaLinia.setText("Ob=" + a + "+2*" + b);
                            czwartaLinia.setText("");
                            obwod = 2 * b + a;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!ramie.getText().toString().equals(""))) {
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b,jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=&frac12;b&#8730;3"));
                            Double a = b * sqrt(3);
                            drugaLinia.setText("Ob=a+2*b");
                            trzeciaLinia.setText("Ob=" + a + "+2*" + b);
                            czwartaLinia.setText("");
                            obwod = a + 2 * b;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!ramie.getText().toString().equals(""))) {
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=b/&#8730;2"));
                            Double a = b / sqrt(2);
                            a = a * 2;
                            drugaLinia.setText("Ob=a+2*b");
                            trzeciaLinia.setText("Ob=" + a + "+2*" + b);
                            czwartaLinia.setText("");
                            obwod = a + 2 * b;
                            obwod = funkcjePrzelicznikowe.dlugoscWynik(obwod, jednostkaObwod.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(obwod);
                            wynik.setText(x3);
                        }
                    }
                    else if (checkBoxWysokosc.isChecked()) {
                        Double wysokosc = null;
                        if ((!podstawa.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("b<sup><small><small>2</small></small></sup>=(&frac12;a)<sup><small><small>2</small></small></sup>+h<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("h=&#8730;(b<sup><small><small>2</small></small></sup>-&frac14;a<sup><small><small>2</small></small></sup>)"));
                            a = a * a;
                            b = b * b;
                            trzeciaLinia.setText(Html.fromHtml("h=&#8730;(" + b + "-&frac14;" + a + ")"));
                            czwartaLinia.setText("");
                            a = a / 4;
                            wysokosc = b - a;
                            wysokosc = sqrt(wysokosc);
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(x3);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))){
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(opisany.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaOpisany.getText().toString());
                            Double c = Double.parseDouble(pole.getText().toString());
                            c = funkcjePrzelicznikowe.pole(c, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b<sup><small><small>2</small></small></sup>/4R"));
                            drugaLinia.setText(Html.fromHtml("a=4*R*P/b<sup><small><small>2</small></small></sup>"));
                            Double podstawa = 4*b*c/a/a;
                            trzeciaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>-(&frac12;a)<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("h=&#8730;(b<sup><small><small>2</small></small></sup>-&frac14;a<sup><small><small>2</small></small></sup>)"));
                            wysokosc = podstawa*podstawa/4;
                            Double d = a*a;
                            wysokosc=d-wysokosc;
                            wysokosc = sqrt(wysokosc);
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(x3);
                        }
                        else if ((!podstawa.getText().toString().equals("")) && (!pole.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(pole.getText().toString());
                            b = funkcjePrzelicznikowe.pole(b, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            drugaLinia.setText("h=2*P/a");
                            trzeciaLinia.setText("h=2*" + b + "/" + a);
                            czwartaLinia.setText("");
                            wysokosc = 2 * b / a;
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(x3);
                        }
                        else if ((!ramie.getText().toString().equals("")) && (!pole.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(pole.getText().toString());
                            b = funkcjePrzelicznikowe.pole(b, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*b*h<sub><small><small>b</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("h<sub><small><small>b</small></small></sub>=2*P/b"));
                            trzeciaLinia.setText(Html.fromHtml("h<sub><small><small>b</small></small></sub>=2*" + b + "/" + a));
                            czwartaLinia.setText("");
                            wysokosc = 2 * b / a;
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&frac12;a=x&#8730;3"));
                            drugaLinia.setText(Html.fromHtml("h=x"));
                            trzeciaLinia.setText(Html.fromHtml("h=&frac12;a/&#8730;3"));
                            czwartaLinia.setText("");
                            wysokosc = a / 6;
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;3"));
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!podstawa.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=&frac12;a"));
                            drugaLinia.setText(Html.fromHtml("h=&frac12;" + a));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokosc = a / 2;
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("30")) || (katC.getText().toString().equals("120"))) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=&frac12;b"));
                            drugaLinia.setText(Html.fromHtml("h=&frac12;" + a));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokosc = a / 2;
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(x3);
                        }
                        else if (((katA.getText().toString().equals("45")) || (katC.getText().toString().equals("90"))) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=b/&#8730;2"));
                            drugaLinia.setText(Html.fromHtml("h=b&#8730;2/2"));
                            trzeciaLinia.setText(Html.fromHtml("h=" + a + "&#8730;2/2"));
                            czwartaLinia.setText("");
                            wysokosc = a / 2;
                            wysokosc = funkcjePrzelicznikowe.dlugoscWynik(wysokosc, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(wysokosc);
                            wynik.setText(Html.fromHtml(x3 + "&#8730;2"));
                        }
                    } else if (checkBoxWpisany.isChecked()) {
                        Double promien = null;
                        if ((!pole.getText().toString().equals("")) && (!obwod.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(obwod.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc( a, jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(pole.getText().toString());
                            b = funkcjePrzelicznikowe.pole(b, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText("r=2*P/Ob");
                            trzeciaLinia.setText("r=2*" + b + "/" + a);
                            czwartaLinia.setText("");
                            promien = 2 * b / a;
                            promien  = funkcjePrzelicznikowe.dlugoscWynik(promien, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(promien);
                            wynik.setText(x3);
                        } else if ((!pole.getText().toString().equals("")) && (!podstawa.getText().toString().equals("")) && (!ramie.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(ramie.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText("r=2*P/(a+2*b)");
                            trzeciaLinia.setText("r=2*" + a + "/(" + b + "+2*" + c + ")");
                            czwartaLinia.setText("");
                            Double obwod = b + (2 * c);
                            promien = 2 * a / obwod;
                            promien = funkcjePrzelicznikowe.dlugoscWynik(promien, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(promien);
                            wynik.setText(x3);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>-&frac14*a<sup><small><small>2</small></small></sup>"));
                            Double c = b * b;
                            c = c - (a * a / 4);
                            c = sqrt(c);
                            drugaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            Double pole = a * c / 2;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            czwartaLinia.setText("r=2*P/Ob");
                            Double obwod = a+2*b;
                            promien = 2*pole/obwod;
                            promien = funkcjePrzelicznikowe.dlugoscWynik(promien, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(promien);
                            wynik.setText(x3);
                        }
                    }
                    else if(checkBoxOpisany.isChecked()){
                        Double promienOpis = null;
                        if((!pole.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaRamie.getText().toString());
                            Double c = Double.parseDouble(podstawa.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b<sup><small><small>2</small></small></sup>/4R"));
                            drugaLinia.setText(Html.fromHtml("R=a*b<sup><small><small>2</small></small></sup>/4P"));
                            trzeciaLinia.setText(Html.fromHtml("R="+c+"*"+b+"<sup><small><small>2</small></small></sup>/(4*"+a+")"));
                            czwartaLinia.setText("");
                            promienOpis = c*b*b/4/a;
                            promienOpis = funkcjePrzelicznikowe.dlugoscWynik(promienOpis, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(promienOpis);
                            wynik.setText(x3);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a= funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h<sup><small><small>2</small></small></sup>=b<sup><small><small>2</small></small></sup>-&frac14*a<sup><small><small>2</small></small></sup>"));
                            Double c = b * b;
                            c = c - (a * a / 4);
                            c = sqrt(c);
                            drugaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            Double pole = a * c / 2;
                            trzeciaLinia.setText(Html.fromHtml("P=a*b<sup><small><small>2</small></small></sup>/4R"));
                            czwartaLinia.setText(Html.fromHtml("R=a*b<sup><small><small>2</small></small></sup>/4P"));
                            promienOpis = a*b*b/4/pole;
                            promienOpis = funkcjePrzelicznikowe.dlugoscWynik(promienOpis, jednostkaWynik.getText().toString());
                            String x3 = funkcjePrzelicznikowe.intowanie(promienOpis);
                            wynik.setText(x3);
                        }
                    }
                    else if(checkBoxKatA.isChecked()){
                        Double stopnie = null;
                        if((!podstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;a/b"));
                            a=a/2;
                            drugaLinia.setText(Html.fromHtml("cos&#945;="+a+"/"+b));
                            a=a/b;
                            trzeciaLinia.setText(Html.fromHtml("cos&#945;="+a));
                            czwartaLinia.setText("");
                            stopnie = Math.acos(a);
                            stopnie = Math.toDegrees(stopnie);
                            String x3 = funkcjePrzelicznikowe.intowanie(stopnie);
                            wynik.setText(x3);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))){
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a,jednostkaWysokosc.getText().toString());
                            Double b = Double.parseDouble(podstawa.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaPodstawa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#945;=h/&frac12;a"));
                            b=b/2;
                            drugaLinia.setText(Html.fromHtml("tg&#945;="+a+"/"+b));
                            a=a/b;
                            trzeciaLinia.setText(Html.fromHtml("tg&#945;="+a));
                            czwartaLinia.setText("");
                            stopnie = Math.atan(a);
                            stopnie = Math.toDegrees(stopnie);
                            String x3 = funkcjePrzelicznikowe.intowanie(stopnie);
                            wynik.setText(x3);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("sin&#945;=h/b"));
                            drugaLinia.setText(Html.fromHtml("sin&#945;="+a+"/"+b));
                            a=a/b;
                            trzeciaLinia.setText(Html.fromHtml("sin&#945;="+a));
                            czwartaLinia.setText("");
                            stopnie = Math.asin(a);
                            stopnie = Math.toDegrees(stopnie);
                            String x3 = funkcjePrzelicznikowe.intowanie(stopnie);
                            wynik.setText(x3);
                        }
                    }
                    else if(checkBoxKatC.isChecked()){
                        Double stopnieC = null;
                        if((!podstawa.getText().toString().equals(""))&&(!ramie.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramie.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamie.getText().toString());
                            a=a/2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#947;=&frac12;a/b"));
                            drugaLinia.setText(Html.fromHtml("sin&#947;="+a+"/"+b));
                            a=a/b;
                            trzeciaLinia.setText(Html.fromHtml("sin&#947;="+a));
                            czwartaLinia.setText("");
                            stopnieC = Math.asin(a);
                            stopnieC = Math.toDegrees(stopnieC);
                            stopnieC = stopnieC*2;
                            String x3 = funkcjePrzelicznikowe.intowanie(stopnieC);
                            wynik.setText(x3);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tg&#947;=&frac12;a/h"));
                            a=a/2;
                            drugaLinia.setText(Html.fromHtml("tg&#947;="+a+"/"+b));
                            a=a/b;
                            trzeciaLinia.setText(Html.fromHtml("tg&#947;="+a));
                            czwartaLinia.setText("");
                            stopnieC = Math.atan(a);
                            stopnieC = Math.toDegrees(stopnieC);
                            stopnieC = stopnieC*2;
                            String x3 = funkcjePrzelicznikowe.intowanie(stopnieC);
                            wynik.setText(x3);
                        }
                        else if((!ramie.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double a = Double.parseDouble(ramie.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaRamie.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("cos&#947;=h/b"));
                            drugaLinia.setText(Html.fromHtml("cos&#947;="+b+"/"+a));
                            a=b/a;
                            trzeciaLinia.setText(Html.fromHtml("cos&#947;="+a));
                            czwartaLinia.setText("");
                            stopnieC = Math.acos(a);
                            stopnieC = Math.toDegrees(stopnieC);
                            stopnieC = stopnieC*2;
                            String x3 = funkcjePrzelicznikowe.intowanie(stopnieC);
                            wynik.setText(x3);
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        checkBoxKatA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxKatA.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("<sup>o</sup>"));
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxKatC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatA.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxKatC.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("<sup>o</sup>"));
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxObwod.isChecked()){
                    jednostkaWynik.setText(obwodJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        obwodJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxOpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxOpisany.isChecked()){
                    jednostkaWynik.setText(opisanyJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        opisanyJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxPodstawa.isChecked()){
                    jednostkaWynik.setText(podstawaJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        podstawaJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxPole.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml(ramieJ+"<sup><small><small>2</small></small></sup"));
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        poleJ=jednostkaWynik.getText().toString();
                        if(poleJ.charAt(poleJ.length()-1)=='2'){
                            poleJ=poleJ.substring(0,poleJ.length()-1);
                        }
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxWpisany.isChecked()){
                    jednostkaWynik.setText(wpisanyJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wpisanyJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxRamie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxRamie.isChecked()){
                    jednostkaWynik.setText(ramieJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        ramieJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamie.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxOpisany.setChecked(false);
                if(checkBoxWysokosc.isChecked()){
                    jednostkaWynik.setText(wysokoscJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wysokoscJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        mdrawerLayout = findViewById(R.id.drawerRownoramienny_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Pole_trojkata_rownoramiennego.this, Konto.class);
                i.putExtra("miejsce", "Pole_trojkata_rownoramiennego");
                startActivity(i);
                Animatoo.animateFade(Pole_trojkata_rownoramiennego.this);
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
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRownoramienny);
            TextView drugaLinia = findViewById(R.id.drugaLiniaRownoramienny);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRownoramienny);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaRownoramienny);
            TextView wynik = findViewById(R.id.wynikRownoramienny);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRownoramienny);
            TextView drugaLinia = findViewById(R.id.drugaLiniaRownoramienny);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRownoramienny);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaRownoramienny);
            TextView wynik = findViewById(R.id.wynikRownoramienny);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik);
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
            if(jednostkaWynikStr.equals("mm2")){
                jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
            }
            else if(jednostkaWynikStr.equals("cm2")){
                jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
            }
            else if(jednostkaWynikStr.equals("dm2")){
                jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
            }
            else if(jednostkaWynikStr.equals("m2")){
                jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
            }
            else{
                jednostkaWynik.setText(jednostkaWynikStr);
            }
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }
    public void wyczyscLinie(){
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRownoramienny);
        TextView drugaLinia = findViewById(R.id.drugaLiniaRownoramienny);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRownoramienny);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaRownoramienny);
        TextView wynik = findViewById(R.id.wynikRownoramienny);
        pierwszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
    }
    String ktory="";
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        CheckBox checkBoxPodstawa = findViewById(R.id.podstawaWybor);
        CheckBox checkBoxRamie = findViewById(R.id.ramieWybor);
        CheckBox checkBoxObwod = findViewById(R.id.obwodWybor);
        CheckBox checkBoxPole = findViewById(R.id.poleWybor);
        CheckBox checkBoxWysokosc = findViewById(R.id.wysokoscWybor);
        CheckBox checkBoxWpisany = findViewById(R.id.wpisanyWybor);
        CheckBox checkBoxOpisany = findViewById(R.id.opisanyWybor);
        CheckBox checkBoxKatA = findViewById(R.id.katAWybor);
        CheckBox checkBoxKatC = findViewById(R.id.katCWybor);
        if(v.getId()==R.id.jednostkaWysokosc){
            ktory="wysokosc";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPodstawa){
            ktory="podstawa";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaRamie){
            ktory="ramie";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaObwod){
            ktory="obwod";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPole){
            ktory="pole";
            getMenuInflater().inflate(R.menu.pole_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaOpisany){
            ktory="opisany";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaWpisany){
            ktory="wpisany";
            getMenuInflater().inflate(R.menu.dlugosc2_menu,menu);
        }
        else if(v.getId()==R.id.jednostkaWynik){
            if(checkBoxWysokosc.isChecked()||checkBoxObwod.isChecked()||checkBoxOpisany.isChecked()||checkBoxPodstawa.isChecked()||checkBoxRamie.isChecked()||checkBoxWpisany.isChecked()){
                getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            }
            else if(checkBoxPole.isChecked()){
                getMenuInflater().inflate(R.menu.pole_menu, menu);
            }
            ktory="wynik";
        }
    }
    String podstawaJ="cm", ramieJ="cm", obwodJ="cm", poleJ="cm", wysokoscJ="cm", wpisanyJ="cm", opisanyJ="cm";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        CheckBox checkBoxPodstawa = findViewById(R.id.podstawaWybor);
        CheckBox checkBoxRamie = findViewById(R.id.ramieWybor);
        CheckBox checkBoxObwod = findViewById(R.id.obwodWybor);
        CheckBox checkBoxPole = findViewById(R.id.poleWybor);
        CheckBox checkBoxWysokosc = findViewById(R.id.wysokoscWybor);
        CheckBox checkBoxWpisany = findViewById(R.id.wpisanyWybor);
        CheckBox checkBoxOpisany = findViewById(R.id.opisanyWybor);
        CheckBox checkBoxKatA = findViewById(R.id.katAWybor);
        CheckBox checkBoxKatC = findViewById(R.id.katCWybor);
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("podstawa")){
                    jednostkaPodstawa.setText("cm");
                }
                else if(ktory.equals("ramie")){
                    jednostkaRamie.setText("cm");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("cm");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("cm");
                }
                else if(ktory.equals("wpisany")){
                    jednostkaWpisany.setText("cm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("cm");
                }
                else if(ktory.equals("wynik")){
                    if(checkBoxPodstawa.isChecked()){
                        podstawaJ="cm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="cm";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyJ="cm";
                    }
                    else if(checkBoxRamie.isChecked()){
                        ramieJ="cm";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyJ="cm";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscJ="cm";
                    }
                    jednostkaWynik.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("podstawa")){
                    jednostkaPodstawa.setText("dm");
                }
                else if(ktory.equals("ramie")){
                    jednostkaRamie.setText("dm");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("dm");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("dm");
                }
                else if(ktory.equals("wpisany")){
                    jednostkaWpisany.setText("dm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("dm");
                }
                else if(ktory.equals("wynik")){
                    if(checkBoxPodstawa.isChecked()){
                        podstawaJ="dm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="dm";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyJ="dm";
                    }
                    else if(checkBoxRamie.isChecked()){
                        ramieJ="dm";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyJ="dm";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscJ="dm";
                    }
                    jednostkaWynik.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("podstawa")){
                    jednostkaPodstawa.setText("m");
                }
                else if(ktory.equals("ramie")){
                    jednostkaRamie.setText("m");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("m");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("m");
                }
                else if(ktory.equals("wpisany")){
                    jednostkaWpisany.setText("m");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("m");
                }
                else if(ktory.equals("wynik")){
                    if(checkBoxPodstawa.isChecked()){
                        podstawaJ="m";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="m";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyJ="m";
                    }
                    else if(checkBoxRamie.isChecked()){
                        ramieJ="m";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyJ="m";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscJ="m";
                    }
                    jednostkaWynik.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("podstawa")){
                    jednostkaPodstawa.setText("km");
                }
                else if(ktory.equals("ramie")){
                    jednostkaRamie.setText("km");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("km");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("km");
                }
                else if(ktory.equals("wpisany")){
                    jednostkaWpisany.setText("km");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("km");
                }
                else if(ktory.equals("wynik")){
                    if(checkBoxPodstawa.isChecked()){
                        podstawaJ="km";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="km";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyJ="km";
                    }
                    else if(checkBoxRamie.isChecked()){
                        ramieJ="km";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyJ="km";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscJ="km";
                    }
                    jednostkaWynik.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("wynik")){
                    poleJ="mm";
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                else {
                    jednostkaPole.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("wynik")){
                    poleJ="cm";
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                else {
                    jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("wynik")){
                    poleJ="dm";
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                else {
                    jednostkaPole.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("wynik")){
                    poleJ="m";
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                else {
                    jednostkaPole.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("wynik")){
                    poleJ="a";
                    jednostkaWynik.setText("a");
                }
                else {
                    jednostkaPole.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("wynik")){
                    poleJ="ha";
                    jednostkaWynik.setText("ha");
                }
                else {
                    jednostkaPole.setText("ha");
                }
                Toast.makeText(this, "Hektar", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
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
        adapter = new CustomExpandableListAdapter(this, lstTitle, lstChild);
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
                    Intent i = new Intent(Pole_trojkata_rownoramiennego.this, Szkola.class);
                    i.putExtra("miejsce", "Pole_trojkata_rownoramiennego");
                    startActivity(i);
                    Animatoo.animateFade(Pole_trojkata_rownoramiennego.this);
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
            Intent i = new Intent(Pole_trojkata_rownoramiennego.this, Trojkaty.class);
            startActivity(i);
            Animatoo.animateFade(Pole_trojkata_rownoramiennego.this);
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