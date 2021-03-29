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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetAllActions;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class HuoDong_adapter extends RecyclerView.Adapter<HuoDong_adapter.ViewHolder> {

    private List<GetAllActions> getAllActions;
    private FragmentActivity activity;

    public HuoDong_adapter(List<GetAllActions> getAllActions, FragmentActivity activity) {
        this.getAllActions = getAllActions;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_huodong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetAllActions actions = getAllActions.get(position);
        holder.xinwenTitle.setText(actions.getName());
        holder.xinwenMsg.setText(actions.getDescription());
        holder.dianbaoming.setText("报名人数："+actions.getCount());
        holder.dianzan.setText("点赞人数："+actions.getPraiseCount());

        new OkHttpToImage()
                .setUrl(actions.getImage())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.xinwenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong_msg(actions.getId()));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_huodong, fragment).commit();
    }

    @Override
    public int getItemCount() {
        return getAllActions.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout xinwenLayout;
        private ImageView xinwenImg;
        private TextView xinwenTitle;
        private TextView xinwenMsg;
        private TextView dianzan;
        private TextView dianbaoming;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xinwenLayout = itemView.findViewById(R.id.xinwen_layout);
            xinwenImg = itemView.findViewById(R.id.xinwen_img);
            xinwenTitle = itemView.findViewById(R.id.xinwen_title);
            xinwenMsg = itemView.findViewById(R.id.xinwen_msg);
            dianzan = itemView.findViewById(R.id.dianzan);
            dianbaoming = itemView.findViewById(R.id.dianbaoming);
        }
    }
}
