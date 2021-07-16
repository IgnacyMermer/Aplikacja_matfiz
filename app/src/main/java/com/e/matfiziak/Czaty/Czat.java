package com.e.matfiziak.Czaty;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.inne.WyswietlanieZdjecia;
import com.e.matfiziak.inne.dane;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
public class Czat extends TabActivity {
    ImageView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    static TabHost tabHost;
    String incomingName, incomingBoolean, incomingCheckboxPredkosc;
    ArrayList incomingList;
    public static ImageButton button_refresh;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulubione);
        final Intent incomingIntent = getIntent();
        incomingName = incomingIntent.getStringExtra("miejsce");
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        incomingBoolean = incomingIntent.getStringExtra("checkbox");
        incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        button_refresh = findViewById(R.id.button_refresh);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                dane dane1 = new dane();
                dane1.usuwanieZdjec();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(Czat.this, StronaGlowna.class);
                        i.putExtra("miejsce", incomingName);
                        i.putExtra("lista", incomingList);
                        i.putExtra("checkbox", incomingBoolean);
                        i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i2 = funkcjePrzelicznikowe.dolneMenu(incomingName, Czat.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_favourite:
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Czat.this, Konto.class);
                        i3.putExtra("miejsce", incomingName);
                        i3.putExtra("lista", incomingList);
                        i3.putExtra("checkbox", incomingBoolean);
                        i3.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Czat.this, Forum.class);
                        i4.putExtra("miejsce", incomingName);
                        i4.putExtra("lista", incomingList);
                        i4.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i4.putExtra("checkbox", incomingBoolean);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        dane dane1 = new dane();
        if(dane1.index!=-1){
            ustawAktywny(dane1.index);
        }
        profile_image = findViewById(R.id.profileImage);
        username = findViewById(R.id.username);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        tabHost = getTabHost();
        Intent a = new Intent(Czat.this, Chat2.class);
        a.putExtra("userid", "1");
        Intent b = new Intent(Czat.this, Chat2.class);
        b.putExtra("userid", "2");
        Intent c = new Intent(Czat.this, Chat2.class);
        c.putExtra("userid", "3");
        Intent d = new Intent(Czat.this, Chat2.class);
        d.putExtra("userid", "4");
        Intent e = new Intent(Czat.this, Chat2.class);
        e.putExtra("userid", "5");
        Intent f = new Intent(Czat.this, Chat2.class);
        f.putExtra("userid", "6");
        Intent g = new Intent(Czat.this, Chat2.class);
        g.putExtra("userid", "7");
        Intent h = new Intent(Czat.this, Chat2.class);
        h.putExtra("userid", "8");
        Intent i = new Intent(Czat.this, Chat2.class);
        i.putExtra("userid", "9");
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("4 klasa").setContent(a));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("5 klasa").setContent(b));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("6 klasa").setContent(c));
        tabHost.addTab(tabHost.newTabSpec("four").setIndicator("7 klasa").setContent(d));
        tabHost.addTab(tabHost.newTabSpec("five").setIndicator("8 klasa").setContent(e));
        tabHost.addTab(tabHost.newTabSpec("six").setIndicator("1 kl. sz. średniej").setContent(f));
        tabHost.addTab(tabHost.newTabSpec("seven").setIndicator("2 kl. sz. średniej").setContent(g));
        tabHost.addTab(tabHost.newTabSpec("eight").setIndicator("3 kl. sz. średniej").setContent(h));
        tabHost.addTab(tabHost.newTabSpec("nine").setIndicator("4 kl. technikum").setContent(i));
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                dane dane1 = new dane();
                dane1.odpowiedzPozycja = -1;
                Chat2 chat2 = new Chat2();
                //chat2.reference.removeEventListener(chat2.valueEventListener);
            }
        });
    }
    public int  getTab(){
        return tabHost.getCurrentTab();
    }
    public void ustawAktywny(int index){
        tabHost.setCurrentTab(index);
    }
    public void zmiana(){
        Intent intent = new Intent(Czat.this, WyswietlanieZdjecia.class);
        intent.putExtra("miejsce", incomingName);
        intent.putExtra("lista", incomingList);
        intent.putExtra("checkbox", incomingBoolean);
        intent.putExtra("checkbox2", incomingCheckboxPredkosc);
        dane dane1 = new dane();
        dane1.odpowiedzPozycja=-1;
        dane1.index = getTab();
        startActivity(intent);
        Animatoo.animateFade(Czat.this);
    }
}