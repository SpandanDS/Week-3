public class ConcatenateStrings {
    public static void main(String[] args) {
        String[] words = {"Java", "is", "fun", "and", "powerful"};

        String result = concatenateString(words);

        System.out.println("Concatenated String = " + result);
    }

    public static String concatenateString(String[] arr) {
        StringBuffer sb = new StringBuffer();

        for (String word : arr) {
            sb.append(word);
            sb.append(" ");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
}
