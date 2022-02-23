package inheritance.review;

import inheritance.User;

public interface Reviewable {

    void addReview(Review review);

    void updateReviewStars(User author, int newStars);




}
