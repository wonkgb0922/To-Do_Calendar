package com.object.odocalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GoalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        long now = System.currentTimeMillis();  // 오늘 날짜를 long으로 받아옴
        Date date = new Date(now); // 오늘 날짜로 Date클래스 생성

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/M");
        TextView tv = findViewById(R.id.monthTextView);
        tv.setText(formatter.format(date));
    }
}