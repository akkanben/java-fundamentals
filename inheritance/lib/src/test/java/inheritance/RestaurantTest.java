package inheritance;

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
        Restaurant sut = new Restaurant("Dick\'s", 4, 1);
        assert(sut.toString().equals("{ name: Dick's, stars: 4, price: 1 }"));
    }

    @Test
    void test_review_constructor() {
       Review sut = new Review("Person McPersonface", 2, "This is my opinion.");
       assert(sut.toString().equals("{ author: Person McPersonface, score: 2, review: This is my opinion. }"));
    }
}
