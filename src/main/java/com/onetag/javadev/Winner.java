package com.onetag.javadev;

import java.util.ArrayList;

public class Winner {
    private ArrayList<String> years;
    private String age;
    private String name;
    private ArrayList<String> movies;

    public Winner(String year, String age, String name, String movie) {
        this.years.add(year);
        this.age = age;
        this.name = name;
        this.movies.add(movie);
    }

    public void update(String year, String movie) {
        this.years.add(year);
        this.movies.add(movie);
    }

    public ArrayList<String> getYears() {
        return years;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }
}
