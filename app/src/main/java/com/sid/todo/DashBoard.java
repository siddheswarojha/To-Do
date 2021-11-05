package com.sid.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DashBoard extends AppCompatActivity {
    EditText etSupportQuery, etEmailSupport, Topic;
    Button btnSendSupportQuery;
    FirebaseFirestore fb;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        etSupportQuery = findViewById(R.id.etSupportQuery);
        etEmailSupport = findViewById(R.id.etContactDetail);
        Topic = findViewById(R.id.topic);
        btnSendSupportQuery = findViewById(R.id.sendSupportQuery);

        fAuth = FirebaseAuth.getInstance();



        btnSendSupportQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSupportQuery.getText().toString();
                String topic = Topic.getText().toString();
                String email  = etEmailSupport.getText().toString();


                if(TextUtils.isEmpty(query)||TextUtils.isEmpty(topic)||TextUtils.isEmpty(email))
                {
                    Toast.makeText(DashBoard.this, "Enter Correct Values", Toast.LENGTH_SHORT).show();
                }
                else{
                    SendQuery(query,topic,email);

                }
            }
        });






    }

    private void SendQuery(String query, String topic, String email) {
        fb = FirebaseFirestore.getInstance();
        Map<String, Object> listQuery = new HashMap<>();
        listQuery.put("Query",query);
        listQuery.put("topic",topic);
        listQuery.put("Email",email);


        fb.collection(Objects.requireNonNull(fAuth.getUid())).add(listQuery);
        Intent i = new Intent(DashBoard.this, MainActivity.class);
        startActivity(i);

        Toast.makeText(DashBoard.this, "Successfully Uploaded ", Toast.LENGTH_SHORT).show();


    }

}
