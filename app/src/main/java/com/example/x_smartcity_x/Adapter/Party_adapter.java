package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.bean.Lists;

import java.util.List;

public class Party_adapter extends RecyclerView.Adapter<Party_adapter.ViewHolder> {

    private List<GetNewsAll> getNewsAlls;

    public Party_adapter(List<GetNewsAll> getNewsAlls) {
        this.getNewsAlls = getNewsAlls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_party, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNewsAll newsAll = getNewsAlls.get(position);
        holder.textview.setText(newsAll.getTitle());
    }

    @Override
    public int getItemCount() {
        return getNewsAlls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview);
        }
    }
}
