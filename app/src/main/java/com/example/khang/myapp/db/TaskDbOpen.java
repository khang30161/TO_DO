package com.example.khang.myapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khang.myapp.db.TaskContract;

import java.text.SimpleDateFormat;

public class TaskDbOpen extends SQLiteOpenHelper {

    public TaskDbOpen(Context context) {
        super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TaskContract.TaskEntry.TABLE + " ( " +
                TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL,"+
                TaskContract.TaskEntry.COL_TASK_CONTENT + " TEXT NOT NULL,"+
                TaskContract.TaskEntry.COL_TASK_TIME + " TEXT NOT NULL,"+
                TaskContract.TaskEntry.CON_TASK_FINISH + " TEXT NOT NULL,"+
                TaskContract.TaskEntry.COL_TASK_USER_ID + " INTEGER NOT NULL);";
        db.execSQL(createTable);

        String createTableUser = "CREATE TABLE " + TaskContract.TaskEntry.TABLE_USER + " ( " +
                TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.TaskEntry.COL_TASK_NAME + " TEXT NOT NULL,"+
                TaskContract.TaskEntry.COL_TASK_EMAIL + " TEXT NOT NULL);";
        db.execSQL(createTableUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE);
        onCreate(db);
    }
}
