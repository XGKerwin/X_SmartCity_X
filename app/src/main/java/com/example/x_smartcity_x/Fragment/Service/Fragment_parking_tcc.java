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

import com.example.x_smartcity_x.Adapter.Parking_tcc_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Parklotlist;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_parking_tcc extends Fragment {

    private View view;
    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private TextView textShow;
    private List<Parklotlist> parklotlists;
    private Parking_tcc_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_parkinng_tcc, container, false);
        initView(view);
        titleTitle.setText("停车场");

        getOkHttp();

        textShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShow.setVisibility(View.GONE);
                show(parklotlists.size());
            }
        });


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_parking());
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (parklotlists == null){
            parklotlists = new ArrayList<>();
        }else {
            parklotlists.clear();
        }
        new OKHttpTo()
                .setUrl("parklotlist")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s = jsonObject.optString("msg");
                        if (s.equals("查询成功")){
                            parklotlists.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                    new TypeToken<List<Parklotlist>>(){}.getType()));
                            show(2);
                        }else {
                            Toast.makeText(getContext(),"网络失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show(int i) {
            adapter = new Parking_tcc_adapter(parklotlists,i,getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);



    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_parking,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerView = view.findViewById(R.id.recyclerView);
        textShow = view.findViewById(R.id.text_show);
    }
}
