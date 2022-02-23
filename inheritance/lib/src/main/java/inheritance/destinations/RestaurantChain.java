package inheritance.destinations;

import java.util.ArrayList;

public class RestaurantChain {
    String restaurantChainName;
    int locationCount;
    ArrayList<Restaurant> RestaurantList;

    public RestaurantChain(String restaurantChainName) {
        this.restaurantChainName = restaurantChainName;
        RestaurantList = new ArrayList<>();
    }

    public void addLocation(Restaurant restaurant) {
        if(!RestaurantList.contains(restaurant)) {
            RestaurantList.add(restaurant);
            locationCount++;
        }
    }
}
