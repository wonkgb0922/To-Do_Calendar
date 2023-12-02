package com.object.odocalendar;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class popup_test extends AlertDialog {

    Button insert_btn;
    Button close_btn;
    EditText test_text;
    TextView date_text_test;

    private long selectedDate;

    int year, month, dayOfMonth;

    public popup_test(@NonNull Context context, int year, int month, int dayOfMonth) {
        super(context);
        this.year = year; this.month = month; this.dayOfMonth = dayOfMonth;
        setContentView(R.layout.activity_popup_test);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.activity_popup_test, null);
        setView(dialogView);

        test_text = (EditText) dialogView.findViewById(R.id.editTestText);
        insert_btn = (Button) dialogView.findViewById(R.id.insertBtn);
        close_btn = (Button) dialogView.findViewById(R.id.closeBtn);
        date_text_test = (TextView) dialogView.findViewById(R.id.dateTest);


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 확인 버튼 클릭 시 DB저장

                //String dateTest = "" + selectedDate;
                String selectedDate = year + "년 " + month + "월 " + dayOfMonth + "일";
                date_text_test.setText(selectedDate);
            }
        });
    }
}