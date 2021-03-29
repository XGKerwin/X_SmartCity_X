package com.example.x_smartcity_x.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.x_smartcity_x.Fragment.Service.Fragment_Poverty;
import com.example.x_smartcity_x.R;

public class PovertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poverty);

        getFragment(new Fragment_Poverty());

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_poverty,fragment).commit();
    }
}