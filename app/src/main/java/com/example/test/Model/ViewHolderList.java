package com.example.test.Model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderList extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView title;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;

    public ViewHolderList(@NonNull View itemView) {
        super(itemView);
    }
}
