package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mail;
    EditText pass_word;
    FirebaseAuth mAuth;
    TextView forgetpass;
    Button n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail=findViewById(R.id.email);
        pass_word=findViewById(R.id.password);
        Button login = findViewById(R.id.Login);
        Button register = findViewById(R.id.Register);
        forgetpass=findViewById(R.id.Forget);
        n=findViewById(R.id.location);
         mAuth=FirebaseAuth.getInstance();
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Login.this,forgetpassword.class ));
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Login.this,MapsActivity.class ));
            }
        });
        register.setOnClickListener(v -> startActivity(new Intent(Login.this,Register.class )));
        login.setOnClickListener(v -> {
            String email= mail.getText().toString().trim();
            String password=pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                mail.setError("Email is empty");
                mail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                mail.setError("Enter the valid email");
                mail.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                pass_word.setError("Length of password is more than 6");
                pass_word.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(Login.this,"You are successfully Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ClothesActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Login.this, "Please Check Your login Credentials", Toast.LENGTH_SHORT).show();
                }

            });
       });
    }
}
