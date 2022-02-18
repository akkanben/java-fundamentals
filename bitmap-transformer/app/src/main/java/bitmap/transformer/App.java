package bitmap.transformer;

import bitmap.transformer.bitmap.Bitmap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class App {
    public String getGreeting() {
        return "Hello World!";
    }


    public static void main(String[] args) {
        String userPath = System.getProperty("user.dir");
        String resourcesPath = "";
        String fileName = "";
        String outputFileName = "";
        String transformationType = "";

        try {
            fileName = args[0];
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

        File testImage = new File(resourcesPath + fileName);

        BufferedImage img = null;

        try {

            img = ImageIO.read(testImage);
            Bitmap inputImage = new Bitmap(img);
            if (transformationType.equals("copy")) {
                inputImage.copyImage();
            }
            if (transformationType.equals("gray-scale")){
                inputImage.grayScale();
            }
            if (transformationType.equals("invert")){
                inputImage.invert();
            }
            if (transformationType.equals("randomize")){
                inputImage.randomize();
            }
            inputImage.writeOutImage(resourcesPath + outputFileName);


        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }



    }
}