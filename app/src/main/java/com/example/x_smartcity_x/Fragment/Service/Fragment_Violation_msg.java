package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.util.Log;
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

import com.example.x_smartcity_x.Adapter.Violation_msg_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Lllegal;

import java.util.List;

public class Fragment_Violation_msg extends Fragment {

    private List<Lllegal> lllegals;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private RecyclerView recyclerView;
    private Violation_msg_adapter adapter;
    private int i = 1;
    private TextView txtGengduo;
    private static final String TAG = "Fragment_Violation_msg";

    public Fragment_Violation_msg(List<Lllegal> lllegals) {
        this.lllegals = lllegals;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_violation_msg, container, false);
        initView(view);
        titleTitle.setText("违章查询");

        getshow();


        txtGengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+lllegals.size());
                adapter = new Violation_msg_adapter(lllegals, getActivity(), lllegals.size());
                recyclerView.setAdapter(adapter);
                txtGengduo.setVisibility(View.GONE);
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Violation());
            }
        });

        return view;
    }

    private void getshow() {
        if (adapter == null) {
            adapter = new Violation_msg_adapter(lllegals, getActivity(), i);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }


    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_violation, fragment).commit();
    }

    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        titleWode = View.findViewById(R.id.title_wode);
        recyclerView = View.findViewById(R.id.recyclerView);
        txtGengduo = View.findViewById(R.id.txt_gengduo);
    }
}
