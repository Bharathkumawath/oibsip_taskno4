package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle extends RecyclerView.Adapter<recycle.ViewHolder> {
    Context context;
    ArrayList<String> arrayList;
    int[] images={R.drawable.business,R.drawable.geography,R.drawable.personality,R.drawable.history,
            R.drawable.science,R.drawable.tech};
    private static String name="bharathkumar";
    recycle(Context context, ArrayList<String> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public recycle.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.smallunit,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recycle.ViewHolder holder, int position) {
       holder.textView.setText(arrayList.get(position));

//       String s="R.drawable.ai";
//           int i=Integer.parseInt(s);
      holder.imageView.setImageResource(images[position]);
       holder.text2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context,MainActivity.class);
               intent.putExtra(name,holder.textView.getText());
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,text2;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textview);
            text2=itemView.findViewById(R.id.start);
        }
    }
}
