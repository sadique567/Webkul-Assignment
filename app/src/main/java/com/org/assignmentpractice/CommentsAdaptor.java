package com.org.assignmentpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentsAdaptor extends RecyclerView.Adapter<CommentsAdaptor.MyHolder> {
   ArrayList<CommentsModel> lst;
   Context context;

    public CommentsAdaptor( Context context ,ArrayList<CommentsModel> lst) {
        this.lst = lst;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.comments_cards , parent , false);
        return new MyHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.nameC.setText(lst.get(position).name);
        holder.emailC.setText(lst.get(position).email);
        holder.bodyC.setText(lst.get(position).body);

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    //MyHolder Class
    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameC , emailC  , bodyC;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        nameC = itemView.findViewById(R.id.name_cmnt);
        emailC = itemView.findViewById(R.id.email_cmnt);
        bodyC = itemView.findViewById(R.id.body_cmnt);


    }

    @Override
    public void onClick(View view) {

    }
}
}
