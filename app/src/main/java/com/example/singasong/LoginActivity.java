package com.example.singasong;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    Button btnsignup,btnsignin;
    EditText emailtext,passwordtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignin = (Button)findViewById(R.id.email_signin_button);
        btnsignup = (Button) findViewById(R.id.email_signup_button);
        auth = FirebaseAuth.getInstance();
        emailtext = (EditText)findViewById(R.id.email_edittext);
        passwordtext = (EditText)findViewById(R.id.password_edittext);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEmail();
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinEmail();
            }
        });


    }


    private void createEmail(){

        auth.createUserWithEmailAndPassword(emailtext.getText().toString(),passwordtext.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"회원가입 성공",Toast.LENGTH_SHORT).show();
                            moveMainPage(auth.getCurrentUser());
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signinEmail(){

        auth.signInWithEmailAndPassword(emailtext.getText().toString(),passwordtext.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                            moveMainPage(auth.getCurrentUser());
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    void moveMainPage(FirebaseUser user){

        if(user != null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }
}
