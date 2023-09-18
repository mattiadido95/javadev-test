package com.onetag.javadev;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Factory for Extractor generation
 */
public class ExtractorFactory {

    private static class AwardExtractor implements Extractor {
        public List<String> extract() {
            Loader loader = new Loader();
            List<Winner> winners = loader.getWinners();
            return null;
        }
    }

    public static Extractor getExtractor() {
        // TODO: create Class that implements Extractor and return it

        AwardExtractor awardExtractor = new AwardExtractor();
        return awardExtractor;
    }
}
