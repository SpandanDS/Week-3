public class FibonacciComparison {
    public static int fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static int fibIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] testValues = {10, 30, 50};

        for (int n : testValues) {
            System.out.println("Fibonacci of " + n);

            if (n <= 40) {
                long startRecursive = System.nanoTime();
                int resRecursive = fibRecursive(n);
                long endRecursive = System.nanoTime();

                System.out.println("Recursive Result = " + resRecursive);
                System.out.println("Recursive Time = " + (endRecursive - startRecursive) / 1_000_000.0 + " ms");
            } else {
                System.out.println("Recursive Result = Skipped (Too slow)");
            }

            long startIterative = System.nanoTime();
            int resIterative = fibIterative(n);
            long endIterative = System.nanoTime();

            System.out.println("Iterative Result = " + resIterative);
            System.out.println("Iterative Time = " + (endIterative - startIterative) / 1_000_000.0 + " ms");
        }
    }
}
