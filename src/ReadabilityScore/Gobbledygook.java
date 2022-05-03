package ReadabilityScore;

public final class Gobbledygook extends ReadabilityTest {

    public Gobbledygook(Text text) {
        super(text, "Simple Measure of Gobbledygook");
    }

    @Override
    protected void calculateScore() {
        int polySyllables = text.getTotalPolysyllableCount();
        int sentences = text.getTotalSentenceCount();
        score = 1.043 * Math.sqrt(30.0 * polySyllables / sentences) + 3.1291;
    }
}
