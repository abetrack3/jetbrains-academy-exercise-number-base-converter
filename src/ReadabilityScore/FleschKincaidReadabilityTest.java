package ReadabilityScore;

public final class FleschKincaidReadabilityTest extends ReadabilityTest{

    public FleschKincaidReadabilityTest(Text text) {
        super(text, "Flesch–Kincaid readability tests");
    }

    @Override
    protected void calculateScore() {
        int words = text.getTotalWordCount();
        int sentences = text.getTotalSentenceCount();
        int syllables = text.getTotalSyllableCount();
        score = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }
}
