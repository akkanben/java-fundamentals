package inheritance.review;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Review {
    String author;
    private int starScore;
    String body;
    ArrayList<Review> reviewsList;

    public Review(String author, int starScore, String body) {
        this.author = author;
        this.body = body;
        setStarScore(starScore);
        reviewsList = new ArrayList<>();
    }

    public void setStarScore(int starScore) {
        this.starScore = starScore;
    }

    public int getStarScore() {
        return starScore;
    }

    public void updateStars(int starScore) {
        if(starScore < 0 || starScore > 5)
            throw new IllegalArgumentException("Star score " + starScore + " out of range.");
        setStarScore(starScore);
    }

    @Override
    public String toString() {
       return "{ author: " + author + ", score: " + starScore + ", review: " + body + " }";
    }

    public String getAuthor() {
        return author;
    }
}
