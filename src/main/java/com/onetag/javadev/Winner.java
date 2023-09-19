package com.onetag.javadev;

import java.util.ArrayList;

/**
 * Classe winner che contiene i dati di un vincitore caricati dal file csv
 */
public class Winner {
    private ArrayList<Integer> years = new ArrayList<>();
    private int age;
    private String name;
    private ArrayList<String> movies = new ArrayList<>();
    private Integer numWins = 0;
    private Integer actualAge = 0;

    public Winner(int year, int age, String name, String movie) {
        this.years.add(year);
        this.age = age;
        this.name = name;
        this.movies.add(movie);
        this.numWins += 1;
        this.actualAge = 2023 - year + age;
    }

    /*
    * aggiorna i dati di un vincitore gia presente nella HashMap
    * */
    public void update(int year, String movie, int age) {
        this.years.add(year);
        this.movies.add(movie);
        this.age = age;
        this.numWins += 1;
    }

    public Integer getActualAge() {
        return actualAge;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    public Integer getNumWins() {
        return numWins;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "years=" + years +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
