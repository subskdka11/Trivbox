package com.example.trivbox;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trivbox.models.Score;
import com.example.trivbox.utils.ScoresDbHelper;

import java.util.List;

public class ScoreFragment extends Fragment {
    private RecyclerView score_rv;
    private ScoresDbHelper dbObject;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        score_rv = rootView.findViewById(R.id.score_rv);
        score_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbObject = new ScoresDbHelper(this.getActivity());
        List<Score> scoreList = dbObject.getAllScores();

        score_rv.setAdapter(new ScoreAdapter(scoreList));
        return rootView;
    }
}
