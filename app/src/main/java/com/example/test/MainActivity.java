package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("学而思网校");
        tv_title.setGravity(Gravity.CENTER);

        recyclerView = findViewById(R.id.recycler);
        myAdapter = new MyAdapter(this);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (myAdapter.getItemViewType(position)) {
                    case MyAdapter.TYPE_LIST:
                    case MyAdapter.TYPE_LIST2:
                    case MyAdapter.TYPE_GRID:
                        return 1;
                    default:
                        return -1;
                }
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            // 表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            // 不使用缓存
             conn.setUseCaches(false);
            // 这句可有可无，没有影响
             conn.connect();
            // 得到数据流
             InputStream is = conn.getInputStream();
            // 解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            // 关闭数据流
             is.close();
             }catch(Exception e){
             e.printStackTrace();
             }
             return bitmap;

    }


    class MyAdapter extends RecyclerView.Adapter{

        private static final int TYPE_LIST = 0;
        private static final int TYPE_GRID = 1;
        private static final int TYPE_LIST2 = 2;
        private LayoutInflater inflater;
        private List<Integer> datas = new ArrayList<>();
        private List<Integer> images = new ArrayList<>();

        MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            Integer [] array = {R.array.star, R.array.course1, R.array.course2, R.array.course3, R.array.content1, R.array.content2, R.array.content3, R.array.content4
            , R.array.content5, R.array.content6, R.array.content7, R.array.content8, R.array.content9};
            Integer [] image = {R.drawable.star, R.drawable.panda, R.drawable.doctor, R.drawable.panda, R.drawable.haiyang, R.drawable.ernianji, R.drawable.wuxu,
            R.drawable.kehou, R.drawable.loushuaiyi, R.drawable.dawulijiang, R.drawable.xiaoshengchu, R.drawable.shenmibiancheng, R.drawable.xueersibiancheng};
            Collections.addAll(datas, array);
            Collections.addAll(images, image);
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

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolderGrid) {
                ViewHolderGrid viewHolderGrid = (ViewHolderGrid) holder;
                String[] star = getResources().getStringArray(datas.get(position));
                //Bitmap bitmap = getHttpBitmap(star[0]);
                //viewHolderGrid.imageView.setImageBitmap(bitmap);
                viewHolderGrid.imageView.setImageResource(images.get(position));
                String str = "<font color = 'red'>"+"学而思"+"</font>" + "大明星";
                viewHolderGrid.textView1.setText(Html.fromHtml(str));
                viewHolderGrid.textView2.setText(star[2]);
                viewHolderGrid.textView2.setBackgroundResource(R.color.moccasin);
                viewHolderGrid.textView3.setText(star[3]);
            }
            if (holder instanceof ViewHolderList2) {
                ViewHolderList2 viewHolderList2 = (ViewHolderList2) holder;
                String[] content = getResources().getStringArray(datas.get(position));
                viewHolderList2.imageView1.setImageResource(images.get(position));
                viewHolderList2.imageView2.setImageResource(images.get(position));
                viewHolderList2.textView1.setText(content[0]);
                viewHolderList2.textView2.setText(content[1]);
                viewHolderList2.textView3.setText(content[2]);
            }
            if (holder instanceof ViewHolderList) {
                ViewHolderList viewHolderList = (ViewHolderList) holder;
                String[] course = getResources().getStringArray(datas.get(position));
                viewHolderList.imageView.setImageResource(images.get(position));
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
        public String getYen(){
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

        class ViewHolderList extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView title;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;
            private TextView textView4;
            private TextView textView5;
            private TextView textView6;

             ViewHolderList(View itemView) {
                super(itemView);
            }
        }

        class ViewHolderList2 extends RecyclerView.ViewHolder {

            private TextView textView1;
            private TextView textView2;
            private TextView textView3;
            private ImageView imageView1;
            private ImageView imageView2;

             ViewHolderList2(@NonNull View itemView) {
                super(itemView);
            }
        }

        class ViewHolderGrid extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;

             ViewHolderGrid(View itemView) {
                super(itemView);
            }
        }

    }
}
