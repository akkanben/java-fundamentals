package inheritance.restaurant;

import org.junit.jupiter.api.Test;

public class RestaurantChainTest {
    @Test
    void test_restaurantchain_constructor() {
        RestaurantChain sut = new RestaurantChain("Dick's");
        assert(sut.restaurantChainName).equals("Dick's");
    }

    @Test
    void test_addLocation() {
        RestaurantChain sutA = new RestaurantChain("Dick's");
        Restaurant sutB = new Restaurant("Dick's Shoreline", 1);
        sutA.addLocation(sutB);
        assert(sutA.locationCount == 1);
        sutA.addLocation(sutB);
        assert(sutA.locationCount == 1);
    }
}
