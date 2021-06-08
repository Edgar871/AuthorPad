package com.example.authorpad.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.authorpad.R;
import com.example.authorpad.model.Story;
import com.example.authorpad.ui.activity.ViewActivity;

import java.util.ArrayList;

public class RVHomeAdapter extends RecyclerView.Adapter<RVHomeAdapter.ViewHolder> {

    private ArrayList<Story> stories;
    private Activity activity;

    public RVHomeAdapter(ArrayList<Story> stories, Activity activity) {
        this.activity = activity;
        this.stories = stories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_public, parent, false);
        return new RVHomeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewActivity.class);
                intent.putExtra("story", stories.get(position));
                activity.startActivity(intent);
            }
        });
        holder.tvTitle.setText(stories.get(position).getTitle());
        holder.tvStory.setText(stories.get(position).getStory());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvStory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvStory = itemView.findViewById(R.id.tv_item_story);
        }
    }
}
