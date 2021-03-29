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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetServiceAll;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class Pager_ServiceAdapter extends RecyclerView.Adapter<Pager_ServiceAdapter.ViewHolder> {

    private List<GetServiceAll> getServiceAlls;
    private Context context;
    public ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Pager_ServiceAdapter(List<GetServiceAll> getService, Context context) {
        this.getServiceAlls = getService;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pager_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 9){
            holder.itemTxt.setText("更多服务");
            holder.itemImg.setImageResource(R.drawable.fuwu_gengduo);
        }else {
            GetServiceAll all = getServiceAlls.get(position);
            holder.itemTxt.setText(all.getServiceName());
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
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.itemTxt.getText().toString();
                itemClickListener.onClick(position,s);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImg;
        private TextView itemTxt;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_img);
            itemTxt = itemView.findViewById(R.id.item_txt);
            linearLayout = itemView.findViewById(R.id.item_lin);
        }
    }
}
