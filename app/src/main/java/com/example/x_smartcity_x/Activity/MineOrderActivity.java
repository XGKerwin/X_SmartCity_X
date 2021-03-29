package com.example.x_smartcity_x.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetOrderDetails;
import com.example.x_smartcity_x.bean.Mine_Order_adapter;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MineOrderActivity extends AppCompatActivity {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private String id;
    private List<GetOrderDetails> getOrderDetails;
    private Mine_Order_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_order);
        initView();
        id = getIntent().getStringExtra("init");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleTitle.setText("订单详情");
        getOkHtto();

    }

    private void getOkHtto() {
        if (getOrderDetails == null){
            getOrderDetails = new ArrayList<>();
        }else {
            getOrderDetails.clear();
        }
        new OKHttpTo()
                .setUrl("getOrderDetails")
                .setType("get")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getOrderDetails.addAll(new Gson().fromJson(jsonObject.optJSONArray("data").toString(),
                                new TypeToken<List<GetOrderDetails>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {
        if (adapter == null){
            adapter = new Mine_Order_adapter(getOrderDetails);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MineOrderActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }



    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        recyclerView = findViewById(R.id.recyclerView);
    }
}