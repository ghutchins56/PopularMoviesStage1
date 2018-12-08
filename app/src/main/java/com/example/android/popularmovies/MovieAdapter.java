package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private ArrayList<Movie> Movies = new ArrayList<>();
    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";
    private Context context;
    private final MainActivityInterface ActivityInterface;

    MovieAdapter(MainActivityInterface mainActivityInterface) {
        ActivityInterface = mainActivityInterface;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView posterView;

        MovieAdapterViewHolder(View v) {
            super(v);
            posterView = v.findViewById(R.id.poster_view);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ActivityInterface.startDetailActivity(Movies.get(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_item, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        String posterPath = Movies.get(position).getPosterPath();
        if (posterPath.equals("null")) holder.posterView.setImageResource(R.drawable.popcorn_rendered);
        else Picasso.with(context).load(BASE_URL + posterPath).into(holder.posterView);
    }

    @Override
    public int getItemCount() {
        return Movies.size();
    }

    public void setMovies(ArrayList<Movie> movies) {
        Movies = movies;
        notifyDataSetChanged();
    }
}
