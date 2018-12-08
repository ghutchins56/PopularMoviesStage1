package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private static final String
            BASE_URL = "https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        TextView title = findViewById(R.id.title);
        title.setText(movie.getTitle());
        ImageView poster = findViewById(R.id.poster);
        String posterPath = movie.getPosterPath();
        if (posterPath.equals("null")) poster.setImageResource(R.drawable.popcorn_rendered);
        else Picasso.with(this).load(BASE_URL + posterPath).into(poster);
        TextView releaseDateView = findViewById(R.id.release_date);
        String releaseDate = movie.getReleaseDate();
        if (releaseDate.length() > 4) releaseDate = releaseDate.substring(0, 4);
        releaseDateView.setText(releaseDate);
        TextView voteAverageView = findViewById(R.id.vote_average);
        String voteAverageText = "";
        String voteAverage = movie.getVoteAverage();
        if (!voteAverage.isEmpty()) voteAverageText = getString(R.string.vote_average, voteAverage);
        voteAverageView.setText(voteAverageText);
        TextView overview = findViewById(R.id.overview);
        overview.setText(movie.getOverview());
    }
}
