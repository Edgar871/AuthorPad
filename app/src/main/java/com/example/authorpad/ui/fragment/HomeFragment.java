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
import com.example.authorpad.adapter.RVHomeAdapter;
import com.example.authorpad.app.DatabaseHelper;
import com.example.authorpad.model.Story;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvHome;
    private DatabaseHelper databaseHelper;

    private RVHomeAdapter rvHomeAdapter;
    private List<Story> stories;


    public HomeFragment() {
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rvHome = v.findViewById(R.id.rv_home);

        rvHome.setLayoutManager(new GridLayoutManager(getContext(), 2));

        databaseHelper = new DatabaseHelper(getContext());

        stories = new ArrayList<>();

        fetchStories();

        return v;
    }

    private void fetchStories() {
        stories.clear();
        Cursor cursor = databaseHelper.fetchStories();

        while (cursor.moveToNext()) {
            stories.add(new Story(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(0),
                    cursor.getInt(3)
            ));
        }

        rvHomeAdapter = new RVHomeAdapter((ArrayList<Story>) stories, getActivity());
        rvHome.setAdapter(rvHomeAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        fetchStories();
    }
}