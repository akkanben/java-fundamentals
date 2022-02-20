package bitmap.transformer;

import bitmap.transformer.bitmap.Bitmap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        String userPath = System.getProperty("user.dir");
        String resourcesPath = "";
        String inputFileName = "";
        String outputFileName = "";
        String transformationType = "";
        try {
            inputFileName = args[0];
            outputFileName = args[1];
            transformationType = args[2];
        } catch (IndexOutOfBoundsException oob) {
            oob.printStackTrace();
            System.out.println("This application requires 3 arguments.");
            System.exit(1);
        }
        if (userPath.endsWith("bitmap-transformer")) {
            resourcesPath = "app/src/main/resources/";
        } else {
            resourcesPath = "src/main/resources/";
        }
        File inputImageFile = new File(resourcesPath + inputFileName);
        BufferedImage inputBufferedImage = null;
        try {
            inputBufferedImage = ImageIO.read(inputImageFile);
            Bitmap bitmapToTransform = new Bitmap(inputBufferedImage);
            switch (transformationType) {
                case "copy" -> {
                    System.out.println("Copying " + inputFileName + " to " + outputFileName);
                    bitmapToTransform.copyImage();
                }
                case "grayscale" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " in grayscale.");
                    bitmapToTransform.grayScaleTransform();
                }
                case "darken" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by darkening the image.");
                    bitmapToTransform.darkenTransform();
                }
                case "lighten" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by darkening the image.");
                    bitmapToTransform.lightenTransform();
                }
                case "r-multiply" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by multiplying the image hue with red.");
                    bitmapToTransform.hueMultiplyTransform(0);
                }
                case "g-multiply" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by multiplying the image hue with red.");
                    bitmapToTransform.hueMultiplyTransform(1);
                }
                case "b-multiply" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by multiplying the image hue with red.");
                    bitmapToTransform.hueMultiplyTransform(2);
                }
                case "invert" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by inverting the color.");
                    bitmapToTransform.invertTransform();
                }
                case "randomize" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by randomizing each pixel color.");
                    bitmapToTransform.randomizeTransform();
                }
                case "mirror-h" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by mirroring horizontally.");
                    bitmapToTransform.horizontalMirrorTransform();
                }
                case "mirror-v" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by mirroring vertically.");
                    bitmapToTransform.verticalMirrorTransform();
                }
            }
            System.out.println("Transform complete, " + outputFileName + " available in resources directory.");
            bitmapToTransform.writeOutImage(resourcesPath + outputFileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
}