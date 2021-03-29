package com.example.x_smartcity_x.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Fragment.Service.Fragment_Hospital_Department;
import com.example.x_smartcity_x.R;

import java.util.Calendar;

public class Fragment_Hospital_Doctors extends Fragment {

    private String id;
    private ImageView titleBack;
    private TextView titleTitle;
    private Button btnZhuanjia;
    private Button btnPutong;
    private RecyclerView recyclerView;
    private LinearLayout lin;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView time4;
    private TextView time5;
    private TextView time6;
    private LinearLayout gettime1;
    private LinearLayout gettime2;
    private LinearLayout gettime3;
    private LinearLayout gettime4;
    private LinearLayout gettime5;
    private LinearLayout gettime6;

    public Fragment_Hospital_Doctors(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hospital_doctors, container, false);
        initView(view);
        titleTitle.setText("医生选择");

        bnt();


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Hospital_Department(id));
            }
        });

        gettime();
        gettimt1();

        return view;
    }

    private void bnt() {
        gettime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = time1.getText().toString()+"    14：00";
                getFragment(new Fragment_Hospital_save(s,id));
            }
        });

        gettime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = time2.getText().toString()+"    15：00";
                getFragment(new Fragment_Hospital_save(s, id));
            }
        });

        gettime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = time3.getText().toString()+"    16：00";
                getFragment(new Fragment_Hospital_save(s, id));
            }
        });

        gettime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = time4.getText().toString()+"    14：00";
                getFragment(new Fragment_Hospital_save(s, id));
            }
        });
        gettime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = time5.getText().toString()+"    15：00";
                getFragment(new Fragment_Hospital_save(s, id));
            }
        });
        gettime6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = time6.getText().toString()+"    16：00";
                getFragment(new Fragment_Hospital_save(s, id));
            }
        });




        btnZhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.GONE);

            }
        });

        btnPutong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.VISIBLE);
            }
        });
    }

    private void gettimt1() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        String sj = (year + "-" + month + "-" + day);
        time4.setText(sj);
        time5.setText(sj);
        time6.setText(sj);
    }

    private void gettime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String sj = (year + "-" + month + "-" + day);
        time1.setText(sj);
        time2.setText(sj);
        time3.setText(sj);
    }


    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_hospital, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        btnZhuanjia = view.findViewById(R.id.btn_zhuanjia);
        btnPutong = view.findViewById(R.id.btn_putong);
        recyclerView = view.findViewById(R.id.recyclerView);
        lin = view.findViewById(R.id.lin);
        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
        time4 = view.findViewById(R.id.time4);
        time5 = view.findViewById(R.id.time5);
        time6 = view.findViewById(R.id.time6);
        gettime1 = view.findViewById(R.id.gettime1);
        gettime2 = view.findViewById(R.id.gettime2);
        gettime3 = view.findViewById(R.id.gettime3);
        gettime4 = view.findViewById(R.id.gettime4);
        gettime5 = view.findViewById(R.id.gettime5);
        gettime6 = view.findViewById(R.id.gettime6);
    }
}
