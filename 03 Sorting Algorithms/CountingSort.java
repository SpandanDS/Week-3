public class CountingSort {
    public static void main(String[] args) {
        int[] studentAges = {12, 15, 10, 18, 14, 13, 17, 16, 15, 12};

        countingSort(studentAges);

        System.out.print("Sorted student ages = ");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }
    }

    public static void countingSort(int[] arr) {
        int min = 10;
        int max = 19;

        int[] count = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < arr.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            int age = arr[i];
            output[count[age - min] - 1] = age;
            count[age - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
