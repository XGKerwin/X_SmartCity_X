package com.example.x_smartcity_x.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;

import java.util.Map;

public class Pager_ThemeAdapter extends RecyclerView.Adapter<Pager_ThemeAdapter.ViewHolder> {
    private Context context;
    private Map<String, String> map;
    private ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Pager_ThemeAdapter(Context context, Map<String, String> map) {
        this.context = context;
        this.map = map;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pager_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.itemImg.setImageResource(R.drawable.zhuti_guoqing);
                break;
            case 1:
                holder.itemImg.setImageResource(R.drawable.zhuti_kangfeiyan);
                break;
            case 2:
                holder.itemImg.setImageResource(R.drawable.zhuti_dianying);
                break;
            case 3:
                holder.itemImg.setImageResource(R.drawable.zhuti_lieshi);
                break;
        }

        holder.itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position,position+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_img);
        }
    }
}
