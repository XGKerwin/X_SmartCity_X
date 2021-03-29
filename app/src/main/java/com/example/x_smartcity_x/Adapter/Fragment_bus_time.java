package com.example.x_smartcity_x.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetBusList;

import java.util.List;

public class Fragment_bus_time extends Fragment {

    private GetBusList busList;
    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerView;
    private TextView txtTime;
    private Button btnXiayibu;
    private BusTime_adapter adapter;

    public Fragment_bus_time(GetBusList busList) {
        this.busList = busList;
    }

    private String[] arr = {"", "", "", "", "1", "2", "3",
            "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24",
            "25", "26", "27", "28", "29", "30", ""};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bus_time, container, false);
        initView(view);

        showtime();

        return view;
    }

    private void showtime() {
        if (adapter == null){
            adapter = new BusTime_adapter(arr,getActivity(),txtTime,btnXiayibu,busList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),7);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        recyclerView = View.findViewById(R.id.recyclerView);
        txtTime = View.findViewById(R.id.txt_time);
        btnXiayibu = View.findViewById(R.id.btn_xiayibu);
    }
}
