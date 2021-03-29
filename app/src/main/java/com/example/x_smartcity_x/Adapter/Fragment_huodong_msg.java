package com.example.x_smartcity_x.Adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.x_smartcity_x.Fragment.Service.Fragment_HuoDong;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyRecyclerView;
import com.example.x_smartcity_x.bean.GetActionById;
import com.example.x_smartcity_x.bean.GetActionCommitById;
import com.example.x_smartcity_x.bean.GetRecommandAction;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpTo1;
import com.example.x_smartcity_x.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_huodong_msg extends Fragment {

    private int id;
    private List<GetActionById> getActionByIds;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView titleWode;
    private ImageView img;
    private TextView txtMsg;
    private MyRecyclerView recyclerView;
    private TextView txtPinglun;
    private Button btnBaoming;
    private MyRecyclerView recyclerViewping;
    private TextView txtTitle;
    private static final String TAG = "Fragment_huodong_msg";
    private TextView txtBaoming;
    private EditText edPinglun;
    private Button btnTijiao;
    private List<GetActionCommitById> getActionCommitByIds;
    private HuoDong_msg_pinglun_adapter adapter;
    private List<GetRecommandAction> getRecommandActions;
    private Huodong_msg_tuijian_adapter adapter1;
    private String s_pinglun;
    private String userid;

    public Fragment_huodong_msg(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_huodong_msg, container, false);
        initView(view);
        titleTitle.setText("活动详情");
        userid = App.getId();


        getOkhttppinglun();
        gettuijian();


        btnBaoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getbaoming();
            }
        });

        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    s_pinglun = edPinglun.getText().toString();
                    if (s_pinglun.equals("")){
                        Toast.makeText(getContext(),"请输入评论内容",Toast.LENGTH_SHORT).show();
                    }else {
                        setOkHttp();
                    }
                }


            }
        });
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_HuoDong());
            }
        });
        return view;
    }

    private void getbaoming() {
        new OkHttpTo1()
                .setUrl("setActionSignUpCount")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"报名成功",Toast.LENGTH_SHORT).show();
                        getOKhttp();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setOkHttp() {
        /**
         * {"id":"2","userid":"4","commitTime":"2020-10-3 10:00:00","commitContent":"666"}
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);

        new OkHttpTo1()
                .setUrl("publicActionCommit")
                .setJSONObject("id",id+"")
                .setJSONObject("userid",userid)
                .setJSONObject("commitTime",str)
                .setJSONObject("commitContent",s_pinglun)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        edPinglun.setText("");
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        getOkhttppinglun();
                        gettuijian();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void gettuijian() {
        if (getRecommandActions == null){
            getRecommandActions = new ArrayList<>();
        }else {
            getRecommandActions.clear();
        }
        new OkHttpTo1()
                .setUrl("getRecommandAction")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getRecommandActions.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetRecommandAction>>(){}.getType()));
                        gettuijianshwo();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void gettuijianshwo() {
        if (adapter1 == null){
            adapter1 = new Huodong_msg_tuijian_adapter(getRecommandActions);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter1);
        }else {
            adapter.notifyDataSetChanged();
        }


    }

    private void getOkhttppinglun() {
        if (getActionCommitByIds == null){
            getActionCommitByIds = new ArrayList<>();
        }else {
            getActionCommitByIds.clear();
        }
        new OkHttpTo1()
                .setUrl("getActionCommitById")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getActionCommitByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionCommitById>>(){}.getType()));
                        showpinglun();
                        getOKhttp();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showpinglun() {
        if (adapter == null){
            adapter = new HuoDong_msg_pinglun_adapter(getActionCommitByIds);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerViewping.setLayoutManager(linearLayoutManager);
            recyclerViewping.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void getOKhttp() {
        if (getActionByIds == null) {
            getActionByIds = new ArrayList<>();
        } else {
            getActionByIds.clear();
        }
        new OkHttpTo1()
                .setUrl("getActionById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getActionByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionById>>() {
                                }.getType()));
                        txtTitle.setText(getActionByIds.get(0).getName());
                        txtMsg.setText(getActionByIds.get(0).getDescription());
                        txtBaoming.setText("参加人数："+getActionByIds.get(0).getCount()+"人");
                        txtPinglun.setText("评论："+getActionCommitByIds.size());
                        getImage();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImage() {
        new OkHttpToImage()
                .setUrl(getActionByIds.get(0).getImage())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_huodong, fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        titleWode = view.findViewById(R.id.title_wode);
        img = view.findViewById(R.id.img);
        txtMsg = view.findViewById(R.id.txt_msg);
        recyclerView = view.findViewById(R.id.recyclerView);
        txtPinglun = view.findViewById(R.id.txt_pinglun);
        btnBaoming = view.findViewById(R.id.btn_baoming);
        recyclerViewping = view.findViewById(R.id.recyclerViewping);
        txtTitle = view.findViewById(R.id.txt_title);
        txtBaoming = view.findViewById(R.id.txt_baoming);
        edPinglun = view.findViewById(R.id.ed_pinglun);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
