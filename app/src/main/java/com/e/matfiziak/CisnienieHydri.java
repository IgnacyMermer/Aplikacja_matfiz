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

public class CisnienieHydri extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private NavigationManager navigationManager;
    private Map<String, List<String>> lstChild;
    CheckBox checkBoxSila, checkBoxCisnienie, checkBoxWysokosc, checkBoxPowierzchnia, checkBoxPrzesuniecie, checkBoxGestosc;
    EditText sila1,sila2, cisnienie, przesuniecie, powierzchnia1, powierzchnia2, wysokosc, gestosc, masa, cisnienieAtm;
    TextView jednostkaSila1, jednostkaSila2, jednostkaCisnienie, jednostkaPrzesuniecie, jednostkaPowierzchnia1,jednostkaPowierzchnia2, jednostkaGestosc, jednostkaWysokosc, jednostkaWynik;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik, jednostkaMasa, jednostkaCisnienieAtm;
    ArrayList<String> doWyslania;
    Button buttonCzysc, buttonWynik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cisnienie_hydri);
        final ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        final AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        Intent incomingIntent = getIntent();
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(CisnienieHydri.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "CisnienieHydro");
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(CisnienieHydri.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(CisnienieHydri.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "CisnienieHydro");
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(CisnienieHydri.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "CisnienieHydro");
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(CisnienieHydri.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "CisnienieHydro");
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        jednostkaWynik = findViewById(R.id.jednostkaWynikCisnienieHydro);
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        jednostkaWysokosc = findViewById(R.id.jednostkaWysokoscCisnienieHydro);
        jednostkaWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokosc);
                openContextMenu(v);
            }
        });
        jednostkaSila2 = findViewById(R.id.jednostkaSilaNac2CisnienieHydro);
        jednostkaSila2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaSila2);
                openContextMenu(v);
            }
        });
        jednostkaSila1 = findViewById(R.id.jednostkaSilaNac1CisnienieHydro);
        jednostkaSila1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaSila1);
                openContextMenu(v);
            }
        });
        jednostkaPrzesuniecie = findViewById(R.id.jednostkaPrzesuniecieCisnienieHydro);
        jednostkaPrzesuniecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzesuniecie);
                openContextMenu(v);
            }
        });
        jednostkaPowierzchnia2 = findViewById(R.id.jednostkaPowierzchnia2CisnienieHydro);
        jednostkaPowierzchnia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPowierzchnia2);
                openContextMenu(v);
            }
        });
        jednostkaPowierzchnia1 = findViewById(R.id.jednostkaPowierzchnia1CisnienieHydro);
        jednostkaPowierzchnia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPowierzchnia1);
                openContextMenu(v);
            }
        });
        jednostkaCisnienie = findViewById(R.id.jednostkaCisnienieCisnienieHydro);
        jednostkaCisnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCisnienie);
                openContextMenu(v);
            }
        });
        jednostkaGestosc = findViewById(R.id.jednostkaGestoscCisnienieHydro);
        jednostkaGestosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaGestosc);
                openContextMenu(v);
            }
        });
        jednostkaMasa = findViewById(R.id.jednostkaMasaCisnienieHydro);
        jednostkaMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMasa);
                openContextMenu(v);
            }
        });
        jednostkaCisnienieAtm = findViewById(R.id.jednostkaCisnienieCisnienieHydro);
        jednostkaCisnienieAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCisnienieAtm);
                openContextMenu(v);
            }
        });
        jednostkaCisnienieAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCisnienieAtm);
                openContextMenu(v);
            }
        });
        sila1 = findViewById(R.id.silaNac1CisnienieHydro);
        sila2 = findViewById(R.id.silaNac2CisnienieHydro);
        cisnienie = findViewById(R.id.cisnienieCisnienieHydro);
        przesuniecie = findViewById(R.id.przesuniecieCisnienieHydro);
        powierzchnia1 = findViewById(R.id.powierzchnia1CisnienieHydro);
        powierzchnia2 = findViewById(R.id.powierzchnia2CisnienieHydro);
        wysokosc = findViewById(R.id.wysokoscCisnienieHydro);
        gestosc = findViewById(R.id.gestoscCisnienieHydro);
        masa = findViewById(R.id.masaCisnienieHydro);
        cisnienieAtm = findViewById(R.id.cisnienieAtmCisnienieHydro);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaCisnienieHydro);
        drugaLinia = findViewById(R.id.drugaLiniaCisnienieHydro);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaCisnienieHydro);
        czwartaLinia = findViewById(R.id.czwartaLiniaCisnienieHydro);
        wynik = findViewById(R.id.wynikCisnienieHydro);
        checkBoxCisnienie = findViewById(R.id.checkboxCisnienieCisnienieHydro);
        checkBoxGestosc = findViewById(R.id.checkboxGestoscCisnienieHydro);
        checkBoxPowierzchnia = findViewById(R.id.checkboxPowierzchniaCisnienieHydro);
        checkBoxPrzesuniecie = findViewById(R.id.checkboxPrzesuniecieCisnienieHydro);
        checkBoxSila = findViewById(R.id.checkboxSilaCisnienieHydro);
        checkBoxWysokosc = findViewById(R.id.checkboxWysokoscCisnienieHydro);
        buttonCzysc = findViewById(R.id.buttonCzyscCisnienieHydro);
        buttonWynik = findViewById(R.id.buttonObliczCisnienieHydro);
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxCisnienie.isChecked()){
                        Log.i("wynik", "lalaa");
                        Double cisnien = null;
                        if((!cisnienieAtm.getText().toString().equals(""))&&(!gestosc.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            Double wysokos = Double.parseDouble(wysokosc.getText().toString());
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWysokosc.getText().toString());
                            Double cisnienAtm = Double.parseDouble(cisnienieAtm.getText().toString());
                            cisnienAtm = funkcjePrzelicznikowe.cisnienie(cisnienAtm, jednostkaCisnienieAtm.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>+&#961;*g*h"));
                            drugaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>+&#961;*10*h"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            cisnien = gestos*10*wysokos+cisnienAtm;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                        }
                        else if((!gestosc.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            Double wysokos = Double.parseDouble(wysokosc.getText().toString());
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            drugaLinia.setText(Html.fromHtml("p=&#961;*10*h"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            cisnien = gestos*10*wysokos;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                            wynik.setText(x);
                        }
                        else if((!masa.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            pierwszaLinia.setText("p=F/s");
                            drugaLinia.setText(Html.fromHtml("p=F<sub><small><small>g</small></small></sub>/S"));
                            trzeciaLinia.setText("p=m*g/S");
                            czwartaLinia.setText("p=m*10/S");
                            cisnien = mas*10/powierzchn;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                            wynik.setText(x);
                        }
                        else if((!cisnienieAtm.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugoscPred(wys, jednostkaWysokosc.getText().toString());
                            Double cisnienAtm = Double.parseDouble(cisnienieAtm.getText().toString());
                            cisnienAtm = funkcjePrzelicznikowe.cisnienie(cisnienAtm, jednostkaCisnienieAtm.getText().toString());
                            pierwszaLinia.setText("Jeśli ciecz to woda");
                            drugaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>+&#961;*g*h"));
                            trzeciaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>1000*10*h"));
                            czwartaLinia.setText("");
                            cisnien = 10000*wys+cisnienAtm;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                            wynik.setText(x);
                        }
                        else if(!wysokosc.getText().toString().equals("")){
                            Double wys = Double.parseDouble(wysokosc.getText().toString());
                            wys = funkcjePrzelicznikowe.dlugoscPred(wys, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("Jeśli ciecz to woda");
                            drugaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            trzeciaLinia.setText("p=1000*10*h");
                            czwartaLinia.setText("");
                            cisnien = 10000*wys;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        Double wysokos = null;
                        if((!cisnienie.getText().toString().equals(""))&&(gestosc.getText().toString().equals(""))&&(!cisnienieAtm.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            Double cisnienAtm = Double.parseDouble(cisnienieAtm.getText().toString());
                            cisnienAtm = funkcjePrzelicznikowe.cisnienie(cisnienAtm, jednostkaCisnienieAtm.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>+&#961*g*h"));
                            drugaLinia.setText(Html.fromHtml("h=(p-p<sub><small><small>atm</small></small></sub>)/(&#961;*g)"));
                            trzeciaLinia.setText(Html.fromHtml("h=(p-p<sub><small><small>atm</small></small></sub>)/(&#961;*10)"));
                            czwartaLinia.setText("");
                            wysokos = (cisnien-cisnienAtm)/10/gestos;
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                        else if((!sila1.getText().toString().equals(""))&&(!gestosc.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila1.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila1.getText().toString());
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            trzeciaLinia.setText(Html.fromHtml("h=p/(&#961;*g)"));
                            czwartaLinia.setText(Html.fromHtml("h=F/(&#961;*g*S)"));
                            wysokos = sil/gestos/10/powierzchn;
                            wysokos = funkcjePrzelicznikowe.dlugoscPredWynik(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                        else if((!sila1.getText().toString().equals(""))&&(!gestosc.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila1.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila1.getText().toString());
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            trzeciaLinia.setText(Html.fromHtml("h=p/(&#961;*g)"));
                            czwartaLinia.setText(Html.fromHtml("h=F/(&#961;*g*S)"));
                            wysokos = sil/gestos/10/powierzchn;
                            wysokos = funkcjePrzelicznikowe.dlugoscPredWynik(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                        else if((!cisnienie.getText().toString().equals(""))&&(!cisnienieAtm.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double gestos = 1000.0;
                            Double cisnienAtm = Double.parseDouble(cisnienieAtm.getText().toString());
                            cisnienAtm = funkcjePrzelicznikowe.cisnienie(cisnienAtm, jednostkaCisnienieAtm.getText().toString());
                            pierwszaLinia.setText("Jeśli ciecz to woda");
                            drugaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>+&#961*g*h"));
                            trzeciaLinia.setText(Html.fromHtml("h=(p-p<sub><small><small>atm</small></small></sub>)/(&#961;*g)"));
                            czwartaLinia.setText(Html.fromHtml("h=(p-p<sub><small><small>atm</small></small></sub>)/(1000*10)"));
                            wysokos = (cisnien-cisnienAtm)/10/gestos;
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                        else if((!powierzchnia1.getText().toString().equals(""))&&(!cisnienie.getText().toString().equals(""))&&(!gestosc.getText().toString().equals(""))){
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            drugaLinia.setText(Html.fromHtml("h=p/(&#961;*g)"));
                            trzeciaLinia.setText(Html.fromHtml("h=p/(&#961;*10)"));
                            czwartaLinia.setText("");
                            wysokos = cisnien / gestos/10;
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                        else if((!powierzchnia1.getText().toString().equals(""))&&(!cisnienie.getText().toString().equals(""))){
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            pierwszaLinia.setText("Jeśli ciecz to woda");
                            drugaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            trzeciaLinia.setText(Html.fromHtml("h=p/(&#961;*g)"));
                            czwartaLinia.setText(Html.fromHtml("h=p/(1000*10)"));
                            wysokos = cisnien / 10000;
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                        else if((!przesuniecie.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))&&(!powierzchnia2.getText().toString().equals(""))){
                            Double przesuniec = Double.parseDouble(przesuniecie.getText().toString());
                            przesuniec = funkcjePrzelicznikowe.dlugoscPred(przesuniec, jednostkaPrzesuniecie.getText().toString());
                            Double powierzchn1 = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn1 = funkcjePrzelicznikowe.pole(powierzchn1, jednostkaPowierzchnia1.getText().toString());
                            Double powierzchn2 = Double.parseDouble(powierzchnia2.getText().toString());
                            powierzchn2 = funkcjePrzelicznikowe.pole(powierzchn2, jednostkaPowierzchnia2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("S<sub><small><small>1</small></small></sub>/h<sub><small><small>1</small></small></sub>=S<sub><small><small>2</small></small></sub>/h<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("h<sub><small><small>2</small></small></sub>=S<sub><small><small>2</small></small></sub>*h<sub><small><small>1</small></small></sub>/S<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wysokos = powierzchn2*przesuniec/powierzchn1;
                            wysokos = funkcjePrzelicznikowe.dlugoscPredWynik(wysokos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(wysokos);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxGestosc.isChecked()){
                        Double gestos = null;
                        if ((!cisnienieAtm.getText().toString().equals("")) && (!cisnienie.getText().toString().equals("")) && (!wysokosc.getText().toString().equals(""))) {
                            Double cisnienAtm = Double.parseDouble(cisnienieAtm.getText().toString());
                            cisnienAtm = funkcjePrzelicznikowe.cisnienie(cisnienAtm, jednostkaCisnienieAtm.getText().toString());
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double wysokos = Double.parseDouble(wysokosc.getText().toString());
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=p<sub><small><small>atm</small></small></sub>+&#961;*g*h"));
                            drugaLinia.setText(Html.fromHtml("&#961;=(p-p<sub><small><small>atm</small></small></sub>)/(g*h)"));
                            trzeciaLinia.setText(Html.fromHtml("&#961;=(p-p<sub><small><small>atm</small></small></sub>)/(10*h)"));
                            czwartaLinia.setText("");
                            gestos = (cisnien-cisnienAtm)/10/wysokos;
                            gestos = funkcjePrzelicznikowe.gestoscWynik(gestos, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(gestos);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxSila.isChecked()){
                        Double sil=null;
                        if((!sila1.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))&&(!powierzchnia2.getText().toString().equals(""))){
                            Double sil1 = Double.parseDouble(sila1.getText().toString());
                            sil1 = funkcjePrzelicznikowe.sila(sil1, jednostkaSila1.getText().toString());
                            Double powierzchn1 = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn1 = funkcjePrzelicznikowe.pole(powierzchn1, jednostkaPowierzchnia1.getText().toString());
                            Double powierzchn2 = Double.parseDouble(powierzchnia2.getText().toString());
                            powierzchn2 = funkcjePrzelicznikowe.pole(powierzchn2, jednostkaPowierzchnia2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>1</small></small></sub>/S<sub><small><small>1</small></small></sub>=F<sub><small><small>2</small></small></sub>/S<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>2</small></small></sub>=F<sub><small><small>1</small></small></sub>*S<sub><small><small>2</small></small></sub>/S<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sil = sil1*powierzchn2/powierzchn1;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!cisnienie.getText().toString().equals(""))&&(!powierzchnia2.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double powierzchn2 = Double.parseDouble(powierzchnia2.getText().toString());
                            powierzchn2 = funkcjePrzelicznikowe.pole(powierzchn2,jednostkaPowierzchnia2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=F<sub><small><small>g</small></small></sub>/S<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>g</small></small></sub>=P*S<sub><small><small>2</small></small></sub>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sil = cisnien*powierzchn2;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!gestosc.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))){
                            Double gestos = Double.parseDouble(gestosc.getText().toString());
                            gestos = funkcjePrzelicznikowe.gestosc(gestos, jednostkaGestosc.getText().toString());
                            Double wysokos = Double.parseDouble(wysokosc.getText().toString());
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWysokosc.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            drugaLinia.setText(Html.fromHtml("p=&#961;*10*h"));
                            trzeciaLinia.setText(Html.fromHtml("F<sub><small><small>p</small></small></sub>=p*S"));
                            czwartaLinia.setText("");
                            Double cisnien = gestos*10*wysokos;
                            sil = cisnien*powierzchn;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!wysokosc.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))){
                            Double wysokos = Double.parseDouble(wysokosc.getText().toString());
                            wysokos = funkcjePrzelicznikowe.dlugoscPred(wysokos, jednostkaWysokosc.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia1.getText().toString());
                            pierwszaLinia.setText("Jeśli ciecz to woda");
                            drugaLinia.setText(Html.fromHtml("p=&#961;*g*h"));
                            trzeciaLinia.setText(Html.fromHtml("p=&#961;*10*h"));
                            czwartaLinia.setText(Html.fromHtml("F<sub><small><small>p</small></small></sub>=p*S"));
                            Double cisnien = 1000*10*wysokos;
                            sil = cisnien*powierzchn;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!cisnienie.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double powierzchn1 = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn1 = funkcjePrzelicznikowe.pole(powierzchn1,jednostkaPowierzchnia1.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=F<sub><small><small>g</small></small></sub>/S<sub><small><small>1</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>g</small></small></sub>=P*S<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sil = cisnien*powierzchn1;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxPowierzchnia.isChecked()){
                        Double powierzchn = null;
                        if ((!powierzchnia2.getText().toString().equals("")) && (!sila1.getText().toString().equals("")) && (!sila2.getText().toString().equals(""))) {
                            Double powierzchn2 = Double.parseDouble(powierzchnia2.getText().toString());
                            powierzchn2 = funkcjePrzelicznikowe.pole(powierzchn2, jednostkaPowierzchnia2.getText().toString());
                            Double sil1 = Double.parseDouble(sila1.getText().toString());
                            sil1 = funkcjePrzelicznikowe.sila(sil1, jednostkaSila1.getText().toString());
                            Double sil2 = Double.parseDouble(sila2.getText().toString());
                            sil2 = funkcjePrzelicznikowe.sila(sil2, jednostkaSila2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P<sub><small><small>2</small></small></sub>=S<sub><small><small>2</small></small></sub>*P<sub><small><small>1</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("P=F<sub><small><small>g</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("S<sub><small><small>2</small></small></sub>*F<sub><small><small>1</small></small></sub>/S<sub><small><small>1</small></small></sub>=F<sub><small><small>2</small></small></sub>"));
                            czwartaLinia.setText(Html.fromHtml("S<sub><small><small>1</small></small></sub>=S<sub><small><small>2</small></small></sub>*F<sub><small><small>1</small></small></sub>/F<sub><small><small>2</small></small></sub>"));
                            powierzchn = powierzchn2*sil1/sil2;
                            powierzchn = funkcjePrzelicznikowe.poleWynik(powierzchn, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(powierzchn);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxPrzesuniecie.isChecked()){
                        Double przesuniec = null;
                        if((!sila1.getText().toString().equals(""))&&(!powierzchnia1.getText().toString().equals(""))&&(!powierzchnia2.getText().toString().equals(""))&&(!przesuniecie.getText().toString().equals(""))){
                            Double sil1 = Double.parseDouble(sila1.getText().toString());
                            sil1 = funkcjePrzelicznikowe.sila(sil1, jednostkaSila1.getText().toString());
                            Double powierzchn1 = Double.parseDouble(powierzchnia1.getText().toString());
                            powierzchn1 = funkcjePrzelicznikowe.pole(powierzchn1, jednostkaPowierzchnia1.getText().toString());
                            Double powierzchn2 = Double.parseDouble(powierzchnia2.getText().toString());
                            powierzchn2 = funkcjePrzelicznikowe.pole(powierzchn2, jednostkaPowierzchnia2.getText().toString());
                            Double przesuniec1 = Double.parseDouble(przesuniecie.getText().toString());
                            przesuniec1 = funkcjePrzelicznikowe.dlugoscPred(przesuniec1, jednostkaPrzesuniecie.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>1</small></small></sub>/S<sub><small><small>1</small></small></sub>=F<sub><small><small>2</small></small></sub>/S<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>2</small></small></sub>=F<sub><small><small>1</small></small></sub>*S<sub><small><small>2</small></small></sub>/S<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("F<sub><small><small>1</small></small></sub>*h<sub><small><small>1</small></small></sub>=F<sub><small><small>2</small></small></sub>*h<sub><small><small>2</small></small></sub>"));
                            czwartaLinia.setText(Html.fromHtml("h<sub><small><small>2</small></small></sub>=F<sub><small><small>1</small></small></sub>*h<sub><small><small>1</small></small></sub>/F<sub><small><small>2</small></small></sub>"));
                            Double sil2 = sil1*powierzchn2/powierzchn1;
                            przesuniec = sil1*przesuniec1/sil2;
                            przesuniec = funkcjePrzelicznikowe.dlugoscPredWynik(przesuniec, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(przesuniec);
                            wynik.setText(x);
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage());
                }
            }
        });
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                jednostkaWynik.setText("");
                jednostkaCisnienie.setText("Pa");
                jednostkaWysokosc.setText("cm");
                jednostkaGestosc.setText(Html.fromHtml("kg/m<sup><small><small>3</small></small></sup>"));
                jednostkaPowierzchnia1.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaPowierzchnia2.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaPrzesuniecie.setText("cm");
                jednostkaSila1.setText("N");
                jednostkaSila2.setText("N");
                gestosc.setText("");
                wysokosc.setText("");
                cisnienie.setText("");
                powierzchnia1.setText("");
                powierzchnia2.setText("");
                przesuniecie.setText("");
                sila1.setText("");
                sila2.setText("");
                gestoscWynik="kg/m";
                wysokoscWynik="cm";
                cisnienieWynik="cm";
                powierzchniaWynik="cm";
                przesuniecieWynik="cm";
                silaWynik="N";
            }
        });
        checkBoxPrzesuniecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPowierzchnia.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxGestosc.setChecked(false);
                if(checkBoxPrzesuniecie.isChecked()){
                    jednostkaWynik.setText(przesuniecieWynik);
                }
                else{
                    przesuniecieWynik = jednostkaWynik.getText().toString();
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPowierzchnia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzesuniecie.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxGestosc.setChecked(false);
                if(checkBoxPowierzchnia.isChecked()){
                    if(powierzchniaWynik.equals("a")||powierzchniaWynik.equals("ha")){
                        jednostkaWynik.setText(powierzchniaWynik);
                    }
                    else {
                        jednostkaWynik.setText(Html.fromHtml(powierzchniaWynik + "<sup><small><small>2<small></small></sup>"));
                    }
                }
                else{
                    powierzchniaWynik = jednostkaWynik.getText().toString();
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPowierzchnia.setChecked(false);
                checkBoxPrzesuniecie.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxGestosc.setChecked(false);
                if(checkBoxSila.isChecked()){
                    jednostkaWynik.setText(silaWynik);
                }
                else{
                    silaWynik = jednostkaWynik.getText().toString();
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxCisnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPowierzchnia.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzesuniecie.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxGestosc.setChecked(false);
                if(checkBoxCisnienie.isChecked()){
                    jednostkaWynik.setText(cisnienieWynik);
                }
                else{
                    cisnienieWynik = jednostkaWynik.getText().toString();
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPowierzchnia.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxPrzesuniecie.setChecked(false);
                checkBoxGestosc.setChecked(false);
                if(checkBoxWysokosc.isChecked()){
                    jednostkaWynik.setText(wysokoscWynik);
                }
                else{
                    wysokoscWynik = jednostkaWynik.getText().toString();
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxGestosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPowierzchnia.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxPrzesuniecie.setChecked(false);
                if(checkBoxGestosc.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml(gestoscWynik+"<sup><small><small>3</small></small></sup>"));
                }
                else{
                    gestoscWynik = jednostkaWynik.getText().toString();
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        mdrawerLayout = findViewById(R.id.drawerCisnienieHydro_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(CisnienieHydri.this, Konto.class);
                i.putExtra("miejsce", "CisnienieHydro");
                startActivity(i);
                Animatoo.animateFade(CisnienieHydri.this);
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
    String ktory="";
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.jednostkaCisnienieCisnienieHydro){
            ktory="cisnienie";
            getMenuInflater().inflate(R.menu.cisnienie_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaWysokoscCisnienieHydro){
            ktory= "wysokosc";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPrzesuniecieCisnienieHydro){
            ktory = "przesuniecie";
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPowierzchnia1CisnienieHydro){
            ktory="powierzchnia1";
            getMenuInflater().inflate(R.menu.pole_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPowierzchnia2CisnienieHydro){
            ktory="powierzchnia2";
            getMenuInflater().inflate(R.menu.pole_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaGestoscCisnienieHydro){
            ktory="gestosc";
            getMenuInflater().inflate(R.menu.gestosc_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaSilaNac1CisnienieHydro){
            ktory="silaNac1";
            getMenuInflater().inflate(R.menu.sila_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaSilaNac2CisnienieHydro){
            ktory="silaNac2";
            getMenuInflater().inflate(R.menu.sila_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaCisnienieAtmCisnienieHydro){
            ktory="cisnienieAtm";
            getMenuInflater().inflate(R.menu.cisnienie_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaMasaCisnienieHydro){
            ktory="masa";
            getMenuInflater().inflate(R.menu.example_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaWynikCisnienieHydro){
            ktory = "wynik";
            if(checkBoxWysokosc.isChecked()){
                getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            }
            else if(checkBoxSila.isChecked()){
                getMenuInflater().inflate(R.menu.sila_menu, menu);
            }
            else if(checkBoxPrzesuniecie.isChecked()){
                getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            }
            else if(checkBoxPowierzchnia.isChecked()){
                getMenuInflater().inflate(R.menu.pole_menu, menu);
            }
            else if(checkBoxGestosc.isChecked()){
                getMenuInflater().inflate(R.menu.gestosc_menu, menu);
            }
            else if(checkBoxCisnienie.isChecked()){
                getMenuInflater().inflate(R.menu.cisnienie_menu, menu);
            }
        }
    }
    String przesuniecieWynik="cm", wysokoscWynik="cm", gestoscWynik="kg/m",powierzchniaWynik="cm", cisnienieWynik="Pa", silaWynik="N";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("przesuniecie")){
                    jednostkaPrzesuniecie.setText("cm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("cm");
                }
                else{
                    if(checkBoxPrzesuniecie.isChecked()){
                        jednostkaWynik.setText("cm");
                        przesuniecieWynik="cm";
                    }
                    else{
                        jednostkaWynik.setText("cm");
                        wysokoscWynik="cm";
                    }
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("przesuniecie")){
                    jednostkaPrzesuniecie.setText("dm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("dm");
                }
                else{
                    if(checkBoxPrzesuniecie.isChecked()){
                        jednostkaWynik.setText("dm");
                        przesuniecieWynik="dm";
                    }
                    else{
                        jednostkaWynik.setText("dm");
                        wysokoscWynik="dm";
                    }
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("przesuniecie")){
                    jednostkaPrzesuniecie.setText("m");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("m");
                }
                else{
                    if(checkBoxPrzesuniecie.isChecked()){
                        jednostkaWynik.setText("m");
                        przesuniecieWynik="m";
                    }
                    else{
                        jednostkaWynik.setText("m");
                        wysokoscWynik="m";
                    }
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("przesuniecie")){
                    jednostkaPrzesuniecie.setText("km");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("km");
                }
                else{
                    if(checkBoxPrzesuniecie.isChecked()){
                        jednostkaWynik.setText("km");
                        przesuniecieWynik="km";
                    }
                    else{
                        jednostkaWynik.setText("km");
                        wysokoscWynik="km";
                    }
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("powierzchnia1")){
                    jednostkaPowierzchnia1.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                else if(ktory.equals("powierzchnia2")){
                    jednostkaPowierzchnia2.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                else{
                    powierzchniaWynik = "mm";
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("powierzchnia1")){
                    jednostkaPowierzchnia1.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                else if(ktory.equals("powierzchnia2")){
                    jednostkaPowierzchnia2.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                else{
                    powierzchniaWynik = "cm";
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("powierzchnia1")){
                    jednostkaPowierzchnia1.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                else if(ktory.equals("powierzchnia2")){
                    jednostkaPowierzchnia2.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                else{
                    powierzchniaWynik = "dm";
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("powierzchnia1")){
                    jednostkaPowierzchnia1.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                else if(ktory.equals("powierzchnia2")){
                    jednostkaPowierzchnia2.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                else{
                    powierzchniaWynik = "m";
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("powierzchnia1")){
                    jednostkaPowierzchnia1.setText("a");
                }
                else if(ktory.equals("powierzchnia2")){
                    jednostkaPowierzchnia2.setText("a");
                }
                else{
                    powierzchniaWynik = "a";
                    jednostkaWynik.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("powierzchnia1")){
                    jednostkaPowierzchnia1.setText("ha");
                }
                else if(ktory.equals("powierzchnia2")){
                    jednostkaPowierzchnia2.setText("ha");
                }
                else{
                    powierzchniaWynik = "ha";
                    jednostkaWynik.setText("ha");
                }
                Toast.makeText(this, "Hektar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.miliniuton:
                if(ktory.equals("silaNac1")){
                    jednostkaSila1.setText("mN");
                }
                else if(ktory.equals("silaNac2")){
                    jednostkaSila2.setText("mN");
                }
                else{
                    silaWynik = "mN";
                    jednostkaWynik.setText("mN");
                }
                Toast.makeText(this, "Miliniuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.niuton:
                if(ktory.equals("silaNac1")){
                    jednostkaSila1.setText("N");
                }
                else if(ktory.equals("silaNac2")){
                    jednostkaSila2.setText("N");
                }
                else{
                    silaWynik = "N";
                    jednostkaWynik.setText("N");
                }
                Toast.makeText(this, "Niuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kiloniuton:
                if(ktory.equals("silaNac1")){
                    jednostkaSila1.setText("kN");
                }
                else if(ktory.equals("silaNac2")){
                    jednostkaSila2.setText("kN");
                }
                else{
                    silaWynik = "kN";
                    jednostkaWynik.setText("kN");
                }
                Toast.makeText(this, "Kiloniuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.meganiuton2:
                if(ktory.equals("silaNac1")){
                    jednostkaSila1.setText("MN");
                }
                else if(ktory.equals("silaNac2")){
                    jednostkaSila2.setText("MN");
                }
                else{
                    silaWynik = "MN";
                    jednostkaWynik.setText("MN");
                }
                Toast.makeText(this, "Meganiuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.paskal:
                if(ktory.equals("cisnienie")){
                    jednostkaCisnienie.setText("Pa");
                }
                else if(ktory.equals("cisnienieAtm")){
                    jednostkaCisnienieAtm.setText("Pa");
                }
                else{
                    cisnienieWynik = "Pa";
                    jednostkaWynik.setText("Pa");
                }
                Toast.makeText(this, "Paskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.hektopaskal:
                if(ktory.equals("cisnienie")){
                    jednostkaCisnienie.setText("hPa");
                }
                else if(ktory.equals("cisnienieAtm")){
                    jednostkaCisnienieAtm.setText("hPa");
                }
                else{
                    cisnienieWynik="hPa";
                    jednostkaWynik.setText("hPa");
                }
                Toast.makeText(this, "Hektopaskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kilopaskal:
                if(ktory.equals("cisnienie")){
                    jednostkaCisnienie.setText("kPa");
                }
                else if(ktory.equals("cisnienieAtm")){
                    jednostkaCisnienieAtm.setText("kPa");
                }
                else{
                    cisnienieWynik = "kPa";
                    jednostkaWynik.setText("kPa");
                }
                Toast.makeText(this, "Kilopaskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.atmosfera:
                if(ktory.equals("cisnienie")){
                    jednostkaCisnienie.setText("atm");
                }
                else if(ktory.equals("cisnienieAtm")){
                    jednostkaCisnienieAtm.setText("atm");
                }
                else{
                    cisnienieWynik = "atm";
                    jednostkaWynik.setText("atm");
                }
                Toast.makeText(this, "Atmosfera", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bar:
                if(ktory.equals("cisnienie")){
                    jednostkaCisnienie.setText("b");
                }
                else if(ktory.equals("cisnienieAtm")){
                    jednostkaCisnienieAtm.setText("b");
                }
                else{
                    cisnienieWynik = "b";
                    jednostkaWynik.setText("b");
                }
                Toast.makeText(this, "Bar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.megapaskal:
                if(ktory.equals("cisnienie")){
                    jednostkaCisnienie.setText("MPa");
                }
                else if(ktory.equals("cisnienieAtm")){
                    jednostkaCisnienieAtm.setText("MPa");
                }
                else{
                    cisnienieWynik = "MPa";
                    jednostkaWynik.setText("MPa");
                }
                Toast.makeText(this, "Megapaskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.GnaCM3:
                if(ktory.equals("gestosc")){
                    jednostkaGestosc.setText(Html.fromHtml("g/cm<sup><small><small>3</small></small></sup>"));
                }
                else {
                    gestoscWynik = "g/cm";
                    jednostkaWynik.setText(Html.fromHtml("g/cm<sup><small><small>3</small></small></sup>"));
                }
                Toast.makeText(this, "Gramy na centymetr sześcienny", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.GnaDM3:
                if(ktory.equals("gestosc")){
                    jednostkaGestosc.setText(Html.fromHtml("g/dm<sup><small><small>3</small></small></sup>"));
                }
                else {
                    gestoscWynik = "g/dm";
                    jednostkaWynik.setText(Html.fromHtml("g/dm<sup><small><small>3</small></small></sup>"));
                }
                Toast.makeText(this, "Gramy na decymetr sześcienny", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KGnaCM3:
                if(ktory.equals("gestosc")){
                    jednostkaGestosc.setText(Html.fromHtml("kg/cm<sup><small><small>3</small></small></sup>"));
                }
                else {
                    gestoscWynik = "kg/cm";
                    jednostkaWynik.setText(Html.fromHtml("kg/cm<sup><small><small>3</small></small></sup>"));
                }
                Toast.makeText(this, "Kilogramy na centymetr sześcienny", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KGnaDM3:
                if(ktory.equals("gestosc")){
                    jednostkaGestosc.setText(Html.fromHtml("kg/dm<sup><small><small>3</small></small></sup>"));
                }
                else {
                    gestoscWynik = "kg/dm";
                    jednostkaWynik.setText(Html.fromHtml("kg/dm<sup><small><small>3</small></small></sup>"));
                }
                Toast.makeText(this, "Kilogramy na decymetr sześcienny", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KGnaM3:
                if(ktory.equals("gestosc")){
                    jednostkaGestosc.setText(Html.fromHtml("kg/m<sup><small><small>3</small></small></sup>"));
                }
                else {
                    gestoscWynik = "kg/m";
                    jednostkaWynik.setText(Html.fromHtml("kg/m<sup><small><small>3</small></small></sup>"));
                }
                Toast.makeText(this, "Kilogramy na metr sześcienny", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option2:
                jednostkaMasa.setText("g");
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                jednostkaMasa.setText("dag");
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option4:
                jednostkaMasa.setText("kg");
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option5:
                jednostkaMasa.setText("t");
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT).show();
                return true;


        }
        return super.onContextItemSelected(item);
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
        adapter = new CustomExpandableListAdapter(CisnienieHydri.this, lstTitle, lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition)))).get(childPosition).toString();
                if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(CisnienieHydri.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(CisnienieHydri.this);
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
            Intent i = new Intent(CisnienieHydri.this, FizykaKalkulator.class);
            startActivity(i);
            Animatoo.animateFade(CisnienieHydri.this);
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