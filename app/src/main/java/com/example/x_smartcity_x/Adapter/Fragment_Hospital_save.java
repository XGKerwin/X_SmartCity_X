package com.example.x_smartcity_x.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class Fragment_Hospital_save extends Fragment {

    private String s;
    private String id;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView commodityName;
    private TextView name;
    private TextView keshi;
    private TextView jine;
    private TextView shijian;
    private TextView txtSave;

    public Fragment_Hospital_save(String s, String id) {
        this.id = id;
        this.s = s;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hospital_save, container, false);
        initView(view);
        titleTitle.setText("信息确认");
        name.setText(App.getPatientName());
        keshi.setText(App.getDivisionId());
        shijian.setText(s);
        jine.setText(App.getMoeny() + "元");

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Hospital_Doctors(id));
            }
        });


        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOkHttp();
            }
        });

        return view;
    }

    private void setOkHttp() {
        /**
         * {"patientName":"王五","divisionId":"胸外科","typesId":"1","moeny":"8","startime":"2021-3-11","userId":"3"}
         */
        new OKHttpTo()
                .setUrl("sethospitalreservation")
                .setType("post")
                .setJSONObject("patientName",App.getPatientName())
                .setJSONObject("divisionId",App.getDivisionId())
                .setJSONObject("typesId","1")
                .setJSONObject("moeny",App.getMoeny())
                .setJSONObject("startime",s)
                .setJSONObject("userId",App.getId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s = jsonObject.optString("msg");
                        if (s.equals("操作成功")){
                            Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                            getActivity().finish();
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
        fragmentTransaction.replace(R.id.fragment_hospital, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        commodityName = view.findViewById(R.id.commodityName);
        name = view.findViewById(R.id.name);
        keshi = view.findViewById(R.id.keshi);
        jine = view.findViewById(R.id.jine);
        shijian = view.findViewById(R.id.shijian);
        txtSave = view.findViewById(R.id.txt_save);
    }
}
