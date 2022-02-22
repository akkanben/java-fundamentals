package bitmapTransformer.bitmap;

import java.io.*;
import java.util.Arrays;

public class Bitmap {
    private static final int headerSize = 14;
    private int pixelArrayStartOffset;
    private static final byte[] type = {'B', 'M'};
    private int width;
    private int height;
    private int size;
    private byte[] header = new byte[headerSize];
    private byte[] deviceIndependantHeader;
    private byte[] pixelData;
    private RGB[][] pixels;

    public Bitmap(File imageFile) {
        try {
            InputStream is = new FileInputStream(imageFile);
            // check type
            is.read(header, 0, headerSize);
            byte[] imageTypeSignature = {header[0], header[1]};
            // size 4 bytes with a 2 byte offset
            byte[] s = {header[2], header[3], header[4], header[5]};
            // The syntax for getting this right (using 0xFF &) found here https://stackoverflow.com/questions/2840190/java-convert-4-bytes-to-int
            size = ((0xFF & header[5]) << 24) | ((0xFF & header[4]) << 16) | ((0xFF & header[3]) << 8) | (0xFF & header[2]);
            System.out.println(size);
            // pixel start offset is the last 4 bytes of the header
            pixelArrayStartOffset = header[10] + header[11] + header[12] + header[13];
            if(Arrays.compare(imageTypeSignature, type) != 0) {
                System.out.println("Unable to process. Not of filetype BMP");
                System.exit(1);
            }
            System.out.println("SIZE: " + size);
            deviceIndependantHeader = new byte[pixelArrayStartOffset - headerSize];
            is.read(deviceIndependantHeader, 0, deviceIndependantHeader.length);
            System.out.println(pixelArrayStartOffset);
            // set header
            // set width & height
            // print
            System.out.println("TYPE");
            System.out.println(Integer.toHexString(imageTypeSignature[0]) + " " + Integer.toHexString(imageTypeSignature[1]));
            System.out.println("HEADER");
            for (byte b : header) {
                System.out.print(Integer.toHexString(b) + " : ");
            }
            System.out.println("\nDIB");
            for (byte b : deviceIndependantHeader) {
                System.out.print(Integer.toHexString(b) + " : ");
            }
            width = ((0xFF & deviceIndependantHeader[7]) << 24) | ((0xFF & deviceIndependantHeader[6]) << 16) | ((0xFF & deviceIndependantHeader[5]) << 8) | (0xFF & deviceIndependantHeader[4]); // pos 18 of entire header (header + DIB)
            height= ((0xFF & deviceIndependantHeader[11]) << 24) | ((0xFF & deviceIndependantHeader[10]) << 16) | ((0xFF & deviceIndependantHeader[9]) << 8) | (0xFF & deviceIndependantHeader[8]); // pos 22 of entire header (header + DIB)

            System.out.println("\n" + width);
            System.out.println("\n" + height);
            System.out.println("\nPIXEL ARRAY");
            pixelData = new byte[size - headerSize + deviceIndependantHeader.length];
            is.read(pixelData);
            for (byte b : pixelData) {
                System.out.print(Integer.toHexString(b) + " : ");
            }
            pixels = new RGB[height][width];
            for(int i = 1; i <= height; i++) {
                RGB pixel = new RGB();
                for(int j = 0; j < width * 3; j += 3) {
                    pixel.blue = pixelData[i * j];
                    pixel.green = pixelData[i * j + 1];
                    pixel.red = pixelData[i * j + 2];
                    pixels[i - 1][j] = pixel;
                }
                //i += 2; //skip 2 byte end of lines
            }
            for (byte b : pixelData) {
                System.out.print(Integer.toHexString(b) + " : ");
            }
            File out = new File("app/src/main/resources/out.bmp");
            FileOutputStream os = new FileOutputStream(out);
            os.write(header);
            os.write(deviceIndependantHeader);
            os.write(pixelData);
            os.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }

    }
}
