package inheritance.destinations;

public class Shop {
    String name;
    String description;
    int priceScore;

    public Shop(String name, String description, int priceScore) {
        this.name = name;
        this.description = description;
        this.priceScore = priceScore;
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", description: " + description + ", price: " + priceScore + " }";
    }
}
