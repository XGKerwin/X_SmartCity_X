package com.example.x_smartcity_x.Fragment.Service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Fragment_huodong_msg;
import com.example.x_smartcity_x.Adapter.HuoDong_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetActionImages;
import com.example.x_smartcity_x.bean.GetAllActions;
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

public class Fragment_HuoDong extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private ViewFlipper viewFlipper;
    private List<GetActionImages> getActionImages;
    private List<ImageView> imageViews;
    private LinearLayout btnRecreation;
    private LinearLayout btnSports;
    private LinearLayout btnStudy;
    private LinearLayout btnPolitics;
    private RecyclerView recyclerView;
    private List<GetAllActions> getAllActions,getAllActionsList;
    private HuoDong_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_huodong, container, false);
        initView(view);
        titleTitle.setText("活动");
        imageViews = new ArrayList<>();

        btn();

        getImage();

        getRecyNew();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    private void btn() {
        //体育2  学习3  4政治
        btnRecreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong_type(getAllActions,"1"));
            }
        });

        btnSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong_type(getAllActions,"2"));
            }
        });

        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong_type(getAllActions,"3"));
            }
        });

        btnPolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong_type(getAllActions,"4"));
            }
        });


    }

    private void getRecyNew() {
        if (getAllActions == null){
            getAllActions = new ArrayList<>();
        }else {
            getAllActions.clear();
        }
        new OkHttpTo1().setUrl("getAllActions")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getAllActions.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetAllActions>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void show() {
        if (getAllActionsList == null){
            getAllActionsList =new ArrayList<>();
        }else {
            getAllActionsList.clear();
        }
        for (GetAllActions actions : getAllActions){
            if (actions.getRecommand()==1){
                getAllActionsList.add(actions);
            }
        }

        adapter = new HuoDong_adapter(getAllActionsList,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getImage() {
        if (getActionImages == null) {
            getActionImages = new ArrayList<>();
        } else {
            getActionImages.clear();
        }
        new OkHttpTo1()
                .setUrl("getActionImages")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getActionImages.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionImages>>() {
                                }.getType()));
                        showimg();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void showimg() {
        for (int i = 0; i < getActionImages.size(); i++) {
            GetActionImages images = getActionImages.get(i);
            int finalI = i;
            new OkHttpToImage().setUrl(getActionImages.get(i).getImage())
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
                                        getFragment(new Fragment_huodong_msg(images.getId()));
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
        fragmentTransaction.replace(R.id.fragment_huodong,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        titleWode = view.findViewById(R.id.title_wode);
        viewFlipper = view.findViewById(R.id.view_flipper);
        btnRecreation = view.findViewById(R.id.btn_recreation);
        btnSports = view.findViewById(R.id.btn_sports);
        btnStudy = view.findViewById(R.id.btn_study);
        btnPolitics = view.findViewById(R.id.btn_politics);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
