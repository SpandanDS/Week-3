import java.util.*;

public class SearchComparison2 {
    public static void main(String[] args) {
        int[] datasetSizes = {1_000, 100_000, 1_000_000};

        for (int size : datasetSizes) {
            System.out.println("Dataset Size = " + size);

            int[] array = new int[size];
            HashSet<Integer> hashSet = new HashSet<>();
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int i = 0; i < size; i++) {
                array[i] = i;
                hashSet.add(i);
                treeSet.add(i);
            }

            int target = size - 1;

            long startArr = System.nanoTime();
            boolean foundInArr = false;
            for (int value : array) {
                if (value == target) {
                    foundInArr = true;
                    break;
                }
            }
            long endArr = System.nanoTime();

            long startHashSet = System.nanoTime();
            boolean foundInHashSet = hashSet.contains(target);
            long endHashSet = System.nanoTime();

            long startTreeSet = System.nanoTime();
            boolean foundInTreeSet = treeSet.contains(target);
            long endTreeSet = System.nanoTime();

            System.out.println("Array Search Time = " + (endArr - startArr) / 1_000_000.0 + " ms (Found = " + foundInArr + ")");
            System.out.println("HashSet Search Time = " + (endHashSet - startHashSet) / 1_000_000.0 + " ms (Found = " + foundInHashSet + ")");
            System.out.println("TreeSet Search Time = " + (endTreeSet - startTreeSet) / 1_000_000.0 + " ms (Found = " + foundInTreeSet + ")");
        }
    }
}
