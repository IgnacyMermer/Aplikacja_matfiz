package com.e.matfiziak;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.e.matfiziak.Adaptery.NewsAdapter;
import com.e.matfiziak.Adaptery.SectionsPagerAdapter;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.inne.News;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class Frag1 extends AppCompatActivity{
    private SectionsPagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag1);
        Intent incomingIntent = getIntent();
        String incominName = incomingIntent.getStringExtra("miejsce");
        final String nick = incomingIntent.getStringExtra("nick");
        Log.i("wynik", ""+nick);
        final String imageUr = incomingIntent.getStringExtra("imageUrl");
        if(incominName==null){
            incominName="Szkola";
        }
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view1);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        final String finalIncomingName = incominName;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i = funkcjePrzelicznikowe.dolneMenu(finalIncomingName,Frag1.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        return true;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(Frag1.this, Czat.class);
                        i2.putExtra("miejsce", finalIncomingName);
                        i2.putExtra("lista", incomingList);
                        i2.putExtra("checkbox", incomingBoolean);
                        i2.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i2.putExtra("nick", nick);
                        i2.putExtra("imageUrl", imageUr);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(Frag1.this, Konto.class);
                        i3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i3.putExtra("miejsce", finalIncomingName);
                        i3.putExtra("lista", incomingList);
                        i3.putExtra("checkbox", incomingBoolean);
                        i3.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i3.putExtra("nick", nick);
                        i3.putExtra("imageUrl", imageUr);
                        startActivity(i3);
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(Frag1.this, Forum.class);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i4.putExtra("miejsce", finalIncomingName);
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

        ImageButton dodajNews = findViewById(R.id.dodajNews);
        dodajNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Frag1.this, DodajNews.class);
                startActivity(i);
            }
        });
        HorizontalScrollView scrollNews = findViewById(R.id.scrollNewsy);
        final RecyclerView recyclerViewNews = findViewById(R.id.recycler_viewNewsy);
        recyclerViewNews.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewNews.setLayoutManager(linearLayoutManager);
        final List<News> mNews = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("News");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    News news = snapshot.getValue(News.class);
                    mNews.add(news);
                    NewsAdapter newsAdapter = new NewsAdapter(Frag1.this, mNews);
                    recyclerViewNews.setAdapter(newsAdapter);
                    try {
                        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener(){
                            @Override
                            public void onItemClick(int position) {
                                mNews.get(position);
                            }
                        });
                    }
                    catch (Exception ex){

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
    @Override
    public void onBackPressed() {}
}