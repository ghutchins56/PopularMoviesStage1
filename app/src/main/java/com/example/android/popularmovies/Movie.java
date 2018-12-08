package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private final String Title;
    private final String ReleaseDate;
    private final String PosterPath;
    private final String VoteAverage;
    private final String Overview;

    Movie(String title, String releaseDate, String posterPath, String voteAverage, String overview) {
        Title = title;
        ReleaseDate = releaseDate;
        PosterPath = posterPath;
        VoteAverage = voteAverage;
        Overview = overview;
    }

    private Movie(Parcel source) {
        Title = source.readString();
        ReleaseDate = source.readString();
        PosterPath = source.readString();
        VoteAverage = source.readString();
        Overview = source.readString();
    }

    String getTitle() {
        return Title;
    }

    String getReleaseDate() {
        return ReleaseDate;
    }

    String getPosterPath() {
        return PosterPath;
    }

    String getVoteAverage() {
        return VoteAverage;
    }

    String getOverview() {
        return Overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(ReleaseDate);
        dest.writeString(PosterPath);
        dest.writeString(VoteAverage);
        dest.writeString(Overview);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
