package inheritance.review;

import inheritance.User;
import inheritance.restaurant.Restaurant;
import org.junit.jupiter.api.Test;

public class RestaurantReviewTest {

    @Test
    void test_restaurantreview_constructor() {
        User user = new User("Person", "McPersonface");
        Restaurant restaurant = new Restaurant("Dick's",1);
        RestaurantReview sut = new RestaurantReview(restaurant,user, 2, "This is my opinion.");
        assert(sut.restaurant.equals(restaurant));
        assert(sut.author.toString().equals("Person McPersonface"));
        assert(sut.getStarScore() == 2);
        assert(sut.body.equals("This is my opinion."));
    }

    @Test
    void test_restaurantreview_toString() {
        User user = new User("Person", "McPersonface");
        Restaurant restaurant = new Restaurant("Dick's",1);
        RestaurantReview sut = new RestaurantReview(restaurant,user, 2, "This is my opinion.");
        assert(sut.toString().equals("{ restaurant: Dick's, author: Person McPersonface, score: 2, review: This is my opinion. }"));
    }

    @Test
    void test_updatestars() {
        User user = new User("Person", "McPersonface");
        Restaurant restaurant = new Restaurant("Dick's",1);
        RestaurantReview sut = new RestaurantReview(restaurant,user, 2, "This is my opinion.");
        restaurant.addReview(sut);
        assert(restaurant.getStarScore() == 2);
        sut.updateStars(5);
        assert(restaurant.getStarScore() == 5);

    }
}
