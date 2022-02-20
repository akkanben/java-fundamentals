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

    public void darkenTransform(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputBufferedImage.getRGB(i, j));
                int[] rgb = new int[3];
                rgb[0] = currentColor.getRed();
                rgb[1] = currentColor.getGreen();
                rgb[2] = currentColor.getBlue();
                for(int rgbIndex = 0; rgbIndex < rgb.length; rgbIndex++) {
                    rgb[rgbIndex] -= 40;
                    if (rgb[rgbIndex] < 0)
                        rgb[rgbIndex] = 0;
                }
                Color newColor = new Color(rgb[0], rgb[1], rgb[2]);
                outputBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void lightenTransform(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color currentColor = new Color(inputBufferedImage.getRGB(i, j));
                int[] rgb = new int[3];
                rgb[0] = currentColor.getRed();
                rgb[1] = currentColor.getGreen();
                rgb[2] = currentColor.getBlue();
                for(int rgbIndex = 0; rgbIndex < rgb.length; rgbIndex++) {
                    rgb[rgbIndex] += 40;
                    if (rgb[rgbIndex] > 255)
                        rgb[rgbIndex] = 255;
                }
                Color newColor = new Color(rgb[0], rgb[1], rgb[2]);
                outputBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void randomizeTransform(){
        Random random = new Random();
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                int r = random.nextInt(0,256);
                int g = random.nextInt(0,256);
                int b = random.nextInt(0,256);
                Color newColor = new Color(r, g, b);
                outputBufferedImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void pixelateTransform(){
        Color color = new Color(0, 0, 0);
        for(int i = 0; i < height; i += 2){
            for (int j = 0; j < width; j += 2) {
                color = new Color(inputBufferedImage.getRGB(j, i));
                outputBufferedImage.setRGB(j, i, color.getRGB());
                if (j + 1 < width && i + 1 < height)
                    outputBufferedImage.setRGB(j + 1, i + 1, color.getRGB());
                if (j + 1 < width)
                    outputBufferedImage.setRGB(j + 1, i, color.getRGB());
                if (i + 1 < height)
                    outputBufferedImage.setRGB(j, i + 1, color.getRGB());
            }
        }
    }
    public void horizontalMirrorTransform() {
        Color[][] pixelArray  = getPixelArray();
        for(int row = 0; row < pixelArray.length; row++) {
            int index = 0;
            while(index++ < pixelArray[row].length / 2) {
                Color[] currentRow = pixelArray[row];
                Color temp = currentRow[index];
                currentRow[index] = currentRow[currentRow.length - 1 - index];
                currentRow[currentRow.length - 1 - index] = temp;
            }
        }
        putPixelArray(pixelArray);
    }

    public void verticalMirrorTransform() {
        Color[][] pixelArray  = getFlippedPixelArray();
        for(int row = 0; row < pixelArray.length; row++) {
            int index = 0;
            while(index++ < pixelArray[row].length / 2) {
                Color[] currentRow = pixelArray[row];
                Color temp = currentRow[index];
                currentRow[index] = currentRow[currentRow.length - 1 - index];
                currentRow[currentRow.length - 1 - index] = temp;
            }
        }
        putFlippedPixelArray(pixelArray);
    }

    public void copyImage(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color color = new Color(inputBufferedImage.getRGB(i, j));
                outputBufferedImage.setRGB(i, j, color.getRGB());
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

    //HELPER METHODS FOR RASTER DATA TRANSFORMS
    public Color[][] getPixelArray() {
       Color[][] pixelArray = new Color[height][width];
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Color color = new Color(inputBufferedImage.getRGB(j, i));
                pixelArray[i][j] = color;
            }
        }
       return pixelArray;
    }

    public Color[][] getFlippedPixelArray() {
        Color[][] pixelArray = new Color[width][height];
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Color color = new Color(inputBufferedImage.getRGB(i, j));
                pixelArray[i][j] = color;
            }
        }
        return pixelArray;
    }

    public void putPixelArray(Color[][] pixelArray) {
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++)
                outputBufferedImage.setRGB(j, i, pixelArray[i][j].getRGB());
        }
    }

    public void putFlippedPixelArray(Color[][] pixelArray) {
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++)
                outputBufferedImage.setRGB(i, j, pixelArray[i][j].getRGB());
        }
    }
}
