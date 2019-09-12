package com.example.khang.myapp.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khang.myapp.Object.Manager;
import com.example.khang.myapp.R;

import java.util.List;
import java.util.Random;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Manager> managers;
    private Resources resources;
    private Action action;
    private View.OnClickListener itemClick;


    public RecycleviewAdapter(Context context, List<Manager> managers, Action action) {
        this.context = context;
        this.managers = managers;
        this.action = action;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_todo, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.textView.setText(managers.get(i).getText());
        holder.finish.setText(managers.get(i).getFinish());
        holder.content.setText(managers.get(i).getContent());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null)
                    action.onClickItem(managers.get(i), i);


            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (action != null)
                    action.onLongClickItem(managers.get(i), i);
                return false;
            }
        });
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.onDelete(managers.get(i), i);
            }
        });
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.relativeLayout.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return (managers != null ? managers.size() : 0);
    }

    public interface Action {
        void onClickItem(Manager manager, int position);

        void onLongClickItem(Manager manager, int position);

        void onDelete(Manager view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RelativeLayout relativeLayout;
        private Button done;
        private TextView content;
        private TextView finish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_display);
            relativeLayout = itemView.findViewById(R.id.line1);
            done = itemView.findViewById(R.id.btn_done);
            content = itemView.findViewById(R.id.tv_content);
            finish = itemView.findViewById(R.id.finsh);
        }
    }
}
