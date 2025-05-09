import java.io.*;

public class CompareStrClassesaAndReaders {
    public static void main(String[] args) {
        String word = "hello";
        int iterations = 1_000_000;
        String filePath = "largefile.txt";

        long startSB = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(word);
        }
        long endSB = System.nanoTime();
        long timeSB = (endSB - startSB) / 1_000_000;
        System.out.println("StringBuilder Time = " + timeSB + " ms");

        long startBuf = System.nanoTime();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbuf.append(word);
        }
        long endSBuf = System.nanoTime();
        long timeSBuf = (endSBuf - startBuf) / 1_000_000;
        System.out.println("StringBuffer Time = " + timeSBuf + " ms");

        long startFR = System.nanoTime();
        int wordCountFR = countWordsUsingFileReader(filePath);
        long endFR = System.nanoTime();
        System.out.println("FileReader Word Count = " + wordCountFR);
        System.out.println("FIleReader Time = " + (endFR - startFR) / 1_000_000 + " ms");

        long startISR = System.nanoTime();
        int wordCountISR = countWordsUsingInputStreamReader(filePath);
        long endISR = System.nanoTime();
        System.out.println("InputStreamReader Word Count = " + wordCountISR);
        System.out.println("InputStreamReader Time = " + (endISR - startISR) / 1_000_000 + " ms");
    }

    public static int countWordsUsingFileReader(String filepath) {
        int wordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.out.println("Error reading with FileReader");
            e.printStackTrace();
        }
        return wordCount;
    }

    public static int countWordsUsingInputStreamReader(String filepath) {
        int wordCount = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.out.println("Error reading with InputStreamReader");
            e.printStackTrace();
        }
        return wordCount;
    }
}
