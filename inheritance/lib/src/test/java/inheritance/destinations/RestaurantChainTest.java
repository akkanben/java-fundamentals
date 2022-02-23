package inheritance.destinations;

import org.junit.jupiter.api.Test;

public class RestaurantChainTest {
    @Test
    void test_restaurantchain_constructor() {
        RestaurantChain sut = new RestaurantChain("Dick's");
        assert(sut.restaurantChainName).equals("Dick's");
    }

    @Test
    void test_addLocation() {
        RestaurantChain sut = new RestaurantChain("Dick's");
        Restaurant restaurant = new Restaurant("Dick's Shoreline", 1);
        sut.addLocation(restaurant);
        assert(sut.locationCount == 1);
        sut.addLocation(restaurant);
        assert(sut.locationCount == 1);
    }
}
