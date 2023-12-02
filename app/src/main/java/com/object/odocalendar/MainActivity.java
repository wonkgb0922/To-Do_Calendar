package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    private TodoPopupActivity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        CalendarView cv = findViewById(R.id.calendarView);
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Popup();
            }
        });
    }

    public void Popup() {
        act = new TodoPopupActivity(this);
        act.show();
    }
}