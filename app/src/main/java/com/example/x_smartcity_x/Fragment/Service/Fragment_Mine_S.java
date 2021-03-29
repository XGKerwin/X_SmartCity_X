package com.example.x_smartcity_x.Fragment.Service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.Activity.UserActivity;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_round;
import com.example.x_smartcity_x.bean.GetUserByUserId;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

public class Fragment_Mine_S extends Fragment {

    private View view;
    private MyImageView_round imgHead;
    private TextView txtUser;
    private TextView txtWeather;
    private LinearLayout btnUC;
    private LinearLayout btnOrder;
    private LinearLayout btnPass;
    private LinearLayout btnFeedback;
    private LinearLayout btnExit;
    private String id;
    private static final String TAG = "Fragment_Mine_S";
    private GetUserByUserId getUserByUserId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine_s, container, false);
        }
        initView(view);

        btn();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        id = App.getId();

        if (id == null){

        }else {
            getOKHttp();
        }
    }

    private void btn() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.setId(null);
                id = null;
                getOKHttp();
            }
        });
        //意见反馈
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_User_Feed());
                }
            }
        });
        //密码修改
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_User_Pass(getUserByUserId));
                }
            }
        });
        //个人信息
        btnUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_User_UC_Fragment(getUserByUserId));
                }
            }
        });
        //订单类型
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Mine_Order());
                }
            }
        });
        txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_mine,fragment).commit();
    }

    private void getOKHttp() {
        new OKHttpTo()
                .setUrl("getUserByUserId")
                .setType("get")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s = jsonObject.optString("msg");
                        Log.d(TAG, "onResponse: ssssssssssssssssss");
                        if (s.equals("操作成功")){
                            getUserByUserId = new Gson().fromJson(jsonObject.toString(),GetUserByUserId.class);
                            txtUser.setText(getUserByUserId.getNickName());
                            getImage();
                        }else {
                            Log.d(TAG, "onResponse: ssssssssssssssssss");
                            txtUser.setText("请登录");
                            imgHead.setImageResource(R.drawable.user1);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getImage() {
        new OkHttpToImage()
                .setUrl(getUserByUserId.getAvatar())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        imgHead.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        imgHead = view.findViewById(R.id.img_head);
        txtUser = view.findViewById(R.id.txt_user);
        txtWeather = view.findViewById(R.id.txt_weather);
        btnUC = view.findViewById(R.id.btn_UC);
        btnOrder = view.findViewById(R.id.btn_order);
        btnPass = view.findViewById(R.id.btn_pass);
        btnFeedback = view.findViewById(R.id.btn_feedback);
        btnExit = view.findViewById(R.id.btn_exit);
    }
}
