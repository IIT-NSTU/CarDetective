
import org.opencv.core.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarDetective {
public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Welcome to Car Detective!");
    System.out.print("Please provide the path to the image: ");

    try {
        String imagePath = reader.readLine();
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            System.out.println("Image detected!");
            
            String outputImagePath = "output.jpg";
            
            String GrayscalePath =	ImageConverter.convertToJpg(imagePath);

            // Call CannyEdgeDetection method here
            String cannyPath = Grayscale.convertToGrayscale(GrayscalePath);
            
            String ContourPath = CannyEdgeDetection.makeCannyEdgeDetection(cannyPath);
            
           String binary =  ContourDetection.contourDetection(ContourPath,imagePath); 
           
           String ex = Binary.binarizeImagePath(binary);
           DoTesseract.performTesseract(ex);
           KMP.dataMatchingFromFiles();
        } else {
        	
            System.out.println("Image not found.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
