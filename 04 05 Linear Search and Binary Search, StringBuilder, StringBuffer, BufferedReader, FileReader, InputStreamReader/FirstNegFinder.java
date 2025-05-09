public class FirstNegFinder {
    public static void main(String[] args) {
        int[] nums = {4, 7, 12, 0, -3, 8, -1};

        int index = findFirstNeg(nums);

        if (index != -1) {
            System.out.println("1st negative num found at index = " + index);
            System.out.println("Value = " + nums[index]);
        } else {
            System.out.println("No negative num found");
        }
    }

    public static int findFirstNeg(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1;
    }
}
