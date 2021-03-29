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

public class Fragment_User_Pass extends Fragment {

    private View view;
    private String id;
    private GetUserByUserId getUserByUserId;
    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edPass1;
    private EditText edPass2;
    private TextView txtSave;

    public Fragment_User_Pass(GetUserByUserId getUserByUserId) {
        this.getUserByUserId = getUserByUserId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_pass, container, false);
        initView(view);
        titleTitle.setText("密码修改");

        id = App.getId();

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edPass1.getText().toString();
                String pass1 = edPass2.getText().toString();
                if (pass.equals("")){
                    Toast.makeText(getContext(),"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if (pass1.equals("")){
                    Toast.makeText(getContext(),"请确认密码",Toast.LENGTH_SHORT).show();
                } else if (pass.equals(pass1)) {
                    getOkHttp(pass1);
                } else {
                    Toast.makeText(getContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
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

    private void getOkHttp(String pass1) {
        /**
         * {"userId":"1","userName":"zhangsan","password":"123456"}
         */
        new OKHttpTo()
                .setUrl("resetPwd")
                .setType("put")
                .setJSONObject("userId", id)
                .setJSONObject("userName", getUserByUserId.getUserName())
                .setJSONObject("password", pass1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),jsonObject.optString("msg"),Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_Mine_S());
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
        edPass1 = view.findViewById(R.id.ed_pass1);
        edPass2 = view.findViewById(R.id.ed_pass2);
        txtSave = view.findViewById(R.id.txt_save);
    }
}
