public class QuickSort {
    public static void main(String[] args) {
        double[] productPrices = {259.99, 159.99, 699.99, 569.89, 389.99, 269.99, 799.99};

        quickSort(productPrices, 0, productPrices.length - 1);

        System.out.print("Sorted prices = ");
        for (double price : productPrices) {
            System.out.print(price + " ");
        }
    }

    public static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
