public class MatrixSearch {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        int target = 17;

        boolean found = searchMatrix(matrix, target);

        System.out.println("Is target found? " + found);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int midElement = matrix[mid / cols][mid % cols];

            if (midElement == target) {
                return true;
            } else if (midElement < target) {
                left = mid + 1;
            } else  {
                right = mid - 1;
            }
        }
        return  false;
    }
}
