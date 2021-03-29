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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_User_Feed extends Fragment {

    private View view;
    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edMsg;
    private TextView txtSave;
    private EditText edTitle;
    private String title;
    private String msg;
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_feed, container, false);
        initView(view);
        id = App.getId();
        titleTitle.setText("意见反馈");


        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edTitle.getText().toString();
                msg = edMsg.getText().toString();
                if (title.equals("")){
                    Toast.makeText(getContext(),"请输入标题",Toast.LENGTH_SHORT).show();
                }else if (msg.equals("")){
                    Toast.makeText(getContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp();
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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);
        /**
         *  {"title":"222","content":"iiiii","userId":"1","createTime":"2021-02-12"}
         */
        new OKHttpTo()
                .setUrl("setuseradvice")
                .setType("post")
                .setJSONObject("title",title)
                .setJSONObject("content",msg)
                .setJSONObject("userId",id)
                .setJSONObject("createTime",str)
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
        fragmentTransaction.replace(R.id.fragment_mine, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        edMsg = view.findViewById(R.id.ed_msg);
        txtSave = view.findViewById(R.id.txt_save);
        edTitle = view.findViewById(R.id.ed_title);
    }
}
