package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetbusOrder;

import java.util.List;

public class Bus_wode_adapter extends RecyclerView.Adapter<Bus_wode_adapter.ViewHolder> {

    private List<GetbusOrder> getbusOrders;

    public Bus_wode_adapter(List<GetbusOrder> getbusOrders) {
        this.getbusOrders = getbusOrders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bus_wode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetbusOrder order = getbusOrders.get(position);
        holder.chehao.setText(order.getPath());
        holder.luxian.setText(order.getStart()+"---"+order.getEnd());
        holder.name.setText(order.getUsername());
        holder.piaojia.setText("票价："+order.getPrice()+"元");
    }

    @Override
    public int getItemCount() {
        return getbusOrders.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView chehao;
        private TextView luxian;
        private TextView piaojia;
        private TextView riqi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            chehao = itemView.findViewById(R.id.chehao);
            luxian = itemView.findViewById(R.id.luxian);
            piaojia = itemView.findViewById(R.id.piaojia);
            riqi = itemView.findViewById(R.id.riqi);
        }
    }
}
