package com.example.x_smartcity_x.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.ItemClickListener;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_arc;
import com.example.x_smartcity_x.bean.Gethospitallist;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class Hospital_adapter extends RecyclerView.Adapter<Hospital_adapter.ViewHolder> {

    private List<Gethospitallist> gethospitallists;
    private ItemClickListener itemClickListener;


    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public Hospital_adapter(List<Gethospitallist> gethospitallists) {
        this.gethospitallists = gethospitallists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gethospitallist gethospitallist = gethospitallists.get(position);
        holder.title.setText(gethospitallist.getHospitalName());
        int s = gethospitallist.getLevel();
        holder.ratingBar.setRating(s);
        new OkHttpToImage()
                .setUrl(gethospitallist.getImgUrl())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position,gethospitallist.getId()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return gethospitallists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lin;
        private MyImageView_arc img;
        private TextView title;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lin = itemView.findViewById(R.id.lin);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
