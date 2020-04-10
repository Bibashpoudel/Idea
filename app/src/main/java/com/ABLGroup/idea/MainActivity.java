package com.ABLGroup.idea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button signinbtn;
    private EditText emailtxt, passwordtxt, repasswordtxt;
    private TextView alreadytxt;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =  FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        signinbtn = (Button)findViewById(R.id.signin);
        emailtxt = (EditText) findViewById(R.id.username);
        passwordtxt = (EditText) findViewById(R.id.pass);
        alreadytxt = (TextView) findViewById(R.id.log);
        repasswordtxt = (EditText) findViewById(R.id.pass1);

//        signinbtn.setOnClickListener((View.OnClickListener) this);
//        alreadytxt.setOnClickListener((View.OnClickListener) this);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });

        alreadytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to next page for login
            }
        });

    }
    public void registeruser(){
        String email = emailtxt.getText().toString().trim();
        String password = passwordtxt.getText().toString().trim();
        String password1 = repasswordtxt.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            //display error message
            Toast.makeText(this, "Please enter Your Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //dislay error
            Toast.makeText(this, "Please enter Your Password",Toast.LENGTH_SHORT).show();
            return;
        }

//        if(!password.equals(password1)){
//            Toast.makeText(this, "Password Not match", Toast.LENGTH_SHORT).show();
//            return;
//        }

        //if validation are correct

        progressDialog.setMessage("You are register successfully");
        progressDialog.show();



        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
                            //user is sucessfullllllly register and login using startactivity

                            Toast.makeText(MainActivity.this,"Register sucessfully",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Register Failed... Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }




//    public void onClick(View view){
//        if(view == signinbtn ){
//            registeruser();
//        }
//        if(view == alreadytxt){
//            //send to login dashboard
//        }
//
//    }


}
