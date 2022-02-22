package inheritance;

import inheritance.review.RestaurantReview;
import inheritance.review.Review;
import org.junit.jupiter.api.Test;

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
    void test_addreview() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        sutA.addReview(sutB);
        assert(sutA.getReviewCount() == 1);
    }

    @Test
    void test_addreview_multiple_same_review() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        sutA.addReview(sutB);
        assert(sutA.getReviewCount() == 1);
        sutA.addReview(sutB);
        assert(sutA.getReviewCount() == 1);
    }

    @Test
    void test_getaveragereviewstarscore() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        RestaurantReview sutC = new RestaurantReview(sutA,"Hamburger Helper", 1, "Meh.");
        RestaurantReview sutD = new RestaurantReview(sutA,"The Hamburgler", 3, "Yum!");
        sutA.addReview(sutB);
        assert(sutA.getStarScore() == 3);
        sutA.addReview(sutC);
        assert(sutA.getStarScore() == 2);
        sutA.addReview(sutD);
        assert(sutA.getStarScore() == 2);

    }

}
