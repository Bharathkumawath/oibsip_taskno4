package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class input_question extends AppCompatActivity {
   EditText quiz,num,question,a,b,c,d,answer,explain;
   FirebaseDatabase database;
   DatabaseReference ref;
   Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_question);
        quiz=findViewById(R.id.name);
        num=findViewById(R.id.number);
        question=findViewById(R.id.question);
        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);
        d=findViewById(R.id.d);
        button=findViewById(R.id.button);
        answer=findViewById(R.id.answer);
        explain=findViewById(R.id.explain);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child(quiz.getText().toString()).child(num.getText().toString()).child(question.getText().toString()).child("a").setValue(a.getText().toString());
                ref.child(quiz.getText().toString()).child(num.getText().toString()).child(question.getText().toString()).child("b").setValue(b.getText().toString());
                ref.child(quiz.getText().toString()).child(num.getText().toString()).child(question.getText().toString()).child("c").setValue(c.getText().toString());
                ref.child(quiz.getText().toString()).child(num.getText().toString()).child(question.getText().toString()).child("d").setValue(d.getText().toString());
                ref.child(quiz.getText().toString()).child(num.getText().toString()).child(question.getText().toString()).child("answer").setValue(answer.getText().toString());
                ref.child(quiz.getText().toString()).child(num.getText().toString()).child(question.getText().toString()).child("explain").setValue(explain.getText().toString());

            }
        });

    }
}