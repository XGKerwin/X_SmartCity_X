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
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GethospitalId;
import com.example.x_smartcity_x.bean.Gethospitaljpg;
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

public class Fragment_Hospital extends Fragment {

    private View view;
    private String id;
    private ImageView titleBack;
    private TextView titleTitle;
    private ViewFlipper viewFlipper;
    private TextView txtMsg;
    private LinearLayout txtInquire;
    private LinearLayout btnRegistered;
    private List<Gethospitaljpg> gethospitaljpgs;
    private GethospitalId gethospitalIds;
    private List<ImageView> imageViews;
    private String userid;

    public Fragment_Hospital(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hospital, container, false);
        initView(view);
        userid = App.getId();
        titleTitle.setText("医院详情");
        imageViews = new ArrayList<>();

        getImageOKHttp();
        getOKHttp();

        btnRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Hospital_name(id));
                }

            }
        });

        txtInquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Inquire(userid));
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_hospital,fragment).commit();
    }

    private void getImageOKHttp() {
        if (gethospitaljpgs == null){
            gethospitaljpgs = new ArrayList<>();
        }else {
            gethospitaljpgs.clear();
        }
        new OKHttpTo()
                .setUrl("gethospitaljpg")
                .setType("get")
                .setJSONObject("hospitalId",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        gethospitaljpgs.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<Gethospitaljpg>>(){}.getType()));
                        String s = jsonObject.optString("msg");
                        if (s.equals("操作成功")){
                            show();
                        }else {
                            Toast.makeText(getContext(),"网络错误",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void show() {
        for (int i = 0; i < gethospitaljpgs.size(); i++) {
            new OkHttpToImage()
                    .setUrl(gethospitaljpgs.get(i).getImgUrl())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                if (imageViews.size() == 4) {
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

    private void getOKHttp() {
        new OKHttpTo()
                .setUrl("gethospitalId")
                .setType("get")
                .setJSONObject("hospitalId",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        gethospitalIds = new Gson().fromJson(jsonObject.toString(),GethospitalId.class);
                        txtMsg.setText(gethospitalIds.getBrief());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        viewFlipper = view.findViewById(R.id.view_flipper);
        txtMsg = view.findViewById(R.id.txt_msg);
        txtInquire = view.findViewById(R.id.txt_Inquire);
        btnRegistered = view.findViewById(R.id.btn_registered);
    }
}
