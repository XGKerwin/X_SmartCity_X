package com.example.x_smartcity_x.Fragment.Service;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Activity.MineOrderActivity;
import com.example.x_smartcity_x.Adapter.Mine_adapter;
import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetOrderForm;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Mine_Order extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private List<GetOrderForm> getOrderForms;
    private RecyclerView recyclerView;
    private Mine_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine_order, container, false);
        initView(view);
        titleTitle.setText("个人订单");

        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Mine_S());
            }
        });

        return view;
    }

    private void getOkHttp() {
        if (getOrderForms == null) {
            getOrderForms = new ArrayList<>();
        } else {
            getOrderForms.clear();
        }
        new OKHttpTo()
                .setUrl("getOrderForm")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getOrderForms.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetOrderForm>>() {
                                }.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null){
            adapter = new Mine_adapter(getOrderForms);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(getContext(), MineOrderActivity.class);
                intent.putExtra("init",name);
                startActivity(intent);
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_mine, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
