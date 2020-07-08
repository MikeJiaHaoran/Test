package com.example.test.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderGrid extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public ViewHolderGrid(@NonNull View itemView) {
        super(itemView);
    }
}
