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

import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Gethospitaldepartments;

import java.util.List;

public class Hospital_Department_adapter extends RecyclerView.Adapter<Hospital_Department_adapter.ViewHolder> {

    private List<Gethospitaldepartments> gethospitaldepartments;
    private FragmentActivity activity;
    private String id;

    public Hospital_Department_adapter(List<Gethospitaldepartments> gethospitaldepartments, FragmentActivity activity, String id) {
        this.gethospitaldepartments = gethospitaldepartments;
        this.activity = activity;
        this.id = id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_depart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gethospitaldepartments ments = gethospitaldepartments.get(position);
        holder.categoryName.setText(ments.getCategoryName());
        holder.tag.setText("Tag:"+ments.getTag());
        holder.desc.setText(ments.getDesc());
        holder.money.setText("挂号费："+ments.getMoney()+"元");

        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.setMoeny(ments.getMoney());
                App.setDivisionId(ments.getCategoryName());
                getFragment(new Fragment_Hospital_Doctors(id));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_hospital,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return gethospitaldepartments.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btnOrder;
        private TextView categoryName;
        private TextView desc;
        private TextView tag;
        private TextView money;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnOrder = itemView.findViewById(R.id.btn_order);
            categoryName = itemView.findViewById(R.id.categoryName);
            desc = itemView.findViewById(R.id.desc);
            tag = itemView.findViewById(R.id.tag);
            money = itemView.findViewById(R.id.money);
        }
    }
}
