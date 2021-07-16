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
import com.e.matfiziak.Adaptery.CustomExpandableListAdapter;
import com.e.matfiziak.Adaptery.NavigationManager;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.StrictMath.sqrt;

public class Kwadrat extends AppCompatActivity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kwadrat);
        ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final EditText bok = findViewById(R.id.bokKwadratu);
        final EditText pole = findViewById(R.id.poleKwadratu);
        final EditText obwod = findViewById(R.id.obwodKwadratu);
        final EditText przekatna = findViewById(R.id.przekatnaKwadratu);
        final CheckBox checkBoxPole = findViewById(R.id.checkboxPoleKwadrat);
        final CheckBox checkBoxBok = findViewById(R.id.checkboxBokKwadratu);
        final CheckBox checkBoxObwod = findViewById(R.id.checkboxObwodKwadratu);
        final CheckBox checkBoxPrzekatna = findViewById(R.id.checkboxPrzekatnaKwadrat);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKwadrat);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaKwadrat);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKwadrat);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaKwadrat);
        final TextView wynik = findViewById(R.id.wynikKwadrat);
        final TextView jednostkaWynik = findViewById(R.id.jednostkaWynikKwadrat);
        final TextView jednostkaBok = findViewById(R.id.jednostkaBokKwadrat);
        final TextView jednostkaPole = findViewById(R.id.jednostkaPoleKwadrat);
        final TextView jednostkaObwod = findViewById(R.id.jednostkaObwodKwadrat);
        final TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaKwadrat);
        doWyslania = new ArrayList<String>();
        final Intent incomingIntent = getIntent();
        ArrayList<String> incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String checkbox = incomingIntent.getStringExtra("checkbox");
        if(incomingList!=null){
            bok.setText(incomingList.get(0));
            jednostkaBok.setText(incomingList.get(1));
            pole.setText(incomingList.get(2));
            jednostkaPole.setText(incomingList.get(3));
            obwod.setText(incomingList.get(4));
            jednostkaObwod.setText(incomingList.get(5));
            przekatna.setText(incomingList.get(6));
            jednostkaPrzekatna.setText(incomingList.get(7));
            pierwszaLinia.setText(incomingList.get(8));
            drugaLinia.setText(incomingList.get(9));
            trzeciaLinia.setText(incomingList.get(10));
            czwartaLinia.setText(incomingList.get(11));
            wynik.setText(incomingList.get(12));
            jednostkaWynik.setText(incomingList.get(13));
        }
        if(checkbox!=null){
            if(checkbox.equals("bok")){
                checkBoxBok.setChecked(true);
            }
            else if(checkbox.equals("pole")){
                checkBoxPole.setChecked(true);
            }
            else if(checkbox.equals("obwod")){
                checkBoxObwod.setChecked(true);
            }
            else if(checkbox.equals("przekatna")){
                checkBoxPrzekatna.setChecked(true);
            }
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(bok.getText().toString());
                doWyslania.add(jednostkaBok.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(jednostkaPole.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(jednostkaObwod.getText().toString());
                doWyslania.add(przekatna.getText().toString());
                doWyslania.add(jednostkaPrzekatna.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                String ktoryCheckbox=null;
                if(checkBoxBok.isChecked()){
                    ktoryCheckbox="bok";
                }
                else if(checkBoxObwod.isChecked()){
                    ktoryCheckbox="obwod";
                }
                else if(checkBoxPole.isChecked()){
                    ktoryCheckbox="pole";
                }
                else if(checkBoxPrzekatna.isChecked()){
                    ktoryCheckbox="przekatne";
                }
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(Kwadrat.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Kwadrat");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Kwadrat.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Kwadrat.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Kwadrat");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Kwadrat.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Kwadrat");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Kwadrat.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Kwadrat");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i4);
                        break;
                }
                return true;
            }
        });
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        Button buttonCzysc = findViewById(R.id.buttonCzyscKwadrat);
        Button buttonOblicz = findViewById(R.id.buttonObliczKwadrat);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bok.setText("");
                jednostkaBok.setText("cm");
                pole.setText("");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                obwod.setText("");
                jednostkaObwod.setText("cm");
                przekatna.setText("");
                jednostkaPrzekatna.setText("cm");
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
                wynik.setText("");
                jednostkaWynik.setText("");
                checkBoxBok.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
            }
        });
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxBok.isChecked()){
                        Double a = null;
                        if(!pole.getText().toString().equals("")){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;P"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a = sqrt(poleD);
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if(!obwod.getText().toString().equals("")){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob,jednostkaObwod.getText().toString());
                            pierwszaLinia.setText("Ob=4a");
                            drugaLinia.setText("a=Ob/4");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a = Ob/4;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if(!przekatna.getText().toString().equals("")){
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            pierwszaLinia.setText("d=a&#8730;2");
                            drugaLinia.setText("a=d/&#8730;2");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a=d/2;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(Html.fromHtml(x+"&#8730;2"));
                        }
                        else{
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wynik.setText("");
                        }
                    }
                    else if(checkBoxObwod.isChecked()){
                        Double Ob = null;
                        if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText("Ob=4a");
                            drugaLinia.setText("Ob=4*"+a);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Ob = 4*a;
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob,jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if(!pole.getText().toString().equals("")){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;P"));
                            trzeciaLinia.setText("Ob=4a");
                            Double a = sqrt(poleD);
                            czwartaLinia.setText("Ob=4*"+a);
                            Ob=4*a;
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if(!przekatna.getText().toString().equals("")){
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d=a&#8730;2"));
                            drugaLinia.setText(Html.fromHtml("a=d/&#8730;2"));
                            trzeciaLinia.setText("Ob=4*a");
                            czwartaLinia.setText("");
                            Double a = d/2;
                            Ob = 4*a;
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(Html.fromHtml(x+"&#8730;2"));
                        }
                        else{
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wynik.setText("");
                        }
                    }
                    else if(checkBoxPole.isChecked()){
                        Double poleD = null;
                        if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = a*a;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if(!obwod.getText().toString().equals("")){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaObwod.getText().toString());
                            pierwszaLinia.setText("Ob=4a");
                            drugaLinia.setText("a=Ob/4");
                            Double a = Ob/4;
                            trzeciaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>"));
                            poleD = a*a;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if(!przekatna.getText().toString().equals("")) {
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=d<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("P=" + d + "<sup><small><small>2</small></small></sup>/4"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = d * d / 2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else{
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxPrzekatna.isChecked()){
                        Double d = null;
                        if(!pole.getText().toString().equals("")){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=d<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("d=&#8730;(2*P)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = 2*poleD;
                            d = sqrt(poleD);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if(!obwod.getText().toString().equals("")){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaObwod.getText().toString());
                            pierwszaLinia.setText("Ob=4a");
                            drugaLinia.setText("a=Ob/4");
                            trzeciaLinia.setText(Html.fromHtml("d=a&#8730;2"));
                            czwartaLinia.setText("");
                            Double a = Ob/4;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(Html.fromHtml(x+"&#8730;2"));
                        }
                        else if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d=a&#8730;2"));
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            d = a;
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(Html.fromHtml(x+"&#8730;2"));
                        }
                        else{
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wynik.setText("");
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        checkBoxBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
                if(checkBoxBok.isChecked()){
                    jednostkaWynik.setText(bokJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        bokJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBok.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
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
        checkBoxPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBok.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
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
        checkBoxPrzekatna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBok.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                if(checkBoxPrzekatna.isChecked()){
                    jednostkaWynik.setText(przekatnaJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        przekatnaJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        jednostkaBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaBok);
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
        jednostkaPrzekatna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzekatna);
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
        mdrawerLayout = findViewById(R.id.drawerKwadrat_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Kwadrat.this, Konto.class);
                i.putExtra("miejsce", "Kwadrat");
                startActivity(i);
                Animatoo.animateFade(Kwadrat.this);
                return false;
            }
        });
        expandableListView.addHeaderView(listHeaderView);
        /*final CircleImageView imageView = listHeaderView.findViewById(R.id.avatar);
        final TextView nickTe = listHeaderView.findViewById(R.id.name);
        dane dane1 = new dane();
        String nick2 = dane1.nick;
        String imageUrl = dane1.imageUrl;
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
        if(nick2!=null){
            nickTe.setText(nick2);
        }*/
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
    String bokJ="cm", obwodJ="cm", poleJ="cm",przekatnaJ="cm";
    public void wyczyscLinie(){
        TextView pierszaLinia = findViewById(R.id.pierwszaLiniaKwadrat);
        TextView drugaLinia = findViewById(R.id.drugaLiniaKwadrat);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKwadrat);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaKwadrat);
        TextView wynik = findViewById(R.id.wynikKwadrat);
        pierszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
    }
    String ktory="";
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        CheckBox checkBox = findViewById(R.id.checkboxPoleKwadrat);
        if(v.getId()==R.id.jednostkaPoleKwadrat){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if(v.getId()==R.id.jednostkaWynikKwadrat&&checkBox.isChecked()){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaWynikKwadrat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaBokKwadrat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bok";
        }
        else if(v.getId()==R.id.jednostkaObwodKwadrat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="obwod";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaKwadrat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatna";
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView jednostkaWynik = findViewById(R.id.jednostkaWynikKwadrat);
        TextView jednostkaBok = findViewById(R.id.jednostkaBokKwadrat);
        TextView jednostkaPole = findViewById(R.id.jednostkaPoleKwadrat);
        TextView jednostkaObwod = findViewById(R.id.jednostkaObwodKwadrat);
        TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaKwadrat);
        CheckBox checkBoxPole = findViewById(R.id.checkboxPoleKwadrat);
        CheckBox checkBoxBok = findViewById(R.id.checkboxBokKwadratu);
        CheckBox checkBoxObwod = findViewById(R.id.checkboxObwodKwadratu);
        CheckBox checkBoxPrzekatne = findViewById(R.id.checkboxPrzekatnaKwadrat);
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    if(checkBoxBok.isChecked()){
                        bokJ="cm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="cm";
                    }
                    else if(checkBoxPole.isChecked()){
                        poleJ="cm";
                    }
                    else if(checkBoxPrzekatne.isChecked()){
                        przekatnaJ="cm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("cm");
                }
                else if (ktory.equals("obwod")){
                    jednostkaObwod.setText("cm");
                }
                else if(ktory.equals("przekatna")){
                    jednostkaPrzekatna.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                    if(checkBoxBok.isChecked()){
                        bokJ="dm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="dm";
                    }
                    else if(checkBoxPole.isChecked()){
                        poleJ="dm";
                    }
                    else if(checkBoxPrzekatne.isChecked()){
                        przekatnaJ="dm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("dm");
                }
                else if (ktory.equals("obwod")){
                    jednostkaObwod.setText("dm");
                }
                else if(ktory.equals("przekatna")){
                    jednostkaPrzekatna.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                    if(checkBoxBok.isChecked()){
                        bokJ="m";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="m";
                    }
                    else if(checkBoxPole.isChecked()){
                        poleJ="m";
                        Log.i("wynik","lal");
                    }
                    else if(checkBoxPrzekatne.isChecked()){
                        przekatnaJ="m";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("m");
                }
                else if (ktory.equals("obwod")){
                    jednostkaObwod.setText("m");
                }
                else if(ktory.equals("przekatna")){
                    jednostkaPrzekatna.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                    if(checkBoxBok.isChecked()){
                        bokJ="km";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodJ="km";
                    }
                    else if(checkBoxPole.isChecked()){
                        poleJ="km";
                    }
                    else if(checkBoxPrzekatne.isChecked()){
                        przekatnaJ="km";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("km");
                }
                else if (ktory.equals("obwod")){
                    jednostkaObwod.setText("km");
                }
                else if(ktory.equals("przekatna")){
                    jednostkaPrzekatna.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                    poleJ="mm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                    poleJ="cm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                    poleJ="dm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                    poleJ="m";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("a");
                    poleJ="a";
                }
                else{
                    jednostkaPole.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("ha");
                    poleJ="ha";
                }
                else{
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
                    Intent i = new Intent(Kwadrat.this, Szkola.class);
                    i.putExtra("miejsce", "Kwadrat");
                    startActivity(i);
                    Animatoo.animateFade(Kwadrat.this);
                }
                mdrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceData) {
        super.onSaveInstanceState(savedInstanceData);
        try {
           TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKwadrat);
           TextView drugaLinia = findViewById(R.id.drugaLiniaKwadrat);
           TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKwadrat);
           TextView czwartaLinia = findViewById(R.id.czwartaLiniaKwadrat);
           TextView wynik = findViewById(R.id.wynikKwadrat);
           TextView jednostkaWynik = findViewById(R.id.jednostkaWynikKwadrat);
           TextView jednostkaBok = findViewById(R.id.jednostkaBokKwadrat);
           TextView jednostkaPole = findViewById(R.id.jednostkaPoleKwadrat);
           TextView jednostkaObwod = findViewById(R.id.jednostkaObwodKwadrat);
           TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaKwadrat);
           savedInstanceData.putString("pierwszaLinia",pierwszaLinia.getText().toString());
           savedInstanceData.putString("drugaLinia", drugaLinia.getText().toString());
           savedInstanceData.putString("trzeciaLinia", trzeciaLinia.getText().toString());
           savedInstanceData.putString("czwartaLinia", czwartaLinia.getText().toString());
           savedInstanceData.putString("wynik", wynik.getText().toString());
           savedInstanceData.putString("jednostkaWynik", jednostkaWynik.getText().toString());
           savedInstanceData.putString("jednostkaBok", jednostkaBok.getText().toString());
           savedInstanceData.putString("jednostkaPole", jednostkaPole.getText().toString());
           savedInstanceData.putString("jednostkaObwod",jednostkaObwod.getText().toString());
           savedInstanceData.putString("jednostkaPrzekatna", jednostkaPrzekatna.getText().toString());
        }
        catch (Exception ex){
            Log.i("wynik", ex.getMessage().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKwadrat);
        TextView drugaLinia = findViewById(R.id.drugaLiniaKwadrat);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKwadrat);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaKwadrat);
        TextView wynik = findViewById(R.id.wynikKwadrat);
        TextView jednostkaWynik = findViewById(R.id.jednostkaWynikKwadrat);
        TextView jednostkaBok = findViewById(R.id.jednostkaBokKwadrat);
        TextView jednostkaPole = findViewById(R.id.jednostkaPoleKwadrat);
        TextView jednostkaObwod = findViewById(R.id.jednostkaObwodKwadrat);
        TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaKwadrat);
        pierwszaLinia.setText(savedInstanceState.getString("pierwszaLinia"));
        drugaLinia.setText(savedInstanceState.getString("drugaLinia"));
        trzeciaLinia.setText(savedInstanceState.getString("trzeciaLinia"));
        czwartaLinia.setText(savedInstanceState.getString("czwartaLinia"));
        wynik.setText(savedInstanceState.getString("wynik"));
        jednostkaWynik.setText(savedInstanceState.getString("jednostkaWynik"));
        jednostkaBok.setText(savedInstanceState.getString("jednostkaBok"));
        jednostkaObwod.setText(savedInstanceState.getString("jednostkaObwod"));
        jednostkaPrzekatna.setText(savedInstanceState.getString("jednostkaPrzekatna"));
        String x = savedInstanceState.getString("jednostkaPole");
        if((!poleJ.equals("a"))&&(!poleJ.equals("ha"))){
            jednostkaWynik.setText(Html.fromHtml(poleJ+"<sup><small><small>2</small></small></sup"));
        }
        else{
            jednostkaWynik.setText(poleJ);
        }
    }
    @Override
    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent i = new Intent(Kwadrat.this, Czworokaty.class);
            startActivity(i);
            Animatoo.animateFade(Kwadrat.this);
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