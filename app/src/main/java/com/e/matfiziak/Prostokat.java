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

public class Prostokat extends AppCompatActivity {
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
        setContentView(R.layout.activity_prostokat);
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
        final EditText bokA = findViewById(R.id.bokAProstokat);
        final EditText bokB = findViewById(R.id.bokBProstokat);
        final EditText pole = findViewById(R.id.poleProstokat);
        final EditText obwod = findViewById(R.id.obwodProstokat);
        final EditText przekatna = findViewById(R.id.przekatnaProstokat);
        final CheckBox checkBoxPole = findViewById(R.id.checkboxPoleProstokat);
        final CheckBox checkBoxBokA = findViewById(R.id.checkboxBokAProstokat);
        final CheckBox checkBoxBokB = findViewById(R.id.checkboxBokBProstokat);
        final CheckBox checkBoxObwod = findViewById(R.id.checkboxObwodProstokat);
        final CheckBox checkBoxPrzekatna = findViewById(R.id.checkboxPrzekatnaProstokat);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaProstokat);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaProstokat);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaProstokat);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaProstokat);
        final TextView wynik = findViewById(R.id.wynikProstokat);
        final TextView jednostkaWynik = findViewById(R.id.jednostkaWynikProstokat);
        final TextView jednostkaBokA = findViewById(R.id.jednostkaBokAProstokat);
        final TextView jednostkaBokB = findViewById(R.id.jednostkaBokBProstokat);
        final TextView jednostkaPole = findViewById(R.id.jednostkaPoleProstokat);
        final TextView jednostkaObwod = findViewById(R.id.jednostkaObwodProstokat);
        final TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaProstokat);
        doWyslania = new ArrayList<String>();
        final Intent incomingIntent = getIntent();
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        final String nick = incomingIntent.getStringExtra("nick");
        ArrayList<String> incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String checkbox = incomingIntent.getStringExtra("checkbox");
        if(incomingList!=null){
            bokA.setText(incomingList.get(0));
            jednostkaBokA.setText(incomingList.get(1));
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
            bokB.setText(incomingList.get(14));
            jednostkaBokB.setText(incomingList.get(15));
        }
        if(checkbox!=null){
            if(checkbox.equals("bokA")){
                checkBoxBokA.setChecked(true);
            }
            else if(checkbox.equals("bokB")){
                checkBoxBokB.setChecked(true);
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
                doWyslania.add(bokA.getText().toString());
                doWyslania.add(jednostkaBokA.getText().toString());
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
                doWyslania.add(bokB.getText().toString());
                doWyslania.add(jednostkaBokB.getText().toString());
                String ktoryCheckbox=null;
                if(checkBoxBokA.isChecked()){
                    ktoryCheckbox="bokA";
                }
                else if (checkBoxBokB.isChecked()){
                    ktoryCheckbox="bokB";
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
                        Intent i = new Intent(Prostokat.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Prostokat");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Prostokat.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Prostokat.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Prostokat");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Prostokat.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Prostokat");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Prostokat.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Prostokat");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox",ktoryCheckbox);
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return true;
            }
        });
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        Button buttonCzysc = findViewById(R.id.buttonCzyscProstokat);
        Button buttonOblicz = findViewById(R.id.buttonObliczProstokat);
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bokA.setText("");
                jednostkaBokA.setText("cm");
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
                checkBoxBokA.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
                checkBoxBokB.setChecked(false);
                bokB.setText("");
                jednostkaBokB.setText("cm");
            }
        });
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxBokA.isChecked()){
                        Double a = null;
                        if((!pole.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText("P=a*b");
                            drugaLinia.setText("a=P/b");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a = poleD/b;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob,jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            drugaLinia.setText("2a=Ob-2b");
                            Ob = Ob - (2*b);
                            trzeciaLinia.setText("a="+Ob+"/2");
                            czwartaLinia.setText("");
                            a = Ob/2;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!przekatna.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText("a<sup><small><small>2</small></small></sup>=d<sup><small><small>2</small></small></sup>-b<sup><small><small>2</small></small></sup>");
                            d=d*d;
                            b=b*b;
                            a=d-b;
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;"+a));
                            czwartaLinia.setText("");
                            a=sqrt(a);
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
                    else if(checkBoxBokB.isChecked()){
                        Double b = null;
                        if((!pole.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText("P=a*b");
                            drugaLinia.setText("b=P/a");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            b = poleD/a;
                            b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(b);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob,jednostkaObwod.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            drugaLinia.setText("2b=Ob-2a");
                            Ob = Ob - (2*a);
                            trzeciaLinia.setText("b="+Ob+"/2");
                            czwartaLinia.setText("");
                            b = Ob/2;
                            b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(b);
                            wynik.setText(x);
                        }
                        else if((!przekatna.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText("b<sup><small><small>2</small></small></sup>=d<sup><small><small>2</small></small></sup>-a<sup><small><small>2</small></small></sup>");
                            d=d*d;
                            a=a*a;
                            b=d-a;
                            trzeciaLinia.setText(Html.fromHtml("b=&#8730;"+b));
                            czwartaLinia.setText("");
                            b=sqrt(b);
                            b = funkcjePrzelicznikowe.dlugoscWynik(b, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(b);
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
                        if((!bokA.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            drugaLinia.setText("Ob=2*"+a+"+2*"+b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Ob = (2*a)+(2*b);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob,jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b"));
                            drugaLinia.setText(Html.fromHtml("b=P/a"));
                            trzeciaLinia.setText("Ob=2a+2b");
                            Double b = poleD/a;
                            czwartaLinia.setText("Ob=2*"+a+"+2*"+b);
                            Ob=(2*a)+(2*b);
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b"));
                            drugaLinia.setText(Html.fromHtml("a=P/b"));
                            trzeciaLinia.setText("Ob=2a+2b");
                            Double a = poleD/b;
                            czwartaLinia.setText("Ob=2*"+a+"+2*"+b);
                            Ob=(2*a)+(2*b);
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatna.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            d=d*d;
                            a=a*a;
                            Double b = d-a;
                            String x5 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText(Html.fromHtml("a=&#8730;"+x5));
                            trzeciaLinia.setText("Ob=2a+2b");
                            czwartaLinia.setText("");
                            b=sqrt(b);
                            Ob = (2*a)+(2*b);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatna.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            d=d*d;
                            b=b*b;
                            Double a = d-b;
                            String x5 = funkcjePrzelicznikowe.intowanie(a);
                            drugaLinia.setText(Html.fromHtml("b=&#8730;"+x5));
                            trzeciaLinia.setText("Ob=2a+2b");
                            czwartaLinia.setText("");
                            a = sqrt(a);
                            Ob = (2*a)+(2*b);
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
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
                        if((!bokA.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b"));
                            drugaLinia.setText(Html.fromHtml("P="+a+"*"+b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = a*b;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaObwod.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            drugaLinia.setText("2b=Ob-2a");
                            Double b = Ob-a-a;
                            b=b/2;
                            trzeciaLinia.setText(Html.fromHtml("P=a*b"));
                            czwartaLinia.setText(Html.fromHtml("P="+a+"*"+b));
                            poleD = a*b;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            drugaLinia.setText("2a=Ob-2b");
                            Double a = Ob-b-b;
                            a=a/2;
                            trzeciaLinia.setText(Html.fromHtml("P=a*b"));
                            czwartaLinia.setText(Html.fromHtml("P="+a+"*"+b));
                            poleD = a*b;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!przekatna.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))) {
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small><2</small></small></sup>=a<sup><small><small><2</small></small></sup>+b<sup><small><small><2</small></small></sup>"));
                            d=d*d;
                            Double c=a*a;
                            Double b = d-c;
                            String x5 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText(Html.fromHtml("a=&#8730;" + x5));
                            trzeciaLinia.setText("P=a*b");
                            czwartaLinia.setText("");
                            b=sqrt(b);
                            poleD = a*b;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!przekatna.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))) {
                            Double d = Double.parseDouble(przekatna.getText().toString());
                            d = funkcjePrzelicznikowe.dlugosc(d, jednostkaPrzekatna.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small><2</small></small></sup>=a<sup><small><small><2</small></small></sup>+b<sup><small><small><2</small></small></sup>"));
                            d=d*d;
                            Double c=b*b;
                            Double a = d-c;
                            String x5 = funkcjePrzelicznikowe.intowanie(b);
                            drugaLinia.setText(Html.fromHtml("a=&#8730;" + x5));
                            trzeciaLinia.setText("P=a*b");
                            czwartaLinia.setText("");
                            a=sqrt(a);
                            poleD = a*b;
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
                        if((!pole.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b"));
                            drugaLinia.setText(Html.fromHtml("b=P/a"));
                            trzeciaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            Double b = poleD/a;
                            a=a*a;
                            b=b*b;
                            d=a+b;
                            czwartaLinia.setText(Html.fromHtml("d=&#8730;"+d));
                            d=sqrt(d);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        if((!pole.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a*b"));
                            drugaLinia.setText(Html.fromHtml("b=P/a"));
                            trzeciaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            Double a = poleD/b;
                            b=b*b;
                            a=a*a;
                            d=a+b;
                            czwartaLinia.setText(Html.fromHtml("d=&#8730;"+d));
                            d=sqrt(d);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!bokA.getText().toString().equals(""))){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaObwod.getText().toString());
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            Double b = Ob-a-a;
                            b=b/2;
                            drugaLinia.setText("b="+b);
                            trzeciaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            a=a*a;
                            b=b*b;
                            d=a+b;
                            czwartaLinia.setText(Html.fromHtml("d=&#8730;"+d));
                            d=sqrt(d);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double Ob = Double.parseDouble(obwod.getText().toString());
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaObwod.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText("Ob=2a+2b");
                            Double a = Ob-b-b;
                            a=a/2;
                            drugaLinia.setText("a="+a);
                            trzeciaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            a=a*a;
                            b=b*b;
                            d=a+b;
                            czwartaLinia.setText(Html.fromHtml("d=&#8730;"+d));
                            d=sqrt(d);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
                        }
                        else if((!bokA.getText().toString().equals(""))&&(!bokB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bokA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBokA.getText().toString());
                            Double b = Double.parseDouble(bokB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaBokB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("d<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>+b<sup><small><small>2</small></small></sup>"));
                            a=a*a;
                            b=b*b;
                            d = a+b;
                            drugaLinia.setText(Html.fromHtml("d=&#8730;"+d));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            d=sqrt(d);
                            d = funkcjePrzelicznikowe.dlugoscWynik(d, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynik.setText(x);
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
        checkBoxBokA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
                checkBoxBokB.setChecked(false);
                wyczyscLinie();
                if(checkBoxBokA.isChecked()){
                    jednostkaWynik.setText(bokJA);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        bokJA=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
            }
        });
        checkBoxBokB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
                checkBoxBokA.setChecked(false);
                wyczyscLinie();
                if(checkBoxBokB.isChecked()){
                    jednostkaWynik.setText(bokJB);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        bokJB=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
            }
        });
        checkBoxObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
                wyczyscLinie();
                if(checkBoxObwod.isChecked()){
                    jednostkaWynik.setText(obwodJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        obwodJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
            }
        });
        checkBoxPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPrzekatna.setChecked(false);
                wyczyscLinie();
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
            }
        });
        checkBoxPrzekatna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBokA.setChecked(false);
                checkBoxBokB.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPole.setChecked(false);
                wyczyscLinie();
                if(checkBoxPrzekatna.isChecked()){
                    jednostkaWynik.setText(przekatnaJ);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        przekatnaJ=jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
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
        mdrawerLayout = findViewById(R.id.drawerProstokat_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Prostokat.this, Konto.class);
                i.putExtra("miejsce", "Prostokat");
                startActivity(i);
                Animatoo.animateFade(Prostokat.this);
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
    String bokJA="cm",bokJB="cm", obwodJ="cm", poleJ="cm",przekatnaJ="cm";
    public void wyczyscLinie(){
        TextView pierszaLinia = findViewById(R.id.pierwszaLiniaProstokat);
        TextView drugaLinia = findViewById(R.id.drugaLiniaProstokat);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaProstokat);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaProstokat);
        TextView wynik = findViewById(R.id.wynikProstokat);
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
        CheckBox checkBox = findViewById(R.id.checkboxPoleProstokat);
        if(v.getId()==R.id.jednostkaPoleProstokat){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if(v.getId()==R.id.jednostkaWynikProstokat&&checkBox.isChecked()){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaWynikProstokat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaBokAProstokat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bokA";
        }
        else if(v.getId()==R.id.jednostkaBokBProstokat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bokB";
        }
        else if(v.getId()==R.id.jednostkaObwodProstokat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="obwod";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaProstokat){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatna";
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView jednostkaWynik = findViewById(R.id.jednostkaWynikProstokat);
        TextView jednostkaBokA = findViewById(R.id.jednostkaBokAProstokat);
        TextView jednostkaBokB = findViewById(R.id.jednostkaBokBProstokat);
        TextView jednostkaPole = findViewById(R.id.jednostkaPoleProstokat);
        TextView jednostkaObwod = findViewById(R.id.jednostkaObwodProstokat);
        TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaProstokat);
        CheckBox checkBoxPole = findViewById(R.id.checkboxPoleProstokat);
        CheckBox checkBoxBokA = findViewById(R.id.checkboxBokAProstokat);
        CheckBox checkBoxBokB = findViewById(R.id.checkboxBokBProstokat);
        CheckBox checkBoxObwod = findViewById(R.id.checkboxObwodProstokat);
        CheckBox checkBoxPrzekatne = findViewById(R.id.checkboxPrzekatnaProstokat);
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    if(checkBoxBokA.isChecked()){
                        bokJA="cm";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokJB="cm";
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
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("cm");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("cm");
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
                    if(checkBoxBokA.isChecked()){
                        bokJA="dm";
                    }
                    else if (checkBoxBokB.isChecked()){
                        bokJB="dm";
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
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("dm");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("dm");
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
                    if(checkBoxBokA.isChecked()){
                        bokJA="m";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokJB="m";
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
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("m");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("m");
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
                    if(checkBoxBokA.isChecked()){
                        bokJA="km";
                    }
                    else if(checkBoxBokB.isChecked()){
                        bokJB="km";
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
                else if(ktory.equals("bokA")){
                    jednostkaBokA.setText("km");
                }
                else if(ktory.equals("bokB")){
                    jednostkaBokB.setText("km");
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
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition))))
                        .get(childPosition).toString();
                Log.i("wynik", selectedItem);
                if(items[0].equals(lstTitle.get(groupPosition)))
                    navigationManager.showFragment(selectedItem);
                else if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(Prostokat.this, Szkola.class);
                    i.putExtra("miejsce", "Prostokat");
                    startActivity(i);
                    Animatoo.animateFade(Prostokat.this);
                }
                else
                    throw new IllegalArgumentException("Not supported fragment");

                mdrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceData) {
        super.onSaveInstanceState(savedInstanceData);
        try {
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaProstokat);
            TextView drugaLinia = findViewById(R.id.drugaLiniaProstokat);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaProstokat);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaProstokat);
            TextView wynik = findViewById(R.id.wynikProstokat);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynikProstokat);
            TextView jednostkaBokA = findViewById(R.id.jednostkaBokAProstokat);
            TextView jednostkaBokB = findViewById(R.id.jednostkaBokBProstokat);
            TextView jednostkaPole = findViewById(R.id.jednostkaPoleProstokat);
            TextView jednostkaObwod = findViewById(R.id.jednostkaObwodProstokat);
            TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaProstokat);
            savedInstanceData.putString("pierwszaLinia",pierwszaLinia.getText().toString());
            savedInstanceData.putString("drugaLinia", drugaLinia.getText().toString());
            savedInstanceData.putString("trzeciaLinia", trzeciaLinia.getText().toString());
            savedInstanceData.putString("czwartaLinia", czwartaLinia.getText().toString());
            savedInstanceData.putString("wynik", wynik.getText().toString());
            savedInstanceData.putString("jednostkaWynik", jednostkaWynik.getText().toString());
            savedInstanceData.putString("jednostkaBokA", jednostkaBokA.getText().toString());
            savedInstanceData.putString("jednostkaBokB", jednostkaBokB.getText().toString());
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
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaProstokat);
        TextView drugaLinia = findViewById(R.id.drugaLiniaProstokat);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaProstokat);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaProstokat);
        TextView wynik = findViewById(R.id.wynikProstokat);
        TextView jednostkaWynik = findViewById(R.id.jednostkaWynikProstokat);
        TextView jednostkaBokA = findViewById(R.id.jednostkaBokAProstokat);
        TextView jednostkaBokB = findViewById(R.id.jednostkaBokBProstokat);
        TextView jednostkaPole = findViewById(R.id.jednostkaPoleProstokat);
        TextView jednostkaObwod = findViewById(R.id.jednostkaObwodProstokat);
        TextView jednostkaPrzekatna = findViewById(R.id.jednostkaPrzekatnaProstokat);
        pierwszaLinia.setText(savedInstanceState.getString("pierwszaLinia"));
        drugaLinia.setText(savedInstanceState.getString("drugaLinia"));
        trzeciaLinia.setText(savedInstanceState.getString("trzeciaLinia"));
        czwartaLinia.setText(savedInstanceState.getString("czwartaLinia"));
        wynik.setText(savedInstanceState.getString("wynik"));
        jednostkaWynik.setText(savedInstanceState.getString("jednostkaWynik"));
        jednostkaBokA.setText(savedInstanceState.getString("jednostkaBokA"));
        jednostkaBokB.setText(savedInstanceState.getString("jednostkaBokB"));
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
            Intent i = new Intent(Prostokat.this, Czworokaty.class);
            startActivity(i);
            Animatoo.animateFade(Prostokat.this);
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