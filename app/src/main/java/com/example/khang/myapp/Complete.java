package com.example.khang.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.khang.myapp.Adapter.RecycleviewAdapter;
import com.example.khang.myapp.Adapter.RecycleviewAdapterFinish;
import com.example.khang.myapp.Object.Manager;
import com.example.khang.myapp.db.TaskContract;
import com.example.khang.myapp.db.TaskDbOpen;

import java.util.ArrayList;
import java.util.List;

import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_CONTENT;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_TIME;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.CON_TASK_FINISH;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.TABLE;

public class Complete extends AppCompatActivity {
    private TaskDbOpen mHelper;
    private RecyclerView recyclerView;
    private RecycleviewAdapterFinish recycleviewAdapterFinish;
    private List<Manager> managers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        recyclerView=findViewById(R.id.rv_complete);
        mHelper=new TaskDbOpen(this);
        updateUI();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent intent = new Intent(Complete.this, MainActivityTwo.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void updateUI() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE,
                        COL_TASK_CONTENT, COL_TASK_TIME, CON_TASK_FINISH},
                null, null, null, null, null);
        managers.clear();
        while (cursor.moveToNext()) {
            String idx = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(COL_TASK_CONTENT));
            String time = cursor.getString(cursor.getColumnIndex(COL_TASK_TIME));
            String done = cursor.getString(cursor.getColumnIndex(CON_TASK_FINISH));
            if (!done.equals("")) {
                managers.add(new Manager(idx, content, time, done));
            } else {
                continue;
            }


        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleviewAdapterFinish = new RecycleviewAdapterFinish(this, managers, new RecycleviewAdapter.Action() {
            @Override
            public void onClickItem(Manager manager, int position) {
                Intent intent = new Intent(Complete.this, DetailItem.class);
                intent.putExtra("manager", manager);
                startActivity(intent);
            }

            @Override
            public void onLongClickItem(Manager manager, int position) {

            }

            @Override
            public void onDelete(Manager view, int position) {

            }
        });

        recyclerView.setAdapter(recycleviewAdapterFinish);
        recycleviewAdapterFinish.notifyDataSetChanged();
        cursor.close();
        db.close();
    }
}
