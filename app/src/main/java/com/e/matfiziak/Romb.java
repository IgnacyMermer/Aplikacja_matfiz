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

public class Romb extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;

    /*
    Tutaj wpisujesz sobie rzeczy, które chcesz mieć w całym pliku możesz tu wrzucić jednostki i checkbox i tylko nazwy:
    np. Textview jednostkaWynik;
        Checkbox pole;*/
    CheckBox checkBoxPole;
    CheckBox checkBoxBok;
    CheckBox checkBoxObwod;
    CheckBox checkBoxWysokosc;
    CheckBox checkBoxKatA;
    CheckBox checkBoxKatB;
    CheckBox checkBoxPrzekatnaA;
    CheckBox checkBoxPrzekatnaB;
    EditText wysokosc, obwod, przekatnaA, przekatnaB, bok, pole, katA, katB;
    TextView jednostkaWysokosc, jednostkaObwod, jednostkaPrzekatnaA, jednostkaPrzekatnaB, jednostkaBok, jednostkaPole, jednostkaWynik;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik;
    ArrayList<String> doWyslania;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romb);
        /*to  jest animacja wykonująca się w tle jako tło*/
        final ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        final AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        checkBoxPrzekatnaB = findViewById(R.id.checkboxPrzekatnaBRomb);
        checkBoxPrzekatnaA = findViewById(R.id.checkboxPrzekatnaARomb);
        checkBoxObwod = findViewById(R.id.checkboxObwodRombu);
        checkBoxKatB = findViewById(R.id.checkboxKatBRombu);
        checkBoxKatA = findViewById(R.id.checkboxKatARombu);
        checkBoxWysokosc = findViewById(R.id.checkboxWysokoscRombu);
        checkBoxBok = findViewById(R.id.checkboxBokRombu);
        checkBoxPole = findViewById(R.id.checkboxPoleRombu);
        wynik = findViewById(R.id.wynikRomb);
        wysokosc = findViewById(R.id.wysokoscRomb);
        obwod = findViewById(R.id.obwodRomb);
        przekatnaB = findViewById(R.id.przekatnaBRomb);
        przekatnaA = findViewById(R.id.przekatnaARomb);
        bok = findViewById(R.id.bokARomb);
        pole = findViewById(R.id.poleRomb);
        katA = findViewById(R.id.katARomb);
        katB = findViewById(R.id.katBRomb);
        jednostkaWynik = findViewById(R.id.jednostkaWynikRomb);
        jednostkaPrzekatnaB = findViewById(R.id.jednostkaPrzekatnaBRomb);
        jednostkaPrzekatnaA = findViewById(R.id.jednostkaPrzekatnaARomb);
        jednostkaPole = findViewById(R.id.jednostkaPoleRomb);
        jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
        jednostkaObwod = findViewById(R.id.jednostkaObwodRomb);
        jednostkaWysokosc = findViewById(R.id.jednostkaWysokoscRomb);
        jednostkaBok = findViewById(R.id.jednostkaBokARomb);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaRomb);
        drugaLinia = findViewById(R.id.drugaLiniaRomb);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaRomb);
        czwartaLinia = findViewById(R.id.czwartaLiniaRomb);
        wynik = findViewById(R.id.wynikRomb);
        Button buttonWynik = findViewById(R.id.buttonObliczRomb);
        Button buttonCzysc = findViewById(R.id.buttonCzyscRomb);
        /*to jest dolny pasek, menu to te 5 elementów, a menuItem to wziety element*/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        //
        // bottomNavigationView.setBackgroundColor(Color.parseColor("#000000"));
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        /*tu ustawione jest zeby pokazywało podpis pod ikonka*/
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        /*W tym miejscu najlepiej wpisz sobie wszystkie edittext, textview, checkbox, button*/

        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<String>();
        /*Tu bierzemy tablice i nazwe checkboxa w ktorych mamy zapisane dane ktore wczesniej wpisalismy ale gdzies poszlismy i wro  cilismy tu*/
        Intent incomingIntent = getIntent();
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        checkbox = incomingIntent.getStringExtra("checkbox");
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUr");
        if(checkbox==null){
            checkbox="";
        }
        /*Robimy ArrayLista zeby działał*/
        doWyslania = new ArrayList<String>();

        /**/
        //no i tu robisz ify zeby zaznaczyc odpowiedniego checkboxa
        if(checkbox.equals("pole")){
            checkBoxPole.setChecked(true);
        }
        else if(checkbox.equals("bok")){
            checkBoxBok.setChecked(true);
        }
        else if(checkbox.equals("wysokosc")){
            checkBoxWysokosc.setChecked(true);
        }
        else if(checkbox.equals("katA")){
            checkBoxKatA.setChecked(true);
        }
        else if(checkbox.equals("katB")){
            checkBoxKatB.setChecked(true);
        }
        else if(checkbox.equals("obwod")){
            checkBoxObwod.setChecked(true);
        }
        else if(checkbox.equals("przekatnaA")){
            checkBoxPrzekatnaA.setChecked(true);
        }
        else if(checkbox.equals("przekatnaB")){
            checkBoxPrzekatnaB.setChecked(true);
        }

        if(incomingList!=null){
            bok.setText(incomingList.get(0));
            jednostkaBok.setText(incomingList.get(1));
            wysokosc.setText(incomingList.get(2));
            jednostkaWysokosc.setText(incomingList.get(3));
            obwod.setText(incomingList.get(4));
            jednostkaObwod.setText(incomingList.get(5));
            pole.setText(incomingList.get(6));
            jednostkaPole.setText(incomingList.get(7));
            przekatnaA.setText(incomingList.get(8));
            jednostkaPrzekatnaA.setText(incomingList.get(9));
            przekatnaB.setText(incomingList.get(10));
            jednostkaPrzekatnaB.setText(incomingList.get(11));
            katA.setText(incomingList.get(12));
            katB.setText(incomingList.get(13));
            pierwszaLinia.setText(incomingList.get(14));
            drugaLinia.setText(incomingList.get(15));
            trzeciaLinia.setText(incomingList.get(16));
            czwartaLinia.setText(incomingList.get(17));
            wynik.setText(incomingList.get(18));
            jednostkaWynik.setText(incomingList.get(19));
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(bok.getText().toString());
                doWyslania.add(jednostkaBok.getText().toString());
                doWyslania.add(wysokosc.getText().toString());
                doWyslania.add(jednostkaWysokosc.getText().toString());
                doWyslania.add(obwod.getText().toString());
                doWyslania.add(jednostkaObwod.getText().toString());
                doWyslania.add(pole.getText().toString());
                doWyslania.add(jednostkaPole.getText().toString());
                doWyslania.add(przekatnaA.getText().toString());
                doWyslania.add(jednostkaPrzekatnaA.getText().toString());
                doWyslania.add(przekatnaB.getText().toString());
                doWyslania.add(jednostkaPrzekatnaB.getText().toString());
                doWyslania.add(katA.getText().toString());
                doWyslania.add(katB.getText().toString());
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
                else if(checkBoxPrzekatnaA.isChecked()){
                    ktoryCheckbox="przekatnaA";
                }
                else if(checkBoxPrzekatnaB.isChecked()){
                    ktoryCheckbox="przekatnaB";
                }
                else if(checkBoxObwod.isChecked()){
                    ktoryCheckbox="obwod";
                }
                else if(checkBoxKatA.isChecked()){
                    ktoryCheckbox="katA";
                }
                else if(checkBoxKatB.isChecked()){
                    ktoryCheckbox="katB";
                }
                //Tu robisz tablice ktora zaraz wyslesz do jakiegos activity a on potem przesle ja dalej i bedziesz ja mogl odczytac gdy wrocimy tu
                //Zwracaj uwage na zapis stringa ifa ktoryCheckbox, zeby nie był inny niz ifa checkbox bo nie odczyta
                //no a indeksy arraylistu ida od zera jak cos
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        /*To jest instrukcja podobna do ifa, i po nacisnieciu tworzy sie Intent, ktory po startActivity przenosi do innego activity*/
                        Intent i = new Intent(Romb.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        /*tu wysyłasz jakieś dane typu stringi, arraylisty*/
                        i.putExtra("miejsce", "Romb");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        i.putExtra("imageUrl", imageUr);
                        i.putExtra("nick", nick);
                        //i tak w kazdym trzeba wyslac oprocz R.id.navigation_school
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Romb.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("imageUrl", imageUr);
                        i1.putExtra("nick", nick);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Romb.this, Czat.class);
                        i2.putExtra("miejsce", "Romb");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.putExtra("imageUrl", imageUr);
                        i2.putExtra("nick", nick);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Romb.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Romb");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        i3.putExtra("imageUrl", imageUr);
                        i3.putExtra("nick", nick);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Romb.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Romb");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox", ktoryCheckbox);
                        i4.putExtra("imageUrl", imageUr);
                        i4.putExtra("nick", nick);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        // Tu wpisujesz dane z tej tablicy ktora wyslales z danymi
        // Jeśli robisz jednostki to rob listenery i odnosniki do context menu zebu mozna było zmieniac jednostki to wystarczy tak jak nizej
        jednostkaBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaBok);
                openContextMenu(v);
            }
        });
        jednostkaWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWysokosc);
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
        jednostkaPrzekatnaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzekatnaA);
                openContextMenu(v);
            }
        });
        jednostkaPrzekatnaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPrzekatnaB);
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
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wynik.setText("");
                jednostkaWynik.setText("");
                jednostkaBok.setText("cm");
                jednostkaObwod.setText("cm");
                jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                jednostkaWysokosc.setText("cm");
                jednostkaPrzekatnaA.setText("cm");
                jednostkaPrzekatnaB.setText("cm");
                wysokosc.setText("");
                przekatnaA.setText("");
                przekatnaB.setText("");
                bok.setText("");
                pole.setText("");
                obwod.setText("");
                katA.setText("");
                katB.setText("");
                checkBoxBok.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxObwod.setChecked(false);
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        //no i tak główny przycisk i te ify


        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxPole.isChecked()){
                        Double poleD = null;
                        if((!bok.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double h = Double.parseDouble(wysokosc.getText().toString());
                            h = funkcjePrzelicznikowe.dlugosc(h, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=a*h");
                            drugaLinia.setText("P="+a+"*"+h);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = a*h;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(przekatnaA.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaPrzekatnaA.getText().toString());
                            Double b = Double.parseDouble(przekatnaB.getText().toString());
                            b = funkcjePrzelicznikowe.dlugosc(b, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=d<sub><small><small>1</small></small></sub>*d<sub><small><small>2</small></small></sub>/2"));
                            drugaLinia.setText("P="+a+"*"+b+"/2");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            poleD = a*b/2;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*sin"+kat));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            trzeciaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*"+sin));
                            czwartaLinia.setText("");
                            poleD = a*a*sin;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*sin"+kat));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            trzeciaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*"+sin));
                            czwartaLinia.setText("");
                            poleD = a*a*sin;
                            poleD = funkcjePrzelicznikowe.poleWynik(poleD, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(poleD);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxWysokosc.isChecked()){
                        Double h = null;
                        if((!pole.getText().toString().equals(""))&&(!bok.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText("P=a*h");
                            drugaLinia.setText("h=P/a");
                            trzeciaLinia.setText("h="+poleD+"/"+a);
                            czwartaLinia.setText("");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!obwod.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double a = ob/4;
                            pierwszaLinia.setText("P=a*h");
                            drugaLinia.setText("h=P/a");
                            trzeciaLinia.setText("h="+poleD+"/"+a);
                            czwartaLinia.setText("");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*"+sin));
                            Double a2 = a*a;
                            Double poleD = a2*sin;
                            trzeciaLinia.setText("P="+poleD);
                            czwartaLinia.setText("P=a*h");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double a = ob/4;
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*"+sin));
                            Double a2 = a*a;
                            Double poleD = a2*sin;
                            trzeciaLinia.setText("P="+poleD);
                            czwartaLinia.setText("P=a*h");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            Double a = ob/4;
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*"+sin));
                            Double a2 = a*a;
                            Double poleD = a2*sin;
                            trzeciaLinia.setText("P="+poleD);
                            czwartaLinia.setText("P=a*h");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            drugaLinia.setText(Html.fromHtml("P="+a+"<sup><small><small>2</small></small></sup>*"+sin));
                            Double a2 = a*a;
                            Double poleD = a2*sin;
                            trzeciaLinia.setText("P="+poleD);
                            czwartaLinia.setText("P=a*h");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(P/sin&#945;)"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double a = poleD/sin;
                            a = sqrt(a);
                            trzeciaLinia.setText("P=a*h");
                            czwartaLinia.setText("h=P/a");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(P/sin&#945;)"));
                            kat = Math.toRadians(kat);
                            Double sin = Math.sin(kat);
                            Double a = poleD/sin;
                            a = sqrt(a);
                            trzeciaLinia.setText("P=a*h");
                            czwartaLinia.setText("h=P/a");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))&&(!pole.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                            d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=(&frac12;d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>)+(&frac12;a<sup><small><small>2</small></small></sup>=d<sub><small><small>2</small></small></sub><sup><small><small>2</small></small></sup>)"));
                            d1=d1/2;
                            d2 = d2/2;
                            d1=d1*d1;
                            d2=d2*d2;
                            d1+=d2;
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>="+d1));
                            trzeciaLinia.setText("P=a*h");
                            czwartaLinia.setText("h=P/a");
                            Double a=sqrt(d1);
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))&&(bok.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                            d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=d<sub><small><small>1</small></small></sub>*d<sub><small><small>2</small></small></sub>"));
                            Double poleD = d1*d2;
                            drugaLinia.setText("P="+poleD);
                            trzeciaLinia.setText("P=a*h");
                            czwartaLinia.setText("h=P/a");
                            h = poleD/a;
                            h = funkcjePrzelicznikowe.dlugoscWynik(h ,jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(h);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxBok.isChecked()){
                        Double a = null;
                        if((!pole.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double h = Double.parseDouble(wysokosc.getText().toString());
                            h = funkcjePrzelicznikowe.dlugosc(h, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=a*h");
                            drugaLinia.setText("a=P/h");
                            trzeciaLinia.setText("a="+poleD+"/"+h);
                            czwartaLinia.setText("");
                            a = poleD/h;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!obwod.getText().toString().equals(""))){
                            Double ob = Double.parseDouble(obwod.getText().toString());
                            ob = funkcjePrzelicznikowe.dlugosc(ob, jednostkaObwod.getText().toString());
                            pierwszaLinia.setText("Ob=4a");
                            drugaLinia.setText("a=Ob/4");
                            trzeciaLinia.setText("a="+ob+"/4");
                            czwartaLinia.setText("");
                            a = ob/4;
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                            d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=(&frac12;d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>)+(&frac12;a<sup><small><small>2</small></small></sup>=d<sub><small><small>2</small></small></sub><sup><small><small>2</small></small></sup>)"));
                            d1=d1/2;
                            d2 = d2/2;
                            d1=d1*d1;
                            d2=d2*d2;
                            d1+=d2;
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>="+d1));
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;"+d1));
                            czwartaLinia.setText("");
                            a=sqrt(d1);
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(P/sin&#945;)"));
                            Double sin = Math.sin(kat);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;("+poleD+"/"+sin));
                            czwartaLinia.setText("");
                            a = poleD/sin;
                            a= sqrt(a);
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(P/sin&#945;)"));
                            Double sin = Math.sin(kat);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;("+poleD+"/"+sin));
                            czwartaLinia.setText("");
                            a = poleD/sin;
                            a= sqrt(a);
                            a = funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>2</small></small></sub>/cos&#945;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("");
                            a=funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>2</small></small></sub>/cos&#945;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("");
                            a=funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double kat2 = Double.parseDouble(katA.getText().toString());
                            Double kat = 180-kat2;
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=&frac12;d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>1</small></small></sub>/cos&#946;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("");
                            a=funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double kat = Double.parseDouble(katB.getText().toString());
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=&frac12;d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>1</small></small></sub>/cos&#946;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("");
                            a=funkcjePrzelicznikowe.dlugoscWynik(a, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxObwod.isChecked()){
                        Double Ob = null;
                        Double a=null;
                        if(!bok.getText().toString().equals("")){
                            a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            pierwszaLinia.setText("Ob=4a");
                            drugaLinia.setText("Ob=4*"+a);
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Ob=4*a;
                            Ob = funkcjePrzelicznikowe.dlugosc(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!wysokosc.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double h = Double.parseDouble(wysokosc.getText().toString());
                            h = funkcjePrzelicznikowe.dlugosc(h, jednostkaWysokosc.getText().toString());
                            pierwszaLinia.setText("P=a*h");
                            drugaLinia.setText("a=P/h");
                            trzeciaLinia.setText("a="+poleD+"/"+h);
                            czwartaLinia.setText("Ob=4a");
                            a = poleD/h;
                            Ob=4*a;
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                            d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>=(&frac12;d<sub><small><small>1</small></small></sub><sup><small><small>2</small></small></sup>)+(&frac12;a<sup><small><small>2</small></small></sup>=d<sub><small><small>2</small></small></sub><sup><small><small>2</small></small></sup>)"));
                            d1=d1/2;
                            d2 = d2/2;
                            d1=d1*d1;
                            d2=d2*d2;
                            d1+=d2;
                            drugaLinia.setText(Html.fromHtml("a<sup><small><small>2</small></small></sup>="+d1));
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;"+d1));
                            czwartaLinia.setText("Ob=4a");
                            a=sqrt(d1);
                            Ob=4*a;
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(P/sin&#945;)"));
                            Double sin = Math.sin(kat);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;("+poleD+"/"+sin));
                            czwartaLinia.setText("Ob=4a");
                            a = poleD/sin;
                            a= sqrt(a);
                            Ob=4*a;
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("P=a<sup><small><small>2</small></small></sup>*sin&#945;"));
                            drugaLinia.setText(Html.fromHtml("a=&#8730;(P/sin&#945;)"));
                            Double sin = Math.sin(kat);
                            trzeciaLinia.setText(Html.fromHtml("a=&#8730;("+poleD+"/"+sin));
                            czwartaLinia.setText("Ob=4a");
                            a = poleD/sin;
                            a= sqrt(a);
                            Ob=4*a;
                            Ob = funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>2</small></small></sub>/cos&#945;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("Ob=4a");
                            Ob=4*a;
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>2</small></small></sub>/cos&#945;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("Ob=4a");
                            Ob=4*a;
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double kat2 = Double.parseDouble(katA.getText().toString());
                            Double kat = 180-kat2;
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=&frac12;d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>1</small></small></sub>/cos&#946;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("Ob=4a");
                            Ob=4*a;
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double kat = Double.parseDouble(katB.getText().toString());
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#946;=&frac12;d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("a=&frac12;d<sub><small><small>1</small></small></sub>/cos&#946;"));
                            d1=d1/2;
                            Double cos = Math.cos(kat);
                            a=d1/cos;
                            trzeciaLinia.setText(Html.fromHtml("a="+d1+"/"+cos));
                            czwartaLinia.setText("Ob=4a");
                            Ob=4*a;
                            Ob=funkcjePrzelicznikowe.dlugoscWynik(Ob, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(Ob);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        Double d2= null;
                        if((!bok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub>=2a*cos&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            d2 = 2*a*cos;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat=kat/2;
                            kat=Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub>=2a*cos&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            d2 = 2*a*cos;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=d<sub><small><small>1</small></small></sub>*d<sub><small><small>2</small></small></sub>/2"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub>=2P/d<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("d<sub><small><small>2</small></small></sub>=2*"+poleD+"/"+d1));
                            czwartaLinia.setText("");
                            d2 = 2*poleD/d1;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double kat = Double.parseDouble(katB.getText().toString());
                            kat = kat/2;
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            d1 = d1/2;
                            pierwszaLinia.setText(Html.fromHtml("tan&#945;=&frac12;d<sub><small><small>2</small></small></sub>/d<sub><small><small>1</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>2</small></small></sub>=tan&#945;*d<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>2</small></small></sub>="+tan+"*"+d1));
                            czwartaLinia.setText("");
                            d2 = d1*tan*2;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!przekatnaA.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            Double kat2 = Double.parseDouble(katA.getText().toString());
                            Double kat = 180-kat2;
                            kat = kat/2;
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            d1 = d1/2;
                            pierwszaLinia.setText(Html.fromHtml("tan&#945;=&frac12;d<sub><small><small>2</small></small></sub>/d<sub><small><small>1</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>2</small></small></sub>=tan&#945;*d<sub><small><small>1</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>2</small></small></sub>="+tan+"*"+d1));
                            czwartaLinia.setText("");
                            d2 = d1*tan*2;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("(&frac12;d<sub><small><small>2</small></small></sub>)<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>-(&frac12;d<sub><small><small>1</small></small></sub>)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(&frac12;d<sub><small><small>2</small></small></sub>)<sup><small><small>2</small></small></sup>="+a+"<sup><small><small>2</small></small></sup>-"+d1+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a=a*a;
                            d1=d1/2;
                            d1=d1*d1;
                            d2 = a-d1;
                            d2=sqrt(d2);
                            d2=d2*2;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        Double d2= null;
                        if((!bok.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat2 = Double.parseDouble(katB.getText().toString());
                            Double kat = 180-kat2;
                            kat=kat/2;
                            kat = Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub>=2a*cos&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            d2 = 2*a*cos;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double kat = Double.parseDouble(katA.getText().toString());
                            kat=kat/2;
                            kat=Math.toRadians(kat);
                            Double cos = Math.cos(kat);
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub>=2a*cos&#945;"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            d2 = 2*a*cos;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!pole.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double poleD = Double.parseDouble(pole.getText().toString());
                            poleD = funkcjePrzelicznikowe.pole(poleD, jednostkaPole.getText().toString());
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("P=d<sub><small><small>1</small></small></sub>*d<sub><small><small>2</small></small></sub>/2"));
                            drugaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub>=2P/d<sub><small><small>2</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("d<sub><small><small>1</small></small></sub>=2*"+poleD+"/"+d1));
                            czwartaLinia.setText("");
                            d2 = 2*poleD/d1;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!katA.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            Double kat2 = Double.parseDouble(katA.getText().toString());
                            Double kat = 180-kat2;
                            kat = kat/2;
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            d1 = d1/2;
                            pierwszaLinia.setText(Html.fromHtml("tan&#945;=&frac12;d<sub><small><small>1</small></small></sub>/d<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>1</small></small></sub>=tan&#945;*d<sub><small><small>2</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>1</small></small></sub>="+tan+"*"+d1));
                            czwartaLinia.setText("");
                            d2 = d1*tan*2;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!przekatnaB.getText().toString().equals(""))&&(!katB.getText().toString().equals(""))){
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            Double kat = Double.parseDouble(katB.getText().toString());
                            kat = kat/2;
                            kat = Math.toRadians(kat);
                            Double tan = Math.tan(kat);
                            d1 = d1/2;
                            pierwszaLinia.setText(Html.fromHtml("tan&#945;=&frac12;d<sub><small><small>1</small></small></sub>/d<sub><small><small>2</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>1</small></small></sub>=tan&#945;*d<sub><small><small>2</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;d<sub><small><small>1</small></small></sub>="+tan+"*"+d1));
                            czwartaLinia.setText("");
                            d2 = d1*tan*2;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double d1 = Double.parseDouble(przekatnaB.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("(&frac12;d<sub><small><small>1</small></small></sub>)<sup><small><small>2</small></small></sup>=a<sup><small><small>2</small></small></sup>-(&frac12;d<sub><small><small>2</small></small></sub>)<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("(&frac12;d<sub><small><small>1</small></small></sub>)<sup><small><small>2</small></small></sup>="+a+"<sup><small><small>2</small></small></sup>-"+d1+"<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            a=a*a;
                            d1=d1/2;
                            d1=d1*d1;
                            d2 = a-d1;
                            d2=sqrt(d2);
                            d2=d2*2;
                            d2 = funkcjePrzelicznikowe.dlugoscWynik(d2, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(d2);
                            wynik.setText(x);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkBoxKatA.isChecked()){
                        Double kata = null;
                        if((!przekatnaA.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double przekatna1 = Double.parseDouble(przekatnaA.getText().toString());
                            przekatna1 = funkcjePrzelicznikowe.dlugosc(przekatna1, jednostkaPrzekatnaA.getText().toString());
                            Double przekatna2 = Double.parseDouble(przekatnaB.getText().toString());
                            przekatna2 = funkcjePrzelicznikowe.dlugosc(przekatna2, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tan&#945;=&frac12;d<sub><small><small>1</small></small></sub>/&frac12;d<sub><small><small>2</small></small></sub>"));
                            przekatna1 = przekatna1/2;
                            przekatna2 = przekatna2/2;
                            drugaLinia.setText(Html.fromHtml("tan&#945;="+przekatna1+"/"+przekatna2));
                            Double tan = przekatna1/przekatna2;
                            trzeciaLinia.setText(Html.fromHtml("&frac12;&#945;=tan&#945;"));
                            czwartaLinia.setText("");
                            kata = Math.atan(tan);
                            kata = Math.toDegrees(kata);
                            kata = kata*2;
                            String x = funkcjePrzelicznikowe.intowanie(kata);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            d1=d1/2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#945;=&frac12;*d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("sin&#945;="+d1+"/"+a));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double sin = d1/a;
                            kata = Math.asin(sin);
                            kata = Math.toDegrees(kata);
                            kata=kata*2;
                            String x = funkcjePrzelicznikowe.intowanie(kata);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                            d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                            d2=d2/2;
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;*d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("cos&#945;="+d2+"/"+a));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double cos = d2/a;
                            kata = Math.acos(cos);
                            kata = Math.toDegrees(kata);
                            kata=kata*2;
                            String x = funkcjePrzelicznikowe.intowanie(kata);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxKatB.isChecked()){
                        Double kata = null;
                        if((!przekatnaA.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double przekatna1 = Double.parseDouble(przekatnaA.getText().toString());
                            przekatna1 = funkcjePrzelicznikowe.dlugosc(przekatna1, jednostkaPrzekatnaA.getText().toString());
                            Double przekatna2 = Double.parseDouble(przekatnaB.getText().toString());
                            przekatna2 = funkcjePrzelicznikowe.dlugosc(przekatna2, jednostkaPrzekatnaB.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("tan&#945;=&frac12;d<sub><small><small>2</small></small></sub>/&frac12;d<sub><small><small>1</small></small></sub>"));
                            przekatna1 = przekatna1/2;
                            przekatna2 = przekatna2/2;
                            drugaLinia.setText(Html.fromHtml("tan&#945;="+przekatna2+"/"+przekatna1));
                            Double tan = przekatna2/przekatna1;
                            trzeciaLinia.setText(Html.fromHtml("&frac12;&#945;=tan&#945;"));
                            czwartaLinia.setText("");
                            kata = Math.atan(tan);
                            kata = Math.toDegrees(kata);
                            kata = kata*2;
                            String x = funkcjePrzelicznikowe.intowanie(kata);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!przekatnaA.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double d1 = Double.parseDouble(przekatnaA.getText().toString());
                            d1 = funkcjePrzelicznikowe.dlugosc(d1, jednostkaPrzekatnaA.getText().toString());
                            d1=d1/2;
                            pierwszaLinia.setText(Html.fromHtml("cos&#945;=&frac12;*d<sub><small><small>1</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("cos&#945;="+d1+"/"+a));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;&#945;=cos&#945;"));
                            czwartaLinia.setText("");
                            Double cos = d1/a;
                            kata = Math.acos(cos);
                            kata = Math.toDegrees(kata);
                            kata=kata*2;
                            String x = funkcjePrzelicznikowe.intowanie(kata);
                            wynik.setText(x);
                        }
                        else if((!bok.getText().toString().equals(""))&&(!przekatnaB.getText().toString().equals(""))){
                            Double a = Double.parseDouble(bok.getText().toString());
                            a = funkcjePrzelicznikowe.dlugosc(a, jednostkaBok.getText().toString());
                            Double d2 = Double.parseDouble(przekatnaB.getText().toString());
                            d2 = funkcjePrzelicznikowe.dlugosc(d2, jednostkaPrzekatnaB.getText().toString());
                            d2=d2/2;
                            pierwszaLinia.setText(Html.fromHtml("sin&#945;=&frac12;*d<sub><small><small>2</small></small></sub>/a"));
                            drugaLinia.setText(Html.fromHtml("sin&#945;="+d2+"/"+a));
                            trzeciaLinia.setText(Html.fromHtml("&frac12;&#945;=sin&#945;"));
                            czwartaLinia.setText("");
                            Double sin = d2/a;
                            kata = Math.asin(sin);
                            kata = Math.toDegrees(kata);
                            kata=kata*2;
                            String x = funkcjePrzelicznikowe.intowanie(kata);
                            wynik.setText(x);
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
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
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
        checkBoxPrzekatnaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxBok.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxPrzekatnaA.isChecked()){
                    jednostkaWynik.setText(przekatnaAWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        przekatnaAWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxPole.isChecked()){
                    if((!poleWynik.equals("a"))&&(!poleWynik.equals("ha"))){
                        jednostkaWynik.setText(Html.fromHtml(poleWynik+"<sup><small><small>2</small></small></sup"));
                    }
                    else{
                        jednostkaWynik.setText(poleWynik);
                    }
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        poleWynik=jednostkaWynik.getText().toString();
                        if(poleWynik.charAt(poleWynik.length()-1)=='2'){
                            poleWynik=poleWynik.substring(0,poleWynik.length()-1);
                        }
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzekatnaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                if(checkBoxPrzekatnaB.isChecked()){
                    jednostkaWynik.setText(przekatnaBWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        przekatnaBWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWysokosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
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
        checkBoxKatA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxObwod.setChecked(false);
                jednostkaWynik.setText(R.string.stopnie);
            }
        });
        checkBoxKatB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxKatA.setChecked(false);
                checkBoxObwod.setChecked(false);
                jednostkaWynik.setText(R.string.stopnie);
            }
        });
        checkBoxObwod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxPrzekatnaA.setChecked(false);
                checkBoxPole.setChecked(false);
                checkBoxPrzekatnaB.setChecked(false);
                checkBoxWysokosc.setChecked(false);
                checkBoxBok.setChecked(false);
                checkBoxKatB.setChecked(false);
                checkBoxBok.setChecked(false);
                if(checkBoxObwod.isChecked()){
                    jednostkaWynik.setText(obwodWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        obwodWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        /*To jest boczne menu i tego zrozumieć się nie da to trzeba edytowac metoda prob i bledow*/
        mdrawerLayout = findViewById(R.id.drawerRomb_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Romb.this, Konto.class);
                i.putExtra("miejsce", "Romb");
                startActivity(i);
                Animatoo.animateFade(Romb.this);
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
    String ktory="";
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.jednostkaBokARomb){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="bok";
        }
        else if(v.getId()==R.id.jednostkaWysokoscRomb){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wysokosc";
        }
        else if(v.getId()==R.id.jednostkaObwodRomb){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="obwod";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaARomb){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaA";
        }
        else if(v.getId()==R.id.jednostkaPrzekatnaBRomb){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="przekatnaB";
        }
        else if(v.getId()==R.id.jednostkaPoleRomb){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="pole";
        }
        else if((v.getId()==R.id.jednostkaWynikRomb)&&(checkBoxPole.isChecked())){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikRomb)&&((!checkBoxKatA.isChecked())&&(!checkBoxKatB.isChecked()))){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
    }
    String poleWynik="cm", bokWynik="cm", wysokoscWynik="cm", przekatnaAWynik="cm", obwodWynik="cm", przekatnaBWynik="cm";
    String ktory2="", ktory3="";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    if(checkBoxBok.isChecked()){
                        bokWynik="cm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="cm";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="cm";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="cm";
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
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("cm");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("cm");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                    if(checkBoxBok.isChecked()){
                        bokWynik="dm";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="dm";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="dm";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="dm";
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
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("dm");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("dm");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                    if(checkBoxBok.isChecked()){
                        bokWynik="m";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="m";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="m";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="m";
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
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("m");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("m");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                    if(checkBoxBok.isChecked()){
                        bokWynik="km";
                    }
                    else if(checkBoxObwod.isChecked()){
                        obwodWynik="km";
                    }
                    else if(checkBoxPrzekatnaA.isChecked()){
                        przekatnaAWynik="km";
                    }
                    else if(checkBoxPrzekatnaB.isChecked()){
                        przekatnaBWynik="km";
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
                else if (ktory.equals("przekatnaA")) {
                    jednostkaPrzekatnaA.setText("km");
                }
                else if(ktory.equals("przekatnaB")){
                    jednostkaPrzekatnaB.setText("km");
                }
                else if(ktory.equals("obwod")){
                    jednostkaObwod.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                    poleWynik="mm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                    poleWynik="cm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                    poleWynik="dm";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                    poleWynik="m";
                }
                else{
                    jednostkaPole.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("a");
                    poleWynik="a";
                }
                else{
                    jednostkaPole.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("ha");
                    poleWynik="ha";
                }
                else{
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
        adapter = new CustomExpandableListAdapter(Romb.this, lstTitle, lstChild);
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
                    Intent i = new Intent(Romb.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(Romb.this);
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
            Intent i = new Intent(Romb.this, Czworokaty.class);
            startActivity(i);
            Animatoo.animateFade(Romb.this);
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