package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ListView;

public class MainActivityWon extends AppCompatActivity {

    private TodoPopupActivity act;
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
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                // 년, 월, 일을 파라미터로 Popup에 전달
                Popup(year, month, day);
            }
        });
    }

    public void Popup(int year, int month, int day) {
        // 년, 월, 일을 파라미터로 팝업 액티비티 생성
        act = new TodoPopupActivity(this, year, month, day);
        act.show();
    }
}