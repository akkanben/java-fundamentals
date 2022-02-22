package inheritance;

public class Restaurant {
    private final String name;
    private int starScore;
    private int priceScore;

    public Restaurant(String name, int starScore, int priceScore) {
        this.name = name;
        this.starScore = starScore;
        this.priceScore = priceScore;
    }

    public String getName() {
        return name;
    }

    public int getStarScore() {
        return starScore;
    }

    public int getPriceScore() {
        return priceScore;
    }

    public void setStarScore(int starScore) {
       if(starScore < 0 || starScore > 5)
           throw new IllegalArgumentException("Star score " + starScore + " out of range.");
       this.starScore = starScore;
    }

    public void setPriceScore(int priceScore) {
        if(priceScore < 0 || priceScore > 3)
            throw new IllegalArgumentException("Star score " + starScore + " out of range.");
        this.priceScore = priceScore;
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", stars: " + starScore + ", price: " + priceScore + " }";
    }




}
