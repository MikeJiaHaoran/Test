package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
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
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (myAdapter.getItemViewType(position)) {
                    case MyAdapter.TYPE_LIST:
                    case MyAdapter.TYPE_LIST2:
                        return 4;
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

    class MyAdapter extends RecyclerView.Adapter{

        private static final int TYPE_LIST = 0;
        private static final int TYPE_GRID = 1;
        private static final int TYPE_LIST2 = 2;
        private LayoutInflater inflater;
        private List<String> datas = new ArrayList<>();

        MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            String[] array = {"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8", "a","清华数学老师免费直播课火爆报名中", "北大语文老师免费直播课火爆报名中"
                    , "清华数学老师免费直播课火爆报名中"};
            Collections.addAll(datas, array);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_LIST) {
                View view = inflater.inflate(R.layout.item_listview, parent, false);
                ViewHolderList viewHolderList = new ViewHolderList(view);
                viewHolderList.imageView = view.findViewById(R.id.first);

                return viewHolderList;
            }
            else if (viewType == TYPE_LIST2) {
                View view = inflater.inflate(R.layout.item_listview2, parent, false);
                ViewHolderList2 viewHolderList2 = new ViewHolderList2(view);
                viewHolderList2.imageView1 = view.findViewById(R.id.picture1);
                viewHolderList2.imageView1.setBackgroundResource(R.drawable.picture);
                viewHolderList2.textView1 = view.findViewById(R.id.title1);
                viewHolderList2.imageView2 = view.findViewById(R.id.picture2);
                viewHolderList2.imageView2.setBackgroundResource(R.drawable.picture);
                viewHolderList2.textView2 = view.findViewById(R.id.title2);

                return viewHolderList2;
            }
            else {
                View view = inflater.inflate(R.layout.item_gridview, parent, false);
                ViewHolderGrid viewHolderGrid = new ViewHolderGrid(view);
                viewHolderGrid.imageView = view.findViewById(R.id.iv_grid);
                viewHolderGrid.textView = view.findViewById(R.id.name_grid);

                return viewHolderGrid;
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolderGrid) {
                ViewHolderGrid viewHolderGrid = (ViewHolderGrid) holder;
                viewHolderGrid.textView.setText(datas.get(position));
            }
            if (holder instanceof ViewHolderList2) {
                ViewHolderList2 viewHolderList2 = (ViewHolderList2) holder;
                viewHolderList2.textView1.setText(datas.get(position));
                viewHolderList2.textView2.setText(datas.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public int getItemViewType(int position) {
            if (position == 8) {
                return TYPE_LIST;
            }
            else if (position < 8) {
                return TYPE_GRID;
            }
            else {
                return TYPE_LIST2;
            }
        }

        class ViewHolderList extends RecyclerView.ViewHolder {
            private ImageView imageView;

             ViewHolderList(View itemView) {
                super(itemView);
            }
        }

        class ViewHolderList2 extends RecyclerView.ViewHolder {

            private TextView textView1;
            private TextView textView2;
            private ImageView imageView1;
            private ImageView imageView2;

             ViewHolderList2(@NonNull View itemView) {
                super(itemView);
            }
        }

        class ViewHolderGrid extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView textView;

             ViewHolderGrid(View itemView) {
                super(itemView);
            }
        }

    }
}
