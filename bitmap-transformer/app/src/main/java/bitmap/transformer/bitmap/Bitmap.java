package bitmap.transformer.bitmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Bitmap {


    BufferedImage inputImage;
    BufferedImage outputImage;
    int width;
    int height;
    int type;

    public Bitmap(BufferedImage inputImage){
        width = inputImage.getWidth();
        height = inputImage.getHeight();
        type = BufferedImage.TYPE_INT_RGB;
        this.outputImage = new BufferedImage(width,height,type);
        this.inputImage = inputImage;
    }

    public BufferedImage getBufferedImage() {
        return outputImage;
    }

    public void grayScale(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputImage.getRGB(i, j));
                int r = currentColor.getRed();
                int g = currentColor.getGreen();
                int b = currentColor.getBlue();
                int gs = (r + g + b) / 3;

                Color newColor = new Color(gs, gs, gs);
                outputImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void invert(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputImage.getRGB(i, j));
                int r = 255 - currentColor.getRed();
                int g = 255 - currentColor.getGreen();
                int b = 255 - currentColor.getBlue();

                Color newColor = new Color(r, g, b);
                outputImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void randomize(){
        Random random = new Random();

        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputImage.getRGB(i, j));
                int r = random.nextInt(0,256);
                int g = random.nextInt(0,256);
                int b = random.nextInt(0,256);

                Color newColor = new Color(r, g, b);
                outputImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }


    public void copyImage(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color c = new Color(inputImage.getRGB(i, j));
                outputImage.setRGB(i, j, c.getRGB());
            }
        }
    }


    public void writeOutImage(String outputPath){

        try {
            ImageIO.write(outputImage, "bmp", new File(outputPath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("This file cannot be scanned");
            System.exit(1);
        }
    }
}
