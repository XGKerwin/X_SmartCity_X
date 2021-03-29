package com.example.x_smartcity_x.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_arc;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class New_List2_Adapter extends RecyclerView.Adapter<New_List2_Adapter.ViewHolder> {

    private List<GetNewsAll> getNewsAlls;
    private ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public New_List2_Adapter(List<GetNewsAll> getNewsAlls) {
        this.getNewsAlls = getNewsAlls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pager_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNewsAll all = getNewsAlls.get(position);
        holder.itemTxt.setText(all.getTitle());
        holder.itemMsg.setText(all.getContent());
        new OkHttpToImage()
                .setUrl(all.getImgUrl())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.itemImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
        holder.itemLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position,all.getId()-1+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return getNewsAlls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout itemLin;
        private MyImageView_arc itemImg;
        private TextView itemTxt;
        private TextView itemMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLin = itemView.findViewById(R.id.item_lin);
            itemImg = itemView.findViewById(R.id.item_img);
            itemTxt = itemView.findViewById(R.id.item_txt);
            itemMsg = itemView.findViewById(R.id.item_msg);
        }
    }
}
