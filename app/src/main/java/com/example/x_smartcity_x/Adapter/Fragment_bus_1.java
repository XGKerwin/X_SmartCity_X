package com.example.x_smartcity_x.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.Fragment.Service.Fragment_Bus;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetBusList;

public class Fragment_bus_1 extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private ImageView img;
    private TextView txtLuxian;
    private TextView txtPiaojia;
    private TextView txtLicheng;
    private Button btnXiayibu;
    private GetBusList busList;

    public Fragment_bus_1(GetBusList list) {
        this.busList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bus_1, container, false);
        initView(view);
        titleTitle.setText("智慧巴士");

        getData();


        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_bus_time(busList));
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Bus());
            }
        });
        return view;
    }

    private void getData() {
        txtLuxian.setText(busList.getFirst()+" —— "+busList.getEndTime());
        txtLicheng.setText("里程："+busList.getMileage()+"KM");
        txtPiaojia.setText("票价："+busList.getPrice()+"元");

        switch (busList.getNumber()){
            case "1":
                img.setImageResource(R.drawable.map4);
                break;
            case "2":
                img.setImageResource(R.drawable.map8);
                break;
            case "3":
                img.setImageResource(R.drawable.map10);
            case "4":
                img.setImageResource(R.drawable.map12);
                break;
            default:
                img.setImageResource(R.drawable.map14);
                break;
        }

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus,fragment).commit();
    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        img = View.findViewById(R.id.img);
        txtLuxian = View.findViewById(R.id.txt_luxian);
        txtPiaojia = View.findViewById(R.id.txt_piaojia);
        txtLicheng = View.findViewById(R.id.txt_licheng);
        btnXiayibu = View.findViewById(R.id.btn_xiayibu);
    }
}
