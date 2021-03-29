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

import com.example.x_smartcity_x.R;

public class Fragment_Poverty_Workbench extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private ImageView btnQiuzhu;
    private ImageView btnBangfu;
    private ImageView btnZoufang;
    private ImageView btnAnli;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_poverty_workbench, container, false);
        initView(view);
        titleTitle.setText("我的工作台");
        btn();

        return view;
    }

    private void btn() {
        btnAnli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty_Workbench_Case());
            }
        });

        btnZoufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty_Workbench_Access());
            }
        });

        btnBangfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty_Workbench_help());
            }
        });

        btnQiuzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fupin_qiuzhu());
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_poverty,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        titleWode = view.findViewById(R.id.title_wode);
        btnQiuzhu = view.findViewById(R.id.btn_qiuzhu);
        btnBangfu = view.findViewById(R.id.btn_bangfu);
        btnZoufang = view.findViewById(R.id.btn_zoufang);
        btnAnli = view.findViewById(R.id.btn_anli);
    }
}
