package com.example.khang.myapp.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.khang.myapp.DetailItem;
import com.example.khang.myapp.MainActivity;
import com.example.khang.myapp.Manager;
import com.example.khang.myapp.R;
import com.example.khang.myapp.RecycleviewAdapter;
import com.example.khang.myapp.db.TaskContract;
import com.example.khang.myapp.db.TaskDbOpen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_CONTENT;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_TIME;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.COL_TASK_TITLE;
import static com.example.khang.myapp.db.TaskContract.TaskEntry.TABLE;


public class Doing extends Fragment {
    private static final String TAG = "MainActivity";
    private TaskDbOpen mHelper;
    private RecyclerView recyclerView;
    private RecycleviewAdapter recycleviewAdapter;
    private List<Manager> managers = new ArrayList<>();
    private RecycleviewAdapter.Action action;
    private TextView day;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doing, container, false);
        recyclerView=view.findViewById(R.id.lv_todo);
        return view;
    }
    private void updateUI() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE,
                        COL_TASK_CONTENT, COL_TASK_TIME},
                null, null, null, null, null);
        managers.clear();
        while (cursor.moveToNext()) {
            String idx = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(COL_TASK_CONTENT));
            String time = cursor.getString(cursor.getColumnIndex(COL_TASK_TIME));
            managers.add(new Manager(idx, content, time));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleviewAdapter = new RecycleviewAdapter(this, managers, new RecycleviewAdapter.Action() {
            @Override
            public void onClickItem(Manager manager, int position) {
                Intent intent = new Intent(getActivity(), DetailItem.class);
                intent.putExtra("manager", manager);
                startActivity(intent);
            }

            @Override
            public void onLongClickItem(Manager manager, int position) {
                dialogChangeData(manager.getText(), manager.getContent());
            }
        });

        recyclerView.setAdapter(recycleviewAdapter);
        recycleviewAdapter.notifyDataSetChanged();
        cursor.close();
        db.close();
    }

    private void dialogChangeData(final String title, String content) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.dialog_additem, null);
        final EditText taskEditText = promptsView.findViewById(R.id.et_title);
        final EditText taskContent = promptsView.findViewById(R.id.et_content);
        taskEditText.setText(title);
        taskContent.setText(content);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
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



}
