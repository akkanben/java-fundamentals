package bitmapTransformer.bitmap;

public enum Transform {
    COPY("copy"),
    GRAYSCALE("grayscale"),
    DARKEN("darken"),
    LIGHTEN("lighten"),
    RED_MULTIPLY("r-multiply"),
    GREEN_MULTIPLY("g-multiply"),
    BLUE_MULTIPLY("b-multiply"),
    PIXELATE("pixelate"),
    INVERT("invert"),
    RANDOMIZE("randomize"),
    MIRROR_HORIZONTAL("mirror-h"),
    MIRROR_VERTICAL("mirror-v"),
    BORDER("border"),
    ROTATE_RIGHT("r-rotate"),
    ROTATE_LEFT("l-rotate");

    public final String transformName;

    Transform(String transformName) {
        this.transformName = transformName;
    }

    @Override
    public String toString() {
        return transformName;
    }

}
