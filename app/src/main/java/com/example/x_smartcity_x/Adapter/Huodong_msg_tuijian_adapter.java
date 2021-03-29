package com.example.x_smartcity_x.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetRecommandAction;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class Huodong_msg_tuijian_adapter extends RecyclerView.Adapter<Huodong_msg_tuijian_adapter.ViewHolder> {

    private List<GetRecommandAction> getRecommandActions;

    public Huodong_msg_tuijian_adapter(List<GetRecommandAction> getRecommandActions) {
        this.getRecommandActions = getRecommandActions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_huodong_tuijian, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetRecommandAction action = getRecommandActions.get(position);
        holder.xinwenTitle.setText(action.getName());
        holder.xinwenMsg.setText(action.getDescription());
        new OkHttpToImage()
                .setUrl(action.getImage())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout xinwenLayout;
        private ImageView xinwenImg;
        private TextView xinwenTitle;
        private TextView xinwenMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xinwenLayout = itemView.findViewById(R.id.xinwen_layout);
            xinwenImg = itemView.findViewById(R.id.xinwen_img);
            xinwenTitle = itemView.findViewById(R.id.xinwen_title);
            xinwenMsg = itemView.findViewById(R.id.xinwen_msg);
        }
    }
}
