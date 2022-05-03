package ReadabilityScore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record Text(String text) {

    public static final String SENTENCE_SPLIT_PATTERN = "[!?.]\\s?";
    public static final String WHITESPACE = "\\s";
    public static final String ONE_OR_MORE_VOWEL = "[aeiouy]+";
    public static final String ENDS_WITH_E = "e$";
    public static final String EMPTY_STRING = "";

    private String[] getSentencesFromText() {
        return text.split(SENTENCE_SPLIT_PATTERN);
    }

    private String[] getWordsFromSentence(String sentence) {
        return sentence.split(WHITESPACE);
    }

    public int getTotalWordCount() {
        String[] sentences = getSentencesFromText();
        int totalWordCount = 0;
        for (String eachSentence : sentences) {
            int wordCount = getWordsFromSentence(eachSentence).length;
            totalWordCount += wordCount;
        }
        return totalWordCount;
    }

    public int getTotalCharacterCount() {
        return text.length() - getTotalWordCount() + 1;
    }

    public int getTotalSentenceCount() {
        return getSentencesFromText().length;
    }

    public int getTotalSyllableCount() {
        int count = 0;
        for (String eachSentence : getSentencesFromText()) {
            for (String eachWord : getWordsFromSentence(eachSentence)) {
                count += countSyllableInWord(eachWord);
            }
        }
        return count;
    }

    public int getTotalPolysyllableCount() {
        return Stream
                .of(getSentencesFromText())
                .mapToInt(sentence -> Stream
                        .of(getWordsFromSentence(sentence))
                        .mapToInt(word -> isPolysyllable(word) ? 1 : 0)
                        .sum()).sum();
    }

    private boolean isPolysyllable(String word) {
        return countSyllableInWord(word) > 2;
    }

    private int countSyllableInWord(String word) {
        word = word.toLowerCase().trim();
        if (word.equals("the")) {
            return 1;
        }
        int count = 0;
        Pattern pattern = Pattern.compile(ONE_OR_MORE_VOWEL);
        word = word.replaceAll(ENDS_WITH_E, EMPTY_STRING);
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()) {
            count++;
        }
        return Math.max(1, count);
    }

    public String toString() {
        return """
                The text is:
                %s
                Words: %d
                Sentences: %d
                Characters: %d
                Syllables: %d
                Polysyllables: %d"""
                .formatted(
                        text,
                        getTotalWordCount(),
                        getTotalSentenceCount(),
                        getTotalCharacterCount(),
                        getTotalSyllableCount(),
                        getTotalPolysyllableCount()
                );
    }

}
