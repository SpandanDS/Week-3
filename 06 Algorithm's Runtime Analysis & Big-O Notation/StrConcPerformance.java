public class StrConcPerformance {
    public static void main(String[] args) {
        int[] counts = {1000, 10000, 1000000};

        for (int n : counts) {
            System.out.println("Operations Count = " + n);

            long start = System.nanoTime();
            String str = "";
            for (int i = 0; i < n; i++) {
                str += "hello";
            }
            long end = System.nanoTime();
            System.out.printf("String Time = %.3f ms\n", (end - start) / 1e6);


            start = System.nanoTime();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append("hello");
            }
            end = System.nanoTime();
            System.out.printf("StringBuilder Time = %.3f ms\n", (end - start) / 1e6);

            start = System.nanoTime();
            StringBuffer sbf = new StringBuffer();
            for (int i = 0; i < n; i++) {
                sbf.append("hello");
            }
            end = System.nanoTime();
            System.out.printf("StringBuffer Time = %.3f ms\n", (end - start) / 1e6);
        }
    }
}
