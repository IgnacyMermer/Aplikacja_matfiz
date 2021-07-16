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
import android.widget.RelativeLayout;
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

public class pole_trojkata extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private
    NavigationManager navigationManager;
    ArrayList<String> doWyslania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole_trojkata);
        RelativeLayout relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        Intent incomingIntent = getIntent();
        ArrayList<String> incomingList = new ArrayList<String>();
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        String checkbox="";
        checkbox = incomingIntent.getStringExtra("checkbox");
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        final Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_enter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mdrawerLayout = findViewById(R.id.drawerPoleTrojkata_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(pole_trojkata.this, Konto.class);
                startActivity(i);
                Animatoo.animateFade(pole_trojkata.this);
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
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        ImageButton buttonBar = findViewById(R.id.buttonBar);
        buttonBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();

        /**/
        final EditText bok = findViewById(R.id.bok1);
        final EditText wysokosc = findViewById(R.id.wysokosc1);
        final EditText promienWpis = findViewById(R.id.wpisany1);
        final EditText promienOpis = findViewById(R.id.opisany1);
        final EditText pole = findViewById(R.id.pole1);
        final CheckBox checkBoxPole = findViewById(R.id.checkboxPole1);
        final CheckBox checkBoxBok = findViewById(R.id.checkboxBok);
        final CheckBox checkBoxWysokosc = findViewById(R.id.checkboxWysokosc);
        final CheckBox checkBoxWpisany = findViewById(R.id.checkboxWpisany);
        final CheckBox checkBoxOpisany = findViewById(R.id.checkboxOpisany);
        final TextView jednostkaBok = findViewById(R.id.jednostkaBok1);
        if(checkbox.equals("pole")){
            checkBoxPole.setChecked(true);
        }
        else if(checkbox.equals("bok")){
            checkBoxBok.setChecked(true);
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
        final TextView wynik = findViewById(R.id.wynik1);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLinia1);
        final TextView drugaLinia = findViewById(R.id.drugaLinia1);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLinia1);
        final TextView czwartaLinia = findViewById(R.id.czwartaLinia1);
        final TextView jednostkaWynik = findViewById(R.id.jednostkaWynik1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(bok.getText().toString());
                doWyslania.add(wysokosc.getText().toString());
                doWyslania.add(promienWpis.getText().toString());
                doWyslania.add(promienOpis.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxPole.isChecked()){
                    ktoryCheckbox="pole";
                }
                else if(checkBoxBok.isChecked()){
                    ktoryCheckbox="bok";
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
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(pole_trojkata.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "poleTrojkata");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(pole_trojkata.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(pole_trojkata.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "poleTrojkata");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(pole_trojkata.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "poleTrojkata");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(pole_trojkata.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "poleTrojkata");
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
        if(incomingList!=null){
            bok.setText(incomingList.get(0));
            wysokosc.setText(incomingList.get(1));
            promienWpis.setText(incomingList.get(2));
            promienOpis.setText(incomingList.get(3));
            pole.setText(incomingList.get(4));
            pierwszaLinia.setText(incomingList.get(5));
            drugaLinia.setText(incomingList.get(6));
            trzeciaLinia.setText(incomingList.get(7));
            czwartaLinia.setText(incomingList.get(8));
            wynik.setText(incomingList.get(9));
            jednostkaWynik.setText(incomingList.get(10));
        }
        jednostkaBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaBok);
                openContextMenu(v);
            }
        });
        final TextView jednostkaWysokosc = findViewById(R.id.jednostkaWysokosc1);
        jednostkaWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokosc);
                openContextMenu(v);
            }
        });
        final TextView jednostkaOpisany = findViewById(R.id.jednostkaOpisany1);
        jednostkaOpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaOpisany);
                openContextMenu(v);
            }
        });
        final TextView jednostkaWpisany = findViewById(R.id.jednostkaWpisany1);
        jednostkaWpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWpisany);
                openContextMenu(v);
            }
        });
        final TextView jednostkaPole = findViewById(R.id.jednostkaPole1);
        jednostkaPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPole);
                openContextMenu(v);
            }
        });
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        final Button buttonOblicz = findViewById(R.id.buttonWynik);
        final Button buttonCzysc = findViewById(R.id.buttonCzysc1);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wynik.setText("");
                jednostkaWynik.setText("");
                jednostkaBok.setText("cm");
                jednostkaOpisany.setText("cm");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaWpisany.setText("cm");
                jednostkaWysokosc.setText("cm");
                wysokosc.setText("");
                promienOpis.setText("");
                promienWpis.setText("");
                bok.setText("");
                pole.setText("");
                checkBoxBok.setChecked(false);
                checkBoxOpisany.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxPole.isChecked()){
                        Double pole = null;
                        if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double b = a*a;
                            pole = b/4;
                            String x = funkcjePrzelicznikowe.intowanie(pole);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.poleWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            drugaLinia.setText("P="+b+Html.fromHtml("<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wynik.setText(x+(Html.fromHtml( "&#8730;3")));
                        }
                        else if(!wysokosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("h=a*"+Html.fromHtml("&#8730;3/2"));
                            drugaLinia.setText("a=h*2/"+Html.fromHtml("&#8730;3"));
                            trzeciaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            Double c = a*a/3*4;
                            String y = funkcjePrzelicznikowe.intowanie(c);
                            c=Double.parseDouble(y);
                            c = funkcjePrzelicznikowe.poleWynik(c , jednostkaWynik.getText().toString());
                            czwartaLinia.setText("");
                            pole = c/4;
                            String x = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x+(Html.fromHtml( "&#8730;3")));
                        }
                        else if(!promienOpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienOpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText("R=a*"+Html.fromHtml("&#8730;3/3"));
                            drugaLinia.setText("a=R*3/"+Html.fromHtml("&#8730;3"));
                            trzeciaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            Double c = a*a/3*9;
                            String y = funkcjePrzelicznikowe.intowanie(c);
                            c = funkcjePrzelicznikowe.poleWynik(c, jednostkaWynik.getText().toString());
                            czwartaLinia.setText("P="+c+Html.fromHtml("<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            pole = c/4;
                            String x = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x+(Html.fromHtml( "&#8730;3")));
                        }
                        else if(!promienWpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienWpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText("r=a*"+Html.fromHtml("&#8730;3/6"));
                            drugaLinia.setText("a=r*6/"+Html.fromHtml("&#8730;3"));
                            trzeciaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            Double c = a*a/3*36;
                            String y = funkcjePrzelicznikowe.intowanie(c);
                            c = funkcjePrzelicznikowe.dlugoscWynik(c, jednostkaWynik.getText().toString());
                            czwartaLinia.setText("P="+c+Html.fromHtml("<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            pole = c/4;
                            String x = funkcjePrzelicznikowe.intowanie(pole);
                            wynik.setText(x+(Html.fromHtml( "&#8730;3")));
                        }
                        else {
                            wynik.setText("");
                            pierwszaLinia.setText("");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                    }
                    else if(checkBoxBok.isChecked()){
                        if(!pole.getText().toString().equals("")){
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=4*P/&#8730;3"));
                            a=4*a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730")+x);
                            czwartaLinia.setText("");
                            a=sqrt(a);
                            String y = funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(y);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d , jednostkaWynik.getText().toString());
                            y=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(y);
                        }
                        else if(!wysokosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            Double b = a;
                            String b2 = funkcjePrzelicznikowe.intowanie(b);
                            pierwszaLinia.setText(Html.fromHtml("h=a*&#8730;3/2"));
                            drugaLinia.setText(Html.fromHtml("a=h*2/&#8730;3"));
                            a=2*a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText("h="+b2+Html.fromHtml("*2/&#8730;3"));
                            czwartaLinia.setText("");
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if(!promienOpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienOpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaOpisany.getText().toString());
                            Double b = a;
                            pierwszaLinia.setText(Html.fromHtml("R=a*&#8730;3/3"));
                            drugaLinia.setText(Html.fromHtml("a=R*3/&#8730;3"));
                            a=3*a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText("R="+b+Html.fromHtml("*3/&#8730;3"));
                            czwartaLinia.setText("");
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if(!promienWpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienWpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            Double b = a;
                            pierwszaLinia.setText(Html.fromHtml("r=a*&#8730;3/6"));
                            drugaLinia.setText(Html.fromHtml("a=r*6/&#8730;3"));
                            a=6*a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText("r="+b+Html.fromHtml("*6/&#8730;3"));
                            czwartaLinia.setText("");
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
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
                    else if (checkBoxWysokosc.isChecked()) {
                        if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("h=a*&#8730;3/2"));
                            drugaLinia.setText("h="+a+Html.fromHtml("&#8730;3/2"));
                            a=a/2;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x+Html.fromHtml("&#8730;3"));
                        }
                        else if(!promienOpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienOpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("R=&frac23;*h"));
                            drugaLinia.setText(Html.fromHtml("h=R*3/2"));
                            trzeciaLinia.setText("h="+a+"*3/2");
                            a=a*3/2;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            czwartaLinia.setText("");
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if(!promienWpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienWpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("r=&frac13;*h"));
                            drugaLinia.setText(Html.fromHtml("h=r*3"));
                            trzeciaLinia.setText("h="+a+"*3");
                            a=a*3;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            czwartaLinia.setText("");
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if(!pole.getText().toString().equals("")){
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=4*P/&#8730;3"));
                            a=a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;(4*"+x+")"));
                            czwartaLinia.setText(Html.fromHtml("h=a*&#8730;3/2"));
                            a=a*4;
                            a=sqrt(a);
                            a=a/2;
                            x=funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x+Html.fromHtml("&#8730;3"));
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
                        if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("R=a*&#8730;3/3"));
                            drugaLinia.setText("R="+a+Html.fromHtml("*&#8730;3/3"));
                            a=a*1.7306/3;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                        else if(!wysokosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("R=&frac23*h"));
                            drugaLinia.setText(""+Html.fromHtml("R=&frac23*")+a);
                            a=a*2/3;
                            String x =funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                        else if(!promienWpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienWpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWpisany.getText().toString());
                            pierwszaLinia.setText("R=2*r");
                            drugaLinia.setText("R=2*"+a);
                            a=a*2;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                        else if(!pole.getText().toString().equals("")){
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=4*P/&#8730;3"));
                            a=a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;(4*"+x+")"));
                            czwartaLinia.setText(Html.fromHtml("h=a*&#8730;3/3"));
                            a=a*4;
                            a=sqrt(a);
                            a=a/3;
                            x=funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x+Html.fromHtml("&#8730;3"));
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
                        if(!bok.getText().toString().equals("")){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("R=a*&#8730;3/6"));
                            drugaLinia.setText("R="+a+Html.fromHtml("*&#8730;3/6"));
                            a=a*1.7306/6;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                        else if(!wysokosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(wysokosc.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("R=&frac13*h"));
                            drugaLinia.setText(""+Html.fromHtml("R=&frac13*")+a);
                            a=a/3;
                            String x =funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                        else if(!!promienOpis.getText().toString().equals("")){
                            Double a = Double.parseDouble(promienOpis.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaOpisany.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("r=&frac12*R"));
                            drugaLinia.setText(""+Html.fromHtml("r=&frac12*")+a);
                            a=a/2;
                            String x =funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                        }
                        else if(!pole.getText().toString().equals("")){
                            Double a = Double.parseDouble(pole.getText().toString());
                            a = funkcjePrzelicznikowe.pole(a, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>&#8730;3/4"));
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=4*P/&#8730;3"));
                            a=a/1.7306;
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;(4*"+x+")"));
                            czwartaLinia.setText(Html.fromHtml("h=a*&#8730;3/6"));
                            a=a*4;
                            a=sqrt(a);
                            a=a/6;
                            x=funkcjePrzelicznikowe.intowanie(a);
                            Double d = Double.parseDouble(x);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            x=funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x+Html.fromHtml("&#8730;3"));
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
                    wynik.setText("Wpisz liczby");
                }
            }
        });
        checkBoxBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxOpisany.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxBok.isChecked()){
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
        checkBoxOpisany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBok.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxOpisany.isChecked()){
                    jednostkaWynik.setText(opisanyWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        opisanyWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxOpisany.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxWpisany.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxPole.isChecked()){
                    if(poleWynik.equals("a")||poleWynik.equals("ha")){
                        jednostkaWynik.setText(poleWynik);
                    }
                    else{
                        jednostkaWynik.setText(Html.fromHtml(poleWynik+"<sup><small><small>2</small></small></sup>"));
                    }
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        poleWynik = jednostkaWynik.getText().toString();
                        if(jednostkaWynik.getText().toString().equals("a")||jednostkaWynik.getText().toString().equals("ha")){

                        }
                        else{
                            poleWynik = poleWynik.substring(0,poleWynik.length()-1);
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
                checkBoxOpisany.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                if(checkBoxWpisany.isChecked()){
                    jednostkaWynik.setText(wpisanyWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        wpisanyWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxOpisany.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxWpisany.setChecked(false);
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



        /**/
    }
    public  void wyczyscLinie(){
        TextView pierwszaLinia = findViewById(R.id.pierwszaLinia1);
        TextView drugaLinia = findViewById(R.id.drugaLinia1);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLinia1);
        TextView czwartaLinia = findViewById(R.id.czwartaLinia1);
        TextView wynik = findViewById(R.id.wynik1);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLinia1);
            TextView drugaLinia = findViewById(R.id.drugaLinia1);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLinia1);
            TextView czwartaLinia = findViewById(R.id.czwartaLinia1);
            TextView wynik = findViewById(R.id.wynik1);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik1);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLinia1);
            TextView drugaLinia = findViewById(R.id.drugaLinia1);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLinia1);
            TextView czwartaLinia = findViewById(R.id.czwartaLinia1);
            TextView wynik = findViewById(R.id.wynik1);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik1);
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
        CheckBox checkBoxPole = findViewById(R.id.checkboxPole1);
        if(v.getId()==R.id.jednostkaBok1){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bok";
        }
        else if(v.getId()==R.id.jednostkaWysokosc1){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wysokosc";
        }
        else if(v.getId()==R.id.jednostkaOpisany1){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="opisany";
        }
        else if(v.getId()==R.id.jednostkaWpisany1){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wpisany";
        }
        else if(v.getId()==R.id.jednostkaWynik1&&checkBoxPole.isChecked()){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaPole1){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if(v.getId()==R.id.jednostkaWynik1){
            getMenuInflater().inflate(R.menu.dlugosc2_menu,menu);
            ktory="wynik";
        }
    }
    String poleWynik="cm", bokWynik="cm", wysokoscWynik="cm", wpisanyWynik="cm", opisanyWynik="cm";
    String ktory2="", ktory3="";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView jednostkaBok = findViewById(R.id.jednostkaBok1);
        TextView jednostkaWysokosc = findViewById(R.id.jednostkaWysokosc1);
        TextView jednostkaWpisany = findViewById(R.id.jednostkaWpisany1);
        TextView jednostkaOpisany = findViewById(R.id.jednostkaOpisany1);
        TextView jednostkaPole = findViewById(R.id.jednostkaPole1);
        TextView jednostkaWynik = findViewById(R.id.jednostkaWynik1);
        final CheckBox checkBoxPole = findViewById(R.id.checkboxPole1);
        final CheckBox checkBoxBok = findViewById(R.id.checkboxBok);
        final CheckBox checkBoxWysokosc = findViewById(R.id.checkboxWysokosc);
        final CheckBox checkBoxWpisany = findViewById(R.id.checkboxWpisany);
        final CheckBox checkBoxOpisany = findViewById(R.id.checkboxOpisany);
        switch (item.getItemId()){
            case R.id.Centymetr:
               if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    if(checkBoxBok.isChecked()){
                        bokWynik="cm";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyWynik="cm";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyWynik="cm";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="cm";
                    }
               }
               else if(ktory.equals("bok")){
                   jednostkaBok.setText("cm");
               }
               else if(ktory.equals("wysokosc")){
                   jednostkaWysokosc.setText("cm");
               }
               else if (ktory.equals("wpisany")) {
                    jednostkaWpisany.setText("cm");
               }
               else if(ktory.equals("opisany")){
                   jednostkaOpisany.setText("cm");
               }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                    if(checkBoxBok.isChecked()){
                        bokWynik="dm";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyWynik="dm";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyWynik="dm";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="dm";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("dm");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("dm");
                }
                else if (ktory.equals("wpisany")) {
                    jednostkaWpisany.setText("dm");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                    if(checkBoxBok.isChecked()){
                        bokWynik="m";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyWynik="m";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyWynik="m";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="m";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("m");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("m");
                }
                else if (ktory.equals("wpisany")) {
                    jednostkaWpisany.setText("m");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                    if(checkBoxBok.isChecked()){
                        bokWynik="km";
                    }
                    else if(checkBoxOpisany.isChecked()){
                        opisanyWynik="km";
                    }
                    else if(checkBoxWpisany.isChecked()){
                        wpisanyWynik="km";
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        wysokoscWynik="km";
                    }
                }
                else if(ktory.equals("bok")){
                    jednostkaBok.setText("km");
                }
                else if(ktory.equals("wysokosc")){
                    jednostkaWysokosc.setText("km");
                }
                else if (ktory.equals("wpisany")) {
                    jednostkaWpisany.setText("km");
                }
                else if(ktory.equals("opisany")){
                    jednostkaOpisany.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                    ktory2 = "mm2";
                    poleWynik="mm";
                }
                else{
                    ktory3="mm2";
                    jednostkaPole.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                    ktory2 = "cm2";
                    poleWynik="cm";
                }
                else{
                    ktory3 = "cm2";
                    jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }

                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                    ktory2 = "dm2";
                    poleWynik="dm";
                }
                else{
                    ktory3="dm2";
                    jednostkaPole.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                    ktory2="m2";
                    poleWynik="m";
                }
                else{
                    ktory3="m2";
                    jednostkaPole.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("a");
                    ktory2="a";
                    poleWynik="a";
                }
                else{
                    ktory3="a";
                    jednostkaPole.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("ha");
                    ktory2="ha";
                    poleWynik="ha";
                }
                else{
                    ktory3="ha";
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
                    Intent i = new Intent(pole_trojkata.this, Szkola.class);
                    startActivity(i);
                    Animatoo.animateFade(pole_trojkata.this);
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
            Intent i = new Intent(pole_trojkata.this, Trojkaty.class);
            startActivity(i);
            Animatoo.animateFade(pole_trojkata.this);
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