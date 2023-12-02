package com.object.odocalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TARGET_TABLE = "target";
    private static final String TODO_TABLE = "todo";
    private static final String CATEGORY_TABLE = "category";

    public DBHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory){
        super(context, "todoCal.db", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlTarget = "CREATE TABLE " + TARGET_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, ACHIEVE TEXT, MONTHWEEK INT," +
                " TARGET_MONTH INT, TARGET_WEEK INTEGER);";
        db.execSQL(sqlTarget);

        String sqlCategory = "CREATE TABLE " + CATEGORY_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORY_NAME TEXT, CATEGORY_COLOR TEXT);";
        db.execSQL(sqlCategory);
        setCategoryTable();

        String sqlTodo = "CREATE TABLE " + TODO_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "SCHEDULE TEXT, DATE DATE, CHECK_ACHIEVE INT, CATEGORY INTEGER, " +
                "FOREIGN KEY(CATEGORY) REFERENCES " + CATEGORY_TABLE + "(_id));";
        db.execSQL(sqlTodo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTarget = "DROP TABLE IF EXISTS " + TARGET_TABLE;
        db.execSQL(dropTarget);

        String dropCategory = "DROP TABLE IF EXISTS " + CATEGORY_TABLE;
        db.execSQL(dropCategory);

        String dropTodo = "DROP TABLE IF EXISTS " + TODO_TABLE;
        db.execSQL(dropTodo);

        onCreate(db);
    }

    public void setCategoryTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + CATEGORY_TABLE + " VALUES (null, exercise), (null, study), " +
                "(null, project), (null, meeting), (null, promise), (null, reservation);";
        // 1. 카테고리에 색상을 저장할 color 생성 -> RGB16진수 사용으로 TEXT 설정
        // 2. 기본 카테고리에 기본 색상 설정 -> 초기값 (setCategoryTable)
        db.execSQL(sql);
    }

    public void insertCategory(String name, String color){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + CATEGORY_TABLE + "(_id, CATEGORY_NAME, CATEGORY_COLOR) VALUES (null, ?, ?);";

        db.beginTransaction();
        try{
            db.execSQL(sql, new Object[]{name, color});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void insertTarget(String detail, int monthOrWeek,int month, Integer week){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TARGET_TABLE + " (_id, ACHIEVE, MONTHWEEK, TARGET_MONTH, TARGET_WEEK) VALUES (null, ?, ?, ?, ?);";

        db.beginTransaction();
        try{
            db.execSQL(sql, new Object[]{detail, monthOrWeek, month, week});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void insertTodo(String schedule, long selecDate, int check_achieve, Integer category){
        String sql = "INSERT INTO " + TODO_TABLE + " (_id, SCHEDULE, DATE, CHECK_ACHIEVE, CATEGORY) VALUES (null, ?, ?, ?, ?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try{
            db.execSQL(sql, new Object[]{schedule, selecDate, check_achieve, category});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public String getAll(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM todoCal.db", null);
        while(cursor.moveToNext()){
            result += cursor.getString(1) + "\n";
            result += cursor.getString(2) + "\n";
            // schedule이랑 date만 출력 테스트
        }

        return result;
    }

    public void deleteTodo(int i){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM " + TODO_TABLE + " WHERE _id = " + i);
    }

}