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
public class FunkcjaKwadratowa extends AppCompatActivity {
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
    TextView znakRownosci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funkcja_kwadratowa);
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
        znakRownosci = findViewById(R.id.znakRownosciFunkcjaKwadratowa);
        znakRownosci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(znakRownosci);
                openContextMenu(v);
            }
        });
        final EditText x2 = findViewById(R.id.x2FunkcjaKwadratowa);
        final EditText x = findViewById(R.id.xFunkcjaKwadratowa);
        final EditText c = findViewById(R.id.cFunkcjaKwadratowa);
        final EditText d = findViewById(R.id.ostatniElFunkcjaKwadratowa);
        final EditText delta = findViewById(R.id.deltaFunkcjaKwadratowa);
        final EditText suma = findViewById(R.id.sumaMiejscZerFunkcjaKwadratowa);
        final EditText sumaKwadratow = findViewById(R.id.sumaKwadratowMiejscZerFunkcjaKwadratowa);
        final EditText iloczyn = findViewById(R.id.iloczynFunkcjaKwadratowa);
        final EditText pierwszeMiejsce = findViewById(R.id.pierwszeMiejsceZerFunkcjaKwadratowa);
        final EditText drugieMiejsce = findViewById(R.id.drugieMiejsceZerFunkcjaKwadratowa);
        final CheckBox checkBoxA = findViewById(R.id.checkboxAFunkcjaKwadratowa);
        final CheckBox checkBoxB = findViewById(R.id.checkboxBFunkcjaKwadratowa);
        final CheckBox checkBoxC = findViewById(R.id.checkboxCFunkcjaKwadratowa);
        final CheckBox checkBoxDelta = findViewById(R.id.checkboxDeltaFunkcjaKwadratowa);
        final CheckBox checkBoxSuma = findViewById(R.id.checkboxSumaMiejZerFunkcjaKwadratowa);
        final CheckBox checkBoxIloczyn = findViewById(R.id.checkboxIloczynFunkcjaKwadratowa);
        final CheckBox checkBoxMiejscaZer = findViewById(R.id.checkboxMiejZerFunkcjaKwadratowa);
        final CheckBox checkBoxWspolrzedne = findViewById(R.id.checkboxWspolrzedne);
        final TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaFunkcjaKwadratowa);
        final TextView drugaLinia = findViewById(R.id.drugaLiniaFunkcjaKwadratowa);
        final TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaFunkcjaKwadratowa);
        final TextView czwartaLinia = findViewById(R.id.czwartaLiniaFunkcjaKwadratowa);
        final TextView piataLinia = findViewById(R.id.piataLiniaFunkcjaKwadratowa);
        final TextView szostaLinia = findViewById(R.id.szostaLiniaFunkcjaKwadratowa);
        final TextView wynik = findViewById(R.id.wynikFunkcjaKwadratowa);
        doWyslania = new ArrayList<String>();
        final Intent incomingIntent = getIntent();
        ArrayList<String> incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String checkbox = incomingIntent.getStringExtra("checkbox");
        if(incomingList!=null){
            x2.setText(incomingList.get(0));
            x.setText(incomingList.get(1));
            c.setText(incomingList.get(2));
            d.setText(incomingList.get(3));
            delta.setText(incomingList.get(4));
            pierwszeMiejsce.setText(incomingList.get(5));
            drugieMiejsce.setText(incomingList.get(6));
            suma.setText(incomingList.get(7));
            sumaKwadratow.setText(incomingList.get(8));
            iloczyn.setText(incomingList.get(9));
            pierwszaLinia.setText(incomingList.get(10));
            drugaLinia.setText(incomingList.get(11));
            trzeciaLinia.setText(incomingList.get(12));
            czwartaLinia.setText(incomingList.get(13));
            piataLinia.setText(incomingList.get(14));
            if(piataLinia.getText().toString().equals("")){
                piataLinia.setVisibility(View.GONE);
            }
            szostaLinia.setText(incomingList.get(15));
            if(szostaLinia.getText().toString().equals("")){
                szostaLinia.setVisibility(View.GONE);
            }
            wynik.setText(incomingList.get(16));
        }
        if(checkbox!=null){
            if(checkbox.equals("delta")){
                checkBoxDelta.setChecked(true);
            }
            else if(checkbox.equals("a")){
                checkBoxA.setChecked(true);
            }
            else if(checkbox.equals("b")){
                checkBoxB.setChecked(true);
            }
            else if(checkbox.equals("c")){
                checkBoxC.setChecked(true);
            }
            else if(checkbox.equals("suma")){
                checkBoxSuma.setChecked(true);
            }
            else if(checkbox.equals("iloczyn")){
                checkBoxIloczyn.setChecked(true);
            }
            else if(checkbox.equals("miejscaZer")){
                checkBoxMiejscaZer.setChecked(true);
            }
            else if(checkbox.equals("wspolrzedne")){
                checkBoxWspolrzedne.setChecked(true);
            }
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                doWyslania.add(x2.getText().toString());
                doWyslania.add(x.getText().toString());
                doWyslania.add(c.getText().toString());
                doWyslania.add(d.getText().toString());
                doWyslania.add(delta.getText().toString());
                doWyslania.add(pierwszeMiejsce.getText().toString());
                doWyslania.add(drugieMiejsce.getText().toString());
                doWyslania.add(suma.getText().toString());
                doWyslania.add(sumaKwadratow.getText().toString());
                doWyslania.add(iloczyn.getText().toString());
                doWyslania.add(pierwszaLinia.getText().toString());
                doWyslania.add(drugaLinia.getText().toString());
                doWyslania.add(trzeciaLinia.getText().toString());
                doWyslania.add(czwartaLinia.getText().toString());
                doWyslania.add(piataLinia.getText().toString());
                doWyslania.add(szostaLinia.getText().toString());
                doWyslania.add(wynik.getText().toString());
                String ktoryCheckbox=null;
                if(checkBoxA.isChecked()){
                    ktoryCheckbox="a";
                }
                else if(checkBoxB.isChecked()){
                    ktoryCheckbox="b";
                }
                else if(checkBoxC.isChecked()){
                    ktoryCheckbox="c";
                }
                else if(checkBoxDelta.isChecked()){
                    ktoryCheckbox="delta";
                }
                else if(checkBoxIloczyn.isChecked()){
                    ktoryCheckbox="iloczyn";
                }
                else if(checkBoxMiejscaZer.isChecked()){
                    ktoryCheckbox="miejscaZer";
                }
                else if(checkBoxSuma.isChecked()){
                    ktoryCheckbox="suma";
                }
                else if(checkBoxWspolrzedne.isChecked()){
                    ktoryCheckbox="wspolrzedne";
                }
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(FunkcjaKwadratowa.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "funkcjaKwadratowa");
                        i.putExtra("lista", doWyslania);
                        i.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(FunkcjaKwadratowa.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(FunkcjaKwadratowa.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "funkcjaKwadratowa");
                        i2.putExtra("lista", doWyslania);
                        i2.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(FunkcjaKwadratowa.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "funkcjaKwadratowa");
                        i3.putExtra("lista", doWyslania);
                        i3.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(FunkcjaKwadratowa.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "funkcjaKwadratowa");
                        i4.putExtra("lista", doWyslania);
                        i4.putExtra("checkbox",ktoryCheckbox);
                        startActivity(i4);
                        break;
                }
                return true;
            }
        });
        Button buttonWynik = findViewById(R.id.buttonObliczFunkcjaKwadratowa);
        Button buttonCzysc = findViewById(R.id.buttonCzyscFunkcjaKwadratowa);
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    piataLinia.setText("");
                    szostaLinia.setText("");
                    piataLinia.setVisibility(View.GONE);
                    szostaLinia.setVisibility(View.GONE);
                    if(checkBoxDelta.isChecked()){
                        Double delt = null;
                        if((!x2.getText().toString().equals(""))&&(!x.getText().toString().equals(""))&&(!c.getText().toString().equals(""))){
                            Double xx2 = Double.parseDouble(x2.getText().toString());
                            Double xx = Double.parseDouble(x.getText().toString());
                            Double cc = Double.parseDouble(c.getText().toString());
                            Double dd;
                            if(!d.getText().toString().equals("")) {
                                dd = Double.parseDouble(d.getText().toString());
                                cc-=dd;
                            }
                            pierwszaLinia.setText(Html.fromHtml("&#916;=b<sup><small><small>2</small></small></sup>-4ac"));
                            drugaLinia.setText("");
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            Double pom = (xx*xx)-(4*xx2*cc);
                            delt = pom;
                            String x2 = funkcjePrzelicznikowe.intowanie(delt);
                            wynik.setText(x2);
                        }
                        else if((!suma.getText().toString().equals(""))&&(!iloczyn.getText().toString().equals(""))){
                            Double sum = Double.parseDouble(suma.getText().toString());
                            Double iloczynn = Double.parseDouble(iloczyn.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>+x<sub><small><small>02</small></small></sub>=-b/a"));
                            drugaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>*x<sub><small><small>02</small></small></sub>=c/a"));
                            Double cc = iloczynn;
                            Double xx2 =1.0;
                            Double xx = -sum;
                            trzeciaLinia.setText(Html.fromHtml("a="+xx2+"\tb="+xx+"\tc="+cc));
                            czwartaLinia.setText(Html.fromHtml("&#916;=b<sup><small><small>2</small></small></sup>-4ac"));
                            Double pom = (xx*xx)-(4*xx2*cc);
                            delt = pom;
                            String x2 = funkcjePrzelicznikowe.intowanie(delt);
                            wynik.setText(x2);
                        }
                        else if((!sumaKwadratow.getText().toString().equals(""))&&(!iloczyn.getText().toString().equals(""))){
                            Double sumKw = Double.parseDouble(sumaKwadratow.getText().toString());
                            Double iloczynn = Double.parseDouble(iloczyn.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub><sup><small><small>2</small></small></sup>+x<sub><small><small>02</small></small></sub><sup><small><small>2</small></small></sup>-2x<sub><small><small>01</small></small></sub>x<sub><small><small>02</small></small></sub>=-b/a"));
                            drugaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>*x<sub><small><small>02</small></small></sub>=c/a"));
                            Double cc = iloczynn;
                            Double xx2 =1.0;
                            Double xx = (sumKw-(2*iloczynn))*-1;
                            trzeciaLinia.setText(Html.fromHtml("a="+xx2+"\tb="+xx+"\tc="+cc));
                            czwartaLinia.setText(Html.fromHtml("&#916;=b<sup><small><small>2</small></small></sup>-4ac"));
                            Double pom = (xx*xx)-(4*xx2*cc);
                            delt = pom;
                            String x2 = funkcjePrzelicznikowe.intowanie(delt);
                            wynik.setText(x2);
                        }
                    }
                    else if((checkBoxMiejscaZer.isChecked())){
                        Double miejsceZer1=null, miejsceZer2=null;
                        if((!x2.getText().toString().equals(""))&&(!x.getText().toString().equals(""))&&(c.getText().toString().equals(""))&&(!d.getText().toString().equals(""))){
                            Double xx2 = Double.parseDouble(x2.getText().toString());
                            Double xx = Double.parseDouble(x.getText().toString());
                            Double cc = Double.parseDouble(c.getText().toString());
                            Double dd;
                            if(!d.getText().toString().equals("")) {
                                dd = Double.parseDouble(d.getText().toString());
                                cc-=dd;
                            }
                            pierwszaLinia.setText(Html.fromHtml("&#916;=b<sup><small><small>2</small></small></sup>-4ac"));
                            Double pom = (xx*xx)-(4*xx2*cc);
                            Double delt = pom;
                            if(delt<0){
                                drugaLinia.setText(Html.fromHtml("&#916<0\t0 miejsc zer."));
                                trzeciaLinia.setText("");
                                czwartaLinia.setText("");
                                wynik.setText("Brak miejsc zer.");
                            }
                            else if(delt==0){
                                drugaLinia.setText(Html.fromHtml("&#916;=0\t1 miejsce zer."));
                                trzeciaLinia.setText(Html.fromHtml("x<sub><small><small>0</small></small></sub>=-b/2a"));
                                czwartaLinia.setText("");
                                miejsceZer1 = -xx/2/xx2;
                                String x2 = funkcjePrzelicznikowe.intowanie(miejsceZer1);
                                wynik.setText(x2);
                            }
                            else{
                                drugaLinia.setText(Html.fromHtml("&#916>0\t2 miejsca zer."));
                                trzeciaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>=-b-&#8730;&#916;/2a"));
                                czwartaLinia.setText(Html.fromHtml("x<sub><small><small>02</small></small></sub>=-b+&#8730;&#916;/2a"));
                                delt = Math.sqrt(delt);
                                miejsceZer1 = (-xx-delt)/2/xx2;
                                miejsceZer2 = (-xx+delt)/2/xx2;
                                String x2 = funkcjePrzelicznikowe.intowanie(miejsceZer1);
                                String x3 = funkcjePrzelicznikowe.intowanie(miejsceZer2);
                                wynik.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>="+x2+"\tx<sub><small><small>01</small></small></sub>"+x3));
                            }
                        }
                        else if((!suma.getText().toString().equals(""))&&(!iloczyn.getText().toString().equals(""))){
                            Double sum = Double.parseDouble(suma.getText().toString());
                            Double iloczynn = Double.parseDouble(iloczyn.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>+x<sub><small><small>02</small></small></sub>=-b/a"));
                            drugaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>*x<sub><small><small>02</small></small></sub>=c/a"));
                            Double cc = iloczynn;
                            Double xx2 =1.0;
                            Double xx = -sum;
                            trzeciaLinia.setText(Html.fromHtml("a="+xx2+"\tb="+xx+"\tc="+cc));
                            czwartaLinia.setText(Html.fromHtml("&#916;=b<sup><small><small>2</small></small></sup>-4ac"));
                            Double delt = (xx*xx)-(4*xx2*cc);
                            if(delt<0){
                                piataLinia.setText("");
                                szostaLinia.setText("");
                                wynik.setText("Brak miejsc zer.");
                            }
                            else if(delt==0){
                                piataLinia.setText(Html.fromHtml("x<sub><small><small>0</small></small></sub>=-b/2a"));
                                piataLinia.setVisibility(View.VISIBLE);
                                szostaLinia.setText("");
                                miejsceZer1 = -xx/2/xx2;
                                String x2 = funkcjePrzelicznikowe.intowanie(miejsceZer1);
                                wynik.setText(x2);
                            }
                            else{
                                piataLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>=-b-&#8730;&#916;/2a"));
                                piataLinia.setVisibility(View.VISIBLE);
                                szostaLinia.setText(Html.fromHtml("x<sub><small><small>02</small></small></sub>=-b+&#8730;&#916;/2a"));
                                szostaLinia.setVisibility(View.VISIBLE);
                                miejsceZer1 = (-xx-delt)/2/xx2;
                                miejsceZer2 = (-xx+delt)/2/xx2;
                                String x2 = funkcjePrzelicznikowe.intowanie(miejsceZer1);
                                String x3 = funkcjePrzelicznikowe.intowanie(miejsceZer2);
                                wynik.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>="+x2+"\tx<sub><small><small>01</small></small></sub>"+x3));
                            }
                        }
                        else if((!sumaKwadratow.getText().toString().equals(""))&&(!iloczyn.getText().toString().equals(""))){
                            Double sumKw = Double.parseDouble(sumaKwadratow.getText().toString());
                            Double iloczynn = Double.parseDouble(iloczyn.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub><sup><small><small>2</small></small></sup>+x<sub><small><small>02</small></small></sub><sup><small><small>2</small></small></sup>-2x<sub><small><small>01</small></small></sub>x<sub><small><small>02</small></small></sub>=-b/a"));
                            drugaLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>*x<sub><small><small>02</small></small></sub>=c/a"));
                            Double cc = iloczynn;
                            Double xx2 =1.0;
                            Double xx = (sumKw-(2*iloczynn))*-1;
                            trzeciaLinia.setText(Html.fromHtml("a="+xx2+"\tb="+xx+"\tc="+cc));
                            czwartaLinia.setText(Html.fromHtml("&#916;=b<sup><small><small>2</small></small></sup>-4ac"));
                            Double delt = (xx*xx)-(4*xx2*cc);
                            if(delt<0){
                                piataLinia.setText("");
                                szostaLinia.setText("");
                                wynik.setText("Brak miejsc zer.");
                            }
                            else if(delt==0){
                                piataLinia.setText(Html.fromHtml("x<sub><small><small>0</small></small></sub>=-b/2a"));
                                piataLinia.setVisibility(View.VISIBLE);
                                szostaLinia.setText("");
                                miejsceZer1 = -xx/2/xx2;
                                String x2 = funkcjePrzelicznikowe.intowanie(miejsceZer1);
                                wynik.setText(x2);
                            }
                            else{
                                piataLinia.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>=-b-&#8730;&#916;/2a"));
                                piataLinia.setVisibility(View.VISIBLE);
                                szostaLinia.setText(Html.fromHtml("x<sub><small><small>02</small></small></sub>=-b+&#8730;&#916;/2a"));
                                szostaLinia.setVisibility(View.VISIBLE);
                                miejsceZer1 = (-xx-delt)/2/xx2;
                                miejsceZer2 = (-xx+delt)/2/xx2;
                                String x2 = funkcjePrzelicznikowe.intowanie(miejsceZer1);
                                String x3 = funkcjePrzelicznikowe.intowanie(miejsceZer2);
                                wynik.setText(Html.fromHtml("x<sub><small><small>01</small></small></sub>="+x2+"\tx<sub><small><small>01</small></small></sub>"+x3));
                            }
                        }
                    }
                    else if(checkBoxA.isChecked()){
                        Double wspolA = null;
                        if((!d.getText().toString().equals(""))&&(!pierwszeMiejsce.getText().toString().equals(""))){
                            if(x.getText().toString().equals("")){
                                x.setText("0");
                            }
                            Double wspolB = Double.parseDouble(x.getText().toString());
                            if(c.getText().toString().equals("")){
                                c.setText("0");
                            }
                            Double wspolC = Double.parseDouble(c.getText().toString());
                            Double wspolD = Double.parseDouble(d.getText().toString());
                            Double wspolX = Double.parseDouble(pierwszeMiejsce.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a*x<sup><small><small>2</small></small></sup>+b*x+c=y"));
                            drugaLinia.setText(Html.fromHtml("a*x<sup><small><small>2</small></small></sup>=y-(b*x+c)"));
                            trzeciaLinia.setText(Html.fromHtml("a=(y-(b*x+c))/x<sup><small><small>2</small></small></sup>"));
                            czwartaLinia.setText("");
                            wspolB *= wspolX;
                            wspolX *= wspolX;
                            wspolD-=wspolC;
                            wspolD-=wspolB;
                            wspolA = wspolD/wspolX;
                            String xStr = funkcjePrzelicznikowe.intowanie(wspolA);
                            wynik.setText(xStr);
                        }
                    }
                    else if(checkBoxB.isChecked()){
                        Double wspolB = null;
                        if((!d.getText().toString().equals(""))&&(!pierwszeMiejsce.getText().toString().equals(""))){
                            if(x2.getText().toString().equals("")){
                                x2.setText("0");
                            }
                            Double wspolA = Double.parseDouble(x2.getText().toString());
                            if(c.getText().toString().equals("")){
                                c.setText("0");
                            }
                            Double wspolC = Double.parseDouble(c.getText().toString());
                            Double wspolD = Double.parseDouble(d.getText().toString());
                            Double wspolX = Double.parseDouble(pierwszeMiejsce.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a*x<sup><small><small>2</small></small></sup>+b*x+c=y"));
                            drugaLinia.setText(Html.fromHtml("b*x=y-(a*x<sup><small><small>2</small></small></sup>+c)"));
                            trzeciaLinia.setText(Html.fromHtml("b=(y-(a*x<sup><small><small>2</small></small></sup>+c))/x"));
                            czwartaLinia.setText("");
                            wspolA *= wspolX*wspolX;
                            wspolD-=wspolC;
                            wspolD-=wspolA;
                            wspolB = wspolD/wspolX;
                            String xStr = funkcjePrzelicznikowe.intowanie(wspolB);
                            wynik.setText(xStr);
                        }
                    }
                    else if(checkBoxC.isChecked()){
                        Double wspolC = null;
                        if((!d.getText().toString().equals(""))&&(!pierwszeMiejsce.getText().toString().equals(""))){
                            if(x.getText().toString().equals("")){
                                x.setText("0");
                            }
                            Double wspolB = Double.parseDouble(x.getText().toString());
                            if(x2.getText().toString().equals("")){
                                x2.setText("0");
                            }
                            Double wspolA = Double.parseDouble(x2.getText().toString());
                            Double wspolD = Double.parseDouble(d.getText().toString());
                            Double wspolX = Double.parseDouble(pierwszeMiejsce.getText().toString());
                            pierwszaLinia.setText(Html.fromHtml("a*x<sup><small><small>2</small></small></sup>+b*x+c=y"));
                            drugaLinia.setText(Html.fromHtml("c=y-(a*x<sup><small><small>2</small></small></sup>+b*x)"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wspolB *= wspolX;
                            wspolA *= wspolX*wspolX;
                            wspolD-=wspolA;
                            wspolD-=wspolB;
                            wspolC = wspolD;
                            String xStr = funkcjePrzelicznikowe.intowanie(wspolC);
                            wynik.setText(xStr);
                        }
                    }
                    else if(checkBoxIloczyn.isChecked()){
                       Double iloczynWynik = null;

                    }
                    else if(checkBoxSuma.isChecked()) {
                        Double sumaWynik = null;

                    }
                    else if(checkBoxWspolrzedne.isChecked()){
                        Double wspolrzedneX = null, wspolrzedneY = null;
                        if((!x2.getText().toString().equals(""))&&(!x.getText().toString().equals(""))&&(!c.getText().toString().equals(""))){
                            Double wspolA = Double.parseDouble(x2.getText().toString());
                            Double wspolB = Double.parseDouble(x.getText().toString());
                            Double wspolC = Double.parseDouble(c.getText().toString());
                            if(d.getText().toString().equals("")){
                                d.setText("0");
                            }
                            Double wspolD = Double.parseDouble(d.getText().toString());
                            wspolC-=wspolD;
                            pierwszaLinia.setText(Html.fromHtml("x<sub><small><small>w</small></small></sub>=-b/a"));
                            wspolrzedneX = -wspolB/wspolA;
                            drugaLinia.setText(Html.fromHtml("y<sub><small><small>w</small></small></sub>=a*x<sub><small><small>w</small></small></sub><sup><small><small>2</small></small></sup>+b*x<sub><small><small>w</small></small></sub>+c"));
                            trzeciaLinia.setText("");
                            czwartaLinia.setText("");
                            wspolrzedneY=wspolA*wspolrzedneX*wspolrzedneX+wspolB*wspolrzedneX+wspolC;
                            String strWynik1 = funkcjePrzelicznikowe.intowanie(wspolrzedneX);
                            String strWynik2 = funkcjePrzelicznikowe.intowanie(wspolrzedneY);
                            wynik.setText(Html.fromHtml("x<sub><small><small>w</small></small></sub>="+strWynik1+"\ty<sub><small><small>w</small></small></sub>="+strWynik2));
                        }
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wyczyscLinie();
                x2.setText("");
                x.setText("");
                c.setText("");
                d.setText("");
                delta.setText("");
                pierwszeMiejsce.setText("");
                drugieMiejsce.setText("");
                suma.setText("");
                sumaKwadratow.setText("");
                iloczyn.setText("");
                checkBoxA.setChecked(false);
                checkBoxB.setChecked(false);
                checkBoxC.setChecked(false);
                checkBoxDelta.setChecked(false);
                checkBoxIloczyn.setChecked(false);
                checkBoxMiejscaZer.setChecked(false);
                checkBoxSuma.setChecked(false);
                checkBoxWspolrzedne.setChecked(false);
            }
        });

        mdrawerLayout = findViewById(R.id.drawerFunkcjaKwadratowa_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(FunkcjaKwadratowa.this, Konto.class);
                i.putExtra("miejsce", "funkcjaKwadratowa");
                startActivity(i);
                Animatoo.animateFade(FunkcjaKwadratowa.this);
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.znakRownosciFunkcjaKwadratowa){
            getMenuInflater().inflate(R.menu.znaki_rownosci_menu, menu);
            MenuItem menuItem = menu.getItem(3);
            menuItem.setTitle(Html.fromHtml("#8805"));
            MenuItem menuItem1 = menu.getItem(4);
            menuItem1.setTitle(Html.fromHtml("#8804"));
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        znakRownosci.setText(item.getTitle());
        return super.onContextItemSelected(item);
    }
    public void wyczyscLinie(){
        TextView pierszaLinia = findViewById(R.id.pierwszaLiniaFunkcjaKwadratowa);
        TextView drugaLinia = findViewById(R.id.drugaLiniaFunkcjaKwadratowa);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaFunkcjaKwadratowa);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaFunkcjaKwadratowa);
        TextView piataLinia = findViewById(R.id.piataLiniaFunkcjaKwadratowa);
        TextView szostaLinia = findViewById(R.id.szostaLiniaFunkcjaKwadratowa);
        TextView wynik = findViewById(R.id.wynikKwadrat);
        pierszaLinia.setText("");
        drugaLinia.setText("");
        trzeciaLinia.setText("");
        czwartaLinia.setText("");
        piataLinia.setText("");
        piataLinia.setVisibility(View.GONE);
        szostaLinia.setText("");
        szostaLinia.setVisibility(View.GONE);
        wynik.setText("");
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
                    Intent i = new Intent(FunkcjaKwadratowa.this, Szkola.class);
                    i.putExtra("miejsce", "funkcjaKwadratowa");
                    startActivity(i);
                    Animatoo.animateFade(FunkcjaKwadratowa.this);
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
            TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaFunkcjaKwadratowa);
            TextView drugaLinia = findViewById(R.id.drugaLiniaFunkcjaKwadratowa);
            TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaFunkcjaKwadratowa);
            TextView czwartaLinia = findViewById(R.id.czwartaLiniaFunkcjaKwadratowa);
            TextView wynik = findViewById(R.id.wynikFunkcjaKwadratowa);
            savedInstanceData.putString("pierwszaLinia",pierwszaLinia.getText().toString());
            savedInstanceData.putString("drugaLinia", drugaLinia.getText().toString());
            savedInstanceData.putString("trzeciaLinia", trzeciaLinia.getText().toString());
            savedInstanceData.putString("czwartaLinia", czwartaLinia.getText().toString());
            savedInstanceData.putString("wynik", wynik.getText().toString());
        }
        catch (Exception ex){
            Log.i("wynik", ex.getMessage().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView pierwszaLinia = findViewById(R.id.pierwszaLiniaFunkcjaKwadratowa);
        TextView drugaLinia = findViewById(R.id.drugaLiniaFunkcjaKwadratowa);
        TextView trzeciaLinia = findViewById(R.id.trzeciaLiniaFunkcjaKwadratowa);
        TextView czwartaLinia = findViewById(R.id.czwartaLiniaFunkcjaKwadratowa);
        TextView wynik = findViewById(R.id.wynikFunkcjaKwadratowa);
        pierwszaLinia.setText(savedInstanceState.getString("pierwszaLinia"));
        drugaLinia.setText(savedInstanceState.getString("drugaLinia"));
        trzeciaLinia.setText(savedInstanceState.getString("trzeciaLinia"));
        czwartaLinia.setText(savedInstanceState.getString("czwartaLinia"));
        wynik.setText(savedInstanceState.getString("wynik"));
    }
    @Override
    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent i = new Intent(FunkcjaKwadratowa.this, Czworokaty.class);
            startActivity(i);
            Animatoo.animateFade(FunkcjaKwadratowa.this);
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
}