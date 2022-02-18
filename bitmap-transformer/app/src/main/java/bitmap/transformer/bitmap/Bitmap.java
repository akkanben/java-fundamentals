package bitmap.transformer.bitmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Bitmap {

    BufferedImage inputBufferedImage;
    BufferedImage outputBufferedImage;
    int width;
    int height;
    int type;

    public Bitmap(BufferedImage inputBufferedImage) {
        width = inputBufferedImage.getWidth();
        height = inputBufferedImage.getHeight();
        type = BufferedImage.TYPE_INT_RGB;
        this.outputBufferedImage = new BufferedImage(width,height,type);
        this.inputBufferedImage = inputBufferedImage;
    }

    public BufferedImage getOutputBufferedImage() {
        return outputBufferedImage;
    }

    public void grayScaleTransform(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputBufferedImage.getRGB(i, j));
                int r = currentColor.getRed();
                int g = currentColor.getGreen();
                int b = currentColor.getBlue();
                int gs = (r + g + b) / 3;
                Color newColor = new Color(gs, gs, gs);
                outputBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void invertTransform(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputBufferedImage.getRGB(i, j));
                int r = 255 - currentColor.getRed();
                int g = 255 - currentColor.getGreen();
                int b = 255 - currentColor.getBlue();
                Color newColor = new Color(r, g, b);
                outputBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void randomizeTransform(){
        Random random = new Random();

        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputBufferedImage.getRGB(i, j));
                int r = random.nextInt(0,256);
                int g = random.nextInt(0,256);
                int b = random.nextInt(0,256);
                Color newColor = new Color(r, g, b);
                outputBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void copyImage(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color c = new Color(inputBufferedImage.getRGB(i, j));
                outputBufferedImage.setRGB(i, j, c.getRGB());
            }
        }
    }

    public void writeOutImage(String outputPath){
        try {
            ImageIO.write(outputBufferedImage, "bmp", new File(outputPath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("This file cannot be scanned");
            System.exit(1);
        }
    }
}
