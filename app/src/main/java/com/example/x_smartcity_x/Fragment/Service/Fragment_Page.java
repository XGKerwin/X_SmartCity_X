package com.example.x_smartcity_x.Fragment.Service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Activity.BusActivity;
import com.example.x_smartcity_x.Activity.HospitalActivity;
import com.example.x_smartcity_x.Activity.HuoDongActivity;
import com.example.x_smartcity_x.Activity.New_ListActivity;
import com.example.x_smartcity_x.Activity.NewsActivity;
import com.example.x_smartcity_x.Activity.ParkingActivity;
import com.example.x_smartcity_x.Activity.PovertyActivity;
import com.example.x_smartcity_x.Activity.SubwayActivity;
import com.example.x_smartcity_x.Activity.ViolationActivity;
import com.example.x_smartcity_x.Activity.WeatherActivity;
import com.example.x_smartcity_x.Adapter.Pager_NewsAdapter;
import com.example.x_smartcity_x.Adapter.Pager_ServiceAdapter;
import com.example.x_smartcity_x.Adapter.Pager_ThemeAdapter;
import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyRecyclerView;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.bean.GetServiceAll;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Page extends Fragment {

    private View view;
    private EditText seekEdit;
    private TextView seekBtn;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerService;
    private RecyclerView recyclerTheme;
    private MyRecyclerView recyclerNews;
    private List<Lists> lists;
    private List<ImageView> imageViews;
    private static final String TAG = "Fragment_Page";
    private List<GetServiceAll> getServiceAlls,getService;
    private List<GetNewsAll> getNewsAlls;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_page, container, false);
        initView(view);
        imageViews = new ArrayList<>();

        getImage();         //轮播图
        getService();       //活动
        getTheme();         //热门主题

        return view;
    }


    private void getTheme() {
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
                        showTheme();
                        showNew();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showNew() {
        Pager_NewsAdapter adapter = new Pager_NewsAdapter(getContext(),getNewsAlls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerNews.setLayoutManager(linearLayoutManager);
        recyclerNews.setAdapter(adapter);

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(getContext(), NewsActivity.class);
                intent.putExtra("init",position+"");
                startActivity(intent);
            }
        });
    }

    private void showTheme() {
        Map<String,String> map = new HashMap<>();
        for (GetNewsAll all : getNewsAlls){
            map.put(all.getTheme(),"1");
        }

        Pager_ThemeAdapter adapter = new Pager_ThemeAdapter(getContext(),map);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerTheme.setLayoutManager(gridLayoutManager);
        recyclerTheme.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                String s = "";
                switch (position){
                    case 0:
                        s = "国庆专题";
                        break;
                    case 1:
                        s = "抗疫情";
                        break;
                    case 2:
                        s = "电影";
                        break;
                    case 3:
                        s = "烈士专栏";
                        break;
                }
                Intent intent = new Intent(getContext(), New_ListActivity.class);
                intent.putExtra("init",s);
                startActivity(intent);
            }
        });

    }

    private void getService() {
        if (getServiceAlls == null){
            getServiceAlls = new ArrayList<>();
            getService = new ArrayList<>();
        }else {
            getService.clear();
            getServiceAlls.clear();
        }
        new OKHttpTo()
                .setUrl("getServiceAll")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getServiceAlls.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetServiceAll>>(){}.getType()));
                        showService();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void showService() {
        for (GetServiceAll all : getServiceAlls){
            if (all.getIsRecommend() == 1){
                getService.add(all);
            }
        }
        Pager_ServiceAdapter adapter = new Pager_ServiceAdapter(getService,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),5);
        recyclerService.setLayoutManager(gridLayoutManager);
        recyclerService.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                if (name.equals("门诊预约")){
                    Intent intent = new Intent(getContext(), HospitalActivity.class);
                    startActivity(intent);
                }else if (name.equals("实时天气")){
                    Intent intent = new Intent(getContext(), WeatherActivity.class);
                    startActivity(intent);
                } else if (name.equals("停车场")) {
                    Intent intent = new Intent(getContext(), ParkingActivity.class);
                    startActivity(intent);
                }else if (name.equals("生活缴费")){
                    Intent intent = new Intent(getContext(), FeesActivity.class);
                    startActivity(intent);
                }else if (name.equals("地铁查询")){
                    Intent intent = new Intent(getContext(), SubwayActivity.class);
                    startActivity(intent);
                }else if (name.equals("智慧巴士")){
                    Intent intent = new Intent(getContext(), BusActivity.class);
                    startActivity(intent);
                }else if (name.equals("违章查询")){
                    Intent intent = new Intent(getContext(), ViolationActivity.class);
                    startActivity(intent);
                } else if (name.equals("活动")){
                    Intent intent = new Intent(getContext(), HuoDongActivity.class);
                    startActivity(intent);
                }else if (name.equals("充值中心")){     //扶贫
                    Intent intent = new Intent(getContext(), PovertyActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getImage() {
        if (lists == null){
            lists = new ArrayList<>();
        }else {
            lists.clear();
        }
        new OKHttpTo()
                .setType("get")
                .setUrl("lists")
                .setJSONObject("type","45")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lists.addAll(new Gson().fromJson(jsonObject.optJSONArray("roes").toString(),
                                new TypeToken<List<Lists>>(){}.getType()));
                        getImageshow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getImageshow() {
        for (int i = 0; i < lists.size(); i++) {
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
                                        Intent intent = new Intent(getContext(),NewsActivity.class);
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

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        seekEdit = view.findViewById(R.id.seek_edit);
        seekBtn = view.findViewById(R.id.seek_btn);
        viewFlipper = view.findViewById(R.id.view_flipper);
        recyclerService = view.findViewById(R.id.recycler_service);
        recyclerTheme = view.findViewById(R.id.recycler_theme);
        recyclerNews = view.findViewById(R.id.recycler_news);
    }
}
