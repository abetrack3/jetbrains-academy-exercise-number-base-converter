package ReadabilityScore;

public final class ColemanLiauIndex extends ReadabilityTest {
    public ColemanLiauIndex(Text text) {
        super(text, "Coleman–Liau index");
    }

    @Override
    protected void calculateScore() {
        int wordCount = text.getTotalWordCount();
        int characterCount = text.getTotalCharacterCount();
        int sentenceCount = text.getTotalSentenceCount();
        score = 100.0 * 0.0588 * characterCount / wordCount - 100.0 * 0.296 * sentenceCount / wordCount - 15.8;
    }
}
