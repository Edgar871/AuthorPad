package com.example.authorpad.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.authorpad.R;
import com.example.authorpad.adapter.RVPersonalAdapter;
import com.example.authorpad.app.DatabaseHelper;
import com.example.authorpad.model.Story;

import java.util.ArrayList;

public class MyStoryFragment extends Fragment {

    private ArrayList<Story> stories;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;

    private RVPersonalAdapter rvPersonalAdapter;


    public MyStoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_story, container, false);

        recyclerView = v.findViewById(R.id.rv_private);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        databaseHelper = new DatabaseHelper(getContext());

        stories = new ArrayList<>();

        fetchStories();

        return v;
    }

    private void fetchStories() {
        stories.clear();
        Cursor cursor = databaseHelper.fetchPersonalStories(getContext());

        while (cursor.moveToNext()) {
            stories.add(new Story(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(0),
                    cursor.getInt(3)
            ));
        }

        rvPersonalAdapter = new RVPersonalAdapter(stories, getActivity());
        recyclerView.setAdapter(rvPersonalAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchStories();
    }
}