package com.sid.todo;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignIn extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button signIn;
    TextInputEditText etMail,Password;
    TextView txtToSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        fAuth = FirebaseAuth.getInstance();
        etMail =  findViewById(R.id.etLoginMail);
        Password =  findViewById(R.id.etLoginPassword);
        signIn = findViewById(R.id.myLoginBtn);
        txtToSignUp = findViewById(R.id.gatewayTextSignUp);






        txtToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this,SignUp.class);
                startActivity(i);
            }

        });



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = etMail.getText().toString();
                String password = Password.getText().toString();
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignIn.this, "Fields Can't be null", Toast.LENGTH_SHORT).show();

                }
                else {
                    logIn(email, password);
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You cannot go back at this moment", Toast.LENGTH_SHORT).show();
    }

    private void logIn(String email, String password) {
        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignIn.this, "Successful", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SignIn.this, MainActivity.class);
                    startActivity(i);


                }
                else
                {
                    Toast.makeText(SignIn.this, "Something Unusual Occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}