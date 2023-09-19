package com.onetag.javadev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Classe loader che legge i dati dai file csv e li carica in una HashMap
 */
public class Loader {
    private String F_PATH = "src/main/resources/oscar_age_female.csv";
    private String M_PATH = "src/main/resources/oscar_age_male.csv";

    HashMap<String, Winner> awards = new HashMap<>(); // HashMap che contiene tutti i dati dei vincitori
    List<Winner> winners = new ArrayList<>(); // ArrayList che contiene i vincitori con piu di 1 oscar trovati durante la lettura dei file

    public Loader() {
        run();
    }

    /*
     * legge i dati dai file csv e li carica in una HashMap
     * */
    public HashMap<String, Winner> run() {
        readAwards(F_PATH);
        readAwards(M_PATH);
        return awards;
    }

    /*
     * legge i dati da un file csv e li carica in una HashMap
     * */
    private void readAwards(String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine(); // skip the first line
            while ((line = reader.readLine()) != null) {
                Winner newWinner = extractWinner(line);
                if (newWinner != null) {
                    if (this.awards.containsKey(newWinner.getName())) {
                        // winner gia presente nella HashMap, aggiorno i dati del vincitore nella HashMap
                        Winner winner = this.awards.get(newWinner.getName());
                        winner.update(newWinner.getYears().get(0), newWinner.getMovies().get(0), newWinner.getAge());
                        // se il vincitore ha piu di 1 oscar e non e gia presente nella lista dei vincitori con piu di 1 oscar lo aggiungo
                        if (winner.getYears().size() > 1 && !this.winners.contains(winner)) {
                            this.winners.add(winner);
                        }
                    } else {
                        // winner non presente nella HashMap, lo aggiungo
                        this.awards.put(newWinner.getName(), newWinner);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * estrae i dati di un vincitore da una stringa
     * */
    private Winner extractWinner(String line) {
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


    /*
     * ordina la lista vincitori con piu di oscar in base al numero di oscar, all'eta attuale e all'anno della prima vittoria
     * */
    public List<Winner> getWinners() {
        Collections.sort(this.winners, (w1, w2) -> {
            int result = Integer.compare(w2.getNumWins(), w1.getNumWins());
            if (result == 0) {
                result = Integer.compare(w1.getActualAge(), w2.getActualAge());
            }
            // ordino in base all'anno della prima vittoria perchè usando un arraylist winners con i vincitori con piu di 1 oscar
            // l'ordine per i pari eta era stabilito in base all'ordine di inserimento nella lista, quindi l'ultima vittoria
            // e non su chi ha vinto per primo come nel file csv, il test non funzionava per gli attori "Jack Nicholson" e "Dustin Hoffman"
            if (result == 0) {
                result = Integer.compare(w1.getYears().get(0), w2.getYears().get(0));
            }

            return result;
        });
        return this.winners;
    }
}
