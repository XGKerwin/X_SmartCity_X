package com.example.x_smartcity_x.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.SubwayStation;

import java.util.List;

public class Aubway_msg_adapter extends RecyclerView.Adapter<Aubway_msg_adapter.ViewHolder> {

    private List<SubwayStation> subwayStations;
    private String name;

    public Aubway_msg_adapter(List<SubwayStation> subwayStations, String currentName) {
        this.subwayStations = subwayStations;
        this.name = currentName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_aubway_msg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubwayStation subwayStation = subwayStations.get(position);
        if (name.equals(subwayStation.getName())){
            holder.txtDidian.setTextColor(Color.RED);
        }else {
            holder.txtDidian.setTextColor(Color.BLACK);
        }
        holder.txtDidian.setText(subwayStation.getName());



    }

    @Override
    public int getItemCount() {
        return subwayStations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDidian;
        private TextView txtTime;
        private TextView txtJvli;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDidian = itemView.findViewById(R.id.txt_didian);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtJvli = itemView.findViewById(R.id.txt_jvli);
        }
    }
}
