
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Binary {

    public static void main(String[] args) throws IOException {
        String imagePath = "C:\\Users\\Asus\\Downloads\\PIC\\";
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the image file name: ");
        String imageName = scanner.nextLine();
        String inputImagePath = imagePath + imageName;

        String outputImagePath = binarizeImagePath(inputImagePath);

        System.out.println("Image binarization completed. Output image saved at: " + outputImagePath);
    }

    public static String binarizeImagePath(String inputImagePath) {
        try {
            File file = new File(inputImagePath);
            BufferedImage originalImage = ImageIO.read(file);

            int threshold = 100; // Threshold value (adjust as needed)
            BufferedImage binarizedImage = binarizeImage(originalImage, threshold);

            String outputImagePath = inputImagePath.replaceFirst("[.][^.]+$", "") + "_Binary.jpg";
            File outputImageFile = new File(outputImagePath);
            ImageIO.write(binarizedImage, "jpg", outputImageFile);

            return outputImagePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage binarizeImage(BufferedImage image, int threshold) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage binarizedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = binarizedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = new Color(binarizedImage.getRGB(col, row));
                int intensity = color.getRed(); 

                if (intensity < threshold) {
                    binarizedImage.setRGB(col, row, Color.BLACK.getRGB());
                } else {
                    binarizedImage.setRGB(col, row, Color.WHITE.getRGB());
                }
            }
        }

        return binarizedImage;
    }
}
