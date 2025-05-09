public class FirstLastOcc {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 5, 5};

        int target = 3;

        int first = findFirstOcc(nums, target);
        int last = findLastOcc(nums, target);

        if (first == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("First Occ = " + first);
            System.out.println("Last Occ = " + last);
        }
    }

    public static int findFirstOcc(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static int findLastOcc(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
