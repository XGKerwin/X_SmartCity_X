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

import com.example.x_smartcity_x.Adapter.Bus_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetBusList;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Bus extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private List<GetBusList> getBusLists;
    private Bus_adapter adapter;
    private TextView titleWode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bus, container, false);
        initView(view);
        titleTitle.setText("智慧巴士");
        titleWode.setVisibility(View.VISIBLE);

        titleWode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Bus_wode());
            }
        });

        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus,fragment).commit();
    }

    private void getOkHttp() {
        if (getBusLists == null) {
            getBusLists = new ArrayList<>();
        } else {
            getBusLists.clear();
        }

        new OKHttpTo()
                .setUrl("getBusList")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String name = jsonObject.optString("msg");
                        if (name.equals("操作成功")) {
                            getBusLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                    new TypeToken<List<GetBusList>>() {
                                    }.getType()));
                            show();
                        } else {
                            Toast.makeText(getContext(), "操作失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (adapter == null) {
            adapter = new Bus_adapter(getBusLists, getActivity());
            LinearLayoutManager linearLayoutManage = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManage);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }


    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        recyclerView = View.findViewById(R.id.recyclerView);
        titleWode = View.findViewById(R.id.title_wode);
    }
}
