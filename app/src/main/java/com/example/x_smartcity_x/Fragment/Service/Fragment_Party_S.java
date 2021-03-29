package com.example.x_smartcity_x.Fragment.Service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Party_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.bean.Lists;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.example.x_smartcity_x.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Party_S extends Fragment {

    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView;
    private List<Lists> lists;
    private List<ImageView> imageViews;
    private List<GetNewsAll> getNewsAlls;
    private static final String TAG = "Fragment_Party_S";
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_party_s, container, false);
        initView(view);
        imageViews = new ArrayList<>();

        getImage();
        getNews();

        return view;
    }

    private void getNews() {
        if (getNewsAlls == null){
            getNewsAlls = new ArrayList<>();
        }else {
            getNewsAlls.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsAll")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getNewsAlls.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetNewsAll>>(){}.getType()));
                        Log.d(TAG, "onResponse: "+getNewsAlls.size());
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        Party_adapter adapter = new Party_adapter(getNewsAlls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getImage() {
        if (lists == null){
            lists = new ArrayList<>();
        }else {
            lists.clear();
        }
        new OKHttpTo()
                .setUrl("lists")
                .setType("get")
                .setJSONObject("type","45")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lists.addAll(new Gson().fromJson(jsonObject.optJSONArray("roes").toString(),
                                new TypeToken<List<Lists>>(){}.getType()));
                        showImage();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showImage() {
        try {
            for (int i = 0; i < lists.size(); i++) {
                ImageView imageView = new ImageView(getContext());
                new OkHttpToImage()
                        .setUrl(lists.get(i).getImgUrl())
                        .setOkHttpLoImage(new OkHttpLoImage() {
                            @Override
                            public void onResponse(Call call, Bitmap bitmap) {
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                if (imageViews.size() == 5) {
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }

                            }

                            @Override
                            public void onFailure(Call call, IOException obj) {

                            }
                        }).start();
            }
        }catch (NullPointerException e){

        }
    }

    private void initView(View view) {
        viewFlipper = view.findViewById(R.id.view_flipper);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
