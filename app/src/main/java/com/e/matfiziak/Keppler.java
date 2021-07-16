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

public class Keppler extends AppCompatActivity {
    TextView jednostkaPredkosc;
    TextView jednostkaDlugosc;
    TextView jednostkaMasa;
    TextView jednostkaPraca;
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
        setContentView(R.layout.activity_keppler);
        ScrollView relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        String checkbox="";
        String imageUr = null, nick=null;
        ArrayList<String> incomingList = new ArrayList<String>();
        try {
            Intent incomingIntent = getIntent();
            incomingList = incomingIntent.getStringArrayListExtra("lista");
            checkbox = incomingIntent.getStringExtra("checkbox");
            nick = incomingIntent.getStringExtra("nick");
            imageUr = incomingIntent.getStringExtra("imageUrl");
        }
        catch (Exception ex){
            //incomingList.clear();
        }
        if(checkbox==null){
            checkbox="";
        }
        doWyslania = new ArrayList<String>();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final EditText czestotliwosc = findViewById(R.id.czestotliwosc);
        final EditText predkosc = findViewById(R.id.predkosc);
        final EditText dlugoscFali = findViewById(R.id.dlugoscFali);
        final EditText masa = findViewById(R.id.masa);
        final EditText praca = findViewById(R.id.praca);
        final EditText energiaKin = findViewById(R.id.energiaKin);
        final EditText energiaFot = findViewById(R.id.energiaFot);
        final CheckBox checkboxCzestotliwosc = findViewById(R.id.checkboxCzestotliwosc);
        final CheckBox checkboxPredkosc = findViewById(R.id.checkboxPredkosc);
        final CheckBox checkboxDlugosc = findViewById(R.id.checkboxDlugosc);
        final CheckBox checkboxMasa = findViewById(R.id.checkboxMasa);
        final CheckBox checkboxPraca = findViewById(R.id.checkboxPraca);
        final CheckBox checkboxKin = findViewById(R.id.checkboxKinetyczna);
        final CheckBox checkboxFot = findViewById(R.id.checkboxFoton);
        if(checkbox.equals("dlugosc")){
            checkboxDlugosc.setChecked(true);
        }
        else if(checkbox.equals("praca")){
            checkboxPraca.setChecked(true);
        }
        else if(checkbox.equals("predkosc")){
            checkboxPredkosc.setChecked(true);
        }
        else if(checkbox.equals("czestotliwosc")){
            checkboxCzestotliwosc.setChecked(true);
        }
        else if(checkbox.equals("kin")){
            checkboxKin.setChecked(true);
        }
        else if(checkbox.equals("foton")){
            checkboxFot.setChecked(true);
        }
        else if(checkbox.equals("masa")){
            checkboxMasa.setChecked(true);
        }
        final TextView wynikEnergia = findViewById(R.id.wynikEnergia);
        final EditText dziesiatkaDlugosc = findViewById(R.id.dziesiatkaDlugosc);
        final EditText dziesiatkaCzestotliwosc = findViewById(R.id.dziesiatkaCzestotliwosc);
        final EditText dziesiatkaPredkosc = findViewById(R.id.dziesiatkaPredkosc);
        final EditText dziesiatkaMasa = findViewById(R.id.dziesiatkaMasa);
        final EditText dziesiatkaPraca = findViewById(R.id.dziesiatkaPraca);
        final EditText dziesiatkaKin = findViewById(R.id.dziesiatkaKinet);
        final EditText dziesiatkaFoton = findViewById(R.id.dziesiatkaFoton);
        final TextView wynikDziesiatki = findViewById(R.id.dziesiatkaWynik);
        final TextView dziesiatkaOst = findViewById(R.id.dziesiatkaWynik22);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKeppler);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaKeppler);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKeppler);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaKeppler);
        final TextView dziesiatkaTekst = findViewById(R.id.dziesiatkaWynik22);
        final EditText eV = findViewById(R.id.elektronowolty);
        final EditText J = findViewById(R.id.dzule);
        final EditText dziesiatka = findViewById(R.id.dziesiatkaPrzelicznik);
        jednostkaWynik = findViewById(R.id.jednostkaWynik2);
        if(incomingList!=null){
            czestotliwosc.setText(incomingList.get(0));
            predkosc.setText(incomingList.get(1));
            dlugoscFali.setText(incomingList.get(2));
            masa.setText(incomingList.get(3));
            praca.setText(incomingList.get(4));
            energiaKin.setText(incomingList.get(5));
            energiaFot.setText(incomingList.get(6));
            dziesiatkaCzestotliwosc.setText(incomingList.get(7));
            dziesiatkaPredkosc.setText(incomingList.get(8));
            dziesiatkaDlugosc.setText(incomingList.get(9));
            dziesiatkaMasa.setText(incomingList.get(10));
            dziesiatkaPraca.setText(incomingList.get(11));
            dziesiatkaKin.setText(incomingList.get(12));
            dziesiatkaFoton.setText(incomingList.get(13));
            pierwszaLinia.setText(incomingList.get(14));
            drugaLinia.setText(incomingList.get(15));
            trzeciaLinia.setText(incomingList.get(16));
            czwartaLinia.setText(incomingList.get(17));
            wynikEnergia.setText(incomingList.get(18));
            wynikDziesiatki.setText(incomingList.get(19));
            dziesiatkaTekst.setText(incomingList.get(20));
            jednostkaWynik.setText(incomingList.get(21));
            eV.setText(incomingList.get(22));
            J.setText(incomingList.get(23));
            dziesiatka.setText(incomingList.get(24));
        }
        final String finalNick = nick;
        final String finalImageUr = imageUr;
        final String finalImageUr1 = imageUr;
        final String finalNick1 = nick;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                doWyslania.add(czestotliwosc.getText().toString());
                doWyslania.add(predkosc.getText().toString());
                doWyslania.add(dlugoscFali.getText().toString());
                doWyslania.add(masa.getText().toString());
                doWyslania.add(praca.getText().toString());
                doWyslania.add(energiaKin.getText().toString());
                doWyslania.add(energiaFot.getText().toString());
                doWyslania.add(dziesiatkaCzestotliwosc.getText().toString());
                doWyslania.add(dziesiatkaPredkosc.getText().toString());
                doWyslania.add(dziesiatkaDlugosc.getText().toString());
                doWyslania.add(dziesiatkaMasa.getText().toString());
                doWyslania.add(dziesiatkaPraca.getText().toString());
                doWyslania.add(dziesiatkaKin.getText().toString());
                doWyslania.add(dziesiatkaFoton.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(wynikEnergia.getText().toString());
                doWyslania.add(wynikDziesiatki.getText().toString());
                doWyslania.add(dziesiatkaTekst.getText().toString());
                doWyslania.add(jednostkaWynik.getText().toString());
                doWyslania.add(eV.getText().toString());
                doWyslania.add(J.getText().toString());
                doWyslania.add(dziesiatka.getText().toString());
                String ktoryCheckbox="";
                if(checkboxKin.isChecked()){
                    ktoryCheckbox="kin";
                }
                else if(checkboxPraca.isChecked()){
                    ktoryCheckbox="praca";
                }
                else if(checkboxMasa.isChecked()){
                    ktoryCheckbox="masa";
                }
                else if(checkboxCzestotliwosc.isChecked()){
                    ktoryCheckbox="czestotliwosc";
                }
                else if(checkboxDlugosc.isChecked()){
                    ktoryCheckbox="dlugosc";
                }
                else if(checkboxPredkosc.isChecked()){
                    ktoryCheckbox = "predkosc";
                }
                else if(checkboxFot.isChecked()){
                    ktoryCheckbox="foton";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(Keppler.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Keppler");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        i.putExtra("nick", finalNick);
                        i.putExtra("imageUrl", finalImageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Keppler.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", finalNick1);
                        i1.putExtra("imageUrl", finalImageUr1);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Keppler.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Keppler");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Keppler.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Keppler");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Keppler.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Keppler");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        Button buttonEnergia = findViewById(R.id.buttonEnergia);
        buttonEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dziesiatkaCzestotliwoscInt;
                int dziesiatkaDlugoscInt;
                int dziesiatkaFotonInt;
                int dziesiatkaKinInt;
                int dziesiatkaMasaInt;
                int dziesiatkaPracaInt;
                int dziesiatkaPredkoscInt;
                dziesiatkaOst.setText("*10");

                try {
                    String dziesiatkaCzestotliwosc1 = dziesiatkaCzestotliwosc.getText().toString();
                    String dziesiatkaDlugosc1 = dziesiatkaDlugosc.getText().toString();
                    String dziesiatkaFoton1 = dziesiatkaFoton.getText().toString();
                    String dziesiatkaKin1 = dziesiatkaKin.getText().toString();
                    String dziesiatkaMasa1 = dziesiatkaMasa.getText().toString();
                    String dziesiatkaPraca1 = dziesiatkaPraca.getText().toString();
                    String dziesiatkaPredkosc1 = dziesiatkaPredkosc.getText().toString();
                    if(dziesiatkaCzestotliwosc1.equals("")){
                        dziesiatkaCzestotliwoscInt = 0;
                    }
                    else {
                        dziesiatkaCzestotliwoscInt = (int) Double.parseDouble(dziesiatkaCzestotliwosc1);
                    }
                    if(dziesiatkaDlugosc1.equals("")){
                        dziesiatkaDlugoscInt=0;
                    }
                    else {
                        dziesiatkaDlugoscInt = (int) Double.parseDouble(dziesiatkaDlugosc1);
                    }
                    if(dziesiatkaFoton1.equals("")){
                        dziesiatkaFotonInt = 0;
                    }
                    else{
                        dziesiatkaFotonInt = (int) Double.parseDouble(dziesiatkaFoton1);
                    }
                    if(dziesiatkaKin1.equals("")){
                        dziesiatkaKinInt = 0;
                    }
                    else{
                        dziesiatkaKinInt  = (int) Double.parseDouble(dziesiatkaKin1);
                    }
                    if(dziesiatkaMasa1.equals("")){
                        dziesiatkaMasaInt = 0;
                    }
                    else{
                        dziesiatkaMasaInt = (int) Double.parseDouble(dziesiatkaMasa1);
                    }
                    if(dziesiatkaPraca1.equals("")){
                        dziesiatkaPracaInt=0;
                    }
                    else{
                        dziesiatkaPracaInt = (int) Double.parseDouble(dziesiatkaPraca1);
                    }
                    if(dziesiatkaPredkosc1.equals("")){
                        dziesiatkaPredkoscInt = 0;
                    }
                    else{
                        dziesiatkaPredkoscInt = (int) Double.parseDouble(dziesiatkaPredkosc1);
                    }
                    if(checkboxCzestotliwosc.isChecked()){
                        Double czes=null;
                        int dziesiatki = 0;
                        if(!energiaFot.getText().toString().equals("")){
                            Double a = Double.parseDouble(energiaFot.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("&#956;=F<sub><small><small>fot</small></small></sub>/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                            drugaLinia.setText(Html.fromHtml("&#956;="+a+"*10<sup><small><small>"+dziesiatkaFotonInt+"</small></small></sup>/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            czes = a/6.63;
                            dziesiatki = (int) (dziesiatkaFotonInt+34);
                            String x = funkcjePrzelicznikowe.intowanie(czes);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if(!dlugoscFali.getText().toString().equals("")){
                            Double a = Double.parseDouble(dlugoscFali.getText().toString());
                            if(jednostkaDlugosc.getText().toString().equals("cm")){
                                dziesiatkaDlugoscInt-=2;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("dm")){
                                dziesiatkaDlugoscInt-=1;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("km")){
                                dziesiatkaDlugoscInt+=3;
                            }
                            pierwszaLinia.setText(Html.fromHtml("&#956;=3*10<sup><small><small>8</small></small></sup>/&#955;"));
                            drugaLinia.setText(Html.fromHtml("&#956;=3*10<sup><small><small>8</small></small></sup>/("+a+"*10<sup><small><small>"+dziesiatkaDlugoscInt+"</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            czes = 3 / a;
                            dziesiatki = (int) (8-dziesiatkaDlugoscInt);
                            String x = funkcjePrzelicznikowe.intowanie(czes);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if(!masa.getText().toString().equals("")){
                            if(!predkosc.getText().toString().equals("")){
                                Double a= Double.parseDouble(masa.getText().toString());
                                if(jednostkaMasa.getText().toString().equals("g")){
                                    dziesiatkaMasaInt-=3;
                                }
                                else if(jednostkaMasa.getText().toString().equals("dag")){
                                    dziesiatkaMasaInt-=2;
                                }
                                else if(jednostkaMasa.getText().toString().equals("t")){
                                    dziesiatkaMasaInt+=3;
                                }
                                Double b = Double.parseDouble(predkosc.getText().toString());
                                b = funkcjePrzelicznikowe.predkosc(b, jednostkaPredkosc.getText().toString());
                                Double c=0.0,d;
                                if(!praca.getText().toString().equals("")){
                                    c = Double.parseDouble(praca.getText().toString());
                                    if(jednostkaPraca.getText().toString().equals("eV")){
                                        c=c*1.6;
                                        dziesiatkaPracaInt-=19;
                                    }
                                    String x3 = funkcjePrzelicznikowe.intowanie(c);
                                    c=Double.parseDouble(x3);
                                    if(dziesiatkaPracaInt==dziesiatkaMasaInt+dziesiatkaPredkoscInt+dziesiatkaPredkoscInt){
                                        int f5 = dziesiatkaPredkoscInt*2;
                                        pierwszaLinia.setText(Html.fromHtml("&#956;=m*v<sup><small><small>2</small></small></sup>/2+W"));
                                        drugaLinia.setText(Html.fromHtml("&#956;="+a+"*10<sup><small><small>"+dziesiatkaMasaInt+"*"+b+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+f5+"</small></small></sup>/2+"+c+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>"));
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        d = a*b*b/2+c;
                                        czes = d/6.63;
                                        dziesiatki = (int) (dziesiatkaMasaInt + dziesiatkaPredkoscInt+dziesiatkaPredkoscInt+34);
                                    }
                                    else{
                                        Double f=a*b*b/2;
                                        int e = (int) (dziesiatkaPracaInt-(dziesiatkaMasaInt+dziesiatkaPredkoscInt+dziesiatkaPredkoscInt));
                                        int g = (int) (dziesiatkaMasaInt+dziesiatkaPredkoscInt+dziesiatkaPredkoscInt);
                                        if(e>0){
                                            for(int i=0; i<e;i++){
                                                c=c*10;
                                                dziesiatkaPracaInt--;
                                            }
                                        }
                                        else{
                                            for(int i=0;i>e;i--){
                                                f=f*10;
                                                g--;
                                            }
                                        }
                                        int f5 = 2*dziesiatkaPredkoscInt;
                                        pierwszaLinia.setText(Html.fromHtml("&#956;=((m*v<sup><small><small>2</small></small></sup>/2)+W)/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                        drugaLinia.setText(Html.fromHtml("&#956=(("+a+"*10<sup><small><small>"+dziesiatkaMasaInt+"</small></small></sup>*"+b+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+f5+"</small></small></sup>/2) +"+c+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>)/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                        trzeciaLinia.setText("");
                                        czwartaLinia.setText("");
                                        czes=(f+c)/6.63;
                                        dziesiatki = g+34;
                                    }
                                }
                                else{
                                    int f5 = dziesiatkaPredkoscInt*2;
                                    pierwszaLinia.setText(Html.fromHtml("&#956;=m*v<sup><small><small>2</small></small></sup>/(2*6.63*10<sup><small><small>-34</small></small></sup>)"));
                                    drugaLinia.setText(Html.fromHtml("&#956;="+a+"*10<sup><small><small>"+dziesiatkaMasaInt+"*"+b+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+f5+"</small></small></sup>/(2*6.63*10<sup><small><small>-34</small></small></sup>)"));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    d= a*b*b/2;
                                    czes = d/6.63;
                                    dziesiatki = (int) (dziesiatkaMasaInt + dziesiatkaPredkoscInt+dziesiatkaPredkoscInt+34);
                                }
                                String x = funkcjePrzelicznikowe.intowanie(czes);
                                wynikEnergia.setText(x);
                                wynikDziesiatki.setText(""+dziesiatki);
                            }
                        }
                        else if(!energiaKin.getText().toString().equals("")){
                            if(!praca.getText().toString().equals("")){
                                Double a = Double.parseDouble(energiaKin.getText().toString());
                                Double b = Double.parseDouble(praca.getText().toString());
                                if(jednostkaPraca.getText().toString().equals("eV")){
                                    b=b*1.6;
                                    dziesiatkaPracaInt-=16;
                                }
                                String x3 = funkcjePrzelicznikowe.intowanie(b);
                                Double c = 0.0;
                                int d=0;
                                if(dziesiatkaPracaInt==dziesiatkaKinInt){
                                    pierwszaLinia.setText(Html.fromHtml("&#956;=(W+E<sub><small><small>kin</small></small></sub>)/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                    drugaLinia.setText(Html.fromHtml("&#956;=("+b+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>+"+a+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>)/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    c = a+b;
                                    c=c/6.63;
                                    dziesiatki = (int) (dziesiatkaPracaInt+34);
                                }
                                else{
                                    d=dziesiatkaPracaInt-dziesiatkaKinInt;
                                    if(d<0){
                                        d=d*-1;
                                    }
                                    if(dziesiatkaPracaInt<dziesiatkaKinInt){
                                        for (int i=0;i<d;i++){
                                            a=a*10;
                                            dziesiatki = dziesiatkaPracaInt+34;
                                        }
                                    }
                                    else{
                                        for(int i=0;i<d;i++){
                                            b=b*10;
                                            dziesiatki = dziesiatkaKinInt+34;
                                        }
                                    }
                                    pierwszaLinia.setText(Html.fromHtml("&#956;=(W+E<sub><small><small>kin</small></small></sub>)/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                    drugaLinia.setText(Html.fromHtml("&#956;=("+b+"*10<sup><small><small>"+dziesiatki+"</small></small></sup>+"+a+"*10<sup><small><small>"+dziesiatki+"</small></small></sup>)/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                                    trzeciaLinia.setText("");
                                    czwartaLinia.setText("");
                                    c=a+b;
                                    c=c/6.63;
                                }
                                String x= funkcjePrzelicznikowe.intowanie(c);
                                wynikDziesiatki.setText(""+dziesiatki);
                                wynikEnergia.setText(x);
                            }
                        }
                        else if(!praca.getText().toString().equals("")){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                a=a*1.6;
                                dziesiatkaPracaInt-=19;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(a);
                            a=Double.parseDouble(x3);
                            pierwszaLinia.setText(Html.fromHtml("&#956;=W/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                            drugaLinia.setText(Html.fromHtml("&#956;="+a+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>/(6.63*10<sup><small><small>-34</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            czes = a/6.63;
                            dziesiatki = (int)(dziesiatkaPracaInt+34);
                            String x = funkcjePrzelicznikowe.intowanie(czes);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else {
                            wyczyscLinie();
                        }
                    }
                    else if(checkboxDlugosc.isChecked()){
                        int dziesiatki=0;
                        Double dlugosc=null;
                        if(!energiaFot.getText().toString().equals("")){
                             Double a = Double.parseDouble(energiaFot.getText().toString());
                             dziesiatki= (int) (-34+8-dziesiatkaFotonInt);
                             pierwszaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-31</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/(E<sub><small><small>fot</small></small></sub>)"));
                             drugaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-31</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/("+a+"*10<sup><small><small>"+dziesiatkaFotonInt+"</small></small></sup>)"));
                             trzeciaLinia.setText("");
                             czwartaLinia.setText("");
                             dlugosc = 6.63*3/a;
                             String x2 = funkcjePrzelicznikowe.intowanie(dlugosc);
                             wynikEnergia.setText(x2);
                             wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if(!czestotliwosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(czestotliwosc.getText().toString());
                            dziesiatki = (int) (8-dziesiatkaCzestotliwoscInt);
                            pierwszaLinia.setText(Html.fromHtml("&#955;=3*10<sup><small><small>8</small></small></sup>/&#956;"));
                            drugaLinia.setText(Html.fromHtml("&#955;=3*10<sup><small><small>8</small></small></sup>/("+a+"*10<sup><small><small>"+dziesiatkaCzestotliwoscInt+"</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            dlugosc = 3/a;
                            String x2 = funkcjePrzelicznikowe.intowanie(dlugosc);
                            wynikEnergia.setText(x2);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if(!energiaKin.getText().toString().equals("")){
                            if(!praca.getText().toString().equals("")){
                                Double a = Double.parseDouble(energiaKin.getText().toString());
                                Double b = Double.parseDouble(praca.getText().toString());
                                if(jednostkaPraca.getText().toString().equals("eV")){
                                    b=b*1.6;
                                    dziesiatkaPracaInt-=19;
                                }
                                String x3 = funkcjePrzelicznikowe.intowanie(b);
                                b=Double.parseDouble(x3);
                                int c =0;
                                if(dziesiatkaPracaInt!=dziesiatkaKinInt){
                                    if(dziesiatkaPracaInt>dziesiatkaKinInt){
                                        for(int i=0;i<dziesiatkaPracaInt-dziesiatkaKinInt;i++){
                                            b=b*10;
                                            c = dziesiatkaKinInt;
                                        }
                                    }
                                    else if(dziesiatkaKinInt>dziesiatkaPracaInt){
                                        for(int i=0;i<dziesiatkaKinInt-dziesiatkaPracaInt;i++){
                                            a=a*10;
                                            c = dziesiatkaPracaInt;
                                        }
                                    }
                                }
                                else{
                                    c = dziesiatkaPracaInt;
                                }
                                pierwszaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/ (W*E<sub><small><small>kin</small></small></sub>)"));
                                drugaLinia.setText(Html.fromHtml("&#955=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/ ("+b+"*10<sup><small><small>"+c+"</small></small></sup>*"+a+"*10<sup><small><small>"+c+"</small></small></sup>)"));
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                Double d  =a+b;
                                dlugosc = 6.63 * 3;
                                dlugosc = dlugosc / d;
                                dziesiatki = -26 - c;
                                String x = funkcjePrzelicznikowe.intowanie(dlugosc);
                                wynikDziesiatki.setText(""+dziesiatki);
                                wynikEnergia.setText(x);
                            }
                        }
                        else if(!masa.getText().toString().equals("")){
                            if(!predkosc.getText().toString().equals("")){
                                if(!praca.getText().toString().equals("")){
                                    Double a = Double.parseDouble(predkosc.getText().toString());
                                    a = funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                                    Double b = Double.parseDouble(praca.getText().toString());
                                    if(jednostkaPraca.getText().toString().equals("eV")){
                                        b=b*1.6;
                                        dziesiatkaPracaInt-=19;
                                    }
                                    String x3 = funkcjePrzelicznikowe.intowanie(b);
                                    b=Double.parseDouble(x3);
                                    Double d = Double.parseDouble(masa.getText().toString());
                                    if(jednostkaMasa.getText().toString().equals("t")){
                                        dziesiatkaMasaInt+=3;
                                    }
                                    else if(jednostkaMasa.getText().toString().equals("dag")){
                                        dziesiatkaMasaInt-=2;
                                    }
                                    else if(jednostkaMasa.getText().toString().equals("g")){
                                        dziesiatkaMasaInt-=3;
                                    }
                                    pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                                    drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>="+d+"*10<sup><small><small>"+dziesiatkaMasaInt+"</small></small></sup>*"+a+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+dziesiatkaPredkoscInt*2+"</small></small></sup>/2"));
                                    a=a*d*a/2;
                                    int c =0;
                                    if(dziesiatkaPracaInt!=dziesiatkaPredkoscInt+dziesiatkaMasaInt+dziesiatkaPredkoscInt){
                                        if(dziesiatkaPracaInt>dziesiatkaMasaInt+dziesiatkaPredkoscInt+dziesiatkaPredkoscInt){
                                            for(int i=0;i<dziesiatkaPracaInt-(dziesiatkaPredkoscInt+dziesiatkaPredkoscInt+dziesiatkaMasaInt);i++){
                                                b=b*10;
                                                c = (int) (dziesiatkaPredkoscInt+dziesiatkaMasaInt+dziesiatkaPredkoscInt);
                                            }
                                        }
                                        else if(dziesiatkaPredkoscInt+dziesiatkaPredkoscInt+dziesiatkaMasaInt>dziesiatkaPracaInt){
                                            for(int i=0;i<dziesiatkaPredkoscInt+dziesiatkaPredkoscInt+dziesiatkaMasaInt-dziesiatkaPracaInt;i++){
                                                a=a*10;
                                                c = dziesiatkaPracaInt;
                                            }
                                        }
                                    }
                                    else{
                                        c = dziesiatkaPracaInt;
                                    }
                                    trzeciaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/(W+E<sub><small><small>kin</small></small></sub>)"));
                                    czwartaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/("+b+"*10<sup><small><small>"+c+"</small></small></sup>+"+a+"*10<sup><small><small>"+c+"</small></small></sup>)"));
                                    Double f  =a+b;
                                    dlugosc = 6.63 * 3;
                                    dlugosc = dlugosc / f;
                                    dziesiatki = -26 - c;
                                    String x = funkcjePrzelicznikowe.intowanie(dlugosc);
                                    wynikDziesiatki.setText(""+dziesiatki);
                                    wynikEnergia.setText(x);
                                }
                            }
                        }
                        else if((!predkosc.getText().toString().equals(""))&&(!praca.getText().toString().equals(""))){
                            Double a = Double.parseDouble(predkosc.getText().toString());
                            a=funkcjePrzelicznikowe.predkosc(a, jednostkaPredkosc.getText().toString());
                            Double b = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                dziesiatkaPracaInt-=19;
                                b=b*1.6;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(b);
                            b=Double.parseDouble(x3);
                            Double c = 9.11;
                            Double d=0.0;
                            int e=0;
                            int f=0;
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=m*10<sup><small><small>-31</small></small></sup>*v<sup><small><small>2</small></small></sup>*10<sup><small><small>"+dziesiatkaPredkoscInt*2+"</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=9.11*10<sup><small><small>-31</small></small></sup>*"+a+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+dziesiatkaPredkoscInt*2+"</small></small></sup>/2"));
                            d=9.1*a*a/2;
                            int y = (int) (-31+dziesiatkaPredkoscInt+dziesiatkaPredkoscInt);
                            if(dziesiatkaPracaInt!=y){
                                if(dziesiatkaPracaInt>y){
                                    for(int i=0;i<dziesiatkaPracaInt-y;i++) {
                                        d = d / 10;
                                    }
                                    f=dziesiatkaPracaInt;
                                }
                                else if(dziesiatkaPredkoscInt+dziesiatkaPredkoscInt-31>dziesiatkaPracaInt){
                                    e=dziesiatkaPredkoscInt+dziesiatkaPredkoscInt-31-dziesiatkaPracaInt;
                                    if(e<0){
                                        e=e*-1;
                                    }
                                    for(int i=0;i<e;i++){
                                        b=b/10;
                                    }
                                    f = y;
                                }
                            }
                            else {
                                f = dziesiatkaPracaInt;
                            }
                            trzeciaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/(W+E<sub><small><small>kin</small></small></sub>)"));
                            czwartaLinia.setText(Html.fromHtml("&#955;=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/("+b+"*10<sup><small><small>"+f+"</small></small></sup>+"+d+"*10<sup><small><small>"+f+"</small></small></sup>)"));
                            d=d+b;
                            d=6.63*3/d;
                            dziesiatki = -26-f;
                            wynikDziesiatki.setText(""+dziesiatki);
                            String x = funkcjePrzelicznikowe.intowanie(d);
                            wynikEnergia.setText(x);
                        }
                        else if(!praca.getText().toString().equals("")){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                dziesiatkaPracaInt-=19;
                                a=a*1.6;
                            }
                            String x = funkcjePrzelicznikowe.intowanie(a);
                            a=Double.parseDouble(x);
                            dziesiatki = (-26-dziesiatkaPracaInt);
                            pierwszaLinia.setText(Html.fromHtml("&#955;<sub><small><small>gr</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/W"));
                            drugaLinia.setText(Html.fromHtml("&#955;<sub><small><small>gr</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/("+a+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            dlugosc=6.63*3/a;
                            String x2 = funkcjePrzelicznikowe.intowanie(dlugosc);
                            wynikEnergia.setText(x2);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else{
                            wyczyscLinie();
                        }
                        String x2 = wynikDziesiatki.getText().toString();
                        int h2 = Integer.parseInt(x2);
                        if(jednostkaWynik.getText().toString().equals("km")){
                               h2-=3;
                        }
                        else if(jednostkaWynik.getText().toString().equals("dm")){
                            h2+=1;
                        }
                        else if(jednostkaWynik.getText().toString().equals("cm")){
                            h2+=2;
                        }
                        wynikDziesiatki.setText(""+h2);
                    }
                    else if(checkboxPredkosc.isChecked()){
                        Double predkosc2 = null;
                        int x11=0;
                        int dziesiatki = 0;
                        if(!energiaKin.getText().toString().equals("")){
                            if(!masa.getText().toString().equals("")){
                                Double a = Double.parseDouble(energiaKin.getText().toString());
                                Double b = Double.parseDouble(masa.getText().toString());
                                if(jednostkaMasa.getText().toString().equals("t")){
                                    dziesiatkaMasaInt+=3;
                                }
                                else if(jednostkaMasa.getText().toString().equals("dag")){
                                    dziesiatkaMasaInt-=2;
                                }
                                else if(jednostkaMasa.getText().toString().equals("g")){
                                    dziesiatkaMasaInt-=3;
                                }
                                pierwszaLinia.setText(Html.fromHtml("v=&#8730;(2*E<sub><small><small>kin</small></small></sub>/m)"));
                                drugaLinia.setText(Html.fromHtml("v=&#8730;(2*"+a+"*10<sup><small><small>"+dziesiatkaKinInt+"</small></small></sup>/("+b+"*10<sup><small><small>"+dziesiatkaMasaInt+"</small></small></sup>))"));
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                predkosc2 = 2*a/b;
                                predkosc2 = sqrt(predkosc2);
                                String x = funkcjePrzelicznikowe.intowanie(predkosc2);
                                wynikEnergia.setText(x);
                                dziesiatki = (int) (dziesiatkaKinInt - dziesiatkaMasaInt);
                                dziesiatki = dziesiatki/2;
                                wynikDziesiatki.setText(""+dziesiatki);
                            }
                        }
                        else if((!dlugoscFali.getText().toString().equals(""))&&(!praca.getText().toString().equals(""))){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                a=a*1.6;
                                dziesiatkaPracaInt-=19;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(a);
                            a=Double.parseDouble(x3);
                            Double b = Double.parseDouble(dlugoscFali.getText().toString());
                            if(jednostkaDlugosc.getText().toString().equals("cm")){
                                dziesiatkaDlugoscInt-=2;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("dm")){
                                dziesiatkaDlugoscInt-=1;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("km")){
                                dziesiatkaDlugoscInt+=3;
                            }
                            Double kinetyczna;
                            Double foto;
                            if(b>=100){
                                b=b/100;
                                dziesiatkaDlugoscInt+=2;
                            }
                            int c;
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/&#955;"));
                            foto = 6.63*3/b;
                            int dziesiatkiFoto = (int) (-26-dziesiatkaDlugoscInt);
                            if(dziesiatkaPracaInt!=dziesiatkiFoto){
                                int d = dziesiatkaPracaInt-dziesiatkiFoto;
                                if(d<0){
                                    d=d*-1;
                                    for(int i=0;i<d;i++){
                                        foto=foto/10;
                                    }
                                    dziesiatki=dziesiatkaPracaInt;
                                }
                                else if(d>0){
                                    for(int i=0;i<d;i++){
                                        a=a/10;
                                    }
                                    dziesiatki=dziesiatkiFoto;
                                }
                            }
                            else{
                                dziesiatki = dziesiatkaPracaInt;
                            }
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=E<sub><small><small>fot</small></small></sub>-W"));
                            kinetyczna = foto-a;
                            String x2 = funkcjePrzelicznikowe.intowanie(kinetyczna);
                            kinetyczna=Double.parseDouble(x2);
                            trzeciaLinia.setText(Html.fromHtml("v=&#8730;(2*E<sub><small><small>kin</small></small></sub>/(9.11*10<sup><small><small>-31</small></small></sup>))"));
                            czwartaLinia.setText(Html.fromHtml("v=&#8730;(2*"+kinetyczna+"*10<sup><small><small>"+dziesiatki+"</small></small></sup>/(9.11*10<sup><small><small>-31</small></small></sup>))"));
                            predkosc2= kinetyczna*2;
                            predkosc2=predkosc2/9.11;
                            predkosc2=sqrt(predkosc2);
                            dziesiatki=dziesiatki+31;
                            if(dziesiatki%2==0){
                                dziesiatki = dziesiatki/2;
                            }
                            else{
                                dziesiatki=dziesiatki/2;
                                predkosc2=predkosc2*10;
                            }
                            String x = funkcjePrzelicznikowe.intowanie(predkosc2);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else{
                            wyczyscLinie();
                        }
                        String x = wynikDziesiatki.getText().toString();
                        int x2 = Integer.parseInt(x);
                        String y = wynikEnergia.getText().toString();
                        Double y2 = Double.parseDouble(y);
                        if(jednostkaWynik.getText().toString().equals("km/h")){
                            y2=y2*3.6;
                        }
                        else if(jednostkaWynik.getText().toString().equals("km/m")){
                            y2=y2*6/10;
                            x2-=1;
                        }
                        else if(jednostkaWynik.getText().toString().equals("km/s")){
                            x2-=3;
                        }
                        else if(jednostkaWynik.getText().toString().equals("m/min")){
                            y2=y2*6;
                            x2+=1;
                        }
                        String x3 = funkcjePrzelicznikowe.intowanie(y2);
                        if (x11==0){
                            wynikEnergia.setText(x3);
                            wynikDziesiatki.setText(""+x2);
                        }
                        else{
                            x11=0;
                        }
                    }
                    else if(checkboxPraca.isChecked()){
                        Double praca = null;
                        int dziesiatki = 0;
                        if(!energiaFot.getText().toString().equals("")&&(!energiaKin.getText().toString().equals(""))){
                            Double a = Double.parseDouble(energiaFot.getText().toString());
                            Double b = Double.parseDouble(energiaKin.getText().toString());
                            int c=0;
                            if(dziesiatkaFotonInt!=dziesiatkaKinInt){
                                if(dziesiatkaFotonInt>dziesiatkaKinInt){
                                    for(int i=0;i<dziesiatkaFotonInt-dziesiatkaKinInt;i++){
                                        b=b/10;
                                        c = dziesiatkaFotonInt;
                                    }
                                }
                                else if(dziesiatkaKinInt>dziesiatkaFotonInt){
                                    for(int i=0;i<dziesiatkaKinInt-dziesiatkaFotonInt;i++){
                                        a=a/10;
                                        c = dziesiatkaKinInt;
                                    }
                                }
                            }
                            else{
                                c = dziesiatkaFotonInt;
                            }
                            pierwszaLinia.setText(Html.fromHtml("W=E<sub><small><small>fot</small></small></sub>-E<sub><small><small>kin</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("W="+a+"*10<sup><small><small>"+dziesiatkaFotonInt+"</small></small></sup>-"+b+"*10<sup><small><small>"+dziesiatkaKinInt+"</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            praca  =a-b;
                            String x = funkcjePrzelicznikowe.intowanie(praca);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+c);
                        }
                       else if(!energiaKin.getText().toString().equals("")){
                            if(!czestotliwosc.getText().toString().equals("")){
                                Double a = Double.parseDouble(energiaKin.getText().toString());
                                Double b = Double.parseDouble(czestotliwosc.getText().toString());
                                int c=0;
                                if(dziesiatkaKinInt!=dziesiatkaCzestotliwoscInt-34){
                                    if(dziesiatkaKinInt>dziesiatkaCzestotliwoscInt-34){
                                        for(int i=0;i<dziesiatkaKinInt-dziesiatkaCzestotliwoscInt+34;i++){
                                            b=b/10;
                                            c = dziesiatkaKinInt;
                                        }
                                    }
                                    else if(dziesiatkaCzestotliwoscInt-34>dziesiatkaKinInt){
                                        for(int i=0;i<dziesiatkaCzestotliwoscInt-34-dziesiatkaKinInt;i++){
                                            a=a/10;
                                            c = dziesiatkaCzestotliwoscInt-34;
                                        }
                                    }
                                }
                                else{
                                    c = dziesiatkaKinInt;
                                }
                                pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>=&#956;*6.63*10<sup><small><small>-34</small></small></sup>"));
                                b=b*6.63;
                                drugaLinia.setText(Html.fromHtml("W=E<sub><small><small>fot</small></small></sub>-E<sub><small><small>kin</small></small></sub>"));
                                trzeciaLinia.setText(Html.fromHtml("W="+b+"*10<sup><small><small>"+c+"</small></small></sup>-"+a+"*10<sup><small><small>"+c+"</small></small></sup>"));
                                czwartaLinia.setText("");
                                b=b-a;
                                String x = funkcjePrzelicznikowe.intowanie(b);
                                wynikEnergia.setText(x);
                                wynikDziesiatki.setText(""+c);
                            }
                        }
                        else if(!czestotliwosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(czestotliwosc.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("W=6.63*10<sup><small><small>-34</small></small></sup>*&#956;"));
                            drugaLinia.setText(Html.fromHtml("W=6.63*10<sup><small><small>-34</small></small></sup>*"+a+"*10<sup><small><small>"+dziesiatkaCzestotliwoscInt+"</sup></small></small>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            praca = 6.63 * a;
                            dziesiatki = -34 + dziesiatkaCzestotliwoscInt;
                            String x = funkcjePrzelicznikowe.intowanie(praca);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if (!dlugoscFali.getText().toString().equals("")){
                            Double a = Double.parseDouble(dlugoscFali.getText().toString());
                            if(jednostkaDlugosc.getText().toString().equals("cm")){
                                dziesiatkaDlugoscInt-=2;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("dm")){
                                dziesiatkaDlugoscInt-=1;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("km")){
                                dziesiatkaDlugoscInt+=3;
                            }
                            pierwszaLinia.setText(Html.fromHtml("W=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/&#955<sub><small><small>gr</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("W=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/("+a+"*10<sup><small><small>"+dziesiatkaDlugoscInt+"</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            praca = 6.63*3/a;
                            dziesiatki = (int) (-26 - dziesiatkaDlugoscInt);
                            String x = funkcjePrzelicznikowe.intowanie(praca);
                            wynikDziesiatki.setText(""+dziesiatki);
                            wynikEnergia.setText(x);
                        }
                        String x = wynikEnergia.getText().toString();
                        Double x2 = Double.parseDouble(x);
                        String y = wynikDziesiatki.getText().toString();
                        int y2 = Integer.parseInt(y);
                        if(jednostkaWynik.getText().toString().equals("eV")){
                            y2+=19;
                            x2=x2/1.6;
                            wynikDziesiatki.setText(""+y2);
                            String x3 = funkcjePrzelicznikowe.intowanie(x2);
                            wynikEnergia.setText(x3);
                        }
                        wyczyscLinie();
                    }
                    else if(checkboxMasa.isChecked()){
                        Double masa = null;
                        int dziesiatki = 0;
                        if((!energiaKin.getText().toString().equals(""))&&(!predkosc.getText().toString().equals(""))) {
                            Double a = Double.parseDouble(energiaKin.getText().toString());
                            Double b = Double.parseDouble(predkosc.getText().toString());
                            if(jednostkaPredkosc.getText().toString().equals("m/min")){
                                b=b/60;
                            }
                            else if(jednostkaPredkosc.getText().toString().equals("km/s")){
                                dziesiatki+=3;
                            }
                            else if(jednostkaPredkosc.getText().toString().equals("km/m")){
                                dziesiatki+=1;
                                b=b*100/60;
                            }
                            else if(jednostkaPredkosc.getText().toString().equals("km/h")){
                                b=b/3.6;
                            }
                            int x4 = dziesiatkaPredkoscInt*2;
                            pierwszaLinia.setText(Html.fromHtml("m=2*E<sub><small><small>kin</small></small></sub>/v<sup><small><small>2</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("m=2*"+a+"*10<sup><small><small>"+dziesiatkaKinInt+"</small></small></sup>/("+b+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+x4+"</small></small></sup>)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            masa = 2 * a / b / b;
                            dziesiatki = (int) (dziesiatkaKinInt - dziesiatkaPredkoscInt - dziesiatkaPredkoscInt);
                            String x = funkcjePrzelicznikowe.intowanie(masa);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText("" + dziesiatki);
                        }
                        String x = wynikDziesiatki.getText().toString();
                        int x2 = Integer.parseInt(x);
                        if(jednostkaWynik.getText().toString().equals("t")){
                            x2-=3;
                        }
                        else if(jednostkaWynik.getText().toString().equals("dag")){
                            x2+=2;
                        }
                        else if(jednostkaWynik.getText().toString().equals("g")){
                            x2+=3;
                        }
                        wynikDziesiatki.setText(""+x2);
                    }
                    else if(checkboxKin.isChecked()){
                        Double kinetyczna = null;
                        int dziesiatki = 0;
                        if((!masa.getText().toString().equals(""))&&(!predkosc.getText().toString().equals(""))){
                            Double a = Double.parseDouble(masa.getText().toString());
                            if(jednostkaMasa.getText().toString().equals("t")){
                                dziesiatkaMasaInt+=3;
                            }
                            else if(jednostkaMasa.getText().toString().equals("dag")){
                                dziesiatkaMasaInt-=2;
                            }
                            else if(jednostkaMasa.getText().toString().equals("g")){
                                dziesiatkaMasaInt-=3;
                            }
                            Double b = Double.parseDouble(predkosc.getText().toString());
                            if(jednostkaPredkosc.getText().toString().equals("m/min")){
                                b=b/60;
                            }
                            else if(jednostkaPredkosc.getText().toString().equals("km/s")){
                                dziesiatki+=3;
                            }
                            else if(jednostkaPredkosc.getText().toString().equals("km/m")){
                                dziesiatki+=1;
                                b=b*100/60;
                            }
                            else if(jednostkaPredkosc.getText().toString().equals("km/h")){
                                b=b/3.6;
                            }
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=m*v<sup><small><small>2</small></small></sup>/2"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>="+a+"*10<sup><small><small>"+dziesiatkaMasaInt+"</small></small></sup>*"+b+"<sup><small><small>2</small></small></sup>*10<sup><small><small>"+dziesiatkaPredkoscInt*2+"</small></small></sup>/2"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kinetyczna = a*b*b/2;
                            dziesiatki = (int) (dziesiatkaMasaInt+dziesiatkaPredkoscInt+dziesiatkaPredkoscInt);
                            String x = funkcjePrzelicznikowe.intowanie(kinetyczna);
                            wynikDziesiatki.setText(""+dziesiatki);
                            wynikEnergia.setText(x);
                        }
                        else if((!praca.getText().toString().equals(""))&&(!energiaFot.getText().toString().equals(""))){
                            Double a = Double.parseDouble(energiaFot.getText().toString());
                            Double b = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                dziesiatkaPracaInt-=19;
                                b=b*1.6;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(b);
                            b=Double.parseDouble(x3);
                            int c=0;
                            if(dziesiatkaFotonInt!=dziesiatkaPracaInt){
                                if(dziesiatkaFotonInt>dziesiatkaPracaInt){
                                    for(int i=0;i<dziesiatkaFotonInt-dziesiatkaPracaInt;i++){
                                        b=b/10;
                                        c = dziesiatkaFotonInt;
                                    }
                                }
                                else if(dziesiatkaPracaInt>dziesiatkaFotonInt){
                                    for(int i=0;i<dziesiatkaPracaInt-dziesiatkaFotonInt;i++){
                                        a=a/10;
                                        c = dziesiatkaPracaInt;
                                    }
                                }
                            }
                            else{
                                c = dziesiatkaFotonInt;
                            }
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=E<sub><small><small>fot</small></small></sub>-W"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>="+a+"*10<sup><small><small>"+dziesiatkaFotonInt+"</small></small></sup>-"+b+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            kinetyczna =a-b;
                            String x = funkcjePrzelicznikowe.intowanie(kinetyczna);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+c);
                        }
                        else if((!praca.getText().toString().equals(""))&&(!czestotliwosc.getText().toString().equals(""))){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                a=a*1.6;
                                dziesiatkaPracaInt-=19;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(a);
                            a=Double.parseDouble(x3);
                            Double b = Double.parseDouble(czestotliwosc.getText().toString());
                            int c=0;
                            if(dziesiatkaPracaInt!=dziesiatkaCzestotliwoscInt-34){
                                if(dziesiatkaPracaInt>dziesiatkaCzestotliwoscInt-34){
                                    for(int i=0;i<dziesiatkaPracaInt-dziesiatkaCzestotliwoscInt+34;i++){
                                        b=b/10;
                                        c = dziesiatkaPracaInt;
                                    }
                                }
                                else if(dziesiatkaCzestotliwoscInt-34>dziesiatkaPracaInt){
                                    for(int i=0;i<dziesiatkaCzestotliwoscInt-34-dziesiatkaPracaInt;i++){
                                        a=a/10;
                                        c = dziesiatkaCzestotliwoscInt-34;
                                    }
                                }
                            }
                            else{
                                c = dziesiatkaPracaInt;
                            }
                            int x5 = -34+dziesiatkaCzestotliwoscInt;
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>=&#956;*6.63*10<sup><small><small>-34</small></small></sup>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=E<sub><small><small>fot</small></small></sub>-W"));
                            b=b*6.63;
                            trzeciaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>="+b+"*10<sup><small><small>"+x5+"</small></small></sup>-"+a+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>"));
                            czwartaLinia.setText("");
                            b=b-a;
                            String x = funkcjePrzelicznikowe.intowanie(b);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+c);
                        }
                        else if((!praca.getText().toString().equals("")&&(!dlugoscFali.getText().toString().equals("")))){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                a=a*1.6;
                                dziesiatkaPracaInt-=19;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(a);
                            a = Double.parseDouble(x3);
                            Double b = Double.parseDouble(dlugoscFali.getText().toString());
                            if(jednostkaDlugosc.getText().toString().equals("km")){
                                dziesiatkaDlugoscInt+=3;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("dm")){
                                dziesiatkaDlugoscInt-=1;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("cm")){
                                dziesiatkaDlugoscInt-=2;
                            }
                            Double foto;
                            int c;
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>=6.63*10<sup><small><small>-34</small></small></sup>*3*10<sup><small><small>8</small></small></sup>/&#955;"));
                            foto = 6.63*3/b;
                            int dziesiatkiFoto = (int) (-26-dziesiatkaDlugoscInt);
                            if(dziesiatkaPracaInt!=dziesiatkiFoto){
                                int d = dziesiatkaPracaInt-dziesiatkiFoto;
                                if(d<0){
                                    d=d*-1;
                                    for(int i=0;i<d;i++){
                                        foto=foto/10;
                                    }
                                    dziesiatki=dziesiatkaPracaInt;
                                }
                                else if(d>0){
                                    for(int i=0;i<d;i++){
                                        a=a/10;
                                    }
                                    dziesiatki=dziesiatkiFoto;
                                }
                            }
                            else{
                                dziesiatki = dziesiatkaPracaInt;
                            }
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>=E<sub><small><small>fot</small></small></sub>-W"));
                            trzeciaLinia.setText(Html.fromHtml("E<sub><small><small>kin</small></small></sub>="+foto+"*10<sup><small><small>"+dziesiatkiFoto+"</small></small></sup>-"+a+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>"));
                            czwartaLinia.setText("");
                            kinetyczna = foto-a;
                            String x = funkcjePrzelicznikowe.intowanie(kinetyczna);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else{
                            wyczyscLinie();
                        }
                    }
                    else if(checkboxFot.isChecked()){
                        Double fotoel = null;
                        int dziesiatki = 0;
                        if(!czestotliwosc.getText().toString().equals("")){
                            Double a = Double.parseDouble(czestotliwosc.getText().toString());
                            fotoel = a*6.63;
                            dziesiatki = -34 + dziesiatkaCzestotliwoscInt;
                            String x = funkcjePrzelicznikowe.intowanie(fotoel);
                            wynikDziesiatki.setText(""+dziesiatki);
                            wynikEnergia.setText(x);
                        }
                        else if(!dlugoscFali.getText().toString().equals("")){
                            Double a = Double.parseDouble(dlugoscFali.getText().toString());
                            if(jednostkaDlugosc.getText().toString().equals("km")){
                                dziesiatkaDlugoscInt+=3;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("dm")){
                                dziesiatkaDlugoscInt-=1;
                            }
                            else if(jednostkaDlugosc.getText().toString().equals("cm")){
                                dziesiatkaDlugoscInt-=2;
                            }
                            fotoel = 6.63*3/a;
                            dziesiatki = (int) (-26+dziesiatkaDlugoscInt);
                            String x = funkcjePrzelicznikowe.intowanie(fotoel);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if((!praca.getText().toString().equals(""))&&(!energiaKin.getText().toString().equals(""))){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                a=a*1.6;
                                dziesiatkaPracaInt-=19;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(a);
                            a= Double.parseDouble(x3);
                            Double b = Double.parseDouble(energiaKin.getText().toString());
                            if(dziesiatkaPracaInt!=dziesiatkaKinInt){
                                int d = dziesiatkaPracaInt - dziesiatkaKinInt;
                                if(d<0){
                                    d=d*-1;
                                    for(int i=0;i<d;i++){
                                        a=a/10;
                                    }
                                    dziesiatki = dziesiatkaKinInt;
                                }
                                else if(d>0){
                                    for(int i=0;i<d;i++){
                                        b=b/10;
                                    }
                                    dziesiatki = dziesiatkaPracaInt;
                                }
                            }
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>=W+E<sub><small><small>kin</small></small></sub>"));
                            drugaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>="+a+"*10<sup><small><small>"+dziesiatkaPracaInt+"</small></small></sup>+"+b+"*10<sup><small><small>"+dziesiatkaKinInt+"</small></small></sup>"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            fotoel = a+b;
                            String x = funkcjePrzelicznikowe.intowanie(fotoel);
                            wynikEnergia.setText(x);
                            wynikDziesiatki.setText(""+dziesiatki);
                        }
                        else if(!praca.getText().toString().equals("")){
                            Double a = Double.parseDouble(praca.getText().toString());
                            if(jednostkaPraca.getText().toString().equals("eV")){
                                dziesiatkaPracaInt-=19;
                                a=a*1.6;
                            }
                            String x3 = funkcjePrzelicznikowe.intowanie(a);
                            a=Double.parseDouble(x3);
                            pierwszaLinia.setText(Html.fromHtml("E<sub><small><small>fot</small></small></sub>=W"));
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            fotoel = a;
                            dziesiatki = dziesiatkaPracaInt;
                            wynikEnergia.setText(""+a);
                            wynikDziesiatki.setText(""+dziesiatki);
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
        Button buttonPrzelicznik = findViewById(R.id.buttonPrzelicznik);
        buttonPrzelicznik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double wynikPrzelicz = null;
                int dziesiatki = 0;
                if(!eV.getText().toString().equals("")){
                    Double a = Double.parseDouble(eV.getText().toString());
                    wynikPrzelicz = a*1.6;
                    dziesiatki = -19;
                    String x = funkcjePrzelicznikowe.intowanie(wynikPrzelicz);
                    J.setText(x);
                    dziesiatka.setText(""+dziesiatki);
                }
                else if(!J.getText().toString().equals("")){
                    Double a = Double.parseDouble(J.getText().toString());
                    int dz = Integer.parseInt(dziesiatka.getText().toString());
                    if(dz<-19){
                       int d = -19 - dz;
                       for (int i=0;i<d;i++){
                           a=a/10;
                       }
                    }
                    else if(dz>-19){
                        int d = dz+19;
                        for(int i=0;i<d;i++){
                            a=a*10;
                        }
                    }
                    wynikPrzelicz=a/1.6;
                    String x = funkcjePrzelicznikowe.intowanie(wynikPrzelicz);
                    eV.setText(x);
                }
            }
        });
        checkboxCzestotliwosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxDlugosc.setChecked(false);
                checkboxFot.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxPredkosc.setChecked(false);
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
                checkboxFot.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxPredkosc.setChecked(false);
                if(checkboxDlugosc.isChecked()){
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxFot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxDlugosc.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxPredkosc.setChecked(false);
                if(checkboxFot.isChecked()){
                    jednostkaWynik.setText("J");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxKin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxDlugosc.setChecked(false);
                checkboxFot.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxPredkosc.setChecked(false);
                if(checkboxKin.isChecked()){
                    jednostkaWynik.setText("J");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxDlugosc.setChecked(false);
                checkboxFot.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxPredkosc.setChecked(false);
                if(checkboxMasa.isChecked()){
                    jednostkaWynik.setText("kg");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxPraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxDlugosc.setChecked(false);
                checkboxFot.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                checkboxPredkosc.setChecked(false);
                if(checkboxPraca.isChecked()){
                    jednostkaWynik.setText("J");
                }
                else{
                    jednostkaWynik.setText("");
                }
                wyczyscLinie();
            }
        });
        checkboxPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxDlugosc.setChecked(false);
                checkboxFot.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxCzestotliwosc.setChecked(false);
                if(checkboxPredkosc.isChecked()){
                    jednostkaWynik.setText("m/s");
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
                checkboxCzestotliwosc.setChecked(false);
                checkboxDlugosc.setChecked(false);
                checkboxFot.setChecked(false);
                checkboxKin.setChecked(false);
                checkboxMasa.setChecked(false);
                checkboxPraca.setChecked(false);
                checkboxPredkosc.setChecked(false);
                dziesiatkaCzestotliwosc.setText("");
                dziesiatkaDlugosc.setText("");
                dziesiatkaFoton.setText("");
                dziesiatkaKin.setText("");
                dziesiatkaMasa.setText("");
                dziesiatkaOst.setText("");
                dziesiatkaPraca.setText("");
                dziesiatka.setText("");
                dziesiatkaPredkosc.setText("");
                wynikDziesiatki.setText("");
                czestotliwosc.setText("");
                predkosc.setText("");
                dlugoscFali.setText("");
                masa.setText("");
                praca.setText("");
                energiaKin.setText("");
                energiaFot.setText("");
                eV.setText("");
                J.setText("");
                dziesiatka.setText("");
                jednostkaWynik.setText("");
                wynikEnergia.setText("");
                pierwszaLinia.setText("");
                drugaLinia.setText("");
                trzeciaLinia.setText("");
                czwartaLinia.setText("");
            }
        });
        jednostkaPredkosc = findViewById(R.id.jednostkaPredkosc2);
        jednostkaDlugosc = findViewById(R.id.jednostkaDlugosc2);
        jednostkaMasa= findViewById(R.id.jednostkaMasa2);
        jednostkaPraca = findViewById(R.id.jednostkaPraca2);
        jednostkaWynik = findViewById(R.id.jednostkaWynik2);
        jednostkaPredkosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPredkosc);
                openContextMenu(v);
            }
        });
        jednostkaDlugosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaDlugosc);
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
        jednostkaPraca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(jednostkaPraca);
                openContextMenu(v);
            }
        });
        try {
            jednostkaWynik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(jednostkaWynik);
                    openContextMenu(v);

                }
            });
        }
        catch (Exception ex){
            Log.i("wynik", ex.getMessage().toString());
        }
        mdrawerLayout = findViewById(R.id.drawerKeppler_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Keppler.this, Konto.class);
                i.putExtra("miejsce", "Keppler");
                startActivity(i);
                Animatoo.animateFade(Keppler.this);
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
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKeppler);
        TextView drugaLinia = findViewById(R.id.drugaLiniaKeppler);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKeppler);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaKeppler);
        TextView wynik = findViewById(R.id.wynikEnergia);
        TextView wynikDziesiatka = findViewById(R.id.dziesiatkaWynik22);
        TextView wynikDziesiatka1 = findViewById(R.id.dziesiatkaWynik);
        pierwszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        wynik.setText("");
        wynikDziesiatka.setText("");
        wynikDziesiatka1.setText("");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKeppler);
            TextView drugaLinia = findViewById(R.id.drugaLiniaKeppler);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKeppler);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaKeppler);
            TextView wynik = findViewById(R.id.wynikEnergia);
            TextView dziesiatkaWynik = findViewById(R.id.dziesiatkaWynik);
            TextView dziesiatkaWynik2 = findViewById(R.id.dziesiatkaWynik22);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik2);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaKeppler);
            TextView drugaLinia = findViewById(R.id.drugaLiniaKeppler);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaKeppler);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaKeppler);
            TextView wynik = findViewById(R.id.wynikEnergia);
            TextView dziesiatkaWynik = findViewById(R.id.dziesiatkaWynik);
            TextView dziesiatkaWynik2 = findViewById(R.id.dziesiatkaWynik22);
            TextView jednostkaWynik = findViewById(R.id.jednostkaWynik2);
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
    String ktory = "";
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        CheckBox checkBoxPredkosc = findViewById(R.id.checkboxPredkosc);
        CheckBox checkBoxDlugosc = findViewById(R.id.checkboxDlugosc);
        CheckBox checkBoxMasa = findViewById(R.id.checkboxMasa);
        CheckBox checkBoxPraca = findViewById(R.id.checkboxPraca);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.jednostkaPredkosc2){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory="predkosc";
        }
        else if(v.getId()==R.id.jednostkaWynik2&&checkBoxPredkosc.isChecked()){
            getMenuInflater().inflate(R.menu.predkosc_menu, menu);
            ktory = "wynik";
        }
        else if(v.getId()==R.id.jednostkaDlugosc2){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory = "dlugosc";
        }
        else if(v.getId()==R.id.jednostkaWynik2&&checkBoxDlugosc.isChecked()){
            getMenuInflater().inflate(R.menu.dlugosc2_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaMasa2){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="masa";
        }
        else if(v.getId()==R.id.jednostkaWynik2&&checkBoxMasa.isChecked()){
            getMenuInflater().inflate(R.menu.example_menu, menu);
            ktory="wynik";
        }
        else if(v.getId()==R.id.jednostkaPraca2){
            getMenuInflater().inflate(R.menu.elektronowolty_menu, menu);
            ktory="praca";
        }
        else if(v.getId()==R.id.jednostkaWynik2&&checkBoxPraca.isChecked()){
            getMenuInflater().inflate(R.menu.elektronowolty_menu, menu);
            ktory="wynik";
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/min");
                }
                else{
                    jednostkaPredkosc.setText("m/min");
                }
                Toast.makeText(this, "Metry na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.MnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("m/s");
                }
                else{
                    jednostkaPredkosc.setText("m/s");
                }
                Toast.makeText(this, "Metry na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaH:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/h");
                }
                else{
                    jednostkaPredkosc.setText("km/h");
                }
                Toast.makeText(this, "Kilometry na godzinę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaM:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/m");
                }
                else{
                    jednostkaPredkosc.setText("km/m");
                }
                Toast.makeText(this, "Kilometry na minutę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.KMnaS:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("km/s");
                }
                else{
                    jednostkaPredkosc.setText("km/s");
                }
                Toast.makeText(this, "Kilometry na sekundę", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Centymetr:
                if (ktory.equals("wynik")) {
                    jednostkaWynik.setText("cm");
                }
                else{
                    jednostkaDlugosc.setText("cm");
                }
                Toast.makeText(this, "Centymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Decymetr:
                if (ktory.equals("wynik")) {
                    jednostkaWynik.setText("dm");
                }
                else{
                    jednostkaDlugosc.setText("dm");
                }
                Toast.makeText(this, "Decymetr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Metr:
                if (ktory.equals("wynik")) {
                    jednostkaWynik.setText("m");
                }
                else{
                    jednostkaDlugosc.setText("m");
                }
                Toast.makeText(this, "Metr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Kilometr:
                if (ktory.equals("wynik")) {
                    jednostkaWynik.setText("km");
                }
                else{
                    jednostkaDlugosc.setText("km");
                }
                Toast.makeText(this, "Kilometr", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option2:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("g");
                }
                else{
                    jednostkaMasa.setText("g");
                }
                Toast.makeText(this, "Gram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("dag");
                }
                else{
                    jednostkaMasa.setText("dag");
                }
                Toast.makeText(this, "Dekagram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option4:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("kg");
                }
                else{
                    jednostkaMasa.setText("kg");
                }
                Toast.makeText(this, "Kilogram", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option5:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("t");
                }
                else{
                    jednostkaMasa.setText("t");
                }
                Toast.makeText(this, "Tona", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.elektronowolty:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("eV");
                }
                else{
                    jednostkaPraca.setText("eV");
                }
                Toast.makeText(this, "Elektronowolty", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dzule:
                if(ktory.equals("wynik")){
                    jednostkaWynik.setText("J");
                }
                else{
                    jednostkaPraca.setText("J");
                }
                Toast.makeText(this, "Dżule", Toast.LENGTH_SHORT).show();
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
                String selectedItem = ((List)(lstChild.get(lstTitle.get(groupPosition))))
                        .get(childPosition).toString();
                // getSupportActionBar().setTitle(selectedItem);
                Log.i("wynik", selectedItem);
                if(items[0].equals(lstTitle.get(groupPosition)))
                    navigationManager.showFragment(selectedItem);
                else if(selectedItem.equals("Czworokąty")){
                    Intent i = new Intent(Keppler.this, Szkola.class);
                    i.putExtra("miejsce", "Keppler");
                    startActivity(i);
                    Animatoo.animateFade(Keppler.this);
                }
                else
                    throw new IllegalArgumentException("Not supported fragment");

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
            Intent i = new Intent(Keppler.this, Grawitacja.class);
            startActivity(i);
            Animatoo.animateFade(Keppler.this);
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

    private void initItems() {
        items = new String[]{"Android Programing", "Xamarin Programing", "iOS Programming"};
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