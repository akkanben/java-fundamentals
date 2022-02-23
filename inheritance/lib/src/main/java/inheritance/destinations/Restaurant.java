package inheritance.destinations;

import java.util.ArrayList;

public class Restaurant extends Destination {

    public Restaurant(String name, int priceScore) {
        super(name, priceScore);
        this.name = name;
        reviews = new ArrayList<>();
    }

}
