package inheritance.review;

import inheritance.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterReviewTest {

    @Test
    void test_theaterreview_constructor() {
        User user = new User("Person", "McPersonface");
        TheaterReview sut = new TheaterReview(user, 2, "This is my opinion.", "Nausicca");
        assert(sut.author.toString().equals("Person McPersonface"));
        assert(sut.getStarScore() == 2);
        assert(sut.body.equals("This is my opinion."));
        assert(sut.movieViewed.equals("Nausicca"));
    }

    @Test
    void test_theaterreview_constructor_no_movie() {
        User user = new User("Person", "McPersonface");
        TheaterReview sut = new TheaterReview(user, 2, "This is my opinion.");
        assert(sut.author.toString().equals("Person McPersonface"));
        assert(sut.getStarScore() == 2);
        assert(sut.body.equals("This is my opinion."));
        assert(sut.movieViewed.equals(""));
    }

    @Test
    void test_theaterreview_toString() {
        User user = new User("Person", "McPersonface");
        TheaterReview sut = new TheaterReview(user, 2, "This is my opinion.", "Nausicca");
        assert(sut.toString().equals("{ author: Person McPersonface, score: 2, " +
                "review: This is my opinion., movie_viewed: Nausicca }"));
    }

    @Test
    void test_theaterreview_toString_no_movie() {
        User user = new User("Person", "McPersonface");
        TheaterReview sut = new TheaterReview(user, 2, "This is my opinion.");
        assert(sut.toString().equals("{ author: Person McPersonface, score: 2, review: This is my opinion. }"));
    }

    @Test
    void test_theaterreview_updatestarscore_valid() {
        User user = new User("King", "Kong");
        TheaterReview sut = new TheaterReview(user, 4, "Rwwraaar");
        assert(sut.getStarScore() == 4);
        sut.updateStars(2);
        assert(sut.getStarScore() == 2);
    }

    @Test
    void test_theaterreview_updatestarscore_valid_with_movie() {
        User user = new User("King", "Kong");
        TheaterReview sut = new TheaterReview(user, 4, "Rwwraaar", "King Kong 2005");
        assert(sut.getStarScore() == 4);
        sut.updateStars(2);
        assert(sut.getStarScore() == 2);
    }

    @Test
    void test_theaterreview_updatestarscore_invalid() {
        User user = new User("King", "Kong");
        TheaterReview sut = new TheaterReview(user, 4, "Rwwraaar");
        assertThrows(IllegalArgumentException.class, () -> sut.updateStars(-1));
        assertThrows(IllegalArgumentException.class, () -> sut.updateStars(6));
    }
}
