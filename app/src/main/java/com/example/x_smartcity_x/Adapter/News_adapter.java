package com.example.x_smartcity_x.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.Activity.NewsFragment;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_arc;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class News_adapter extends RecyclerView.Adapter<News_adapter.ViewHolder> {

    private List<GetNewsAll> getNewsAlls;
    private Context context;
    private FragmentActivity activity;

    public News_adapter(List<GetNewsAll> newrecommend, Context context, FragmentActivity activity) {
        this.getNewsAlls = newrecommend;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pager_news, parent, false);
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
                getFragment(new NewsFragment(all.getId()-1+""));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_new,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return 3;
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
