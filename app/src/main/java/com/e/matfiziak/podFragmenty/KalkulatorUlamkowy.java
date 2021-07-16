package com.e.matfiziak.podFragmenty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.e.matfiziak.Adaptery.SectionsPagerAdapter;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.Fragments.Kalkulator1;
import com.e.matfiziak.Fragments.Kalkulator2;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class KalkulatorUlamkowy extends AppCompatActivity {
    SectionsPagerAdapter mSectionsStatePagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_ulamkowy);
        mSectionsStatePagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_prawo);
        String incomingName = null;
        String incominBoolean = null;
        Intent incomingIntent = getIntent();
        final String nick = incomingIntent.getStringExtra("nick");
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        ArrayList<String> incominList = null;
        Intent i = getIntent();
        try {
            incomingName = i.getStringExtra("miejsce");
            incominBoolean = i.getStringExtra("checkbox");
            incominList = i.getStringArrayListExtra("lista");
        }
        catch (Exception ex){
            Log.i("wynik", ex.getMessage().toString());
        }
        final String finalIncomingName = incomingName;
        final String incomingBoolean = incominBoolean;
        final String incomingCheckboxPredkosc = i.getStringExtra("checkbox2");
        final ArrayList<String> incomingList = incominList;
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final String finalIncomingName1 = incomingName;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i = funkcjePrzelicznikowe.dolneMenu(finalIncomingName1, KalkulatorUlamkowy.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("nick", nick);
                        i.putExtra("imageUrl", imageUr);
                        startActivity(i);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(KalkulatorUlamkowy.this, Czat.class);
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
                        Intent i3 = new Intent(KalkulatorUlamkowy.this, Konto.class);
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
                        Intent i4 = new Intent(KalkulatorUlamkowy.this, Forum.class);
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
        adapter.addFragment(new Kalkulator1(), "Fragment1");
        adapter.addFragment(new Kalkulator2(), "Fragment2");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {}
}