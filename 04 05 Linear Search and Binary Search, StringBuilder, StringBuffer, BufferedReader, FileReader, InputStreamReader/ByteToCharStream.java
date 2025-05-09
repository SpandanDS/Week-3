import java.io.*;

public class ByteToCharStream {
    public static void main(String[] args) {
        String filePath = "utf8data.txt";

        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
             BufferedReader br = new BufferedReader(isr)) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found = " + filePath);
        } catch (UnsupportedEncodingException e) {
            System.out.println("The specified encoding is not supported");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }
    }
}
