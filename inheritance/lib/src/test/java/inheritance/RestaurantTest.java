package inheritance;

import org.junit.jupiter.api.Test;

public class RestaurantTest {

    @Test
    void test_restaurant_constructor() {
        Restaurant sut = new Restaurant("Toyoda Sushi", 5);
        assert(sut.name.equals("Toyoda Sushi"));
        assert(sut.starScore == 5);
    }
}
