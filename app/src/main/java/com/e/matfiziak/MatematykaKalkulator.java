package com.e.matfiziak;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
public class MatematykaKalkulator extends AppCompatActivity {
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
        setContentView(R.layout.activity_matematyka_kalkulator);
        ScrollView relativeLayout = findViewById(R.id.tloScrollek);
        AnimationDrawable animationDrawable = (AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        Intent incomingIntent = getIntent();
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(MatematykaKalkulator.this, StronaGlowna.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", "MatematykaKalkulator");
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = new Intent(MatematykaKalkulator.this, Szkola.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i1.putExtra("nick", nick);
                        i1.putExtra("imageUrl", imageUr);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(MatematykaKalkulator.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", "MatematykaKalkulator");
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(MatematykaKalkulator.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", "MatematykaKalkulator");
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(MatematykaKalkulator.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", "MatematykaKalkulator");
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        dane dane = new dane();
        Button buttonTrojkaty = findViewById(R.id.buttonTrojkaty);
        buttonTrojkaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatematykaKalkulator.this, Trojkaty.class);
                i.putExtra("nick", nick);
                i.putExtra("imageUrl", imageUr);
                startActivity(i);
                Animatoo.animateFade(MatematykaKalkulator.this);
            }
        });
        Button buttonCzworokaty = findViewById(R.id.buttonCzworokaty);
        buttonCzworokaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatematykaKalkulator.this, Czworokaty.class);
                i.putExtra("nick", nick);
                i.putExtra("imageUrl", imageUr);
                startActivity(i);
                Animatoo.animateFade(MatematykaKalkulator.this);
            }
        });
        Button buttonInne = findViewById(R.id.buttonInneFigury);
        /*buttonInne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatematykaKalkulator.this, Pole_trojkata_roznobocznego.class);
                i.putExtra("nick", nick);
                i.putExtra("imageUrl", imageUr);
                startActivity(i);
                Animatoo.animateFade(MatematykaKalkulator.this);
            }
        });*/
        Button buttonFunkcje = findViewById(R.id.buttonFunkcje);
        buttonFunkcje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatematykaKalkulator.this, FunkcjaKwadratowa.class);
                startActivity(i);
                Animatoo.animateFade(MatematykaKalkulator.this);
            }
        });





        mdrawerLayout = findViewById(R.id.drawerMatematykaKalkulator_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(MatematykaKalkulator.this, Konto.class);
                startActivity(i);
                Animatoo.animateFade(MatematykaKalkulator.this);
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
                    Intent i = new Intent(MatematykaKalkulator.this, Szkola.class);
                    startActivity(i);
                    Animatoo.animateFade(MatematykaKalkulator.this);
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
            Intent i = new Intent(MatematykaKalkulator.this, Szkola.class);
            startActivity(i);
            Animatoo.animateFade(MatematykaKalkulator.this);
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