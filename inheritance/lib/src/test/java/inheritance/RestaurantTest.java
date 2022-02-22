package inheritance;

import inheritance.review.Review;
import org.junit.jupiter.api.Test;

public class RestaurantTest {

    @Test
    void test_restaurant_constructor() {
        Restaurant sut = new Restaurant("Toyoda Sushi", 5, 2);
        assert(sut.getName().equals("Toyoda Sushi"));
        assert(sut.getStarScore() == 5);
        assert(sut.getPriceScore() == 2);
    }

    @Test
    void test_restaurant_tostring() {
        Restaurant sut = new Restaurant("Dick's", 4, 1);
        assert(sut.toString().equals("{ name: Dick's, stars: 4, price: 1 }"));
    }


}
