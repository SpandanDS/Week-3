import java.util.*;

public class ZeroSumSubarrays {
    public static List<int[]> findZeroSum(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        int sum = 0;
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                for (Integer start : map.get(sum)) {
                    result.add(new int[]{start + 1, i});
                }
            }
            map.putIfAbsent(sum, new ArrayList<>());
            map.get(sum).add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -7, 3, 1, 3, 1, -4, -2, -2};

        List<int[]> subarrays = findZeroSum(arr);
        System.out.println("Zero-sum Subarrays:");
        for (int[] pair : subarrays) {
            System.out.println("From index " + pair[0] + " to " + pair[1]);
        }
    }
}
