package com.example.x_smartcity_x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.FpApplyListByUserid;
import com.example.x_smartcity_x.bean.FpVillageList;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Poverty_Workbench_help_adapter extends RecyclerView.Adapter<Poverty_Workbench_help_adapter.ViewHolder> {
    private List<FpApplyListByUserid> fpApplyListByUserids;
    private List<FpVillageList> list;

    public Poverty_Workbench_help_adapter(List<FpApplyListByUserid> fpApplyListByUserids) {
        this.fpApplyListByUserids = fpApplyListByUserids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fupin_bangfu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FpApplyListByUserid userid = fpApplyListByUserids.get(position);
        if (userid.getApplystate().equals("1")){
            holder.txtMsg.setText("状态：已通过");
        }else {
            holder.txtMsg.setText("状态：审核中");
        }

        holder.txtQiuzhuren.setText("扶贫简介："+userid.getApplydesc());
        holder.txtTime.setText("开始时间："+userid.getStarttime());

        if (list ==null) {
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        new OkHttpTo1()
                .setUrl("fpVillageList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        list.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<FpVillageList>>(){}.getType()));

                        for (int i=0;i<list.size();i++){
                            FpVillageList fpvill = list.get(i);
                            if (fpvill.getVillid() == userid.getVillid()){
                                holder.txtBiaoti.setText(fpvill.getVillname());
                            }
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    @Override
    public int getItemCount() {
        return fpApplyListByUserids.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtBiaoti;
        private TextView txtMsg;
        private TextView txtQiuzhuren;
        private TextView txtTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtBiaoti = view.findViewById(R.id.txt_biaoti);
            txtMsg = view.findViewById(R.id.txt_msg);
            txtQiuzhuren = view.findViewById(R.id.txt_qiuzhuren);
            txtTime = view.findViewById(R.id.txt_time);
        }
    }
}
