package inheritance.review;

import inheritance.User;

public class Review {
    User author;
    protected int starScore;
    String body;

    public Review(User author, int starScore, String body) {
        this.author = author;
        this.body = body;
        setStarScore(starScore);
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
       return "{ author: " + author.toString() + ", score: " + starScore + ", review: " + body + " }";
    }

    public User getAuthor() {
        return author;
    }
}
