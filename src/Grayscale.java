
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Grayscale {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the input image: ");
        String imagePath = scanner.nextLine();

        String savedImagePath = convertToGrayscale(imagePath);
        if (savedImagePath != null) {
            System.out.println("Grayscale image saved successfully at: " + savedImagePath);
        } else {
            System.out.println("Error saving grayscale image.");
        }
    }

    public static String convertToGrayscale(String inputImagePath) {
        BufferedImage img = null;
        try {
            // Read the converted JPG image
            File inputFile = new File(inputImagePath);
            img = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

        // Get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        // Convert to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // Calculate average
                int avg = (r + g + b) / 3;

                // Replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(x, y, p);
            }
        }

        // Build the output image path
        String outputImagePath = inputImagePath.replaceFirst("[.][^.]+$", "") + "_Grayscale.jpg";

        // Save the grayscale image
        try {
            File outputFile = new File(outputImagePath);
            ImageIO.write(img, "jpg", outputFile);
            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
