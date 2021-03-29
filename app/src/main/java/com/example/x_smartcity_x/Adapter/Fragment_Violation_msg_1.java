package com.example.x_smartcity_x.Adapter;

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

import com.example.x_smartcity_x.Fragment.Service.Fragment_Violation_msg;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Lllegal;

import java.util.List;

public class Fragment_Violation_msg_1 extends Fragment {


    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private TextView txtTime;
    private TextView txtDidian;
    private TextView txtNo;
    private TextView txtKoufen;
    private TextView txtFakuan;
    private Lllegal lllegal;
    private List<Lllegal> lllegals;

    public Fragment_Violation_msg_1(Lllegal lllegal, List<Lllegal> lllegals) {
        this.lllegal = lllegal;
        this.lllegals = lllegals;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_violation_msg_1, container, false);
        initView(view);
        titleTitle.setText("违章详情");
        txtKoufen.setText("违章扣分："+lllegal.getDeductMarks()+"分");
        txtDidian.setText("违章地点："+lllegal.getIllegalSites());
        txtTime.setText("违章时间："+lllegal.getBadTime());
        txtFakuan.setText("违章罚款："+lllegal.getMoney());
        txtNo.setText("车牌号："+lllegal.getLicencePlate());



        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Violation_msg(lllegals));
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_violation,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        titleWode = view.findViewById(R.id.title_wode);
        txtTime = view.findViewById(R.id.txt_time);
        txtDidian = view.findViewById(R.id.txt_didian);
        txtNo = view.findViewById(R.id.txt_no);
        txtKoufen = view.findViewById(R.id.txt_koufen);
        txtFakuan = view.findViewById(R.id.txt_fakuan);
    }
}
