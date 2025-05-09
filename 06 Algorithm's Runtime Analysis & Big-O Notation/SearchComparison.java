import java.util.*;

public class SearchComparison {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};
        Random rand = new Random();

        for (int size : sizes) {
            int[] data = new int[size];

            for (int i = 0; i < size; i++) {
                data[i] = rand.nextInt(size * 2);
            }

            int target = data[rand.nextInt(size)];

            System.out.println("Dataset size = " + size);
            System.out.println("Target = " + target);

            long startLinear = System.nanoTime();
            int linearIndex = linearSearch(data, target);
            long endLinear = System.nanoTime();

            System.out.printf("Linear Search: Index = %d, Time = %.3f ms\n", linearIndex, (endLinear - startLinear) / 1e6);

            Arrays.sort(data);
            long startBinary = System.nanoTime();
            int binaryIndex = Arrays.binarySearch(data, target);
            long endBinary = System.nanoTime();
            System.out.printf("Binary Search: Index = %d, Time = %.3f ms\n", binaryIndex, (endBinary - startBinary) / 1e6);
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }
}
