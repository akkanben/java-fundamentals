package inheritance.destinations;

import java.util.ArrayList;

public class Theater extends Destination {
    private ArrayList<String> nowShowingList;

    public Theater(String name, int priceScore) {
        super(name, priceScore);
        nowShowingList = new ArrayList<>();
    }

    public void addMovie(String movieTitle) {
        if(!nowShowingList.contains(movieTitle))
            nowShowingList.add(movieTitle);
    }

    public void removeMovie(String movieTitle) {
        if(!nowShowingList.contains(movieTitle))
            throw new IllegalArgumentException();
        nowShowingList.remove(movieTitle);
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", price: " + priceScore + ", rating: "
                + getStarScore() + ", now_showing: " + nowShowingList + " }";
    }

}
