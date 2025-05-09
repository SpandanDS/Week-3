import java.util.HashSet;

public class PairWithGivenSum {
    public static boolean hasPaiWithSum(int[] arr, int target) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                System.out.println("Pair found = (" + complement + ", " + num + ")");
                return true;
            }
            seen.add(num);
        }

        System.out.println("no pair found");
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {10, 15, 3, 7};
        int target = 17;

        hasPaiWithSum(arr, target);
    }
}

