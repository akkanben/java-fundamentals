package inheritance.review;

import inheritance.restaurant.Restaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewTest {

    @Test
    void test_review_constructor() {
        Review sut = new Review("Person McPersonface", 2, "This is my opinion.");
        assert(sut.author.equals("Person McPersonface"));
        assert(sut.getStarScore() == 2);
        assert(sut.body.equals("This is my opinion."));
    }

    @Test
    void test_review_toString() {
        Review sut = new Review("Person McPersonface", 2, "This is my opinion.");
        assert(sut.toString().equals("{ author: Person McPersonface, score: 2, review: This is my opinion. }"));
    }

    @Test
    void test_updatestarscore_valid() {
        Review sut = new Review("King Kong", 4, "Rwwraaar");
        assert(sut.getStarScore() == 4);
        sut.updateStars(2);
        assert(sut.getStarScore() == 2);
    }

    @Test
    void test_updatestarscore_invalid() {
        Review sut = new Review("King Kong", 4, "Rwwraaar");
        assertThrows(IllegalArgumentException.class, () -> sut.updateStars(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.updateStars(6));
    }

}
