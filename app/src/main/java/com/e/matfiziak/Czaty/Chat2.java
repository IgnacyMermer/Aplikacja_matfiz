package com.e.matfiziak.Czaty;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.matfiziak.Adaptery.MessageAdapter;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.Chat;
import com.e.matfiziak.inne.LadowanieOkna;
import com.e.matfiziak.inne.Photo;
import com.e.matfiziak.inne.User;
import com.e.matfiziak.inne.dane;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import de.hdodenhof.circleimageview.CircleImageView;
import static com.e.matfiziak.inne.dane.listaZdj;
public class Chat2 extends AppCompatActivity {
    CircleImageView profile_image;
    TextView username;
    FirebaseUser fUser;
    public static DatabaseReference reference, reference3;
    Intent intent;
    ImageButton btn_send;
    EditText text_send;
    MessageAdapter messageAdapter;
    public static List<Chat> mChat;
    RecyclerView recyclerView;
    int ilosc=0;
    String userid;
    public static int ktoraKlasa=0;
    public static TextView odpowiedz_tekst;
    public static RelativeLayout odpowiedzLayout;
    private ImageButton usunOdpBtn;
    public static TextView like, liczbaLikow;
    public TextView data;
    public  static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;
    String id, id2;
    TextView zalaczniki;
    RelativeLayout zalacznikiLayout;
    ImageButton anulujZalaczniki;
    public static boolean wywolanieListenera=true;
    public static boolean booleanEventListener=false;
    List<Photo> listaZdjec;
    static RelativeLayout dol;
    int liczba=0;
    @Override
    protected void onResume() {
        super.onResume();
        dane dane1 = new dane();
        if(dane1.gdzieScroll){
            booleanEventListener = false;
            final Czat czat = new Czat();
            recyclerView.setVisibility(View.GONE);
            dol.setVisibility(View.GONE);
            wywolanieListenera=true;
            userid = Integer.toString(czat.getTab()+1);
            final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            final boolean[] pom = {false};
            DatabaseReference referenceUser = FirebaseDatabase.getInstance().getReference("Users");
            referenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        User user = snapshot.getValue(User.class);
                        if(firebaseUser==null){
                            ktoraKlasa=-1;
                        }
                        else{
                            if(user.getId().equals(firebaseUser.getUid())){
                                ktoraKlasa = Integer.parseInt(user.getKlasa());
                                if(ktoraKlasa==czat.getTab()){
                                    pom[0] =true;
                                }
                            }
                        }
                    }
                    if(pom[0]){
                        dol.setVisibility(View.VISIBLE);
                    }
                    else{
                        dol.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
            final LadowanieOkna ladowanieOkna = new LadowanieOkna(Chat2.this);
            ladowanieOkna.startLoadingDialog();
            reference3 = FirebaseDatabase.getInstance().getReference("Chats");
            liczba = 0;
            reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Chat> lista = mChat;
                    mChat.clear();
                    if(dataSnapshot.getChildrenCount()>0) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Chat chat = snapshot.getValue(Chat.class);
                            if (chat != null && userid != null) {
                                if (chat.getReceiver() != null) {
                                    if (chat.getReceiver().equals(userid) && chat.getPosition() == -1) {
                                        mChat.add(chat);
                                    } else if (chat.getReceiver().equals(userid)) {
                                        mChat.add(chat.getPosition(), chat);
                                    }
                                    messageAdapter = new MessageAdapter(Chat2.this, mChat);
                                    recyclerView.setAdapter(messageAdapter);
                                    try {
                                        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(int position) {
                                                mChat.get(position);
                                            }
                                        });
                                    } catch (Exception ex) {
                                        readMessage(userid, false);
                                    }
                                }
                            }
                        }
                        if (mChat.size() == 0) {
                            mChat = lista;
                        }
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Chat chat = snapshot.getValue(Chat.class);
                            if (chat.getReceiver().equals(Integer.toString(czat.getTab() + 1))) {
                                liczba++;
                            }
                        }
                        while (recyclerView.getAdapter().getItemCount() != liczba) {
                        }
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    ladowanieOkna.dismissDialog();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        else {
            dane1.gdzieScroll=true;
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czat2);
        dol = findViewById(R.id.dol);
        ImageView dolaczZnak = findViewById(R.id.dolaczZnak);
        listaZdjec = new ArrayList<>();
        Czat czat = new Czat();
        SharedPreferences sharedPreferences = getSharedPreferences("lista", MODE_PRIVATE);
        final dane dane = new dane();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        sharedPreferences = getSharedPreferences("lista", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        text_send = findViewById(R.id.text_send);
        btn_send = findViewById(R.id.btn_send);
        intent = getIntent();
        Intent intent = getIntent();
        userid = Integer.toString(czat.getTab()+1);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        zalaczniki = findViewById(R.id.zalaczniki);
        zalacznikiLayout = findViewById(R.id.zalacznikiLayout);
        anulujZalaczniki = findViewById(R.id.anulujZalaczniki);
        final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Photo");
        id2 = reference1.push().getKey();
        final dane dane1 = new dane();
        dane1.id = id2;
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = text_send.getText().toString();
                if(!msg.equals("")) {
                    Pattern pattern = Pattern.compile("[Kk]{1,100}[uvUV]{1,100}[r]{1,100}[wvWV]{1,100}[a]{1,100}");
                    Matcher matcher = pattern.matcher(msg);
                    boolean lala = true;
                    if(matcher.matches()){
                        lala=false;
                    }
                    if(lala){
                        id2="";
                        if(odpowiedzLayout.getVisibility()==View.GONE){
                            dane1.odpowiedzPozycja = -1;
                        }
                        wywolanieListenera=true;
                        sendMessage(fUser.getUid(), userid, msg);
                        readMessage(userid, false);
                        id2 = reference1.push().getKey();
                        dane1.id = id2;
                        ilosc=0;
                        zalacznikiLayout.setVisibility(View.GONE);
                        listaZdjec.clear();
                    }
                }
                else {
                    Toast.makeText(Chat2.this, "Nie możesz wysłać pustej wiadomości", Toast.LENGTH_SHORT).show();
                }
                id = null;
                text_send.setText("");
            }
        });
        profile_image = findViewById(R.id.profileImage);
        username = findViewById(R.id.username);
        odpowiedz_tekst = findViewById(R.id.tekst_odpowiedz);
        odpowiedzLayout = findViewById(R.id.odpowiedzLayout);
        usunOdpBtn = findViewById(R.id.usunOdpowiedzBtn);
        data = findViewById(R.id.data);
        like = findViewById(R.id.like);
        liczbaLikow = findViewById(R.id.liczbaLikow);
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        readMessage(userid, false);
        anulujZalaczniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< listaZdj.size(); i++){
                    DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("Photo").child(listaZdj.get(i).getId());
                    reference3.removeValue();
                }
                dane1.listaZdj.clear();
                ilosc = 0;
                zalacznikiLayout.setVisibility(View.GONE);
            }
        });
        usunOdpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                odpowiedz_tekst.setText("");
                odpowiedzLayout.setVisibility(View.GONE);
                dane1.odpowiedz = -1;
                dane1.odpowiedzPozycja = -1;
            }
        });
        dolaczZnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openImage();
                final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Photo");
                reference1.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                        try{
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Photo photo = snapshot.getValue(Photo.class);
                                if(photo.getIdWiad()==null&&photo.getSender()!=null){
                                    if(photo.getSender().equals(fUser.getUid())){
                                        com.e.matfiziak.inne.dane dane1 = new dane();
                                        reference1.child(photo.getId()).child("idWiad").setValue(dane1.id);
                                    }
                                }
                            }
                            throw new Exception("lalala");
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        Query query = FirebaseDatabase.getInstance().getReference("Chats").orderByChild("receiver").startAt(userid).endAt(userid+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(booleanEventListener) {
                    if (recyclerView.getAdapter() != null) {
                        if (recyclerView.getAdapter().getItemCount() != dataSnapshot.getChildrenCount()) {
                            readMessage(userid, true);
                        }
                    }
                }
                else{
                    booleanEventListener = true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
    void sendMessage(String sender, String receiver, String message) {
        booleanEventListener = false;
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()==NetworkInfo.State.CONNECTED||connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        else{
            connected = false;
        }
        if (connected) {
            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
            final dane dane1 = new dane();
            int position=-1;
            if(odpowiedzLayout.getVisibility()!=View.GONE){
                odpowiedzLayout.setVisibility(View.GONE);
                position = dane1.position+1;
            }
            else{
                position=-1;
            }
            LocalDateTime localDateTime = null;
            StringBuilder stringBuilder = new StringBuilder();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                localDateTime = LocalDateTime.now(ZoneId.of("ECT"));
                stringBuilder.append(localDateTime.getYear()+":");
                int a =localDateTime.getMonthValue();
                String a1 = Integer.toString(a);
                if(a1.length()==1){
                    a1 = "0"+a1;
                }
                stringBuilder.append(a1+":");
                int b =localDateTime.getDayOfMonth();
                String b1 = Integer.toString(b);
                if(b1.length()==1){
                    b1 = "0"+b1;
                }
                stringBuilder.append(b1+" ");
                int c =localDateTime.getHour();
                String c1 = Integer.toString(c);
                if(c1.length()==1){
                    c1 = "0"+c1;
                }
                stringBuilder.append(c1+":");
                int d =localDateTime.getMinute();
                String d1 = Integer.toString(d);
                if(d1.length()==1){
                    d1 = "0"+d1;
                }
                stringBuilder.append(d1);
            }
            id = reference2.child("Chats").push().getKey();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", id);
            hashMap.put("sender", sender);
            hashMap.put("receiver", receiver);
            hashMap.put("message", message);
            hashMap.put("position", position);
            hashMap.put("odpowiedz", dane1.odpowiedz);
            hashMap.put("data", stringBuilder.toString());
            hashMap.put("like", 0);
            hashMap.put("zdjecie", dane1.id);
            hashMap.put("iloscZdjec", ilosc);
            List<String> users = new ArrayList<>();
            users.add(fUser.getUid());
            hashMap.put("lista", users);
            dane1.odpowiedz = -1;
            reference2.child("Chats").child(id).setValue(hashMap);
        }
        else {
            Toast.makeText(Chat2.this, "Nie jesteś podłączony do sieci", Toast.LENGTH_SHORT).show();
        }
    }
    private void readMessage(final String userID , final boolean pomocnik) {
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        final dane dane1 = new dane();
        Parcelable recyclerViewState = null;
        if(pomocnik){
            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        }
        final Parcelable finalRecyclerViewState = recyclerViewState;
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(wywolanieListenera) {
                    List<Chat> lista = mChat;
                    mChat.clear();
                    if (dataSnapshot.getChildrenCount() > 0) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Chat chat = snapshot.getValue(Chat.class);
                            if (chat != null && userid != null) {
                                if (chat.getReceiver() != null) {
                                    if (chat.getReceiver().equals(userid) && chat.getPosition() == -1) {
                                        mChat.add(chat);
                                    } else if (chat.getReceiver().equals(userid)) {
                                        mChat.add(chat.getPosition(), chat);
                                    }
                                    messageAdapter = new MessageAdapter(Chat2.this, mChat);
                                    recyclerView.setAdapter(messageAdapter);
                                    try {
                                        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(int position) {
                                                mChat.get(position);
                                            }
                                        });
                                    } catch (Exception ex) {
                                        readMessage(userid, false);
                                    }
                                }
                            }
                        }
                        if (mChat.size() == 0) {
                            mChat = lista;
                        }
                        if (dane1.odpowiedzPozycja != -1) {
                            if (dane1.odpowiedzPozycja >= 0) {
                                if (mChat.size() > dane1.odpowiedzPozycja + 3 && dane1.gdzieScroll) {
                                    dane1.odpowiedzPozycja += 3;
                                    dane1.gdzieScroll = false;
                                } else if (mChat.size() > dane1.odpowiedzPozycja + 1 && dane1.gdzieScroll) {
                                    dane1.odpowiedzPozycja += 1;
                                    dane1.gdzieScroll = false;
                                }
                                recyclerView.scrollToPosition(dane1.odpowiedzPozycja);
                            }
                        }
                        View keyboard = getCurrentFocus();
                        if (keyboard != null) {
                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(keyboard.getWindowToken(), 0);
                        }
                        if (pomocnik) {
                            recyclerView.getLayoutManager().onRestoreInstanceState(finalRecyclerViewState);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
    private void openImage(){
        if(ilosc<5){
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(i, IMAGE_REQUEST);
        }
        else{
            Toast.makeText(Chat2.this, "Nie możesz załączyć więcej niż 5 zdjęć", Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(Chat2.this);
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
                        final String id = reference.child("Photo").push().getKey();
                        map.put("id",id);
                        map.put("sender", fUser.getUid());
                        reference.child("Photo").child(id).setValue(map);
                        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Photo");
                        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Photo photo = snapshot.getValue(Photo.class);
                                    if(photo.getId().equals(id)) {
                                        listaZdjec.add(photo);
                                        listaZdj = listaZdjec;
                                        break;
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                        ilosc++;
                        zalacznikiLayout.setVisibility(View.VISIBLE);
                        if(ilosc==1){
                            zalaczniki.setText("Dołączyłes "+ilosc+" załącznik");
                        }
                        else if(ilosc%10>1&&ilosc%10<5){
                            zalaczniki.setText("Dołączyłes "+ilosc+" załączniki");
                        }
                        else{
                            zalaczniki.setText("Dołączyłes "+ilosc+" załączników");
                        }
                        pd.dismiss();
                    }
                    else{
                        Toast.makeText(Chat2.this, "Błąd", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Chat2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(Chat2.this, "Brak wybranego zdjęcia", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null) {
            imageUri = data.getData();
            if (uploadTask != null && uploadTask.isInProgress()) {
                Toast.makeText(Chat2.this, "ładowanie...", Toast.LENGTH_SHORT).show();
            } else {
                uploadImage();
            }
        }
    }
}