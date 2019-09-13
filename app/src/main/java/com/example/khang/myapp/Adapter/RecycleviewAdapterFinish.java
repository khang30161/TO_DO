package com.example.khang.myapp.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khang.myapp.Complete;
import com.example.khang.myapp.Object.Manager;
import com.example.khang.myapp.R;

import java.util.List;

public class RecycleviewAdapterFinish extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Manager> managers;
    private Resources resources;
    private RecycleviewAdapter.Action action;
    private View.OnClickListener itemClick;

    public RecycleviewAdapterFinish(Complete context, List<Manager> managers, RecycleviewAdapter.Action action) {
        this.context = context;
        this.managers = managers;
        this.action = (RecycleviewAdapter.Action) action;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_completed, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.title.setText(managers.get(i).getText());
        holder.content.setText(managers.get(i).getContent());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null)
                    action.onClickItem(managers.get(i), i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action !=null)
                    action.onDelete(managers.get(i),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (managers != null ? managers.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RelativeLayout relativeLayout;
        private TextView content;
        private TextView finish;
        private Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_display);
            relativeLayout = itemView.findViewById(R.id.line1);
            content = itemView.findViewById(R.id.tv_content);
            finish = itemView.findViewById(R.id.finsh);
            delete=itemView.findViewById(R.id.btn_delete);
        }
    }
}
