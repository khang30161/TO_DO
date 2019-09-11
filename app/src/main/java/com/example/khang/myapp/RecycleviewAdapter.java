package com.example.khang.myapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khang.myapp.fragment.Doing;

import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Manager> managers;
    private Resources resources;
    private Action action;


    public RecycleviewAdapter(Doing context, List<Manager> managers, Action action) {
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
        //holder.content.setText(managers.get(i).getContent());
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


    }

    @Override
    public int getItemCount() {
        return (managers != null ? managers.size() : 0);
    }

    public interface Action {
        void onClickItem(Manager manager, int position);

        void onLongClickItem(Manager manager, int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RelativeLayout relativeLayout;
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_display);
            //content = itemView.findViewById(R.id.tv_content);
            relativeLayout = itemView.findViewById(R.id.line1);
        }
    }
}
