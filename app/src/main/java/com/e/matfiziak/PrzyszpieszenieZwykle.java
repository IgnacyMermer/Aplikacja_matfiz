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

public class PrzyszpieszenieZwykle extends AppCompatActivity {
    TextView jednostkaPredkosc;
    TextView jednostkaPredkoscPocz;
    TextView jednostkaCzas;
    TextView jednostkaDroga;
    TextView jednostkaWynik;
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;
    ArrayList<String> doWyslania;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przyszpieszenie_zwykle);
        ScrollView relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<String>();
        Intent incomingIntent = getIntent();
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        checkbox = incomingIntent.getStringExtra("checkbox");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        final String nick = incomingIntent.getStringExtra("nick");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        doWyslania = new ArrayList<String>();
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final EditText predkosc = findViewById(R.id.predkosc);
        final EditText predkoscPocz = findViewById(R.id.predkoscPocz);
        final EditText predkoscKonc = findViewById(R.id.predkoscKonc);
        final EditText czas = findViewById(R.id.czas);
        final EditText droga = findViewById(R.id.droga);
        final EditText przyszpieszenie = findViewById(R.id.przyszpieszenie2);
        final EditText sekunda = findViewById(R.id.sekunda);
        final CheckBox checkBoxPrzyszpieszenie = findViewById(R.id.checkboxPrzyszpieszenie);
        final CheckBox checkBoxDroga = findViewById(R.id.checkboxDroga);
        final CheckBox checkBoxOpoznienie = findViewById(R.id.checkboxOpoznienie);
        final CheckBox checkBoxDrogaSekund = findViewById(R.id.checkboxDrogaSekund);
        final CheckBox checkBoxCzas = findViewById(R.id.checkboxCzas);
        final CheckBox checkBoxPredkosc = findViewById(R.id.checkboxPredkosc);
        if(checkbox==null){
            checkbox="";
        }
        if(checkbox.equals("opoznienie")){
            checkBoxOpoznienie.setChecked(true);
        }
        else if(checkbox.equals("przyszpieszenie")){
            checkBoxPrzyszpieszenie.setChecked(true);
        }
        else if(checkbox.equals("predkosc")){
            checkBoxPredkosc.setChecked(true);
        }
        else if(checkbox.equals("czas")){
            checkBoxCzas.setChecked(true);
        }
        else if(checkbox.equals("droga")){
            checkBoxDroga.setChecked(true);
        }
        else if(checkbox.equals("drogaSekund")){
            checkBoxDrogaSekund.setChecked(true);
        }
        final TextView wynikPrzyszpieszenie = findViewById(R.id.wynikPrzyszpieszenie);
        final TextView jednostkaPrzyszpieszenie = findViewById(R.id.jednostkaPrzyszpieszenie4);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPrzyspieszenie);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaPrzyspieszenie);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPrzyspieszenie);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaPrzyspieszenie);
        jednostkaWynik = findViewById(R.id.jednostkaWynik4);
        jednostkaPredkoscPocz = findViewById(R.id.jednostkaPredkoscPocz4);
        jednostkaPredkosc = findViewById(R.id.jednostkaPredkosc4);
        jednostkaDroga = findViewById(R.id.jednostkaDroga4);
        jednostkaCzas = findViewById(R.id.jednostkaCzas4);
        if(incomingList!=null){
            predkosc.setText(incomingList.get(0));
            predkoscPocz.setText(incomingList.get(1));
            predkoscKonc.setText(incomingList.get(2));
            czas.setText(incomingList.get(3));
            droga.setText(incomingList.get(4));
            przyszpieszenie.setText(incomingList.get(5));
            sekunda.setText(incomingList.get(6));
            pierwszaLinia.setText(incomingList.get(7));
            drugaLinia.setText(incomingList.get(8));
            trzeciaLinia.setText(incomingList.get(9));
            czwartaLinia.setText(incomingList.get(10));
            wynikPrzyszpieszenie.setText(incomingList.get(11));
            jednostkaWynik.setText(incomingList.get(12));
            jednostkaCzas.setText(incomingList.get(13));
            jednostkaDroga.setText(incomingList.get(14));
            jednostkaPredkosc.setText(incomingList.get(15));
            jednostkaPredkoscPocz.setText(incomingList.get(16));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(predkosc.getText().toString());
                doWyslania.add(predkoscPocz.getText().toString());
                doWyslania.add(predkoscKonc.getText().toString());
                doWyslania.add(czas.getText().toString());
                doWyslania.add(droga.getText().toString());
                doWyslania.add(przyszpieszenie.getText().toString());
                doWyslania.add(sekunda.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynikPrzyszpieszenie.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                doWyslania.add(jednostkaCzas.getText().toString());
                doWyslania.add(jednostkaDroga.getText().toString());
                doWyslania.add(jednostkaPredkosc.getText().toString());
                doWyslania.add(jednostkaPredkoscPocz.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxPrzyszpieszenie.isChecked()){
                    ktoryCheckbox="przyszpieszenie";
                }
                else if(checkBoxDroga.isChecked()){
                    ktoryCheckbox="droga";
                }
                else if(checkBoxOpoznienie.isChecked()){
                    ktoryCheckbox="opoznienie";
                }
                else if(checkBoxDrogaSekund.isChecked()){
                    ktoryCheckbox="drogaSekund";
                }
                else if(checkBoxCzas.isChecked()){
                    ktoryCheckbox="czas";
                }
                else if(checkBoxPredkosc.isChecked()){
                    ktoryCheckbox = "predkosc";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(PrzyszpieszenieZwykle.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "PrzyszpieszenieZwykle");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(PrzyszpieszenieZwykle.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(PrzyszpieszenieZwykle.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "PrzyszpieszenieZwykle");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(PrzyszpieszenieZwykle.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "PrzyszpieszenieZwykle");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(PrzyszpieszenieZwykle.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "PrzyszpieszenieZwykle");
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
        jednostkaPrzyszpieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
        jednostkaPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPredkosc);
                openContextMenu(v);
            }
        });
        jednostkaPredkoscPocz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPredkoscPocz);
                openContextMenu(v);
            }
        });
        jednostkaCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCzas);
                openContextMenu(v);
            }
        });
        jednostkaDroga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaDroga);
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
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        Button buttonPrzyszpieszenie = findViewById(R.id.buttonPrzyszpieszenie);
        buttonPrzyszpieszenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String strPredkosc = predkosc.getText().toString();
                    String strPredkoscKonc = predkoscKonc.getText().toString();
                    String strPredkoscPocz = predkoscPocz.getText().toString();
                    String strCzas = czas.getText().toString();
                    String strDroga = droga.getText().toString();
                    String strPrzyszpieszenie = przyszpieszenie.getText().toString();
                    String strSekunda = sekunda.getText().toString();
                    if(checkBoxPrzyszpieszenie.isChecked()){
                        Double przyszpieszonko = null;
                        if((!strPredkoscKonc.equals(""))&&(!strCzas.equals(""))&&(!strPredkoscPocz.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            Double b = Double.parseDouble(strPredkoscPocz);
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strCzas);
                            c=  funkcjePrzelicznikowe.czas(c, jednostkaCzas.getText().toString());
                            if(a<b){
                                Double t = a;
                                a=b;
                                b=t;
                                predkoscKonc.setText(""+a);
                                predkoscPocz.setText(""+b);
                            }
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            pierwszaLinia.setText(Html.fromHtml("a=(v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>)/t"));
                            drugaLinia.setText("a=("+x5+"-"+x6+")/"+c);
                            przyszpieszonko = a-b;
                            x5 = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            trzeciaLinia.setText("a="+x5+"/"+c);
                            czwartaLinia.setText("");
                            przyszpieszonko = przyszpieszonko/c;
                            String x = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkoscKonc.equals(""))&&(!strDroga.equals(""))&&(!strCzas.equals(""))){
                            Double vK = Double.parseDouble(strPredkoscKonc);
                            vK = funkcjePrzelicznikowe.predkosc(vK, jednostkaPredkoscPocz.getText().toString());
                            Double s = Double.parseDouble(strDroga);
                            s = funkcjePrzelicznikowe.droga(s, jednostkaDroga.getText().toString());
                            Double t = Double.parseDouble(strCzas);
                            t=funkcjePrzelicznikowe.czas(t, jednostkaCzas.getText().toString());
                            String x5 = funkcjePrzelicznikowe.intowanie(vK);
                            pierwszaLinia.setText(Html.fromHtml("v<sub><small><small>o</small></small></sub>=((2*s)-(v<sub><small><small>k</small></small></sub>*t))/t"));
                            drugaLinia.setText(Html.fromHtml("v<sub><small><small>o</small></small></sub>=((2*"+s+")-("+x5+"*"+t+"))/"+t));
                            Double v0 = ((2*s)-(vK*t))/t;
                            przyszpieszonko = (vK-v0)/t;
                            trzeciaLinia.setText(Html.fromHtml("a=(v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>)/t"));
                            czwartaLinia.setText("a=("+x5+"-"+v0+")/"+t);
                            String x = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkosc.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPredkosc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            pierwszaLinia.setText(Html.fromHtml("a=&#916;v/&#916;t"));
                            drugaLinia.setText("a="+x5+"/"+b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            przyszpieszonko = a/b;
                            String x = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkoscKonc.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strDroga);
                            c = funkcjePrzelicznikowe.droga(c, jednostkaDroga.getText().toString());
                            if(a<b){
                                Double t = a;
                                a=b;
                                b=t;
                                predkoscKonc.setText(""+a);
                                predkoscPocz.setText(""+b);
                            }
                            pierwszaLinia.setText(Html.fromHtml("a=(v<sub><small><small>k</small></small></sub><sup><small><small>2</small></small></sup>-v<sub><small><small>o</small></small></sub><sup><small><small>2</small></small></sup>)/2s"));
                            a=a*a;
                            b=b*b;
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText("a=("+x5+"-"+x6+")/(2*"+c+")");
                            przyszpieszonko = a-b;
                            x5 = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            trzeciaLinia.setText("a="+x5+"/(2*"+c+")");
                            przyszpieszonko=przyszpieszonko/2/c;
                            if(przyszpieszonko<0){
                                przyszpieszonko=przyszpieszonko*-1;
                            }
                            czwartaLinia.setText("");
                            String x = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strCzas.equals(""))&&(!strDroga.equals(""))){
                            Double t = Double.parseDouble(strCzas);
                            t = funkcjePrzelicznikowe.czas(t, jednostkaCzas.getText().toString());
                            Double s = Double.parseDouble(strDroga);
                            s = funkcjePrzelicznikowe.droga(s, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a=(2*s)/t<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=(2*"+s+")/"+t+"<sup><small><small>2</small></small></sup>"));
                            s=2*s;
                            t=t*t;
                            trzeciaLinia.setText("a="+s+"/"+t);
                            czwartaLinia.setText("");
                            Double c = s/t;
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strSekunda.equals(""))&&(!strDroga.equals(""))){
                            int sek = Integer.parseInt(strSekunda);
                            Double s = Double.parseDouble(strDroga);
                            s = funkcjePrzelicznikowe.droga(s, jednostkaDroga.getText().toString());
                            Double s3 = s;
                            Double a = 2*s/sek/sek;
                            pierwszaLinia.setText(Html.fromHtml("a=(2*s<sub><small><small>"+sek+"</small></small></sub>)/t<sub><small><small>"+sek+"</small></small></sub><sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=(2*"+s3+")/"+sek+"<sup><small><small>2</small></small></sup>"));
                            int sek2 = sek-1;
                            Double s2 = a*sek2*sek2/2;
                            trzeciaLinia.setText(Html.fromHtml("s<sub><small><small>"+sek2+"</small></small></sub>=a*t<sub><small><small>"+sek2+"</small></small></sub><sup><small><small>2</small></small></sup>/2"));
                            s=s-s2;
                            czwartaLinia.setText(Html.fromHtml("a=s<sub><small><small>"+sek+"</small></small></sub>/(s<sub><small><small>"+sek+"</small></small></sub>-s<sub><small><small>"+sek2+"</small></small></sub>)"));
                            s=s3/s;
                            String x = funkcjePrzelicznikowe.intowanie(s);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxOpoznienie.isChecked()){
                        Double przyszpieszonko = null;
                        if((!strPredkoscKonc.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strDroga);
                            c = funkcjePrzelicznikowe.droga(c, jednostkaDroga.getText().toString());
                            if(a>b){
                                Double t = a;
                                a=b;
                                b=t;
                                predkoscKonc.setText(""+a);
                                predkoscPocz.setText(""+b);
                            }
                            pierwszaLinia.setText(Html.fromHtml("a=(v<sub><small><small>o</small></small></sub><sup><small><small>2</small></small></sup>-v<sub><small><small>k</small></small></sub><sup><small><small>2</small></small></sup>)/s"));
                            a=a*a;
                            b=b*b;
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText("a=("+x6+"-"+x5+")/(2*"+c+")");
                            przyszpieszonko = b-a;
                            x5=funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            trzeciaLinia.setText("a="+x5+"/(2*"+c+")");
                            czwartaLinia.setText("");
                            przyszpieszonko=przyszpieszonko/2/c;
                            if(przyszpieszonko>0){
                                przyszpieszonko=przyszpieszonko*-1;
                            }
                            String x = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strDroga.equals(""))&&(!strCzas.equals(""))){
                            Double s = Double.parseDouble(strDroga);
                            s = funkcjePrzelicznikowe.droga(s, jednostkaDroga.getText().toString());
                            Double t = Double.parseDouble(strCzas);
                            t = funkcjePrzelicznikowe.czas(t, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a=(2*s)/t<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=(2*"+s+")/"+t+"<sup><small><small>2</small></small></sup>"));
                            s=2*s;
                            t=t*t;
                            trzeciaLinia.setText("a="+s+"/"+t);
                            czwartaLinia.setText("");
                            przyszpieszonko = s/t;
                            wynikPrzyszpieszenie.setText(""+przyszpieszonko);
                        }
                        else if((!strPredkosc.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPredkosc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            if(a<0){
                                a=a*-1;
                            }
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            pierwszaLinia.setText("a=&#916;v/&#916;t");
                            drugaLinia.setText("a="+x5+"/"+b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            przyszpieszonko = a/b;
                            String x = funkcjePrzelicznikowe.intowanie(przyszpieszonko);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strCzas.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strCzas);
                            c = funkcjePrzelicznikowe.czas(c, jednostkaCzas.getText().toString());
                            if(a>b){
                                Double t = a;
                                a=b;
                                b=t;
                                predkoscKonc.setText(""+a);
                                predkoscPocz.setText(""+b);
                            }
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            pierwszaLinia.setText(Html.fromHtml("a=(v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>)/t"));
                            drugaLinia.setText("a=("+x6+"-"+x5+")/"+c);
                            Double d = b-a;
                            x5=funkcjePrzelicznikowe.intowanie(d);
                            trzeciaLinia.setText("a="+x5+"/"+c);
                            czwartaLinia.setText("");
                            d=d/c;
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxDroga.isChecked()){
                        Double droga = null;
                        if((!strPrzyszpieszenie.equals(""))&&(!strCzas.equals(""))&&(!strPredkoscKonc.equals(""))&&(!strPredkoscPocz.equals(""))){
                            Double a = Double.parseDouble(strPrzyszpieszenie);
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            Double c = Double.parseDouble(strPredkoscKonc);
                            Double d = Double.parseDouble( strPredkoscPocz);
                            c = funkcjePrzelicznikowe.predkosc(c, jednostkaPredkoscPocz.getText().toString());
                            d = funkcjePrzelicznikowe.predkosc(d, jednostkaPredkoscPocz.getText().toString());
                            Double y, przysz;
                            String x5 = funkcjePrzelicznikowe.intowanie(c);
                            String x6 = funkcjePrzelicznikowe.intowanie(d);
                            if(d>c) {
                                pierwszaLinia.setText(Html.fromHtml("s=v<sub><small><small>o</small></small></sub>*t-a*t<sup><small><small>2</small></small></sup>/2"));
                                drugaLinia.setText(Html.fromHtml("s="+b+"*"+x6+"-"+a+"*"+b+"<sup><small><small>2</small></small></sup>/2"));
                                Double s3 = b*d-(a*b*b/2);
                                droga = s3;
                            }
                            else {
                                pierwszaLinia.setText(Html.fromHtml("s=v<sub><small><small>o</small></small></sub>*t+a*t<sup><small><small>2</small></small></sup>/2"));
                                drugaLinia.setText(Html.fromHtml("s="+b+"*"+x6+"+"+a+"*"+b+"<sup><small><small>2</small></small></sup>/2"));
                                Double s3 = d*b+(a*b*b/2);
                                droga = s3;
                            }
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPrzyszpieszenie.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPrzyszpieszenie);
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("jeżeli v"+"<sub><small><small>o</small></small></sub>=0"));
                            drugaLinia.setText(Html.fromHtml("s=a*t<sup><small><small>2</small></small></sup>/2"));
                            trzeciaLinia.setText(Html.fromHtml("s="+a+"*"+b+"<sup><small><small>2</small></small></sup>/2"));
                            czwartaLinia.setText("");
                            droga = b*b*a/2;
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strSekunda.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            Double a = Double.parseDouble(strSekunda);
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            Double c = a-1;
                            pierwszaLinia.setText(Html.fromHtml("jeżeli v<sub><small><small>o</small></small></sub>=0"));
                            drugaLinia.setText(Html.fromHtml("s=a*t<sup><small><small>2</small></small></sup>/2"));
                            trzeciaLinia.setText(Html.fromHtml("s<sub><small><small>"+a+"</small></small></sub>="+b+"*"+a+"<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("s<sub><small><small>"+c+"</small></small></sub>="+b+"*"+c+"<sup><small><small>2</small></small></sup>"));
                            Double d = a*a*b/2;
                            c=c*c*b/2;
                            droga = d-c;
                            if(droga<0){
                                droga=droga*-1;
                            }
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkosc.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            Double a = Double.parseDouble(strPredkosc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            pierwszaLinia.setText(Html.fromHtml("jeżeli v<sub><small><small>o</small></small></sub>=0"));
                            drugaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                            Double t = a/b;
                            trzeciaLinia.setText(Html.fromHtml("s=a*t<sup><small><small>2</small></small></sup>/2"));
                            czwartaLinia.setText(Html.fromHtml("s="+b+"*"+t+"<sup><small><small>2</small></small></sup>/2"));
                            droga = b*t*t/2;
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkoscKonc.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strCzas);
                            c = funkcjePrzelicznikowe.czas(c, jednostkaCzas.getText().toString());
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            if(a>b){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+x5+"-"+x6));
                                Double d = a-b;
                                x5=funkcjePrzelicznikowe.intowanie(d);
                                trzeciaLinia.setText(Html.fromHtml("s=v<sub><small><small>o</small></small></sub>*t+&#916;v*t/2"));
                                czwartaLinia.setText(Html.fromHtml("s="+x6+"*"+c+"+"+x5+"*"+c+"/"+2));
                                d=b*c+(d*c/2);
                                droga = d;
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+x6+"-"+x5));
                                Double d = b-a;
                                x5=funkcjePrzelicznikowe.intowanie(d);
                                trzeciaLinia.setText(Html.fromHtml("s=v<sub><small><small>o</small></small></sub>*t-&#916;v*t/2"));
                                czwartaLinia.setText(Html.fromHtml("s="+x6+"*"+c+"-"+x5+"*"+c+"/"+2));
                                d=b*c-(d*c/2);
                                droga = d;
                            }
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPrzyszpieszenie.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))){
                            Double a  =Double.parseDouble(strPrzyszpieszenie);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            Double c = Double.parseDouble(strPredkoscKonc);
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            c = funkcjePrzelicznikowe.predkosc(c, jednostkaPredkoscPocz.getText().toString());
                            Double d,e;
                            String x5 = funkcjePrzelicznikowe.intowanie(c);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            if(b>c){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                d=b-c;
                                drugaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                                e=d/a;
                                String x = funkcjePrzelicznikowe.intowanie(e);
                                e=Double.parseDouble(x);
                                trzeciaLinia.setText(Html.fromHtml("s=v<sub><small><small>o</small></small></sub>*t-a*t<sup><small><small>2</small></small></sup>/2"));
                                czwartaLinia.setText(Html.fromHtml("s="+x6+"*"+e+"-"+a+"*"+e+"<sup><small><small>2</small></small></sup>/2"));
                                droga = (b*e)-(a*e*e/2);
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                d=c-b;
                                drugaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                                e=d/a;
                                String x = funkcjePrzelicznikowe.intowanie(e);
                                e=Double.parseDouble(x);
                                trzeciaLinia.setText(Html.fromHtml("s=v<sub><small><small>o</small></small></sub>*t+a*t<sup><small><small>2</small></small></sup>/2"));
                                czwartaLinia.setText(Html.fromHtml("s="+x6+"*"+e+"+"+a+"*"+e+"<sup><small><small>2</small></small></sup>/2"));
                                droga = (b*e)+(a*e*e/2);
                            }
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            if(droga<0){
                                droga=droga*-1;
                            }
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxDrogaSekund.isChecked()){
                        Double droga = null;
                        if((!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))&&(!strSekunda.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strSekunda);
                            Double przysz = Double.parseDouble(strPrzyszpieszenie);
                            if(przysz<0){
                                przysz=przysz*-1;
                            }
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            if(b>a){
                                pierwszaLinia.setText(Html.fromHtml("s<sub><small><small>"+c+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*t-a*t<sup><small><small>2</small></small></sup>/2"));
                                Double s3 = b*c-(przysz*c*c/2);
                                Double c2 = c-1;
                                drugaLinia.setText(Html.fromHtml("s<sub><small><small>"+c2+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*t-a*t<sup><small><small>2</small></small></sup>/2"));
                                Double s2 = b*c2-(przysz*c2*c2/2);
                                trzeciaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+c+"</small></small></sub>=s<sub><small><small>"+c+"</small></small></sub>-s<sub><small><small>"+c2+"</small></small></sub>"));
                                czwartaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+c+"</small></small></sub>="+s3+"-"+s2));
                                droga = s3-s2;
                            }
                            else {
                                pierwszaLinia.setText(Html.fromHtml("s<sub><small><small>"+c+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*t+a*t<sup><small><small>2</small></small></sup>/2"));
                                Double s3 = b*c+(przysz*c*c/2);
                                Double c2 = c-1;
                                drugaLinia.setText(Html.fromHtml("s<sub><small><small>"+c2+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*t+a*t<sup><small><small>2</small></small></sup>/2"));
                                Double s2 = b*c2+(przysz*c2*c2/2);
                                trzeciaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+c+"</small></small></sub>=s<sub><small><small>"+c+"</small></small></sub>-s<sub><small><small>"+c2+"</small></small></sub>"));
                                czwartaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+c+"</small></small></sub>="+s3+"-"+s2));
                                droga = s3-s2;
                            }
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))&&(!strSekunda.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strSekunda);
                            Double d = Double.parseDouble(strCzas);
                            d = funkcjePrzelicznikowe.czas(d, jednostkaCzas.getText().toString());
                            Double y;
                            Double przysz;
                            if(b>a) {
                                y = b - a;
                                pierwszaLinia.setText(Html.fromHtml("a=&#916;v/&#916;t"));
                                przysz = y/d;
                                drugaLinia.setText(Html.fromHtml("s<sub><small><small>"+c+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*t-a*t<sup><small><small>2</small></small></sup>/2"));
                                Double s3 = b*c-(przysz*c*c/2);
                                Double c2 = c-1;
                                trzeciaLinia.setText(Html.fromHtml("s<sub><small><small>"+c2+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*-a*t<sup><small><small>2</small></small></sup>/2"));
                                czwartaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+c+"</small></small></sub>=s<sub><small><small>"+c+"</small></small></sub>-s<sub><small><small>"+c2+"</small></small></sub>"));
                                Double s2 = b*c2-(przysz*c2*c2/2);
                                droga = s3-s2;
                            }
                            else {
                                y = a-b;
                                pierwszaLinia.setText(Html.fromHtml("a=&#916;v/&#916;t"));
                                przysz = y/d;
                                drugaLinia.setText(Html.fromHtml("s<sub><small><small>"+c+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*t+a*t<sup><small><small>2</small></small></sup>/2"));
                                Double s3 = b*c+(przysz*c*c/2);
                                Double c2 = c-1;
                                trzeciaLinia.setText(Html.fromHtml("s<sub><small><small>"+c2+"</small></small></sub>=v<sub><small><small>o</small></small></sub>*+a*t<sup><small><small>2</small></small></sup>/2"));
                                czwartaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+c+"</small></small></sub>=s<sub><small><small>"+c+"</small></small></sub>-s<sub><small><small>"+c2+"</small></small></sub>"));
                                Double s2 = b*c2+(przysz*c2*c2/2);
                                droga = s3-s2;
                            }
                            if(przysz<0){
                                przysz=przysz*-1;
                            }
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strSekunda.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            int a = Integer.parseInt(strSekunda);
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            int c = a-1;
                            pierwszaLinia.setText(Html.fromHtml("s<sub><small><small>"+a+"</small></small></sub>=a*t<sub><small><small>"+a+"</small></small></sub><sup><small><small>2</small></small></sup>/2"));
                            Double d = a*a*b/2;
                            drugaLinia.setText(Html.fromHtml("s<sub><small><small>"+c+"</small></small></sub>=a*t<sub><small><small>"+c+"</small></small></sub><sup><small><small>2</small></small></sup>/2"));
                            Double e=c*c*b/2;
                            trzeciaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+a+"</small></small></sub>=s<sub><small><small>"+a+"</small></small></sub>-s<sub><small><small>"+c+"</small></small></sub>"));
                            czwartaLinia.setText(Html.fromHtml("&#916;s<sub><small><small>"+a+"</small></small></sub>="+d+"-"+e));
                            droga = d-e;
                            if(droga<0){
                                droga=droga*-1;
                            }
                            droga = funkcjePrzelicznikowe.drogaWynik(droga, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                        String x = wynikPrzyszpieszenie.getText().toString();
                        if(x.charAt(0)=='-'){
                            Double y = Double.parseDouble(x);
                            if(y<0){
                                y=y*-1;
                            }
                            wynikPrzyszpieszenie.setText(""+y);
                        }
                    }
                    else if(checkBoxPredkosc.isChecked()){
                        Double predkosc = null;
                        if((!strPrzyszpieszenie.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPrzyszpieszenie);
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#916;v=a*t"));
                            drugaLinia.setText(Html.fromHtml("&#916;v="+a+"*"+b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            predkosc = a*b;
                            predkosc = funkcjePrzelicznikowe.predkoscWynik(predkosc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(predkosc);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strCzas.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strDroga);
                            a = funkcjePrzelicznikowe.droga(a, jednostkaDroga.getText().toString());
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a=(2*s)/(t<sup><small><small>2</small></small></sup>)"));
                            drugaLinia.setText(Html.fromHtml("a=(2*"+a+")/("+b+"<sup><small><small>2</small></small></sup>)"));
                            Double przys = 2*a/b/b;
                            trzeciaLinia.setText("v=a*t");
                            czwartaLinia.setText("v="+przys+"*"+b);
                            predkosc = przys * b;
                            predkosc = funkcjePrzelicznikowe.predkoscWynik(predkosc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(predkosc);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxCzas.isChecked()){
                        Double czas = null;
                        if((!strPredkosc.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            Double a = Double.parseDouble(strPredkosc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            pierwszaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                            drugaLinia.setText("t="+a+"/"+b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            czas=a/b;
                            czas = funkcjePrzelicznikowe.czas(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(czas);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkoscKonc.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strPrzyszpieszenie);
                            Double d;
                            if(a>b){
                                d=a-b;
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+a+"-"+b));
                            }
                            else{
                                d=b-a;
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+b+"-"+a));
                            }
                            trzeciaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                            czwartaLinia.setText("t="+d+"/"+c);
                            czas = d/c;
                            czas = funkcjePrzelicznikowe.czas(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(czas);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPredkoscKonc.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strPredkoscKonc);
                            Double b = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strDroga);
                            c = funkcjePrzelicznikowe.droga(c, jednostkaDroga.getText().toString());
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            String x6 = funkcjePrzelicznikowe.intowanie(b);
                            if(b==0){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+x5+"-"+x6));
                                Double d = a-b;
                                if(d<0){
                                    d=d*-1;
                                }
                                x5 = funkcjePrzelicznikowe.intowanie(d);
                                trzeciaLinia.setText(Html.fromHtml("t=2*s/&#916;v"));
                                czwartaLinia.setText("t=2*"+c+"/"+x5);
                                czas = 2*c/d;
                            }
                            else if(a>b){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+x5+"-"+x6));
                                Double d = a-b;
                                if(d<0){
                                    d=d*-1;
                                }
                                x5 = funkcjePrzelicznikowe.intowanie(d);
                                czas = c/(b+(d/2));
                                trzeciaLinia.setText(Html.fromHtml("t=s/(v<sub><small><small>o</small></small></sub>+&#916;v/2)"));
                                czwartaLinia.setText("t="+c+"/("+x6+"+"+x5+"/2)");
                            }
                            else {
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+x6+"-"+x5));
                                Double d = b-a;
                                if(d<0){
                                    d=d*-1;
                                }
                                x5 = funkcjePrzelicznikowe.intowanie(d);
                                trzeciaLinia.setText(Html.fromHtml("t=s/(v<sub><small><small>o</small></small></sub>+&#916;v/2)"));
                                czwartaLinia.setText("t="+c+"/("+x6+"+"+x5+"/2)");
                                czas = c/(b+(d/2));
                            }
                            czas = funkcjePrzelicznikowe.czas(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(czas);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else if((!strPrzyszpieszenie.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strPrzyszpieszenie);
                            Double b = Double.parseDouble(strDroga);
                            b = funkcjePrzelicznikowe.droga(b, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("t=&#8730;(2*s/a)"));
                            drugaLinia.setText(Html.fromHtml("t=&#8730;(2*"+b+"/"+a+")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double c = 2*b/a;
                            c=sqrt(c);
                            czas = funkcjePrzelicznikowe.czas(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(c);
                            wynikPrzyszpieszenie.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                    wynikPrzyszpieszenie.setText("Wpisz liczby");
                }
            }
        });
        checkBoxCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                if(checkBoxCzas.isChecked()){
                    jednostkaWynik.setText("s");
                }
                else{
                    jednostkaWynik.setText("");
                }
                checkBoxDroga.setChecked(false);
                checkBoxDrogaSekund.setChecked(false);
                checkBoxOpoznienie.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxPredkosc.setChecked(false);
            }
        });
        checkBoxDroga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxDrogaSekund.setChecked(false);
                checkBoxOpoznienie.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxDroga.isChecked()){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxDrogaSekund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxOpoznienie.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxDrogaSekund.isChecked()){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxOpoznienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxDrogaSekund.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxOpoznienie.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzyszpieszenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxDrogaSekund.setChecked(false);
                checkBoxOpoznienie.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxPrzyszpieszenie.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxDrogaSekund.setChecked(false);
                checkBoxOpoznienie.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                if(checkBoxPredkosc.isChecked()){
                    jednostkaWynik.setText("m/s");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        Button buttonCzysc = findViewById(R.id.buttonCzysc);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxDrogaSekund.setChecked(false);
                checkBoxOpoznienie.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                wynikPrzyszpieszenie.setText("");
                predkosc.setText("");
                predkoscKonc.setText("");
                predkoscPocz.setText("");
                przyszpieszenie.setText("");
                sekunda.setText("");
                czas.setText("");
                droga.setText("");
                jednostkaWynik.setText("");
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
                jednostkaPrzyszpieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                jednostkaPredkoscPocz.setText("m/s");
                jednostkaPredkosc.setText("m/s");
                jednostkaCzas.setText("s");
                jednostkaDroga.setText("m");
            }
        });
        mdrawerLayout = findViewById(R.id.drawerPrzyszpieszenieZwykle_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(PrzyszpieszenieZwykle.this, Konto.class);
                i.putExtra("miejsce", "PrzyszpieszenieZwykle");
                startActivity(i);
                Animatoo.animateFade(PrzyszpieszenieZwykle.this);
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
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPrzyspieszenie);
        TextView drugaLinia = findViewById(R.id.drugaLiniaPrzyspieszenie);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPrzyspieszenie);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaPrzyspieszenie);
        TextView wynik = findViewById(R.id.wynikPrzyszpieszenie);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPrzyspieszenie);
            TextView drugaLinia = findViewById(R.id.drugaLiniaPrzyspieszenie);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPrzyspieszenie);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaPrzyspieszenie);
            TextView wynik = findViewById(R.id.wynikPrzyszpieszenie);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik4);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPrzyspieszenie);
            TextView drugaLinia = findViewById(R.id.drugaLiniaPrzyspieszenie);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPrzyspieszenie);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaPrzyspieszenie);
            TextView wynik = findViewById(R.id.wynikPrzyszpieszenie);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik4);
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
        CheckBox checkBoxPredkosc = findViewById(R.id.checkboxPredkosc);
        CheckBox checkBoxDroga = findViewById(R.id.checkboxDroga);
        CheckBox checkBoxDrogaSekund = findViewById(R.id.checkboxDrogaSekund);
        CheckBox checkBoxCzas = findViewById(R.id.checkboxCzas);
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.jednostkaPredkosc4){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="predkosc";
        }
        else if(v.getId()==R.id.jednostkaWynik4&&checkBoxPredkosc.isChecked()){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="wynik";
        }
        else if (v.getId() == R.id.jednostkaPredkoscPocz4) {
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="predkosc2";
        }
        else if (v.getId() == R.id.jednostkaDroga4) {
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="dlugosc";
        }
        else if(v.getId()==R.id.jednostkaWynik4&&checkBoxDroga.isChecked()){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
        else if (v.getId() == R.id.jednostkaWynik4&&checkBoxDrogaSekund.isChecked()) {
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
        else if (v.getId() == R.id.jednostkaCzas4) {
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory = "czas";
        }
        else if (v.getId() == R.id.jednostkaWynik4&&checkBoxCzas.isChecked()) {
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory = "wynik";
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")) {
                    jednostkaWynik.setText("cm");
                }
                else{
                    jednostkaDroga.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")) {
                    jednostkaWynik.setText("dm");
                }
                else{
                    jednostkaDroga.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")) {
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaDroga.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")) {
                    jednostkaWynik.setText("km");
                }
                else{
                    jednostkaDroga.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sekunda2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("s");
                }
                else{
                    jednostkaCzas.setText("s");
                }
                Toast.makeText(this, "Sekunda", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.minuta2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("min");
                }
                else{
                    jednostkaCzas.setText("min");
                }
                Toast.makeText(this, "Minuta", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.godzina2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("h");
                }
                else{
                    jednostkaCzas.setText("h");
                }
                Toast.makeText(this, "Godzina", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/s");
                }
                else if(ktory.equals("predkosc")){
                    jednostkaPredkosc.setText("m/s");
                }
                else{
                    jednostkaPredkoscPocz.setText("m/s");
                }
                Toast.makeText(this, "Metry na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/min");
                }
                else if(ktory.equals("predkosc")){
                    jednostkaPredkosc.setText("m/m");
                }
                else{
                    jednostkaPredkoscPocz.setText("m/m");
                }
                Toast.makeText(this, "Metry na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/s");
                }
                else if(ktory.equals("predkosc")){
                    jednostkaPredkosc.setText("km/s");
                }
                else{
                    jednostkaPredkoscPocz.setText("km/s");
                }
                Toast.makeText(this, "Kilometry na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/m");
                }
                else if(ktory.equals("predkosc")){
                    jednostkaPredkosc.setText("km/m");
                }
                else{
                    jednostkaPredkoscPocz.setText("km/m");
                }
                Toast.makeText(this, "Kilometry na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaH:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/h");
                }
                else if(ktory.equals("predkosc")){
                    jednostkaPredkosc.setText("km/h");
                }
                else{
                    jednostkaPredkoscPocz.setText("km/h");
                }
                Toast.makeText(this, "Kilometry na godzinę", Toast.LENGTH_SHORT).show();
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
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition))))
                        .get(childPosition).toString();
                if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(PrzyszpieszenieZwykle.this, Szkola.class);
                    i.putExtra("miejsce", "PrzyszpieszenieZwykle");
                    startActivity(i);
                    Animatoo.animateFade(PrzyszpieszenieZwykle.this);
                }
                else
                    throw new IllegalArgumentException("Not supported fragment");
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
            Intent i = new Intent(PrzyszpieszenieZwykle.this, FizykaKalkulator.class);
            startActivity(i);
            Animatoo.animateFade(PrzyszpieszenieZwykle.this);
        }
    }
    private void genData() {
        List<String> title = Arrays.asList("Fizyka teoria", "Matematyka teoria", "Fizyka kalkulator", "Matematyka kalkulator", "Informatyka algorytmy");
        List<String> childitem = Arrays.asList("Kinematyka", "Dynamika", "Hydrostatyka", "Aerostatyka", "Termodynamika");
        List<String> childitem2 = Arrays.asList("Trójkąty", "Czworokąty", "Figury przestrzenne", "Algebra", "lalala");
        List<String> childitem3 = Arrays.asList("");
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