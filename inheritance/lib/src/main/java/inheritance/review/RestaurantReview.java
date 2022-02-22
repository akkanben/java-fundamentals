package inheritance.review;

import inheritance.restaurant.Restaurant;

public class RestaurantReview extends Review {
    Restaurant restaurant;

    public RestaurantReview(Restaurant restaurant, String author, int starScore, String body) {
        super(author, starScore, body);
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "{ restaurant: " + restaurant.getName() + ", author: " + author + ", score: " + getStarScore() + ", review: " + body + " }";
    }
}
