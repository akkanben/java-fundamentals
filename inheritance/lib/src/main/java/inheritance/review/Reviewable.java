package inheritance.review;

import inheritance.User;

public interface Reviewable {

    public void addReview(Review review);

    public void updateReviewStars(User author, int newStars);




}
