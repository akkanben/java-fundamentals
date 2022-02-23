package inheritance.destination;

import inheritance.User;
import inheritance.review.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTest {

    void test_theater_constructor() {
        Theater sut = new Theater("Neptune Theatre", 2);
        assert(sut.getName().equals("Neptune Theatre"));
        assert(sut.getPriceScore() == 2);
    }

    @Test
    void test_theater_shop_tostring() {
        Theater sut = new Theater("Neptune Theatre", 3);
        sut.addMovie("Face Off");
        sut.addMovie("Vampire's Kiss");
        sut.addMovie("The Wicker Man");
        assert(sut.toString().equals("{ name: Neptune Theatre, " +
                "price: 3, rating: 0, now_showing: [Face Off, Vampire's Kiss, The Wicker Man] }"));
    }

    @Test
    void test_theater_setpricescore_valid() {
        Theater sut = new Theater("Neptune Theatre", 3);
        assert(sut.getPriceScore() == 3);
        sut.setPriceScore(1);
        assert(sut.getPriceScore() == 1);
    }

    @Test
    void test_theater_setpricescore_invalid() {
        Theater sut = new Theater("Neptune Theatre", 3);
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(4));
    }

    @Test
    void test_theater_addreview() {
        User user = new User("Reviewer", "Reviewsalot");
        Theater sut = new Theater("Neptune Theatre", 3);
        Review review = new Review(user, 3, "Pretty great.");
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
    }

    @Test
    void test_theater_addreview_multiple_same_review() {
        User user = new User("Reviewer", "Reviewsalot");
        Theater sut = new Theater("Neptune Theatre", 3);
        Review review = new Review(user, 3, "Wowwwwww!");
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
        sut.addReview(review);
        assert(sut.getReviewCount() == 1);
    }

    @Test
    void test_theater_getaveragereviewstarscore() {
        Theater sut = new Theater("Neptune Theatre", 3);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Movie", "Goer");
        User userC = new User("Mr.", "Critical");
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
    void test_theater_updatereviewstars_single_review() {
        Theater sut = new Theater("Neptune Theatre", 3);
        User user = new User("Reviewer", "Reviewsalot");
        Review review = new Review(user, 3, "Stuff!");
        sut.addReview(review);
        sut.updateReviewStars(review.getAuthor(), 1);
        assert(sut.getStarScore() == 1);
    }

    @Test
    void test_theater_updatereviewstars_multiple() {
        Theater sut = new Theater("Neptune Theatre", 3);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Movie", "Goer");
        User userC = new User("Mr.", "Critical");
        Review reviewA = new Review(userA, 3, "Cool!");
        Review reviewB = new Review(userB,1, "Boring.");
        Review reviewC = new Review(userC, 3, "So cool!");
        sut.addReview(reviewA);
        sut.addReview(reviewB);
        sut.addReview(reviewC);
        sut.updateReviewStars(reviewB.getAuthor(), 1);
        assert(sut.getStarScore() == 2);
        sut.updateReviewStars(reviewB.getAuthor(), 3);
        sut.updateReviewStars(reviewA.getAuthor(), 3);
        assert(sut.getStarScore() == 3);
    }

    @Test
    void test_theater_getstarscore_empty() {
        Theater sut = new Theater("Neptune Theatre", 3);
        assert(sut.getStarScore() == 0);
    }

    @Test
    void test_theater_getstarscore_multiple() {
        Theater sut = new Theater("Neptune Theatre", 3);
        User userA = new User("Reviewer", "Reviewsalot");
        User userB = new User("Movie", "Goer");
        User userC = new User("Mr.", "Critical");
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
