package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityWon extends AppCompatActivity {

    private TodoPopupActivity act;
    int pYear, pMonth, pDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_won);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        CalendarView cv = findViewById(R.id.calendarView);
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        ListView lv = findViewById(R.id.TodoListView);
        cv.setFirstDayOfWeek(2);

        long now = System.currentTimeMillis();  // 오늘 날짜를 long으로 받아옴
        Date date = new Date(now); // 오늘 날짜로 Date클래스 생성

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        pYear = Integer.parseInt(formatter.format(date)); // 년
        formatter = new SimpleDateFormat("M"); // 월(1~12의 값)
        pMonth = Integer.parseInt(formatter.format(date));
        formatter = new SimpleDateFormat("d"); // 일
        pDay = Integer.parseInt(formatter.format(date));

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++; // month는 0~11의 값이므로 +1 해줌

                // Toast로 이전의 년/월/일 확인
                Toast.makeText(getApplicationContext(), pYear+ "/"+pMonth+"/"+pDay, Toast.LENGTH_SHORT).show();
                if(pYear == year && pMonth == month && pDay == day) {
                    // 년, 월, 일을 파라미터로 Popup에 전달
                    Popup(year, month, day);
                }
                else Toast.makeText(getApplicationContext(), "새로 선택함", Toast.LENGTH_SHORT).show();
                // 현재 선택 연월일을 이전 연월일로 이동
                pYear = year;
                pMonth = month;
                pDay = day;
            }
        });
    }
    public void Popup(int year, int month, int day) {
        // 년, 월, 일을 파라미터로 팝업 액티비티 생성
        act = new TodoPopupActivity(this, year, month, day);
        act.show();
    }
    public void Refresh() {
        // 새로고침
    }
}