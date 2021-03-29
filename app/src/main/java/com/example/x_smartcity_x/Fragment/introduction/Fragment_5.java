package com.example.x_smartcity_x.Fragment.introduction;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.Fragment.Fragment_home;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;

public class Fragment_5 extends Fragment {

    private View view;
    private ImageView imgage;
    private Button btnSettings;
    private Button btnStart;
    private LinearLayout linInternet;
    private EditText edIp;
    private EditText edDuankou;
    private Button btnAlter;
    private Button btnSave;
    private Animation animation;
    private String img;

    public Fragment_5(String imgUrl) {
        this.img = imgUrl;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_5, container, false);
        initView(view);

        getOkHttp();

        edIp.setFocusable(false);
        edDuankou.setFocusable(false);
        edIp.setFocusableInTouchMode(false);
        edDuankou.setFocusableInTouchMode(false);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.becometransparent);
        btn_set();
        btn();
        return view;
    }

    private void getOkHttp() {
        new OkHttpToImage()
                .setUrl(img)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        imgage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void btn() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_home());
            }
        });
        btnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edIp.setFocusableInTouchMode(true);
                edDuankou.setFocusableInTouchMode(true);
                edIp.setFocusable(true);
                edDuankou.setFocusable(true);
                edIp.requestFocus();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linInternet.setVisibility(View.GONE);
                btnSettings.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.VISIBLE);
                btnSettings.startAnimation(animation);
                btnStart.startAnimation(animation);
                edIp.setFocusable(false);
                edDuankou.setFocusable(false);
                edIp.setFocusableInTouchMode(false);
                edDuankou.setFocusableInTouchMode(false);
                Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSettings.setVisibility(View.GONE);
                btnStart.setVisibility(View.GONE);
                linInternet.setVisibility(View.VISIBLE);
                linInternet.startAnimation(animation);
            }
        });
    }

    private void btn_set() {
        btnSettings.startAnimation(animation);            //启动动画效果
        btnStart.startAnimation(animation);            //启动动画效果
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment).commit();
    }

    private void initView(View view) {
        imgage = view.findViewById(R.id.imgage);
        btnSettings = view.findViewById(R.id.btn_settings);
        btnStart = view.findViewById(R.id.btn_start);
        linInternet = view.findViewById(R.id.lin_internet);
        edIp = view.findViewById(R.id.ed_ip);
        edDuankou = view.findViewById(R.id.ed_duankou);
        btnAlter = view.findViewById(R.id.btn_alter);
        btnSave = view.findViewById(R.id.btn_save);
    }
}
