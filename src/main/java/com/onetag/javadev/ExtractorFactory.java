package com.onetag.javadev;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Factory for Extractor generation
 */
public class ExtractorFactory {

    private static class AwardExtractor implements Extractor {
        public List<String> extract() {
            Loader loader = new Loader();
            List<Winner> winners = loader.getWinners(); // chiamo il loader per leggere e caricare i dati dai file.csv
            // extract the names of the winners
            ArrayList<String> names = new ArrayList<>(); // estraggo i nomi dei vincitori con piu di 1 un oscar
            for (Winner winner : winners) {
                names.add(winner.getName());
            }
            return names;
        }
    }

    public static Extractor getExtractor() {
        // TODO: create Class that implements Extractor and return it
        AwardExtractor awardExtractor = new AwardExtractor();
        return awardExtractor;
    }
}
