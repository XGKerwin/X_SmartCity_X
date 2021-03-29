package com.example.x_smartcity_x.Adapter;

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

import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Lllegal;

import java.util.List;

public class Violation_msg_adapter extends RecyclerView.Adapter<Violation_msg_adapter.ViewHolder> {

    private List<Lllegal> lllegals;
    private FragmentActivity activity;
    private int i;

    public Violation_msg_adapter(List<Lllegal> lllegals, FragmentActivity activity, int i) {
        this.activity = activity;
        this.lllegals = lllegals;
        this.i = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_violation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lllegal lllegal = lllegals.get(position);
        holder.txtDiain.setText("违章地点：" + lllegal.getIllegalSites());
        holder.txtFakuan.setText("违章罚款：" + lllegal.getMoney() + "元");
        holder.txtJifen.setText("违章扣分：" + lllegal.getDeductMarks() + "分");
        holder.txtNO.setText("车牌号：" + lllegal.getLicencePlate());
        if (lllegal.getDisposeState().equals("1")) {
            holder.txtZhuangtai.setText("处理状态：未处理");
        } else {
            holder.txtZhuangtai.setText("处理状态：已处理");
        }
        holder.txtTime.setText(lllegal.getBadTime());

        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Violation_msg_1(lllegal,lllegals));
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_violation,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return i;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDiain;
        private TextView txtNO;
        private TextView txtZhuangtai;
        private TextView txtJifen;
        private TextView txtFakuan;
        private TextView txtTime;
        private LinearLayout lin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lin = itemView.findViewById(R.id.lin);
            txtDiain = itemView.findViewById(R.id.txt_diain);
            txtNO = itemView.findViewById(R.id.txt_NO);
            txtZhuangtai = itemView.findViewById(R.id.txt_zhuangtai);
            txtJifen = itemView.findViewById(R.id.txt_jifen);
            txtFakuan = itemView.findViewById(R.id.txt_fakuan);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}
