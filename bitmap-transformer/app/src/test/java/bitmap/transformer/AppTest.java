/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitmap.transformer;

import bitmap.transformer.bitmap.Bitmap;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void testForFile(){
        App sut = new App();
    }

    @Test
    void testForGreyScale(){
        App sut = new App();

        String userPath = System.getProperty("user.dir");
        System.out.println(userPath);
        String resourcesPath;
        String testGrayFileName = "test-gray.bmp";
        String fileName = "red_100x100.bmp";
        String outputFileName = "grey-output.bmp";

        if (userPath.endsWith("bitmap-transformer")) {
            resourcesPath = "app/src/test/resources/";
        } else {
            resourcesPath = "src/test/resources/";
        }

        File testImage = new File(resourcesPath + fileName);
        File testGrayFile = new File(resourcesPath + testGrayFileName);
        BufferedImage img = null;
        BufferedImage testGray = null;

        try{
            img = ImageIO.read(testImage);
            testGray = ImageIO.read(testGrayFile);
            Bitmap inputBitmap = new Bitmap(img);
            inputBitmap.grayScale();
            inputBitmap.writeOutImage(resourcesPath + outputFileName);
            Color outputColor = new Color(inputBitmap.getBufferedImage().getRGB(0, 0));
            Color testGrayColor = new Color(testGray.getRGB(0, 0));
            assert(outputColor.getRed() == testGrayColor.getRed());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }


}