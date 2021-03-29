package com.example.x_smartcity_x.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetBusList;

public class BusTime_adapter extends RecyclerView.Adapter<BusTime_adapter.ViewHolder> {

    private String[] arr;
    private FragmentActivity activity;
    private TextView textView;
    private ItemClickListener itemClickListener;
    private Button btn;
    private String s = "2020-4-1";
    private GetBusList list;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BusTime_adapter(String[] arr, FragmentActivity activity, TextView txtTime, Button btnXiayibu, GetBusList busList) {
        this.btn = btnXiayibu;
        this.arr = arr;
        this.activity = activity;
        this.textView = txtTime;
        this.list = busList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bus_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (arr[position].equals("")){

        }else {
            if (position % 7 == 0 || position%7==6){
                holder.layout.setBackgroundColor(Color.parseColor("#B4FFF8"));
            }
        }

        holder.txtview.setText(arr[position]);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.layout.setBackgroundColor(Color.parseColor("#FF6363"));
                textView.setText("2020-4-"+arr[position]);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = textView.getText().toString();
                getFragment(new Fragment_bus_name(s,list));
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtview;
        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            txtview = itemView.findViewById(R.id.txtview);
        }
    }
}
