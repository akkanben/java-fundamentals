package inheritance.review;

import org.junit.jupiter.api.Test;

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


}
