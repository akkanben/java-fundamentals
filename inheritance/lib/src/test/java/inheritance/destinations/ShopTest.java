package inheritance.destinations;

import inheritance.User;
import inheritance.review.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShopTest {

    @Test
    void test_constructor() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        assert(sut.getName().equals("The Fish Store"));
        assert(sut.getDescription().equals("Seattle's Favorite Fish Shop"));
        assert(sut.getPriceScore() == 2);
    }

    @Test
    void test_shop_tostring() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        System.out.println(sut);
        assert(sut.toString().equals("{ name: The Fish Store, description: Seattle's Favorite Fish Shop, price: 2 }"));
    }

    @Test
    void test_setpricescore_valid() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        assert(sut.getPriceScore() == 2);
        sut.setPriceScore(3);
        assert(sut.getPriceScore() == 3);
    }

    @Test
    void test_setpricescore_invalid() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(4));
    }

    @Test
    void test_addreview() {
        User user = new User("Reviewer", "Reviewsalot");
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        Review review = new Review(user, 3, "Cute Fish!");
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
    }

    @Test
    void test_addreview_multiple_same_review() {
        User user = new User("Reviewer", "Reviewsalot");
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        Review review = new Review(user, 3, "Wowwwwww!");
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
    }

    @Test
    void test_getaveragereviewstarscore() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Fishy", "McFishface");
        User userC = new User("It's", "Aquaman");
        Review reviewA = new Review(userA, 3, "Fish!");
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
    void test_updatereviewstars_single_review() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        User user = new User("Reviewer", "Reviewsalot");
        Review review = new Review(user, 3, "Fish!");
        sut.addReview(review);
        sut.updateReviewStars(review.getAuthor(), 1);
        assert(sut.getStarScore() == 1);
    }

    @Test
    void test_updatereviewstars_multiple() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Fishy", "McFishface");
        User userC = new User("It's", "Aquaman");
        Review reviewA = new Review(userA, 1, "Fish!");
        Review reviewB = new Review(userB, 3, "Meh.");
        Review reviewC = new Review(userC, 3, "Yum!");
        sut.addReview(reviewA);
        sut.addReview(reviewB);
        sut.addReview(reviewC);
        sut.updateReviewStars(reviewB.getAuthor(), 1);
        assert(sut.getStarScore() == 1);
        sut.updateReviewStars(reviewB.getAuthor(), 3);
        sut.updateReviewStars(reviewA.getAuthor(), 3);
        assert(sut.getStarScore() == 3);
    }

    @Test
    void test_getstarscore_empty() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        assert(sut.getStarScore() == 0);
    }

    @Test
    void test_getstarscore_multiple() {
        Shop sut = new Shop("The Fish Store", "Seattle's Favorite Fish Shop", 2);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Fishy", "McFishface");
        User userC = new User("It's", "Aquaman");
        Review review = new Review(userA, 3, "Fish!");
        sut.addReview(review);
        Review sutB = new Review(userB, 1, "Meh.");
        sut.addReview(sutB);
        Review sutC = new Review(userC, 3, "Yum!");
        sut.addReview(sutC);
        assert(sut.getStarScore() == 2);
        sutB.updateStars(3);
        assert(sut.getStarScore() == 3);
    }
}
