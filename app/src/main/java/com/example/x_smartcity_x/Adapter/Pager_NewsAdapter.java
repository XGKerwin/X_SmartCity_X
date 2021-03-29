package com.example.x_smartcity_x.Adapter;

import android.content.Context;
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

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_arc;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class Pager_NewsAdapter extends RecyclerView.Adapter<Pager_NewsAdapter.ViewHolder> {

    private List<GetNewsAll> getNewsAlls;
    private Context context;
    private ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Pager_NewsAdapter(Context context, List<GetNewsAll> getNewsAlls) {
        this.context = context;
        this.getNewsAlls = getNewsAlls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pager_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNewsAll getNewsAll = getNewsAlls.get(position);
        holder.itemTxt.setText(getNewsAll.getTitle());
        holder.itemMsg.setText(getNewsAll.getContent());
        new OkHttpToImage()
                .setUrl(getNewsAll.getImgUrl())
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
                itemClickListener.onClick(position,getNewsAll.getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return getNewsAlls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
