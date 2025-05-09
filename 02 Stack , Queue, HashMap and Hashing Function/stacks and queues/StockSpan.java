import java.util.Stack;

public class StockSpan {
    public static int[] calcSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[i] >= prices[stack.peek()]) {
                stack.pop();
            }

            span[i] = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }
        return span;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] span = calcSpan(prices);

        System.out.println("Stock prices:");
        for (int price : prices) {
            System.out.print(price + " ");
        }

        System.out.println("\nStock spans:");
        for (int s : span) {
            System.out.print(s + " ");
        }
    }
}
