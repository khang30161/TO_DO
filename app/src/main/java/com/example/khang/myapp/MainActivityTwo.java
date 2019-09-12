package com.example.khang.myapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khang.myapp.Adapter.RecycleviewAdapter;
import com.example.khang.myapp.Object.Manager;
import com.example.khang.myapp.db.MainActivity;
import com.example.khang.myapp.db.TaskContract;
import com.example.khang.myapp.db.TaskDbOpen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_CONTENT;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_EMAIL;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_NAME;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_TIME;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_TITLE;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.CON_TASK_FINISH;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.TABLE;

public class MainActivityTwo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;
    private TaskDbOpen mHelper;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloat;
    private RecyclerView recyclerView;
    private RecycleviewAdapter recycleviewAdapter;
    private List<Manager> managers = new ArrayList<>();
    private FragmentManager fragmentManager;
    private TextView name, email;
    private ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mHelper = new TaskDbOpen(this);
        recyclerView = findViewById(R.id.lv_todo);
//        getSupportActionBar().setTitle("TabLayout ViewPager");
        mFloat = findViewById(R.id.fab);
        mFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        fragmentManager = this.getSupportFragmentManager();
        updateUI();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        email = findViewById(R.id.email);
        edit = findViewById(R.id.iv_add_info);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });
    }

    private boolean OpenDialog() {
        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View promptsView = li.inflate(R.layout.dialog_addinfo, null);
        final EditText taskEditText = promptsView.findViewById(R.id.et_name);
        final EditText taskContent = promptsView.findViewById(R.id.et_email);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Profile User")
                .setMessage("For your information")
                .setView(promptsView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = String.valueOf(taskEditText.getText());
                        String email = String.valueOf(taskContent.getText());
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(COL_TASK_NAME, name);
                        values.put(COL_TASK_EMAIL, email);
                        db.insertWithOnConflict(TABLE,
                                null,
                                values,
                                SQLiteDatabase.CONFLICT_REPLACE);
                        updateUI();
                        db.close();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
        return true;
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent=new Intent(MainActivityTwo.this, Complete.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                Intent intent = new Intent(MainActivityTwo.this, MainActivityTwo.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public boolean dialog() {
        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View promptsView = li.inflate(R.layout.dialog_additem, null);
        final EditText taskEditText = promptsView.findViewById(R.id.et_title);
        final EditText taskContent = promptsView.findViewById(R.id.et_content);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(promptsView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        String task1 = String.valueOf(taskContent.getText());
                        calander = Calendar.getInstance();
                        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date = (simpledateformat.format(calander.getTime()));
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(COL_TASK_TITLE, task);
                        values.put(COL_TASK_CONTENT, task1);
                        values.put(COL_TASK_TIME, Date);
                        values.put(CON_TASK_FINISH, "");
                        db.insertWithOnConflict(TABLE,
                                null,
                                values,
                                SQLiteDatabase.CONFLICT_REPLACE);

                        db.close();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
        return true;


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
            if (done.equals("")) {
                managers.add(new Manager(idx, content, time, done));
            } else {
                continue;
            }


        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleviewAdapter = new RecycleviewAdapter(this, managers, new RecycleviewAdapter.Action() {
            @Override
            public void onClickItem(Manager manager, int position) {
                Intent intent = new Intent(MainActivityTwo.this, DetailItem.class);
                intent.putExtra("manager", manager);
                startActivity(intent);
            }

            @Override
            public void onLongClickItem(Manager manager, int position) {
                dialogChangeData(manager.getText(), manager.getContent());
            }

            @Override
            public void onDelete(Manager view, int position) {

            }
        });

        recyclerView.setAdapter(recycleviewAdapter);
        recycleviewAdapter.notifyDataSetChanged();
        cursor.close();
        db.close();
    }

    private void dialogChangeData(final String title, String content) {
        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View promptsView = li.inflate(R.layout.dialog_additem, null);
        final EditText taskEditText = promptsView.findViewById(R.id.et_title);
        final EditText taskContent = promptsView.findViewById(R.id.et_content);
        taskEditText.setText(title);
        taskContent.setText(content);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(promptsView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UpdateData(taskEditText.getText().toString(), taskContent.getText().toString());
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private int UpdateData(String title, String content) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COL_TASK_TITLE, title);
        values.put(TaskContract.TaskEntry.COL_TASK_CONTENT, content);
        return db.update(TABLE, values, COL_TASK_TITLE + " = ?",
                new String[]{title});
    }
    public int doneTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.tv_content);
        taskTextView.setText("finish");
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String done = "finish";
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.CON_TASK_FINISH, done);
        return db.update(TABLE, values, CON_TASK_FINISH + " = ?",
                new String[]{done});

//        Log.e(db.Select(all));
//
//        return resultReturn;

    }
}
