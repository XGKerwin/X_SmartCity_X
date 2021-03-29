package com.example.x_smartcity_x.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpTo extends Thread{

    private String u1 = "http://192.168.155.208:8080/demo1/";        //本地网
    private String u3 = "http://10.173.94.106:8080/demo1/";
    private String u2 = "http://192.168.155.208:8080/mobileA/";
    private String Url = u1;

    private JSONObject jsonObject = new JSONObject();
    private OkHttpLo okHttpLo;
    private boolean isLoop;
    private int time;
    private String type;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            if (msg.what == 1){
                okHttpLo.onResponse((JSONObject) msg.obj);
            }else if (msg.what == 2){
                okHttpLo.onFailure((IOException) msg.obj);
            }
            return false;
        }
    });


    public OKHttpTo setUrl(String url){
        Url += url;
        return this;
    }

    public OKHttpTo setJSONObject(String k , Object v){
        try {
            jsonObject.put(k, v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public OKHttpTo setType(String type){
        this.type = type;
        return this;
    }

    public OKHttpTo setOkHttpLo(OkHttpLo okHttpLo){
        this.okHttpLo = okHttpLo;
        return this;
    }

    public OKHttpTo setLoop(boolean loop){
        isLoop = loop;
        return this;
    }

    public OKHttpTo setTime(int time){
        this.time = time;
        return this;
    }

    public OKHttpTo setDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("网络请求中。。。");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            switch (type){
                case "get":
                    get_get();
                    break;
                case "post":
                    get_post();
                    break;
                case "put":
                    get_put();
                    break;
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);
    }

    private void get_put() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=utf-8"),jsonObject.toString());
        Request request = new Request.Builder()
                .url(Url)
                .put(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Message message = new Message();
                message.what = 2;
                message.obj = e;
                if (progressDialog != null){
                    handler.sendMessageDelayed(message,1000);
                }else {
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.what = 1;
                try {
                    message.obj = new JSONObject(response.body().string());
                    if (progressDialog != null){
                        handler.sendMessageDelayed(message , 1000);
                    }else {
                        handler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void get_post() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json;charset=utf-8"),jsonObject.toString());
        Request request = new Request.Builder()
                .url(Url)
                .post(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Message message = new Message();
                message.what = 2;
                message.obj = e;
                if (progressDialog != null){
                    handler.sendMessageDelayed(message,1000);
                }else {
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.what = 1;
                try {
                    message.obj = new JSONObject(response.body().string());
                    if (progressDialog != null){
                        handler.sendMessageDelayed(message , 1000);
                    }else {
                        handler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void get_get() {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(8000, TimeUnit.MILLISECONDS)        //不能超过8秒
//                .build();

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpUrl = HttpUrl.parse(Url).newBuilder();
        Iterator<String> keys = jsonObject.keys();
        for (Iterator<String> it = keys; it.hasNext(); ) {
            String key = it.next();
            httpUrl.addQueryParameter(key , (String) jsonObject.opt(key));
        }



        Request request = new Request.Builder()
                .get()
                .url(httpUrl.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = 2;
                message.obj = e;
                if (progressDialog != null){
                    handler.sendMessageDelayed(message,1000);
                }else {
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Message message = new Message();
                message.what = 1;
                try {
                    message.obj = new JSONObject(response.body().string());
                    if (progressDialog != null){
                        handler.sendMessageDelayed(message , 1000);
                    }else {
                        handler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
