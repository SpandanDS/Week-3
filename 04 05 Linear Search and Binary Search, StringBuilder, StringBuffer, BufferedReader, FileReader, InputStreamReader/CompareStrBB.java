public class CompareStrBB {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        String word = "hello";

        long startBuilder = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(word);
        }
        long endBuilder = System.nanoTime();
        long durationBuilder = endBuilder - startBuilder;

        long startBuffer = System.nanoTime();
        StringBuffer sB = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sB.append(word);
        }
        long endBuffer = System.nanoTime();
        long durationBuffer = endBuffer - startBuffer;

        System.out.println("Time taken by StringBuilder = " + durationBuilder / 1_000_000 + " ms");
        System.out.println("Time taken by StringBuffer = " + durationBuffer / 1_000_000 + " ms");
    }
}
