package com.example.x_smartcity_x.Fragment.Service;

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

import com.example.x_smartcity_x.Adapter.Spinner1_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Lllegal;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Violation extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private EditText edCph;
    private EditText edFadongji;
    private Spinner spinner;
    private String type = "小型汽车";
    private Button btnChaxun;
    private Spinner1_adapter adapter;
    private String[] arr = {"小型汽车","中型汽车","大型汽车"};
    private List<Lllegal> lllegals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_violation, container, false);
        initView(view);
        titleTitle.setText("违章查询");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = arr[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter = new Spinner1_adapter(getContext(),arr);
        spinner.setAdapter(adapter);

        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cph = "鲁"+edCph.getText().toString();
                String fadongji = edFadongji.getText().toString();
                getOkHttp(cph,fadongji);
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

    private void getOkHttp(String cph, String fadongji) {

        /**
         * {"licencePlate":"鲁123","engineNumber":"1234","catType":"小型汽车"}
         */

        if (lllegals == null){
            lllegals = new ArrayList<>();
        }else {
            lllegals.clear();
        }
        new OKHttpTo()
                .setUrl("illegal")
                .setType("get")
                .setJSONObject("licencePlate",cph)
                .setJSONObject("engineNumber",fadongji)
                .setJSONObject("catType",type)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lllegals.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<Lllegal>>(){}.getType()));
                        if (lllegals.size()==0){
                            Toast.makeText(getContext(),"未找到此车牌",Toast.LENGTH_SHORT).show();
                        }else {
                            getFragment(new Fragment_Violation_msg(lllegals));
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_violation, fragment).commit();
    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        titleWode = View.findViewById(R.id.title_wode);
        edCph = View.findViewById(R.id.ed_cph);
        edFadongji = View.findViewById(R.id.ed_fadongji);
        spinner = View.findViewById(R.id.spinner);
        btnChaxun = View.findViewById(R.id.btn_chaxun);
    }
}
