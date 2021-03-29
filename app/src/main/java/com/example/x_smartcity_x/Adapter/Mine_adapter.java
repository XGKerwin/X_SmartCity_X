package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetOrderForm;

import java.util.List;

public class Mine_adapter extends RecyclerView.Adapter<Mine_adapter.ViewHolder> {
    private List<GetOrderForm> getOrderForms;
    private ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Mine_adapter(List<GetOrderForm> getOrderForms) {
        this.getOrderForms = getOrderForms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetOrderForm orderForm = getOrderForms.get(position);
        holder.orderTime.setText(orderForm.getOrderTime());
        holder.orderStatus.setText("状态："+orderForm.getOrderStatus());
        holder.txtNumber.setText("编号："+orderForm.getOrderNum());
        holder.orderType.setText(orderForm.getOrderType());
        holder.shopName.setText("地址："+orderForm.getShopName());
        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position,orderForm.getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return getOrderForms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btnOrder;
        private TextView orderType;
        private TextView txtNumber;
        private TextView shopName;
        private TextView orderStatus;
        private TextView orderTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnOrder = itemView.findViewById(R.id.btn_order);
            orderType = itemView.findViewById(R.id.orderType);
            txtNumber = itemView.findViewById(R.id.txt_number);
            shopName = itemView.findViewById(R.id.shopName);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTime = itemView.findViewById(R.id.orderTime);
        }
    }
}
