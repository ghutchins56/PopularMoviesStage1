package com.example.android.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

interface MainActivityInterface {
    String getSortBy();
    MovieAdapter getMovieAdapter();
    TextView getErrorMessage();
    ProgressBar getLoadingIndicator();
    RecyclerView getMovieGrid();
    void startDetailActivity(Movie movie);
}
