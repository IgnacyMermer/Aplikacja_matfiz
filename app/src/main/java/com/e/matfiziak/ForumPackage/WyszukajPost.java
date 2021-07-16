package com.e.matfiziak.ForumPackage;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.matfiziak.Adaptery.ForumAdapter;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.inne.Post;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;
import java.util.ArrayList;
import java.util.List;
public class WyszukajPost extends AppCompatActivity {
    EditText editTextWyszukiwanie;
    RecyclerView recyclerView;
    List<Post> posts;
    ForumAdapter forumAdapter;
    String klasa="", dzial="";
    int klasaNumer = 0, dzialNumer=0;
    @Override
    protected void onResume() {
        super.onResume();
        szukaniePostow(editTextWyszukiwanie.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyszukaj_post);
        final Intent incomingIntent = getIntent();
        posts = new ArrayList<>();
        final String incomingName = incomingIntent.getStringExtra("miejsce");
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        final String text = incomingIntent.getStringExtra("text");
        klasa = incomingIntent.getStringExtra("klasa");
        dzial = incomingIntent.getStringExtra("dzial");
        klasaNumer = incomingIntent.getIntExtra("klasaNumer",0);
        dzialNumer = incomingIntent.getIntExtra("dzialNumer",0);
        editTextWyszukiwanie = findViewById(R.id.editTextWyszukaj);
        editTextWyszukiwanie.setText(text);
        szukaniePostow(editTextWyszukiwanie.getText().toString());
        recyclerView= findViewById(R.id.recycler_viewWyszukaj);
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
                        Intent i = new Intent(WyszukajPost.this, StronaGlowna.class);
                        i .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", incomingName);
                        i.putExtra("lista", incomingList);
                        i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i.putExtra("checkbox", incomingBoolean);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i1 = funkcjePrzelicznikowe.dolneMenu(incomingName, WyszukajPost.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(WyszukajPost.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", incomingName);
                        i2.putExtra("lista", incomingList);
                        i2.putExtra("checkbox", incomingBoolean);
                        i2.putExtra("checkbox2", incomingCheckboxPredkosc);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(WyszukajPost.this, Konto.class);
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
        editTextWyszukiwanie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                szukaniePostow(s.toString());
                recyclerView.setAdapter(forumAdapter);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        final Button btnWyjezdzajaceMenu = findViewById(R.id.btnFiltrujForum);
        btnWyjezdzajaceMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(WyszukajPost.this, R.style.bottomSheetDialogTheme);
                final View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_wyjedzajace_menu,(LinearLayout) findViewById(R.id.bottomSheetMenu));
                final Spinner spinner = bottomSheetView.findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(WyszukajPost.this,R.array.wielkosci, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection(klasaNumer);
                final Spinner spinner1 = bottomSheetView.findViewById(R.id.spinner2);
                ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(WyszukajPost.this, R.array.dzialy, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter1);
                spinner1.setSelection(dzialNumer);
                bottomSheetView.findViewById(R.id.btnZatwierdzMenu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(spinner.getSelectedItem().toString().equals("Wszystkie")){
                            klasa="";
                            klasaNumer=0;
                        }
                        else{
                            klasa = spinner.getSelectedItem().toString();
                            klasaNumer = spinner.getSelectedItemPosition();
                        }
                        if(spinner1.getSelectedItem().toString().equals("Wszystkie")){
                            dzial = "";
                            dzialNumer=0;
                        }
                        else{
                            dzial = spinner1.getSelectedItem().toString();
                            dzialNumer = spinner1.getSelectedItemPosition();
                        }
                        bottomSheetDialog.dismiss();
                        szukaniePostow(editTextWyszukiwanie.getText().toString());
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        UIUtil.showKeyboard(WyszukajPost.this, editTextWyszukiwanie);
    }
    public void szukaniePostow(String x){
        posts.clear();
        Query query = FirebaseDatabase.getInstance().getReference("Forum").orderByChild("tresc").startAt(x).endAt(x+"\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    if(!klasa.equals("")){
                        if(post.getKlasa().equals(klasa)){
                            if(!dzial.equals("")){
                                if(post.getDzial().equals(dzial)){
                                    posts.add(post);
                                }
                            }
                            else{
                                posts.add(post);
                            }
                        }
                    }
                    else if(!dzial.equals("")){
                        if(post.getDzial().equals(dzial)){
                            posts.add(post);
                        }
                    }
                    else{
                        posts.add(post);
                    }
                }
                forumAdapter = new ForumAdapter(WyszukajPost.this, posts);
                recyclerView.setLayoutManager(new LinearLayoutManager(WyszukajPost.this));
                recyclerView.setAdapter(forumAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}