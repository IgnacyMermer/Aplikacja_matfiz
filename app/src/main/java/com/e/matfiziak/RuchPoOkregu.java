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

import static java.lang.Math.sqrt;

public class RuchPoOkregu extends AppCompatActivity {
    TextView jednostkaPromien;
    TextView jednostkaOkres;
    TextView jednostkaCzas;
    TextView jednostkaPredkosc;
    TextView jednostkaPrzyszpieszenie;
    TextView jednostkaMasa;
    TextView jednostkaWynik;
    String ktory="", ktory2="";
    CheckBox checkBoxPredkosc;
    CheckBox checkBoxPromien;
    CheckBox checkboxCzas;
    CheckBox checkboxCzestotliwosc;
    CheckBox checkboxOkres;
    CheckBox checkBoxSila;
    CheckBox checkBoxPrzyszpieszenie;
    CheckBox checkBoxOkress;
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
        setContentView(R.layout.activity_ruch_po_okregu);
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
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        if(checkbox==null){
            checkbox="";
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        doWyslania = new ArrayList<String>();
        checkBoxPredkosc = findViewById(R.id.predkoscWybor);
        checkBoxPromien = findViewById(R.id.promienWybor);
        checkboxCzas = findViewById(R.id.czasWybor);
        checkboxCzestotliwosc = findViewById(R.id.czestotliwoscWybor);
        checkboxOkres = findViewById(R.id.okresWybor);
        checkBoxSila = findViewById(R.id.checkboxSila);
        checkBoxPrzyszpieszenie = findViewById(R.id.checkBoxPrzyszpieszenie);
        checkBoxOkress = findViewById(R.id.checkBoxOkress);
        if(checkbox.equals("promien")){
            checkBoxPromien.setChecked(true);
        }
        else if(checkbox.equals("przyszpieszenie")){
            checkBoxPrzyszpieszenie.setChecked(true);
        }
        else if(checkbox.equals("predkosc")){
            checkBoxPredkosc.setChecked(true);
        }
        else if(checkbox.equals("czestotliwosc")){
            checkboxCzestotliwosc.setChecked(true);
        }
        else if(checkbox.equals("okress")){
            checkBoxOkress.setChecked(true);
        }
        else if(checkbox.equals("okres")){
            checkboxOkres.setChecked(true);
        }
        else if(checkbox.equals("sila")){
            checkBoxSila.setChecked(true);
        }
        else if(checkbox.equals("czas")){
            checkboxCzas.setChecked(true);
        }
        jednostkaPromien = findViewById(R.id.jednostkaPromien);
        jednostkaOkres = findViewById(R.id.jednostkaOkres);
        jednostkaCzas = findViewById(R.id.jednostkaCzas);
        jednostkaPredkosc = findViewById(R.id.jednostkaPredkosc);
        jednostkaPrzyszpieszenie = findViewById(R.id.jednostkaPrzyszpieszenie);
        jednostkaMasa = findViewById(R.id.jednostkaMasa);
        jednostkaWynik = findViewById(R.id.jednostkaWynik);
        jednostkaPrzyszpieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        jednostkaPromien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPromien);
                openContextMenu(v);
            }
        });
        jednostkaOkres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktory="okres";
                registerForContextMenu(jednostkaOkres);
                openContextMenu(v);
            }
        });
        jednostkaCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktory="czas";
                registerForContextMenu(jednostkaCzas);
                openContextMenu(v);
            }
        });
        jednostkaPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPredkosc);
                openContextMenu(v);
            }
        });
        jednostkaPrzyszpieszenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzyszpieszenie);
                openContextMenu(v);
            }
        });
        jednostkaMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMasa);
                openContextMenu(v);
            }
        });
        checkBoxPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxOkress.setChecked(false);
                jednostkaWynik.setText("m/s");
                ktory2="predkosc";
                if(checkBoxPredkosc.isChecked()){
                    jednostkaWynik.setText("m/s");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPromien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPredkosc.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxOkress.setChecked(false);
                jednostkaWynik.setText("m");
                ktory2="promien";
                if(checkBoxPromien.isChecked()){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxOkress.setChecked(false);
                jednostkaWynik.setText("s");
                ktory2="czas";
                if(checkboxCzas.isChecked()){
                    jednostkaWynik.setText("s");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxCzestotliwosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxOkress.setChecked(false);
                jednostkaWynik.setText("Hz");
                if(checkboxCzestotliwosc.isChecked()){
                    jednostkaWynik.setText("Hz");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxOkres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxOkress.setChecked(false);
                jednostkaWynik.setText("kg");
                ktory2="masa";
                if(checkboxOkres.isChecked()){
                    jednostkaWynik.setText("kg");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxOkress.setChecked(false);
                jednostkaWynik.setText("N");
                if(checkBoxSila.isChecked()){
                    jednostkaWynik.setText("N");
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
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxSila.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxOkress.setChecked(false);
                if(checkBoxPrzyszpieszenie.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxOkress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxSila.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                ktory2="okres";
                if(checkBoxOkress.isChecked()){
                    jednostkaWynik.setText("s");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        final TextView wynik = findViewById(R.id.wynik);
        Button button = findViewById(R.id.button);
        final EditText promien = findViewById(R.id.promien);
        final EditText obwod = findViewById(R.id.obwod);
        final EditText predkosc = findViewById(R.id.predkosc);
        final EditText czas = findViewById(R.id.czas);
        final EditText okres = findViewById(R.id.okres);
        final EditText czestotliwosc = findViewById(R.id.czestotliwosc);
        final EditText liczbaOkrazen = findViewById(R.id.liczbaOkrazen);
        final EditText sila = findViewById(R.id.sila2);
        final EditText przyszpieszenie = findViewById(R.id.przyszpieszenie2);
        final EditText masa = findViewById(R.id.masa);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPoOkregu);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaPoOkregu);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPoOkregu);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaPoOkregu);
        if(incomingList!=null){
            promien.setText(incomingList.get(0));
            obwod.setText(incomingList.get(1));
            czestotliwosc.setText(incomingList.get(2));
            okres.setText(incomingList.get(3));
            czas.setText(incomingList.get(4));
            predkosc.setText(incomingList.get(5));
            przyszpieszenie.setText(incomingList.get(6));
            sila.setText(incomingList.get(7));
            masa.setText(incomingList.get(8));
            liczbaOkrazen.setText(incomingList.get(9));
            pierwszaLinia.setText(incomingList.get(10));
            drugaLinia.setText(incomingList.get(11));
            trzeciaLinia.setText(incomingList.get(12));
            czwartaLinia.setText(incomingList.get(13));
            wynik.setText(incomingList.get(14));
            jednostkaWynik.setText(incomingList.get(15));
        }
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(promien.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(czestotliwosc.getText().toString());
                doWyslania.add(okres.getText().toString());
                doWyslania.add(czas.getText().toString());
                doWyslania.add(predkosc.getText().toString());
                doWyslania.add(przyszpieszenie.getText().toString());
                doWyslania.add(sila.getText().toString());
                doWyslania.add(masa.getText().toString());
                doWyslania.add(liczbaOkrazen.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxPrzyszpieszenie.isChecked()){
                    ktoryCheckbox="przyszpieszenie";
                }
                else if(checkBoxSila.isChecked()){
                    ktoryCheckbox="sila";
                }
                else if(checkBoxOkress.isChecked()){
                    ktoryCheckbox="okress";
                }
                else if(checkboxCzestotliwosc.isChecked()){
                    ktoryCheckbox="czestotliwosc";
                }
                else if(checkboxOkres.isChecked()){
                    ktoryCheckbox="okres";
                }
                else if(checkBoxPredkosc.isChecked()){
                    ktoryCheckbox = "predkosc";
                }
                else if(checkBoxPromien.isChecked()){
                    ktoryCheckbox="promien";
                }
                else if(checkboxCzas.isChecked()){
                    ktoryCheckbox="czas";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(RuchPoOkregu.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "RuchPoOkregu");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(RuchPoOkregu.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(RuchPoOkregu.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "RuchPoOkregu");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(RuchPoOkregu.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "RuchPoOkregu");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(RuchPoOkregu.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "RuchPoOkregu");
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
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkboxCzas.isChecked()||checkboxCzestotliwosc.isChecked()||checkboxOkres.isChecked()||checkBoxPredkosc.isChecked() ||checkBoxPromien.isChecked()
                ||checkBoxPrzyszpieszenie.isChecked()||checkBoxSila.isChecked()||checkBoxOkress.isChecked()){
                    try {
                        String a=obwod.getText().toString();
                        String b=predkosc.getText().toString();
                        String c=czas.getText().toString();
                        String d=okres.getText().toString();
                        String e=czestotliwosc.getText().toString();
                        String f=promien.getText().toString();
                        String g=liczbaOkrazen.getText().toString();
                        String h=sila.getText().toString();
                        String i=przyszpieszenie.getText().toString();
                        String j=masa.getText().toString();
                        if(checkBoxPromien.isChecked()){
                            Double r=null;
                            if(!obwod.getText().toString().equals("")){
                                Double ob = Double.parseDouble(a);
                                ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaPromien.getText().toString());
                                pierwszaLinia.setText(Html.fromHtml("Ob=2*&#960;*r"));
                                drugaLinia.setText(Html.fromHtml("r=Ob/(2*&#960;)"));
                                trzeciaLinia.setText("r="+ob+"/(2*3.14)");
                                czwartaLinia.setText("");
                                r=(ob/2)/3.14;
                            }
                            else if(!predkosc.getText().toString().equals("")){
                                if(!czas.getText().toString().equals("")){
                                    Double t = Double.parseDouble(c);
                                    t = funkcjePrzelicznikowe.czas(t, jednostkaCzas.getText().toString());
                                    Double pre = Double.parseDouble(b);
                                    pre = funkcjePrzelicznikowe.predkosc(pre, jednostkaPredkosc.getText().toString());
                                    pierwszaLinia.setText("Ob=t*v");
                                    drugaLinia.setText("Ob="+t+"*"+pre);
                                    trzeciaLinia.setText(Html.fromHtml("r=Ob/(2*&#960;)"));
                                    Double s = t*pre;
                                    czwartaLinia.setText(Html.fromHtml("r="+s+"/(2*3.14)"));
                                    r = (s/2)/3.14;
                                }
                                else if(!czestotliwosc.getText().toString().equals("")){
                                    Double czes = Double.parseDouble(e);
                                    Double pre = Double.parseDouble(b);
                                    pre = funkcjePrzelicznikowe.predkosc(pre, jednostkaPredkosc.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("r=v/(2*&#960;*&#956;)"));
                                    drugaLinia.setText("r="+pre+"/(2*3.14*"+czes+")");
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    r = pre/(2*3.14*czes);
                                }
                                else if(!i.equals("")){
                                    Double przys = Double.parseDouble(i);
                                    Double pre = Double.parseDouble(b);
                                    pre = funkcjePrzelicznikowe.predkosc(pre, jednostkaPredkosc.getText().toString());
                                    r = (pre*pre)/przys;
                                }
                            }
                            else if((!j.equals(""))&&(!e.equals(""))&&(!h.equals(""))){
                                Double mas = Double.parseDouble(j);
                                mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                Double sil = Double.parseDouble(h);
                                Double czes = Double.parseDouble(e);
                                pierwszaLinia.setText(Html.fromHtml("r=F/(2<sup><small><small>2</small></small></sup>*m*&#960;<sup><small><small>2</small></small></sup>*&#956;<sup><small><small>2</small></small></sup>)"));
                                drugaLinia.setText(Html.fromHtml("r="+sil+"/(2<sup><small><small>2</small></small></sup>*"+mas+"*9.86*"+czes+"<sup><small><small>2</small></small></sup>)"));
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                r=sil/(4*mas*9.86*czes*czes);
                            }
                            else{
                                wyczyscLinie();
                            }
                            r = funkcjePrzelicznikowe.dlugoscWynik(r, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(r);
                            wynik.setText(x);
                        }
                        else if(checkboxCzas.isChecked()){
                            Double t = null;
                            if(!predkosc.getText().toString().equals("")){
                                if(!obwod.getText().toString().equals("")){
                                    Double ob = Double.parseDouble(a);
                                    ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaPromien.getText().toString());
                                    Double pre = Double.parseDouble(b);
                                    pre = funkcjePrzelicznikowe.predkosc(pre, jednostkaPredkosc.getText().toString());
                                    pierwszaLinia.setText("t=Ob/v");
                                    drugaLinia.setText("t="+ob+"/"+pre);
                                    t = ob / pre;
                                }
                                else if(!promien.getText().toString().equals("")){
                                    Double pre = Double.parseDouble(b);
                                    pre = funkcjePrzelicznikowe.predkosc(pre, jednostkaPredkosc.getText().toString());
                                    Double r = Double.parseDouble(f);
                                    r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                    pierwszaLinia.setText("t=Ob/v");
                                    drugaLinia.setText(Html.fromHtml("t=(2*&#960;*r)/v"));
                                    trzeciaLinia.setText("t=(2*3.14*"+r+")/"+pre);
                                    czwartaLinia.setText("");
                                    t = (2*3.14*r)/pre;
                                }
                            }
                            else if(!liczbaOkrazen.getText().toString().equals("")){
                                if(!e.equals("")){
                                    Double liczba = Double.parseDouble(g);
                                    Double czes = Double.parseDouble(e);
                                    pierwszaLinia.setText(Html.fromHtml("t=n/&#956;"));
                                    drugaLinia.setText("t="+liczba+"/"+czes);
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    t = liczba/czes;
                                }
                                else if(!okres.getText().toString().equals("")){
                                    Double liczba = Double.parseDouble(g);
                                    Double okres2 = Double.parseDouble(okres.getText().toString());
                                    pierwszaLinia.setText("t=n*T");
                                    drugaLinia.setText("t="+liczba+"*"+okres2);
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    t=liczba*okres2;
                                }
                            }
                            else if(!e.equals("")){
                                Double czes = Double.parseDouble(e);
                                pierwszaLinia.setText(Html.fromHtml("T=1/&#956;"));
                                drugaLinia.setText("T=1/"+czes);
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                t = 1/czes;
                            }
                            t = funkcjePrzelicznikowe.czasWynik(t, jednostkaCzas.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(t);
                            wynik.setText(x);
                        }
                        else if(checkboxCzestotliwosc.isChecked()){
                            Double czes = null;
                            if(!d.equals("")){
                                Double czas = Double.parseDouble(d);
                                czas = funkcjePrzelicznikowe.czas(czas, jednostkaCzas.getText().toString());
                                pierwszaLinia.setText(Html.fromHtml("&#956;=1/T"));
                                drugaLinia.setText(Html.fromHtml("&#956;=1/"+czas));
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                czes=1/czas;
                            }
                            else if(!g.equals("")){
                                if(!c.equals("")){
                                    Double liczba = Double.parseDouble(g);
                                    Double czasss = Double.parseDouble(c);
                                    czasss = funkcjePrzelicznikowe.czas(czasss, jednostkaCzas.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("&#956;=n/t"));
                                    drugaLinia.setText(Html.fromHtml("&#956;="+liczba+"/"+czasss));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    czes = liczba/czasss;
                                }
                            }
                            else if(!b.equals("")){
                                Double pred = Double.parseDouble(b);
                                pred = funkcjePrzelicznikowe.predkosc(pred, jednostkaPredkosc.getText().toString());
                                if(!a.equals("")){
                                    Double ob = Double.parseDouble(a);
                                    ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaPromien.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("&#956;=v/Ob"));
                                    drugaLinia.setText(Html.fromHtml("&#956;="+pred+"/"+ob));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    czes = pred/ob;
                                }
                                else if(!f.equals("")){
                                    Double r = Double.parseDouble(f);
                                    r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("&#956;=v/(2*&#960;*r)"));
                                    drugaLinia.setText(Html.fromHtml("&#956;="+pred+"/(2*3.14*"+r+")"));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    czes=pred/(2*3.14*r);
                                }
                            }
                            else{
                                wyczyscLinie();
                            }
                            String x = funkcjePrzelicznikowe.intowanie(czes);
                            wynik.setText(x);
                        }
                        else if(checkBoxPredkosc.isChecked()){
                            Double pred = null;
                            if(!e.equals("")){
                                Double czas = Double.parseDouble(e);
                                if(!a.equals("")){
                                    Double ob = Double.parseDouble(a);
                                    ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaPromien.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("v=Ob*&#956;"));
                                    drugaLinia.setText("v="+ob+"*"+czas);
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    pred = ob*czas;
                                }
                                else if(!f.equals("")){
                                    Double r = Double.parseDouble(f);
                                    r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("v=Ob*&#956;"));
                                    drugaLinia.setText(Html.fromHtml("v=(2*&#960;*r)*&#956;"));
                                    trzeciaLinia.setText("v=(2*3.14*"+r+")*"+czas);
                                    czwartaLinia.setText("");
                                    pred= (2*3.14*r)*czas;
                                }
                            }
                            else if((!promien.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))&&(!liczbaOkrazen.getText().toString().equals(""))){
                                Double r = Double.parseDouble(promien.getText().toString());
                                r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                Double t = Double.parseDouble(czas.getText().toString());
                                t = funkcjePrzelicznikowe.czas(t, jednostkaCzas.getText().toString());
                                Double n = Double.parseDouble(liczbaOkrazen.getText().toString());
                                pierwszaLinia.setText(Html.fromHtml("&#956;=n/t"));
                                Double czes = n/t;
                                drugaLinia.setText(Html.fromHtml("v=Ob*&#956;"));
                                trzeciaLinia.setText(Html.fromHtml("v=(2*&#960;*r)*&#956;)"));
                                czwartaLinia.setText(Html.fromHtml("v=(2*3.14*"+r+")*"+czes));
                                pred = 2*3.14*r*czes;
                            }
                            else if(!i.equals("")){
                                if(!f.equals("")){
                                    Double r = Double.parseDouble(f);
                                    r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                    Double przys = Double.parseDouble(i);
                                    Double wyn = r*przys;
                                    pierwszaLinia.setText(Html.fromHtml("v=&#8730;(r*a)"));
                                    drugaLinia.setText(Html.fromHtml("v=&#8730;("+r+"*"+a+")"));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    wyn = sqrt(wyn);
                                    pred = wyn;
                                }
                            }
                            else if(!h.equals("")){
                                if(!j.equals("")){
                                    if(!f.equals("")){
                                        Double mas = Double.parseDouble(j);
                                        mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                        Double r = Double.parseDouble(f);
                                        r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                        Double sil = Double.parseDouble(h);
                                        Double wyn = (sil*r)/mas;
                                        pierwszaLinia.setText(Html.fromHtml("v=&#8730;(F*r/m)"));
                                        drugaLinia.setText(Html.fromHtml("v=&#8730;("+sil+"*"+r+"/"+mas+")"));
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        wyn = sqrt(wyn);
                                        pred=wyn;
                                    }
                                    else if(!c.equals("")){
                                        Double mas = Double.parseDouble(j);
                                        mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                        Double czas = Double.parseDouble(c);
                                        czas = funkcjePrzelicznikowe.czas(czas, jednostkaCzas.getText().toString());
                                        Double sil = Double.parseDouble(h);
                                        pierwszaLinia.setText("v=F*t/m");
                                        drugaLinia.setText("v="+sil+"*"+czas+"/"+mas);
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        Double wyn = sil/mas*czas;
                                    }
                                }
                            }
                            else if(!c.equals("")){
                                if(!f.equals("")){
                                    Double czas  =Double.parseDouble(c);
                                    czas = funkcjePrzelicznikowe.czas(czas, jednostkaCzas.getText().toString());
                                    Double promien = Double.parseDouble(f);
                                    promien = funkcjePrzelicznikowe.dlugosc(promien, jednostkaPromien.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("v=2*&#960;*r/t"));
                                    drugaLinia.setText("v=2*3.14*"+promien+"/"+czas);
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    Double r = 2*3.14*promien/czas;
                                    pred=r;
                                }
                            }
                            else{
                                wyczyscLinie();
                            }
                            pred = funkcjePrzelicznikowe.predkoscWynik(pred, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(pred);
                            wynik.setText(x);
                        }
                        else if(checkBoxPrzyszpieszenie.isChecked()){
                                Double przysz=null;
                                if(!b.equals("")){
                                    if(!f.equals("")){
                                        Double pred = Double.parseDouble(b);
                                        pred = funkcjePrzelicznikowe.predkosc(pred, jednostkaPredkosc.getText().toString());
                                        Double r = Double.parseDouble(f);
                                        r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                        pierwszaLinia.setText(Html.fromHtml("a=v<sup><small><small>2</small></small></sup>/r"));
                                        drugaLinia.setText(Html.fromHtml("a="+pred+"<sup><small><small>2</small></small></sup>/"+r));
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        przysz = pred*pred/r;
                                    }
                                }
                                else if(!h.equals("")){
                                    if(!j.equals("")){
                                        Double sil= Double.parseDouble(h);
                                        Double mas = Double.parseDouble(j);
                                        mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                        pierwszaLinia.setText("a=F/m");
                                        drugaLinia.setText("a="+sil+"/"+mas);
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        przysz = sil/mas;
                                    }
                                }
                                else if(!e.equals("")){
                                    if(!f.equals("")){
                                        Double czes = Double.parseDouble(e);
                                        Double r = Double.parseDouble(f);
                                        r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                        pierwszaLinia.setText(Html.fromHtml("a=2<sup><small><small>2</small></small></sup>*&#960;<sup><small><small>2</small></small></sup>*&#956;<sup><small><small>2</small></small></sup>*r"));
                                        drugaLinia.setText(Html.fromHtml("a=4*9.86*"+czes+"<sup><small><small>2</small></small></sup>*r"));
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        przysz = 4*9.86*r*czes*czes;
                                    }
                                }
                                else if((!c.equals(""))&&(!f.equals(""))){
                                    Double r = Double.parseDouble(f);
                                    r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                    Double czas = Double.parseDouble(c);
                                    czas = funkcjePrzelicznikowe.czas(czas, jednostkaCzas.getText().toString());
                                    pierwszaLinia.setText(Html.fromHtml("a=2<sup><small><small>2</small></small></sup>*&#960;<sup><small><small>2</small></small></sup>*r/t<sup><small><small>2</small></small></sup>"));
                                    drugaLinia.setText(Html.fromHtml("4*9.86*"+r+"/"+czas+"<sup><small><small>2</small></small></sup>"));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    przysz = 3.14*3.14*4*r/czas/czas;
                                }
                                else{
                                    wyczyscLinie();
                                }
                                String x = funkcjePrzelicznikowe.intowanie(przysz);
                                wynik.setText(x);
                            }
                        else if(checkboxOkres.isChecked()){
                                Double masa = null;
                                if(!i.equals("")){
                                    if(!h.equals("")){
                                        Double sil = Double.parseDouble(h);
                                        Double przys = Double.parseDouble(i);
                                        pierwszaLinia.setText("m=F/a");
                                        drugaLinia.setText("m="+sil+"/"+przys);
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        masa = sil/przys;
                                    }
                                }
                                else if(!b.equals("")){
                                    if(!f.equals("")){
                                        if(!h.equals("")){
                                            Double pred = Double.parseDouble(b);
                                            pred = funkcjePrzelicznikowe.predkosc(pred, jednostkaPredkosc.getText().toString());
                                            Double r = Double.parseDouble(f);
                                            r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                            Double sil = Double.parseDouble(h);
                                            pierwszaLinia.setText(Html.fromHtml("m=F*r/v<sup><small><small>2</small></small></sup>"));
                                            drugaLinia.setText(Html.fromHtml("m="+sil+"*"+r+"/"+pred+"<sup><small><small>2</small></small></sup>"));
                                            trzeciaLinia.setText("");
                                            czwartaLinia.setText("");
                                            masa = (sil * r)/(pred*pred);
                                        }
                                    }
                                }
                                else{
                                    wyczyscLinie();
                                }
                                masa = funkcjePrzelicznikowe.masaWynik(masa, jednostkaWynik.getText().toString());
                                String x = funkcjePrzelicznikowe.intowanie(masa);
                                wynik.setText(x);
                            }
                         else if(checkBoxSila.isChecked()){
                            Double sila1 = null;
                            if(!i.equals("")){
                                if(!j.equals("")){
                                    Double mas = Double.parseDouble(j);
                                    mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                    Double przys = Double.parseDouble(i);
                                    pierwszaLinia.setText("F=m*a");
                                    drugaLinia.setText("F="+mas+"*"+przys);
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    sila1 = mas*przys;
                                }
                            }
                            else if(!b.equals("")){
                                if(!f.equals("")){
                                    if(!j.equals("")){
                                        Double mas = Double.parseDouble(j);
                                        mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                        Double r = Double.parseDouble(f);
                                        r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                        Double pred = Double.parseDouble(b);
                                        pred = funkcjePrzelicznikowe.predkosc(pred, jednostkaPredkosc.getText().toString());
                                        pierwszaLinia.setText(Html.fromHtml("F=m*v<sup><small><small>2</small></small></sup>/r"));
                                        drugaLinia.setText(Html.fromHtml("F="+mas+"*"+pred+"<sup><small><small>2</small></small></sup>/"+r));
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        sila1 = (mas*pred*pred)/r;
                                    }
                                }
                            }
                            else if(!d.equals("")){
                                if(!j.equals("")){
                                    if(!f.equals("")){
                                        Double mas = Double.parseDouble(j);
                                        mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                        Double r =Double.parseDouble(f);
                                        r = funkcjePrzelicznikowe.dlugosc(r, jednostkaPromien.getText().toString());
                                        Double okresss = Double.parseDouble(d);
                                        Double s=2*3.14*r;
                                        pierwszaLinia.setText("v=Ob/T");
                                        drugaLinia.setText("v="+s+"/"+okresss);
                                        Double pre = s/okresss;
                                        trzeciaLinia.setText(Html.fromHtml("F=m*v<sup><small><small>2</small></small></sup>/r"));
                                        czwartaLinia.setText(Html.fromHtml("F="+mas+"*"+pre+"<sup><small><small>2</small></small></sup>/"+r));
                                        sila1 = mas*pre*pre/r;
                                    }
                                    else if(!a.equals("")){
                                        Double mas = Double.parseDouble(j);
                                        mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                                        Double ob =Double.parseDouble(a);
                                        ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaPromien.getText().toString());
                                        Double okresss = Double.parseDouble(d);
                                        okresss = funkcjePrzelicznikowe.czas(okresss, jednostkaOkres.getText().toString());
                                        pierwszaLinia.setText("v=Ob/T");
                                        drugaLinia.setText("v="+ob+"/"+okresss);
                                        Double pre = ob/okresss;
                                        trzeciaLinia.setText(Html.fromHtml("F=m*v<sup><small><small>2</small></small></sup>/Ob"));
                                        czwartaLinia.setText(Html.fromHtml("F="+mas+"*"+pre+"<sup><small><small>2</small></small></sup>/"+ob));
                                        sila1 = mas*pre*pre/ob;
                                    }
                                }
                            }
                            else{
                                wyczyscLinie();
                            }
                            String x = funkcjePrzelicznikowe.intowanie(sila1);
                            wynik.setText(x);
                        }
                         else if(checkBoxOkress.isChecked()){
                             Double okress = null;
                             if(!e.equals("")){
                                Double czes = Double.parseDouble(e);
                                pierwszaLinia.setText(Html.fromHtml("T=1/&#956;"));
                                drugaLinia.setText("T=1/"+czes);
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                okress = 1/czes;
                            }
                             else if(!liczbaOkrazen.getText().toString().equals("")){
                                if(!czas.getText().toString().equals("")){
                                    Double liczbaOkrazen1 = Double.parseDouble(g);
                                    Double czas1 = Double.parseDouble(czas.getText().toString());
                                    czas1 = funkcjePrzelicznikowe.czas(czas1, jednostkaCzas.getText().toString());
                                    pierwszaLinia.setText("T=t/n");
                                    drugaLinia.setText("T="+czas1+"/"+liczbaOkrazen1);
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    okress = czas1/liczbaOkrazen1;
                                }
                             }
                             else{
                                 wyczyscLinie();
                             }
                             okress = funkcjePrzelicznikowe.czas(okress, jednostkaWynik.getText().toString());
                             String x = funkcjePrzelicznikowe.intowanie(okress);
                             wynik.setText(x);
                        }
                    }
                    catch (Exception ex){
                        wynik.setText("Wpisz liczby");
                    }
                }
            }
        });
        Button czysciciel = findViewById(R.id.czysciciel);
        czysciciel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPromien.setChecked(false);
                checkboxCzas.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxOkres.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxOkress.setChecked(false);
                promien.setText("");
                obwod.setText("");
                sila.setText("");
                czestotliwosc.setText("");
                okres.setText("");
                czas.setText("");
                przyszpieszenie.setText("");
                liczbaOkrazen.setText("");
                masa.setText("");
                predkosc.setText("");
                wynik.setText("");
                jednostkaWynik.setText("");
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        mdrawerLayout = findViewById(R.id.drawerRuchPoOkregu_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(RuchPoOkregu.this, Konto.class);
                i.putExtra("miejsce", "RuchPoOkregu");
                startActivity(i);
                Animatoo.animateFade(RuchPoOkregu.this);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPoOkregu);
            TextView drugaLinia = findViewById(R.id.drugaLiniaPoOkregu);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPoOkregu);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaPoOkregu);
            TextView wynik = findViewById(R.id.wynik);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPoOkregu);
            TextView drugaLinia = findViewById(R.id.drugaLiniaPoOkregu);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPoOkregu);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaPoOkregu);
            TextView wynik = findViewById(R.id.wynik);
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
            if(jednostkaWynikStr.equals("m/s2")){
                jednostkaWynik.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
            }
            else{
                jednostkaWynik.setText(jednostkaWynikStr);
            }
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }
    String ktory3 = "";
    public void wyczyscLinie(){
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaPoOkregu);
        TextView drugaLinia = findViewById(R.id.drugaLiniaPoOkregu);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaPoOkregu);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaPoOkregu);
        TextView wynik = findViewById(R.id.wynik);
        pierwszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.jednostkaPromien){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory3="promien";
        }
        else if(v.getId()==R.id.jednostkaWynik&&checkBoxPromien.isChecked()){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory3="wynik";
        }
        else if(v.getId()==R.id.jednostkaOkres||v.getId()==R.id.jednostkaCzas){
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory3="okres";
        }
        else if(v.getId()==R.id.jednostkaWynik&&(checkBoxOkress.isChecked()||checkboxCzas.isChecked())){
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory3="wynik";
        }
        else if (v.getId() == R.id.jednostkaPredkosc) {
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory3="predkosc";
        }
        else if(v.getId()==R.id.jednostkaWynik&&checkBoxPredkosc.isChecked()){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory3="wynik";
        }
        else if(v.getId()==R.id.jednostkaMasa){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory3="masa";
        }
        else if(v.getId()==R.id.jednostkaWynik&&checkboxOkres.isChecked()){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory3="wynik";
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sekunda2:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("s");
                }
                else {
                    if(ktory.equals("okres")){
                        jednostkaOkres.setText("s");
                    }
                    else if(ktory.equals("czas")){
                        jednostkaCzas.setText("s");
                    }
                }
                Toast.makeText(this, "Sekunda", Toast.LENGTH_SHORT);
                return true;
            case R.id.minuta2:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("min");
                }
                else {
                    if(ktory.equals("okres")){
                        jednostkaOkres.setText("min");
                    }
                    else if(ktory.equals("czas")){
                        jednostkaCzas.setText("min");
                    }
                }
                Toast.makeText(this, "Minuta", Toast.LENGTH_SHORT);
                return true;
            case R.id.godzina2:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("h");
                }
                else {
                    if(ktory.equals("okres")){
                        jednostkaOkres.setText("h");
                    }
                    else if(ktory.equals("czas")){
                        jednostkaCzas.setText("h");
                    }
                }
                Toast.makeText(this, "Godzina", Toast.LENGTH_SHORT);
                return true;
            case R.id.Centymetr:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("cm");
                }
                else {
                    jednostkaPromien.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT);
                return true;
            case R.id.Decymetr:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("dm");
                }
                else {
                    jednostkaPromien.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT);
                return true;
            case R.id.Metr:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("m");
                }
                else {
                    jednostkaPromien.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT);
                return true;
            case R.id.Kilometr:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("km");
                }
                else {
                    jednostkaPromien.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT);
                return true;
            case R.id.MnaM:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("m/m");
                }
                else{
                    jednostkaPredkosc.setText("m/m");
                }
                Toast.makeText(this, "Metry na minutę", Toast.LENGTH_SHORT);
                return true;
            case R.id.MnaS:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("m/s");
                }
                else{
                    jednostkaPredkosc.setText("m/s");
                }
                Toast.makeText(this, "Metry na sekundę", Toast.LENGTH_SHORT);
                return true;
            case R.id.KMnaH:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("km/h");
                }
                else{
                    jednostkaPredkosc.setText("km/h");
                }
                Toast.makeText(this, "Kilometry na godzinę", Toast.LENGTH_SHORT);
                return true;
            case R.id.KMnaM:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("km/m");
                }
                else{
                    jednostkaPredkosc.setText("km/m");
                }
                Toast.makeText(this, "Kilometry na minutę", Toast.LENGTH_SHORT);
                return true;
            case R.id.KMnaS:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("km/s");
                }
                else{
                    jednostkaPredkosc.setText("km/s");
                }
                Toast.makeText(this, "Kilometry na sekundę", Toast.LENGTH_SHORT);
                return true;
            case R.id.option2:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("g");
                }
                else{
                    jednostkaMasa.setText("g");
                }
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT);
                return true;
            case R.id.option3:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("dag");
                }
                else{
                    jednostkaMasa.setText("dag");
                }
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT);
                return true;
            case R.id.option4:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("kg");
                }
                else{
                    jednostkaMasa.setText("kg");
                }
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT);
                return true;
            case R.id.option5:
                if(ktory3.equals("wynik")){
                    jednostkaWynik.setText("t");
                }
                else{
                    jednostkaMasa.setText("t");
                }
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT);
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
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition)))).get(childPosition).toString();
                if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(RuchPoOkregu.this, Szkola.class);
                    i.putExtra("miejsce", "RuchPoOkregu");
                    startActivity(i);
                    Animatoo.animateFade(RuchPoOkregu.this);
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
            Intent i = new Intent(RuchPoOkregu.this, Grawitacja.class);
            startActivity(i);
            Animatoo.animateFade(RuchPoOkregu.this);
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
            Log.i("wynik", ""+id+"lala");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}