package inheritance.review;

import inheritance.User;
import inheritance.restaurant.Restaurant;

public class RestaurantReview extends Review {
    Restaurant restaurant;

    public RestaurantReview(Restaurant restaurant, User author, int starScore, String body) {
        super(author, starScore, body);
        this.restaurant = restaurant;
    }

    @Override
    public void updateStars(int starScore) {
        if(starScore < 0 || starScore > 5)
            throw new IllegalArgumentException("Star score " + starScore + " out of range.");
        restaurant.updateReviewStars(author, starScore);
        this.setStarScore(starScore);
    }

    @Override
    public String toString() {
        return "{ restaurant: " + restaurant.getName() + ", author: " + author.toString() + ", score: " + getStarScore() + ", review: " + body + " }";
    }
}
