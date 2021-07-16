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

public class ZasadyDynamiki extends AppCompatActivity {
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
        setContentView(R.layout.activity_zasady_dynamiki);
        ScrollView relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<String>();
        try {
            Intent incomingIntent = getIntent();
            incomingList = incomingIntent.getStringArrayListExtra("lista");
            checkbox = incomingIntent.getStringExtra("checkbox");
        }
        catch (Exception ex){
            //incomingList.clear();
        }
        if(checkbox==null){
            checkbox="";
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final Button buttonDynamika = findViewById(R.id.buttonDynamika2);
        final CheckBox checkBoxPredkosc = findViewById(R.id.checkboxZmiana);
        final CheckBox checkBoxMasa = findViewById(R.id.checkboxMasa2);
        final CheckBox checkBoxCzas = findViewById(R.id.checkboxCzas);
        final CheckBox checkBoxDroga = findViewById(R.id.checkboxDroga);
        final CheckBox checkBoxSila = findViewById(R.id.checkboxSila);
        final CheckBox checkBoxPrzyszpieszenie = findViewById(R.id.checkboxPrzyszpieszenie);
        final CheckBox checkBoxSilaNacisku = findViewById(R.id.checkboxSilaNac);
        TextView jednostkaPrzyszpieszenie = findViewById(R.id.jednostkaPrzyszpieszenie5);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaDynamika);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaDynamika);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaDynamiki);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaDynamika);
        jednostkaPrzyszpieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
        final TextView jednostkaPredkoscPocz = findViewById(R.id.jednostkaPredkoscPocz5);
        jednostkaPredkoscPocz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPredkoscPocz);
                openContextMenu(v);
            }
        });
        final TextView jednostkaPredkosc = findViewById(R.id.jednostkaPredkosc5);
        jednostkaPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPredkosc);
                openContextMenu(v);
            }
        });
        final TextView jednostkaMasa1 = findViewById(R.id.jednostkaMasa51);
        jednostkaMasa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMasa1);
                openContextMenu(v);
            }
        });
        final TextView jednostkaMasa2 = findViewById(R.id.jednostkaMasa52);
        jednostkaMasa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMasa2);
                openContextMenu(v);
            }
        });
        final TextView jednostkaCzas = findViewById(R.id.jednostkaCzas5);
        jednostkaCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCzas);
                openContextMenu(v);
            }
        });
        final TextView jednostkaDroga = findViewById(R.id.jednostkaDroga5);
        jednostkaDroga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaDroga);
                openContextMenu(v);
            }
        });
        final TextView jednostkaWynik = findViewById(R.id.jednostkaWynik5);
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        final EditText predkoscPocz = findViewById(R.id.predkoscPocz);
        final EditText predkoscKonc = findViewById(R.id.predkoscKonc);
        final EditText predkosc = findViewById(R.id.zmianaPredkosc);
        final EditText masa = findViewById(R.id.masa);
        final EditText Masa2 = findViewById(R.id.masa2);
        final EditText czas = findViewById(R.id.czas);
        final EditText droga = findViewById(R.id.droga);
        final EditText sila = findViewById(R.id.sila);
        final EditText Sila2 = findViewById(R.id.sila2);
        final EditText silaOporu = findViewById(R.id.silaOporu);
        final EditText przyszpieszenie = findViewById(R.id.przyszpieszenie2);
        final TextView wynik = findViewById(R.id.wynikDynamika);
        if(checkbox.equals("predkosc")){
            checkBoxPredkosc.setChecked(true);
        }
        else if(checkbox.equals("sila")){
            checkBoxSila.setChecked(true);
        }
        else if(checkbox.equals("silaNacisku")){
            checkBoxSilaNacisku.setChecked(true);
        }
        else if(checkbox.equals("masa")){
            checkBoxMasa.setChecked(true);
        }
        else if(checkbox.equals("droga")){
            checkBoxDroga.setChecked(true);
        }
        else if(checkbox.equals("czas")){
            checkBoxCzas.setChecked(true);
        }
        else if(checkbox.equals("przyszpieszenie")){
            checkBoxPrzyszpieszenie.setChecked(true);
        }
        if(incomingList!=null){
            predkoscPocz.setText(incomingList.get(0));
            predkoscKonc.setText(incomingList.get(1));
            jednostkaPredkoscPocz.setText(incomingList.get(2));
            predkosc.setText(incomingList.get(3));
            jednostkaPredkosc.setText(incomingList.get(4));
            masa.setText(incomingList.get(5));
            jednostkaMasa1.setText(incomingList.get(6));
            Masa2.setText(incomingList.get(7));
            jednostkaMasa2.setText(incomingList.get(8));
            czas.setText(incomingList.get(9));
            jednostkaCzas.setText(incomingList.get(10));
            droga.setText(incomingList.get(11));
            jednostkaDroga.setText(incomingList.get(12));
            przyszpieszenie.setText(incomingList.get(13));
            sila.setText(incomingList.get(14));
            Sila2.setText(incomingList.get(15));
            silaOporu.setText(incomingList.get(16));
            pierwszaLinia.setText(incomingList.get(17));
            drugaLinia.setText(incomingList.get(18));
            trzeciaLinia.setText(incomingList.get(19));
            czwartaLinia.setText(incomingList.get(20));
            wynik.setText(incomingList.get(21));
            jednostkaWynik.setText(incomingList.get(22));
        }
        doWyslania = new ArrayList<String>();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(predkoscPocz.getText().toString());
                doWyslania.add(predkoscKonc.getText().toString());
                doWyslania.add(jednostkaPredkoscPocz.getText().toString());
                doWyslania.add(predkosc.getText().toString());
                doWyslania.add(jednostkaPredkosc.getText().toString());
                doWyslania.add(masa.getText().toString());
                doWyslania.add(jednostkaMasa1.getText().toString());
                doWyslania.add(Masa2.getText().toString());
                doWyslania.add(jednostkaMasa2.getText().toString());
                doWyslania.add(czas.getText().toString());
                doWyslania.add(jednostkaCzas.getText().toString());
                doWyslania.add(droga.getText().toString());
                doWyslania.add(jednostkaDroga.getText().toString());
                doWyslania.add(przyszpieszenie.getText().toString());
                doWyslania.add(sila.getText().toString());
                doWyslania.add(Sila2.getText().toString());
                doWyslania.add(silaOporu.getText().toString());
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
                else if(checkBoxMasa.isChecked()){
                    ktoryCheckbox="masa";
                }
                else if(checkBoxDroga.isChecked()){
                    ktoryCheckbox="okres";
                }
                else if(checkBoxPredkosc.isChecked()){
                    ktoryCheckbox = "predkosc";
                }
                else if(checkBoxCzas.isChecked()){
                    ktoryCheckbox="czas";
                }
                else if(checkBoxSilaNacisku.isChecked()){
                    ktoryCheckbox="silaNacisku";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(ZasadyDynamiki.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "ZasadyDynamiki");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(ZasadyDynamiki.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("miejsce", "ZasadyDynamiki");
                        i1.putExtra("lista", doWyslania);
                        i1.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(ZasadyDynamiki.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "ZasadyDynamiki");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(ZasadyDynamiki.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "ZasadyDynamiki");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(ZasadyDynamiki.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "ZasadyDynamiki");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox", ktoryCheckbox);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        buttonDynamika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPredkoscPocz = predkoscPocz.getText().toString();
                String strPredkoscKonc = predkoscKonc.getText().toString();
                String strPredkosc = predkosc.getText().toString();
                String strMasa = masa.getText().toString();
                String strCzas = czas.getText().toString();
                String strDroga = droga.getText().toString();
                String strSila = sila.getText().toString();
                String strPrzyszpieszenie = przyszpieszenie.getText().toString();
                String strSilaOporu = silaOporu.getText().toString();
                String strSila2 = Sila2.getText().toString();
                String strMasa2 = Masa2.getText().toString();
                try {
                    if(checkBoxSila.isChecked()){
                        Double sila2 = null;
                        if((!strMasa.equals(""))&&(!strPredkosc.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strMasa);
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            Double b = Double.parseDouble(strPredkosc);
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkosc.getText().toString());
                            Double c = Double.parseDouble(strCzas);
                            c = funkcjePrzelicznikowe.czas(c, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F=m*&#916;v/t"));
                            drugaLinia.setText("F="+a+"*"+b+"/"+c);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sila2 = a*b/c;
                            String x = funkcjePrzelicznikowe.intowanie(sila2);
                            wynik.setText(x);
                        }
                        else if((!strMasa.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strCzas.equals(""))&&(!strPredkoscKonc.equals(""))){
                            Double a = Double.parseDouble(strMasa);
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            Double b = Double.parseDouble(strPredkoscPocz);
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strCzas);
                            c= funkcjePrzelicznikowe.czas(c, jednostkaCzas.getText().toString());
                            Double d = Double.parseDouble(strPredkoscKonc);
                            d = funkcjePrzelicznikowe.predkosc(d, jednostkaPredkoscPocz.getText().toString());
                            if(b>d){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+b+"-"+d));
                                b=b-d;
                            }
                            else {
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+d+"-"+b));
                                b=d-b;
                            }
                            trzeciaLinia.setText(Html.fromHtml("F=m*&#916;v/t"));
                            czwartaLinia.setText("F="+a+"*"+b+"/"+c);
                            sila2 = a*b/c;
                            String x = funkcjePrzelicznikowe.intowanie(sila2);
                            wynik.setText(x);
                        }
                        else if(((!strSilaOporu.equals(""))||(!strSila.equals("")))&&(!strPrzyszpieszenie.equals(""))&&(!strMasa.equals(""))){
                            Double a;
                            if(strSilaOporu.equals("")){
                                a=Double.parseDouble(strSila);
                            }
                            else{
                                a=Double.parseDouble(strSilaOporu);
                            }
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            Double c = Double.parseDouble(strMasa);
                            c = funkcjePrzelicznikowe.masa(c, jednostkaMasa1.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F=a*m+F<sub><small><small>op</small></small></sub>"));
                            drugaLinia.setText("F="+b+"*"+c+"+"+a);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sila2 = b*c+a;
                            String x = funkcjePrzelicznikowe.intowanie(sila2);
                            wynik.setText(x);
                        }
                        else if((!strMasa.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strMasa);
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            Double b = Double.parseDouble(strDroga);
                            b = funkcjePrzelicznikowe.droga(b, jednostkaDroga.getText().toString());
                            Double c = Double.parseDouble(strPredkoscPocz);
                            Double d = Double.parseDouble(strPredkoscKonc);
                            c = funkcjePrzelicznikowe.predkosc(c, jednostkaPredkoscPocz.getText().toString());
                            d = funkcjePrzelicznikowe.predkosc(d, jednostkaPredkoscPocz.getText().toString());
                            Double e,f;
                            if(c>d){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+c+"-"+d));
                                e=c-d;
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+d+"-"+c));
                                e=d-c;
                            }
                            trzeciaLinia.setText(Html.fromHtml("F=m*&#916;v<sup><small><small>2</small></small></small></sup>/(2*s)"));
                            czwartaLinia.setText(Html.fromHtml("F="+a+"*"+e+"<sup><small><small>2</small></small></sup>/(2*"+b+")"));
                            sila2 = a*e*e/2/b;
                            String x = funkcjePrzelicznikowe.intowanie(sila2);
                            wynik.setText(x);
                        }
                        else if(((!strSila.equals(""))||(!strSila2.equals(""))||(strSilaOporu.equals("")))&&(!strMasa.equals(""))&&(!strMasa2.equals(""))){
                            Double sil = null;
                            if(!strSila.equals("")){
                                sil = Double.parseDouble(strSila);
                            }
                            else if(!strSila2.equals("")){
                                sil = Double.parseDouble(strSila2);
                            }
                            else if(!strSilaOporu.equals("")){
                                sil = Double.parseDouble(strSilaOporu);
                            }
                            else {
                                sil=0.0;
                            }
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            Double c = Double.parseDouble(strMasa2);
                            c= funkcjePrzelicznikowe.masa(c, jednostkaMasa2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>2</small></small></sub>=F<sub><small><small>1</small></small></sub>*(m<sub><small><small>1</small></small></sub>+m<sub><small><small>2</small></small></sub>)/m<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>2</small></small></sub>="+sil+"*("+b+"+"+c+")/"+c+")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sila2 = sil*(b+c)/c;
                            if(sila2==0){
                                wynik.setText("Wpisz liczby");
                            }
                            else{
                                String x = funkcjePrzelicznikowe.intowanie(sila2);
                                wynik.setText(x);
                            }
                        }
                        else if((!strPrzyszpieszenie.equals(""))&&(!strMasa.equals(""))){
                            Double a = Double.parseDouble(strMasa);
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>2</small></small></sub>=m<sub><small><small>1</small></small></sub>*a"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>2</small></small></sub>="+a+"*"+b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sila2 = a*b;
                            String x = funkcjePrzelicznikowe.intowanie(sila2);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxMasa.isChecked()){
                        Double masa2 = null;
                        if((!strSila.equals(""))&&(!strCzas.equals(""))&&(!strDroga.equals(""))){
                            Double a = Double.parseDouble(strSila);
                            Double b = Double.parseDouble(strCzas);
                            b = funkcjePrzelicznikowe.czas(b, jednostkaCzas.getText().toString());
                            Double c = Double.parseDouble(strDroga);
                            c = funkcjePrzelicznikowe.droga(c, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText("m=F*t<sup><small><small>2</small></small></sup>/(2*s)");
                            drugaLinia.setText(Html.fromHtml("m="+a+"*"+b+"<sup><small><small>2</small></small></sup>/(2*"+c+")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            masa2 = a/c*b*b/2;
                            masa2 = funkcjePrzelicznikowe.masaWynik(masa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(masa2);
                            wynik.setText(x);
                        }
                        else if((!strSila.equals(""))&&(!strDroga.equals(""))&&(!strPredkosc.equals(""))){
                            Double a = Double.parseDouble(strSila);
                            Double b = Double.parseDouble(strDroga);
                            b= funkcjePrzelicznikowe.droga(b, jednostkaDroga.getText().toString());
                            Double c = Double.parseDouble(strPredkosc);
                            c = funkcjePrzelicznikowe.predkosc(c, jednostkaPredkosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("m=F*2*s/v<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("m="+a+"*2*"+b+"/"+c+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            masa2 = a/c/c*2*b;
                            masa2 = funkcjePrzelicznikowe.masaWynik(masa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(masa2);
                            wynik.setText(x);
                        }
                        else if((!strSila.equals(""))&&(!strDroga.equals(""))&&(!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))){
                            Double a = Double.parseDouble(strSila);
                            Double b = Double.parseDouble(strDroga);
                            b = funkcjePrzelicznikowe.droga(b, jednostkaDroga.getText().toString());
                            Double c = Double.parseDouble(strPredkoscPocz);
                            c= funkcjePrzelicznikowe.predkosc(c, jednostkaPredkoscPocz.getText().toString());
                            Double d = Double.parseDouble(strPredkoscKonc);
                            d = funkcjePrzelicznikowe.predkosc(d, jednostkaPredkoscPocz.getText().toString());
                            if(c>d){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+c+"-"+d));
                                c=c-d;
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+d+"-"+c));
                                c=d-c;
                            }
                            trzeciaLinia.setText(Html.fromHtml("m=2*F*s/&#916;v<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("m=2*"+a+"*"+b+"/"+c+"<sup><small><small>2</small></small></sup>"));
                            masa2 = a/c/c*2*b;
                            masa2 = funkcjePrzelicznikowe.masaWynik(masa2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(masa2);
                            wynik.setText(x);
                        }
                        else {
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxDroga.isChecked()){
                        Double droga2 = null;
                        if((!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))&&(!strMasa.equals(""))&&((!strSilaOporu.equals(""))||(!strSila.equals("")))){
                            Double a = Double.parseDouble(strPredkoscPocz);
                            Double b = Double.parseDouble(strPredkoscKonc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strMasa);
                            c = funkcjePrzelicznikowe.masa(c, jednostkaMasa1.getText().toString());
                            Double d;
                            if(!strSila.equals("")){
                                d = Double.parseDouble(strSila);
                            }
                            else{
                                d=Double.parseDouble(strSilaOporu);
                            }
                            Double e;
                            if(a>b){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                e=a-b;
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                e=b-a;
                            }
                            drugaLinia.setText(Html.fromHtml("a=F<sub><small><small>op</small></small></sub>/m"));
                            Double przysz = d/c;
                            trzeciaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                            Double czas2 = e/przysz;
                            czwartaLinia.setText(Html.fromHtml("s=a*t<sup><small><small>2</small></small></sup>/2"));
                            droga2 = przysz*czas2*czas2/2;
                            droga2 = funkcjePrzelicznikowe.drogaWynik(droga2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga2);
                            wynik.setText(x);
                        }
                        else if((!strPredkosc.equals(""))&&(!strMasa.equals(""))&&((!strSilaOporu.equals(""))||(!strSila.equals("")))){
                            Double a = Double.parseDouble(strPredkosc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            Double c;
                            if(!strSilaOporu.equals("")){
                                c = Double.parseDouble(strSilaOporu);
                                pierwszaLinia.setText(Html.fromHtml("a=F<sub><small><small>op</small></small></sub>/m"));
                            }
                            else{
                                c = Double.parseDouble(strSilaOporu);
                                pierwszaLinia.setText(Html.fromHtml("a=F/m"));
                            }
                            Double przysz = c/b;
                            drugaLinia.setText(Html.fromHtml("t=&#916;v/a"));
                            Double czas2 = a/przysz;
                            trzeciaLinia.setText(Html.fromHtml("s=a*t<sup><small><small>2</small></small></sup>/2"));
                            czwartaLinia.setText(Html.fromHtml("s="+przysz+"*"+czas2+"<sup><small><small>2</small></small></sup>/2"));
                            droga2 = przysz*czas2*czas2/2;
                            droga2 = funkcjePrzelicznikowe.drogaWynik(droga2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(droga2);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxPrzyszpieszenie.isChecked()){
                        Double przysz = null;
                        if((!strSila.equals(""))&&(!strMasa.equals(""))&&(!strMasa2.equals(""))){
                            Double a = Double.parseDouble(strSila);
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            Double c = Double.parseDouble(strMasa2);
                            c = funkcjePrzelicznikowe.masa(c, jednostkaMasa2.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a=F/(m<sub><small><small>1</small></small></sub>+m<sub><small><small>2</small></small></sub>)"));
                            drugaLinia.setText("a="+a+"/("+b+"+"+c+")");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            przysz = a/(b+c);
                            String x = funkcjePrzelicznikowe.intowanie(przysz);
                            wynik.setText(x);
                        }
                        else if((!strPredkoscPocz.equals(""))&&(!strPredkoscKonc.equals(""))&&(!strCzas.equals(""))){
                            Double a = Double.parseDouble(strPredkoscPocz);
                            Double b = Double.parseDouble(strPredkoscKonc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkoscPocz.getText().toString());
                            Double c = Double.parseDouble(strCzas);
                            c = funkcjePrzelicznikowe.czas(c, jednostkaCzas.getText().toString());
                            Double e,f;
                            if(a>b){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+a+"-"+b));
                                e=a-b;
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+b+"-"+a));
                                e=b-a;
                            }
                            trzeciaLinia.setText(Html.fromHtml("a=&#916;v/t"));
                            czwartaLinia.setText("a="+e+"/"+c);
                            przysz = e/c;
                            String x = funkcjePrzelicznikowe.intowanie(przysz);
                            wynik.setText(x);
                        }
                        else if((!strSila.equals(""))&&(!strMasa.equals(""))){
                            Double a = Double.parseDouble(strSila);
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            pierwszaLinia.setText("a=F/m");
                            drugaLinia.setText("a="+a+"/"+b);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            przysz = a/b;
                            String x = funkcjePrzelicznikowe.intowanie(przysz);
                            wynik.setText(x);
                        }
                        else {
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxSilaNacisku.isChecked()){
                        Double nacisk = null;
                        if((!strMasa.equals(""))&&(!strMasa2.equals(""))&&(!strSila.equals(""))){
                            Double a = Double.parseDouble(strMasa);
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            Double b = Double.parseDouble(strMasa2);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa2.getText().toString());
                            Double c = Double.parseDouble(strSila);
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>N</small></small></sub>=m<sub><small><small>2</small></small></sub>*F/(m<sub><small><small>1</small></small></sub>+m<sub><small><small>2</small></small></sub>)"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>N</small></small></sub>="+b+"*"+c+"/("+a+"+"+b+")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            nacisk = b*c/(a+b);
                            String x = funkcjePrzelicznikowe.intowanie(nacisk);
                            wynik.setText(x);
                        }
                        else if((!strMasa.equals(""))&&(!strPrzyszpieszenie.equals(""))){
                            Double a = Double.parseDouble(strMasa);
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            Double b = Double.parseDouble(strPrzyszpieszenie);
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>N</small></small></sub>=m*a"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>N</small></small></sub>="+a+"*"+b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            nacisk = a*b;
                            String x = funkcjePrzelicznikowe.intowanie(nacisk);
                            wynik.setText(x);
                        }
                        else if((!masa.getText().toString().equals(""))){
                            Double a = Double.parseDouble(masa.getText().toString());
                            a = funkcjePrzelicznikowe.masa(a, jednostkaMasa1.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>N</small></small></sub>=m*g"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>N</small></small></sub>="+a+"*9.81"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            nacisk = a * 9.81;
                            String x = funkcjePrzelicznikowe.intowanie(nacisk);
                            wynik.setText(x);
                        }
                        else {
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxCzas.isChecked()){
                        Double czas=null;
                        if((!strPredkosc.equals(""))&&(!strMasa.equals(""))&&(!strSilaOporu.equals(""))){
                            Double a = Double.parseDouble(strPredkosc);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            Double c = Double.parseDouble(strSilaOporu);
                            pierwszaLinia.setText(Html.fromHtml("t=m*&#916;v/F<sub><small><small>op</small></small></sub>"));
                            drugaLinia.setText("t="+b+"*"+a+"/"+c);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double d = b*a/c;
                            czas=d;
                            czas = funkcjePrzelicznikowe.czasWynik(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(czas);
                            wynik.setText(x);
                        }
                        else if((!strPredkoscPocz.equals(""))&&(!strMasa.equals(""))&&(!strSilaOporu.equals(""))&&(!strPredkoscKonc.equals(""))){
                            Double a = Double.parseDouble(strPredkoscPocz);
                            a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkoscPocz.getText().toString());
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            Double c = Double.parseDouble(strSilaOporu);
                            Double e = Double.parseDouble(strPredkoscKonc);
                            e = funkcjePrzelicznikowe.predkosc(e, jednostkaPredkoscPocz.getText().toString());
                            if(a>e){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>o</small></small></sub>-v<sub><small><small>k</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+a+"-"+e));
                                a=a-e;
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=v<sub><small><small>k</small></small></sub>-v<sub><small><small>o</small></small></sub>"));
                                drugaLinia.setText(Html.fromHtml("&#916;v="+e+"-"+a));
                                a=e-a;
                            }
                            trzeciaLinia.setText(Html.fromHtml("t=m*&#916;v/F<sub><small><small>op</small></small></sub>"));
                            czwartaLinia.setText("t="+b+"*"+a+"/"+c);
                            Double d = b*a/c;
                            czas=d;
                            czas= funkcjePrzelicznikowe.czasWynik(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(czas);
                            wynik.setText(x);
                        }
                        else if((!strDroga.equals(""))&&(!strMasa.equals(""))&&(!strSilaOporu.equals(""))){
                            Double a = Double.parseDouble(strDroga);
                            a = funkcjePrzelicznikowe.droga(a, jednostkaDroga.getText().toString());
                            Double b = Double.parseDouble(strMasa);
                            b = funkcjePrzelicznikowe.masa(b, jednostkaMasa1.getText().toString());
                            Double c = Double.parseDouble(strSilaOporu);
                            pierwszaLinia.setText(Html.fromHtml("t=&#8730;(2*m*s/F<sub><small><small>op</small></small></sub>)"));
                            drugaLinia.setText(Html.fromHtml("t=&#8730;(2*"+b+"*"+a+"/"+c+")"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double d = 2*b*a/c;
                            d=sqrt(d);
                            czas = d;
                            czas = funkcjePrzelicznikowe.czasWynik(czas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(czas);
                            wynik.setText(x);
                        }
                        else {
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxPredkosc.isChecked()){
                        Double predkosc = null;
                        if((!strCzas.equals(""))&&(!strMasa.equals(""))&&((!strSilaOporu.equals(""))||(!strSila.equals("")))){
                            Double a;
                            if(!strSilaOporu.equals("")){
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=F<sub><small><small>op</small></small></sub>*t/m"));
                                a = Double.parseDouble(strSilaOporu);
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=F*t/m"));
                                a = Double.parseDouble(strSila);
                            }
                            Double b = Double.parseDouble(strMasa);
                            Double c = Double.parseDouble(strCzas);
                            drugaLinia.setText(Html.fromHtml("&#916;v="+a+"*"+c+"/"+b));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            predkosc = a*c/b;
                            predkosc = funkcjePrzelicznikowe.predkoscWynik(predkosc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(predkosc);
                            wynik.setText(x);
                        }
                        else if((!strDroga.equals(""))&&(!strMasa.equals(""))&&((!strSilaOporu.equals(""))||(!strSila.equals("")))){
                            Double a;
                            if(strSilaOporu.equals("")){
                                a = Double.parseDouble(strSila);
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=&#8730;(F/(2*m*s))"));
                            }
                            else{
                                pierwszaLinia.setText(Html.fromHtml("&#916;v=&#8730;(F<sub><small><small>op</small></small></sub>/(2*m*s))"));
                                a = Double.parseDouble(strSilaOporu);
                            }
                            Double b = Double.parseDouble(strMasa);
                            Double c = Double.parseDouble(strDroga);
                            drugaLinia.setText(Html.fromHtml("&#916;v=&#8730;("+a+"/(2*"+b+"*"+c+"))"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            predkosc = a/b*c*2;
                            predkosc = sqrt(predkosc);
                            predkosc = funkcjePrzelicznikowe.predkoscWynik(predkosc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(predkosc);
                            wynik.setText(x);
                        }
                        else {
                            wyczyscLinie();
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                    wynik.setText("Wpisz liczby");
                }
            }
        });
        checkBoxCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                if(checkBoxCzas.isChecked()){
                    jednostkaWynik.setText("s");
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxDroga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                if(checkBoxDroga.isChecked()){
                    jednostkaWynik.setText("m");
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                if(checkBoxMasa.isChecked()){
                    jednostkaWynik.setText("kg");
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                if(checkBoxPredkosc.isChecked()){
                    jednostkaWynik.setText("m/s");
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                if(checkBoxSila.isChecked()){
                    jednostkaWynik.setText("N");
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzyszpieszenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                if(checkBoxPrzyszpieszenie.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxSilaNacisku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                if(checkBoxSilaNacisku.isChecked()){
                    jednostkaWynik.setText("N");
                }
                else {
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        Button buttonCzysc = findViewById(R.id.czyscicielek);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxCzas.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPrzyszpieszenie.setChecked(false);
                checkBoxSilaNacisku.setChecked(false);
                jednostkaCzas.setText("s");
                jednostkaDroga.setText("m");
                jednostkaMasa1.setText("kg");
                jednostkaMasa2.setText("kg");
                jednostkaPredkosc.setText("m/s");
                jednostkaPredkoscPocz.setText("m/s");
                jednostkaWynik.setText("");
                czas.setText("");
                predkosc.setText("");
                predkoscKonc.setText("");
                predkoscPocz.setText("");
                droga.setText("");
                masa.setText("");
                Masa2.setText("");
                sila.setText("");
                silaOporu.setText("");
                Sila2.setText("");
                przyszpieszenie.setText("");
                wynik.setText("");
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        mdrawerLayout = findViewById(R.id.drawerZasadyDynamiki_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(ZasadyDynamiki.this, Konto.class);
                i.putExtra("miejsce", "ZasadyDynamiki");
                startActivity(i);
                Animatoo.animateFade(ZasadyDynamiki.this);
                return false;
            }
        });
        expandableListView.addHeaderView(listHeaderView);
        final CircleImageView imageView = listHeaderView.findViewById(R.id.avatar);
        final TextView nickTe = listHeaderView.findViewById(R.id.name);
        dane dane1 = new dane();
        String imageUrl = dane1.imageUrl;
        String nick = dane1.nick;
        System.out.println(dane1.nick);
        if(!nick.equals("")){
            nickTe.setText(nick);
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
    public void wyczyscLinie(){
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaDynamika);
        TextView drugaLinia = findViewById(R.id.drugaLiniaDynamika);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaDynamiki);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaDynamika);
        TextView wynik = findViewById(R.id.wynikDynamika);
        pierwszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        CheckBox checkBoxPredkosc = findViewById(R.id.checkboxZmiana);
        CheckBox checkBoxMasa = findViewById(R.id.checkboxMasa2);
        CheckBox checkBoxCzas = findViewById(R.id.checkboxCzas);
        CheckBox checkBoxDroga = findViewById(R.id.checkboxDroga);
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.jednostkaPredkoscPocz5){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="predkosc1";
        }
        else if (v.getId() == R.id.jednostkaPredkosc5) {
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="predkosc";
        }
        else if(v.getId()==R.id.jednostkaWynik5&&checkBoxPredkosc.isChecked()){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaMasa51){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="masa1";
        }
        else if(v.getId()==R.id.jednostkaMasa52){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="masa2";
        }
        else if (v.getId() == R.id.jednostkaWynik5 && checkBoxMasa.isChecked()) {
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaCzas5){
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory="czas";
        }
        else if(v.getId()==R.id.jednostkaWynik5&&checkBoxCzas.isChecked()){
            getMenuInflater().inflate(R.menu.czas2_menu,menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaDroga5){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="dlugosc";
        }
        else if(v.getId()==R.id.jednostkaWynik5&&checkBoxDroga.isChecked()){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView jednostkaPredkoscPocz = findViewById(R.id.jednostkaPredkoscPocz5);
        TextView jednostkaPredkosc = findViewById(R.id.jednostkaPredkosc5);
        TextView jednostkaMasa1 = findViewById(R.id.jednostkaMasa51);
        TextView jednostkaMasa2 = findViewById(R.id.jednostkaMasa52);
        TextView jednostkaCzas = findViewById(R.id.jednostkaCzas5);
        TextView jednostkaDroga = findViewById(R.id.jednostkaDroga5);
        TextView jednostkaWynik = findViewById(R.id.jednostkaWynik5);
        switch (item.getItemId()){
            case R.id.MnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/s");
                }
                else if(ktory.equals("predkosc1")){
                    jednostkaPredkoscPocz.setText("m/s");
                }
                else{
                    jednostkaPredkosc.setText("m/s");
                }
                Toast.makeText(this, "Metr na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/m");
                }
                else if(ktory.equals("predkosc1")){
                    jednostkaPredkoscPocz.setText("m/m");
                }
                else{
                    jednostkaPredkosc.setText("m/m");
                }
                Toast.makeText(this, "Metr na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/s");
                }
                else if(ktory.equals("predkosc1")){
                    jednostkaPredkoscPocz.setText("km/s");
                }
                else{
                    jednostkaPredkosc.setText("km/s");
                }
                Toast.makeText(this, "Kilometr na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/m");
                }
                else if(ktory.equals("predkosc1")){
                    jednostkaPredkoscPocz.setText("km/m");
                }
                else{
                    jednostkaPredkosc.setText("km/m");
                }
                Toast.makeText(this, "Kiloetr na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaH:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/h");
                }
                else if(ktory.equals("predkosc1")){
                    jednostkaPredkoscPocz.setText("km/h");
                }
                else{
                    jednostkaPredkosc.setText("km/h");
                }
                Toast.makeText(this, "Kilometr na godzinę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("g");
                }
                else if(ktory.equals("masa1")){
                    jednostkaMasa1.setText("g");
                }
                else{
                    jednostkaMasa2.setText("g");
                }
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dag");
                }
                else if(ktory.equals("masa1")){
                    jednostkaMasa1.setText("dag");
                }
                else{
                    jednostkaMasa2.setText("dag");
                }
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option4:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("kg");
                }
                else if(ktory.equals("masa1")){
                    jednostkaMasa1.setText("kg");
                }
                else{
                    jednostkaMasa2.setText("kg");
                }
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option5:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("t");
                }
                else if(ktory.equals("masa1")){
                    jednostkaMasa1.setText("t");
                }
                else{
                    jednostkaMasa2.setText("t");
                }
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                }
                else{
                    jednostkaDroga.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                }
                else{
                    jednostkaDroga.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaDroga.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
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
                else {
                    jednostkaCzas.setText("h");
                }
                Toast.makeText(this, "Godzina", Toast.LENGTH_SHORT).show();
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
                    Intent i = new Intent(ZasadyDynamiki.this, Szkola.class);
                    i.putExtra("miejsce", "ZasadyDynamiki");
                    startActivity(i);
                    Animatoo.animateFade(ZasadyDynamiki.this);
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
            Intent i = new Intent(ZasadyDynamiki.this, FizykaKalkulator.class);
            startActivity(i);
            Animatoo.animateFade(ZasadyDynamiki.this);
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaDynamika);
            TextView drugaLinia = findViewById(R.id.drugaLiniaDynamika);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaDynamiki);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaDynamika);
            TextView wynik = findViewById(R.id.wynikDynamika);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik5);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaDynamika);
            TextView drugaLinia = findViewById(R.id.drugaLiniaDynamika);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaDynamiki);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaDynamika);
            TextView wynik = findViewById(R.id.wynikDynamika);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik5);
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