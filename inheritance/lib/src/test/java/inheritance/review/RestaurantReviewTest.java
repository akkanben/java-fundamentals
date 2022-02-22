package inheritance.review;

import inheritance.restaurant.Restaurant;
import org.junit.jupiter.api.Test;

public class RestaurantReviewTest {

    @Test
    void test_restaurantreview_constructor() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Person McPersonface", 2, "This is my opinion.");
        assert(sutB.restaurant.equals(sutA));
        assert(sutB.author.equals("Person McPersonface"));
        assert(sutB.getStarScore() == 2);
        assert(sutB.body.equals("This is my opinion."));
    }

    @Test
    void test_restaurantreview_toString() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Person McPersonface", 2, "This is my opinion.");
        assert(sutB.toString().equals("{ restaurant: Dick's, author: Person McPersonface, score: 2, review: This is my opinion. }"));
    }
}
