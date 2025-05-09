public class InsertionSort {
    public static void main(String[] args) {
        int[] empIDs = {105, 103, 106, 101, 102, 104};

        insertionSort(empIDs);

        System.out.print("Sorted Employee IDs = ");
        for (int id : empIDs) {
            System.out.print(id + " ");
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n ; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = key;
        }
    }
}
