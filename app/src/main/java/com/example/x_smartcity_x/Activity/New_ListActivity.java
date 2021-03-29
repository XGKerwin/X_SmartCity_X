package com.example.x_smartcity_x.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.New_List_Adapter;
import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class New_ListActivity extends AppCompatActivity {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private String s;
    private List<GetNewsAll> getNewsAlls,allList;
    private static final String TAG = "New_ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__list);
        initView();
        s = getIntent().getStringExtra("init");
        titleTitle.setText(s);

        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getOkHttp() {
        if (getNewsAlls == null){
            getNewsAlls = new ArrayList<>();
        }else {
            getNewsAlls.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsAll")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getNewsAlls.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetNewsAll>>(){}.getType()));
                        showData();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showData() {
        if (allList == null){
            allList = new ArrayList<>();
        }else {
            allList.clear();
        }

        for (GetNewsAll all : getNewsAlls){
            if (all.getTheme().equals(s)){
                allList.add(all);
            }
        }
        New_List_Adapter adapter = new New_List_Adapter(allList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(New_ListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(New_ListActivity.this,NewsActivity.class);
                intent.putExtra("init",name);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        recyclerView = findViewById(R.id.recyclerView);
    }
}