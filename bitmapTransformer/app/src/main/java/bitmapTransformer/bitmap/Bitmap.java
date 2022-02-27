package bitmapTransformer.bitmap;

import java.io.*;
import java.util.Arrays;

public class Bitmap {
    private static final byte[] IMAGE_TYPE = {'B', 'M'};
    private static final int HEADER_SIZE = 14;
    private byte[] header = new byte[HEADER_SIZE];
    private int pixelArrayStartOffset;
    private int width;
    private int height;
    private int sizeInBytes;
    private byte[] deviceIndependantHeader;
    private byte[] pixelData;
    private RGB[][] pixels;

    public Bitmap(File imageFile) {
        try {
            InputStream is = new FileInputStream(imageFile);
            // check type
            is.read(header, 0, HEADER_SIZE);
            byte[] imageTypeSignature = {header[0], header[1]};
            // The syntax for getting this right (using 0xFF &) found here https://stackoverflow.com/questions/2840190/java-convert-4-bytes-to-int
            // We need to concatenate the bytes in position 2-5 to get a single value
            sizeInBytes = ((0xFF & header[5]) << 24) | ((0xFF & header[4]) << 16) | ((0xFF & header[3]) << 8) | (0xFF & header[2]);
            // pixel start offset is the last 4 bytes of the header
            pixelArrayStartOffset =  ((0xFF & header[13]) << 24) | ((0xFF & header[12]) << 16) | ((0xFF & header[11]) << 8) | (0xFF & header[10]);
            System.out.println("pixelarray offset: " + Integer.toHexString(pixelArrayStartOffset));
            if (Arrays.compare(imageTypeSignature, IMAGE_TYPE) != 0) {
                System.out.println("Unable to process. Not of filetype BMP");
                System.exit(1);
            }
            System.out.println("SIZE: " + sizeInBytes);
            deviceIndependantHeader = new byte[pixelArrayStartOffset - HEADER_SIZE];
            is.read(deviceIndependantHeader, 0, deviceIndependantHeader.length);
            System.out.println(pixelArrayStartOffset);

            System.out.println();
            System.out.println("TYPE --> " + Integer.toHexString(imageTypeSignature[0]) + " " + Integer.toHexString(imageTypeSignature[1]));

            System.out.print("HEADER --> ");
            for (byte b : header) {
                System.out.print(Integer.toHexString(b) + " : ");
            }
            System.out.print("\nDIB --> ");
            for (byte b : deviceIndependantHeader) {
                System.out.print(Integer.toHexString(b) + " : ");
            }

            // set height and width
            width = ((0xFF & deviceIndependantHeader[7]) << 24) | ((0xFF & deviceIndependantHeader[6]) << 16) | ((0xFF & deviceIndependantHeader[5]) << 8) | (0xFF & deviceIndependantHeader[4]); // pos 18 of entire header (header + DIB)
            height = ((0xFF & deviceIndependantHeader[11]) << 24) | ((0xFF & deviceIndependantHeader[10]) << 16) | ((0xFF & deviceIndependantHeader[9]) << 8) | (0xFF & deviceIndependantHeader[8]); // pos 22 of entire header (header + DIB)

            System.out.println("\nWIDTH --> " + width);
            System.out.println("\nHEIGHT --> " + height);

            //System.out.println("\n-- PIXEL ARRAY --");
            int pixelDataSizeInBytes = sizeInBytes - pixelArrayStartOffset;
            System.out.println("PIXEL DATA SIZE --> " + pixelDataSizeInBytes);
            pixelData = new byte[pixelDataSizeInBytes];
            is.read(pixelData);


            // pixelData.length - 1 is used because demo bitmap has extra zero bit at the end.
            pixels = new RGB[height][width];
            int pixelsHeightMultiplier = 1;
            int pixelsWidthIndex = 0;
            int pixelPaddingSize = (pixelDataSizeInBytes - (width * 3 * height)) / height;
            int endOfRowOffset = 0;
            for (int i = 0; i < pixelData.length; i++) {
                if(i - endOfRowOffset == (width * 3 * pixelsHeightMultiplier)) {
                    System.out.println("\n-----");
                    i += pixelPaddingSize - 1;
                    pixelsWidthIndex = 0;
                    pixelsHeightMultiplier++;
                    endOfRowOffset += pixelPaddingSize;
                }
                else {
                    int blue = Byte.toUnsignedInt(pixelData[i++]);
                    int green = Byte.toUnsignedInt(pixelData[i++]);
                    int red = Byte.toUnsignedInt(pixelData[i]);
                    RGB pixel = new RGB(red, green, blue);
                    pixels[pixelsHeightMultiplier - 1][pixelsWidthIndex] = pixel;
                    pixelsWidthIndex++;
                    System.out.println(pixel);
                }
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
