package com.e.matfiziak.Konta;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.Czworokaty;
import com.e.matfiziak.FizykaKalkulator;
import com.e.matfiziak.Grawitacja;
import com.e.matfiziak.Keppler;
import com.e.matfiziak.Kwadrat;
import com.e.matfiziak.MatematykaKalkulator;
import com.e.matfiziak.ModelBohra;
import com.e.matfiziak.Pole_trojkata_rownoramiennego;
import com.e.matfiziak.Pole_trojkata_roznobocznego;
import com.e.matfiziak.Predkosc;
import com.e.matfiziak.Prostokat;
import com.e.matfiziak.Przyszpieszenie;
import com.e.matfiziak.PrzyszpieszenieZwykle;
import com.e.matfiziak.R;
import com.e.matfiziak.Romb;
import com.e.matfiziak.Rownoleglobok;
import com.e.matfiziak.RuchPoOkregu;
import com.e.matfiziak.Szkola;
import com.e.matfiziak.Trapezy;
import com.e.matfiziak.Trojkaty;
import com.e.matfiziak.ForumPackage.Forum;
import com.e.matfiziak.ZasadyDynamiki;
import com.e.matfiziak.inne.User;
import com.e.matfiziak.inne.dane;
import com.e.matfiziak.podFragmenty.StronaGlowna;
import com.e.matfiziak.pole_trojkata;
import com.e.matfiziak.trapez_prostokatny;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email, password;
    Button btn_login;
    TextView forgotPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent incomingIntent = getIntent();
        final String incomingName = incomingIntent.getStringExtra("miejsce");
        final ArrayList incomingList = incomingIntent.getStringArrayListExtra("lista");
        final String incomingBoolean = incomingIntent.getStringExtra("checkbox");
        final String incomingCheckboxPredkosc = incomingIntent.getStringExtra("checkbox2");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        Menu menu = bottomNavigationView.getMenu();
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent i = new Intent(LoginActivity.this, StronaGlowna.class);
                        i.putExtra("miejsce", incomingName);
                        i.putExtra("lista", incomingList);
                        i.putExtra("checkbox",incomingBoolean);
                        i.putExtra("checkbox2", incomingCheckboxPredkosc);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        break;
                    case R.id.navigation_school:
                        Intent i1 = null;
                        if(incomingName.equals("ZasadyDynamiki")){
                            i1 = new Intent(LoginActivity.this, ZasadyDynamiki.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Grawitacja")){
                            i1 = new Intent(LoginActivity.this, Grawitacja.class);
                        }
                        else if(incomingName.equals("Szkola")){
                            i1=new Intent(LoginActivity.this, Szkola.class);
                        }
                        else if(incomingName.equals("Keppler")){
                            i1 = new Intent(LoginActivity.this, Keppler.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("FizykaKalkulator")){
                            i1 = new Intent(LoginActivity.this, FizykaKalkulator.class);
                        }
                        else if(incomingName.equals("MatematykaKalkulator")){
                            i1 = new Intent(LoginActivity.this, MatematykaKalkulator.class);
                        }
                        else if(incomingName.equals("ModelBohra")){
                            i1 = new Intent(LoginActivity.this, ModelBohra.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("poleTrojkata")){
                            i1 = new Intent(LoginActivity.this, pole_trojkata.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Predkosc")){
                            i1 = new Intent(LoginActivity.this, Predkosc.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                            i1.putExtra("checkbox2", incomingCheckboxPredkosc);
                        }
                        else if(incomingName.equals("Przyszpieszenie")){
                            i1 = new Intent(LoginActivity.this, Przyszpieszenie.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("PrzyszpieszenieZwykle")){
                            i1 = new Intent(LoginActivity.this, PrzyszpieszenieZwykle.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("RuchPoOkregu")){
                            i1 = new Intent(LoginActivity.this, RuchPoOkregu.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Pole_trojkata_rownoramiennego")){
                            i1 = new Intent(LoginActivity.this, Pole_trojkata_rownoramiennego.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Pole_trojkata_roznobocznego")){
                            i1 = new Intent(LoginActivity.this, Pole_trojkata_roznobocznego.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Trojkaty")){
                            i1 = new Intent(LoginActivity.this, Trojkaty.class);
                        }
                        else if(incomingName.equals("Kwadrat")){
                            i1 = new Intent(LoginActivity.this, Kwadrat.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Prostokat")){
                            i1 = new Intent(LoginActivity.this, Prostokat.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Romb")){
                            i1 = new Intent(LoginActivity.this, Romb.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Rownoleglobok")){
                            i1 = new Intent(LoginActivity.this, Rownoleglobok.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Czworokaty")){
                            i1 = new Intent(LoginActivity.this, Czworokaty.class);
                        }
                        else if(incomingName.equals("trapezProstokatny")){
                            i1 = new Intent(LoginActivity.this, trapez_prostokatny.class);
                            i1.putExtra("lista", incomingList);
                            i1.putExtra("checkbox", incomingBoolean);
                        }
                        else if(incomingName.equals("Trapezy")){
                            i1 = new Intent(LoginActivity.this, Trapezy.class);
                        }
                        i1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i1);
                        break;
                    case R.id.navigation_favourite:
                        Intent i2 = new Intent(LoginActivity.this, Czat.class);
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
                        Intent i4 = new Intent(LoginActivity.this, Forum.class);
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
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        forgotPass = findViewById(R.id.forgot_password);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if(TextUtils.isEmpty(emailText)||TextUtils.isEmpty(passwordText)){
                    Toast.makeText(LoginActivity.this, "Wszystkie pola wymagane", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, Konto2.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                                if(fUser!=null){
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(fUser.getUid());
                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            User user = dataSnapshot.getValue(User.class);
                                            String username = user.getUsername();
                                            String imageUrl = user.getImageUrl();
                                            dane dane1 = new dane();
                                            dane1.nick = username;
                                            dane1.imageUrl = imageUrl;
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {}
                                    });
                                }
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Niepoprawne dane", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
