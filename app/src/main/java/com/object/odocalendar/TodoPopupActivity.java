package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoPopupActivity extends Dialog {

    public TodoPopupActivity(@NonNull Context context, int year, int month, int day) {
        super(context);

        setContentView(R.layout.activity_todo_popup);
        String date = (month + 1) + "월 " + day + "일";

        TextView td = findViewById(R.id.textDate);
        td.setText(date);

    }

}