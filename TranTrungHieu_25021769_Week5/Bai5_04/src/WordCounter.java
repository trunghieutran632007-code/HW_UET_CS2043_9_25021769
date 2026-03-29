package Bai5_04.src;

import java.util.*;

public class WordCounter {
    private Map<String, Integer> wordMap;

    public WordCounter() {
        wordMap = new HashMap<>();
    }

    public void analyze(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        String cleanedText = text.toLowerCase().replaceAll("[,.;!]", "");

        String[] words = cleanedText.split("\\s+");

        for (String word : words) {
            if (word.isEmpty())
                continue;

            if (wordMap.containsKey(word)) {
                int count = wordMap.get(word);
                wordMap.put(word, count + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
    }

    public void displayResult() {
        System.out.println("=== Word Count Hash Map===");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void findMostFrequentWord() {
        String mostFrequent = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }

        if (!mostFrequent.isEmpty()) {
            System.out.println("=== Most frequent word Hash Map ===");
            System.out.println("   " + mostFrequent + "(" + maxCount + " times)");
        }
    }

}
