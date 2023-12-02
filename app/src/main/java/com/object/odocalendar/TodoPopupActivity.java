package com.object.odocalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class TodoPopupActivity extends Dialog {

    public TodoPopupActivity(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_todo_popup);
    }

}