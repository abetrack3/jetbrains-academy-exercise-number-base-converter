package ReadabilityScore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = args[0];
        String text = new Scanner(new File(fileName)).nextLine();
        String[] sentences = text.split("[!?.]\\s?");
        int characterCount = text.length();
        int sentenceCount = sentences.length;
        int totalWordCount = 0;
        for (String eachSentence : sentences) {
            int wordCount = eachSentence.split("\\s").length;
            totalWordCount += wordCount;
        }
        characterCount -= totalWordCount - 1;
        double readabilityScore = 4.71 * characterCount / totalWordCount + 0.5 * totalWordCount / sentenceCount - 21.43;
        int ageFrom = (int) (Math.ceil(readabilityScore) + 5);
        int ageTo = ageFrom == 18 ? 24 : ageFrom + 1;
        System.out.println("""
                The text is:
                %s
                Words: %d
                Sentences: %d
                Characters: %d
                The score is: %.2f
                This text should be understood by %d-%d-year-olds."""
                .formatted(
                        text,
                        totalWordCount,
                        sentenceCount,
                        characterCount,
                        readabilityScore,
                        ageFrom,
                        ageTo
                ));
    }
}
