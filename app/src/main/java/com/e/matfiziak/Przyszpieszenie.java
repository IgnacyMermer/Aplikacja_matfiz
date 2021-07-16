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

public class Przyszpieszenie extends AppCompatActivity {
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
        setContentView(R.layout.activity_przyszpieszenie);
        ScrollView relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        animationDrawable.start();
        final EditText masaPrzyciaganie1 = findViewById(R.id.masaPrzyciaganie1);
        final EditText masaPrzyciaganie2 = findViewById(R.id.masaPrzyciaganie2);
        final EditText odlegloscPrzyciaganie = findViewById(R.id.odlegloscPrzyciaganie);
        final EditText masaPrzyciaganieDziesiatki1 = findViewById(R.id.dziesiatkaMasa1);
        final EditText masaPrzyciaganieDziesiatki2 = findViewById(R.id.dziesiatkaMasa2);
        final EditText odlegloscPrzyciaganieDziesiatki = findViewById(R.id.dziesiatkaMasa3);
        final TextView wynikDziesiatkiPrzyciaganie = findViewById(R.id.wynikPrzyciaganieDziesiatki);
        final TextView wynikPrzyciaganie = findViewById(R.id.wynikPrzyciaganie);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        Button buttonGrawitacja1 = findViewById(R.id.buttonGrawitacja1);
        buttonGrawitacja1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String masaPrzyciagania1Str = masaPrzyciaganie1.getText().toString();
                    final Double masaPrzyciagania1Int = Double.parseDouble(masaPrzyciagania1Str);
                    String masaPrzyciagania2Str = masaPrzyciaganie2.getText().toString();
                    final Double masaPrzyciagania2Int = Double.parseDouble(masaPrzyciagania2Str);
                    String odlegloscPrzyciaganieStr = odlegloscPrzyciaganie.getText().toString();
                    final Double odlegloscPrzyciaganieInt = Double.parseDouble(odlegloscPrzyciaganieStr);
                    final int masaPrzyciaganieDz = Integer.parseInt(masaPrzyciaganieDziesiatki1.getText().toString());
                    final int masaPrzyciaganie2Dz = Integer.parseInt(masaPrzyciaganieDziesiatki2.getText().toString());
                    final int odlegloscPrzyciaganieDz = Integer.parseInt(odlegloscPrzyciaganieDziesiatki.getText().toString());
                    final TextView f = findViewById(R.id.jednostkaOdlegloscPrzyciaganie);
                    final TextView g = findViewById(R.id.jednostkaMasaPrzyciaganie);
                    Double a;
                    a = (6.67 * masaPrzyciagania1Int * masaPrzyciagania2Int);
                    a = a / (odlegloscPrzyciaganieInt * odlegloscPrzyciaganieInt);
                    int dziesiatki = -11 + masaPrzyciaganieDz + masaPrzyciaganie2Dz - odlegloscPrzyciaganieDz - odlegloscPrzyciaganieDz;
                    if (f.getText().toString().equals("km")) {
                        dziesiatki -= 6;
                    } else if (f.getText().toString().equals("dm")) {
                        dziesiatki += 2;
                    } else if (f.getText().toString().equals("cm")) {
                        dziesiatki += 4;
                    }
                    if (g.getText().toString().equals("t")) {
                        dziesiatki += 6;
                    } else if (g.getText().toString().equals("dag")) {
                        dziesiatki -= 4;
                    } else if (g.getText().toString().equals("g")) {
                        dziesiatki -= 6;
                    }
                    String x = funkcjePrzelicznikowe.intowanie(a);
                    wynikPrzyciaganie.setText(x);
                    wynikDziesiatkiPrzyciaganie.setText("" + dziesiatki);
                } catch (Exception ex) {
                    wynikPrzyciaganie.setText("Wpisz liczby");
                }
            }
        });
        final TextView pole1 = findViewById(R.id.editText4);
        final TextView pole2 = findViewById(R.id.editText5);
        final TextView pole3 = findViewById(R.id.editText6);
        Button button = findViewById(R.id.buttonGestosc);
        final TextView jednostkaMasaGestosc = findViewById(R.id.jednostkaMasaGestosc);
        final TextView jednostkaObjetoscGestosc = findViewById(R.id.jednostkaObjetoscGestosc);
        jednostkaObjetoscGestosc.setText(Html.fromHtml("m<sup><small><small>3</small></small></sup>"));
        final TextView jednostkaGestosc = findViewById(R.id.jednostkaGestosc);
        jednostkaGestosc.setText(Html.fromHtml("kg/m<sup><small><small>3</small></small></sup>"));
        jednostkaMasaGestosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMasaGestosc);
                ktory = "gestosc";
                openContextMenu(v);
            }
        });
        jednostkaObjetoscGestosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaObjetoscGestosc);
                openContextMenu(v);
            }
        });
        jednostkaGestosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaGestosc);
                openContextMenu(v);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String co = null;
                try {
                    String masa = pole2.getText().toString();
                    String gestosc = pole3.getText().toString();
                    String objetosc = pole1.getText().toString();
                    Double a = null;
                    if ((!masa.equals("")) && (!gestosc.equals(""))) {
                        Double masaInt = Double.parseDouble(masa);
                        masaInt = funkcjePrzelicznikowe.masa(masaInt, jednostkaMasaGestosc.getText().toString());
                        Double gestoscsInt = Double.parseDouble(gestosc);
                        gestoscsInt = funkcjePrzelicznikowe.gestosc(gestoscsInt, jednostkaGestosc.getText().toString());
                        a = masaInt / gestoscsInt;
                        a = funkcjePrzelicznikowe.powierzchniaWynik(a, jednostkaObjetoscGestosc.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(a);
                        pole1.setText(x);
                    }
                    if ((!objetosc.equals("")) && (!gestosc.equals(""))) {
                        Double objetoscInt = Double.parseDouble(objetosc);
                        objetoscInt = funkcjePrzelicznikowe.powierzchnia(objetoscInt, jednostkaObjetoscGestosc.getText().toString());
                        Double gestoscsInt = Double.parseDouble(gestosc);
                        gestoscsInt = funkcjePrzelicznikowe.gestosc(gestoscsInt, jednostkaGestosc.getText().toString());
                        a = objetoscInt * gestoscsInt;
                        a = funkcjePrzelicznikowe.masaWynik(a, jednostkaMasaGestosc.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(a);
                        pole2.setText(x);
                    }
                    if ((!objetosc.equals("")) && (!masa.equals(""))) {
                        Double objetoscInt = Double.parseDouble(objetosc);
                        objetoscInt = funkcjePrzelicznikowe.powierzchnia(objetoscInt, jednostkaObjetoscGestosc.getText().toString());
                        Double masaInt = Double.parseDouble(masa);
                        masaInt = funkcjePrzelicznikowe.masa(masaInt, jednostkaMasaGestosc.getText().toString());
                        a = masaInt / objetoscInt;
                        a = funkcjePrzelicznikowe.gestoscWynik(a, jednostkaGestosc.getText().toString());
                        String x = funkcjePrzelicznikowe.intowanie(a);
                        pole3.setText(x);
                    }
                } catch (Exception ex) {
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        final EditText pole4 = findViewById(R.id.editText01);
        final EditText pole5 = findViewById(R.id.editText02);
        final EditText pole6 = findViewById(R.id.editText03);
        Button button1 = findViewById(R.id.buttonPorownanie);
        final TextView wynik = findViewById(R.id.wynikPorownania);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String co = null;
                try {
                    Double a = null;
                    if ((!pole5.getText().toString().equals("")) && (!pole6.getText().toString().equals(""))) {
                        String masa2 = pole5.getText().toString();
                        final Double masa2Int = Double.parseDouble(masa2);
                        String porownanie = pole6.getText().toString();
                        final Double porownanieInt = Double.parseDouble(porownanie);
                        a = masa2Int * porownanieInt;
                    }
                    else if ((!pole4.getText().toString().equals("")) && (!pole6.getText().toString().equals(""))) {
                        String masa1 = pole4.getText().toString();
                        final Double masa1Int = Double.parseDouble(masa1);
                        String porownanie = pole6.getText().toString();
                        final Double porownanieInt = Double.parseDouble(porownanie);
                        a = masa1Int / porownanieInt;
                    }
                    else if ((!pole4.getText().toString().equals("")) && (!pole5.getText().toString().equals(""))) {
                        String masa1 = pole4.getText().toString();
                        final Double masa1Int = Double.parseDouble(masa1);
                        String masa2 = pole5.getText().toString();
                        final Double masa2Int = Double.parseDouble(masa2);
                        a = masa1Int / masa2Int;
                    }
                    String x = funkcjePrzelicznikowe.intowanie(a);
                    wynik.setText(x);
                } catch (Exception ex) {
                    wynik.setText("Wpisz liczby");
                }
            }
        });
        final EditText pole7 = findViewById(R.id.editText001);
        final EditText pole8 = findViewById(R.id.editText002);
        Button button001 = findViewById(R.id.buttonCiezar1);
        final TextView wynikCiezar = findViewById(R.id.wynikCiezar);
        button001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String masa1 = pole7.getText().toString();
                    String ciezar1 = pole8.getText().toString();
                    if (!ciezar1.equals("")) {
                        Double ciezar = Double.parseDouble(ciezar1);
                        Double masa = ciezar / 9.81;
                        String x = funkcjePrzelicznikowe.intowanie(masa);
                        wynikCiezar.setText(x);
                    }
                    if (!masa1.equals("")) {
                        Double masa = Double.parseDouble(masa1);
                        Double ciezar = masa * 9.81;
                        String x = funkcjePrzelicznikowe.intowanie(ciezar);
                        wynikCiezar.setText(x);
                    }
                } catch (Exception ex) {
                    wynikCiezar.setText("Wpisz liczby");
                }
            }
        });
        final TextView jednostkaOdlegloscPrzyciaganie = findViewById(R.id.jednostkaOdlegloscPrzyciaganie);
        jednostkaOdlegloscPrzyciaganie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaOdlegloscPrzyciaganie);
                openContextMenu(v);
            }
        });
        final TextView jednostkaMasaPrzyciaganie = findViewById(R.id.jednostkaMasaPrzyciaganie);
        jednostkaMasaPrzyciaganie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMasaPrzyciaganie);
                ktory = "przyciaganie";
                openContextMenu(v);
            }
        });
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
        if(incomingList!=null){
            masaPrzyciaganie1.setText(incomingList.get(0));
            masaPrzyciaganieDziesiatki1.setText(incomingList.get(1));
            masaPrzyciaganie2.setText(incomingList.get(2));
            masaPrzyciaganieDziesiatki2.setText(incomingList.get(3));
            odlegloscPrzyciaganie.setText(incomingList.get(4));
            odlegloscPrzyciaganieDziesiatki.setText(incomingList.get(5));
            pole1.setText(incomingList.get(6));
            pole2.setText(incomingList.get(7));
            pole3.setText(incomingList.get(8));
            pole4.setText(incomingList.get(9));
            pole5.setText(incomingList.get(10));
            pole6.setText(incomingList.get(11));
            pole7.setText(incomingList.get(12));
            pole8.setText(incomingList.get(13));
            wynikPrzyciaganie.setText(incomingList.get(14));
            wynikDziesiatkiPrzyciaganie.setText(incomingList.get(15));
            wynik.setText(incomingList.get(16));
            wynikCiezar.setText(incomingList.get(17));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(masaPrzyciaganie1.getText().toString());
                doWyslania.add(masaPrzyciaganieDziesiatki1.getText().toString());
                doWyslania.add(masaPrzyciaganie2.getText().toString());
                doWyslania.add(masaPrzyciaganieDziesiatki2.getText().toString());
                doWyslania.add(odlegloscPrzyciaganie.getText().toString());
                doWyslania.add(odlegloscPrzyciaganieDziesiatki.getText().toString());
                doWyslania.add(pole1.getText().toString());
                doWyslania.add(pole2.getText().toString());
                doWyslania.add(pole3.getText().toString());
                doWyslania.add(pole4.getText().toString());
                doWyslania.add(pole5.getText().toString());
                doWyslania.add(pole6.getText().toString());
                doWyslania.add(pole7.getText().toString());
                doWyslania.add(pole8.getText().toString());
                doWyslania.add(wynikPrzyciaganie.getText().toString());
                doWyslania.add(wynikDziesiatkiPrzyciaganie.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(wynikCiezar.getText().toString());
                String ktoryCheckbox="";
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(Przyszpieszenie.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Przyszpieszenie");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Przyszpieszenie.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Przyszpieszenie.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Przyszpieszenie");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Przyszpieszenie.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Przyszpieszenie");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Przyszpieszenie.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Przyszpieszenie");
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
        final Button buttonCzysc = findViewById(R.id.czyscicielek);
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masaPrzyciaganie1.setText("");
                masaPrzyciaganie2.setText("");
                masaPrzyciaganieDziesiatki1.setText("");
                masaPrzyciaganieDziesiatki2.setText("");
                jednostkaMasaGestosc.setText("kg");
                jednostkaGestosc.setText(Html.fromHtml("kg/m<sup><small><small>3</small></small></sup>"));
                jednostkaMasaPrzyciaganie.setText("kg");
                jednostkaObjetoscGestosc.setText(Html.fromHtml("m<sup><small><small>3</small></small></sup>"));
                jednostkaOdlegloscPrzyciaganie.setText("m");
                odlegloscPrzyciaganie.setText("");
                odlegloscPrzyciaganieDziesiatki.setText("");
                wynikCiezar.setText("");
                wynik.setText("");
                wynikDziesiatkiPrzyciaganie.setText("");
                wynikPrzyciaganie.setText("");
                pole1.setText("");
                pole3.setText("");
                pole2.setText("");
                pole4.setText("");
                pole5.setText("");
                pole6.setText("");
                pole7.setText("");
                pole8.setText("");
            }
        });
        mdrawerLayout = findViewById(R.id.drawerPrzyszpieszenie_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Przyszpieszenie.this, Konto.class);
                i.putExtra("miejsce", "Przyszpieszenie");
                startActivity(i);
                Animatoo.animateFade(Przyszpieszenie.this);
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
    String ktory = "";
    String co = "", co2 = "";
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            EditText masa1 = findViewById(R.id.masaPrzyciaganie1);
            EditText masa2 = findViewById(R.id.masaPrzyciaganie2);
            EditText masa1dziesiatki = findViewById(R.id.dziesiatkaMasa1);
            EditText masa2dziesiatki = findViewById(R.id.dziesiatkaMasa2);
            EditText odleglosc = findViewById(R.id.odlegloscPrzyciaganie);
            EditText odlegloscDziesiatki = findViewById(R.id.dziesiatkaMasa3);
            TextView jednostkaOdleglosc = findViewById(R.id.jednostkaOdlegloscPrzyciaganie);
            TextView jednostkaMasa = findViewById(R.id.jednostkaMasaPrzyciaganie);
            TextView wynikPrzyciaganie = findViewById(R.id.wynikPrzyciaganie);
            TextView wynikPrzyciaganieDziesiatki = findViewById(R.id.wynikPrzyciaganieDziesiatki);
            EditText objetoscGestosc = findViewById(R.id.editText4);
            TextView jednostkaObjetoscGestosc = findViewById(R.id.jednostkaObjetoscGestosc);
            EditText masaGestosc = findViewById(R.id.editText5);
            TextView jednostkaMasaGestosc = findViewById(R.id.jednostkaMasaGestosc);
            EditText Gestosc = findViewById(R.id.editText6);
            TextView jednostkaGestosc = findViewById(R.id.jednostkaGestosc);
            EditText ciezar1 = findViewById(R.id.editText01);
            EditText ciezar2 = findViewById(R.id.editText02);
            EditText stosunekCiezarow = findViewById(R.id.editText03);
            TextView wynikStosunku = findViewById(R.id.wynikPorownania);
            EditText masa = findViewById(R.id.editText001);
            EditText ciezar = findViewById(R.id.editText002);
            TextView wynikCiezar = findViewById(R.id.wynikCiezar);
            savedInstanceState.putString("masa1", masa1.getText().toString());
            savedInstanceState.putString("masa1dziesiatki",masa1dziesiatki.getText().toString());
            savedInstanceState.putString("masa2", masa2.getText().toString());
            savedInstanceState.putString("masa2dziesiatki", masa2dziesiatki.getText().toString());
            savedInstanceState.putString("jednostkaMasaPrzyciaganie", jednostkaMasa.getText().toString());
            savedInstanceState.putString("odleglosc", odleglosc.getText().toString());
            savedInstanceState.putString("odlegloscDziesiatki", odlegloscDziesiatki.getText().toString());
            savedInstanceState.putString("jednostkaOdleglosc", jednostkaOdleglosc.getText().toString());
            savedInstanceState.putString("wynikPrzyciaganie", wynikPrzyciaganie.getText().toString());
            savedInstanceState.putString("wynikPrzyciaganieDziesiatki", wynikPrzyciaganieDziesiatki.getText().toString());
            savedInstanceState.putString("objetoscGestosc", objetoscGestosc.getText().toString());
            savedInstanceState.putString("jednostkaObjetoscGestosc", jednostkaObjetoscGestosc.getText().toString());
            savedInstanceState.putString("masaGestosc", masaGestosc.getText().toString());
            savedInstanceState.putString("jednostkaMasaGestosc", jednostkaMasaGestosc.getText().toString());
            savedInstanceState.putString("gestosc", Gestosc.getText().toString());
            savedInstanceState.putString("jednostkaGestosc", jednostkaGestosc.getText().toString());
            savedInstanceState.putString("ciezar1", ciezar1.getText().toString());
            savedInstanceState.putString("ciezar2", ciezar2.getText().toString());
            savedInstanceState.putString("stosuniekCiezarow", stosunekCiezarow.getText().toString());
            savedInstanceState.putString("wynikStosunku", wynikStosunku.getText().toString());
            savedInstanceState.putString("masa", masa.getText().toString());
            savedInstanceState.putString("ciezar", ciezar.getText().toString());
            savedInstanceState.putString("wynikCiezar", wynikCiezar.getText().toString());
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            EditText masa1 = findViewById(R.id.masaPrzyciaganie1);
            EditText masa2 = findViewById(R.id.masaPrzyciaganie2);
            EditText masa1dziesiatki = findViewById(R.id.dziesiatkaMasa1);
            EditText masa2dziesiatki = findViewById(R.id.dziesiatkaMasa2);
            EditText odleglosc = findViewById(R.id.odlegloscPrzyciaganie);
            EditText odlegloscDziesiatki = findViewById(R.id.dziesiatkaMasa3);
            TextView jednostkaOdleglosc = findViewById(R.id.jednostkaOdlegloscPrzyciaganie);
            TextView jednostkaMasa = findViewById(R.id.jednostkaMasaPrzyciaganie);
            TextView wynikPrzyciaganie = findViewById(R.id.wynikPrzyciaganie);
            TextView wynikPrzyciaganieDziesiatki = findViewById(R.id.wynikPrzyciaganieDziesiatki);
            EditText objetoscGestosc = findViewById(R.id.editText4);
            TextView jednostkaObjetoscGestosc = findViewById(R.id.jednostkaObjetoscGestosc);
            EditText masaGestosc = findViewById(R.id.editText5);
            TextView jednostkaMasaGestosc = findViewById(R.id.jednostkaMasaGestosc);
            EditText Gestosc = findViewById(R.id.editText6);
            TextView jednostkaGestosc = findViewById(R.id.jednostkaGestosc);
            EditText ciezar1 = findViewById(R.id.editText01);
            EditText ciezar2 = findViewById(R.id.editText02);
            EditText stosunekCiezarow = findViewById(R.id.editText03);
            TextView wynikStosunku = findViewById(R.id.wynikPorownania);
            EditText masa = findViewById(R.id.editText001);
            EditText ciezar = findViewById(R.id.editText002);
            TextView wynikCiezar = findViewById(R.id.wynikCiezar);
            masa1.setText(savedInstanceState.getString("masa1"));
            masa1dziesiatki.setText(savedInstanceState.getString("masa1dziesiatki"));
            masa2.setText(savedInstanceState.getString("masa2"));
            masa2dziesiatki.setText(savedInstanceState.getString("masa2dziesiatki"));
            jednostkaMasa.setText(savedInstanceState.getString("jednostkaMasaPrzyciaganie"));
            odleglosc.setText(savedInstanceState.getString("odleglosc"));
            odlegloscDziesiatki.setText(savedInstanceState.getString("odlegloscDziesiatki"));
            jednostkaOdleglosc.setText(savedInstanceState.getString("jednostkaOdleglosc"));
            wynikPrzyciaganie.setText(savedInstanceState.getString("wynikPrzyciaganie"));
            wynikPrzyciaganieDziesiatki.setText(savedInstanceState.getString("wynikPrzyciaganieDziesiatki"));
            objetoscGestosc.setText(savedInstanceState.getString("objetoscGestosc"));
            jednostkaObjetoscGestosc.setText(savedInstanceState.getString("jednostkaObjetoscGestosc"));
            masaGestosc.setText(savedInstanceState.getString("masaGestosc"));
            jednostkaMasaGestosc.setText(savedInstanceState.getString("jednostkaMasaGestosc"));
            Gestosc.setText(savedInstanceState.getString("gestosc"));
            jednostkaGestosc.setText(savedInstanceState.getString("jednostkaGestosc"));
            ciezar1.setText(savedInstanceState.getString("ciezar1"));
            ciezar2.setText(savedInstanceState.getString("ciezar2"));
            stosunekCiezarow.setText(savedInstanceState.getString("stosuniekCiezarow"));
            wynikStosunku.setText(savedInstanceState.getString("wynikStosunku"));
            masa.setText(savedInstanceState.getString("masa"));
            ciezar.setText(savedInstanceState.getString("ciezar"));
            wynikCiezar.setText(savedInstanceState.getString("wynikCiezar"));
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz jednostkę");
        if (v.getId() == R.id.jednostkaOdlegloscPrzyciaganie) {
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
        } else if (v.getId() == R.id.jednostkaMasaPrzyciaganie || v.getId() == R.id.jednostkaMasaGestosc) {
            getMenuInflater().inflate(R.menu.example_menu, menu);
        } else if (v.getId() == R.id.jednostkaObjetoscGestosc) {
            getMenuInflater().inflate(R.menu.objetosc_menu, menu);
        } else if (v.getId() == R.id.jednostkaGestosc) {
            getMenuInflater().inflate(R.menu.gestosc_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView masa10 = findViewById(R.id.jednostkaMasaPrzyciaganie);
        TextView odleglosc10 = findViewById(R.id.jednostkaOdlegloscPrzyciaganie);
        TextView masaGestosc = findViewById(R.id.jednostkaMasaGestosc);
        TextView objetoscGestosc = findViewById(R.id.jednostkaObjetoscGestosc);
        TextView gestosc10 = findViewById(R.id.jednostkaGestosc);
        switch (item.getItemId()) {
            case R.id.Centymetr:
                odleglosc10.setText("cm");
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT);
                return true;
            case R.id.Decymetr:
                odleglosc10.setText("dm");
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT);
                return true;
            case R.id.Metr:
                odleglosc10.setText("m");
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT);
                return true;
            case R.id.Kilometr:
                odleglosc10.setText("km");
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT);
                return true;
            case R.id.option2:
                if (ktory.equals("przyciaganie")) {
                    masa10.setText("g");
                } else if (ktory.equals("gestosc")) {
                    masaGestosc.setText("g");
                }
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT);
                return true;
            case R.id.option3:
                if (ktory.equals("przyciaganie")) {
                    masa10.setText("dag");
                } else if (ktory.equals("gestosc")) {
                    masaGestosc.setText("dag");
                }
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT);
                return true;
            case R.id.option4:
                if (ktory.equals("przyciaganie")) {
                    masa10.setText("kg");
                } else if (ktory.equals("gestosc")) {
                    masaGestosc.setText("kg");
                }
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT);
                return true;
            case R.id.option5:
                if (ktory.equals("przyciaganie")) {
                    masa10.setText("t");
                } else if (ktory.equals("gestosc")) {
                    masaGestosc.setText("t");
                }
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT);
                return true;
            case R.id.centymetrSzescian2:
                objetoscGestosc.setText(Html.fromHtml("cm<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "centymetr sześcienny", Toast.LENGTH_SHORT);
                return true;
            case R.id.decymetrSzescian2:
                objetoscGestosc.setText(Html.fromHtml("dm<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Decymetr sześcienny", Toast.LENGTH_SHORT);
                return true;
            case R.id.metrSzescian2:
                objetoscGestosc.setText(Html.fromHtml("m<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Metr sześcienny", Toast.LENGTH_SHORT);
                return true;
            case R.id.GnaCM3:
                gestosc10.setText(Html.fromHtml("g/cm<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Gramy na cm sześcienne", Toast.LENGTH_SHORT);
                return true;
            case R.id.GnaDM3:
                gestosc10.setText(Html.fromHtml("g/dm<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Gramy na dm sześcienne", Toast.LENGTH_SHORT);
                return true;
            case R.id.KGnaCM3:
                gestosc10.setText(Html.fromHtml("kg/cm<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Kilogramy na cm sześcienne", Toast.LENGTH_SHORT);
                return true;
            case R.id.KGnaDM3:
                gestosc10.setText(Html.fromHtml("kg/dm<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Kilogramy na dm sześcienne", Toast.LENGTH_SHORT);
                return true;
            case R.id.KGnaM3:
                gestosc10.setText(Html.fromHtml("kg/m<sup><small><small>3</small></small></sup>"));
                Toast.makeText(this, "Kilogramy na m sześcienne", Toast.LENGTH_SHORT);
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
                    Intent i = new Intent(Przyszpieszenie.this, Szkola.class);
                    i.putExtra("miejsce", "Przyszpieszenie");
                    startActivity(i);
                    Animatoo.animateFade(Przyszpieszenie.this);
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
            Intent i = new Intent(Przyszpieszenie.this, Grawitacja.class);
            startActivity(i);
            Animatoo.animateFade(Przyszpieszenie.this);
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