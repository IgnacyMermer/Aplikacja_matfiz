package com.e.matfiziak.Konta;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.R;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password;
    Button btn_register;
    FirebaseAuth auth;
    DatabaseReference reference;
    int pozycja=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Intent incomingIntent = getIntent();
        final String incomingName = incomingIntent.getStringExtra("miejsce");
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        auth = FirebaseAuth.getInstance();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(RegisterActivity.this, StronaGlowna.class);
                        i.putExtra("miejsce", incomingName);
                        i.putExtra("lista", incomingList);
                        i.putExtra("checkbox",incomingBoolean);
                        i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
                        Intent i1 = funkcjePrzelicznikowe.dolneMenu(incomingName,RegisterActivity.this, incomingList, incomingBoolean, incomingCheckboxPredkosc);
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(RegisterActivity.this, Czat.class);
                        i2.putExtra("miejsce", incomingName);
                        i2.putExtra("lista", incomingList);
                        i2.putExtra("checkbox",incomingBoolean);
                        i2.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i2);
                        break;
                    case R.id.navigation_account:
                        break;
                    case R.id.navigation_settings:
                        Intent i4 = new Intent(RegisterActivity.this, Forum.class);
                        i4.putExtra("miejsce", incomingName);
                        i4.putExtra("lista", incomingList);
                        i4.putExtra("checkbox",incomingBoolean);
                        i4.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i4);
                        break;
                }
                return false;
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if(TextUtils.isEmpty(usernameText)||TextUtils.isEmpty(emailText)||TextUtils.isEmpty(passwordText)){
                    Toast.makeText(RegisterActivity.this, "Wszystkie pola są wymagane", Toast.LENGTH_SHORT);
                }
                else if(passwordText.length()<6){
                    Toast.makeText(RegisterActivity.this, "Hasło musi mieć co najmniej 6 znaków", Toast.LENGTH_SHORT);
                }
                else{
                    register(usernameText, emailText, passwordText, pozycja);
                }
            }
        });
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.klasy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pozycja=position+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    void register(final String username, String email, final String password, final int pozycja){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser!=null;
                    String userId = firebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                    HashMap<String ,String> hashMap = new HashMap<>();
                    hashMap.put("id", userId);
                    hashMap.put("username", username);
                    hashMap.put("imageURL", "default");
                    hashMap.put("status", "offline");
                    hashMap.put("klasa", Integer.toString(pozycja));
                    hashMap.put("search", username.toLowerCase());
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this, Konto2.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Błędny login lub hasło", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}