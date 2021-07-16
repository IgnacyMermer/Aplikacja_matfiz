package com.e.matfiziak;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class Predkosc extends AppCompatActivity {
    ArrayList<String>itemList;
    ArrayAdapter<String> adapter2;
    EditText item1;
    EditText item2;
    Button buttonAdd;
    Button buttonUsun;
    ListView lv;
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predkosc);
        ScrollView relativeLayout = findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        String checkbox="";
        ArrayList<String> incomingList = new ArrayList<String>();
        Intent incomingIntent = getIntent();
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        try {
            incomingList = incomingIntent.getStringArrayListExtra("lista");
            checkbox = incomingIntent.getStringExtra("checkbox");
        }
        catch (Exception ex){
            //incomingList.clear();
        }
        String incomingCheckbox="";
        itemList = new ArrayList<>();
        if(incomingList!=null){
            itemList = incomingList;
        }
        incomingCheckbox=incomingIntent.getStringExtra("checkbox2");
        if(incomingCheckbox==null){
            incomingCheckbox="";
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        lv = findViewById(R.id.listView);
        item1 = findViewById(R.id.licznik);
        item2 = findViewById(R.id.mianownik);
        buttonAdd = findViewById(R.id.buttonDodaj);
        buttonUsun = findViewById(R.id.buttonUsun);
        final TextView wynikPredkosc = findViewById(R.id.wynikPredkosc);
        if(checkbox!=null){
            wynikPredkosc.setText(checkbox);
        }
        adapter2 = new ArrayAdapter<String>(Predkosc.this,android.R.layout.simple_list_item_multiple_choice, itemList);
        final EditText predkosc = findViewById(R.id.predkosc);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(item1.getText().toString()+"/"+item2.getText().toString()+"   v="+predkosc.getText().toString());
                item2.setText("");
                item1.setText("");
                predkosc.setText("");
                if((itemList.size() > 6)&&Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        lv.setNestedScrollingEnabled(true);
                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        lv.setNestedScrollingEnabled(false);
                    }
                }
                adapter2.notifyDataSetChanged();
            }
        });
        buttonUsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecked = lv.getCheckedItemPositions();
                int count = lv.getCount();
                for(int i=count-1;i>=0;i--){
                    if(positionChecked.get(i)){
                        adapter2.remove(itemList.get(i));
                    }
                }
                if((itemList.size() > 6)&&Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    lv.setNestedScrollingEnabled(true);
                }
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    lv.setNestedScrollingEnabled(false);
                }
                positionChecked.clear();
                adapter2.notifyDataSetChanged();
            }
        });
        final CheckBox checkBoxCzas = findViewById(R.id.checkboxCzas);
        final CheckBox checkBoxDroga = findViewById(R.id.checkPredkosc);
        if(incomingCheckbox.equals("czas")){
            checkBoxCzas.setChecked(true);
        }
        else if(incomingCheckbox.equals("droga")){
            checkBoxDroga.setChecked(true);
        }
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String checkbox3 ="";
                if(checkBoxCzas.isChecked()){
                    checkbox3="czas";
                }
                else if(checkBoxDroga.isChecked()){
                    checkbox3="droga";
                }
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(Predkosc.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "Predkosc");
                        i.putExtra("lista", itemList);
                        i.putExtra("checkbox", wynikPredkosc.getText().toString());
                        i.putExtra("checkbox2", checkbox3);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(Predkosc.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Predkosc.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "Predkosc");
                        i2.putExtra("lista", itemList);
                        i2.putExtra("checkbox", wynikPredkosc.getText().toString());
                        i2.putExtra("checkbox2", checkbox3);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Predkosc.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "Predkosc");
                        i3.putExtra("lista", itemList);
                        i3.putExtra("checkbox", wynikPredkosc.getText().toString());
                        i3.putExtra("checkbox2", checkbox3);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Predkosc.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "Predkosc");
                        i4.putExtra("lista", itemList);
                        i4.putExtra("checkbox", wynikPredkosc.getText().toString());
                        i4.putExtra("checkbox2", checkbox3);
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        lv.setAdapter(adapter2);
        Button buttonOblicz = findViewById(R.id.buttonOblicz);
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList liczniki = new ArrayList();
                ArrayList mianowniki = new ArrayList();
                ArrayList predkosc = new ArrayList();
                int count = lv.getCount();
                try {
                    for(int i=0;i<=count-1;i++){
                        String x = itemList.get(i).toString();
                        String y="";
                        String y2="";
                        String y3="";
                        int z = 0;
                        while(z<5){
                            if(x.charAt(z)=='/'){
                                break;
                            }
                            y=y+x.charAt(z);
                            z++;
                        }
                        int a1 = Integer.parseInt(y);
                        liczniki.add(a1);
                        z++;
                        while (z<10){
                            if(x.charAt(z)==' '){
                                break;
                            }
                            y2=y2+x.charAt(z);
                            z++;
                        }
                        int a2 = Integer.parseInt(y2);
                        mianowniki.add(a2);
                        z++;
                        z=z+4;
                        while(z<15){
                            if(x.length()==z){
                                break;
                            }
                            y3=y3+x.charAt(z);
                            z++;
                        }
                        Double a3 = Double.parseDouble(y3);
                        predkosc.add(a3);
                    }
                    int y = (int) mianowniki.get(0);
                    for(int i=0;i<mianowniki.size()-1;i++){
                        int z = (int) mianowniki.get(i+1);
                        int yz=y*z;
                        while(z!=0){
                            int t=z;
                            z=y%z;
                            y=t;
                        }
                        int a=yz-y;
                        int b=1;
                        while(a>0){
                            a=a-y;
                            b++;
                        }
                        if(a!=0){
                            a=a+y;
                        }
                        else{
                            a=b;
                        }
                        y=a;
                    }
                    for(int i=0;i<liczniki.size();i++){
                        int x= (int) liczniki.get(i);
                        int x2= (int) mianowniki.get(i);
                        int z = y/x2;
                        z=z*x;
                        liczniki.set(i,z);
                    }
                    int z=0;
                    for(int i=0;i<liczniki.size();i++){
                        int x = (int) liczniki.get(i);
                        z+=x;
                    }
                    if(z!=y){
                        wynikPredkosc.setText("Niepoprawne dane");
                    }
                    else {
                        wynikPredkosc.setText("");
                    }
                    if(z!=y){

                    }
                    else if(z==y){
                        CheckBox checkBoxCzas = findViewById(R.id.checkboxCzas);
                        CheckBox checkBoxDroga = findViewById(R.id.checkPredkosc);
                        if(checkBoxCzas.isChecked()) {
                             ArrayList wyniki = new ArrayList();
                            for (int i = 0; i < liczniki.size(); i++) {
                                int x = (int) liczniki.get(i);
                                String x11 = Integer.toString(x);
                                Double x01 = Double.parseDouble(x11);
                                int x2 = (int) mianowniki.get(i);
                                String x22 = Integer.toString(x2);
                                Double x02 = Double.parseDouble(x22);
                                Double x3 = (Double) predkosc.get(i);
                                Double x4 = x01 / y * x3;
                                wyniki.add(x4);
                            }
                            Double x = 0.0;
                            for (int i = 0; i < wyniki.size(); i++) {
                                Double x2 = (Double) wyniki.get(i);
                                x += x2;
                            }
                            String x12 = funkcjePrzelicznikowe.intowanie(x);
                            wynikPredkosc.setText(x12);
                        }
                        else if(checkBoxDroga.isChecked()){
                            ArrayList wyniki = new ArrayList();
                            for (int i = 0; i < liczniki.size(); i++) {
                                int x = (int) liczniki.get(i);
                                String x11 = Integer.toString(x);
                                Double x01 = Double.parseDouble(x11);
                                int x2 = (int) mianowniki.get(i);
                                String x22 = Integer.toString(x2);
                                Double x02 = Double.parseDouble(x22);
                                Double x3 = (Double) predkosc.get(i);
                                Double x4 = x01 / y / x3;
                                wyniki.add(x4);
                            }
                            Double x = 0.0;
                            for (int i = 0; i < wyniki.size(); i++) {
                                Double x2 = (Double) wyniki.get(i);
                                x += x2;
                            }
                            x=1/x;
                            String x12 = funkcjePrzelicznikowe.intowanie(x);
                            wynikPredkosc.setText(x12);
                        }
                    }
                }
                catch (Exception ex){
                    Log.i("wynik", ex.getMessage().toString());
                }
            }
        });
        checkBoxCzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxDroga.setChecked(false);
            }
        });
        checkBoxDroga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxCzas.setChecked(false);
            }
        });
        mdrawerLayout = findViewById(R.id.drawerPredkosc_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(Predkosc.this, Konto.class);
                i.putExtra("miejsce", "Predkosc");
                startActivity(i);
                Animatoo.animateFade(Predkosc.this);
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
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        try {
            TextView wynik = findViewById(R.id.wynikPredkosc);
            savedInstanceState.putString("wynik", wynik.getText().toString());
            savedInstanceState.putStringArrayList("lista", itemList);
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            TextView wynik = findViewById(R.id.wynikPredkosc);
            String wynikStr = savedInstanceState.getString("wynik");
            wynik.setText(wynikStr);
            ArrayList<String> lista = savedInstanceState.getStringArrayList("lista");
            itemList = lista;
        }
        catch (Exception ex){
            Log.i("wynik2", ex.getMessage().toString());
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
                    Intent i = new Intent(Predkosc.this, Szkola.class);
                    i.putExtra("miejsce", "Predkosc");
                    startActivity(i);
                    Animatoo.animateFade(Predkosc.this);
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
            Intent i = new Intent(Predkosc.this, FizykaKalkulator.class);
            startActivity(i);
            Animatoo.animateFade(Predkosc.this);
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