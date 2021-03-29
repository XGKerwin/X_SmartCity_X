package com.example.x_smartcity_x.Fragment.Service;

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

import com.example.x_smartcity_x.Adapter.Fees_zixun_adapter;
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

public class Fragment_fees_zixun extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private List<GetNewsAll> getNewsAlls;
    private Fees_zixun_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fees_zixun, container, false);
        initView(view);
        titleTitle.setText("相关资讯");


        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Fees());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_fees,fragment).commit();
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
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null){
            adapter = new Fees_zixun_adapter(getNewsAlls);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
