package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;

public class Fragment_Fees extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private LinearLayout btnShuifei;
    private LinearLayout btnDianfei;
    private LinearLayout btnHuhao;
    private LinearLayout btnZixun;
    private LinearLayout btnJiaofei;
    private LinearLayout btnGuanli;
    private String userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fees, container, false);
        initView(view);
        titleTitle.setText("生活缴费");
        userid = App.getId();

        btn();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    private void btn() {
        btnHuhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Fees_huhao());
                }

            }
        });

        btnShuifei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_fees_shui("1",userid));
                }
            }
        });

        btnDianfei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_fees_shui("2",userid));
                }
            }
        });

        btnZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fees_zixun());
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_fees,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        btnShuifei = view.findViewById(R.id.btn_shuifei);
        btnDianfei = view.findViewById(R.id.btn_dianfei);
        btnHuhao = view.findViewById(R.id.btn_huhao);
        btnZixun = view.findViewById(R.id.btn_zixun);
        btnJiaofei = view.findViewById(R.id.btn_jiaofei);
        btnGuanli = view.findViewById(R.id.btn_guanli);
    }
}
