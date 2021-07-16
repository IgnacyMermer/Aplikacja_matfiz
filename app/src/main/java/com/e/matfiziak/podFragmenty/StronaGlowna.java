package com.e.matfiziak.podFragmenty;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.e.matfiziak.Frag1;
import com.e.matfiziak.Frag2;
import com.e.matfiziak.R;

import java.util.ArrayList;

public class StronaGlowna extends TabActivity  {
    String username, imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strona_glowna);
        final TabHost tabHost = getTabHost();
        String incomingName;
        ArrayList incomingList = null;
        String incomingBoolean=null;
        String incomingCheckboxPredkosc="";
        try {
            Intent incomingIntent = getIntent();
            username = incomingIntent.getStringExtra("nick");
            imageUrl = incomingIntent.getStringExtra("imageUrl");
            incomingName = incomingIntent.getStringExtra("miejsce");
            incomingList = incomingIntent.getStringArrayListExtra("lista");
            incomingBoolean = incomingIntent.getStringExtra("checkbox");
            incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        }
        catch (Exception ex){
            incomingName = "Szkola";
        }
        if(incomingName==null){
            incomingName="Szkola";
        }
        Intent a = new Intent(this, Frag1.class);
        a.putExtra("miejsce", incomingName);
        a.putExtra("lista",incomingList);
        a.putExtra("checkbox", incomingBoolean);
        a.putExtra("checkbox2", incomingCheckboxPredkosc);
        a.putExtra("nick", username);
        a.putExtra("imageUrl", imageUrl);
        Intent b = new Intent(this, Frag2.class);
        b.putExtra("miejsce", incomingName);
        b.putExtra("lista",incomingList);
        b.putExtra("checkbox", incomingBoolean);
        b.putExtra("checkbox2", incomingCheckboxPredkosc);
        b.putExtra("nick", username);
        b.putExtra("imageUrl", imageUrl);
        Intent c = new Intent(this, Trygonometryczny.class);
        c.putExtra("miejsce", incomingName);
        c.putExtra("lista", incomingList);
        c.putExtra("checkbox", incomingBoolean);
        c.putExtra("checkbox2", incomingCheckboxPredkosc);
        c.putExtra("nick", username);
        c.putExtra("imageUrl", imageUrl);
        Intent d = new Intent(this, KalkulatorUlamkowy.class);
        d.putExtra("miejsce", incomingName);
        d.putExtra("lista", incomingList);
        d.putExtra("checkbox", incomingBoolean);
        d.putExtra("checkbox2", incomingCheckboxPredkosc);
        d.putExtra("nick", username);
        d.putExtra("imageUrl", imageUrl);
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("Strona główna").setContent(a));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("Przelicznik").setContent(b));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("Trygonometryczny").setContent(c));
        tabHost.addTab(tabHost.newTabSpec("four").setIndicator("Kalkulator ułamkowy").setContent(d));
    }
}