package com.example.khang.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailItem extends AppCompatActivity {
    private TextView content;
    private TextView title;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;
    private TextView day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        content=findViewById(R.id.content);
        day=findViewById(R.id.date);
        title=findViewById(R.id.tv_detail_title);
        Manager manager= (Manager) getIntent().getSerializableExtra("manager");
        content.setText(manager.getContent());
        title.setText(manager.getText());
        day.setText(manager.getTime());

    }
}
