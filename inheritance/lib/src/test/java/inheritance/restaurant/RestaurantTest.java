package inheritance.restaurant;

import inheritance.User;
import inheritance.review.RestaurantReview;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantTest {

    @Test
    void test_restaurant_constructor() {
        Restaurant sut = new Restaurant("Toyoda Sushi",2);
        assert(sut.getName().equals("Toyoda Sushi"));
        assert(sut.getPriceScore() == 2);
    }

    @Test
    void test_restaurant_tostring() {
        Restaurant sut = new Restaurant("Dick's",1);
        assert(sut.toString().equals("{ name: Dick's, stars: 0, price: 1 }"));
    }

    @Test
    void test_setpricescore_valid() {
       Restaurant sut = new Restaurant("Judy Fu's Snappy Dragon", 1);
       assert(sut.getPriceScore() == 1);
       sut.setPriceScore(3);
       assert(sut.getPriceScore() == 3);
    }

    @Test
    void test_setpricescore_invalid() {
        Restaurant sut = new Restaurant("Judy Fu's Snappy Dragon", 1);
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(4));
    }

    @Test
    void test_addreview() {
        User user = new User("Reviewer", "Reviewsalot");
        Restaurant restaurant = new Restaurant("Dick's",1);
        RestaurantReview sut = new RestaurantReview(restaurant,user, 3, "Hamburgers!");
        restaurant.addReview(sut);
        assert(restaurant.getReviewCount() == 1);
    }

    @Test
    void test_addreview_multiple_same_review() {
        User user = new User("Reviewer", "Reviewsalot");
        Restaurant restaurant = new Restaurant("Dick's",1);
        RestaurantReview sut = new RestaurantReview(restaurant,user, 3, "Hamburgers!");
        restaurant.addReview(sut);
        assert(restaurant.getReviewCount() == 1);
        restaurant.addReview(sut);
        assert(restaurant.getReviewCount() == 1);
    }

    @Test
    void test_getaveragereviewstarscore() {
        Restaurant restaurant = new Restaurant("Dick's",1);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Hamburger", "Helper");
        User userC = new User("The", "Hamburgler");
        RestaurantReview sutA = new RestaurantReview(restaurant,userA, 3, "Hamburgers!");
        RestaurantReview sutB = new RestaurantReview(restaurant,userB,1, "Meh.");
        RestaurantReview sutC = new RestaurantReview(restaurant,userC, 3, "Yum!");
        restaurant.addReview(sutA);
        assert(restaurant.getStarScore() == 3);
        restaurant.addReview(sutB);
        assert(restaurant.getStarScore() == 2);
        restaurant.addReview(sutC);
        assert(restaurant.getStarScore() == 2);
    }

    @Test
    void test_updatereviewstars_single_review() {
        Restaurant restaurant = new Restaurant("Dick's",1);
        User user = new User("Reviewer", "Reviewsalot");
        RestaurantReview sut = new RestaurantReview(restaurant,user, 3, "Hamburgers!");
        restaurant.addReview(sut);
        restaurant.updateReviewStars(sut.getAuthor(), 1);
        assert(restaurant.getStarScore() == 1);
    }

    @Test
    void test_updatereviewstars_multiple() {
        Restaurant restaurant = new Restaurant("Dick's",1);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Hamburger", "Helper");
        User userC = new User("The", "Hamburgler");
        RestaurantReview sutA = new RestaurantReview(restaurant,userA, 1, "Hamburgers!");
        RestaurantReview sutB = new RestaurantReview(restaurant,userB, 3, "Meh.");
        RestaurantReview sutC = new RestaurantReview(restaurant,userC, 3, "Yum!");
        restaurant.addReview(sutA);
        restaurant.addReview(sutB);
        restaurant.addReview(sutB);
        System.out.println(restaurant);
        restaurant.updateReviewStars(sutB.getAuthor(), 1);
        System.out.println(restaurant);
        assert(restaurant.getStarScore() == 1);
        restaurant.updateReviewStars(sutB.getAuthor(), 3);
        restaurant.updateReviewStars(sutA.getAuthor(), 3);
        assert(restaurant.getStarScore() == 3);
    }

    @Test
    void test_getstarscore_empty() {
        Restaurant sut = new Restaurant("Dick's",1);
        assert(sut.getStarScore() == 0);
    }

    @Test
    void test_getstarscore_multiple() {
        Restaurant restaurant = new Restaurant("Dick's",1);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Hamburger", "Helper");
        User userC = new User("The", "Hamburgler");
        RestaurantReview sutA = new RestaurantReview(restaurant,userA, 3, "Hamburgers!");
        restaurant.addReview(sutA);
        RestaurantReview sutB = new RestaurantReview(restaurant,userB, 1, "Meh.");
        restaurant.addReview(sutB);
        RestaurantReview sutC = new RestaurantReview(restaurant,userC, 3, "Yum!");
        restaurant.addReview(sutC);
        assert(restaurant.getStarScore() == 2);
        sutB.updateStars(3);
        assert(restaurant.getStarScore() == 3);
    }
}
