package com.ABLGroup.idea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    private EditText emaillog, passlog;
    private Button  btnlogin;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emaillog = findViewById(R.id.logemail);
        passlog = findViewById(R.id.logpass);
        btnlogin = findViewById(R.id.loginbtn);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login(){

        String lemail = emaillog.getText().toString().trim();
        String lpassword = passlog.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(lemail, lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    Boolean condition = firebaseAuth.getCurrentUser().isEmailVerified();
                    if(condition == true){
                        Toast.makeText(Login.this, "You are log in  sucessfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this,"verify your email", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Login.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
