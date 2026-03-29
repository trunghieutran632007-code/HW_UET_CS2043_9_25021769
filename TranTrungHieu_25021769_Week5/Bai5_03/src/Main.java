package Bai5_03.src;

import java.util.*;

public class Main {
    //1.Chuan hoa ve chu thuong, bo dau
    static String normalize(String text) {
        text = text.toLowerCase();

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= 'a' && c <= 'z') {
                result.append(c);
            } else if (c >= '0' && c <= '9') {
                result.append(c);
            } else if (c == ' ') {
                result.append(c);
            }
        }

        return result.toString();
    }

    //2.Dem so tu va luu vao Map
    static HashMap<String, Integer> countWords(String normalize) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] words = normalize.split("\\s+");

        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        return map;
    }

    //3.1 Sap xep, thong ke
    static String findMostFrequent(HashMap<String, Integer> map) {
        String mostFreqWord = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFreqWord = entry.getKey();
            }
        }
        return mostFreqWord + " (" + maxCount + " times)";
    }

    //3.2 Cac tu xuat hien dung 1 lan
    static List<String> findUniqueWords(HashMap<String, Integer> map) {
        List<String> uniqueWords = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueWords.add(entry.getKey());
            }
        }

        Collections.sort(uniqueWords);
        return uniqueWords;
    }

    public static void main(String[] args) {
        String text = "To be or not to be, that is the question. "
                + "Whether 'tis nobler in the mind to suffer "
                + "the slings and arrows of outrageous fortune, "
                + "or to take arms against a sea of troubles.";

        String normalized = normalize(text);
        System.out.println("=== Normalized text ===");
        System.out.println(normalized);

        HashMap<String, Integer> wordMap = countWords(normalized);

        System.out.println("\n=== Word count ===");
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("  %-15s : %d%n", e.getKey(), e.getValue());
        }

        System.out.println("\n=== Most frequent word ===");
        System.out.println("  " + findMostFrequent(wordMap));

        List<String> unique = findUniqueWords(wordMap);
        System.out.println("\n=== Unique words ===");
        System.out.println("  Count: " + unique.size());
        System.out.println("  " + unique);
    }
}
