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
                    System.out.println("Complete. " + outputFileName + " available in resources directory.");
                }
                case "grayscale" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " in grayscale.");
                    bitmapToTransform.grayScaleTransform();
                    System.out.println("Complete. " + outputFileName + " available in resources directory.");
                }
                case "invert" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by inverting the color.");
                    bitmapToTransform.invertTransform();
                    System.out.println("Complete. " + outputFileName + " available in resources directory.");
                }
                case "randomize" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by randomizing each pixel color.");
                    bitmapToTransform.randomizeTransform();
                    System.out.println("Complete. " + outputFileName + " available in resources directory.");
                }
                case "mirror-h" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by mirroring horizontally.");
                    bitmapToTransform.horizontalMirrorTransform();
                    System.out.println("Complete. " + outputFileName + " available in resources directory.");
                }
                case "mirror-v" -> {
                    System.out.println("Transforming " + inputFileName + " to " + outputFileName + " by mirroring vertically.");
                    bitmapToTransform.verticalMirrorTransform();
                    System.out.println("Complete. " + outputFileName + " available in resources directory.");
                }
            }
            bitmapToTransform.writeOutImage(resourcesPath + outputFileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
}