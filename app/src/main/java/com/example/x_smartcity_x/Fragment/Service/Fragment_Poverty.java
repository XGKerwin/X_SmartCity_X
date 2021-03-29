package com.example.x_smartcity_x.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;

public class Fragment_Poverty extends Fragment {


    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private ImageView btnXinwen;
    private ImageView btnFupinanli;
    private ImageView btnCunqing;
    private ImageView btnGongzuotai;
    private String userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_poverty, container, false);
        initView(view);
        titleTitle.setText("精准扶贫");
        userid = App.getId();
        btn();

        return view;
    }

    private void btn() {
        //工作台
        btnGongzuotai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Poverty_Workbench());
                }

            }
        });
        //村情
        btnCunqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_fupin_cun());
            }
        });

        btnFupinanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    getFragment(new Fragment_fuwu_fupin_anli());
                }

            }
        });

        btnXinwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Poverty_News());
            }
        });


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_poverty,fragment).commit();
    }


    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        titleWode = view.findViewById(R.id.title_wode);
        btnXinwen = view.findViewById(R.id.btn_xinwen);
        btnFupinanli = view.findViewById(R.id.btn_fupinanli);
        btnCunqing = view.findViewById(R.id.btn_cunqing);
        btnGongzuotai = view.findViewById(R.id.btn_gongzuotai);
    }
}
