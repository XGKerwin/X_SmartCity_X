package com.example.x_smartcity_x.Fragment.Service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Activity.BusActivity;
import com.example.x_smartcity_x.Activity.HospitalActivity;
import com.example.x_smartcity_x.Activity.HuoDongActivity;
import com.example.x_smartcity_x.Activity.ParkingActivity;
import com.example.x_smartcity_x.Activity.PensionActivity;
import com.example.x_smartcity_x.Activity.PovertyActivity;
import com.example.x_smartcity_x.Activity.SubwayActivity;
import com.example.x_smartcity_x.Activity.ViolationActivity;
import com.example.x_smartcity_x.Activity.WeatherActivity;
import com.example.x_smartcity_x.Adapter.ServiceAdapter;
import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetServiceAll;
import com.example.x_smartcity_x.bean.Lists;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_service_S extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private View view;
    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private TextView btn4;
    private TextView btn5;
    private TextView btn6;
    private List<GetServiceAll> getServiceAlls,serviceAlls;
    private ServiceAdapter adapter;
    private static final String TAG = "Fragment_service_S";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_s, container, false);
        initView(view);
        titleTitle.setText("服务");
        titleBack.setVisibility(View.GONE);
        getOkHttp();
        btn();


        return view;
    }

    private void getOkHttp() {
        if (getServiceAlls == null){
            getServiceAlls = new ArrayList<>();
        }else {
            getServiceAlls.clear();
        }
        new OKHttpTo()
                .setUrl("getServiceAll")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getServiceAlls.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetServiceAll>>(){}.getType()));
                        shwo(1);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void shwo(int s) {
        if (serviceAlls == null){
            serviceAlls = new ArrayList<>();
        }else {
            serviceAlls.clear();
        }
        for (GetServiceAll all : getServiceAlls){
            if (all.getServiceType() == s){
                serviceAlls.add(all);
            }
        }
        if (adapter == null){
            adapter = new ServiceAdapter(serviceAlls);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                Log.d(TAG, "onClick: "+name);
                if (name.equals("门诊预约")){
                    Intent intent = new Intent(getContext(), HospitalActivity.class);
                    startActivity(intent);
                }else if (name.equals("实时天气")){
                    Intent intent = new Intent(getContext(), WeatherActivity.class);
                    startActivity(intent);
                } else if (name.equals("停车场")){
                    Intent intent = new Intent(getContext(), ParkingActivity.class);
                    startActivity(intent);
                }else if (name.equals("生活缴费")){
                    Intent intent = new Intent(getContext(), FeesActivity.class);
                    startActivity(intent);
                }else if (name.equals("地铁查询")){
                    Intent intent = new Intent(getContext(), SubwayActivity.class);
                    startActivity(intent);
                }else if (name.equals("智慧巴士")){
                    Intent intent = new Intent(getContext(), BusActivity.class);
                    startActivity(intent);
                }else if (name.equals("违章查询")){
                    Intent intent = new Intent(getContext(), ViolationActivity.class);
                    startActivity(intent);
                } else if (name.equals("活动")){
                    Intent intent = new Intent(getContext(), HuoDongActivity.class);
                    startActivity(intent);
                }else if (name.equals("充值中心")){     //扶贫
                    Intent intent = new Intent(getContext(), PovertyActivity.class);
                    startActivity(intent);
                }else if (name.equals("智慧养老")){
                    Intent intent = new Intent(getContext(), PensionActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void btn() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwo(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwo(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwo(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwo(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwo(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shwo(6);
            }
        });
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerView = view.findViewById(R.id.recyclerView);
        btn1 = view.findViewById(R.id.btn_1);
        btn2 = view.findViewById(R.id.btn_2);
        btn3 = view.findViewById(R.id.btn_3);
        btn4 = view.findViewById(R.id.btn_4);
        btn5 = view.findViewById(R.id.btn_5);
        btn6 = view.findViewById(R.id.btn_6);
    }
}
