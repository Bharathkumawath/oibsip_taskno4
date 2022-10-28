package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class result_activity extends AppCompatActivity {
  CircularProgressBar circularProgressBar;
  TextView textView;
  RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView=findViewById(R.id.re);
        textView=findViewById(R.id.text);
        Intent intent=getIntent();
        ArrayList<String> arrayList=intent.getStringArrayListExtra("bharath");
        circularProgressBar=findViewById(R.id.circularProgressBar);
        String mark=arrayList.get(arrayList.size()-1);
        int i=Integer.parseInt(mark);
        circularProgressBar.setProgress(i);

        textView.setText(arrayList.get(arrayList.size()-1)+"/"+"8");
        recycle2 adapter = new recycle2(result_activity.this,arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}