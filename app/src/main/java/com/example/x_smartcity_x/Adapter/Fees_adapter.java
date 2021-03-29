package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.ChargeList;

import java.util.List;

public class Fees_adapter extends RecyclerView.Adapter<Fees_adapter.ViewHolder> {

    private List<ChargeList> lists;

    public Fees_adapter(List<ChargeList> chargeLists) {
        this.lists = chargeLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fees, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChargeList chargeList = lists.get(position);
        holder.txtTitle.setText(chargeList.getType());
        holder.txtHuming.setText("张三");
        holder.bianhao.setText("编号："+chargeList.getAccountId());
        holder.danwei.setText("单位："+chargeList.getUnit());
        holder.txtZhuzhi.setText("住址："+chargeList.getAccountAddress());
        holder.txtKeyong.setText("可用余额："+chargeList.getBanlance());
        int s = chargeList.getBanlance() - chargeList.getCost();
        holder.txtQianfei.setText("欠费余额："+s);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtHuming;
        private TextView bianhao;
        private TextView danwei;
        private TextView txtZhuzhi;
        private TextView txtKeyong;
        private TextView txtQianfei;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtHuming = itemView.findViewById(R.id.txt_huming);
            bianhao = itemView.findViewById(R.id.bianhao);
            danwei = itemView.findViewById(R.id.danwei);
            txtZhuzhi = itemView.findViewById(R.id.txt_zhuzhi);
            txtKeyong = itemView.findViewById(R.id.txt_keyong);
            txtQianfei = itemView.findViewById(R.id.txt_qianfei);
        }
    }

}
