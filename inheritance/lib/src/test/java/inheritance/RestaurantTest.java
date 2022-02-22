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

}
