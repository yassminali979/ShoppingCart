package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button b;
    EditText email;
    EditText pass_word;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.mail);
        pass_word=findViewById(R.id.pass);
        b=findViewById(R.id.register);
        mAuth= FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String password= pass_word.getText().toString().trim();
                if(mail.isEmpty())
                {
                    email.setError("Email is empty");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                {
                    email.setError("Enter the valid email address");
                    email.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass_word.setError("Enter the password");
                    pass_word.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    pass_word.setError("Length of the password should be more than 6");
                    pass_word.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(Register.this,BirthDate.class ));
                        }
                        else
                        {
                            Toast.makeText(Register.this,"You Have already registerd by this email,Or this is a wrong email??!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

}