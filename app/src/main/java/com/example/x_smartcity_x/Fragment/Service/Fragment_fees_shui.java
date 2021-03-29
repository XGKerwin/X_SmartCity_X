package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.util.Log;
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

import com.example.x_smartcity_x.Adapter.Fees_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.ChargeList;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_fees_shui extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private String s;
    private String userid;
    private List<ChargeList> chargeLists;
    private static final String TAG = "Fragment_fees_shui";
    private Fees_adapter adapter;

    public Fragment_fees_shui(String s, String userid) {
        this.s = s;
        this.userid = userid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fees_shui, container, false);
        initView(view);
        if (s.equals("1")){
            titleTitle.setText("水费");
        }else {
            titleTitle.setText("电费");
        }

        getOkHttp();


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Fees());
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (chargeLists == null){
            chargeLists = new ArrayList<>();
        }else {
            chargeLists.clear();
        }
        new OkHttpTo1()
                .setUrl("chargeList")
                .setJSONObject("userid",userid)
                .setJSONObject("type",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        chargeLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<ChargeList>>(){}.getType()));
                        Log.d(TAG, "onResponse: "+chargeLists.size());
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {
        if (adapter == null){
            adapter = new Fees_adapter(chargeLists);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_fees,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
