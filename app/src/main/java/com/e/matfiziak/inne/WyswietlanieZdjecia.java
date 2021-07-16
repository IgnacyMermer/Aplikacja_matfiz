package com.e.matfiziak.inne;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.e.matfiziak.R;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.ArrayList;
public class WyswietlanieZdjecia extends AppCompatActivity {
    String incomingName, incomingBoolean, incomingCheckboxPredkosc;
    ArrayList incomingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietlanie_zdjecia);
        final Intent incomingIntent = getIntent();
        incomingName = incomingIntent.getStringExtra("miejsce");
        incomingList = incomingIntent.getStringArrayListExtra("lista");
        incomingBoolean = incomingIntent.getStringExtra("checkbox");
        incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        PhotoView poleZdjecia = findViewById(R.id.poleZdjecia);
        String url = incomingIntent.getStringExtra("id");
        if(url==null){
            Toast.makeText(this, "Błąd przy załadowaniu", Toast.LENGTH_SHORT).show();
        }
        else{
            Glide.with(getApplicationContext()).load(url).into(poleZdjecia);
        }
        ImageButton powrot = findViewById(R.id.powrotButton);
        powrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WyswietlanieZdjecia.super.onBackPressed();
            }
        });
    }
}