package bitmapTransformer.bitmap;

public class RGB {
    int red;
    int blue;
    int green;

    @Override
    public String toString() {
        return "" + Integer.toHexString(red) + Integer.toHexString(blue) + Integer.toHexString(green);
    }

    public RGB(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
}
