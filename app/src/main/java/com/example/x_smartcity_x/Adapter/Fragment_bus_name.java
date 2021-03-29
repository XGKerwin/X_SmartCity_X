package com.example.x_smartcity_x.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetBusList;
import com.example.x_smartcity_x.bean.Getbusline;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_bus_name extends Fragment {

    private String time;
    private GetBusList busList;
    private ImageView titleBack;
    private TextView titleTitle;
    private Spinner spinner1;
    private Spinner spinner2;
    private EditText txtName;
    private EditText txtTel;
    private Button btnXiayibu;
    private Spinner_adapter adapter;
    private List<Getbusline> getbuslines;
    private static final String TAG = "Fragment_bus_name";
    private String shangche;
    private String xiache;

    public Fragment_bus_name(String s, GetBusList list) {
        this.time = s;
        this.busList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bus_name, container, false);
        initView(view);
        titleTitle.setText("智慧巴士");

        getOKHttp();

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shangche = getbuslines.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                xiache = getbuslines.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String tel = txtTel.getText().toString();
                if (shangche.equals("")){
                    Toast.makeText(getContext(),"请输入上车地点",Toast.LENGTH_SHORT).show();
                }else if (xiache.equals("")){
                    Toast.makeText(getContext(),"请输入下车地点",Toast.LENGTH_SHORT).show();
                }else if (name.equals("")){
                    Toast.makeText(getContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_bus_save(time,name,tel,shangche,xiache,busList));
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_bus_time(busList));
            }
        });
        return view;
    }

    private void getOKHttp() {
        if (getbuslines == null){
            getbuslines =new ArrayList<>();
        }else {
            getbuslines.clear();
        }
        new OKHttpTo()
                .setUrl("getbusline")
                .setType("get")
                .setJSONObject("number",busList.getNumber())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String name = jsonObject.optString("msg");
                        if (name.equals("操作成功")){
                            getbuslines.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                    new TypeToken<List<Getbusline>>(){}.getType()));

                            show();
                        }else {
                            Toast.makeText(getContext(),"网络失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        adapter = new Spinner_adapter(getContext(),getbuslines);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus,fragment).commit();
    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        spinner1 = View.findViewById(R.id.spinner1);
        spinner2 = View.findViewById(R.id.spinner2);
        txtName = View.findViewById(R.id.txt_name);
        txtTel = View.findViewById(R.id.txt_tel);
        btnXiayibu = View.findViewById(R.id.btn_xiayibu);
    }
}
