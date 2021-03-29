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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.GetAllActions;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

public class Huodong_type_adapter extends RecyclerView.Adapter<Huodong_type_adapter.ViewHolder1> {

    private List<GetAllActions> getAllActions;
    private FragmentActivity activity;

    public Huodong_type_adapter(List<GetAllActions> allActionsList, FragmentActivity activity) {
        this.getAllActions = allActionsList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_type, parent, false);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        GetAllActions actions = getAllActions.get(position);
        holder.xinwenTitle.setText(actions.getName());
        holder.xinwenMsg.setText(actions.getDescription());
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
        fragmentTransaction.replace(R.id.fragment_huodong,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return getAllActions.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private LinearLayout xinwenLayout;
        private ImageView xinwenImg;
        private TextView xinwenTitle;
        private TextView xinwenMsg;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            xinwenLayout = itemView.findViewById(R.id.xinwen_layout);
            xinwenImg = itemView.findViewById(R.id.xinwen_img);
            xinwenTitle = itemView.findViewById(R.id.xinwen_title);
            xinwenMsg = itemView.findViewById(R.id.xinwen_msg);
        }
    }
}
