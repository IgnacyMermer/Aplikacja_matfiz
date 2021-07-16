package com.e.matfiziak.ForumPackage;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.e.matfiziak.Konta.Konto;
import com.e.matfiziak.R;
import com.e.matfiziak.Szkola;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.inne.PhotoForum;
import com.e.matfiziak.inne.User;
import com.e.matfiziak.inne.WyswietlanieZdjecia;
import com.e.matfiziak.inne.dane;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.e.matfiziak.inne.dane.listaZdjForum;

public class DodajOdpowiedz extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mDrawerToogle;
    private String mActivityTitle;
    private String[] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;
    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;
    DatabaseReference reference;
    FirebaseUser fUser;
    int ilosc=0;
    dane dane1;
    String idPost="";
    List<PhotoForum> listaZdjec;
    ImageButton pierwszeZdj, drugieZdj, trzecieZdj, czwarteZdj, piateZdj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_odpowiedz);
        dane1 = new dane();
        dane1.iloscZdjecForum=0;
        final Intent incomingIntent = getIntent();
        final String incomingName = incomingIntent.getStringExtra("miejsce");
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        idPost = incomingIntent.getStringExtra("id");
        if(idPost==null){
            idPost="";
        }
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
                        Intent i = new Intent(DodajOdpowiedz.this, StronaGlowna.class);
                        i .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.putExtra("miejsce", incomingName);
                        i.putExtra("lista", incomingList);
                        i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i.putExtra("checkbox", incomingBoolean);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i1 = funkcjePrzelicznikowe.dolneMenu(incomingName, DodajOdpowiedz.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(DodajOdpowiedz.this, Czat.class);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i2.putExtra("miejsce", incomingName);
                        i2.putExtra("lista", incomingList);
                        i2.putExtra("checkbox", incomingBoolean);
                        i2.putExtra("checkbox2", incomingCheckboxPredkosc);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        Intent i3 = new Intent(DodajOdpowiedz.this, Konto.class);
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


        mdrawerLayout = findViewById(R.id.drawerDodajOdp_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        listHeaderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(DodajOdpowiedz.this, Konto.class);
                startActivity(i);
                Animatoo.animateFade(DodajOdpowiedz.this);
                return false;
            }
        });

        pierwszeZdj = findViewById(R.id.pierwszeZdjecie);
        pierwszeZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost()==null&&photoForum.getImageURL()!=null){
                                Intent i = new Intent(DodajOdpowiedz.this, WyswietlanieZdjecia.class);
                                i.putExtra("id", photoForum.getImageURL());
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
        drugieZdj = findViewById(R.id.drugieZdjecie);
        drugieZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                final int liczba[]={0};
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost()==null&&photoForum.getImageURL()!=null){
                                if(liczba[0]==1){
                                    Intent i = new Intent(DodajOdpowiedz.this, WyswietlanieZdjecia.class);
                                    i.putExtra("id", photoForum.getImageURL());
                                    startActivity(i);
                                    break;
                                }
                                else{
                                    liczba[0]++;
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        trzecieZdj = findViewById(R.id.trzecieZdjecie);
        trzecieZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                final int liczba[]={0};
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost()==null&&photoForum.getImageURL()!=null){
                                if(liczba[0]==2){
                                    Intent i = new Intent(DodajOdpowiedz.this, WyswietlanieZdjecia.class);
                                    i.putExtra("id", photoForum.getImageURL());
                                    startActivity(i);
                                    break;
                                }
                                else{
                                    liczba[0]++;
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        czwarteZdj = findViewById(R.id.czwarteZdjecie);
        czwarteZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                final int liczba[]={0};
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost()==null&&photoForum.getImageURL()!=null){
                                if(liczba[0]==3){
                                    Intent i = new Intent(DodajOdpowiedz.this, WyswietlanieZdjecia.class);
                                    i.putExtra("id", photoForum.getImageURL());
                                    startActivity(i);
                                    break;
                                }
                                else{
                                    liczba[0]++;
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        piateZdj = findViewById(R.id.piateZdjecie);
        piateZdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForum");
                final int liczba[]={0};
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost()==null&&photoForum.getImageURL()!=null){
                                if(liczba[0]==4){
                                    Intent i = new Intent(DodajOdpowiedz.this, WyswietlanieZdjecia.class);
                                    i.putExtra("id", photoForum.getImageURL());
                                    startActivity(i);
                                    break;
                                }
                                else{
                                    liczba[0]++;
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        final EditText tytul = findViewById(R.id.odpowiedz);
        final EditText dodatkoweInf = findViewById(R.id.dodatkoweInf);
        Button btn_opublikuj = findViewById(R.id.btnOpublikuj);
        btn_opublikuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((tytul.getText().toString().equals(""))) {
                    Toast.makeText(DodajOdpowiedz.this, "Musisz podać odpowiedz", Toast.LENGTH_SHORT).show();
                }
                else{
                    final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                    final String id = reference1.push().getKey();
                    final int liczba[]={0};
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                                if(photoForum.getIdPost()==null&&photoForum.getImageURL()!=null&&photoForum.getSender().equals(fUser.getUid())){
                                    reference1.child(photoForum.getId()).child("idPost").setValue(id);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {}
                    });
                    DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("Users");
                    reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String nick="", imageurl="";
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                User user = snapshot.getValue(User.class);
                                if(user.getId().equals(fUser.getUid().toString())){
                                    nick = user.getUsername();
                                    imageurl = user.getImageUrl();
                                    break;
                                }
                            }
                            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("ForumOdpowiedzi");
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", id);
                            hashMap.put("odpowiedz", tytul.getText().toString());
                            hashMap.put("sender", fUser.getUid());
                            hashMap.put("dodatkoweInf", dodatkoweInf.getText().toString());
                            hashMap.put("username", nick);
                            hashMap.put("imageURL", imageurl);
                            if(dane1.idPost==null){
                                dane1.idPost="";
                            }
                            hashMap.put("idPost", dane1.idPost);
                            hashMap.put("iloscLike", "0");
                            hashMap.put("iloscZdjec", dane1.iloscZdjecForum);
                            List<String> lista = new ArrayList<>();
                            lista.add(fUser.getUid());
                            hashMap.put("listaLajkujacych",lista);
                            reference2.child(id).setValue(hashMap);
                            final DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("Forum").child(dane1.idPost).child("liczbaKomentarzy");
                            reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String liczbaStr = dataSnapshot.getValue(String.class);
                                    if(liczbaStr==null){
                                        reference4.setValue("1");
                                    }
                                    else if(liczbaStr.equals("0")||liczbaStr.equals("")){
                                        reference4.setValue("1");
                                    }
                                    else{
                                        try {
                                            int a = Integer.parseInt(liczbaStr);
                                            a+=1;
                                            reference4.setValue(Integer.toString(a));
                                        }
                                        catch (Exception ex){
                                            reference4.setValue("1");
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {}
                            });
                            onBackPressed();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {}
                    });
                }
            }
        });
        storageReference = FirebaseStorage.getInstance().getReference("ZdjeciaForumOdp");
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Photo");
        String id2 = reference1.push().getKey();
        final dane dane1 = new dane();
        dane1.id = id2;
        ImageButton buttonZalaczniki = findViewById(R.id.dodajZdj);
        buttonZalaczniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });
        listaZdjec = new ArrayList<>();

        expandableListView.addHeaderView(listHeaderView);
        final CircleImageView imageView = listHeaderView.findViewById(R.id.avatar);
        final TextView nick = listHeaderView.findViewById(R.id.name);
        String imageUrl = dane1.imageUrl;
        String nick2 = dane1.nick;
        if(nick2!=null){
            nick.setText(nick2);
        }
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
    private void openImage(){
        if(dane1.iloscZdjecForum<5){
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(i, IMAGE_REQUEST);
        }
        else{
            Toast.makeText(DodajOdpowiedz.this, "Nie możesz załączyć więcej niż 5 zdjęć", Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(DodajOdpowiedz.this);
        pd.setMessage("ładowanie...");
        pd.show();
        if(imageUri!=null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();
                        fUser = FirebaseAuth.getInstance().getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", mUri);
                        final String id = reference.child("PhotoForumOdpowiedzi").push().getKey();
                        map.put("id",id);
                        map.put("sender", fUser.getUid());
                        reference.child("PhotoForumOdpowiedzi").child(id).setValue(map);
                        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                                    if(photoForum.getId().equals(id)) {
                                        listaZdjec.add(photoForum);
                                        listaZdjForum = listaZdjec;
                                        break;
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                        dane1.iloscZdjecForum++;
                        if(dane1.iloscZdjecForum>=1){
                            pierwszeZdj.setVisibility(View.VISIBLE);
                        }
                        if(dane1.iloscZdjecForum>=2){
                            drugieZdj.setVisibility(View.VISIBLE);
                        }
                        if(dane1.iloscZdjecForum>=3){
                            trzecieZdj.setVisibility(View.VISIBLE);
                        }
                        if(dane1.iloscZdjecForum>=4){
                            czwarteZdj.setVisibility(View.VISIBLE);
                        }
                        if(dane1.iloscZdjecForum>=5){
                            piateZdj.setVisibility(View.VISIBLE);
                        }
                        pd.dismiss();
                    }
                    else{
                        Toast.makeText(DodajOdpowiedz.this, "Błąd", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DodajOdpowiedz.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(DodajOdpowiedz.this, "Brak wybranego zdjęcia", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null) {
            imageUri = data.getData();
            if (uploadTask != null && uploadTask.isInProgress()) {
                Toast.makeText(DodajOdpowiedz.this, "ładowanie...", Toast.LENGTH_SHORT).show();
            }
            else {
                uploadImage();
            }
        }
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
                    Intent i = new Intent(DodajOdpowiedz.this, Szkola.class);
                    startActivity(i);
                    Animatoo.animateFade(DodajOdpowiedz.this);
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
            super.onBackPressed();
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
}
