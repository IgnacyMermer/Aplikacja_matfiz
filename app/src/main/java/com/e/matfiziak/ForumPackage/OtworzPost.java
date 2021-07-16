package com.e.matfiziak.ForumPackage;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.matfiziak.Adaptery.ForumOdpowiedziAdapter;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.inne.Odpowiedzi;
import com.e.matfiziak.inne.PhotoForum;
import com.e.matfiziak.inne.Post;
import com.e.matfiziak.inne.WyswietlanieZdjecia;
import com.e.matfiziak.inne.dane;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtworzPost extends AppCompatActivity {
    Post glownyPost;
    int iloscZdjec=0;
    int index=0;
    String id;
    dane dane1;
    RecyclerView recyclerView;
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("ForumOdpowiedzi");
        recyclerView = findViewById(R.id.recycler_viewOtworz);
        final ProgressDialog pd = new ProgressDialog(OtworzPost.this);
        reference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Odpowiedzi> mOdpowiedzi = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Odpowiedzi odpowiedzi = snapshot.getValue(Odpowiedzi.class);
                    if(odpowiedzi.getIdPost().equals(id)){
                        mOdpowiedzi.add(odpowiedzi);
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(OtworzPost.this));
                ForumOdpowiedziAdapter forumOdpowiedziAdapter = new ForumOdpowiedziAdapter(OtworzPost.this, mOdpowiedzi);
                recyclerView.setAdapter(forumOdpowiedziAdapter);
                pd.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otworz_post);
        final ProgressDialog pd = new ProgressDialog(OtworzPost.this);
        Intent incomingIntent = getIntent();
        dane1 = new dane();
        recyclerView = findViewById(R.id.recycler_viewOtworz);
        final String incomingName = incomingIntent.getStringExtra("miejsce");
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(OtworzPost.this, StronaGlowna.class);
                        i .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", incomingName);
                        i.putExtra("lista", incomingList);
                        i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i.putExtra("checkbox", incomingBoolean);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i1 = funkcjePrzelicznikowe.dolneMenu(incomingName, OtworzPost.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(OtworzPost.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", incomingName);
                        i2.putExtra("lista", incomingList);
                        i2.putExtra("checkbox", incomingBoolean);
                        i2.putExtra("checkbox2", incomingCheckboxPredkosc);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(OtworzPost.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", incomingName);
                        i3.putExtra("lista", incomingList);
                        i3.putExtra("checkbox", incomingBoolean);
                        i3.putExtra("checkbox2", incomingCheckboxPredkosc);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        break;
                }
                return false;
            }
        });
        id = incomingIntent.getStringExtra("id");
        if(id==null){
            id="";
        }
        final CircleImageView profilowe = findViewById(R.id.profilowePost);
        final TextView nick = findViewById(R.id.nickPost);
        nick.setVisibility(View.GONE);
        final TextView tytul = findViewById(R.id.tytulPost);
        tytul.setVisibility(View.GONE);
        final TextView tresc = findViewById(R.id.trescPost);
        tresc.setVisibility(View.GONE);
        final TextView numerZad = findViewById(R.id.numerZadPost);
        numerZad.setVisibility(View.GONE);
        final TextView dodatkoweInf = findViewById(R.id.dodatInfPost);
        dodatkoweInf.setVisibility(View.GONE);
        final ImageButton pierwszeZdj = findViewById(R.id.pierwszeZdjecie);
        pierwszeZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(id)){
                                Intent i = new Intent(OtworzPost.this, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                startActivity(i);
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        final ImageButton drugieZdj = findViewById(R.id.drugieZdjecie);
        drugieZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                index=0;
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(id)&&index==1){
                                Intent i = new Intent(OtworzPost.this, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                startActivity(i);
                                break;
                            }
                            else{
                                index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        final ImageButton trzecieZdj = findViewById(R.id.trzecieZdjecie);
        trzecieZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                index=0;
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(id)&&index==2){
                                Intent i = new Intent(OtworzPost.this, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                startActivity(i);
                                break;
                            }
                            else{
                                index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        final ImageButton czwarteZdj = findViewById(R.id.czwarteZdjecie);
        czwarteZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                index=0;
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(id)&&index==3){
                                Intent i = new Intent(OtworzPost.this, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                startActivity(i);
                                break;
                            }
                            else{
                                index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        final ImageButton piateZdj = findViewById(R.id.piateZdjecie);
        piateZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                index=0;
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(id)&&index==4){
                                Intent i = new Intent(OtworzPost.this, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                startActivity(i);
                                break;
                            }
                            else{
                                index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        Button btnDodajKom = findViewById(R.id.dodajKomPost);
        btnDodajKom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OtworzPost.this, DodajOdpowiedz.class);
                i.putExtra("id", id);
                dane1.idPost = id;
                startActivity(i);
            }
        });
        final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Forum");
        final String finalId = id;
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pd.setMessage("ładowanie...");
                pd.show();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    if (post.getId().equals(finalId)) {
                        glownyPost = post;
                        break;
                    }
                }
                nick.setText(glownyPost.getUsername());
                tytul.setText(glownyPost.getTytul());
                tresc.setText(glownyPost.getTresc());
                if(tresc.getText().toString().equals("")){
                    tresc.setVisibility(View.GONE);
                }
                numerZad.setText(glownyPost.getNumerZad());
                if(numerZad.getText().toString().equals("")){
                    numerZad.setVisibility(View.GONE);
                }
                dodatkoweInf.setText(glownyPost.getDodatkoweInf());
                if(dodatkoweInf.getText().toString().equals("")){
                    dodatkoweInf.setVisibility(View.GONE);
                }
                String imageurl = glownyPost.getImageURL();
                if(imageurl==null){
                    imageurl="default";
                }
                if(imageurl.equals("default")){
                    Glide.with(OtworzPost.this).load(R.mipmap.ic_launcher).into(profilowe);
                }
                else{
                    Glide.with(OtworzPost.this).load(imageurl).into(profilowe);
                }
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        iloscZdjec=0;
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(glownyPost.getId())){
                                iloscZdjec++;
                            }
                        }
                        if(iloscZdjec>=1){
                            pierwszeZdj.setVisibility(View.VISIBLE);
                        }
                        if(iloscZdjec>=2){
                            drugieZdj.setVisibility(View.VISIBLE);
                        }
                        if(iloscZdjec>=3){
                            trzecieZdj.setVisibility(View.VISIBLE);
                        }
                        if(iloscZdjec>=4){
                            czwarteZdj.setVisibility(View.VISIBLE);
                        }
                        if(iloscZdjec>=5){
                            piateZdj.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
                DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("ForumOdpowiedzi");
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Odpowiedzi> mOdpowiedzi = new ArrayList<>();
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            Odpowiedzi odpowiedzi = snapshot.getValue(Odpowiedzi.class);
                            if(odpowiedzi.getIdPost().equals(id)){
                                mOdpowiedzi.add(odpowiedzi);
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(OtworzPost.this));
                        ForumOdpowiedziAdapter forumOdpowiedziAdapter = new ForumOdpowiedziAdapter(OtworzPost.this, mOdpowiedzi);
                        recyclerView.setAdapter(forumOdpowiedziAdapter);
                        nick.setVisibility(View.VISIBLE);
                        tytul.setVisibility(View.VISIBLE);
                        tresc.setVisibility(View.VISIBLE);
                        numerZad.setVisibility(View.VISIBLE);
                        dodatkoweInf.setVisibility(View.VISIBLE);
                        pd.dismiss();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}