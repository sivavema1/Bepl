package com.example.subhashmorla.bepl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignin;
    private EditText editTextEmail,editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        firebaseauth= FirebaseAuth.getInstance();
        if(firebaseauth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        progressDialog= new ProgressDialog(this);
        buttonSignin=(Button)findViewById(R.id.regbtn1);
        editTextEmail=(EditText)findViewById(R.id.email1);
        editTextPassword=(EditText)findViewById(R.id.pword1);
        textViewSignup=(TextView)findViewById(R.id.textview1);
        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }
    private void userLogin(){

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter Email Id", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();


                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==buttonSignin)
            userLogin();
        if(view== textViewSignup){
            startActivity(new Intent(this,Login.class));
        }
    }
}
