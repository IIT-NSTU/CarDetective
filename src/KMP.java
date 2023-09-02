import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KMP {
    public static int[] computePrefixArray(String pattern) {
        int[] prefixArray = new int[pattern.length()];
        prefixArray[0] = 0;

        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixArray[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefixArray[i] = j;
        }
        return prefixArray;
    }

    public static boolean kmpSearch(String text, String pattern) {
        int[] prefixArray = computePrefixArray(pattern);
        int j = 0; 

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefixArray[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == pattern.length()) {
                    return true; 
                }
            }
        }
        return false; 
    }

    public static void dataMatching(List<Pair> dataset, String outputContent) {
        for (Pair pair : dataset) {
            if (kmpSearch(outputContent, pair.first)) {
            	System.out.println("The Vehicle Information: ");
                System.out.println(pair.second);
                return;
            }
        }
        System.out.println("No matching pair found.");
    }

    public static void main(String[] args) {
        dataMatchingFromFiles();
    }

    public static void dataMatchingFromFiles() {
        String datasetFile = "src\\dataset.txt";
        List<Pair> dataset = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(datasetFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(">");
                if (parts.length == 2) {
                    String first = parts[0];
                    String second = parts[1];
                    dataset.add(new Pair(first, second));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String outputContent = "";
        String outputFilePath = "src\\Output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputContent += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        dataMatching(dataset, outputContent);
    }

    static class Pair {
        String first;
        String second;

        Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }
}

