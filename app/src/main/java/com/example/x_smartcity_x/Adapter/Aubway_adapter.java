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
import com.example.x_smartcity_x.bean.SubwayStationRoute;

import java.util.List;

public class Aubway_adapter extends RecyclerView.Adapter<Aubway_adapter.ViewHolder> {

    private List<SubwayStationRoute> subwayStationRoutes;
    private FragmentActivity activity;

    public Aubway_adapter(List<SubwayStationRoute> subwayStationRoutes, FragmentActivity activity) {
        this.subwayStationRoutes = subwayStationRoutes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_aubway, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubwayStationRoute route = subwayStationRoutes.get(position);
        holder.txtDitie.setText(route.getLineName());
        holder.txtTime.setText(route.getReachTime()+"min");
        holder.txtdidian.setText(route.getNextName());
        holder.btnRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_aubway_msg(route));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_subway,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return subwayStationRoutes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btnRegistered;
        private TextView txtDitie;
        private TextView txtTime;
        private TextView txtdidian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnRegistered = itemView.findViewById(R.id.btn_registered);
            txtDitie = itemView.findViewById(R.id.txt_ditie);
            txtdidian = itemView.findViewById(R.id.txt_didian);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}
