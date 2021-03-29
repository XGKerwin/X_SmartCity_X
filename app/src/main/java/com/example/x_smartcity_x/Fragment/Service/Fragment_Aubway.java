package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Aubway_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.SubwayStationRoute;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Aubway extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edJianguo;
    private TextView btnSousuo;
    private List<SubwayStationRoute> subwayStationRoutes;
    private RecyclerView recyclerView;
    private Aubway_adapter adapter;
    private static final String TAG = "Fragment_Aubway";
    private ImageView imgDitu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_aubway, container, false);
        initView(view);
        titleTitle.setText("地铁查询");

        getOKHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOKHttp();
            }
        });

        imgDitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_aubway_ditu());
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_subway,fragment).commit();
    }

    private void getOKHttp() {
        String s = edJianguo.getText().toString().trim();
        /**
         * {"currentName":"建国门"}
         */
        if (subwayStationRoutes == null) {
            subwayStationRoutes = new ArrayList<>();
        } else {
            subwayStationRoutes.clear();
        }
        new OKHttpTo()
                .setUrl("subwayStationRoute")
                .setType("get")
                .setJSONObject("currentName", s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s1 = jsonObject.optString("msg");
                        if (s1.equals("操作成功")) {
                            subwayStationRoutes.addAll(new Gson().fromJson(jsonObject.optJSONArray("roes").toString(),
                                    new TypeToken<List<SubwayStationRoute>>() {
                                    }.getType()));
                            show();
                        } else {
                            Toast.makeText(getContext(), "查询失败", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {
        if (adapter == null) {
            adapter = new Aubway_adapter(subwayStationRoutes,getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        edJianguo = view.findViewById(R.id.ed_jianguo);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        recyclerView = view.findViewById(R.id.recyclerView);
        imgDitu = view.findViewById(R.id.img_ditu);
    }
}
