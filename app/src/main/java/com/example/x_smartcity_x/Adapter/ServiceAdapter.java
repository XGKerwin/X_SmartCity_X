package com.example.x_smartcity_x.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetServiceAll;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private List<GetServiceAll> getServiceAlls;

    public ServiceAdapter(List<GetServiceAll> serviceAlls) {
        this.getServiceAlls = serviceAlls;
    }

    private ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetServiceAll byType = getServiceAlls.get(position);
        holder.fuwuTxt.setText(byType.getServiceName());
        holder.btnRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position,byType.getServiceName());
            }
        });

        new OkHttpToImage()
                .setUrl(byType.getImgUrl())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.fuwuImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

    }

    @Override
    public int getItemCount() {
        return getServiceAlls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout btnRegistered;
        private ImageView fuwuImg;
        private TextView fuwuTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnRegistered = itemView.findViewById(R.id.btn_registered);
            fuwuImg = itemView.findViewById(R.id.fuwu_img);
            fuwuTxt = itemView.findViewById(R.id.fuwu_txt);
        }
    }
}
