package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class listofquiz extends AppCompatActivity {
     RecyclerView recyclerView;
     ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> arrayList2=new ArrayList<>();
    FirebaseDatabase database;
    int counter=0;
    Timer timer=new Timer();
    ProgressBar progressBar;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofquiz);
        recyclerView=findViewById(R.id.recyclerview);
        database=FirebaseDatabase.getInstance();
        progressBar=findViewById(R.id.progess);
        ref=database.getReference();
        progressBar.setVisibility(View.VISIBLE);

                progressBar.setProgress(1);
        dataaccess();

    }
    public void dataaccess(){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        arrayList.add(dataSnapshot.getKey().toString());
//                        for (DataSnapshot snapshot1:dataSnapshot.getChildren()){
////                            if(snapshot1.exists()) {
////                                if (snapshot1.getKey().equals("id")) {
////                                    arrayList2.add((Integer) snapshot1.getValue());
////                                    Toast.makeText(listofquiz.this, "ok", Toast.LENGTH_SHORT).show();
////                                }
////                            }
//                        }
                    }

                //    recycle adapter = new recycle(listofquiz.this,arrayList,arrayList2);
                    recycle adapter = new recycle(listofquiz.this,arrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}