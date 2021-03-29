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

import com.example.x_smartcity_x.Adapter.Inquire_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Gethospitalreservationuser;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_Inquire extends Fragment {

    private View view;
    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private String id;
    private List<Gethospitalreservationuser> gethospitalreservationusers;
    private Inquire_adapter adapter;
    private static final String TAG = "Fragment_Inquire";

    public Fragment_Inquire(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_inquire, container, false);
        initView(view);
        titleTitle.setText("预约查询");


        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Hospital(id));
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (gethospitalreservationusers == null){
            gethospitalreservationusers = new ArrayList<>();
        }else {
            gethospitalreservationusers.clear();
        }

        new OKHttpTo()
                .setUrl("gethospitalreservationuser")
                .setType("get")
                .setJSONObject("userid",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        gethospitalreservationusers.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<Gethospitalreservationuser>>(){}.getType()));
                        show();
                        Log.d(TAG, "onResponse: "+gethospitalreservationusers.size());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void show() {
        Collections.sort(gethospitalreservationusers, new Comparator<Gethospitalreservationuser>() {
            @Override
            public int compare(Gethospitalreservationuser o1, Gethospitalreservationuser o2) {
                return o2.getId() - o1.getId();
            }
        });

        if (adapter == null){
            adapter = new Inquire_adapter(gethospitalreservationusers);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }



    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_hospital,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
