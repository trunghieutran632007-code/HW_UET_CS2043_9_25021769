package Bai5_04.src;

import java.util.*;

//class phu de luu thong tin cua cac tu
class WordEntry {
    String word;
    int count;

    WordEntry(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

public class WordCounterArrayList {
    private List<WordEntry> wordList;

    public WordCounterArrayList() {
        wordList = new ArrayList<>();
    }

    public void analyzeWithArrayList(String text) {
        if (text == null || text.isEmpty())
            return;

        String cleanedText = text.toLowerCase().replaceAll("[,.;!]", "");
        String[] words = cleanedText.split("\\s+");

        for (String word : words) {
            if (word.isEmpty())
                continue;

            WordEntry existingEntry = findEntry(word);

            if (existingEntry != null) {
                existingEntry.count++;
            } else {
                wordList.add(new WordEntry(word, 1));
            }
        }
    }

    public WordEntry findEntry(String word) {
        for (WordEntry entry : wordList) {
            if (entry.word.equals(word)) {
                return entry;
            }
        }
        return null;
    }

    public void displayResult() {
        System.out.println("=== Word Count ArrayList ===");
        for (WordEntry entry : wordList) {
            System.out.println(entry.word + ": " + entry.count);
        }
    }

    public void findMostFrequentWord() {
        if (wordList.isEmpty()) {
            System.out.println("Danh sach rong, khong co thong tin de hien thi.");
            return;
        }

        WordEntry mostFrequent = wordList.get(0);

        for (WordEntry entry : wordList) {
            if (entry.count > mostFrequent.count) {
                mostFrequent = entry;
            }
        }

        System.out.println("=== Most frequent word ArrayList ===");
        System.out.println("   " + mostFrequent.word + "(" + mostFrequent.count + " times)");
    }
}
