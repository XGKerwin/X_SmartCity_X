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
import com.example.x_smartcity_x.bean.GetUserByUserId;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class Fragment_User_UC_Fragment extends Fragment {

    private View view;
    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edName;
    private EditText edSex;
    private EditText edTel;
    private EditText edIdCard;
    private EditText edEmail;
    private TextView txtSave;
    private GetUserByUserId getUserByUserId;
    private String id;
    private String name;
    private String userName;
    private String tel;
    private String sex;
    private String cardid;
    private String file;
    private static final String TAG = "Fragment_User_UC_Fragme";
    private String email;

    public Fragment_User_UC_Fragment(GetUserByUserId getUserByUserId) {
        this.getUserByUserId = getUserByUserId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_uc, container, false);
        initView(view);
        titleTitle.setText("个人中心");
        id = App.getId();

        edName.setText(getUserByUserId.getNickName());
        edEmail.setText(getUserByUserId.getEmail());
        edIdCard.setText(getUserByUserId.getIdCard());
        edTel.setText(getUserByUserId.getPhonenumber());
        edSex.setText(getUserByUserId.getSex());

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                userName = getUserByUserId.getUserName();
                email = edEmail.getText().toString();
                tel = edTel.getText().toString();
                sex = edSex.getText().toString();
                cardid = edIdCard.getText().toString();
                file = getUserByUserId.getAvatar();
                if (name.equals("")){
                    Toast.makeText(getContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (sex.equals("")){
                    Toast.makeText(getContext(),"请输入性别",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (cardid.equals("")){
                    Toast.makeText(getContext(),"请输入身份证",Toast.LENGTH_SHORT).show();
                }else if (email.equals("")){
                    Toast.makeText(getContext(),"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else {
                    if (sex.equals("男")){
                        sex = "1";
                        getOkHttp();
                    }else if (sex.equals("女")){
                        sex = "0";
                        getOkHttp();
                    }else {
                        Toast.makeText(getContext(),"性别输入不正确",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Mine_S());
            }
        });
        return view;
    }

    private void getOkHttp() {
        /**
         * {"userId":"2","idCard":"532546465757","userName":"lisi","nickName":"李四",
         * "email":"3213424@qq.com","phonenumber":"12345678901","sex":"1","file":"touxiang2.jpg"}
         */
        new OKHttpTo()
                .setUrl("updateUser")
                .setType("post")
                .setJSONObject("userId",id)
                .setJSONObject("idCard",cardid)
                .setJSONObject("userName",userName)
                .setJSONObject("nickName",name)
                .setJSONObject("email",email)
                .setJSONObject("phonenumber",tel)
                .setJSONObject("sex",sex)
                .setJSONObject("file","user6.png")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s = jsonObject.optString("msg");
                        if (s.equals("操作成功")){
                            Toast.makeText(getContext(),"操作成功",Toast.LENGTH_SHORT).show();
                            getFragment(new Fragment_Mine_S());
                        }else {
                            Toast.makeText(getContext(),"操作失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_mine,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        edName = view.findViewById(R.id.ed_name);
        edSex = view.findViewById(R.id.ed_sex);
        edTel = view.findViewById(R.id.ed_tel);
        edIdCard = view.findViewById(R.id.ed_idCard);
        edEmail = view.findViewById(R.id.ed_email);
        txtSave = view.findViewById(R.id.txt_save);
    }
}
