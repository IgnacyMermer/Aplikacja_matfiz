package com.e.matfiziak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DodajNews extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_news);
        final EditText tytul = findViewById(R.id.tytulNewsa);
        final EditText link = findViewById(R.id.link);
        final EditText zrodlo = findViewById(R.id.zrodlo);
        Button dodajNewsBtn = findViewById(R.id.buttonDodajNewsa);
        dodajNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("News");
                String id = reference.push().getKey();
                Map<String, String> mapa = new HashMap<>();
                mapa.put("id", id);
                mapa.put("link", link.getText().toString());
                mapa.put("zrodlo", zrodlo.getText().toString());
                mapa.put("tekst", tytul.getText().toString());
                reference.child(id).setValue(mapa);
                Intent i = new Intent(DodajNews.this, Frag1.class);
                startActivity(i);
            }
        });
    }
}