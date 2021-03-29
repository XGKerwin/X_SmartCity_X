package com.example.x_smartcity_x.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_round;
import com.example.x_smartcity_x.bean.GetCommentByPressId;

import java.util.List;

public class News_CommentAdapter extends RecyclerView.Adapter<News_CommentAdapter.ViewHolder> {

    private List<GetCommentByPressId> getCommentByPressIds;
    private Context context;

    public News_CommentAdapter(List<GetCommentByPressId> getCommentByPressIds, Context context) {
        this.context = context;
        this.getCommentByPressIds = getCommentByPressIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetCommentByPressId pressId = getCommentByPressIds.get(position);
        holder.name.setText(pressId.getNickName());
        holder.msg.setText(pressId.getContent());
        holder.time.setText(pressId.getCreateTime());

    }

    @Override
    public int getItemCount() {
        return getCommentByPressIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MyImageView_round img;
        private TextView name;
        private TextView msg;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
            time = itemView.findViewById(R.id.time);
        }
    }
}
