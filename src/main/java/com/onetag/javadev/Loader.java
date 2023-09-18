package com.onetag.javadev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Loader {
    private String F_PATH = "resources/oscar_age_female.csv";
    private String M_PATH = "resources/oscar_age_male.csv";

    HashMap<String, Winner> awards = new HashMap<>();
    List<Winner> winners = null;

    public Loader() {
        run();
    }

    public HashMap<String, Winner> run() {
        HashMap<String, Winner> awards = new HashMap<>();
        readAwards(F_PATH);
        readAwards(M_PATH);
        return awards;
    }

    private void readAwards(String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Winner winner = getWinner(line); // TODO: non va bene
                if (winner != null){
                    if (this.awards.containsKey(winner.getName())) {
                        // update the winner
                        Winner oldWinner = this.awards.get(winner.getName());

                    } else {
                        this.awards.put(winner.getName(), winner);
                    }

                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Winner getWinner(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            String year = parts[1].trim();
            String age = parts[2].trim();
            String name = parts[3].trim();
            String movie = parts[4].trim();
            Winner winner = new Winner(year, age, name, movie);
            return winner;
        }
        return null;
    }


    public List<Winner> getWinners() {
        return winners;
    }
}
