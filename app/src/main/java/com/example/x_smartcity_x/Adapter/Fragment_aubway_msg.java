package com.example.x_smartcity_x.Adapter;

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

import com.example.x_smartcity_x.Fragment.Service.Fragment_Aubway;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.SubwayStation;
import com.example.x_smartcity_x.bean.SubwayStationRoute;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_aubway_msg extends Fragment {

    private SubwayStationRoute route;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtDidian;
    private RecyclerView recyclerview;
    private List<SubwayStation> subwayStations;
    private Aubway_msg_adapter adapter;
    private static final String TAG = "Fragment_aubway_msg";

    public Fragment_aubway_msg(SubwayStationRoute route) {
        this.route = route;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_aubway_msg, container, false);
        initView(view);
        titleTitle.setText("地铁查询");
        txtDidian.setText(route.getLineName());

        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Aubway());
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (subwayStations == null){
            subwayStations = new ArrayList<>();
        }else {
            subwayStations.clear();
        }
        new OKHttpTo()
                .setUrl("subwayStation")
                .setType("get")
                .setJSONObject("lineId",route.getLineId()+"")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String name = jsonObject.optString("msg");
                        if (name.equals("操作成功")){
                            subwayStations.addAll(new Gson().fromJson(jsonObject.optJSONArray("metroStepsList").toString(),
                                    new TypeToken<List<SubwayStation>>(){}.getType()));
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
            adapter = new Aubway_msg_adapter(subwayStations,route.getCurrentName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);
            recyclerview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }


    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_subway,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtDidian = view.findViewById(R.id.txt_didian);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
