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

public class PracaMocEnergia extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private NavigationManager navigationManager;
    private Map<String, List<String>> lstChild;
    CheckBox checkBoxDroga, checkBoxCzas, checkBoxPrzyspieszenie, checkBoxMasa, checkBoxPraca, checkBoxSila, checkBoxWspolczynnik, checkBoxMoc, checkBoxKat, checkBoxPredkosc, checkBoxEnPotencjalna, checkBoxEnKinetyczna;
    EditText droga, czas, przyspieszenie, masa, praca, sila, wspolczynnik, moc, kat, opory, predkoscPocz, enPotencjalna, enKinetyczna;
    TextView jednostkaDroga, jednostkaCzas, jednostkaPrzyspieszenie, jednostkaMasa, jednostkaPraca, jednostkaSila, jednostkaWynik, jednostkaMoc, jednostkaPredkoscPocz, jednostkaEnPotencjalna, jednostkaEnKinetyczna;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik, jednostkaOpory;
    ArrayList<String> doWyslania;
    Button buttonCzysc, buttonWynik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praca_moc_energia);
        final ScrollView scrollView = findViewById(R.id.rellayoutMiddle);
        final AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        checkBoxCzas = findViewById(R.id.checkboxCzasPracaMocEnergia);
        checkBoxDroga = findViewById(R.id.checkboxKatDrogaPracaMocEnergia);
        checkBoxPrzyspieszenie = findViewById(R.id.checkboxPrzyszpieszeniePracaMocEnergia);
        checkBoxMasa = findViewById(R.id.checkboxMasaPracaMocEnergia);
        checkBoxPraca = findViewById(R.id.checkboxPracaPracaMocEnergia);
        checkBoxSila = findViewById(R.id.checkboxSilaPracaMocEnergia);
        checkBoxWspolczynnik = findViewById(R.id.checkboxWspolczynnikPracaMocEnergia);
        checkBoxMoc = findViewById(R.id.checkboxMocPracaMocEnergia);
        checkBoxKat = findViewById(R.id.checkboxKatNachyleniaPracaMocEnergia);
        checkBoxPredkosc = findViewById(R.id.checkboxPredkoscPracaMocEnergia);
        checkBoxEnKinetyczna = findViewById(R.id.checkboxEnKinetycznaPracaMocEnergia);
        checkBoxEnPotencjalna = findViewById(R.id.checkboxEnPotencjalnaPracaMocEnergia);
        droga = findViewById(R.id.drogaPracaMocEnergia);
        czas = findViewById(R.id.czasPracaMocEnergia);
        przyspieszenie = findViewById(R.id.przyszpieszeniePracaMocEnergia);
        masa = findViewById(R.id.masaPracaMocEnergia);
        sila = findViewById(R.id.silaPracaMocEnergia);
        praca = findViewById(R.id.pracaPracaMocEnergia);
        moc = findViewById(R.id.mocPracaMocEnergia);
        wspolczynnik = findViewById(R.id.wspolczynnikPracaMocEnergia);
        kat = findViewById(R.id.katNachyleniaPracaMocEnergia);
        opory = findViewById(R.id.oporyPracaMocEnergia);
        predkoscPocz = findViewById(R.id.predkoscPracaMocEnergia);
        enKinetyczna = findViewById(R.id.enKinetycznaPracaMocEnergia);
        enPotencjalna = findViewById(R.id.enPotencjalnaPracaMocEnergia);
        jednostkaCzas = findViewById(R.id.jednostkaCzasPracaMocEnergia);
        jednostkaDroga = findViewById(R.id.jednostkaDrogaPracaMocEnergia);
        jednostkaMasa = findViewById(R.id.jednostkaMasaPracaMocEnergia);
        jednostkaMoc = findViewById(R.id.jednostkaMocPracaMocEnergia);
        jednostkaOpory = findViewById(R.id.jednostkaOporyPracaMocEnergia);
        jednostkaPraca = findViewById(R.id.jednostkaPracaPracaMocEnergia);
        jednostkaPrzyspieszenie = findViewById(R.id.jednostkaPrzyszpieszeniePracaMocEnergia);
        jednostkaPrzyspieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
        jednostkaSila = findViewById(R.id.jednostkaSilaPracaMocEnergia);
        jednostkaPredkoscPocz = findViewById(R.id.jednostkaPredkoscPracaMocEnergia);
        jednostkaEnKinetyczna = findViewById(R.id.jednostkaEnKinetycznaPracaMocEnergia);
        jednostkaEnPotencjalna = findViewById(R.id.jednostkaEnPotencjalnaPracaMocEnergia);
        jednostkaWynik = findViewById(R.id.jednostkaWynikPracaMocEnergia);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaPracaMocEnergia);
        drugaLinia = findViewById(R.id.drugaLiniaPracaMocEnergia);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaPracaMocEnergia);
        czwartaLinia = findViewById(R.id.czwartaLiniaPracaMocEnergia);
        wynik = findViewById(R.id.wynikPracaMocEnergia);
        buttonCzysc = findViewById(R.id.buttonCzyscPracaMocEnergia);
        buttonWynik = findViewById(R.id.buttonObliczPracaMocEnergia);
        doWyslania = new ArrayList<>();
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<>();
        Intent incomingIntent = getIntent();
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        checkbox = incomingIntent.getStringExtra("checkbox");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        final String nick = incomingIntent.getStringExtra("nick");
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();
        if(checkbox.equals("kat")){
            checkBoxKat.setChecked(true);
        }
        else if(checkbox.equals("droga")){
            checkBoxDroga.setChecked(true);
        }
        else if(checkbox.equals("czas")){
            checkBoxCzas.setChecked(true);
        }
        else if(checkbox.equals("moc")){
            checkBoxMoc.setChecked(true);
        }
        else if(checkbox.equals("sila")){
            checkBoxSila.setChecked(true);
        }
        else if(checkbox.equals("praca")){
            checkBoxPraca.setChecked(true);
        }
        else if(checkbox.equals("przyspieszenie")){
            checkBoxPrzyspieszenie.setChecked(true);
        }
        else if(checkbox.equals("wspolczynnik")){
            checkBoxWspolczynnik.setChecked(true);
        }
        else if(checkbox.equals("masa")){
            checkBoxMasa.setChecked(true);
        }
        else if(checkbox.equals("predkosc")){
            checkBoxPredkosc.setChecked(true);
        }
        else if(checkbox.equals("enKinetyczna")){
            checkBoxEnKinetyczna.setChecked(true);
        }
        else if(checkbox.equals("enPotencjalna")){
            checkBoxEnPotencjalna.setChecked(true);
        }
        if(incomingList!=null){
            droga.setText(incomingList.get(0));
            jednostkaDroga.setText(incomingList.get(1));
            czas.setText(incomingList.get(2));
            jednostkaCzas.setText(incomingList.get(3));
            moc.setText(incomingList.get(4));
            jednostkaMoc.setText(incomingList.get(5));
            przyspieszenie.setText(incomingList.get(6));
            jednostkaPrzyspieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
            masa.setText(incomingList.get(8));
            jednostkaMasa.setText(incomingList.get(9));
            praca.setText(incomingList.get(10));
            jednostkaPraca.setText(incomingList.get(11));
            sila.setText(incomingList.get(12));
            jednostkaSila.setText(incomingList.get(13));
            opory.setText(incomingList.get(14));
            jednostkaOpory.setText(incomingList.get(15));
            wspolczynnik.setText(incomingList.get(16));
            kat.setText(incomingList.get(17));
            pierwszaLinia.setText(incomingList.get(18));
            drugaLinia.setText(incomingList.get(19));
            trzeciaLinia.setText(incomingList.get(20));
            czwartaLinia.setText(incomingList.get(21));
            wynik.setText(incomingList.get(22));
            jednostkaWynik.setText(incomingList.get(23));
            predkoscPocz.setText(incomingList.get(24));
            jednostkaPredkoscPocz.setText(incomingList.get(25));
            enPotencjalna.setText(incomingList.get(26));
            jednostkaEnPotencjalna.setText(incomingList.get(27));
            enKinetyczna.setText(incomingList.get(28));
            jednostkaEnKinetyczna.setText(incomingList.get(29));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(droga.getText().toString());
                doWyslania.add(jednostkaDroga.getText().toString());
                doWyslania.add(czas.getText().toString());
                doWyslania.add(jednostkaCzas.getText().toString());
                doWyslania.add(moc.getText().toString());
                doWyslania.add(jednostkaMoc.getText().toString());
                doWyslania.add(przyspieszenie.getText().toString());
                doWyslania.add(jednostkaPrzyspieszenie.getText().toString());
                doWyslania.add(masa.getText().toString());
                doWyslania.add(jednostkaMasa.getText().toString());
                doWyslania.add(praca.getText().toString());
                doWyslania.add(jednostkaPraca.getText().toString());
                doWyslania.add(sila.getText().toString());
                doWyslania.add(jednostkaSila.getText().toString());
                doWyslania.add(opory.getText().toString());
                doWyslania.add(jednostkaOpory.getText().toString());
                doWyslania.add(wspolczynnik.getText().toString());
                doWyslania.add(kat.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                doWyslania.add(predkoscPocz.getText().toString());
                doWyslania.add(jednostkaPredkoscPocz.getText().toString());
                doWyslania.add(enPotencjalna.getText().toString());
                doWyslania.add(jednostkaEnPotencjalna.getText().toString());
                doWyslania.add(enKinetyczna.getText().toString());
                doWyslania.add(jednostkaEnKinetyczna.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxWspolczynnik.isChecked()){
                    ktoryCheckbox="wspolczynnik";
                }
                else if(checkBoxPrzyspieszenie.isChecked()){
                    ktoryCheckbox="przyspieszenie";
                }
                else if(checkBoxPraca.isChecked()){
                    ktoryCheckbox="praca";
                }
                else if(checkBoxMoc.isChecked()){
                    ktoryCheckbox="moc";
                }
                else if(checkBoxMasa.isChecked()){
                    ktoryCheckbox="masa";
                }
                else if(checkBoxKat.isChecked()){
                    ktoryCheckbox="kat";
                }
                else if(checkBoxDroga.isChecked()){
                    ktoryCheckbox="droga";
                }
                else if(checkBoxCzas.isChecked()){
                    ktoryCheckbox="czas";
                }
                else if(checkBoxSila.isChecked()){
                    ktoryCheckbox="sila";
                }
                else if(checkBoxPredkosc.isChecked()){
                    ktoryCheckbox="predkosc";
                }
                else if(checkBoxEnKinetyczna.isChecked()){
                    ktoryCheckbox = "enKinetyczna";
                }
                else if(checkBoxEnPotencjalna.isChecked()){
                    ktoryCheckbox = "enPotencjalna";
                }
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(PracaMocEnergia.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "PracaMocEnergia");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        i.putExtra("imageUrl", imageUr);
                        i.putExtra("nick", nick);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(PracaMocEnergia.this, Szkola.class);
                        i1.putExtra("imageUrl", imageUr);
                        i1.putExtra("nick", nick);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(PracaMocEnergia.this, Czat.class);
                        i2.putExtra("miejsce", "PracaMocEnergia");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.putExtra("imageUrl", imageUr);
                        i2.putExtra("nick", nick);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(PracaMocEnergia.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "PracaMocEnergia");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        i3.putExtra("imageUrl", imageUr);
                        i3.putExtra("nick", nick);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(PracaMocEnergia.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "PracaMocEnergia");
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
        jednostkaWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaWynik);
                openContextMenu(v);
            }
        });
        jednostkaSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaSila);
                openContextMenu(v);
            }
        });
        jednostkaPraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPraca);
                openContextMenu(v);
            }
        });
        jednostkaSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaSila);
                openContextMenu(v);
            }
        });
        jednostkaMoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaMoc);
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
        jednostkaOpory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaOpory);
                openContextMenu(v);
            }
        });
        jednostkaEnKinetyczna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaEnKinetyczna);
                openContextMenu(v);
            }
        });
        jednostkaEnPotencjalna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaEnPotencjalna);
                openContextMenu(v);
            }
        });
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxPredkosc.isChecked()){
                        Double vK=null;
                        if((!droga.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double v0 = Double.parseDouble(predkoscPocz.getText().toString());
                            v0 = funkcjePrzelicznikowe.predkosc(v0, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>k0</small></small></sub>=E<sub><small><small>p</small></small></sub>+E<sub><small><small>k</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("mv<sub><small><small>0</small></small></sub><sup><small><small>2</small></small></sup>/2=(mgh)+(mv<sup><small><small>2</small></small></sup>/2)"));
                            trzeciaLinia.setText(Html.fromHtml("v<sub><small><small>0</small></small></sub><sup><small><small>2</small></small></sup>=2gh+v<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("v=&#8730;(v<sub><small><small>0</small></small></sub><sup><small><small>2</small></small></sup>-2gh)"));
                            v0 = v0*v0;
                            drog = 2*10*drog;
                            vK = v0-drog;
                            vK = Math.sqrt(vK);
                            vK=funkcjePrzelicznikowe.predkoscWynik(vK, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(vK);
                            wynik.setText(x);
                        }
                        else if((!praca.getText().toString().equals(""))&&(!sila.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))){
                            Double prac = Double.parseDouble(praca.getText().toString());
                            prac = funkcjePrzelicznikowe.praca(prac, jednostkaPraca.getText().toString());
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText("s=v*t");
                            trzeciaLinia.setText("W=F*v*t");
                            czwartaLinia.setText("v=W/(F*t)");
                            vK = prac/sil/czass;
                            vK = funkcjePrzelicznikowe.predkoscWynik(vK, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(vK);
                            wynik.setText(x);
                        }
                        else if((!enKinetyczna.getText().toString().equals(""))&&(!masa.getText().toString().equals(""))){
                            Double enKin = Double.parseDouble(enKinetyczna.getText().toString());
                            enKin = funkcjePrzelicznikowe.praca(enKin, jednostkaEnKinetyczna.getText().toString());
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("v<sup><small><small>2</small></small></sup>=2*E<sub><small><small>k</small></small></sub>/m"));
                            trzeciaLinia.setText(Html.fromHtml("v=&#8730;(2*E<sub><small><small>k</small></small></sub>/m)"));
                            czwartaLinia.setText("");
                            vK = 2*enKin/mas;
                            vK = Math.sqrt(vK);
                            vK = funkcjePrzelicznikowe.predkoscWynik(vK, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(vK);
                            wynik.setText(x);
                        }
                        else if((!enKinetyczna.getText().toString().equals(""))&&(!enPotencjalna.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double enKin = Double.parseDouble(enKinetyczna.getText().toString());
                            enKin = funkcjePrzelicznikowe.praca(enKin, jednostkaEnKinetyczna.getText().toString());
                            Double enPoten = Double.parseDouble(enPotencjalna.getText().toString());
                            enPoten = funkcjePrzelicznikowe.praca(enPoten, jednostkaEnPotencjalna.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*g*h"));
                            drugaLinia.setText(Html.fromHtml("m=E<sub><small><small>pg</small></small></sub>/(g*h)"));
                            trzeciaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            czwartaLinia.setText(Html.fromHtml("v=&#8730;(2*E<sub><small><small>k</small></small></sub>/m)"));
                            Double mas = enPoten/10/drog;
                            vK = enKin*2/mas;
                            vK = Math.sqrt(vK);
                            vK = funkcjePrzelicznikowe.predkoscWynik(vK, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(vK);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxDroga.isChecked()){
                        Double drog=null;
                        if(!predkoscPocz.getText().toString().equals("")){
                            Double v0 = Double.parseDouble(predkoscPocz.getText().toString());
                            v0 = funkcjePrzelicznikowe.predkosc(v0, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=E<sub><small><small>k</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("mgh=v<sup><small><small>2</small></small></sup>/2"));
                            trzeciaLinia.setText(Html.fromHtml("gh=v<sup><small><small>2</small></small></sup>/2"));
                            czwartaLinia.setText(Html.fromHtml("h=v<sup><small><small>2</small></small></sup>/2g"));
                            drog=v0*v0/2/10;
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(drog);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxSila.isChecked()){
                        Double sil = null;
                        if((!predkoscPocz.getText().toString().equals(""))&&(!moc.getText().toString().equals(""))){
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            Double mocc = Double.parseDouble(moc.getText().toString());
                            mocc = funkcjePrzelicznikowe.moc(mocc, jednostkaMoc.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("P=F*s/t");
                            trzeciaLinia.setText("P=F*v");
                            czwartaLinia.setText("F=P/v");
                            sil = mocc/predkosc;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!praca.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double prac = Double.parseDouble(praca.getText().toString());
                            prac = funkcjePrzelicznikowe.praca(prac, jednostkaPraca.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText("F=W/s");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sil = prac / drog;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!enKinetyczna.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double enKin = Double.parseDouble(enKinetyczna.getText().toString());
                            enKin = funkcjePrzelicznikowe.praca(enKin, jednostkaEnKinetyczna.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("W=E<sub><small><small>k</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F*s=E<sub><small><small>k</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("F=E<sub><small><small>k</small></small></sub>/s"));
                            czwartaLinia.setText("");
                            sil = enKin / drog;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x =funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!predkoscPocz.getText().toString().equals(""))&&(!masa.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            pierwszaLinia.setText(Html.fromHtml("W=E<sub><small><small>k</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("F*s=E<sub><small><small>k</small></small></sub>"));
                            trzeciaLinia.setText(Html.fromHtml("F=E<sub><small><small>k</small></small></sub>/s"));
                            Double enKin = mas*predkosc*predkosc/2;
                            sil = enKin / drog;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x =funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxKat.isChecked()){
                        Double katt=null;
                        if((!sila.getText().toString().equals(""))&&(!praca.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            Double prac = Double.parseDouble(praca.getText().toString());
                            prac = funkcjePrzelicznikowe.praca(prac, jednostkaPraca.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("W=F*s*cos&#945;"));
                            drugaLinia.setText(Html.fromHtml("cos&#945;=W/(f*s)"));
                            Double cos = prac/sil/drog;
                            trzeciaLinia.setText(Html.fromHtml("cos&#945;="+cos));
                            czwartaLinia.setText("");
                            katt = Math.acos(cos);
                            katt = Math.toDegrees(katt);
                            String x = funkcjePrzelicznikowe.intowanie(katt);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxWspolczynnik.isChecked()){
                        Double wspol = null;
                        if((!masa.getText().toString().equals(""))&&(!praca.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double prac = Double.parseDouble(praca.getText().toString());
                            prac = funkcjePrzelicznikowe.praca(prac, jednostkaPraca.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText("T=F");
                            drugaLinia.setText("T=W/s");
                            trzeciaLinia.setText("fmg=W/s");
                            czwartaLinia.setText("f=W/mgs");
                            wspol = prac/mas/10/drog;
                            String x = funkcjePrzelicznikowe.intowanie(wspol);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxPraca.isChecked()){
                        Double prac=null;
                        if((!masa.getText().toString().equals(""))&&(!sila.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))&&(!kat.getText().toString().equals(""))&&(!opory.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            Double katt = Double.parseDouble(kat.getText().toString());
                            Double opor = Double.parseDouble(opory.getText().toString());
                            opor = funkcjePrzelicznikowe.sila(opor, jednostkaOpory.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>w</small></small></sub>=ma"));
                            drugaLinia.setText(Html.fromHtml("a=(F*cos&#945;-F<sub><small><small>op</small></small></sub>)/m"));
                            trzeciaLinia.setText(Html.fromHtml("s=at<sup><small><small>2</small></small></sup>/2      |      v=(at)<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText(Html.fromHtml("W=(F<sub><small><small>op</small></small></sub>*s)+(mv<sup><small><small>2</small></small></sup>/2)"));
                            katt = Math.toRadians(katt);
                            Double cos = Math.cos(katt);
                            Double przyszpiesz = (sil*cos-opor)/mas;
                            Double drog = przyszpiesz*czass*czass/2;
                            prac = opor*drog+(mas*(przyszpiesz*czass)*(przyszpiesz*czass)/2);
                            prac = funkcjePrzelicznikowe.pracaWynik(prac, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(prac);
                            wynik.setText(x);
                        }
                        else if((!droga.getText().toString().equals(""))&&(!opory.getText().toString().equals(""))){
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double opor = Double.parseDouble(opory.getText().toString());
                            opor = funkcjePrzelicznikowe.sila(opor, jednostkaOpory.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText("W=F<sub><small><small>op</small></small></sub>*s");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            prac = drog * opor;
                            prac = funkcjePrzelicznikowe.pracaWynik(prac, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(prac);
                            wynik.setText(x);
                        }
                        else if((!droga.getText().toString().equals(""))&&(!sila.getText().toString().equals(""))){
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            prac = drog * sil;
                            prac = funkcjePrzelicznikowe.pracaWynik(prac, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(prac);
                            wynik.setText(x);
                        }
                        else if((!sila.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText("s=v*t");
                            trzeciaLinia.setText("W=F*v*t");
                            czwartaLinia.setText("");
                            prac  = sil * czass*predkosc;
                            prac = funkcjePrzelicznikowe.pracaWynik(prac,jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(prac);
                            wynik.setText(x);
                        }
                        else if((!masa.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText(Html.fromHtml("W=F<sub><small><small>gr</small></small></sub>*s"));
                            trzeciaLinia.setText("W=m*g*s");
                            czwartaLinia.setText("W=m*10*s");
                            prac = mas*10*drog;
                            prac = funkcjePrzelicznikowe.pracaWynik(prac, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(prac);
                            wynik.setText(x);
                        }
                        else if((!czas.getText().toString().equals(""))&&(!moc.getText().toString().equals(""))){
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            Double mocc = Double.parseDouble(moc.getText().toString());
                            mocc = funkcjePrzelicznikowe.moc(mocc, jednostkaMoc.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("W=P*t");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            prac = mocc*czass;
                            prac = funkcjePrzelicznikowe.pracaWynik(prac, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(prac);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxMasa.isChecked()){
                        Double mas = null;
                        if((!praca.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double prac = Double.parseDouble(praca.getText().toString());
                            prac = funkcjePrzelicznikowe.praca(prac, jednostkaPraca.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText("W=F*s");
                            drugaLinia.setText("W=m*g*s");
                            trzeciaLinia.setText("m=W/(g*s)");
                            czwartaLinia.setText("");
                            mas = prac/10/drog;
                            mas = funkcjePrzelicznikowe.masaWynik(mas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mas);
                            wynik.setText(x);
                        }
                        else if((!enKinetyczna.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double enKin = Double.parseDouble(enKinetyczna.getText().toString());
                            enKin = funkcjePrzelicznikowe.praca(enKin, jednostkaEnKinetyczna.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("m=2*E<sub><small><small>k</small></small></sub>/v<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            mas = 2*enKin/predkosc/predkosc;
                            mas = funkcjePrzelicznikowe.masaWynik(mas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mas);
                            wynik.setText(x);
                        }
                        else if((!enPotencjalna.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double enPoten = Double.parseDouble(enPotencjalna.getText().toString());
                            enPoten = funkcjePrzelicznikowe.praca(enPoten, jednostkaEnPotencjalna.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*g*h"));
                            drugaLinia.setText(Html.fromHtml("m=E<sub><small><small>pg</small></small></sub>/(g*h)"));
                            trzeciaLinia.setText(Html.fromHtml("m=E<sub><small><small>pg</small></small></sub>/(10*h)"));
                            czwartaLinia.setText("");
                            mas = enPoten/10/drog;
                            mas = funkcjePrzelicznikowe.masaWynik(mas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mas);
                            wynik.setText(x);
                        }
                        else if((!moc.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))){
                            Double mocc = Double.parseDouble(moc.getText().toString());
                            mocc = funkcjePrzelicznikowe.moc(mocc, jednostkaMoc.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("P=F*s/t");
                            trzeciaLinia.setText("P=m*g*s/t");
                            czwartaLinia.setText("m=P*t/(g*s)");
                            mas = mocc*czass/10/drog;
                            mas = funkcjePrzelicznikowe.masaWynik(mas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mas);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxMoc.isChecked()){
                        Double mocc = null;
                        if((!masa.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("P=F*s/t");
                            trzeciaLinia.setText("P=m*g*s/t");
                            czwartaLinia.setText("P=m*10*s/t");
                            mocc = mas*10*drog/czass;
                            mocc = funkcjePrzelicznikowe.mocWynik(mocc,jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mocc);
                            wynik.setText(x);
                        }
                        else if((!sila.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))&&(!czas.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil,jednostkaSila.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double czass = Double.parseDouble(czas.getText().toString());
                            czass = funkcjePrzelicznikowe.czas(czass, jednostkaCzas.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("P=F*s/t");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            mocc = sil * drog/czass;
                            mocc = funkcjePrzelicznikowe.mocWynik(mocc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mocc);
                            wynik.setText(x);
                        }
                        else if((!opory.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double opor = Double.parseDouble(opory.getText().toString());
                            opor = funkcjePrzelicznikowe.sila(opor, jednostkaOpory.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("P=F*s/t");
                            trzeciaLinia.setText("v=s/t");
                            czwartaLinia.setText("P=F<sub><small><small>op</small></small></sub>*v");
                            mocc = opor*predkosc;
                            mocc = funkcjePrzelicznikowe.mocWynik(mocc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mocc);
                            wynik.setText(x);
                        }
                        else if((!sila.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText("P=W/t");
                            drugaLinia.setText("P=F*s/t");
                            trzeciaLinia.setText("v=s/t");
                            czwartaLinia.setText("P=F*v");
                            mocc = sil*predkosc;
                            mocc = funkcjePrzelicznikowe.mocWynik(mocc, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mocc);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxEnPotencjalna.isChecked()){
                        Double enPotencj=null;
                        if((!masa.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*g*h"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*10*h"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            enPotencj = 10*mas*drog;
                            enPotencj = funkcjePrzelicznikowe.pracaWynik(enPotencj, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(enPotencj);
                            wynik.setText(x);
                        }
                        else if((!enKinetyczna.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))){
                            Double enKin = Double.parseDouble(enKinetyczna.getText().toString());
                            enKin = funkcjePrzelicznikowe.praca(enKin, jednostkaEnKinetyczna.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("m=2*E<sub><small><small>k</small></small></sub>/v<sup><small><small>2</small></small></sup>"));
                            trzeciaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*g*h"));
                            czwartaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*10*h"));
                            Double mas = 2*enKin/predkosc/predkosc;
                            enPotencj = mas*10*drog;
                            enPotencj = funkcjePrzelicznikowe.pracaWynik(enPotencj, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(enPotencj);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxEnKinetyczna.isChecked()){
                        Double enKin = null;
                        if((!masa.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            enKin = mas*predkosc*predkosc/2;
                            enKin = funkcjePrzelicznikowe.pracaWynik(enKin, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(enKin);
                            wynik.setText(x);
                        }
                        else if((!enPotencjalna.getText().toString().equals(""))&&(!droga.getText().toString().equals(""))&&(!predkoscPocz.getText().toString().equals(""))){
                            Double enPoten = Double.parseDouble(enPotencjalna.getText().toString());
                            enPoten = funkcjePrzelicznikowe.praca(enPoten, jednostkaEnPotencjalna.getText().toString());
                            Double drog = Double.parseDouble(droga.getText().toString());
                            drog = funkcjePrzelicznikowe.dlugoscPred(drog, jednostkaDroga.getText().toString());
                            Double predkosc = Double.parseDouble(predkoscPocz.getText().toString());
                            predkosc = funkcjePrzelicznikowe.predkosc(predkosc, jednostkaPredkoscPocz.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>pg</small></small></sub>=m*g*h"));
                            drugaLinia.setText(Html.fromHtml("m=E<sub><small><small>pg</small></small></sub>/(g*h)"));
                            trzeciaLinia.setText(Html.fromHtml("m=E<sub><small><small>pg</small></small></sub>/(10*h)"));
                            czwartaLinia.setText(Html.fromHtml("E<sub><small><small>k</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            Double mas = enPoten/10/drog;
                            enKin = mas*predkosc*predkosc/2;
                            enKin = funkcjePrzelicznikowe.pracaWynik(enKin, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(enKin);
                            wynik.setText(x);
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                droga.setText("");
                jednostkaDroga.setText("m");
                czas.setText("");
                jednostkaCzas.setText("s");
                masa.setText("");
                jednostkaMasa.setText("kg");
                praca.setText("");
                jednostkaPraca.setText("J");
                sila.setText("");
                jednostkaSila.setText("N");
                przyspieszenie.setText("");
                jednostkaPrzyspieszenie.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                wspolczynnik.setText("");
                opory.setText("");
                jednostkaOpory.setText("N");
                moc.setText("");
                jednostkaMoc.setText("W");
                predkoscPocz.setText("");
                jednostkaPredkoscPocz.setText("m/s");
                enKinetyczna.setText("");
                enPotencjalna.setText("");
                jednostkaEnKinetyczna.setText("J");
                jednostkaEnPotencjalna.setText("J");
                kat.setText("");
                checkBoxEnKinetyczna.setChecked(false);
                checkBoxEnPotencjalna.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxCzas.setChecked(false);
            }
        });
        checkBoxDroga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxDroga.isChecked()){
                    jednostkaWynik.setText(drogaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        drogaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxCzas.isChecked()){
                    jednostkaWynik.setText(czasWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        czasWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxKat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxKat.isChecked()){
                    jednostkaWynik.setText(R.string.stopnie);
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxMoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxMoc.isChecked()){
                    jednostkaWynik.setText(mocWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        mocWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxPraca.isChecked()){
                    jednostkaWynik.setText(pracaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        pracaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxMasa.isChecked()){
                    jednostkaWynik.setText(masaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        masaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxPrzyspieszenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxPrzyspieszenie.isChecked()){
                    jednostkaWynik.setText(Html.fromHtml("m/s<sup><small><small>2</small></small></sup>"));
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxWspolczynnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                jednostkaWynik.setText("");
                wyczyscLinie();
            }
        });
        checkBoxSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxSila.isChecked()){
                    jednostkaWynik.setText(silaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        silaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxEnPotencjalna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxEnKinetyczna.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                if(checkBoxEnPotencjalna.isChecked()){
                    jednostkaWynik.setText(potencjalnaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        potencjalnaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxEnKinetyczna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
                checkBoxKat.setChecked(false);
                checkBoxMoc.setChecked(false);
                checkBoxPraca.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPrzyspieszenie.setChecked(false);
                checkBoxWspolczynnik.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxDroga.setChecked(false);
                checkBoxPredkosc.setChecked(false);
                checkBoxEnPotencjalna.setChecked(false);
                if(checkBoxEnKinetyczna.isChecked()){
                    jednostkaWynik.setText(kinetycznaWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        kinetycznaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        mdrawerLayout = findViewById(R.id.drawerPracaMocEnergia_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(PracaMocEnergia.this, Konto.class);
                i.putExtra("miejsce", "PracaMocEnergia");
                startActivity(i);
                Animatoo.animateFade(PracaMocEnergia.this);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.jednostkaCzasPracaMocEnergia){
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory="czas";
        }
        else if(v.getId()==R.id.jednostkaDrogaPracaMocEnergia){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="droga";
        }
        else if(v.getId()==R.id.jednostkaMasaPracaMocEnergia){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="masa";
        }
        else if(v.getId()==R.id.jednostkaPredkoscPracaMocEnergia){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="predkosc";
        }
        else if(v.getId()==R.id.jednostkaOporyPracaMocEnergia){
            getMenuInflater().inflate(R.menu.sila_menu, menu);
            ktory="opory";
        }
        else if(v.getId()==R.id.jednostkaSilaPracaMocEnergia){
            getMenuInflater().inflate(R.menu.sila_menu, menu);
            ktory="sila";
        }
        else if(v.getId()==R.id.jednostkaMocPracaMocEnergia){
            getMenuInflater().inflate(R.menu.moc_menu, menu);
            ktory="moc";
        }
        else if(v.getId()==R.id.jednostkaPracaPracaMocEnergia){
            getMenuInflater().inflate(R.menu.praca_menu, menu);
            ktory="praca";
        }
        else if(v.getId()==R.id.jednostkaEnKinetycznaPracaMocEnergia){
            getMenuInflater().inflate(R.menu.praca_menu, menu);
            ktory = "enKinetyczna";
        }
        else if(v.getId()==R.id.jednostkaEnPotencjalnaPracaMocEnergia){
            getMenuInflater().inflate(R.menu.praca_menu, menu);
            ktory = "enPotencjalna";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&(checkBoxPredkosc.isChecked())){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&(checkBoxSila.isChecked())){
            getMenuInflater().inflate(R.menu.sila_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&(checkBoxMasa.isChecked())){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&(checkBoxCzas.isChecked())){
            getMenuInflater().inflate(R.menu.czas2_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&(checkBoxMoc.isChecked())){
            getMenuInflater().inflate(R.menu.moc_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&(checkBoxDroga.isChecked())){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
        else if((v.getId()==R.id.jednostkaWynikPracaMocEnergia)&&((checkBoxPraca.isChecked())||(checkBoxEnKinetyczna.isChecked())||(checkBoxEnPotencjalna.isChecked()))){
            getMenuInflater().inflate(R.menu.praca_menu, menu);
            ktory="wynik";
        }
    }
    String drogaWynik="m", czasWynik="s", masaWynik="kg", pracaWynik="J", silaWynik="N", potencjalnaWynik="J";
    String mocWynik="W", predkoscWynik="m/s", kinetycznaWynik="J";
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Centymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("cm");
                    drogaWynik = "cm";
                }
                else{
                    jednostkaDroga.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dm");
                    drogaWynik = "dm";
                }
                else {
                    jednostkaDroga.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m");
                    drogaWynik = "m";
                }
                else {
                    jednostkaDroga.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km");
                    drogaWynik = "km";
                }
                else {
                    jednostkaDroga.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sekunda2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("s");
                    czasWynik = "s";
                }
                else {
                    jednostkaCzas.setText("s");
                }
                Toast.makeText(this, "Sekunda", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.minuta2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("min");
                    czasWynik = "min";
                }
                else {
                    jednostkaCzas.setText("min");
                }
                Toast.makeText(this, "Minuta", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.godzina2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("h");
                    czasWynik = "h";
                }
                else {
                    jednostkaCzas.setText("h");
                }
                Toast.makeText(this, "Godzina", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Wat:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("W");
                    mocWynik = "W";
                }
                else{
                    jednostkaMoc.setText("W");
                }
                Toast.makeText(this, "Wat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilowat:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("kW");
                    mocWynik = "kW";
                }
                else{
                    jednostkaMoc.setText("kW");
                }
                Toast.makeText(this, "Kilowat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Megawat:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("MW");
                    mocWynik = "MW";
                }
                else{
                    jednostkaMoc.setText("MW");
                }
                Toast.makeText(this, "Megawat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Konie:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("kM");
                    mocWynik = "kM";
                }
                else{
                    jednostkaMoc.setText("kM");
                }
                Toast.makeText(this, "Konie mechaniczne", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.miliwat:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("mW");
                    silaWynik = "mW";
                }
                else{
                    jednostkaMoc.setText("mW");
                }
                Toast.makeText(this, "Miliwat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.meganiuton2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("mN");
                    silaWynik = "mN";
                }
                else if(checkBoxSila.isChecked()){
                    jednostkaSila.setText("mN");
                }
                else{
                    jednostkaOpory.setText("mN");
                }
                Toast.makeText(this, "Miliniuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.niuton:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("N");
                    silaWynik = "N";
                }
                else if(checkBoxSila.isChecked()){
                    jednostkaSila.setText("N");
                }
                else{
                    jednostkaOpory.setText("N");
                }
                Toast.makeText(this, "Niuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kiloniuton:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("kN");
                    silaWynik = "kN";
                }
                else{
                    jednostkaSila.setText("kN");
                }
                Toast.makeText(this, "Kiloniuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/s");
                    predkoscWynik = "m/s";
                }
                else{
                    jednostkaPredkoscPocz.setText("m/s");
                }
                Toast.makeText(this, "Metry na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/min");
                    predkoscWynik = "m/min";
                }
                else{
                    jednostkaPredkoscPocz.setText("m/min");
                }
                Toast.makeText(this, "Metry na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/s");
                    predkoscWynik = "km/s";
                }
                else{
                    jednostkaPredkoscPocz.setText("km/s");
                }
                Toast.makeText(this, "Kilometry na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/m");
                    predkoscWynik = "km/m";
                }
                else{
                    jednostkaPredkoscPocz.setText("km/m");
                }
                Toast.makeText(this, "Kilometry na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaH:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/h");
                    predkoscWynik = "km/h";
                }
                else{
                    jednostkaPredkoscPocz.setText("km/h");
                }
                Toast.makeText(this, "Kilometry na godzinę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("g");
                    masaWynik = "g";
                }
                else{
                    jednostkaMasa.setText("g");
                }
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dag");
                    masaWynik = "dag";
                }
                else{
                    jednostkaMasa.setText("dag");
                }
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option4:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("kg");
                    masaWynik = "kg";
                }
                else{
                    jednostkaMasa.setText("kg");
                }
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option5:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("t");
                    masaWynik = "t";
                }
                else{
                    jednostkaMasa.setText("t");
                }
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dzule:
                if(ktory.equals("wynik")){
                    if(checkBoxEnPotencjalna.isChecked()){
                        potencjalnaWynik = "J";
                    }
                    else if(checkBoxEnKinetyczna.isChecked()){
                        kinetycznaWynik = "J";
                    }
                    else{
                        pracaWynik = "J";
                    }
                    jednostkaWynik.setText("J");
                }
                else if(ktory.equals("praca")){
                    jednostkaPraca.setText("J");
                }
                else if(ktory.equals("enPotencjalna")){
                    jednostkaEnPotencjalna.setText("J");
                }
                else if(ktory.equals("enKinetyczna")){
                    jednostkaEnKinetyczna.setText("J");
                }
                else{
                    jednostkaOpory.setText("J");
                }
                Toast.makeText(this, "Dżule", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kalorie:
                if(ktory.equals("wynik")){
                    if(checkBoxEnPotencjalna.isChecked()){
                        potencjalnaWynik = "cal";
                    }
                    else if(checkBoxEnKinetyczna.isChecked()){
                        kinetycznaWynik = "cal";
                    }
                    else{
                        pracaWynik = "cal";
                    }
                    jednostkaWynik.setText("cal");
                }
                else if(ktory.equals("praca")){
                    jednostkaPraca.setText("cal");
                }
                else if(ktory.equals("enPotencjalna")){
                    jednostkaEnPotencjalna.setText("cal");
                }
                else if(ktory.equals("enKinetyczna")){
                    jednostkaEnKinetyczna.setText("cal");
                }
                else{
                    jednostkaOpory.setText("cal");
                }
                Toast.makeText(this, "Kalorie", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kilojule:
                if(ktory.equals("wynik")){
                    if(checkBoxEnPotencjalna.isChecked()){
                        potencjalnaWynik = "kJ";
                    }
                    else if(checkBoxEnKinetyczna.isChecked()){
                        kinetycznaWynik = "kJ";
                    }
                    else{
                        pracaWynik = "kJ";
                    }
                    jednostkaWynik.setText("kJ");
                }
                else if(ktory.equals("praca")){
                    jednostkaPraca.setText("kJ");
                }
                else if(ktory.equals("enPotencjalna")){
                    jednostkaEnPotencjalna.setText("kJ");
                }
                else if(ktory.equals("enKinetyczna")){
                    jednostkaEnKinetyczna.setText("kJ");
                }
                else{
                    jednostkaOpory.setText("kJ");
                }
                Toast.makeText(this, "Kilodżule", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kilokalorie:
                if(ktory.equals("wynik")){
                    if(checkBoxEnPotencjalna.isChecked()){
                        potencjalnaWynik = "kcal";
                    }
                    else if(checkBoxEnKinetyczna.isChecked()){
                        kinetycznaWynik = "kcal";
                    }
                    else{
                        pracaWynik = "kcal";
                    }
                    jednostkaWynik.setText("kcal");
                }
                else if(ktory.equals("praca")){
                    jednostkaPraca.setText("kcal");
                }
                else if(ktory.equals("enPotencjalna")){
                    jednostkaEnPotencjalna.setText("kcal");
                }
                else if(ktory.equals("enKinetyczna")){
                    jednostkaEnKinetyczna.setText("kcal");
                }
                else{
                    jednostkaOpory.setText("kcal");
                }
                Toast.makeText(this, "Kilokalorie", Toast.LENGTH_SHORT).show();
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
        adapter = new CustomExpandableListAdapter(PracaMocEnergia.this, lstTitle, lstChild);
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
                    Intent i = new Intent(PracaMocEnergia.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(PracaMocEnergia.this);
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
            Intent i = new Intent(PracaMocEnergia.this, FizykaKalkulator.class);
            startActivity(i);
            Animatoo.animateFade(PracaMocEnergia.this);
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
