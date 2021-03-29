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

import com.example.x_smartcity_x.Adapter.Huodong_type_adapter;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetAllActions;

import java.util.ArrayList;
import java.util.List;

public class Fragment_huodong_type extends Fragment {

    private List<GetAllActions> getAllActions, allActionsList;
    private String string;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private RecyclerView recyclerView;

    public Fragment_huodong_type(List<GetAllActions> getAllActions, String s) {
        this.getAllActions = getAllActions;
        this.string = s;
    }

    //体育2  学习3  4政治

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_huodong_type, container, false);
        initView(view);
        titleTitle.setText("主题活动");

        getData();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_HuoDong());
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_huodong,fragment).commit();
    }

    private void getData() {
        allActionsList = new ArrayList<>();
        for (GetAllActions actions : getAllActions) {
            if (actions.getTypeid() .equals(string)) {
                allActionsList.add(actions);
            }
        }

        Huodong_type_adapter adapter = new Huodong_type_adapter(allActionsList,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void initView(View View) {
        titleBack = View.findViewById(R.id.title_back);
        titleTitle = View.findViewById(R.id.title_title);
        titleWode = View.findViewById(R.id.title_wode);
        recyclerView = View.findViewById(R.id.recyclerView);
    }
}
