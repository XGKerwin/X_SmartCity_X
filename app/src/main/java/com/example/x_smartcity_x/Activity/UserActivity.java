package com.example.x_smartcity_x.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";
    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edUser;
    private EditText edPass;
    private TextView txtLogin;
    private TextView txtZhuce;
    private String user;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        titleTitle.setText("登录");

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,User_ZhuceActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edUser.getText().toString();
                pass = edPass.getText().toString();
                if (user.equals("")){
                    Toast.makeText(UserActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(UserActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp();
                }
            }
        });

    }

    private void getOkHttp() {
        new OKHttpTo()
                .setUrl("login")
                .setType("post")
                .setJSONObject("userName",user)
                .setJSONObject("password",pass)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s = jsonObject.optString("msg");
                        if (s.equals("操作成功")){
                            Toast.makeText(UserActivity.this,jsonObject.optString("msg"),Toast.LENGTH_SHORT).show();
                            String id = jsonObject.optString("id");
                            App.setId(id);

                            finish();
                        }else {
                            Toast.makeText(UserActivity.this,jsonObject.optString("msg"),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(UserActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        txtLogin = findViewById(R.id.txt_login);
        txtZhuce = findViewById(R.id.txt_zhuce);
    }
}