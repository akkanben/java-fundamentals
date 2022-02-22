package inheritance;

import inheritance.review.RestaurantReview;
import inheritance.review.Review;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Restaurant {
    private final String name;
    private int starScore = 0;
    private int priceScore;
    private int reviewCount = 0;
    private ArrayList<RestaurantReview> reviews;

    public Restaurant(String name, int priceScore) {
        this.name = name;
        setPriceScore(priceScore);
        reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getStarScore() {
        return starScore;
    }

    public int getPriceScore() {
        return priceScore;
    }

    private void setStarScore(int starScore) {
       if(starScore < 0 || starScore > 5)
           throw new IllegalArgumentException("Star score " + starScore + " out of range.");
       this.starScore = starScore;
    }

    public void setPriceScore(int priceScore) {
        if(priceScore < 0 || priceScore > 3)
            throw new IllegalArgumentException("Star score " + starScore + " out of range.");
        this.priceScore = priceScore;
    }

    public void addReview(RestaurantReview review) {
        reviews.add(review);
        reviewCount++;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", stars: " + starScore + ", price: " + priceScore + " }";
    }




}
