package com.example.x_smartcity_x.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Hospital_adapter;
import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Gethospitallist;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HospitalActivity extends AppCompatActivity {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private List<Gethospitallist> gethospitallists;
    private Hospital_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        initView();
        titleTitle.setText("门诊预约");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getokHttp();

    }

    private void getokHttp() {
        if (gethospitallists == null){
            gethospitallists = new ArrayList<>();
        }else {
            gethospitallists.clear();
        }

        new OKHttpTo()
                .setUrl("gethospitallist")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        gethospitallists.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<Gethospitallist>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {
        if (adapter == null){
            adapter = new Hospital_adapter(gethospitallists);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HospitalActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(HospitalActivity.this,Hospital_2Activity.class);
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