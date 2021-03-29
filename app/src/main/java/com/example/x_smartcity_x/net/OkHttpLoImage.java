package com.example.x_smartcity_x.net;

import android.graphics.Bitmap;
import android.telecom.Call;

import java.io.IOException;

public interface OkHttpLoImage {
    void onResponse(Call call, Bitmap bitmap);

    void onFailure(Call call, IOException obj);

}
