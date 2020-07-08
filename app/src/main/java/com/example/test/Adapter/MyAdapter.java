package com.example.test.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.Model.ViewHolderGrid;
import com.example.test.Model.ViewHolderList;
import com.example.test.Model.ViewHolderList2;
import com.example.test.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter{

    public static final int TYPE_LIST = 0;
    public static final int TYPE_GRID = 1;
    public static final int TYPE_LIST2 = 2;
    private LayoutInflater inflater;
    private List<Integer> datas = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        Integer [] array = {R.array.star, R.array.course1, R.array.course2, R.array.course3, R.array.content1, R.array.content2, R.array.content3, R.array.content4
                , R.array.content5, R.array.content6, R.array.content7, R.array.content8, R.array.content9};
        Integer [] image = {R.drawable.star, R.drawable.panda, R.drawable.doctor, R.drawable.panda, R.drawable.haiyang, R.drawable.ernianji, R.drawable.wuxu,
                R.drawable.kehou, R.drawable.loushuaiyi, R.drawable.dawulijiang, R.drawable.xiaoshengchu, R.drawable.shenmibiancheng, R.drawable.xueersibiancheng};
        Collections.addAll(datas, array);
        Collections.addAll(images, image);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LIST) {
            View view = inflater.inflate(R.layout.item_listview, parent, false);
            ViewHolderList viewHolderList = new ViewHolderList(view);
            viewHolderList.imageView = view.findViewById(R.id.list_view1_image);
            viewHolderList.title = view.findViewById(R.id.list_view1_title);
            viewHolderList.textView1 = view.findViewById(R.id.list_view1_first);
            viewHolderList.textView2 = view.findViewById(R.id.list_view1_second);
            viewHolderList.textView3 = view.findViewById(R.id.list_view1_third);
            viewHolderList.textView4 = view.findViewById(R.id.list_view1_fourth);
            viewHolderList.textView5 = view.findViewById(R.id.list_view1_money);
            viewHolderList.textView6 = view.findViewById(R.id.list_view1_old_money);
            return viewHolderList;
        }
        else if (viewType == TYPE_LIST2) {
            View view = inflater.inflate(R.layout.item_listview2, parent, false);
            ViewHolderList2 viewHolderList2 = new ViewHolderList2(view);
            viewHolderList2.textView1 = view.findViewById(R.id.list_view2_tv1);
            viewHolderList2.textView2 = view.findViewById(R.id.list_view2_tv2);
            viewHolderList2.textView3 = view.findViewById(R.id.list_view2_tv3);
            viewHolderList2.imageView1 = view.findViewById(R.id.list_view2_image1);
            viewHolderList2.imageView2 = view.findViewById(R.id.list_view2_image2);

            return viewHolderList2;
        }
        else {
            View view = inflater.inflate(R.layout.item_gridview, parent, false);
            ViewHolderGrid viewHolderGrid = new ViewHolderGrid(view);
            viewHolderGrid.imageView = view.findViewById(R.id.iv_grid);
            viewHolderGrid.textView1 = view.findViewById(R.id.first_grid);
            viewHolderGrid.textView2 = view.findViewById(R.id.second_grid);
            viewHolderGrid.textView3 = view.findViewById(R.id.third_grid);

            return viewHolderGrid;
        }

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderGrid) {
            ViewHolderGrid viewHolderGrid = (ViewHolderGrid) holder;
            String[] star = context.getResources().getStringArray(datas.get(position));
            Glide.with(context).load(images.get(position)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolderGrid.imageView);
            String str = "<font color = 'red'>"+"学而思"+"</font>" + "大明星";
            viewHolderGrid.textView1.setText(Html.fromHtml(str));
            viewHolderGrid.textView2.setText(star[2]);
            viewHolderGrid.textView3.setText(star[3]);
        }
        if (holder instanceof ViewHolderList2) {
            ViewHolderList2 viewHolderList2 = (ViewHolderList2) holder;
            String[] content = context.getResources().getStringArray(datas.get(position));
            Glide.with(context).load(images.get(position)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolderList2.imageView1);
            Glide.with(context).load(images.get(position)).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(viewHolderList2.imageView2);
            viewHolderList2.textView1.setText(content[0]);
            viewHolderList2.textView2.setText(content[1]);
            viewHolderList2.textView3.setText(content[2]);
        }
        if (holder instanceof ViewHolderList) {
            ViewHolderList viewHolderList = (ViewHolderList) holder;
            String[] course = context.getResources().getStringArray(datas.get(position));
            //viewHolderList.imageView.setImageResource(images.get(position));
            Glide.with(context).load(images.get(position)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolderList.imageView);
            viewHolderList.title.setText(course[6]);
            viewHolderList.textView1.setText(course[1]);
            viewHolderList.textView2.setText(course[2]);
            viewHolderList.textView3.setText(course[3]);
            viewHolderList.textView4.setText(course[4]);
            viewHolderList.textView5.setText(String.format("%s%s", getYen(), course[5]));
            if (course.length == 8) {
                viewHolderList.textView6.setText(String.format("%s%s", getYen(), course[7]));
                viewHolderList.textView6.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }

    //¥ 获取人民币符号
    private String getYen(){
        return String.valueOf(Html.fromHtml("&yen"));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public int getItemViewType(int position) {
        if (position < 4 && position > 0) {
            return TYPE_LIST;
        }
        else if (position == 0) {
            return TYPE_GRID;
        }
        else {
            return TYPE_LIST2;
        }
    }

}
