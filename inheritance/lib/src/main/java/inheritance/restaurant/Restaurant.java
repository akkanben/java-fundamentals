package inheritance.restaurant;

import inheritance.User;
import inheritance.review.RestaurantReview;

import java.util.ArrayList;

public class Restaurant{
    private final String name;
    private int priceScore;
    private int reviewCount = 0;
    private final ArrayList<RestaurantReview> reviews;

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

    public void addReview(RestaurantReview review) {
        if(!reviews.contains(review)) {
            reviews.add(review);
            reviewCount++;
        }
    }

    public void updateReviewStars(User author, int newStars) {
        for(RestaurantReview element : reviews) {
            if(element.getAuthor().equals(author))
                element.setStarScore(newStars);
        }
    }

    private int getAverageReviewStarScore(ArrayList<RestaurantReview> list) {
        int sum = 0;
       for(RestaurantReview element : list) {
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
