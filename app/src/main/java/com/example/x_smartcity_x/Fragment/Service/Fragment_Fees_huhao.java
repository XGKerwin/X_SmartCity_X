package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.Adapter.Fees_Account_adapter;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.AccountByUserId;
import com.example.x_smartcity_x.bean.AccountGroup;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Fees_huhao extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private ExpandableListView expandableListView;
    private Button btnNew;
    private String user;
    private List<AccountGroup> accountGroups;
    private Fees_Account_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fees_huhao,container,false);
        initView(view);
        user = App.getId();
        titleTitle.setText("户号管理");

        btn();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getOkHttp();
    }

    private void getOkHttp() {
        if (accountGroups == null){
            accountGroups= new ArrayList<>();
        }else {
            accountGroups.clear();
        }
        new OkHttpTo1()
                .setUrl("accountGroup")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        accountGroups.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<AccountGroup>>(){}.getType()));
                        getData();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private List<AccountByUserId> accountByUserIds;
    private void getData() {
        if (accountByUserIds == null){
            accountByUserIds = new ArrayList<>();
        }else {
            accountByUserIds.clear();
        }
        new OkHttpTo1()
                .setUrl("accountByUserId")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        accountByUserIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<AccountByUserId>>(){}.getType()));
                        getShow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private Map<AccountGroup, List<AccountByUserId>> map;
    private void getShow() {
        if (map ==null){
            map = new HashMap<>();
        }else {
            map.clear();
        }
        for (int i = 0; i < accountGroups.size(); i++) {
            AccountGroup accoutGroup = accountGroups.get(i);
            List<AccountByUserId> detailsList = new ArrayList<>();
            for (int j = 0; j < accountByUserIds.size(); j++) {
                AccountByUserId hoDetail = accountByUserIds.get(j);
                if (hoDetail.getGroupId() == accoutGroup.getIndex()) {
                    detailsList.add(hoDetail);
                }
            }
            map.put(accoutGroup, detailsList);
        }
        adapter = new Fees_Account_adapter(accountGroups,map);
        expandableListView.setAdapter(adapter);
    }

    private void btn() {

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
//                    getFragment(new Fragment_Service_Payment_Account_1());
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Fees());
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_fees,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        expandableListView = view.findViewById(R.id.ExpandableListView);
        btnNew = view.findViewById(R.id.btn_new);
    }

}
