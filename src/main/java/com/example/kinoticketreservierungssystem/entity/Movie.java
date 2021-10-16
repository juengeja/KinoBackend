package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import java.util.List;

@Container(containerName = "Movies", ru = "400")
public class Movie {
    @Id
    private String movieId;
    private String movieName;
    private String movieDomain;
    //Genre naming convention: Action, Thriller, Drama, Comedy...
    @PartitionKey
    private String mainGenre;
    private List<String> genres;
    private int duration;
    private boolean liveStatus;
    private String img;
    private String description;

    public Movie(String movieId, String movieName, String movieDomain, String mainGenre, int duration, boolean liveStatus, String img, String description) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDomain = movieDomain;
        this.mainGenre = mainGenre;
        this.duration = duration;
        this.liveStatus = liveStatus;
        this.img = img;
        this.description = description;
    }

    public Movie() {

    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, Movie.class);
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDomain() {
        return movieDomain;
    }

    public void setMovieDomain(String movieDomain) {
        this.movieDomain = movieDomain;
    }

    public String getMainGenre() {
        return mainGenre;
    }

    public void setMainGenre(String mainGenre) {
        this.mainGenre = mainGenre;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(boolean liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


