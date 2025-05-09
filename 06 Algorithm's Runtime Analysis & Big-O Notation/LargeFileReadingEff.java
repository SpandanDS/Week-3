import java.io.*;

public class LargeFileReadingEff {
    public static void main(String[] args) {
        String filePath = "largeFile.txt";

        System.out.println("Reading using FileReader...");
        long start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.printf("FileReader Time = %.3f ms\n", (end - start) / 1e9);

        System.out.println("Reading using InputStreamReader...");
        start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            while (br.readLine() != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        end = System.nanoTime();
        System.out.printf("InputStreamReader Time = %.3f ms\n", (end - start) / 1e9);
    }
}
