package com.example.x_smartcity_x.Adapter;

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

import com.example.x_smartcity_x.Fragment.Service.Fragment_parking_tcc;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Parklotlist;

public class Fragment_parking_msg extends Fragment {
    private Parklotlist parklotlist;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView title1;
    private TextView jvli;
    private TextView zhuangtai;
    private TextView shoufei;
    private TextView shengyu;
    private TextView dizhi;

    public Fragment_parking_msg(Parklotlist parklotlist) {
        this.parklotlist = parklotlist;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_parking_msg, container, false);
        initView(view);
        titleTitle.setText("停车场详情");

        title1.setText(parklotlist.getParkName());
        jvli.setText("距离："+parklotlist.getDistance());
        zhuangtai.setText("开放");
        shoufei.setText(parklotlist.getRates()+"元/小时     一天最高"+parklotlist.getPriceCaps());
        shengyu.setText("剩余车位："+parklotlist.getAllPark());
        dizhi.setText("地址："+parklotlist.getAddress());



        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_parking_tcc());
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_parking,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        title1 = view.findViewById(R.id.title1);
        jvli = view.findViewById(R.id.jvli);
        zhuangtai = view.findViewById(R.id.zhuangtai);
        shoufei = view.findViewById(R.id.shoufei);
        shengyu = view.findViewById(R.id.shengyu);
        dizhi = view.findViewById(R.id.dizhi);
    }
}
