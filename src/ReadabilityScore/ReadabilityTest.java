package ReadabilityScore;

public abstract class ReadabilityTest {

    protected final Text text;
    protected double score;
    protected int ageUpperBound;

    private final String testName;


    public ReadabilityTest(Text text, String testName) {
        this.text = text;
        this.testName = testName;
        calculateScore();
        calculateAgeUpperBound();
    }

    protected abstract void calculateScore();

    private void calculateAgeUpperBound() {
        int ageLowerBound = (int) (Math.ceil(score) + 4);
        ageUpperBound = ageLowerBound == 18 ? 24 : ageLowerBound + 1;
    }

    public double getScore() {
        return score;
    }

    public int getAgeUpperBound() {
        return ageUpperBound;
    }

    public String toString() {
        return "%s: %.2f (about %d-year-olds).".formatted(testName, getScore(), getAgeUpperBound());
    }

}
