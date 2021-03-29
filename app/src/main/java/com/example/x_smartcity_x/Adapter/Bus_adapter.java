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
import com.example.x_smartcity_x.bean.GetBusList;

import java.util.List;

public class Bus_adapter extends RecyclerView.Adapter<Bus_adapter.ViewHolder> {

    private List<GetBusList> busLists;
    private FragmentActivity activity;

    public Bus_adapter(List<GetBusList> getBusLists, FragmentActivity activity) {
        this.busLists = getBusLists;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bus, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetBusList list = busLists.get(position);
        holder.number.setText(list.getNumber()+"路");
        holder.piaojia.setText("票价："+list.getPrice()+"元");
        holder.licheng.setText("里程："+list.getMileage()+"KM");
        holder.txtQi.setText(list.getFirst());
        holder.zhi.setText(list.getEnd());
        holder.time1.setText("起："+list.getStartTime()+"终："+list.getEndTime());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_bus_1(list));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return busLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView number;
        private TextView piaojia;
        private TextView licheng;
        private TextView txtQi;
        private TextView zhi;
        private TextView time1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            number = itemView.findViewById(R.id.number);
            piaojia = itemView.findViewById(R.id.piaojia);
            licheng = itemView.findViewById(R.id.licheng);
            txtQi = itemView.findViewById(R.id.txt_qi);
            zhi = itemView.findViewById(R.id.zhi);
            time1 = itemView.findViewById(R.id.time1);
        }
    }
}
