package com.e.matfiziak;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.e.matfiziak.Adaptery.SectionsPagerAdapter;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.Fragments.Tab1Fragment;
import com.e.matfiziak.Fragments.Tab2Fragment;
import com.e.matfiziak.Konta.Konto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Frag2 extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag2);
        Intent incomingIntent = getIntent();
        String incominName = incomingIntent.getStringExtra("miejsce");
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        if(incominName==null){
            incominName="Szkola";
        }
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        mSectionsStatePagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_prawo);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_lewo);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        final String finalIncomingName = incominName;
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_school:
                        Intent i = null;
                        if(finalIncomingName.equals("ZasadyDynamiki")){
                            i = new Intent(Frag2.this, ZasadyDynamiki.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Grawitacja")){
                            i = new Intent(Frag2.this, Grawitacja.class);
                        }
                        else if(finalIncomingName.equals("Szkola")){
                            i=new Intent(Frag2.this, Szkola.class);
                        }
                        else if(finalIncomingName.equals("Keppler")){
                            i = new Intent(Frag2.this, Keppler.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("FizykaKalkulator")){
                            i = new Intent(Frag2.this, FizykaKalkulator.class);
                        }
                        else if(finalIncomingName.equals("MatematykaKalkulator")){
                            i = new Intent(Frag2.this, MatematykaKalkulator.class);
                        }
                        else if(finalIncomingName.equals("ModelBohra")){
                            i = new Intent(Frag2.this, ModelBohra.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("poleTrojkata")){
                            i = new Intent(Frag2.this, pole_trojkata.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Predkosc")){
                            i = new Intent(Frag2.this, Predkosc.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                            i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        }
                        else if(finalIncomingName.equals("Przyszpieszenie")){
                            i = new Intent(Frag2.this, Przyszpieszenie.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("PrzyszpieszenieZwykle")){
                            i = new Intent(Frag2.this, PrzyszpieszenieZwykle.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("RuchPoOkregu")){
                            i = new Intent(Frag2.this, RuchPoOkregu.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Pole_trojkata_rownoramiennego")){
                            i = new Intent(Frag2.this, Pole_trojkata_rownoramiennego.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Pole_trojkata_roznobocznego")){
                            i = new Intent(Frag2.this, Pole_trojkata_roznobocznego.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Trojkaty")){
                            i = new Intent(Frag2.this, Trojkaty.class);
                        }
                        else if(finalIncomingName.equals("Kwadrat")){
                            i = new Intent(Frag2.this, Kwadrat.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Prostokat")){
                            i = new Intent(Frag2.this, Prostokat.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Romb")){
                            i = new Intent(Frag2.this, Romb.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Rownoleglobok")){
                            i = new Intent(Frag2.this, Rownoleglobok.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Czworokaty")){
                            i = new Intent(Frag2.this, Czworokaty.class);
                        }
                        else if(finalIncomingName.equals("trapezProstokatny")){
                            i = new Intent(Frag2.this, trapez_prostokatny.class);
                            i.putExtra("lista", incomingList);
                            i.putExtra("checkbox", incomingBoolean);
                        }
                        else if(finalIncomingName.equals("Trapezy")){
                            i = new Intent(Frag2.this, Trapezy.class);
                        }
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Frag2.this, Czat.class);
                        i2.putExtra("miejsce", finalIncomingName);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("lista", incomingList);
                        i2.putExtra("checkbox", incomingBoolean);
                        i2.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Frag2.this, Konto.class);
                        i3.putExtra("miejsce", finalIncomingName);
                        i3.putExtra("lista", incomingList);
                        i3.putExtra("checkbox", incomingBoolean);
                        i3.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Frag2.this, Forum.class);
                        i4.putExtra("miejsce", finalIncomingName);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("lista", incomingList);
                        i4.putExtra("checkbox", incomingBoolean);
                        i4.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i4.putExtra("nick", nick);
                        i4.putExtra("imageUrl", imageUr);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
    }
    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Fragment1");
        adapter.addFragment(new Tab2Fragment(), "Fragment2");
        viewPager.setAdapter(adapter);
    }
    public void setmViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
