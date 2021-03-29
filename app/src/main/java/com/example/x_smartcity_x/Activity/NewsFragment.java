package com.example.x_smartcity_x.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.x_smartcity_x.Adapter.News_CommentAdapter;
import com.example.x_smartcity_x.Adapter.News_adapter;
import com.example.x_smartcity_x.App;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.Util.MyImageView_arc;
import com.example.x_smartcity_x.Util.MyRecyclerView;
import com.example.x_smartcity_x.bean.GetCommentByPressId;
import com.example.x_smartcity_x.bean.GetNewsAll;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.example.x_smartcity_x.net.OkHttpLoImage;
import com.example.x_smartcity_x.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private View view;
    private String id;
    private TextView title;
    private MyImageView_arc img;
    private TextView msg;
    private MyRecyclerView recyclerRecommend;
    private MyRecyclerView recyclerComment;
    private EditText edComment;
    private List<GetNewsAll> newsAlls, newrecommend;
    private List<GetCommentByPressId> getCommentByPressIds;
    private TextView btnSubmit;
    private String userid;
    private News_adapter adapter;
    private News_CommentAdapter adapter1;

    public NewsFragment(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.newsfragment, container, false);
        initView(view);
        userid = App.getId();

        getOkHttp();
        getcomment();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    String s = edComment.getText().toString();
                    if (s.equals("")){
                        Toast.makeText(getContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                    }else {
                        setOkHttp(s);
                    }
                }
            }
        });

        return view;
    }

    private void setOkHttp(String s) {
        /**
         * {"userId":"1","pressId":"1","content":"好好学习"}   pressId：新闻id
         */
        int id1 = Integer.parseInt(id);

        new OKHttpTo()
                .setUrl("setComment")
                .setType("post")
                .setJSONObject("userId",userid)
                .setJSONObject("pressId",id1+1+"")
                .setJSONObject("content",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String s = jsonObject.optString("msg");
                        if (s.equals("操作成功")){
                            edComment.setText("");
                            Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                            getcomment();
                        }else {
                            Toast.makeText(getContext(),"提交失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void getcomment() {
        if (getCommentByPressIds == null) {
            getCommentByPressIds = new ArrayList<>();
        } else {
            getCommentByPressIds.clear();
        }

        int s = Integer.parseInt(id);
        s = s + 1;
        new OKHttpTo()
                .setUrl("getCommentByPressId")
                .setType("get")
                .setJSONObject("pressId", s + "")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getCommentByPressIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetCommentByPressId>>() {
                                }.getType()));
                        if (adapter1 == null){
                            adapter1 = new News_CommentAdapter(getCommentByPressIds, getContext());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerComment.setLayoutManager(linearLayoutManager);
                            recyclerComment.setAdapter(adapter1);
                        }else {
                            adapter1.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getData(GetNewsAll getNewsAll) {
        title.setText(getNewsAll.getTitle());
        msg.setText(getNewsAll.getContent());
        new OkHttpToImage()
                .setUrl(getNewsAll.getImgUrl())
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

    private void getOkHttp() {
        if (newsAlls == null) {
            newsAlls = new ArrayList<>();
        } else {
            newsAlls.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsAll")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsAlls.addAll(new Gson().fromJson(jsonObject.optJSONArray("rows").toString(),
                                new TypeToken<List<GetNewsAll>>() {
                                }.getType()));
                        int s = Integer.parseInt(id);
                        getData(newsAlls.get(s));
                        getrecommend();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getrecommend() {
        if (newrecommend == null) {
            newrecommend = new ArrayList<>();
        } else {
            newrecommend.clear();
        }

        for (GetNewsAll all : newsAlls) {
            if (all.getIsRecommend() == 1) {
                newrecommend.add(all);
            }
        }
        if (adapter == null){
            adapter = new News_adapter(newrecommend, getContext(), getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerRecommend.setLayoutManager(linearLayoutManager);
            recyclerRecommend.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }



    }

    private void initView(View view) {
        title = view.findViewById(R.id.title);
        img = view.findViewById(R.id.img);
        msg = view.findViewById(R.id.msg);
        recyclerRecommend = view.findViewById(R.id.recycler_recommend);
        recyclerComment = view.findViewById(R.id.recycler_comment);
        edComment = view.findViewById(R.id.ed_comment);
        btnSubmit = view.findViewById(R.id.btn_submit);
    }
}
