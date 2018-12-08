package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {
    private String sortBy;
    private MovieAdapter movieAdapter;
    private RecyclerView movieGrid;
    private TextView errorMessage;
    private ProgressBar loadingIndicator;
    private static final String SORT_BY_MOST_POPULAR = "popularity.desc";
    private static final String SORT_BY_HIGHEST_RATED = "vote_average.desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieGrid = findViewById(R.id.movie_grid);
        int spanCount = getResources().getInteger(R.integer.span_count);
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        movieGrid.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(this);
        movieGrid.setAdapter(movieAdapter);
        errorMessage = findViewById(R.id.error_message);
        loadingIndicator = findViewById(R.id.loading_indicator);
        sortBy = "popularity.desc";
        FetchMovies();
    }

    public String getSortBy() {
        return sortBy;
    }

    public MovieAdapter getMovieAdapter() {
        return movieAdapter;
    }

    public TextView getErrorMessage() {
        return errorMessage;
    }

    public ProgressBar getLoadingIndicator() {
        return loadingIndicator;
    }

    public RecyclerView getMovieGrid() {
        return movieGrid;
    }

    public void startDetailActivity(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_most_popular) {
            sortBy = SORT_BY_MOST_POPULAR;
            FetchMovies();
            return true;
        }

        if (id == R.id.action_highest_rated) {
            sortBy = SORT_BY_HIGHEST_RATED;
            FetchMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void FetchMovies() {
        movieGrid.setVisibility(View.INVISIBLE);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            errorMessage.setVisibility(View.INVISIBLE);
            loadingIndicator.setVisibility(View.VISIBLE);
            new FetchMoviesTask().execute(this);
        } else {
            errorMessage.setVisibility(View.VISIBLE);
            loadingIndicator.setVisibility(View.INVISIBLE);
        }
    }
}
