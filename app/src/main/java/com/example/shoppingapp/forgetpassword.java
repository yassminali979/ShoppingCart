package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {
    EditText email;
    Button resetpass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        email=findViewById(R.id.enter_email);
        resetpass=findViewById(R.id.reset);
        mAuth=FirebaseAuth.getInstance();
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });
    }
    public void resetpassword()
    {
        String mail=email.getText().toString().trim();
        if(mail.isEmpty())
        {
            email.setError("Email is required?!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            email.setError("Please provide valid Email?!");
            email.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(forgetpassword.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(forgetpassword.this, "Try again?! Something wrong happened!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}