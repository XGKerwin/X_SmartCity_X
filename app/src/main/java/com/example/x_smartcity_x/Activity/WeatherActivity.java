package com.example.x_smartcity_x.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_x.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtTianqi;
    private TextView tianqi1;
    private TextView tianqi2;
    private ImageView image;
    private TextView txt1Qiang;
    private TextView txt1Msg;
    private TextView txt2Qiang;
    private TextView txt2Msg;
    private TextView txt3Qiang;
    private TextView txt3Msg;
    private TextView txt4Qiang;
    private TextView txt4Msg;
    private TextView txt5Qiang;
    private TextView txt5Msg;
    private static final String TAG = "WeatherActivity";
    private LineChart lineChart;
    private ImageView btn_shuaxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        titleTitle.setText("实时天气");

        getshuaxin();

        btn_shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getshuaxin();
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getshuaxin() {
        getData();
        gettian();
        getLineChar();
        getDa();
    }

    private void getDa() {
        int s = (int) Math.floor((Math.random() * 4500) + 1);
        if (s<1000){
            txt1Qiang.setText("弱");
            txt1Msg.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
        }else if (s>3000){
            txt1Qiang.setText("强");
            txt1Msg.setText("尽量减少外出，需要涂抹高倍数防晒霜");
        }else {
            txt1Qiang.setText("中等");
            txt1Msg.setText("涂擦SPF大于15、PA+防晒护肤品");
        }

        int s2 = (int) Math.floor((Math.random() * 16) + 1);
        if (s2<8){
            txt2Qiang.setText("较易发");
            txt2Msg.setText("温度低，风较大，较易发生感冒，注意防护");
        }else {
            txt2Qiang.setText("少发");
            txt2Msg.setText("无明显降温，感冒机率较低");
        }

        int s3 = (int) Math.floor((Math.random() * 34) + 1);
        if (s3<12){
            txt3Qiang.setText("冷");
            txt3Msg.setText("建议穿长袖衬衫、单裤等服装");
        }else if (s3>21){
            txt3Qiang.setText("热");
            txt3Msg.setText("适合穿T恤、短薄外套等夏季服装");
        }else {
            txt3Qiang.setText("舒适");
            txt3Msg.setText("建议穿短袖衬衫、单裤等服装");
        }

        int s4 = (int) Math.floor((Math.random() * 8000) + 1);
        if (s4<3000){
            txt4Qiang.setText("适宜");
            txt4Msg.setText("气候适宜，推荐您进行户外运动");
        }else if (s4>6000){
            txt4Qiang.setText("较不宜");
            txt4Msg.setText("空气氧气含量低，请在室内进");
        }else {
            txt4Qiang.setText("中");
            txt4Msg.setText("易感人群应适当减少室外活动");
        }

        int s5 = (int) Math.floor((Math.random() * 150) + 1);
        if (s5<30){
            txt5Qiang.setText("优");
            txt5Msg.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
        }else if (s5>100){
            txt5Qiang.setText("污染");
            txt5Msg.setText("空气质量差，不适合户外活动");
        }else {
            txt5Qiang.setText("良");
            txt5Msg.setText("易感人群应适当减少室外活动");
        }

    }

    private void gettian() {
        int s = (int) Math.floor((Math.random() * 15) + 1);
        int s1 = (int) Math.floor((Math.random() * 35) + 1);
        tianqi1.setText(s+"");
        tianqi2.setText(s1+"");
    }

    private void getLineChar() {
        List<Entry> maxEntry = new ArrayList<>();
        List<Entry> minEntry = new ArrayList<>();
        for (int i=0;i<4;i++){
            int s = (int) Math.floor((Math.random() * 15) + 1);
            int s1 = (int) Math.floor((Math.random() * 30) + 1);
            if (s > s1){
                maxEntry.add(new Entry(i,s1 ));
                minEntry.add(new Entry(i,s));
            }else {
                maxEntry.add(new Entry(i,s ));
                minEntry.add(new Entry(i,s1));
            }

        }
        LineDataSet dataSet = new LineDataSet(maxEntry, "");
        LineDataSet dataSet1 = new LineDataSet(minEntry, "");
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setColor(Color.BLUE);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleHoleRadius(5);
        dataSet.setLineWidth(2);
        dataSet1.setCircleColor(Color.RED);
        dataSet1.setColor(Color.RED);
        dataSet1.setDrawCircleHole(false);
        dataSet1.setCircleHoleRadius(5);
        dataSet1.setLineWidth(2);
        List<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(dataSet);
        iLineDataSets.add(dataSet1);
        LineData data = new LineData(iLineDataSets);
        lineChart.setData(data);
        lineChart.getAxisRight().setEnabled(false);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setTextColor(Color.TRANSPARENT);
        lineChart.getXAxis().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.invalidate();


    }

    private void getData() {
        int s = (int) Math.floor((Math.random() * 6) + 1);
        switch (s){
            case 1:
                txtTianqi.setText("晴");
                image.setImageResource(R.drawable.tianqi_qing);
                break;
            case 2:
                txtTianqi.setText("多云");
                image.setImageResource(R.drawable.tianqi_duoyun);
                break;
            case 3:
                txtTianqi.setText("雾");
                image.setImageResource(R.drawable.tianqi_mai);
                break;
            case 4:
                txtTianqi.setText("小雨");
                image.setImageResource(R.drawable.tianqi_xiaoyu);
                break;
            case 5:
                txtTianqi.setText("雪");
                image.setImageResource(R.drawable.tianqi_xue);
                break;
            default:
                txtTianqi.setText("阴");
                image.setImageResource(R.drawable.tianqi_yin);
                break;
        }

    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        txtTianqi = findViewById(R.id.txt_tianqi);
        tianqi1 = findViewById(R.id.tianqi1);
        tianqi2 = findViewById(R.id.tianqi2);
        image = findViewById(R.id.image);
        txt1Qiang = findViewById(R.id.txt_1_qiang);
        txt1Msg = findViewById(R.id.txt_1_msg);
        txt2Qiang = findViewById(R.id.txt_2_qiang);
        txt2Msg = findViewById(R.id.txt_2_msg);
        txt3Qiang = findViewById(R.id.txt_3_qiang);
        txt3Msg = findViewById(R.id.txt_3_msg);
        txt4Qiang = findViewById(R.id.txt_4_qiang);
        txt4Msg = findViewById(R.id.txt_4_msg);
        txt5Qiang = findViewById(R.id.txt_5_qiang);
        txt5Msg = findViewById(R.id.txt_5_msg);
        lineChart = findViewById(R.id.LineChar);
        btn_shuaxin = findViewById(R.id.btn_shuaxin);
    }
}