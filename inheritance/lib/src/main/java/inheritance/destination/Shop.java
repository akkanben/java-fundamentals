package inheritance.destination;

import inheritance.User;
import inheritance.review.Review;

public class Shop extends Destination {
    private final String description;

    public Shop(String name, String description, int priceScore) {
        super(name, priceScore);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", description: " + description + ", price: " + priceScore + ", rating: " + getStarScore() + " }";
    }

    @Override
    public void addReview(Review review) {
        if(!reviews.contains(review)) {
            reviews.add(review);
            reviewCount++;
        }
    }

    @Override
    public void updateReviewStars(User author, int newStars) {
        for(Review element : reviews) {
            if(element.getAuthor().equals(author))
                element.setStarScore(newStars);
        }
    }
}
