package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test extends AppCompatActivity {

    int pYear, pMonth, pDay;

    CalendarView cv;
    TextView textView;
    Button bt;


    private popup_test act;


    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        cv = (CalendarView) findViewById(R.id.cv1);
        textView = (TextView) findViewById(R.id.textView);
        bt = (Button) findViewById(R.id.button);


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DB 내역 출력
            }
        });



        Date date = new Date(System.currentTimeMillis());
        sdf = new SimpleDateFormat("yyyy");
        pYear = Integer.parseInt(sdf.format(date));
        sdf = new SimpleDateFormat("MM");
        pMonth = Integer.parseInt(sdf.format(date));
        sdf = new SimpleDateFormat("dd");
        pDay = Integer.parseInt(sdf.format(date));

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;
                // 해당 날짜의 일정 출력
                if (pYear == year && pMonth == month && pDay == day) {
                    // 년, 월, 일을 파라미터로 Popup에 전달
                    Popup(year, month, day);
                }

                pYear = year;
                pMonth = month;
                pDay = day;
            }
        });


        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public void Popup(int year, int month, int day) {
            act = new popup_test(this, year, month, day);
            act.show();
    }

}