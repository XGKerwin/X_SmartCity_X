package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class Fragment_Hospital_name extends Fragment {

    private View view;
    private String id;
    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edName;
    private EditText edSex;
    private EditText edTel;
    private EditText edIdCard;
    private EditText edEmail;
    private EditText edDizhi;
    private TextView txtSave;
    private ImageView btnXiayibu;
    private String userid;
    private String name;
    private String sex;
    private String tel;
    private String caid;

    public Fragment_Hospital_name(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hospital_name, container, false);
        initView(view);
        titleTitle.setText("就诊卡");
        userid = App.getId();
        getUser();

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.setPatientName(name);
                getFragment(new Fragment_Hospital_Department(id));
            }
        });

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                sex = edSex.getText().toString();
                tel = edTel.getText().toString();
                caid = edIdCard.getText().toString();
                if (name.equals("")){
                    Toast.makeText(getContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (sex.equals("")){
                    Toast.makeText(getContext(),"性别不能为空",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                }else if (caid.equals("")){
                    Toast.makeText(getContext(),"身份证不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    if (sex.equals("男")){
                        btnXiayibu.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    }else if (sex.equals("女")){
                        btnXiayibu.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"性别不正确",Toast.LENGTH_SHORT).show();
                    }
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

    private void getUser() {
        new OKHttpTo()
                .setUrl("getUserByUserId")
                .setType("get")
                .setJSONObject("id",userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        edName.setText(jsonObject.optString("nickName"));
                        edIdCard.setText(jsonObject.optString("idCard"));
                        edSex.setText(jsonObject.optString("sex"));
                        edTel.setText(jsonObject.optString("phonenumber"));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        edName = view.findViewById(R.id.ed_name);
        edSex = view.findViewById(R.id.ed_sex);
        edTel = view.findViewById(R.id.ed_tel);
        edIdCard = view.findViewById(R.id.ed_idCard);
        edEmail = view.findViewById(R.id.ed_email);
        edDizhi = view.findViewById(R.id.ed_dizhi);
        txtSave = view.findViewById(R.id.txt_save);
        btnXiayibu = view.findViewById(R.id.btn_xiayibu);
    }
}
