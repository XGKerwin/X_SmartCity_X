package com.example.x_smartcity_x.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_x.R;

public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "NewsActivity";
    private String id;
    private ImageView titleBack;
    private TextView titleTitle;
    private FrameLayout fragmentNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTitle.setText("新闻");
        id = getIntent().getStringExtra("init");

        getFragment(new NewsFragment(id));



    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_new, fragment).commit();
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        fragmentNew = findViewById(R.id.fragment_new);
    }
}