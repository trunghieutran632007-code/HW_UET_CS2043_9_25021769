package Bai5_03.src;

import java.util.*;

public class Main {
    static final String SAMPLE_TEXT =
    "THE IMPORTANCE OF CHILDREN'S PLAY " +
    "Brick by brick, six-year-old Alice is building a magical kingdom. " +
    "Imagining fairy-tale turrets and fire-breathing dragons, " +
    "wicked witches and gallant heroes, she's creating an enchanting world. " +
    "Although she isn't aware of it, this fantasy is helping her take her first steps " +
    "towards her capacity for creativity and so it will have important repercussions " +
    "in her adult life. " +
    "Minutes later, Alice has abandoned the kingdom in favour of playing schools " +
    "with her younger brother. " +
    "When she bosses him around as his 'teacher', " +
    "she's practising how to regulate her emotions through pretence. " +
    "Later on, when they tire of this and settle down with a board game, " +
    "she's learning about the need to follow rules and take turns with a partner. " +
    "Play in all its rich variety is one of the highest achievements of the human species, " +
    "says Dr David Whitebread from the Faculty of Education at the University of Cambridge, UK. " +
    "It underpins how we develop as intellectual, problem-solving adults " +
    "and is crucial to our success as a highly adaptable species. " +
    "Recognising the importance of play is not new: " +
    "over two millennia ago, the Greek philosopher Plato extolled its virtues " +
    "as a means of developing skills for adult life, " +
    "and ideas about play-based learning have been developing since the 19th century. " +
    "But we live in changing times, and Whitebread is mindful of a worldwide decline in play, " +
    "pointing out that over half the people in the world now live in cities. " +
    "The opportunities for free play, which I experienced almost every day of my childhood, " +
    "are becoming increasingly scarce. " +
    "Outdoor play is curtailed by perceptions of risk to do with traffic, " +
    "as well as parents increased wish to protect their children from being the victims of crime, " +
    "and by the emphasis on earlier is better which is leading to greater competition " +
    "in academic learning and schools. " +
    "International bodies like the United Nations and the European Union " +
    "have begun to develop policies concerned with children's right to play, " +
    "and to consider implications for leisure facilities and educational programmes. " +
    "But what they often lack is the evidence to base policies on. " +
    "The type of play we are interested in is child-initiated, spontaneous and unpredictable " +
    "but, as soon as you ask a five-year-old to play, " +
    "then you as the researcher have intervened, explains Dr Sara Baker. " +
    "And we want to know what the long-term impact of play is. It's a real challenge.";
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
        String text = SAMPLE_TEXT;

        String normalized = normalize(text);
        System.out.println("=== Normalized text ===");
        System.out.println(normalized);

        HashMap<String, Integer> wordMap = countWords(normalized);

        System.out.println("\n=== Word count ===");
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> e : entries) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        System.out.println("\n=== Most frequent word ===");
        System.out.println("  " + findMostFrequent(wordMap));

        List<String> unique = findUniqueWords(wordMap);
        System.out.println("\n=== Unique words ===");
        System.out.println("  Count: " + unique.size());
        System.out.println("  " + unique);
    }
}
