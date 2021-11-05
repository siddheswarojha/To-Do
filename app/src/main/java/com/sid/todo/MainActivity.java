
package com.sid.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    FirebaseFirestore fb;

    FirestoreRecyclerAdapter adapter;
    FloatingActionButton fabAdd;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.RV_QUERY);
        fabAdd = findViewById(R.id.fabAdd);
        fAuth=FirebaseAuth.getInstance();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DashBoard.class);
                startActivity(i);
            }
        });


        fb = FirebaseFirestore.getInstance();
        Query query = fb.collection(Objects.requireNonNull(fAuth.getUid()));
        FirestoreRecyclerOptions<QueryModel> options = new FirestoreRecyclerOptions.Builder<QueryModel>()
                .setQuery(query, QueryModel.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<QueryModel, MainActivity.ProductViewHolder>(options) {
            @NonNull
            @Override
            public MainActivity.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.design_query, parent, false);
                return new MainActivity.ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final MainActivity.ProductViewHolder holder, int position, @NonNull QueryModel model) {

                holder.topic.setText(model.getTopic());
                holder.txtQuery.setText(model.getQuery());
                holder.name.setText(model.getEmail());


            }
        };
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuery, topic,name;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuery = itemView.findViewById(R.id.ActualQuery);
            topic = itemView.findViewById(R.id.topicOfQuery);
            name = itemView.findViewById(R.id.nameOfQueryPerson);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
