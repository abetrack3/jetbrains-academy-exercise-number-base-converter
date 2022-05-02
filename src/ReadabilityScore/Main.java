package ReadabilityScore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String text = new Scanner(System.in).nextLine();
        String[] sentences = text.split("[!?.]\\s?");
        int totalWordCount = 0;
        for (String eachSentence : sentences) {
            int wordCount = eachSentence.split("\\s").length;
            totalWordCount += wordCount;
        }
        double averageWordCountPerSentence = (double) totalWordCount / sentences.length;
        System.out.println(averageWordCountPerSentence <= 10.0 ? "EASY" : "HARD");
    }
}
