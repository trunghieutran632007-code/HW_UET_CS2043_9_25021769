package Bai5_04.src;

public class Main {
    public static void main(String[] args) {
        WordCounter counter = new WordCounter();
        WordCounterArrayList counter1 = new WordCounterArrayList();
        String input = "Hello world. This is a java program. Hello java, hello world.";

        counter.analyze(input);
        counter.displayResult();
        counter.findMostFrequentWord();
        System.out.println("\n \n");

        counter1.analyzeWithArrayList(input);
        counter1.displayResult();
        counter1.findMostFrequentWord();
    }
}
