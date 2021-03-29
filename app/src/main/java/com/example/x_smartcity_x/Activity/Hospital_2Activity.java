package com.example.x_smartcity_x.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.x_smartcity_x.Fragment.Service.Fragment_Hospital;
import com.example.x_smartcity_x.R;

public class Hospital_2Activity extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_2);

        id = getIntent().getStringExtra("init");

        getFragment(new Fragment_Hospital(id));



    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_hospital,fragment).commit();
    }
}