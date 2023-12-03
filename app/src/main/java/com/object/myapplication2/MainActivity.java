package com.object.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = null;
        Intent intent;

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                switch (menuItem.getItemId()){
                    case R.id.seemBtn:
                        intent = new Intent(InputAllergy.this, Fragment1.class);
                        startActivity(intent);
                        return true;


                    case R.id.calendarBtn:
                        intent = new Intent(InputAllergy.this, Fragment2.class);
                        startActivity(intent);
                        return true;


                    case R.id.addBtn:
                        intent = new Intent(InputAllergy.this, Fragment3.class);
                        startActivity(intent);
                        return true;

                    case R.id.listBtn:
                        intent = new Intent(InputAllergy.this, Fragment4.class);
                        startActivity(intent);
                        return true;

                    case R.id.settingBtn:
                        intent = new Intent(InputAllergy.this, Fragment5.class);
                        startActivity(intent);
                        return true;
                }

                return false;
            }
        });



    }
}