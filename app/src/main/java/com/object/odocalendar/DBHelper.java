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
        String sqlTarget = "CREATE TABLE " + TARGET_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, ACHIEVE TEXT);";
        db.execSQL(sqlTarget);

        String sqlCategory = "CREATE TABLE " + CATEGORY_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORY_NAME TEXT);";
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
        String sql = "INSERT INTO " + CATEGORY_TABLE + " VALUES (null, exercise), (null, study), (null, project), (null, meeting), (null, promise), (null, reservation);";
        db.execSQL(sql);
    }

    public void insertCategory(String insert){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + CATEGORY_TABLE + " VALUES (null, " + insert + ");";
        db.execSQL(sql);
    }

    public void insertTarget(String insert){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERTO INTO " + TARGET_TABLE + " VALUES (null, " + insert + ");";
        db.execSQL(sql);
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
}