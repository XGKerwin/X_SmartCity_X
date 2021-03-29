package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Bus_wode_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetbusOrder;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Bus_wode extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private TextView btnYizhifu;
    private TextView btnWeizhifu;
    private RecyclerView recyclerview;
    private List<GetbusOrder> getbusOrders;
    private Bus_wode_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bus_wode, container, false);
        initView(view);
        titleTitle.setText("我的订单");


        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Bus());
            }
        });

        btnYizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });

        btnWeizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getbusOrders.clear();
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus,fragment).commit();
    }

    private void getOkHttp() {
        if (getbusOrders == null){
            getbusOrders = new ArrayList<>();
        }else {
            getbusOrders.clear();
        }
        new OKHttpTo()
                .setUrl("getbusOrder")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String name = jsonObject.optString("msg");
                        if (name.equals("操作成功")){
                            getbusOrders.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                    new TypeToken<List<GetbusOrder>>(){}.getType()));
                            show();
                        }else {
                            Toast.makeText(getContext(),"网络失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null){
            adapter = new Bus_wode_adapter(getbusOrders);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);
            recyclerview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        titleWode = View.findViewById(R.id.title_wode);
        btnYizhifu = View.findViewById(R.id.btn_yizhifu);
        btnWeizhifu = View.findViewById(R.id.btn_weizhifu);
        recyclerview = View.findViewById(R.id.recyclerview);
    }
}
