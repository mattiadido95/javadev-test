package com.onetag.javadev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Loader {
    private String F_PATH = "src/main/resources/oscar_age_female.csv";
    private String M_PATH = "src/main/resources/oscar_age_male.csv";

    HashMap<String, Winner> awards = new HashMap<>();
    List<Winner> winners = new ArrayList<>();

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
            reader.readLine(); // skip the first line
            while ((line = reader.readLine()) != null) {
                Winner newWinner = getWinner(line);
                if (newWinner != null) {
                    if (this.awards.containsKey(newWinner.getName())) {
                        // update the winner
                        Winner winner = this.awards.get(newWinner.getName());
                        winner.update(newWinner.getYears().get(0), newWinner.getMovies().get(0), newWinner.getAge());
                        if (winner.getYears().size() > 1 && !this.winners.contains(winner)) {
                            this.winners.add(winner);
                        }
                    } else {
                        this.awards.put(newWinner.getName(), newWinner);
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
            int year = Integer.parseInt(removeQuotes(parts[1].trim()));
            int age = Integer.parseInt(removeQuotes(parts[2].trim()));
            String name = removeQuotes(parts[3].trim());
            String movie = removeQuotes(parts[4].trim());
            Winner winner = new Winner(year, age, name, movie);
            return winner;
        }
        return null;
    }

    private String removeQuotes(String s) {
        return s.replaceAll("\"", "");
    }




    // sort function that sort elements of arraylist by numWins value and for same numWins value sort by age
    public List<Winner> getWinners() {
        // sort the winners list by numWins value
        Collections.sort(this.winners, (w1, w2) -> {
            // Confronto per la variabile nuwin
            int result = Integer.compare(w2.getNumWins(), w1.getNumWins());

            // Se le variabili nuwin sono uguali, confronto per la variabile age
            if (result == 0) {
                result = Integer.compare(w1.getActualAge(), w2.getActualAge());
            }

            if (result == 0) {
                result = Integer.compare(w1.getYears().get(0), w2.getYears().get(0));
                // fatto perche usando un array winners inserisco i vincitori in ordine di ultima vincita e usciva un
                // ordine diverso dal test ma non era specificato nel readme
            }

            return result;
        });
        return this.winners;
    }
}
