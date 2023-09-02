
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CannyEdgeDetection {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String inputImagePath = "C:\\Users\\Asus\\Downloads\\PIC\\4-Figure11-1 (1)_Jpg_Grayscale.jpg";

        try {
            String outputImagePath = makeCannyEdgeDetection(inputImagePath);
            System.out.println("Canny Detection done successfully. Result saved at: " + outputImagePath);
        } catch (Exception e) {
            System.out.println("Error during Canny edge detection: " + e.getMessage());
        }
    }

    public static String makeCannyEdgeDetection(String imagePath) {
        try {
            Mat image = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_GRAYSCALE);

            Mat blurredImage = new Mat();
            Imgproc.GaussianBlur(image, blurredImage, new Size(5, 5), 0);

            Mat edges = new Mat();
            double threshold1 = 100; 
            double threshold2 = 200; 
            Imgproc.Canny(blurredImage, edges, threshold1, threshold2);

            String outputImagePath = imagePath.replaceFirst("[.][^.]+$", "") + "_Canny.jpg";

            Imgcodecs.imwrite(outputImagePath, edges);

            return outputImagePath;
        } catch (Exception e) {
            System.out.println("Error during Canny edge detection: " + e.getMessage());
            return null;
        }
    }
}

