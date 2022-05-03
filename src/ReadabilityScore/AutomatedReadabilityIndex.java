package ReadabilityScore;

public final class AutomatedReadabilityIndex extends ReadabilityTest {

    public AutomatedReadabilityIndex(Text text) {
        super(text, "Automated Readability Index");
    }

    @Override
    protected void calculateScore() {
        int wordCount = text.getTotalWordCount();
        int characterCount = text.getTotalCharacterCount();
        int sentenceCount = text.getTotalSentenceCount();
        score = 4.71 * characterCount / wordCount + 0.5 * wordCount / sentenceCount - 21.43;
    }

}
