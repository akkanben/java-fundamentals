package inheritance.destination;

import inheritance.User;
import inheritance.review.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DestinationTest {

    @Test
    void test_destination_constructor() {
        Destination sut = new Destination("Cool Place", 3);
        assert(sut.getName().equals("Cool Place"));
        assert(sut.getPriceScore() == 3);
    }

    @Test
    void test_destination_shop_tostring() {
        Destination sut = new Destination("Cool Place", 1);
        assert(sut.toString().equals("{ name: Cool Place, price: 1, rating: 0 }"));
    }

    @Test
    void test_destination_setpricescore_valid() {
        Destination sut = new Destination("Cool Place", 3);
        assert(sut.getPriceScore() == 3);
        sut.setPriceScore(1);
        assert(sut.getPriceScore() == 1);
    }

    @Test
    void test_destination_setpricescore_invalid() {
        Destination sut = new Destination("Cool Place", 3);
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(4));
    }

    @Test
    void test_destination_addreview() {
        User user = new User("Reviewer", "Reviewsalot");
        Destination sut = new Destination("Cool Place", 3);
        Review review = new Review(user, 3, "Pretty great.");
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
    }

    @Test
    void test_destination_addreview_multiple_same_review() {
        User user = new User("Reviewer", "Reviewsalot");
        Destination sut = new Destination("Cool Place", 3);
        Review review = new Review(user, 3, "Wowwwwww!");
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
    }

    @Test
    void test_destination_getaveragereviewstarscore() {
        Destination sut = new Destination("Cool Place", 3);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Generic", "Shopper");
        User userC = new User("Cheapy", "Cheaperson");
        Review reviewA = new Review(userA, 3, "Cool!");
        Review reviewB = new Review(userB,1, "Boring.");
        Review reviewC = new Review(userC, 3, "So cool!");
        sut.addReview(reviewA);
        assert(sut.getStarScore() == 3);
        sut.addReview(reviewB);
        assert(sut.getStarScore() == 2);
        sut.addReview(reviewC);
        assert(sut.getStarScore() == 2);
    }

    @Test
    void test_destination_updatereviewstars_single_review() {
        Destination sut = new Destination("Cool Place", 3);
        User user = new User("Reviewer", "Reviewsalot");
        Review review = new Review(user, 3, "Stuff!");
        sut.addReview(review);
        sut.updateReviewStars(review.getAuthor(), 1);
        assert(sut.getStarScore() == 1);
    }

    @Test
    void test_destination_updatereviewstars_multiple() {
        Destination sut = new Destination("Cool Place", 3);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Generic", "Shopper");
        User userC = new User("Cheapy", "Cheaperson");
        Review reviewA = new Review(userA, 3, "Cool!");
        Review reviewB = new Review(userB,1, "Boring.");
        Review reviewC = new Review(userC, 3, "So cool!");
        sut.addReview(reviewA);
        sut.addReview(reviewB);
        sut.addReview(reviewC);
        assert(sut.getStarScore() == 2);
        sut.updateReviewStars(reviewA.getAuthor(), 1);
        assert(sut.getStarScore() == 1);
        sut.updateReviewStars(reviewB.getAuthor(), 3);
        sut.updateReviewStars(reviewA.getAuthor(), 3);
        assert(sut.getStarScore() == 3);
    }

    @Test
    void test_destination_getstarscore_empty() {
        Destination sut = new Destination("Cool Place", 3);
        assert(sut.getStarScore() == 0);
    }

    @Test
    void test_destination_getstarscore_multiple() {
        Destination sut = new Destination("Cool Place", 3);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Generic", "Shopper");
        User userC = new User("Cheapy", "Cheaperson");
        Review reviewA = new Review(userA, 3, "Cool!");
        sut.addReview(reviewA);
        Review reviewB = new Review(userB,1, "Boring.");
        sut.addReview(reviewB);
        Review reviewC = new Review(userC, 3, "So cool!");
        sut.addReview(reviewC);
        assert(sut.getStarScore() == 2);
        reviewB.updateStars(3);
        assert(sut.getStarScore() == 3);
    }
}
