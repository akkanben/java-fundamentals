package inheritance;

public class Review {
    String author;
    private int starScore;
    String body;

    public Review(String author, int starScore, String body) {
        this.author = author;
        this.body = body;
        setStarScore(starScore);
    }

    public void setStarScore(int starScore) {
        if(starScore < 0 || starScore > 5)
            throw new IllegalArgumentException("Star score " + starScore + " out of range.");
        this.starScore = starScore;
    }

    @Override
    public String toString() {
       return "{ author: " + author + ", score: " + starScore + ", review: " + body + " }";
    }

}
