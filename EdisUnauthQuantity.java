import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class EdisUnauthQuantity {

    public static void main(String[] args) {

        int[] transactionQty = { 5, 10, 25 };
        // int qty = 27;
        int qtys[] = { 0, 2, 7, 12, 17, 22, 28, 34, 37 };
        for (int qty : qtys) {

            int authorizedQty = IntStream.of(transactionQty).sum();
            // int qtyUtilised = getUnauthQuantity(transactionQty, qty);
            int qtyUtilised = tabulateRecursiveQty(qty, transactionQty);
            System.out.println("............................"+ "Qty target: "+qty+"............................................");
            System.out.println(" Quantity That will be cancel  : " + qtyUtilised);
            System.out.println(" Remaining Qty Will be " + (authorizedQty - qtyUtilised));
            System.out.println("........................................................................");
            System.out.println();
            System.out.println();

        }
    }

    static int tabulateRecursiveQty(int target, int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        // Base cases:
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0; // No amount to utilize, always 0
        }
        for (int j = 1; j <= target; j++) {
            dp[0][j] = 10000; // No amount to utilize, maximum value
        }

        // Tabulation:
        for (int i = n; i >= 0; i--) {
            for (int j = 0; j <= target; j++) {

                if (j == 0) {
                    dp[i][j] = 0;
                } else if (i >= n) {
                    dp[i][j] = 1000;
                } else {
                    int baseSum = j - nums[i];
                    int take = nums[i] + ((baseSum <= 0) ? 0 : dp[i + 1][baseSum]);
                    int notTake = dp[i + 1][j];
                    dp[i][j] = Math.min(notTake, take);
                }
            }
        }
        return dp[0][target];
    }

    static int getUnauthQuantity(int[] nums, int qty) {

        return recursionQty(0, qty, nums);
    }

    static int recursionQty(int index, int target, int[] nums) {
        if (target <= 0)
            return 0;
        if (index >= nums.length)
            return 10000;

        int take = nums[index] + recursionQty(index + 1, target - nums[index], nums);
        int notTake = recursionQty(index + 1, target, nums);
        System.out.println("sum take  and nottake :  " + take + " ,  " + notTake);
        return Math.min(take, notTake);

    }

}