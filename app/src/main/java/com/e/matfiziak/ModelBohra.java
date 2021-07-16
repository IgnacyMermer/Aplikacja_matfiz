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

public class ModelBohra extends AppCompatActivity {
    TextView jednostkaDLugosc;
    TextView jednostkaEnergia;
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
        setContentView(R.layout.activity_model_bohra);
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
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final EditText numer = findViewById(R.id.numerOrbity);
        final EditText numer2 = findViewById(R.id.numerOrbity2);
        final EditText energia = findViewById(R.id.energia);
        final EditText dlugosc = findViewById(R.id.dlugoscFali);
        final EditText czestotliwosc = findViewById(R.id.czestotliwosc);
        final TextView wynikEnergia = findViewById(R.id.wynikEnergia);
        Button buttonEnergia = findViewById(R.id.buttonEnergia);
        final CheckBox checkboxNumer = findViewById(R.id.checkboxNumer);
        final CheckBox checkboxEnergia = findViewById(R.id.checkboxEnergia);
        final CheckBox checkboxDlugosc = findViewById(R.id.checkboxDlugosc);
        final CheckBox checkboxCzestotliwosc = findViewById(R.id.checkboxCzestotliwosc);
        if(checkbox.equals("czestotliwosc")){
            checkboxCzestotliwosc.setChecked(true);
        }
        else if(checkbox.equals("dlugosc")){
            checkboxDlugosc.setChecked(true);
        }
        else if(checkbox.equals("energia")){
            checkboxEnergia.setChecked(true);
        }
        else if(checkbox.equals("numer")){
            checkboxNumer.setChecked(true);
        }
        final TextView wynikDziesiatki = findViewById(R.id.dziesiatkaWynik);
        final EditText dziesiatkaEnergia = findViewById(R.id.dziesiatkaEnergia);
        final EditText dziesiatkaDlugosc = findViewById(R.id.dziesiatkaDlugosc);
        final EditText dziesiatkaCzestotliwosc = findViewById(R.id.dziesiatkaCzestotliwosc);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaBohra);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaBohra);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaBohra);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaBohra);
        jednostkaDLugosc = findViewById(R.id.jednostkaDlugosc3);
        jednostkaEnergia = findViewById(R.id.jednostkaEnergia3);
        jednostkaWynik = findViewById(R.id.jednostkaWynik3);
        if(incomingList!=null){
            numer.setText(incomingList.get(0));
            numer2.setText(incomingList.get(1));
            energia.setText(incomingList.get(2));
            dziesiatkaEnergia.setText(incomingList.get(3));
            dlugosc.setText(incomingList.get(4));
            dziesiatkaDlugosc.setText(incomingList.get(5));
            czestotliwosc.setText(incomingList.get(6));
            dziesiatkaCzestotliwosc.setText(incomingList.get(7));
            pierwszaLinia.setText(incomingList.get(8));
            drugaLinia.setText(incomingList.get(9));
            trzeciaLinia.setText(incomingList.get(10));
            czwartaLinia.setText(incomingList.get(11));
            wynikEnergia.setText(incomingList.get(12));
            wynikDziesiatki.setText(incomingList.get(13));
            jednostkaWynik.setText(incomingList.get(14));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(numer.getText().toString());
                doWyslania.add(numer2.getText().toString());
                doWyslania.add(energia.getText().toString());
                doWyslania.add(dziesiatkaEnergia.getText().toString());
                doWyslania.add(dlugosc.getText().toString());
                doWyslania.add(dziesiatkaDlugosc.getText().toString());
                doWyslania.add(czestotliwosc.getText().toString());
                doWyslania.add(dziesiatkaCzestotliwosc.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynikEnergia.getText().toString());
                doWyslania.add(wynikDziesiatki.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                String ktoryCheckbox="";
                if(checkboxCzestotliwosc.isChecked()){
                    ktoryCheckbox="czestotliwosc";
                }
                else if(checkboxDlugosc.isChecked()){
                    ktoryCheckbox="dlugosc";
                }
                else if(checkboxEnergia.isChecked()){
                    ktoryCheckbox="energia";
                }
                else if(checkboxNumer.isChecked()){
                    ktoryCheckbox="numer";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(ModelBohra.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "ModelBohra");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(ModelBohra.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(ModelBohra.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "ModelBohra");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(ModelBohra.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "ModelBohra");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(ModelBohra.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "ModelBohra");
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
        jednostkaDLugosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaDLugosc);
                openContextMenu(v);
            }
        });
        jednostkaEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaEnergia);
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
        final TextView dziesiatkaWynikTekst = findViewById(R.id.dziesiatkaWynikTekst3);
        buttonEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dziesiatkaWynikTekst.setText("*10");
                    if(checkboxNumer.isChecked()){
                        int numer = 0;
                        if(!energia.getText().toString().equals("")){
                            Double a = Double.parseDouble(energia.getText().toString());
                            int dziesiatki=Integer.parseInt(dziesiatkaEnergia.getText().toString());
                            if(jednostkaEnergia.getText().toString().equals("J")){
                                a=a/1.6;
                                dziesiatki+=19;
                                if(dziesiatki!=0){
                                    if(dziesiatki<0){
                                        dziesiatki=dziesiatki*-1;
                                        for(int i=0;i<dziesiatki;i++){
                                            a=a/10;
                                        }
                                    }
                                    else {
                                        for(int i=0;i<dziesiatki;i++){
                                            a=a*10;
                                        }
                                    }
                                    dziesiatki=0;
                                }
                            }
                            pierwszaLinia.setText(Html.fromHtml("n=&#8730;(-13.6/E<sub><small><small>n</small></small></sub>)"));
                            drugaLinia.setText(Html.fromHtml("n=&#8730;(13.6/"+a+")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            numer = (int) (-13.6/a);
                            numer = (int) sqrt(numer);
                            int b = numer;
                            wynikEnergia.setText(""+b);
                            wynikDziesiatki.setText("");
                            dziesiatkaWynikTekst.setText("");
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if (checkboxEnergia.isChecked()) {
                        if((!numer.getText().toString().equals(""))&&(!numer2.getText().toString().equals(""))){
                            Double a = Double.parseDouble(numer.getText().toString());
                            Double b = Double.parseDouble(numer2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>"+a+"</small></small></sub>=-13.6/"+a+"<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>"+b+"</small></small></sub>=-13.6/"+b+"<sup><small><small>2</small></small></sup>"));
                            if(a<b){
                                trzeciaLinia.setText(Html.fromHtml("&#916;E=E<sub><small><small>"+b+"</small></small></sub>-E<sub><small><small>"+a+"</small></small></sub>"));
                                a=-13.6/a/a;
                                String y = funkcjePrzelicznikowe.intowanie(a);
                                a=Double.parseDouble(y);
                                b=-13.6/b/b;
                                String y2 = funkcjePrzelicznikowe.intowanie(b);
                                b = Double.parseDouble(y2);
                                czwartaLinia.setText(Html.fromHtml("&#916;E="+b+"-"+a));
                                b=b-a;
                                if(jednostkaWynik.getText().toString().equals("J")){
                                    b=b*1.6;
                                    String x = funkcjePrzelicznikowe.intowanie(b);
                                    wynikEnergia.setText(x);
                                    wynikDziesiatki.setText("-19");
                                }
                                else if(jednostkaWynik.getText().toString().equals("eV")){
                                    String x = funkcjePrzelicznikowe.intowanie(b);
                                    wynikEnergia.setText(x);
                                    wynikDziesiatki.setText("0");
                                }
                            }
                            else if(a>b){
                                trzeciaLinia.setText(Html.fromHtml("&#916;E=E<sub><small><small>"+a+"</small></small></sub>-E<sub><small><small>"+b+"</small></small></sub>"));
                                a=-13.6/a/a;
                                String y = funkcjePrzelicznikowe.intowanie(a);
                                b=-13.6/b/b;
                                String y2 = funkcjePrzelicznikowe.intowanie(b);
                                czwartaLinia.setText(Html.fromHtml("&#916;E="+y+"-"+y2));
                                a=a-b;
                                if(jednostkaWynik.getText().toString().equals("J")){
                                    a=a*1.6;
                                    String x = funkcjePrzelicznikowe.intowanie(a);
                                    wynikEnergia.setText(x);
                                    wynikDziesiatki.setText("-19");
                                }
                                else if(jednostkaWynik.getText().toString().equals("eV")){
                                    String x = funkcjePrzelicznikowe.intowanie(a);
                                    wynikEnergia.setText(x);
                                    wynikDziesiatki.setText("0");
                                }
                            }
                            else if(a==b){
                                wynikEnergia.setText("0");
                                wynikDziesiatki.setText("0");

                            }
                        }
                        else if(!numer.getText().toString().equals("")){
                            Double a = Double.parseDouble(numer.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>"+a+"</small></small></sub>=-13.6/n<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>"+a+"</small></small></sub>=-13.6/"+a+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a=-13.6/a/a;
                            if(jednostkaWynik.getText().toString().equals("J")){
                                a=a*1.6;
                                String x = funkcjePrzelicznikowe.intowanie(a);
                                wynikEnergia.setText(x);
                                wynikDziesiatki.setText("-19");
                            }
                            else if(jednostkaWynik.getText().toString().equals("eV")){
                                String x = funkcjePrzelicznikowe.intowanie(a);
                                wynikEnergia.setText(x);
                                wynikDziesiatki.setText("0");
                            }
                        }
                        else if((!dlugosc.getText().toString().equals(""))){
                            Double a = Double.parseDouble(dlugosc.getText().toString());
                            int dziesiatki = Integer.parseInt(dziesiatkaDlugosc.getText().toString());
                            if(jednostkaDLugosc.getText().toString().equals("cm")){
                                dziesiatki-=2;
                            }
                            else if(jednostkaDLugosc.getText().toString().equals("dm")){
                                dziesiatki-=1;
                            }
                            else if(jednostkaDLugosc.getText().toString().equals("km")){
                                dziesiatki+=3;
                            }
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>n</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/&#955;*10<sup><small><small>"+dziesiatki+"</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>n</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/"+a+"*10<sup><small><small>"+dziesiatki+"</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a=6.63*3/a;
                            dziesiatki = -26 - dziesiatki;
                            if(jednostkaWynik.getText().toString().equals("eV")){
                                a=a/1.6;
                                dziesiatki+=19;
                            }
                            wynikDziesiatki.setText(""+dziesiatki);
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynikEnergia.setText(x);
                        }
                        else if(!czestotliwosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(czestotliwosc.getText().toString());
                            int dziesiatki = Integer.parseInt(dziesiatkaCzestotliwosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>n</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*&#956;*10<sup><small><small>"+dziesiatki+"</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/(3*10<sup><small><small>8</small></small></sup>)"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>n</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*"+a+"*10<sup><small><small>"+dziesiatki+"</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a = 3/a;
                            dziesiatki = 8 - dziesiatki;
                            a=6.63*3/a;
                            dziesiatki=-26 - dziesiatki;
                            if(jednostkaWynik.getText().toString().equals("eV")){
                                dziesiatki+=19;
                                a=a/1.6;
                            }
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkboxDlugosc.isChecked()){
                        int dziesiatki=0,t=0;
                        Double dlugosc = null;
                        if(!energia.getText().toString().equals("")){
                            Double a = Double.parseDouble(energia.getText().toString());
                            if(jednostkaEnergia.getText().toString().equals("eV")){
                                t=-19;
                                a=a*1.6;
                            }
                            else if(jednostkaEnergia.getText().toString().equals("J")){
                                int y = Integer.parseInt(dziesiatkaEnergia.getText().toString());
                                t=y;
                            }
                            pierwszaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/E<sub><small><small>n</small></small></sub>*10<sup><small><small>"+t+"</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/"+a+"*10<sup><small><small>"+t+"</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            dziesiatki = -26 - t;
                            dlugosc = 6.63*3/a;
                            if(jednostkaWynik.getText().toString().equals("cm")){
                                dziesiatki+=2;
                            }
                            else if(jednostkaWynik.getText().toString().equals("dm")){
                                dziesiatki+=1;
                            }
                            else if(jednostkaWynik.getText().toString().equals("km")){
                                dziesiatki-=3;
                            }
                            String x = funkcjePrzelicznikowe.intowanie(dlugosc);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if((!numer.getText().toString().equals(""))&&(!numer2.getText().toString().equals(""))){
                            Double a = Double.parseDouble(numer.getText().toString());
                            Double b = Double.parseDouble(numer2.getText().toString());
                            Double c = null, d=null;
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>"+a+"</small></small></sub>=-13.6/"+a+"<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>"+b+"</small></small></sub>=-13.6/"+b+"<sup><small><small>2</small></small></sup>"));
                            a=-13.6/a/a;
                            b=-13.6/b/b;
                            String y = funkcjePrzelicznikowe.intowanie(a);
                            String y2 = funkcjePrzelicznikowe.intowanie(b);
                            if(a<b){
                                c=b-a;
                                c=c*1.6;
                                String y3 = funkcjePrzelicznikowe.intowanie(c);
                                trzeciaLinia.setText(Html.fromHtml("&#916;E=("+y2+"-"+y+")*1.6*10<sup><small><small>-19</small></small></sup>"));
                                czwartaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/"+y3+"*10<sup><small><small>-19</small></small></sup>"));
                                d=6.63*3/c;
                                dziesiatki = -7;
                                if(jednostkaWynik.getText().toString().equals("cm")){
                                    dziesiatki+=2;
                                }
                                else if(jednostkaWynik.getText().toString().equals("dm")){
                                    dziesiatki+=1;
                                }
                                else if(jednostkaWynik.getText().toString().equals("km")){
                                    dziesiatki-=3;
                                }
                                String x = funkcjePrzelicznikowe.intowanie(d);
                                wynikDziesiatki.setText(""+dziesiatki);
                                wynikEnergia.setText(x);
                            }
                            else if(a>b){
                                c=a-b;
                                c=c*1.6;
                                String y3 = funkcjePrzelicznikowe.intowanie(c);
                                trzeciaLinia.setText(Html.fromHtml("&#916;E=("+a+"-"+b+")*1.6*10<sup><small><small>-19</small></small></sup>"));
                                czwartaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/"+y3+"*10<sup><small><small>-19</small></small></sup>"));
                                d=6.63*3/c;
                                dziesiatki = -7;
                                if(jednostkaWynik.getText().toString().equals("cm")){
                                    dziesiatki+=2;
                                }
                                else if(jednostkaWynik.getText().toString().equals("dm")){
                                    dziesiatki+=1;
                                }
                                else if(jednostkaWynik.getText().toString().equals("km")){
                                    dziesiatki-=3;
                                }
                                String x = funkcjePrzelicznikowe.intowanie(d);
                                wynikDziesiatki.setText(""+dziesiatki);
                                wynikEnergia.setText(x);
                            }
                            else if(a==b){
                                wynikDziesiatki.setText("0");
                                wynikEnergia.setText("0");
                            }
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkboxCzestotliwosc.isChecked()){
                        int dziesiatki=0,t=0;
                        Double dlugosc = null;
                        if(!energia.getText().toString().equals("")){
                            Double a = Double.parseDouble(energia.getText().toString());
                            if(jednostkaEnergia.getText().toString().equals("eV")){
                                t=-19;
                                a=a*1.6;
                            }
                            else if(jednostkaEnergia.getText().toString().equals("J")){
                                int y = Integer.parseInt(dziesiatkaEnergia.getText().toString());
                                t=y;
                            }
                            String y3 = funkcjePrzelicznikowe.intowanie(a);
                            pierwszaLinia.setText(Html.fromHtml("&#956;=E<sub><small><small>n</small></small></sub>*10<sup><small><small>"+t+"</small></small></sup>/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                            drugaLinia.setText(Html.fromHtml("&#956;="+y3+"*10<sup><small><small>"+t+"</small></small></sup>/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            dziesiatki = -26 - t;
                            dziesiatki = 8-dziesiatki;
                            dlugosc = a/6.63;
                            String x = funkcjePrzelicznikowe.intowanie(dlugosc);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if((!numer.getText().toString().equals(""))&&(!numer2.getText().toString().equals(""))){
                            Double a = Double.parseDouble(numer.getText().toString());
                            Double b = Double.parseDouble(numer2.getText().toString());
                            Double c = null, d=null;
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>"+a+"</small></small></sub>=-13.6/"+a+"<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>"+b+"</small></small></sub>=-13.6/"+b+"<sup><small><small>2</small></small></sup>"));
                            a=-13.6/a/a;
                            String y = funkcjePrzelicznikowe.intowanie(a);
                            b=-13.6/b/b;
                            String y2 = funkcjePrzelicznikowe.intowanie(b);
                            if(a<b){
                                trzeciaLinia.setText(Html.fromHtml("&#916;E="+y2+"-"+y));
                                c=b-a;
                            }
                            else if(a>b){
                                trzeciaLinia.setText(Html.fromHtml("&#916;E="+y+"-"+y2+""));
                                c=a-b;
                            }
                            else if(a==b){
                                wynikDziesiatki.setText("0");
                                wynikEnergia.setText("0");
                                czwartaLinia.setText("");
                            }
                            if(!wynikEnergia.getText().toString().equals("0")){
                                c=c*1.6;
                                String y3 = funkcjePrzelicznikowe.intowanie(c);
                                czwartaLinia.setText(Html.fromHtml("&#956;="+y3+"*10<sup><small><small>-19</small></small></sup>/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                d=c/6.63;
                                dziesiatki = 15;
                                String x = funkcjePrzelicznikowe.intowanie(d);
                                wynikDziesiatki.setText(""+dziesiatki);
                                wynikEnergia.setText(x);
                            }
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                    wynikEnergia.setText("Wpisz liczby");
                }
            }
        });
        checkboxNumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxCzestotliwosc.setChecked(false);
                checkboxDlugosc.setChecked(false);
                checkboxEnergia.setChecked(false);
                jednostkaWynik.setText("");
                wyczyscLinie();
            }
        });
        checkboxCzestotliwosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxNumer.setChecked(false);
                checkboxDlugosc.setChecked(false);
                checkboxEnergia.setChecked(false);
                if(checkboxCzestotliwosc.isChecked()){
                    jednostkaWynik.setText("Hz");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxDlugosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxCzestotliwosc.setChecked(false);
                checkboxNumer.setChecked(false);
                checkboxEnergia.setChecked(false);
                if(checkboxDlugosc.isChecked()){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxCzestotliwosc.setChecked(false);
                checkboxDlugosc.setChecked(false);
                checkboxNumer.setChecked(false);
                if(checkboxEnergia.isChecked()){
                    jednostkaWynik.setText("eV");
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
                wynikDziesiatki.setText("");
                wynikEnergia.setText("");
                checkboxCzestotliwosc.setChecked(false);
                checkboxDlugosc.setChecked(false);
                checkboxEnergia.setChecked(false);
                checkboxNumer.setChecked(false);
                dziesiatkaCzestotliwosc.setText("");
                dziesiatkaDlugosc.setText("");
                dziesiatkaEnergia.setText("");
                numer.setText("");
                numer2.setText("");
                czestotliwosc.setText("");
                dlugosc.setText("");
                energia.setText("");
                dziesiatkaWynikTekst.setText("");
                jednostkaWynik.setText("");
                jednostkaDLugosc.setText("m");
                jednostkaEnergia.setText("J");
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        mdrawerLayout = findViewById(R.id.drawerModelBohra_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(ModelBohra.this, Konto.class);
                i.putExtra("miejsce", "ModelBohra");
                startActivity(i);
                Animatoo.animateFade(ModelBohra.this);
                return false;
            }
        });
        expandableListView.addHeaderView(listHeaderView);
        final CircleImageView imageView = listHeaderView.findViewById(R.id.avatar);
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
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaBohra);
        TextView drugaLinia = findViewById(R.id.drugaLiniaBohra);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaBohra);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaBohra);
        TextView wynik = findViewById(R.id.wynikEnergia);
        TextView dziesiatkaWynik = findViewById(R.id.dziesiatkaWynik);
        TextView dziesiatkaWynik2 = findViewById(R.id.dziesiatkaWynikTekst3);
        pierwszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
        dziesiatkaWynik.setText("");
        dziesiatkaWynik2.setText("");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaBohra);
            TextView drugaLinia = findViewById(R.id.drugaLiniaBohra);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaBohra);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaBohra);
            TextView wynik = findViewById(R.id.wynikEnergia);
            TextView dziesiatkaWynik = findViewById(R.id.dziesiatkaWynik);
            TextView dziesiatkaWynik2 = findViewById(R.id.dziesiatkaWynikTekst3);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik3);
            savedInstanceState.putString("pierwszaLinia", pierwszaLinia.getText().toString());
            savedInstanceState.putString("drugaLinia", drugaLinia.getText().toString());
            savedInstanceState.putString("trzeciaLinia", trzeciaLinia.getText().toString());
            savedInstanceState.putString("czwartaLinia", czwartaLinia.getText().toString());
            savedInstanceState.putString("wynik", wynik.getText().toString());
            savedInstanceState.putString("dziesiatkaWynik", dziesiatkaWynik.getText().toString());
            savedInstanceState.putString("dziesiatkaWynik2", dziesiatkaWynik2.getText().toString());
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaBohra);
            TextView drugaLinia = findViewById(R.id.drugaLiniaBohra);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaBohra);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaBohra);
            TextView wynik = findViewById(R.id.wynikEnergia);
            TextView dziesiatkaWynik = findViewById(R.id.dziesiatkaWynik);
            TextView dziesiatkaWynik2 = findViewById(R.id.dziesiatkaWynikTekst3);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik3);
            String pierwszaLiniaStr = savedInstanceState.getString("pierwszaLinia");
            String drugaLiniaStr = savedInstanceState.getString("drugaLinia");
            String trzeciaLiniaStr = savedInstanceState.getString("trzeciaLinia");
            String czwartaLiniaStr = savedInstanceState.getString("czwartaLinia");
            String wynikStr = savedInstanceState.getString("wynik");
            String dziesiatkaWynikStr = savedInstanceState.getString("dziesiatkaWynik");
            String dziesiatkaWynik2Str = savedInstanceState.getString("dziesiatkaWynik2");
            String jednostkaWynikStr = savedInstanceState.getString("jednostkaWynik");
            pierwszaLinia.setText(pierwszaLiniaStr);
            drugaLinia.setText(drugaLiniaStr);
            trzeciaLinia.setText(trzeciaLiniaStr);
            czwartaLinia.setText(czwartaLiniaStr);
            dziesiatkaWynik.setText(dziesiatkaWynikStr);
            dziesiatkaWynik2.setText(dziesiatkaWynik2Str);
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
        CheckBox checkBoxEnergia = findViewById(R.id.checkboxEnergia);
        CheckBox checkBoxDlugosc = findViewById(R.id.checkboxDlugosc);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.jednostkaEnergia3){
            getMenuInflater().inflate(R.menu.elektronowolty_menu, menu);
            ktory="energia";
        }
        else if(v.getId()==R.id.jednostkaWynik3&&checkBoxEnergia.isChecked()){
            getMenuInflater().inflate(R.menu.elektronowolty_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaDlugosc3){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="dlugosc";
        }
        else if(v.getId()==R.id.jednostkaWynik3&&checkBoxDlugosc.isChecked()){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.elektronowolty:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("eV");
                }
                else{
                    jednostkaEnergia.setText("eV");
                }
                Toast.makeText(this, "Elektronowolty", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dzule:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("J");
                }
                else{
                    jednostkaEnergia.setText("J");
                }
                Toast.makeText(this, "Dżule", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                }
                else{
                    jednostkaDLugosc.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                }
                else{
                    jednostkaDLugosc.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaDLugosc.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                }
                else{
                    jednostkaDLugosc.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
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
            //getSupportActionBar().setTitle(firstItem);
        }
    }
    private void setupDrawer() {
        mDrawerToogle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("EDMTDev");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle(mActivityTitle);
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
                    Intent i = new Intent(ModelBohra.this, Szkola.class);
                    i.putExtra("miejsce", "ModelBohra");
                    startActivity(i);
                    Animatoo.animateFade(ModelBohra.this);
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
            Intent i = new Intent(ModelBohra.this, Grawitacja.class);
            startActivity(i);
            Animatoo.animateFade(ModelBohra.this);
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