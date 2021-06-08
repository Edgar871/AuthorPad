package com.example.authorpad.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.authorpad.R;
import com.example.authorpad.model.Story;
import com.example.authorpad.ui.activity.UpdateActivity;

import java.util.ArrayList;

public class RVPersonalAdapter extends RecyclerView.Adapter<RVPersonalAdapter.ViewHolder> {

    private ArrayList<Story> stories;
    private Context context;
    private Activity activity;

    public RVPersonalAdapter(ArrayList<Story> stories, Activity activity) {
        this.stories = stories;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_public, parent, false);
        this.context = parent.getContext();
        return new RVPersonalAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("story", stories.get(position));
            activity.startActivity(intent);
        });
        holder.tvTitle.setText(stories.get(position).getTitle());
        holder.tvStory.setText(stories.get(position).getStory());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle, tvStory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvStory = itemView.findViewById(R.id.tv_item_story);
        }
    }
}
