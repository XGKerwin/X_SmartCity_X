package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Adapter.Fuwu_fupin_anli_adapter;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.FpCaseList;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Fragment_fuwu_fupin_anli extends Fragment {


    private ImageView back;
    private TextView title;
    private TextView txtWode;
    private RecyclerView recyclerview;
    private Fuwu_fupin_anli_adapter adapter;
    private String user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fupin_anli, container, false);
        initView(view);
        user = App.getId();
        title.setText("扶贫案例");

        getOkHttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty());
            }
        });
        return view;
    }

    private List<FpCaseList> examples;
    private void getOkHttp() {
        if (examples == null){
            examples = new ArrayList<>();
        }else {
            examples.clear();
        }
        new OkHttpTo1()
                .setUrl("fpCaseList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        examples.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpCaseList>>(){}.getType()));

                        Collections.sort(examples, new Comparator<FpCaseList>() {
                            @Override
                            public int compare(FpCaseList o1, FpCaseList o2) {
                                //2020-04-03 00:00:00
                                Date date = null;
                                Date date1 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date = format.parse(o1.getReporttime());
                                    date1 = format.parse(o2.getReporttime());

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date1.compareTo(date);
                            }
                        });

                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new Fuwu_fupin_anli_adapter(examples,user,getActivity());
                        }else {
                            adapter.notifyDataSetChanged();
                        }
                        recyclerview.setAdapter(adapter);

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

    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_title);
        txtWode = view.findViewById(R.id.title_wode);
        recyclerview = view.findViewById(R.id.recyclerview);
    }


}
