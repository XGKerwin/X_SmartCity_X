package com.example.x_smartcity_x.Fragment.introduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.x_smartcity_x.Fragment.introduction.Fragment_1;
import com.example.x_smartcity_x.Fragment.introduction.Fragment_2;
import com.example.x_smartcity_x.Fragment.introduction.Fragment_3;
import com.example.x_smartcity_x.Fragment.introduction.Fragment_4;
import com.example.x_smartcity_x.Fragment.introduction.Fragment_5;
import com.example.x_smartcity_x.R;
import com.example.x_smartcity_x.bean.Lists;
import com.example.x_smartcity_x.net.OKHttpTo;
import com.example.x_smartcity_x.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Main extends Fragment {

    private View view;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<Lists> lists;
    private static final String TAG = "Fragment_Main";
    private LinearLayout linear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, container, false);
        initView(view);

        getOkHttp();

        return view;
    }

    private void getFragment() {
        fragments.add(new Fragment_1(lists.get(0).getImgUrl()));
        fragments.add(new Fragment_2(lists.get(1).getImgUrl()));
        fragments.add(new Fragment_3(lists.get(2).getImgUrl()));
        fragments.add(new Fragment_4(lists.get(3).getImgUrl()));
        fragments.add(new Fragment_5(lists.get(4).getImgUrl()));

        viewPager.setAdapter(new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <linear.getChildCount();i++){
                    ImageView imageView = (ImageView) linear.getChildAt(i);
                    if (position == i){
                        imageView.setImageResource(R.drawable.bai_yuan);
                    }else {
                        imageView.setImageResource(R.drawable.hui_yuan);
                    }
                }
                if (position==4){
                    linear.setVisibility(View.GONE);
                }else {
                    linear.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i=0;i<fragments.size();i++){
            ImageView imageView = new ImageView(getContext());
            if (i==0){
                imageView.setImageResource(R.drawable.bai_yuan);
            }else {
                imageView.setImageResource(R.drawable.hui_yuan);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60,60));
            imageView.setPadding(10,10,10,10);
            linear.addView(imageView);
        }
    }

    private void getOkHttp() {
        lists = new ArrayList<>();
        new OKHttpTo()
                .setUrl("lists")
                .setJSONObject("type", "47")
                .setType("get")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lists.addAll(new Gson().fromJson(jsonObject.optJSONArray("roes").toString(),
                                new TypeToken<List<Lists>>() {
                                }.getType()));
                        getFragment();

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.ViewPager);
        linear = view.findViewById(R.id.linear);
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
