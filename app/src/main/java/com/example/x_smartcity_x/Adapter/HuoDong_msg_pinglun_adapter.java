package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetActionCommitById;

import java.util.List;

public class HuoDong_msg_pinglun_adapter extends RecyclerView.Adapter<HuoDong_msg_pinglun_adapter.ViewHOlder> {

    private List<GetActionCommitById> getActionCommitByIds;


    public HuoDong_msg_pinglun_adapter(List<GetActionCommitById> getActionCommitByIds) {
        this.getActionCommitByIds = getActionCommitByIds;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_huodong_pinglun, parent, false);
        return new ViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {
        GetActionCommitById commitById = getActionCommitByIds.get(position);
        holder.name.setText(commitById.getUsername());
        holder.msg.setText(commitById.getCommitContent());
        holder.time.setText(commitById.getCommitTime());

    }

    @Override
    public int getItemCount() {
        return getActionCommitByIds.size();
    }

    class ViewHOlder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView msg;
        private TextView time;

        public ViewHOlder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
            time = itemView.findViewById(R.id.time);
        }
    }
}
