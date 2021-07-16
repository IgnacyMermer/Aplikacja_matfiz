package com.e.matfiziak.Konta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.e.matfiziak.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    EditText send_email;
    Button btn_send;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        send_email = findViewById(R.id.send_email);
        btn_send = findViewById(R.id.btn_reset);
        firebaseAuth = FirebaseAuth.getInstance();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = send_email.getText().toString();
                if(email.equals("")){
                    Toast.makeText(ForgotPassActivity.this, "Wpisz adres e-mail", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPassActivity.this, "Sprawdź swój adres e-mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
                            }
                            else{
                                String error = task.getException().getMessage().toString();
                                Toast.makeText(ForgotPassActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
