package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Gethospitalreservationuser;

import java.util.List;

public class Inquire_adapter extends RecyclerView.Adapter<Inquire_adapter.ViewHolder> {

    private List<Gethospitalreservationuser> gethospitalreservationusers;


    public Inquire_adapter(List<Gethospitalreservationuser> gethospitalreservationusers) {
        this.gethospitalreservationusers = gethospitalreservationusers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inquire, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gethospitalreservationuser gethospitalreservationuser = gethospitalreservationusers.get(position);
        holder.keshi.setText(gethospitalreservationuser.getDivisionId());
        holder.feiyong.setText("普通门诊       "+gethospitalreservationuser.getMoeny()+"元");
        holder.time.setText("预约时间："+gethospitalreservationuser.getStartime()+"元");
    }

    @Override
    public int getItemCount() {
        return gethospitalreservationusers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btn;
        private TextView keshi;
        private TextView feiyong;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
            keshi = itemView.findViewById(R.id.keshi);
            feiyong = itemView.findViewById(R.id.feiyong);
            time = itemView.findViewById(R.id.time);
        }
    }
}
