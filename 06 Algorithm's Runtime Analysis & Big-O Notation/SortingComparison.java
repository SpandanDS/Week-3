import java.util.*;

public class SortingComparison {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};

        Random rand = new Random();

        for (int size : sizes) {
            System.out.println("Dataset size = " + size);
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = rand.nextInt(size);
            }

            if (size <= 10000) {
                int[] bubbleArr = Arrays.copyOf(original, size);
                long start = System.nanoTime();
                bubbleSort(bubbleArr);
                long end = System.nanoTime();
                System.out.printf("Bubble Sort Time = %.3f ms\n", (end - start) / 1e6);
            } else {
                System.out.println("Bubble Sort Time = Unfeasible");
            }

            int[] mergeArr = Arrays.copyOf(original, size);
            long start = System.nanoTime();
            mergeSort(mergeArr, 0, mergeArr.length - 1);
            long end = System.nanoTime();
            System.out.printf("Merge Sort Time = %.3f ms\n", (end - start) / 1e6);

            int[] quickArr = Arrays.copyOf(original, size);
            start = System.nanoTime();
            quickSort(quickArr, 0, quickArr.length - 1);
            end = System.nanoTime();
            System.out.printf("Quick Sort Time = %.3f ms\n", (end - start) / 1e6);
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi  + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

