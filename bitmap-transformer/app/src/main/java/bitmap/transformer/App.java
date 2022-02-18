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
                case "gray-scale" -> {
                    System.out.println("Transforming" + inputFileName + " to " + outputFileName + " in grayscale.");
                    bitmapToTransform.grayScaleTransform();
                }
                case "invert" -> {
                    System.out.println("Transforming" + inputFileName + " to " + outputFileName + " by inverting the color.");
                    bitmapToTransform.invertTransform();
                }
                case "randomize" -> {
                    System.out.println("Transforming" + inputFileName + " to " + outputFileName + " by randomizing each pixel color.");
                    bitmapToTransform.randomizeTransform();
                }
            }
            bitmapToTransform.writeOutImage(resourcesPath + outputFileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
}