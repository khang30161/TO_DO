package com.example.khang.myapp.db;

import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "com.example.khang.myapp";
    public static final int DB_VERSION = 4;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "Task";
        public static final String COL_TASK_TITLE = "Title";
        public static final String COL_TASK_CONTENT = "Content";
        public static final String COL_TASK_TIME = "time";
        public static final String CON_TASK_FINISH = "finish";
        public static final String COL_TASK_USER_ID = "userID";

        public static final String TABLE_USER = "User";
        public static final String COL_TASK_NAME = "name";
        public static final String COL_TASK_EMAIL = "email";

    }
}
