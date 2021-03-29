package com.example.x_smartcity_x.Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_x.R;

public class UserUcActivity extends AppCompatActivity {

    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edName;
    private EditText edSex;
    private EditText edTel;
    private EditText edIdCard;
    private EditText edEmail;
    private TextView txtSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_uc);
        initView();
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        edName = findViewById(R.id.ed_name);
        edSex = findViewById(R.id.ed_sex);
        edTel = findViewById(R.id.ed_tel);
        edIdCard = findViewById(R.id.ed_idCard);
        edEmail = findViewById(R.id.ed_email);
        txtSave = findViewById(R.id.txt_save);
    }
}