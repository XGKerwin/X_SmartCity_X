package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.x_smartcity_x.Adapter.Parking_list_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetParkingHistory;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Fragment_parking_list extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView textTime1;
    private TextView textTime2;
    private Button btnRuchang;
    private Button btnChuchang;
    private RecyclerView Recyclerview;
    private List<GetParkingHistory> getParkingHistories;
    private Parking_list_adapter adapter;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getokhttp1();
            return false;
        }
    });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_parking_list, container, false);
        initView(view);
        titleTitle.setText("停车历史查询");

        btn();

        getOkHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        return view;
    }

    private String time1, time2;

    private void btn() {

        btnChuchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getokhttp1();
            }
        });

        btnRuchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time1 = textTime1.getText().toString();
                time2 = textTime2.getText().toString();
                if (time1.equals("入场时间")) {
                    Toast.makeText(getContext(), "请输入入场时间", Toast.LENGTH_SHORT).show();
                } else if (time2.equals("出场时间")) {
                    Toast.makeText(getContext(), "请输入出场时间", Toast.LENGTH_SHORT).show();
                } else {
                    handler.sendEmptyMessage(0);
                }

            }
        });


        textTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtime(textTime1);
            }
        });

        textTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtime(textTime2);
            }
        });


    }

    private int nian, yue, day;
    private TimePickerView timePickerView;

    private void showtime(final TextView textView) {
        timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);                         //根据获选择的时间改变日期
                nian = calendar.get(Calendar.YEAR);
                yue = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                textView.setText(nian + "-" + yue + "-" + day);
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
        timePickerView.show();
    }

    private void getokhttp1() {
        getParkingHistories.clear();
        for (int i = 1; i <= 8; i++) {
            getokhttp2(i);
        }

    }

    private void getokhttp2(int i) {
        /**
         * {parkingid:1,"inTime1":"","inTime2":"2020-9-1"}
         */
        new OkHttpTo1()
                .setUrl("getParkingHistoryByIdAndInTime")
                .setJSONObject("parkingid", i)
                .setJSONObject("inTime1", time1)
                .setJSONObject("inTime2", time2)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getParkingHistories.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetParkingHistory>>() {
                                }.getType()));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getOkHttp() {
        if (getParkingHistories == null) {
            getParkingHistories = new ArrayList<>();
        } else {
            getParkingHistories.clear();
        }
        new OkHttpTo1().setUrl("getParkingHistory")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getParkingHistories.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetParkingHistory>>() {
                                }.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        Recyclerview.setLayoutManager(linearLayoutManager);

                        adapter = new Parking_list_adapter(getParkingHistories);
                        Recyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }


    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        textTime1 = view.findViewById(R.id.text_time1);
        textTime2 = view.findViewById(R.id.text_time2);
        btnRuchang = view.findViewById(R.id.btn_ruchang);
        btnChuchang = view.findViewById(R.id.btn_chuchang);
        Recyclerview = view.findViewById(R.id.Recyclerview);
    }

}
