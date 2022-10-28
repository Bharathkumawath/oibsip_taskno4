package com.example.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle2 extends RecyclerView.Adapter<recycle2.ViewHolder> {
    Context context;
    ArrayList<String> arrayList;
    recycle2(Context context, ArrayList<String> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public recycle2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.result_small,parent,false);
        return new recycle2.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recycle2.ViewHolder holder, int position) {
        holder.textView.setText("Q) "+arrayList.get(position*2));
        holder.textView2.setText("A) "+arrayList.get(position*2+1));
    }

    @Override
    public int getItemCount() {
        return arrayList.size()/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.answer);
            textView2=itemView.findViewById(R.id.question);
        }
    }
}
