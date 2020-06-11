package com.example.trivbox;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trivbox.models.Score;
import com.example.trivbox.utils.ScoresDbHelper;

import java.util.List;

public class ScoreFragment extends Fragment {
    private RecyclerView scoreboard_rv;
    private ScoresDbHelper dbObject;
    private TextView scoreboardTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        scoreboardTitle = rootView.findViewById(R.id.scoreboardTitle_id);
        scoreboardTitle.setText("My Score");

        scoreboard_rv = rootView.findViewById(R.id.scoreboard_rv);
        scoreboard_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbObject = new ScoresDbHelper(this.getActivity());
        List<Score> scoreList = dbObject.getAllScores();

        scoreboard_rv.setAdapter(new ScoreAdapter(scoreList));
        return rootView;
    }
}
