package com.example.x_smartcity_x.bean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;

import java.util.List;

public class Mine_Order_adapter extends RecyclerView.Adapter<Mine_Order_adapter.ViewHolder> {

    private List<GetOrderDetails> getOrderDetails;

    public Mine_Order_adapter(List<GetOrderDetails> getOrderDetails) {
        this.getOrderDetails = getOrderDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetOrderDetails details = getOrderDetails.get(position);
        holder.commodityName.setText(details.getCommodityName());
        holder.commodityPrice.setText("金额："+details.getCommodityPrice()+"元");
    }

    @Override
    public int getItemCount() {
        return getOrderDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btnOrder;
        private TextView commodityName;
        private TextView commodityPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnOrder = itemView.findViewById(R.id.btn_order);
            commodityName = itemView.findViewById(R.id.commodityName);
            commodityPrice = itemView.findViewById(R.id.commodityPrice);
        }
    }
}
