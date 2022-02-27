package bitmapTransformer.bitmap;

import java.io.*;
import java.util.Arrays;

public class Bitmap {
    private static final byte[] IMAGE_TYPE = {'B', 'M'};
    private static final int HEADER_SIZE = 14;
    private static final int SIZE_DATA_START_INDEX = 2; // image size stored at byte offset 2
    private static final int PIXEL_ARRAY_DATA_START_INDEX = 10; // pixel start offset is the last 4 bytes of the header
    private byte[] header = new byte[HEADER_SIZE];
    private int pixelArrayStartOffset; // Value in header that holds the offset for where the pixel array begins
    private int pixelDataSizeInBytes;
    private int width;
    private int height;
    private int sizeInBytes;
    private byte[] deviceIndependantHeader; // DIB header holds width/height data
    private byte[] pixelData;
    private RGB[][] pixels;

    public Bitmap(File imageFile) {
        try {
            InputStream is = new FileInputStream(imageFile);
            // Get header
            is.read(header, 0, HEADER_SIZE);
            verifyTypeBMP(); // stops program if file is not .bmp type
            sizeInBytes = concatinateFourBytesInArray(header, SIZE_DATA_START_INDEX);
            pixelArrayStartOffset = concatinateFourBytesInArray(header, PIXEL_ARRAY_DATA_START_INDEX);
            deviceIndependantHeader = new byte[pixelArrayStartOffset - HEADER_SIZE];
            // Get DIB Header
            is.read(deviceIndependantHeader, 0, deviceIndependantHeader.length);
            // Set height and width
            width = concatinateFourBytesInArray(deviceIndependantHeader, 4);
            height = concatinateFourBytesInArray(deviceIndependantHeader, 8);
            // Get pixel array data
            pixelDataSizeInBytes = sizeInBytes - pixelArrayStartOffset;
            pixelData = new byte[pixelDataSizeInBytes];
            is.read(pixelData);

            fillRGBPixels();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    public int getWidth() {
        return width;
    }

   public int getHeight() {
        return height;
   }

    private void verifyTypeBMP() {
        byte[] imageTypeSignature = {header[0], header[1]};
        if (Arrays.compare(imageTypeSignature, IMAGE_TYPE) != 0) {
            System.out.println("Unable to process. Not of filetype BMP");
            System.exit(1);
        }
    }

    private void fillRGBPixels() {
        pixels = new RGB[height][width];
        int pixelsHeightMultiplier = 1;
        int pixelsWidthIndex = 0; // track which pixel in the row we're on
        // calculate the padding between each set of pixels
        int pixelPaddingSize = (pixelDataSizeInBytes - (width * 3 * height)) / height;
        int endOfRowOffset = 0; // This increases each row to account for the padding bytes between rows
        for (int i = 0; i < pixelData.length; i++) {
            // compare the current index of the pixel array minus any padding vs.
            // width * colors bytes * the row we're on (starts at 1 not 0)
            // This should be true when the index points to the first padding byte at the of a row
            if(i - endOfRowOffset == (width * 3 * pixelsHeightMultiplier)) {
                i += pixelPaddingSize - 1; // this is -1 because we're already on the first padded pixel
                pixelsWidthIndex = 0;
                pixelsHeightMultiplier++;
                endOfRowOffset += pixelPaddingSize;
            }
            else {
                int blue = Byte.toUnsignedInt(pixelData[i++]);
                int green = Byte.toUnsignedInt(pixelData[i++]);
                int red = Byte.toUnsignedInt(pixelData[i]);
                RGB pixel = new RGB(red, green, blue);
                pixels[pixelsHeightMultiplier - 1][pixelsWidthIndex] = pixel; // -1 sets returns height to 0 indexed
                pixelsWidthIndex++;
            }
        }
    }

    public void writeOut(File outFile) throws IOException {
        FileOutputStream os = new FileOutputStream(outFile);
        os.write(header);
        os.write(deviceIndependantHeader);
        os.write(pixelData);
        os.close();

    }

    // The syntax for getting this right (using 0xFF &) found here https://stackoverflow.com/questions/2840190/java-convert-4-bytes-to-int
    public int concatinateFourBytesInArray(byte[] array, int startingIndex) {
        return ((0xFF & array[startingIndex + 3]) << 24)
                | ((0xFF & array[startingIndex + 2]) << 16)
                | ((0xFF & array[startingIndex + 1]) << 8)
                | (0xFF & array[startingIndex]);
    }

}
