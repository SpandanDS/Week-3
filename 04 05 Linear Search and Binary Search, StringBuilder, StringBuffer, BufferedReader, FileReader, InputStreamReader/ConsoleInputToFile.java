import java.io.*;

public class ConsoleInputToFile {
    public static void main(String[] args) {
        String outputFile = "user_input.txt";

        try (
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(isr);

                FileWriter fw = new FileWriter(outputFile);
                BufferedWriter writer = new BufferedWriter(fw)
                ) {
            System.out.println("Enter text = ");

            String inputLine;
            while (true) {
                inputLine = reader.readLine();

                if ("exit".equalsIgnoreCase(inputLine)) {
                    break;
                }

                writer.write(inputLine);
                writer.newLine();
            }
            System.out.println("User input has been saved to '" + outputFile);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input or writing to file");
            e.printStackTrace();
        }
    }
}
