package inheritance.destinations;

import inheritance.User;
import inheritance.review.Review;
import inheritance.review.Reviewable;

import java.util.ArrayList;

public class Destination implements Reviewable {
    protected String name;
    protected int priceScore;
    protected int reviewCount = 0;
    protected ArrayList<Review> reviews;

    public Destination(String name, int priceScore) {
        this.name = name;
        this.priceScore = priceScore;
        reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public int getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(int priceScore) {
        if(priceScore < 0 || priceScore > 3)
            throw new IllegalArgumentException("Star score " + getStarScore() + " out of range.");
        this.priceScore = priceScore;
    }
    public int getStarScore() {
        if(reviews.size() > 0)
            return getAverageReviewStarScore(reviews);
        else
            return 0;
    }

    private int getAverageReviewStarScore(ArrayList<Review> list) {
        int sum = 0;
        for(Review element : list) {
            sum += element.getStarScore();
        }
        return sum / list.size();
    }

    @Override
    public void addReview(Review review) {
        if(!reviews.contains(review)) {
            reviews.add(review);
            reviewCount++;
        }
    }

    @Override
    public void updateReviewStars(User author, int newStars) {
        for(Review element : reviews) {
            if(element.getAuthor().equals(author))
                element.setStarScore(newStars);
        }
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", price: " + priceScore + ", rating: " + getStarScore()+ " }";
    }
}
