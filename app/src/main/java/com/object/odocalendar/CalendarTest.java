package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest extends AppCompatActivity {

    CalendarView cv;
    SQLiteDatabase db;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_test);

        dbHelper = new DBHelper(this, null);
        db = dbHelper.getReadableDatabase();

        cv = (CalendarView) findViewById(R.id.cv1);
        cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                long date = cv.getDate();

            }
        });
    }
}