package inheritance.review;

import inheritance.User;

public class TheaterReview extends Review{

    String movieViewed = "";

    public TheaterReview(User author, int starScore, String body, String movieViewed) {
        super(author, starScore, body);
        this.movieViewed = movieViewed;
    }

    public TheaterReview(User author, int starScore, String body) {
        super(author, starScore, body);
    }

    @Override
    public String toString() {
        if (movieViewed.equals(""))
            return "{ author: " + author.toString() + ", score: " + starScore + ", review: " + body + " }";
        else
            return "{ author: " + author.toString() + ", score: " + starScore + ", review: " + body
                    + ", movie_viewed: " + movieViewed + " }";
    }
}
