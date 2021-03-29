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

public class Fragment_aubway_ditu extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView btnDitie2;
    private TextView btnDitie5;
    private TextView btnDitie6;
    private TextView btnDitie8;
    private ImageView imgTu;
    private ImageView imgDitie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_aubway_ditu, container, false);
        initView(view);
        titleTitle.setText("地铁总览图");

        btn();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Aubway());
            }
        });
        return view;
    }

    private void btn() {
        btnDitie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTu.setImageResource(R.drawable.ditie1);
                imgDitie.setImageResource(R.drawable.ditie_1);
            }
        });
        btnDitie5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTu.setImageResource(R.drawable.ditie2);
                imgDitie.setImageResource(R.drawable.ditie_2);
            }
        });
        btnDitie6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTu.setImageResource(R.drawable.ditie3);
                imgDitie.setImageResource(R.drawable.ditie_3);
            }
        });
        btnDitie8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTu.setImageResource(R.drawable.ditie4);
                imgDitie.setImageResource(R.drawable.ditie_4);
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_subway,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        btnDitie2 = view.findViewById(R.id.btn_ditie2);
        btnDitie5 = view.findViewById(R.id.btn_ditie5);
        btnDitie6 = view.findViewById(R.id.btn_ditie6);
        btnDitie8 = view.findViewById(R.id.btn_ditie8);
        imgTu = view.findViewById(R.id.img_tu);
        imgDitie = view.findViewById(R.id.img_ditie);
    }
}
