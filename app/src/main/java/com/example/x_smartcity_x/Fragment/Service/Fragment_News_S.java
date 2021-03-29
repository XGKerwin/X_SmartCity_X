package com.example.x_smartcity_x.Fragment.Service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.x_smartcity_x.Activity.New_ListActivity;
import com.example.x_smartcity_x.Activity.NewsActivity;
import com.example.x_smartcity_x.Adapter.New_List2_Adapter;
import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyRecyclerView;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.bean.Lists;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_News_S extends Fragment {

    private View view;
    private ImageView titleBack;
    private TextView titleTitle;
    private ViewFlipper viewFlipper;
    private TextView btnAffairs;
    private TextView btnEpidemic;
    private TextView btnEntertainment;
    private MyRecyclerView recyclerView;
    private List<ImageView> imageViews;
    private List<Lists> lists;
    private List<GetNewsAll> getNewsAllLists,getNewsAlls;
    private static final String TAG = "Fragment_News_S";
    private New_List2_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_s, container, false);
        initView(view);
        titleBack.setVisibility(View.GONE);
        titleTitle.setText("新闻");
        imageViews = new ArrayList<>();
        getImageView();     //轮播图
        getNews();

        btn();

        return view;
    }

    private void btn() {
        btnEntertainment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                showList("娱乐");
            }
        });

        btnEpidemic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList("疫情");
            }
        });

        btnAffairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList("时政");
            }
        });
    }

    private void showList(String s) {
        /**
         *   36:时政  37：疫情  38：娱乐
         */
        getNewsAlls.clear();
        for (GetNewsAll all : getNewsAllLists){
            if (all.getPressCategory().equals(s)){
                getNewsAlls.add(all);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void getNews() {
        if (getNewsAllLists == null){
            getNewsAllLists = new ArrayList<>();
        }else {
            getNewsAllLists.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsAll")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getNewsAllLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetNewsAll>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        if (getNewsAlls == null){
            getNewsAlls = new ArrayList<>();
        }else {
            getNewsAlls.clear();
        }
        for (GetNewsAll all : getNewsAllLists){
            if (all.getIsRecommend()==1){
                getNewsAlls.add(all);
            }
        }
        if (adapter == null){
            adapter = new New_List2_Adapter(getNewsAlls);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(getContext(),NewsActivity.class);
                intent.putExtra("init",name);
                startActivity(intent);
            }
        });


    }

    private void getImageView() {
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
                        String name = jsonObject.optString("msg");
                        if (name.equals("查询成功")){
                            lists.addAll(new Gson().fromJson(jsonObject.optJSONArray("roes").toString(),
                                    new TypeToken<List<Lists>>(){}.getType()));
                            getshowImage();
                        }else {
                            Toast.makeText(getContext(),"查询失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getshowImage() {
        for (int i =0; i<lists.size(); i++){
            final int finalI = i;
            new OkHttpToImage()
                    .setUrl(lists.get(i).getImgUrl())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getContext(), NewsActivity.class);
                                        intent.putExtra("init",finalI+"");
                                        startActivity(intent);
                                    }
                                });
                                if (imageViews.size() == 5) {
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }
                            } catch (NullPointerException e) {

                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException obj) {

                        }
                    }).start();
        }


    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        viewFlipper = view.findViewById(R.id.view_flipper);
        btnAffairs = view.findViewById(R.id.btn_affairs);
        btnEpidemic = view.findViewById(R.id.btn_epidemic);
        btnEntertainment = view.findViewById(R.id.btn_entertainment);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
