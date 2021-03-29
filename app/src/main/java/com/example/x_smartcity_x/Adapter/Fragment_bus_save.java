package com.example.x_smartcity_x.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.Fragment.Service.Fragment_Bus;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetBusList;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class Fragment_bus_save extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtName;
    private TextView txtTel;
    private TextView txtShangche;
    private TextView txtXiache;
    private Button btnSave;
    private GetBusList busList;
    private String name, tel, shangche, xiache, time;
    private TextView txtTime;
    private String userid;
    private static final String TAG = "Fragment_bus_save";

    public Fragment_bus_save(String time, String name, String tel, String shangche, String xiache, GetBusList busList) {
        this.busList = busList;
        this.xiache = xiache;
        this.name = name;
        this.tel = tel;
        this.shangche = shangche;
        this.time = time;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bus_save, container, false);
        initView(view);
        titleTitle.setText("提交订单");
        userid = App.getId();

        txtName.setText("乘客姓名："+name);
        txtTel.setText("乘客电话："+tel);
        txtShangche.setText("上车地点："+shangche);
        txtXiache.setText("下车地点："+xiache);
        txtTime.setText("乘车时间："+time);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_bus_name(time, busList));
            }
        });
        return view;
    }

    private void getOkHttp() {
        /**
         * {"start":"泰德大厦","end":"大连北站","userName":"张三","userTel":"12345611","price":"8","path":"一号线","status":"1","userId":"1"}
         */

        Log.d(TAG, "getOkHttp: "+shangche);
        Log.d(TAG, "getOkHttp: "+xiache);
        Log.d(TAG, "getOkHttp: "+name);
        Log.d(TAG, "getOkHttp: "+tel);
        Log.d(TAG, "getOkHttp: "+busList.getPrice());
        Log.d(TAG, "getOkHttp: "+busList.getNumber());
        Log.d(TAG, "getOkHttp: "+userid);




        new OKHttpTo()
                .setUrl("setbusorder")
                .setType("post")
                .setJSONObject("start",shangche)
                .setJSONObject("end",xiache)
                .setJSONObject("userName",name)
                .setJSONObject("userTel",tel)
                .setJSONObject("price",busList.getPrice())
                .setJSONObject("path",busList.getNumber())
                .setJSONObject("status","1")
                .setJSONObject("userId",userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String name = jsonObject.optString("msg");
                        Log.d(TAG, "onResponse: "+name);
                        if (name.equals("操作成功")){
                            Toast.makeText(getContext(),"操作成功",Toast.LENGTH_SHORT).show();
                            getFragment(new Fragment_Bus());
                        }else {
                            Toast.makeText(getContext(),"提交失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }


    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtName = view.findViewById(R.id.txt_name);
        txtTel = view.findViewById(R.id.txt_tel);
        txtShangche = view.findViewById(R.id.txt_shangche);
        txtXiache = view.findViewById(R.id.txt_xiache);
        btnSave = view.findViewById(R.id.btn_save);
        txtTime = view.findViewById(R.id.txt_time);
    }
}
