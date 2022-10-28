package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    FloatingActionButton fal;
    int marks=0;
    int clicked=0;
    ArrayList<String> ques=new ArrayList<>();
    ArrayList<String> ans=new ArrayList<>();
    int selected=0;
    TextView t1,t2,t3,t4,tq,te;
    TextView exp;
    private BottomSheetBehavior bottomSheetBehavior;
    ImageView i1,i2,i3,i4;
    CardView cardView;
    String quesion,answer,explain;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<ArrayList<String>> arraycom=new ArrayList<>();
    DatabaseReference ref;
    Button button;
    private static String name="bharathkumar";
    int k=1;
    String s=""+k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardView=findViewById(R.id.explaincard);
        setContentView(R.layout.activity_main);
//        LinearLayout linearLayout= findViewById(R.id.bott
//        om_sheet);
//        bottomSheetBehavior=BottomSheetBehavior.from(linearLayout);
//     //   bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        database=FirebaseDatabase.getInstance();
        fal=findViewById(R.id.button);
        t1=findViewById(R.id.aoption);
        exp=findViewById(R.id.litle);
        t2=findViewById(R.id.boption);
        t3=findViewById(R.id.coption);
      //  button=findViewById(R.id.button);
        t4=findViewById(R.id.doption);
        tq=findViewById(R.id.question);
        te=findViewById(R.id.explain);
//       String s="200";
         i1=findViewById(R.id.i1);
        i2=findViewById(R.id.i2);
        i3=findViewById(R.id.i3);
        i4=findViewById(R.id.i4);

        Intent intent=getIntent();
        String a = intent.getStringExtra(MainActivity.name);
     //   int i=Integer.parseInt(s);

        ref= database.getReference(a);
        data();
        fal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               exp.setBackgroundColor(getResources().getColor(R.color.white));
               te.setText(" ");
               if(selected==1){
                   selected=0;
               data();
            }
               else {
                   Toast.makeText(MainActivity.this, "Select option", Toast.LENGTH_SHORT).show();
               }
            }
        });


    }
    public void data(){
        i1.setImageResource(R.drawable.wrong);
        i2.setImageResource(R.drawable.wrong);
        i3.setImageResource(R.drawable.wrong);
        i4.setImageResource(R.drawable.wrong);
        i1.setVisibility(View.INVISIBLE);
        i2.setVisibility(View.INVISIBLE);
        i3.setVisibility(View.INVISIBLE);
        i4.setVisibility(View.INVISIBLE);
        ref.child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                if(snapshot.exists()) {
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        quesion = dataSnapshot.getKey();
                        for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                            if (snapshot1.getKey().equals("answer")) {
                                answer = snapshot1.getValue().toString();
                            } else if (snapshot1.getKey().equals("explain")) {
                                explain = snapshot1.getValue().toString();
                            } else {
                                arrayList.add(snapshot1.getValue().toString());
                            }
                        }
                    }
                    tq.setText(s+") "+quesion);
                    t1.setText(arrayList.get(0));
                    t2.setText(arrayList.get(1));
                    t3.setText(arrayList.get(2));
                    t4.setText(arrayList.get(3));
                    k=k+1;
                    s=""+k;
                }
                else{
                    ques.add(marks+"");
                    Intent intent=new Intent(MainActivity.this,result_activity.class);
                    intent.putStringArrayListExtra("bharath",ques);
                    startActivity(intent);
                    MainActivity.this.finish();
                   // Toast.makeText(MainActivity.this, "no more quesion", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    public void onclick(View view){
        clicked=1;
        selected=1;
        int correc=0;
       switch (view.getId()){
           case R.id.aoption:
               if(t1.getText().equals(answer)){
                   te.setText(explain);

                   i1.setImageResource(R.drawable.right);
                   correc=1;
                   marks++;

               }
               else{
                   te.setText(explain);

               }
               t2.setVisibility(View.INVISIBLE);
               t3.setVisibility(View.INVISIBLE);
               t4.setVisibility(View.INVISIBLE);
               i1.setVisibility(View.VISIBLE);
//               i2.setVisibility(View.INVISIBLE);
//               i3.setVisibility(View.INVISIBLE);
               break;
           case R.id.boption:
               if(t2.getText().equals(answer)){
                   te.setText(explain);
                   i2.setImageResource(R.drawable.right);
                   correc=1;
                   marks++;
               }
               else{
                   te.setText(explain);

               }
               t1.setVisibility(View.INVISIBLE);
               t3.setVisibility(View.INVISIBLE);
               t4.setVisibility(View.INVISIBLE);
               i2.setVisibility(View.VISIBLE);
               break;
           case R.id.coption:
               if(t3.getText().equals(answer)){
                   te.setText(explain);
                   i3.setImageResource(R.drawable.right);
                   correc=1;
                   marks++;

               }
               else{
                   te.setText(explain);

               }
               t1.setVisibility(View.INVISIBLE);
               t2.setVisibility(View.INVISIBLE);
               t4.setVisibility(View.INVISIBLE);
               i3.setVisibility(View.VISIBLE);
               break;
           case R.id.doption:
               if(t4.getText().equals(answer)){
                   te.setText(explain);
                   i4.setImageResource(R.drawable.right);
                   correc=1;
                   marks++;
               }
               else{
                   te.setText(explain);

               }
               t2.setVisibility(View.INVISIBLE);
               t3.setVisibility(View.INVISIBLE);
               t1.setVisibility(View.INVISIBLE);
               i4.setVisibility(View.VISIBLE);
               break;
           default:
               break;

       }
        if (correc==1){
            exp.setBackgroundColor(getResources().getColor(R.color.Green));
        }
        else {
            ques.add(quesion);
            ques.add(answer);
            exp.setBackgroundColor(getResources().getColor(R.color.Red));
        }

//cardView.setVisibility(View.VISIBLE);
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}