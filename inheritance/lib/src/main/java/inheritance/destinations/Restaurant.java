package inheritance.destinations;

import inheritance.User;
import inheritance.review.Review;
import inheritance.review.Reviewable;

import java.util.ArrayList;

public class Restaurant implements Reviewable {
    private final String name;
    private int priceScore;
    private int reviewCount = 0;
    private final ArrayList<Review> reviews;

    public Restaurant(String name, int priceScore) {
        this.name = name;
        setPriceScore(priceScore);
        reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getStarScore() {
        if(reviews.size() > 0)
            return getAverageReviewStarScore(reviews);
        else
            return 0;
    }

    public int getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(int priceScore) {
        if(priceScore < 0 || priceScore > 3)
            throw new IllegalArgumentException("Star score " + getStarScore() + " out of range.");
        this.priceScore = priceScore;
    }

    public void addReview(Review review) {
        if(!reviews.contains(review)) {
            reviews.add(review);
            reviewCount++;
        }
    }

    public void updateReviewStars(User author, int newStars) {
        for(Review element : reviews) {
            if(element.getAuthor().equals(author))
                element.setStarScore(newStars);
        }
    }

    private int getAverageReviewStarScore(ArrayList<Review> list) {
        int sum = 0;
       for(Review element : list) {
          sum += element.getStarScore();
       }
       return sum / list.size();
    }

    public int getReviewCount() {
        return reviewCount;
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", stars: " + getStarScore() + ", price: " + priceScore + " }";
    }




}
