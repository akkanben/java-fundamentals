package inheritance.restaurant;

import inheritance.review.RestaurantReview;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantTest {

    @Test
    void test_restaurant_constructor() {
        Restaurant sut = new Restaurant("Toyoda Sushi",2);
        assert(sut.getName().equals("Toyoda Sushi"));
        assert(sut.getPriceScore() == 2);
    }

    @Test
    void test_restaurant_tostring() {
        Restaurant sut = new Restaurant("Dick's",1);
        assert(sut.toString().equals("{ name: Dick's, stars: 0, price: 1 }"));
    }

    @Test
    void test_setpricescore_valid() {
       Restaurant sut = new Restaurant("Judy Fu's Snappy Dragon", 1);
       assert(sut.getPriceScore() == 1);
       sut.setPriceScore(3);
       assert(sut.getPriceScore() == 3);
    }

    @Test
    void test_setpricescore_invalid() {
        Restaurant sut = new Restaurant("Judy Fu's Snappy Dragon", 1);
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.setPriceScore(4));
    }

    @Test
    void test_addreview() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        sutA.addReview(sutB);
        assert(sutA.getReviewCount() == 1);
    }

    @Test
    void test_addreview_multiple_same_review() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        sutA.addReview(sutB);
        assert(sutA.getReviewCount() == 1);
        sutA.addReview(sutB);
        assert(sutA.getReviewCount() == 1);
    }

    @Test
    void test_getaveragereviewstarscore() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        RestaurantReview sutC = new RestaurantReview(sutA,"Hamburger Helper", 1, "Meh.");
        RestaurantReview sutD = new RestaurantReview(sutA,"The Hamburgler", 3, "Yum!");
        sutA.addReview(sutB);
        assert(sutA.getStarScore() == 3);
        sutA.addReview(sutC);
        assert(sutA.getStarScore() == 2);
        sutA.addReview(sutD);
        assert(sutA.getStarScore() == 2);
    }

    @Test
    void test_updatereviewstars_single_review() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        sutA.addReview(sutB);
        sutA.updateReviewStars(sutB.getAuthor(), 1);
        assert(sutA.getStarScore() == 1);
    }

    @Test
    void test_updatereviewstars_multiple() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 1, "Hamburgers!");
        RestaurantReview sutC = new RestaurantReview(sutA,"Hamburger Helper", 3, "Meh.");
        RestaurantReview sutD = new RestaurantReview(sutA,"The Hamburgler", 3, "Yum!");
        sutA.addReview(sutB);
        sutA.addReview(sutC);
        sutA.addReview(sutC);
        System.out.println(sutA);
        sutA.updateReviewStars(sutC.getAuthor(), 1);
        System.out.println(sutA);
        assert(sutA.getStarScore() == 1);
        sutA.updateReviewStars(sutC.getAuthor(), 3);
        sutA.updateReviewStars(sutB.getAuthor(), 3);
        assert(sutA.getStarScore() == 3);
    }

    @Test
    void test_getstarscore_empty() {
        Restaurant sut = new Restaurant("Dick's",1);
        assert(sut.getStarScore() == 0);
    }

    @Test
    void test_getstarscore_multiple() {
        Restaurant sutA = new Restaurant("Dick's",1);
        RestaurantReview sutB = new RestaurantReview(sutA,"Reviewer Reviewsalot", 3, "Hamburgers!");
        sutA.addReview(sutB);
        RestaurantReview sutC = new RestaurantReview(sutA,"Hamburger Helper", 1, "Meh.");
        sutA.addReview(sutC);
        RestaurantReview sutD = new RestaurantReview(sutA,"The Hamburgler", 3, "Yum!");
        sutA.addReview(sutD);
        assert(sutA.getStarScore() == 2);
        sutC.updateStars(3);
        assert(sutA.getStarScore() == 3);

    }


}
