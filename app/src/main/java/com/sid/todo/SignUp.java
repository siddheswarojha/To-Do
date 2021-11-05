package com.sid.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button signUp;
    TextInputEditText etMail,Password,userName;
    TextView txtToSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fAuth = FirebaseAuth.getInstance();
        etMail =  findViewById(R.id.etSignUpMail);
        Password =  findViewById(R.id.etSignUpPassword);
        signUp = findViewById(R.id.btnSignUp);
        userName = findViewById(R.id.etUserName);
        txtToSignIn = findViewById(R.id.gatewayTextSignIn);


        txtToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,SignIn.class);
                startActivity(i);
            }
        });





        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = etMail.getText().toString();
                String password = Password.getText().toString();
                String name = userName.getText().toString();
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignUp.this, "Fields Can't be null", Toast.LENGTH_SHORT).show();

                }
                else {
                    createUser(email, password,name);
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You cannot go back at this moment", Toast.LENGTH_SHORT).show();
    }

    private void createUser(String email, String password,String name) {
        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "Successful", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SignUp.this, SignIn.class);
                    startActivity(i);


                }
                else
                {
                    Toast.makeText(SignUp.this, "Something Unusual Occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }







}