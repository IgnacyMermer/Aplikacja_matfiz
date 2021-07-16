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

import static java.lang.StrictMath.sqrt;

public class Pole_trojkata_roznobocznego extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;
    TextView jednostkaRamieB;
    TextView jednostkaRamieC;
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
        setContentView(R.layout.pole_trojkata_roznobocznego2);
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
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        /**/
        final EditText podstawa = findViewById(R.id.podstawaRoznoboczny);
        final EditText ramieB = findViewById(R.id.ramieB);
        final EditText ramieC = findViewById(R.id.ramieC);
        final EditText obwod = findViewById(R.id.obwodRoznoboczny);
        final EditText pole = findViewById(R.id.poleRoznoboczny);
        final EditText wysokosc = findViewById(R.id.wysokoscRoznoboczny);
        final EditText wpisany = findViewById(R.id.wpisanyRoznoboczny);
        final EditText opisany = findViewById(R.id.opisanyRoznoboczny);
        final EditText katA = findViewById(R.id.katARoznoboczny);
        final EditText katB = findViewById(R.id.katBRoznoboczny);
        final EditText katC = findViewById(R.id.katCRoznoboczny);
        jednostkaPodstawa = findViewById(R.id.jednostkaPodstawaRoznoboczny);
        jednostkaPodstawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPodstawa);
                openContextMenu(v);
            }
        });
        jednostkaRamieB = findViewById(R.id.jednostkaRamieB);
        jednostkaRamieB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaRamieB);
                openContextMenu(v);
            }
        });
        jednostkaRamieC = findViewById(R.id.jednostkaRamieC);
        jednostkaRamieC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaRamieC);
                openContextMenu(v);
            }
        });
        jednostkaObwod = findViewById(R.id.jednostkaObwodRoznoboczny);
        jednostkaObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaObwod);
                openContextMenu(v);
            }
        });
        jednostkaPole = findViewById(R.id.jednostkaPoleRoznoboczny);
        jednostkaPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPole);
                openContextMenu(v);
            }
        });
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        jednostkaWysokosc = findViewById(R.id.jednostkaWysokoscRoznoboczny);
        jednostkaWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokosc);
                openContextMenu(v);
            }
        });
        jednostkaOpisany = findViewById(R.id.jednostkaOpisanyRoznoboczny);
        jednostkaOpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaOpisany);
                openContextMenu(v);
            }
        });
        jednostkaWpisany = findViewById(R.id.jednostkaWpisanyRoznoboczny);
        jednostkaWpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWpisany);
                openContextMenu(v);
            }
        });
        jednostkaWynik = findViewById(R.id.jednostkaWynikRoznoboczny);
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        final CheckBox checkBoxPodstawa = findViewById(R.id.podstawaWyborRoznoboczny);
        final CheckBox checkBoxRamieB = findViewById(R.id.ramieBWyborRoznoboczny);
        final CheckBox checkBoxRamieC = findViewById(R.id.ramieCWyborRoznoboczny);
        final CheckBox checkBoxObwod = findViewById(R.id.obwodWyborRoznoboczny);
        final CheckBox checkBoxPole = findViewById(R.id.poleWyborRoznoboczny);
        final CheckBox checkBoxWysokosc = findViewById(R.id.wysokoscWyborRoznoboczny);
        final CheckBox checkBoxWpisany = findViewById(R.id.wpisanyWyborRoznoboczny);
        final CheckBox checkBoxOpisany = findViewById(R.id.opisanyWyborRoznoboczny);
        final CheckBox checkBoxKatA = findViewById(R.id.katAWyborRoznoboczny);
        final CheckBox checkBoxKatB = findViewById(R.id.katBWyborRoznoboczny);
        final CheckBox checkBoxKatC = findViewById(R.id.katCWyborRoznoboczny);
        final TextView wynik = findViewById(R.id.wynikRoznoboczny);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRoznoboczny);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaRoznoboczny);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRoznoboczny);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaRoznoboczny);
        Intent incomingIntent = getIntent();
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        final String nick = incomingIntent.getStringExtra("nick");
        String checkbox = "";
        ArrayList<String> incomingList = new ArrayList<String>();
        try {
            checkbox = incomingIntent.getStringExtra("checkbox");
            incomingList = incomingIntent.getStringArrayListExtra("lista");
        }
        catch (Exception ex){
            Log.i("wynik", ex.getMessage().toString());
        }
        if(checkbox==null){
            checkbox="";
        }
        if(checkbox.equals("pole")){
            checkBoxPole.setChecked(true);
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
        else if(checkbox.equals("ramieB")){
            checkBoxRamieB.setChecked(true);
        }
        else if(checkbox.equals("ramieC")){
            checkBoxRamieC.setChecked(true);
        }
        else if(checkbox.equals("podstawa")){
            checkBoxPodstawa.setChecked(true);
        }
        else if(checkbox.equals("obwod")){
            checkBoxObwod.setChecked(true);
        }
        else if(checkbox.equals("wpisany")){
            checkBoxWpisany.setChecked(true);
        }
        else if(checkbox.equals("opisany")){
            checkBoxOpisany.setChecked(true);
        }
        else if(checkbox.equals("wysokosc")){
            checkBoxWysokosc.setChecked(true);
        }
        if(incomingList!=null){
            podstawa.setText(incomingList.get(0));
            jednostkaPodstawa.setText(incomingList.get(1));
            ramieB.setText(incomingList.get(2));
            jednostkaRamieB.setText(incomingList.get(3));
            ramieC.setText(incomingList.get(4));
            jednostkaRamieC.setText(incomingList.get(5));
            obwod.setText(incomingList.get(6));
            jednostkaObwod.setText(incomingList.get(7));
            pole.setText(incomingList.get(8));
            jednostkaPole.setText(incomingList.get(9));
            wysokosc.setText(incomingList.get(10));
            jednostkaWysokosc.setText(incomingList.get(11));
            wpisany.setText(incomingList.get(12));
            jednostkaWpisany.setText(incomingList.get(13));
            opisany.setText(incomingList.get(14));
            jednostkaOpisany.setText(incomingList.get(15));
            katA.setText(incomingList.get(16));
            katB.setText(incomingList.get(17));
            katC.setText(incomingList.get(18));
            pierwszaLinia.setText(incomingList.get(19));
            drugaLinia.setText(incomingList.get(20));
            trzeciaLinia.setText(incomingList.get(21));
            czwartaLinia.setText(incomingList.get(22));
            wynik.setText(incomingList.get(23));
            jednostkaWynik.setText(incomingList.get(24));
        }
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        doWyslania = new ArrayList<String>();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(podstawa.getText().toString());
                doWyslania.add(jednostkaPodstawa.getText().toString());
                doWyslania.add(ramieB.getText().toString());
                doWyslania.add(jednostkaRamieB.getText().toString());
                doWyslania.add(ramieC.getText().toString());
                doWyslania.add(jednostkaRamieC.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(jednostkaObwod.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(jednostkaPole.getText().toString());
                doWyslania.add(wysokosc.getText().toString());
                doWyslania.add(jednostkaWysokosc.getText().toString());
                doWyslania.add(wpisany.getText().toString());
                doWyslania.add(jednostkaWpisany.getText().toString());
                doWyslania.add(opisany.getText().toString());
                doWyslania.add(jednostkaOpisany.getText().toString());
                doWyslania.add(katA.getText().toString());
                doWyslania.add(katB.getText().toString());
                doWyslania.add(katC.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxKatA.isChecked()){
                    ktoryCheckbox="katA";
                }
                else if(checkBoxKatB.isChecked()){
                    ktoryCheckbox="katB";
                }
                else if(checkBoxKatC.isChecked()){
                    ktoryCheckbox="katC";
                }
                else if(checkBoxObwod.isChecked()){
                    ktoryCheckbox="obwod";
                }
                else if(checkBoxOpisany.isChecked()){
                    ktoryCheckbox="opisany";
                }
                else if(checkBoxPodstawa.isChecked()){
                    ktoryCheckbox="podstawa";
                }
                else if(checkBoxPole.isChecked()){
                    ktoryCheckbox="pole";
                }
                else if(checkBoxRamieB.isChecked()){
                    ktoryCheckbox="ramieB";
                }
                else if(checkBoxRamieC.isChecked()){
                    ktoryCheckbox = "ramieC";
                }
                else if(checkBoxWpisany.isChecked()){
                    ktoryCheckbox="wpisany";
                }
                else if(checkBoxWysokosc.isChecked()){
                    ktoryCheckbox="wysokosc";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(Pole_trojkata_roznobocznego.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Pole_trojkata_roznobocznego");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Pole_trojkata_roznobocznego.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Pole_trojkata_roznobocznego.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Pole_trojkata_roznobocznego");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Pole_trojkata_roznobocznego.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Pole_trojkata_roznobocznego");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Pole_trojkata_roznobocznego.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Pole_trojkata_roznobocznego");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox", ktoryCheckbox);
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return true;
            }
        });
        Button buttonOblicz = findViewById(R.id.buttonRoznoboczny);
        Button buttonCzysc = findViewById(R.id.buttonCzysc);
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxPole.isChecked()){
                        Double poleD=null;
                        if((!wysokosc.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(wysokosc.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;*a*h"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;"+a+"*"+b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD=a*b/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a=funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText("P=a*b*c/(4*R)");
                            drugaLinia.setText("P="+a+"*"+b+"*"+c+"/(4*"+R+")");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = a*b*c/4/R;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;(a+b+c)*r"));
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12("+a+"+"+b+"+"+c+")*"+r));
                            czwartaLinia.setText("");
                            a = a+b+c;
                            poleD=a/2*r;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(obwod.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaObwod.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;"+a+"*"+r));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = a/2*r;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&frac12;Ob"));
                            drugaLinia.setText(Html.fromHtml("P=&#8730;(p*(p-a)*(p-b)*(p-c))"));
                            Double p = a+b+c;
                            p=p/2;
                            Double a2 = p-a;
                            Double b2 = p-b;
                            Double c2 = p-c;
                            trzeciaLinia.setText(Html.fromHtml("P=&#8730;("+p+"*"+a2+"*"+b2+"*"+c2+")"));
                            Double wynik11 = p*a2*b2*c2;
                            String x5 = funkcjePrzelicznikowe.intowanie(wynik11);
                            czwartaLinia.setText(Html.fromHtml("P=&#8730;"+x5));
                            poleD = sqrt(wynik11);
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxRamieB.isChecked()){
                        Double b = null;
                        if((!obwod.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c");
                            drugaLinia.setText("b=Ob-(a+c)");
                            trzeciaLinia.setText("b="+ob+"-("+a+"+"+c+")");
                            czwartaLinia.setText("");
                            b = ob-a-c;
                            b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(b);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;(a+b+c)*r"));
                            trzeciaLinia.setText("b=2P/r-(a+c)");
                            czwartaLinia.setText("b=2*"+poleD+"/"+r+"-("+a+"+"+c+")");
                            b = 2*poleD/r;
                            b = b - a - c;
                            b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(b);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=abc/4R"));
                            drugaLinia.setText(Html.fromHtml("b=4*P*R/(a*c)"));
                            trzeciaLinia.setText("b=4*"+poleD+"*"+R+"/("+a+"*"+c+")");
                            czwartaLinia.setText("");
                            b = 4*poleD*R;
                            b = b/a/c;
                            b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(b);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxRamieC.isChecked()){
                        Double c = null;
                        if((!obwod.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c");
                            drugaLinia.setText("c=Ob-(a+b)");
                            trzeciaLinia.setText("c="+ob+"-("+a+"+"+b+")");
                            czwartaLinia.setText("");
                            c = ob-a-b;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieC.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;(a+b+c)*r"));
                            trzeciaLinia.setText("c=2P/r-(a+b)");
                            czwartaLinia.setText("c=2*"+poleD+"/"+r+"-("+a+"+"+b+")");
                            c = 2*poleD/r;
                            c = c - a - b;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=abc/4R"));
                            drugaLinia.setText(Html.fromHtml("c=4*P*R/(a*b)"));
                            trzeciaLinia.setText("c=4*"+poleD+"*"+R+"/("+a+"*"+b+")");
                            czwartaLinia.setText("");
                            c = 4*poleD*R;
                            c = c/a/b;
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxPodstawa.isChecked()){
                        Double a = null;
                        if((!obwod.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double c = Double.parseDouble(podstawa.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c");
                            drugaLinia.setText("a=Ob-(b+c)");
                            trzeciaLinia.setText("a="+ob+"-("+b+"+"+c+")");
                            czwartaLinia.setText("");
                            a = ob-c-b;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieC.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("P=&frac12;(a+b+c)*r"));
                            trzeciaLinia.setText("a=2P/r-(b+c)");
                            czwartaLinia.setText("a=(2*"+poleD+"/"+r+")-("+b+"+"+c+")");
                            a = 2*poleD/r;
                            a = a - c - b;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=abc/4R"));
                            drugaLinia.setText(Html.fromHtml("a=4*P*R/(b*c)"));
                            trzeciaLinia.setText("a=4*"+poleD+"*"+R+"/("+b+"*"+c+")");
                            czwartaLinia.setText("");
                            a = 4*poleD*R;
                            a = a/c/b;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))){
                            Double h = Double.parseDouble(wysokosc.getText().toString());
                            h = funkcjePrzelicznikowe.dlugosc(h , jednostkaWysokosc.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;a*h"));
                            drugaLinia.setText(Html.fromHtml("a=2*P/h"));
                            trzeciaLinia.setText(Html.fromHtml("a=2*"+poleD+"/"+h));
                            czwartaLinia.setText("");
                            a = 2*poleD/h;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxObwod.isChecked()){
                        Double ob=null;
                        if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            pierwszaLinia.setText("Ob=a+b+c");
                            drugaLinia.setText("Ob="+a+"+"+b+"+"+c);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            ob=a+b+c;
                            ob = funkcjePrzelicznikowe.dlugoscWynik(ob,jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ob);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText("Ob=2P/r");
                            trzeciaLinia.setText("Ob=2*"+a+"/"+r);
                            czwartaLinia.setText("");
                            ob=2*a/r;
                            ob = funkcjePrzelicznikowe.dlugoscWynik(ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(ob);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        Double h = null;
                        if((!pole.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;a*h"));
                            drugaLinia.setText("h=2P/a");
                            trzeciaLinia.setText("h=2*"+poleD+"/"+a);
                            czwartaLinia.setText("");
                            h=2*poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!ramieB.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double kata = Double.parseDouble(katA.getText().toString());
                            kata = Math.toRadians(kata);
                            Double sinus = Math.sin(kata);
                            pierwszaLinia.setText("sinA=h/b");
                            drugaLinia.setText("h=sinA*b");
                            trzeciaLinia.setText("h="+sinus+"*"+b);
                            czwartaLinia.setText("");
                            h=sinus*b;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!ramieC.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double katb = Double.parseDouble(katB.getText().toString());
                            katb = Math.toRadians(katb);
                            Double sinus = Math.sin(katb);
                            pierwszaLinia.setText("sinB=h/c");
                            drugaLinia.setText("h=sinB*c");
                            trzeciaLinia.setText("h="+sinus+"*"+c);
                            czwartaLinia.setText("");
                            h=sinus*c;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&frac12;Ob"));
                            drugaLinia.setText(Html.fromHtml("P=&#8730;(p*(p-a)*(p-b)*(p-c))"));
                            Double p = a+b+c;
                            p=p/2;
                            Double a2 = p-a;
                            Double b2 = p-b;
                            Double c2 = p-c;
                            Double poleD;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;a*h"));
                            Double wynik11 = p*a2*b2*c2;
                            czwartaLinia.setText(Html.fromHtml("h=2P/a"));
                            poleD = sqrt(wynik11);
                            h = 2*poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxWpisany.isChecked()){
                        Double r = null;
                        if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&frac12;Ob"));
                            drugaLinia.setText(Html.fromHtml("P=&#8730;(p*(p-a)*(p-b)*(p-c))"));
                            Double p = a+b+c;
                            Double ob = p;
                            p=p/2;
                            Double a2 = p-a;
                            Double b2 = p-b;
                            Double c2 = p-c;
                            Double poleD;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            Double wynik11 = p*a2*b2*c2;
                            czwartaLinia.setText(Html.fromHtml("r=2P/Ob"));
                            poleD = sqrt(wynik11);
                            r = 2*poleD/ob;
                            r = funkcjePrzelicznikowe.dlugoscWynik(r, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(r);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b,jednostkaRamieB.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText("P=abc/4R");
                            drugaLinia.setText("c=4*P*R/(a*b)");
                            Double c = 4*poleD*R/a/b;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            Double ob = a+b+c;
                            czwartaLinia.setText("r=2P/Ob");
                            r=2*poleD/ob;
                            r = funkcjePrzelicznikowe.dlugoscWynik(r, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(r);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c,jednostkaRamieC.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText("P=abc/4R");
                            drugaLinia.setText("b=4*P*R/(a*c)");
                            Double b = 4*poleD*R/a/c;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            Double ob = a+b+c;
                            czwartaLinia.setText("r=2P/Ob");
                            r=2*poleD/ob;
                            r = funkcjePrzelicznikowe.dlugoscWynik(r, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(r);
                            wynik.setText(x);
                        }
                        else if((!ramieC.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!opisany.getText().toString().equals(""))){
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b,jednostkaRamieB.getText().toString());
                            Double R = Double.parseDouble(opisany.getText().toString());
                            R = funkcjePrzelicznikowe.dlugosc(R, jednostkaOpisany.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText("P=abc/4R");
                            drugaLinia.setText("a=4*P*R/(b*c)");
                            Double a = 4*poleD*R/c/b;
                            trzeciaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            Double ob = a+b+c;
                            czwartaLinia.setText("r=2P/Ob");
                            r=2*poleD/ob;
                            r = funkcjePrzelicznikowe.dlugoscWynik(r, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(r);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxOpisany.isChecked()){
                        Double R = null;
                        if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b , jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&frac12;Ob"));
                            drugaLinia.setText(Html.fromHtml("P=&#8730;(p*(p-a)*(p-b)*(p-c))"));
                            Double p = a+b+c;
                            p=p/2;
                            Double a2 = p-a;
                            Double b2 = p-b;
                            Double c2 = p-c;
                            Double poleD;
                            trzeciaLinia.setText(Html.fromHtml("P=abc/4R"));
                            Double wynik11 = p*a2*b2*c2;
                            czwartaLinia.setText(Html.fromHtml("R=abc/4P"));
                            poleD = sqrt(wynik11);
                            R = a*b*c/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else if((!ramieB.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText("c=(2P/r)-(a+b)");
                            trzeciaLinia.setText("P=abc/4R");
                            czwartaLinia.setText("R=abc/4P");
                            Double c = 2*poleD/r -b-a;
                            R = a*b*c/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else if((!ramieC.getText().toString().equals(""))&&(!podstawa.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieB.getText().toString());
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText("b=(2P/r)-(a+c)");
                            trzeciaLinia.setText("P=abc/4R");
                            czwartaLinia.setText("R=abc/4P");
                            Double b = 2*poleD/r -c-a;
                            R = a*c*b/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else if((!ramieB.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText("a=(2P/r)-(b+c)");
                            trzeciaLinia.setText("P=abc/4R");
                            czwartaLinia.setText("R=abc/4P");
                            Double a = 2*poleD/r -b-c;
                            R = c*b*a/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("c=(2*P/r)-(a+b)"));
                            Double c = 2*poleD/r;
                            c=c-a-b;
                            trzeciaLinia.setText("P=abc/4R");
                            czwartaLinia.setText("R=abc/4P");
                            R=a*b*c/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else if((!podstawa.getText().toString().equals(""))&&(!ramieC.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double a = Double.parseDouble(podstawa.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPodstawa.getText().toString());
                            Double c = Double.parseDouble(ramieC.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("b=(2*P/r)-(a+c)"));
                            Double b = 2*poleD/r;
                            b=b-a-c;
                            trzeciaLinia.setText("P=abc/4R");
                            czwartaLinia.setText("R=abc/4P");
                            R=a*b*c/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else if((!ramieC.getText().toString().equals(""))&&(!ramieB.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))&&(!wpisany.getText().toString().equals(""))){
                            Double c = Double.parseDouble(podstawa.getText().toString());
                            c = funkcjePrzelicznikowe.dlugosc(c, jednostkaRamieC.getText().toString());
                            Double b = Double.parseDouble(ramieB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaRamieB.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double r = Double.parseDouble(wpisany.getText().toString());
                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=&frac12;Ob*r"));
                            drugaLinia.setText(Html.fromHtml("a=(2*P/r)-(b+c)"));
                            Double a = 2*poleD/r;
                            a=a-c-b;
                            trzeciaLinia.setText("P=abc/4R");
                            czwartaLinia.setText("R=abc/4P");
                            R=a*b*c/4/poleD;
                            R = funkcjePrzelicznikowe.dlugoscWynik(R, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(R);
                            wynik.setText(x);
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
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
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
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
        checkBoxKatB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatA.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxKatB.isChecked()){
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
                checkBoxKatB.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
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
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
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
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
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
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
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
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxPole.isChecked()){
                    if((!poleJ.equals("a"))&&(!poleJ.equals("ha"))){
                        jednostkaWynik.setText(Html.fromHtml(poleJ+"<sup><small><small>2</small></small></sup"));
                    }
                    else{
                        jednostkaWynik.setText(poleJ);
                    }
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
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
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
        checkBoxRamieB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxRamieC.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxRamieB.isChecked()){
                    jednostkaWynik.setText(ramieJB);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        ramieJB=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxRamieC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatB.setChecked(false);
                wyczyscLinie();
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxRamieC.isChecked()){
                    jednostkaWynik.setText(ramieJC);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        ramieJC=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
            }
        });
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxKatB.setChecked(false);
                checkBoxKatC.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPodstawa.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxRamieB.setChecked(false);
                checkBoxRamieC.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxOpisany.setChecked(false);
                wyczyscLinie();
                if(checkBoxWysokosc.isChecked()){
                    jednostkaWynik.setText(wysokoscJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wysokoscJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
            }
        });
        mdrawerLayout = findViewById(R.id.drawerRoznoboczny_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Pole_trojkata_roznobocznego.this, Konto.class);
                i.putExtra("miejsce", "Pole_trojkata_roznobocznego");
                startActivity(i);
                Animatoo.animateFade(Pole_trojkata_roznobocznego.this);
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
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRoznoboczny);
        TextView drugaLinia = findViewById(R.id.drugaLiniaRoznoboczny);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRoznoboczny);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaRoznoboczny);
        TextView wynik = findViewById(R.id.wynikRoznoboczny);
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
        CheckBox checkBoxPodstawa = findViewById(R.id.podstawaWyborRoznoboczny);
        CheckBox checkBoxRamieB = findViewById(R.id.ramieBWyborRoznoboczny);
        CheckBox checkBoxRamieC = findViewById(R.id.ramieCWyborRoznoboczny);
        CheckBox checkBoxObwod = findViewById(R.id.obwodWyborRoznoboczny);
        CheckBox checkBoxPole = findViewById(R.id.poleWyborRoznoboczny);
        CheckBox checkBoxWysokosc = findViewById(R.id.wysokoscWyborRoznoboczny);
        CheckBox checkBoxWpisany = findViewById(R.id.wpisanyWyborRoznoboczny);
        CheckBox checkBoxOpisany = findViewById(R.id.opisanyWyborRoznoboczny);
        CheckBox checkBoxKatA = findViewById(R.id.katAWyborRoznoboczny);
        CheckBox checkBoxKatB = findViewById(R.id.katBWyborRoznoboczny);
        CheckBox checkBoxKatC = findViewById(R.id.katCWyborRoznoboczny);
        if(v.getId()==R.id.jednostkaWysokoscRoznoboczny){
            ktory="wysokosc";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPodstawaRoznoboczny){
            ktory="podstawa";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaRamieB){
            ktory="ramieB";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaRamieC){
            ktory="ramieC";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaObwodRoznoboczny){
            ktory="obwod";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPoleRoznoboczny){
            ktory="pole";
            getMenuInflater().inflate(R.menu.pole_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaOpisanyRoznoboczny){
            ktory="opisany";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaWpisanyRoznoboczny){
            ktory="wpisany";
            getMenuInflater().inflate(R.menu.dlugosc2_menu,menu);
        }
        else if(v.getId()==R.id.jednostkaWynikRoznoboczny){
            if(checkBoxWysokosc.isChecked()||checkBoxObwod.isChecked()||checkBoxOpisany.isChecked()||checkBoxPodstawa.isChecked()||checkBoxRamieB.isChecked()||checkBoxRamieC.isChecked()||checkBoxWpisany.isChecked()){
                getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            }
            else if(checkBoxPole.isChecked()){
                getMenuInflater().inflate(R.menu.pole_menu, menu);
            }
            ktory="wynik";
        }
    }
    String podstawaJ="cm", ramieJB="cm",ramieJC="cm", obwodJ="cm", poleJ="cm", wysokoscJ="cm", wpisanyJ="cm", opisanyJ="cm";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        CheckBox checkBoxPodstawa = findViewById(R.id.podstawaWyborRoznoboczny);
        CheckBox checkBoxRamieB = findViewById(R.id.ramieBWyborRoznoboczny);
        CheckBox checkBoxRamieC = findViewById(R.id.ramieCWyborRoznoboczny);
        CheckBox checkBoxObwod = findViewById(R.id.obwodWyborRoznoboczny);
        CheckBox checkBoxPole = findViewById(R.id.poleWyborRoznoboczny);
        CheckBox checkBoxWysokosc = findViewById(R.id.wysokoscWyborRoznoboczny);
        CheckBox checkBoxWpisany = findViewById(R.id.wpisanyWyborRoznoboczny);
        CheckBox checkBoxOpisany = findViewById(R.id.opisanyWyborRoznoboczny);
        CheckBox checkBoxKatA = findViewById(R.id.katAWyborRoznoboczny);
        CheckBox checkBoxKatB = findViewById(R.id.katBWyborRoznoboczny);
        CheckBox checkBoxKatC = findViewById(R.id.katCWyborRoznoboczny);
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("podstawa")){
                    jednostkaPodstawa.setText("cm");
                }
                else if(ktory.equals("ramieB")){
                    jednostkaRamieB.setText("cm");
                }
                else if(ktory.equals("ramieC")){
                    jednostkaRamieC.setText("cm");
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
                    else if(checkBoxRamieB.isChecked()){
                        ramieJB="cm";
                    }
                    else if(checkBoxRamieC.isChecked()){
                        ramieJC="cm";
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
                else if(ktory.equals("ramieB")){
                    jednostkaRamieB.setText("dm");
                }
                else if(ktory.equals("ramieC")){
                    jednostkaRamieC.setText("dm");
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
                    else if(checkBoxRamieB.isChecked()){
                        ramieJB="dm";
                    }
                    else if(checkBoxRamieC.isChecked()){
                        ramieJC="dm";
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
                else if(ktory.equals("ramieB")){
                    jednostkaRamieB.setText("m");
                }
                else if(ktory.equals("ramieC")){
                    jednostkaRamieC.setText("m");
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
                    else if(checkBoxRamieB.isChecked()){
                        ramieJB="m";
                    }
                    else if(checkBoxRamieC.isChecked()){
                        ramieJC="m";
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
                else if(ktory.equals("ramieB")){
                    jednostkaRamieB.setText("km");
                }
                else if (ktory.equals("ramieC")){
                    jednostkaRamieC.setText("km");
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
                    else if(checkBoxRamieB.isChecked()){
                        ramieJB="km";
                    }
                    else if(checkBoxRamieC.isChecked()){
                        ramieJC="km";
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
                    Intent i = new Intent(Pole_trojkata_roznobocznego.this, Szkola.class);
                    i.putExtra("miejsce", "Pole_trojkata_roznobocznego");
                    startActivity(i);
                    Animatoo.animateFade(Pole_trojkata_roznobocznego.this);
                }

                mdrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRoznoboczny);
            TextView drugaLinia = findViewById(R.id.drugaLiniaRoznoboczny);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRoznoboczny);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaRoznoboczny);
            TextView wynik = findViewById(R.id.wynikRoznoboczny);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynikRoznoboczny);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaRoznoboczny);
            TextView drugaLinia = findViewById(R.id.drugaLiniaRoznoboczny);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaRoznoboczny);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaRoznoboczny);
            TextView wynik = findViewById(R.id.wynikRoznoboczny);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynikRoznoboczny);
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
    @Override
    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent i = new Intent(Pole_trojkata_roznobocznego.this, Trojkaty.class);
            startActivity(i);
            Animatoo.animateFade(Pole_trojkata_roznobocznego.this);
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