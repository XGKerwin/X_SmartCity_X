package com.example.x_smartcity_x.Fragment.Service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Poverty_Workbench_Case_adapter;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpTo1;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_Poverty_Workbench_Case extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private EditText edTitle;
    private EditText edMsg;
    private RecyclerView recyclerview;
    private Button btnTijiao;
    private String user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_poverty_workbench_anli, container, false);
        initView(view);
        titleTitle.setText("我的案例发布");
        user = App.getId();

        getshowReaylist();

        btn();

        return view;
    }

    private void btn() {
        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String biaoti = edTitle.getText().toString();
                String msg = edMsg.getText().toString();

                if (biaoti.equals("")){
                    Toast.makeText(getContext(),"请输入标题",Toast.LENGTH_SHORT).show();
                }else if (msg.equals("")){
                    Toast.makeText(getContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    setOkHttp(biaoti,msg);
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty_Workbench());
            }
        });
    }

    private void setOkHttp(String biaoti, String msg) {
        /**
         * {casetitle:"111",casepicture:"a.jpg",reporttime:"2020-10-2","userid":1,"caeContent":""}
         * {casetitle:"111",casepicture:"a.jpg",reporttime:"2020-10-2","userid":1,"caeContent":""}
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);

        new OkHttpTo1()
                .setUrl("fpPublicCase")
                .setJSONObject("casetitle",biaoti)
                .setJSONObject("casepicture","a.jpg")
                .setJSONObject("reporttime",time)
                .setJSONObject("userid",user)
                .setJSONObject("caseContent",msg)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_Poverty_Workbench());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_poverty,fragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bitmap bm = (Bitmap) data.getExtras().get("data");
        bitmaps.add(0, bm);
        adapter.notifyDataSetChanged();

        super.onActivityResult(requestCode, resultCode, data);
    }

    private List<Bitmap> bitmaps;
    private Poverty_Workbench_Case_adapter adapter;

    private void getshowReaylist() {
        bitmaps = new ArrayList<>();
        bitmaps.add(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);

        adapter = new Poverty_Workbench_Case_adapter(bitmaps);
        recyclerview.setAdapter(adapter);

        adapter.setItemOnclick(new Poverty_Workbench_Case_adapter.ItemOnclick() {
            @Override
            public void setOnclick(int position, String s) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCameraIntent, 1);
            }
        });
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        titleWode = view.findViewById(R.id.title_wode);
        edTitle = view.findViewById(R.id.ed_title);
        edMsg = view.findViewById(R.id.ed_msg);
        recyclerview = view.findViewById(R.id.recyclerview);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
