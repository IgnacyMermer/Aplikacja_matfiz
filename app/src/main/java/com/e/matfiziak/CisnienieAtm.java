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

public class CisnienieAtm extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private NavigationManager navigationManager;
    private Map<String, List<String>> lstChild;
    CheckBox checkBoxSila, checkBoxCisnienie, checkBoxMasa, checkBoxPowierzchnia;
    EditText sila, cisnienie, masa, powierzchnia;
    TextView jednostkaSila, jednostkaCisnienie, jednostkaMasa, jednostkaPowierzchnia, jednostkaWynik;
    TextView pierwszaLinia, drugaLinia, trzeciaLinia, czwartaLinia, wynik;
    ArrayList<String> doWyslania;
    Button buttonCzysc, buttonWynik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cisnienieatmo);
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
        checkBoxCisnienie = findViewById(R.id.checkboxCisnienieCisnienieAtm);
        checkBoxMasa = findViewById(R.id.checkboxMasaCisnienieAtm);
        checkBoxPowierzchnia = findViewById(R.id.checkboxPowierzchniaCisnienieAtm);
        checkBoxSila = findViewById(R.id.checkboxSilaCisnienieAtm);
        cisnienie = findViewById(R.id.cisnienieCisnienieAtm);
        masa = findViewById(R.id.masaCisnienieAtm);
        powierzchnia = findViewById(R.id.powierzchniaCisnienieAtm);
        sila = findViewById(R.id.silaCisnienieAtm);
        jednostkaCisnienie = findViewById(R.id.jednostkaCisnienieCisnienieAtm);
        jednostkaMasa = findViewById(R.id.jednostkaMasaCisnienieAtm);
        jednostkaPowierzchnia = findViewById(R.id.jednostkaPowierzchniaCisnienieAtm);
        jednostkaSila = findViewById(R.id.jednostkasilaCisnienieAtm);
        jednostkaWynik = findViewById(R.id.jednostkaWynikCisnienieAtm);
        pierwszaLinia = findViewById(R.id.pierwszaLiniaCisnienieAtm);
        drugaLinia = findViewById(R.id.drugaLiniaCisnienieAtm);
        trzeciaLinia = findViewById(R.id.trzeciaLiniaCisnienieAtm);
        czwartaLinia = findViewById(R.id.czwartaLiniaCisnienieAtm);
        wynik = findViewById(R.id.wynikCisnienieAtm);
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
        if(checkbox.equals("powierzchnia")){
            checkBoxPowierzchnia.setChecked(true);
        }
        else if(checkbox.equals("masa")){
            checkBoxMasa.setChecked(true);
        }
        else if(checkbox.equals("sila")){
            checkBoxSila.setChecked(true);
        }
        else if(checkbox.equals("cisnienie")){
            checkBoxCisnienie.setChecked(true);
        }
        if(incomingList!=null){
            sila.setText(incomingList.get(0));
            jednostkaSila.setText(incomingList.get(1));
            masa.setText(incomingList.get(2));
            jednostkaMasa.setText(incomingList.get(3));
            cisnienie.setText(incomingList.get(4));
            jednostkaCisnienie.setText(incomingList.get(5));
            powierzchnia.setText(incomingList.get(6));
            jednostkaPowierzchnia.setText(incomingList.get(7));
            pierwszaLinia.setText(incomingList.get(8));
            drugaLinia.setText(incomingList.get(9));
            trzeciaLinia.setText(incomingList.get(10));
            czwartaLinia.setText(incomingList.get(11));
            wynik.setText(incomingList.get(12));
            jednostkaWynik.setText(incomingList.get(13));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(sila.getText().toString());
                doWyslania.add(jednostkaSila.getText().toString());
                doWyslania.add(masa.getText().toString());
                doWyslania.add(jednostkaMasa.getText().toString());
                doWyslania.add(cisnienie.getText().toString());
                doWyslania.add(jednostkaCisnienie.getText().toString());
                doWyslania.add(powierzchnia.getText().toString());
                doWyslania.add(jednostkaPowierzchnia.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                String ktoryCheckbox="";
                if(checkBoxMasa.isChecked()){
                    ktoryCheckbox="masa";
                }
                else if(checkBoxSila.isChecked()){
                    ktoryCheckbox="sila";
                }
                else if(checkBoxCisnienie.isChecked()){
                    ktoryCheckbox="cisnienie";
                }
                else if(checkBoxPowierzchnia.isChecked()){
                    ktoryCheckbox="powierzchnia";
                }
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(CisnienieAtm.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "CisnienieAtm");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox", ktoryCheckbox);
                        i.putExtra("imageUrl", imageUr);
                        i.putExtra("nick", nick);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(CisnienieAtm.this, Szkola.class);
                        i1.putExtra("imageUrl", imageUr);
                        i1.putExtra("nick", nick);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(CisnienieAtm.this, Czat.class);
                        i2.putExtra("miejsce", "CisnienieAtm");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox", ktoryCheckbox);
                        i2.putExtra("imageUrl", imageUr);
                        i2.putExtra("nick", nick);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(CisnienieAtm.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "CisnienieHydro");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox", ktoryCheckbox);
                        i3.putExtra("imageUrl", imageUr);
                        i3.putExtra("nick", nick);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(CisnienieAtm.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "CisnienieHydro");
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
        jednostkaSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaSila);
                openContextMenu(v);
            }
        });
        jednostkaPowierzchnia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPowierzchnia);
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
        jednostkaCisnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaCisnienie);
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
        buttonCzysc = findViewById(R.id.buttonCzyscCisnienieAtm);
        buttonWynik = findViewById(R.id.buttonObliczCisnienieAtm);
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(checkBoxMasa.isChecked()){
                        Double mas = null;
                        if((!sila.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            pierwszaLinia.setText("F=m*g");
                            drugaLinia.setText("m=F/g");
                            trzeciaLinia.setText("m=F/10");
                            czwartaLinia.setText("");
                            mas = sil/10;
                            mas = funkcjePrzelicznikowe.masaWynik(mas, jednostkaMasa.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mas);
                            wynik.setText(x);
                        }
                        else if((!cisnienie.getText().toString().equals(""))&&(!powierzchnia.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.sila(powierzchn, jednostkaPowierzchnia.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText("p=m*g/S");
                            trzeciaLinia.setText("m=p*S/g");
                            czwartaLinia.setText("m=p*S/10");
                            mas = cisnien*powierzchn/10;
                            mas = funkcjePrzelicznikowe.masaWynik(mas, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(mas);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxCisnienie.isChecked()){
                        Double cisnien = null;
                        if((!sila.getText().toString().equals(""))&&(!powierzchnia.getText().toString().equals(""))){
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            cisnien = sil/powierzchn;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                            wynik.setText(x);
                        }
                        else if((!masa.getText().toString().equals(""))&&(!powierzchnia.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            Double powierzchn = Double.parseDouble(powierzchnia.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("p=F/S"));
                            drugaLinia.setText(Html.fromHtml("p=F<sub><small><small>g</small></small></sub>/S"));
                            trzeciaLinia.setText("p=m*g/S");
                            czwartaLinia.setText("p=m*10/S");
                            cisnien = mas*10/powierzchn;
                            cisnien = funkcjePrzelicznikowe.cisnienieWynik(cisnien, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(cisnien);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxSila.isChecked()){
                        Double sil = null;
                        if((!masa.getText().toString().equals(""))){
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("F<sub><small><small>g</small></small></sub>=m*g"));
                            drugaLinia.setText(Html.fromHtml("F<sub><small><small>g</small></small></sub>=m*10"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sil = mas*10;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                        else if((!powierzchnia.getText().toString().equals(""))&&(!cisnienie.getText().toString().equals(""))){
                            Double powierzchn = Double.parseDouble(powierzchnia.getText().toString());
                            powierzchn = funkcjePrzelicznikowe.pole(powierzchn, jednostkaPowierzchnia.getText().toString());
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText("F=p*S");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            sil = cisnien*powierzchn;
                            sil = funkcjePrzelicznikowe.silaWynik(sil, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(sil);
                            wynik.setText(x);
                        }
                    }
                    else if(checkBoxPowierzchnia.isChecked()){
                        Double powierzchn=null;
                        if((!cisnienie.getText().toString().equals(""))&&(!sila.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double sil = Double.parseDouble(sila.getText().toString());
                            sil = funkcjePrzelicznikowe.sila(sil, jednostkaSila.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText("S=F/p");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            powierzchn = sil/cisnien;
                            powierzchn = funkcjePrzelicznikowe.poleWynik(powierzchn, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(powierzchn);
                            wynik.setText(x);
                        }
                        else if((!cisnienie.getText().toString().equals(""))&&(!masa.getText().toString().equals(""))){
                            Double cisnien = Double.parseDouble(cisnienie.getText().toString());
                            cisnien = funkcjePrzelicznikowe.cisnienie(cisnien, jednostkaCisnienie.getText().toString());
                            Double mas = Double.parseDouble(masa.getText().toString());
                            mas = funkcjePrzelicznikowe.masa(mas, jednostkaMasa.getText().toString());
                            pierwszaLinia.setText("p=F/S");
                            drugaLinia.setText("p=m*g/S");
                            trzeciaLinia.setText("S=m*g/p");
                            czwartaLinia.setText("S=m*10/p");
                            powierzchn = mas*10/cisnien;
                            powierzchn = funkcjePrzelicznikowe.poleWynik(powierzchn, jednostkaWynik.getText().toString());
                            String x = funkcjePrzelicznikowe.intowanie(powierzchn);
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
                cisnienie.setText("");
                sila.setText("");
                powierzchnia.setText("");
                masa.setText("");
                jednostkaWynik.setText("");
                jednostkaSila.setText("N");
                jednostkaMasa.setText("kg");
                jednostkaCisnienie.setText("Pa");
                jednostkaPowierzchnia.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                checkBoxPowierzchnia.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxSila.setChecked(false);
                checkBoxMasa.setChecked(false);
                masaWynik = "kg";
                silaWynik = "N";
                powierzchniaWynik = "m";
                cisnienieWynik="Pa";
            }
        });
        checkBoxPowierzchnia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSila.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                if(checkBoxPowierzchnia.isChecked()){
                    if (powierzchniaWynik.equals("a")||powierzchniaWynik.equals("ha")){
                        jednostkaWynik.setText(powierzchniaWynik);
                    }
                    else{
                        jednostkaWynik.setText(Html.fromHtml(powierzchniaWynik+"<sup><small><small>2</small></small></sup>"));
                    }
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        powierzchniaWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkBoxSila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCisnienie.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPowierzchnia.setChecked(false);
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
        checkBoxMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSila.setChecked(false);
                checkBoxCisnienie.setChecked(false);
                checkBoxPowierzchnia.setChecked(false);
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
        checkBoxCisnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSila.setChecked(false);
                checkBoxMasa.setChecked(false);
                checkBoxPowierzchnia.setChecked(false);
                if(checkBoxCisnienie.isChecked()){
                    jednostkaWynik.setText(cisnienieWynik);
                }
                else{
                    if(!jednostkaWynik.getText().toString().equals("")){
                        cisnienieWynik = jednostkaWynik.getText().toString();
                    }
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        mdrawerLayout = findViewById(R.id.drawerCisnienieAtm_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(CisnienieAtm.this, Konto.class);
                i.putExtra("miejsce", "CisnienieAtm");
                startActivity(i);
                Animatoo.animateFade(CisnienieAtm.this);
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
    String ktory="", masaWynik="kg", cisnienieWynik="Pa", silaWynik="N", powierzchniaWynik="m";

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.jednostkaCisnienieCisnienieAtm){
            getMenuInflater().inflate(R.menu.cisnienie_menu, menu);
        }
        else if(v.getId()==R.id.jednostkasilaCisnienieAtm){
            getMenuInflater().inflate(R.menu.sila_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaMasaCisnienieAtm){
            getMenuInflater().inflate(R.menu.example_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaPowierzchniaCisnienieAtm){
            getMenuInflater().inflate(R.menu.pole_menu, menu);
        }
        else if(v.getId()==R.id.jednostkaWynikCisnienieAtm){
            if(checkBoxSila.isChecked()){
                ktory = "sila";
                getMenuInflater().inflate(R.menu.sila_menu, menu);
            }
            else if(checkBoxPowierzchnia.isChecked()){
                ktory = "powierzchnia";
                getMenuInflater().inflate(R.menu.pole_menu, menu);
            }
            else if(checkBoxMasa.isChecked()){
                ktory = "masa";
                getMenuInflater().inflate(R.menu.example_menu, menu);
            }
            else if(checkBoxCisnienie.isChecked()){
                ktory="cisnienie";
                getMenuInflater().inflate(R.menu.cisnienie_menu, menu);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option2:
                if(ktory.equals("masa")){
                    jednostkaWynik.setText("g");
                    masaWynik = "g";
                }
                else{
                    jednostkaMasa.setText("g");
                }
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                if(ktory.equals("masa")){
                    jednostkaWynik.setText("dag");
                    masaWynik = "dag";
                }
                else{
                    jednostkaMasa.setText("dag");
                }
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option4:
                if(ktory.equals("masa")){
                    jednostkaWynik.setText("kg");
                    masaWynik = "kg";
                }
                else{
                    jednostkaMasa.setText("kg");
                }
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option5:
                if(ktory.equals("masa")){
                    jednostkaWynik.setText("t");
                    masaWynik = "t";
                }
                else{
                    jednostkaMasa.setText("t");
                }
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MilimetrKwadrat1:
                if(ktory.equals("powierzchnia")){
                    jednostkaWynik.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                    powierzchniaWynik = "mm";
                }
                else{
                    jednostkaPowierzchnia.setText(Html.fromHtml("mm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Milimetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CentymetrKwadrat1:
                if(ktory.equals("powierzchnia")){
                    jednostkaWynik.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                    powierzchniaWynik = "cm";
                }
                else{
                    jednostkaPowierzchnia.setText(Html.fromHtml("cm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Centymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DecymetrKwadrat1:
                if(ktory.equals("powierzchnia")){
                    jednostkaWynik.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                    powierzchniaWynik = "dm";
                }
                else{
                    jednostkaPowierzchnia.setText(Html.fromHtml("dm<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Decymetr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MetrKwadrat1:
                if(ktory.equals("powierzchnia")){
                    jednostkaWynik.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                    powierzchniaWynik = "m";
                }
                else{
                    jednostkaPowierzchnia.setText(Html.fromHtml("m<sup><small><small>2</small></small></sup>"));
                }
                Toast.makeText(this, "Metr kwadratowy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Ar1:
                if(ktory.equals("powierzchnia")){
                    jednostkaWynik.setText("a");
                    powierzchniaWynik = "a";
                }
                else{
                    jednostkaPowierzchnia.setText("a");
                }
                Toast.makeText(this, "Ar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Hektar1:
                if(ktory.equals("powierzchnia")){
                    jednostkaWynik.setText("ha");
                    powierzchniaWynik = "ha";
                }
                else{
                    jednostkaPowierzchnia.setText("ha");
                }
                Toast.makeText(this, "Hektar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.miliniuton:
                if(ktory.equals("sila")){
                    jednostkaWynik.setText("mN");
                    silaWynik = "mN";
                }
                else{
                    jednostkaSila.setText("mN");
                }
                Toast.makeText(this, "Miliniuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.niuton:
                if(ktory.equals("sila")){
                    jednostkaWynik.setText("N");
                    silaWynik = "N";
                }
                else{
                    jednostkaSila.setText("N");
                }
                Toast.makeText(this, "Niuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kiloniuton:
                if(ktory.equals("sila")){
                    jednostkaWynik.setText("kN");
                    silaWynik = "kN";
                }
                else{
                    jednostkaSila.setText("kN");
                }
                Toast.makeText(this, "Kiloniuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.meganiuton2:
                if(ktory.equals("sila")){
                    jednostkaWynik.setText("MN");
                    silaWynik = "MN";
                }
                else{
                    jednostkaSila.setText("MN");
                }
                Toast.makeText(this, "Meganiuton", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.paskal:
                if(ktory.equals("cisnienie")){
                    jednostkaWynik.setText("Pa");
                    cisnienieWynik = "Pa";
                }
                else{
                    jednostkaCisnienie.setText("Pa");
                }
                Toast.makeText(this, "Paskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.hektopaskal:
                if(ktory.equals("cisnienie")){
                    jednostkaWynik.setText("hPa");
                    cisnienieWynik = "hPa";
                }
                else{
                    jednostkaCisnienie.setText("hPa");
                }
                Toast.makeText(this, "Hektopaskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kilopaskal:
                if(ktory.equals("cisnienie")){
                    jednostkaWynik.setText("kPa");
                    cisnienieWynik = "kPa";
                }
                else{
                    jednostkaCisnienie.setText("kPa");
                }
                Toast.makeText(this, "Kilopaskal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.atmosfera:
                if(ktory.equals("cisnienie")){
                    jednostkaWynik.setText("Atm");
                    cisnienieWynik = "Atm";
                }
                else{
                    jednostkaCisnienie.setText("Atm");
                }
                Toast.makeText(this, "Atmosfera", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bar:
                if(ktory.equals("cisnienie")){
                    jednostkaWynik.setText("b");
                    cisnienieWynik = "b";
                }
                else{
                    jednostkaCisnienie.setText("b");
                }
                Toast.makeText(this, "Bar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.megapaskal:
                if(ktory.equals("cisnienie")){
                    jednostkaWynik.setText("MPa");
                    cisnienieWynik = "MPa";
                }
                else{
                    jednostkaCisnienie.setText("MPa");
                }
                Toast.makeText(this, "Megapaskal", Toast.LENGTH_SHORT).show();
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
        adapter = new CustomExpandableListAdapter(CisnienieAtm.this, lstTitle, lstChild);
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
                    Intent i = new Intent(CisnienieAtm.this, FizykaKalkulator.class);
                    i.putExtra("miejsce", "Trojkaty");
                    startActivity(i);
                    Animatoo.animateFade(CisnienieAtm.this);
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
            Intent i = new Intent(CisnienieAtm.this, FizykaKalkulator.class);
            startActivity(i);
            Animatoo.animateFade(CisnienieAtm.this);
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