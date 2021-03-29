package com.example.x_smartcity_x.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class User_ZhuceActivity extends AppCompatActivity {


    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edUser;
    private EditText edPass;
    private EditText edPass1;
    private TextView txtSave;
    private EditText edName;
    private EditText edTel;
    private EditText edSex;
    private String user;
    private String pass;
    private String pass1;
    private String tel;
    private String name;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__zhuce);
        initView();
        titleTitle.setText("注册");
        btn();

    }

    private void btn() {
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edUser.getText().toString();
                pass = edPass.getText().toString();
                pass1 = edPass1.getText().toString();
                tel = edTel.getText().toString();
                name = edName.getText().toString();
                sex = edSex.getText().toString();
                if (user.equals("")) {
                    Toast.makeText(User_ZhuceActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(User_ZhuceActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (pass1.equals("")) {
                    Toast.makeText(User_ZhuceActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(User_ZhuceActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (name.equals("")){
                    Toast.makeText(User_ZhuceActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }else if (sex.equals("")){
                    Toast.makeText(User_ZhuceActivity.this, "请输入性别", Toast.LENGTH_SHORT).show();
                }else if (pass.equals(pass1)) {
                    if (sex.equals("男")){
                        getOkHttp("1");
                    }else if (sex.equals("女")){
                        getOkHttp("0");
                    }else {
                        Toast.makeText(User_ZhuceActivity.this, "请输入正确性别", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(User_ZhuceActivity.this, "两次输密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getOkHttp(String s) {
        /**
         * {"userName":"cnds","nickName":"张三","phonenumber":"18574353453","sex":"1","password":"123456"}
         */
        new OKHttpTo()
                .setUrl("register")
                .setType("post")
                .setJSONObject("userName",user)
                .setJSONObject("nickName",name)
                .setJSONObject("phonenumber",tel)
                .setJSONObject("sex",s)
                .setJSONObject("password",pass1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(User_ZhuceActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        edPass1 = findViewById(R.id.ed_pass1);
        txtSave = findViewById(R.id.txt_save);
        edName = findViewById(R.id.ed_name);
        edTel = findViewById(R.id.ed_tel);
        edSex = findViewById(R.id.ed_sex);
    }
}