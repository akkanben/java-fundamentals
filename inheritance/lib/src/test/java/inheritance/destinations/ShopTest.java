package inheritance.destinations;

import org.junit.jupiter.api.Test;

public class ShopTest {

    @Test
    void test_shop_constructor() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        assert(sut.name.equals("The Fish Store"));
        assert(sut.description.equals("Seattle's Favorite Fish Shop"));
        assert(sut.priceScore == 2);
    }

    @Test
    void test_shop_tostring() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        System.out.println(sut);
        assert(sut.toString().equals("{ name: The Fish Store, description: Seattle's Favorite Fish Shop, price: 2 }"));
    }
}
