package com.example.kinoticketreservierungssystem.entity;

import java.io.File;
import java.time.LocalDate;


public class Movie {
    private int movieId;
    private String name;
    private String domain;
    private String genre;
    private int duration;
    private LocalDate release_date;
    private String description;
    private boolean menu;
    private boolean night_event;
    private boolean featured;
    private String trailer;

    private String img;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int length) {
        this.duration = length;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDomain() { return domain; }

    public void setDomain(String domain) { this.domain = domain; }

    public boolean isMenu() { return menu; }

    public void setMenu(boolean menu) { this.menu = menu; }

    public boolean isNight_event() { return night_event; }

    public void setNight_event(boolean night_event) { this.night_event = night_event; }

    public boolean isFeatured() { return featured; }

    public void setFeatured(boolean featured) { this.featured = featured; }

    public String getTrailer() { return trailer; }

    public void setTrailer(String trailer) { this.trailer = trailer; }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }
}
