package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Adapter.MyAdapter;

public class MainActivity extends Activity {

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
        setMyAdapter();
    }

    protected void init() {
        tv_title = findViewById(R.id.tv_title);
        recyclerView = findViewById(R.id.recycler);
    }

    protected void setMyAdapter() {
        tv_title.setText("学而思网校");
        tv_title.setGravity(Gravity.CENTER);
        myAdapter = new MyAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       /* final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (myAdapter.getItemViewType(position)) {
                    case MyAdapter.TYPE_LIST:
                    case MyAdapter.TYPE_LIST2:
                        return 1;
                    case MyAdapter.TYPE_GRID:
                        return 1;
                    default:
                        return -1;
                }
            }
        });*/

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }
}
