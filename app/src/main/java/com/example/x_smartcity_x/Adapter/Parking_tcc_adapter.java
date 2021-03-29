package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Parklotlist;

import java.util.List;

public class Parking_tcc_adapter extends RecyclerView.Adapter<Parking_tcc_adapter.ViewHolder> {

    private List<Parklotlist> parklotlists;
    private int i;
    private FragmentActivity activity;

    public Parking_tcc_adapter(List<Parklotlist> parklotlists, int i, FragmentActivity activity) {
        this.parklotlists = parklotlists;
        this.i = i;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_parking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Parklotlist parklotlist = parklotlists.get(position);
        holder.dizhi.setText("地址："+parklotlist.getAddress());
        holder.jvli.setText("距离："+parklotlist.getDistance());
        holder.shengyu.setText("剩余车位："+parklotlist.getAllPark());
        holder.shoufei.setText("1天最高收费："+parklotlist.getAllPark()+"元  "+parklotlist.getRates()+"/小时");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_parking_msg(parklotlist));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_parking,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return i;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView title;
        private TextView dizhi;
        private TextView zhuangtai;
        private TextView jvli;
        private TextView shoufei;
        private TextView shengyu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            title = itemView.findViewById(R.id.title);
            dizhi = itemView.findViewById(R.id.dizhi);
            jvli = itemView.findViewById(R.id.jvli);
            shoufei = itemView.findViewById(R.id.shoufei);
            shengyu = itemView.findViewById(R.id.shengyu);
        }
    }
}
