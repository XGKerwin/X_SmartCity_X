package com.example.x_smartcity_x.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.x_smartcity_x.Fragment.Service.Fragment_Mine;
import com.example.x_smartcity_x.Fragment.Service.Fragment_News;
import com.example.x_smartcity_x.Fragment.Service.Fragment_Page;
import com.example.x_smartcity_x.Fragment.Service.Fragment_Party;
import com.example.x_smartcity_x.Fragment.Service.Fragment_service;
import com.example.x_smartcity_x.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment {

    private View view;
    private ViewPager2 ViewPager;
    private BottomNavigationView BottomNavigation;
    private List<Fragment> fragments;
    private static final String TAG = "Fragment_home";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        initView(view);
        initPager();

        btn_bottom();

        MyFragmentAdapter adapter = new MyFragmentAdapter(getFragmentManager(),getLifecycle(),fragments);

        ViewPager.setAdapter(adapter);

        ViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        return view;
    }


    @SuppressLint("ResourceType")
    private void changTab(int position) {
        Log.d(TAG, "changTab: "+position);
        switch (position){
            case 0:
                BottomNavigation.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BottomNavigation.setSelectedItemId(BottomNavigation.getMenu().getItem(0).getItemId());
                    }
                },100);

                break;
            case 1:
                BottomNavigation.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BottomNavigation.setSelectedItemId(BottomNavigation.getMenu().getItem(1).getItemId());
                    }
                },100);
                break;
            case 2:
                BottomNavigation.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BottomNavigation.setSelectedItemId(BottomNavigation.getMenu().getItem(2).getItemId());
                    }
                },100);
                break;
            case 3:
                BottomNavigation.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BottomNavigation.setSelectedItemId(BottomNavigation.getMenu().getItem(3).getItemId());
                    }
                },100);
                break;
            case 4:
                BottomNavigation.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BottomNavigation.setSelectedItemId(BottomNavigation.getMenu().getItem(4).getItemId());
                    }
                },100);
                break;
        }

    }

    private void initPager() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment_Page());
        fragments.add(new Fragment_service());
        fragments.add(new Fragment_Party());
        fragments.add(new Fragment_News());
        fragments.add(new Fragment_Mine());
    }

    @SuppressLint({"WrongConstant", "ResourceType"})
    private void btn_bottom() {
        BottomNavigation.setLabelVisibilityMode(1);
        BottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bom_home:
                        ViewPager.setCurrentItem(0);
                        break;
                    case R.id.bom_service:
                        ViewPager.setCurrentItem(1);
                        break;
                    case R.id.bom_party:
                        ViewPager.setCurrentItem(2);
                        break;
                    case R.id.bom_news:
                        ViewPager.setCurrentItem(3);
                        break;
                    case R.id.bom_mine:
                        ViewPager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }



    class MyFragmentAdapter extends FragmentStateAdapter {
        private List<Fragment> fragments;

        public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
            super(fragmentManager, lifecycle);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
    

    private void initView(View view) {
        ViewPager = view.findViewById(R.id.ViewPager);
        BottomNavigation = view.findViewById(R.id.BottomNavigation);
    }

}
