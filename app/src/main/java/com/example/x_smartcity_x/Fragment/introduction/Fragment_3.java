package com.example.x_smartcity_x.Fragment.introduction;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;

public class Fragment_3 extends Fragment {

    private View view;
    private String img;
    private ImageView imgage;

    public Fragment_3(String imgUrl) {
        this.img = imgUrl;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_3, container, false);
        initView(view);

        getOkHttp();


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

    private void initView(View view) {
        imgage = view.findViewById(R.id.imgage);
    }
}
