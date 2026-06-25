import java.util.*;

public class RetailInventoryOptimization {

    // Activity Scheduling (Greedy)
    static void activitySelection(int start[], int finish[], int n) {
        System.out.println("Selected Activities:");

        int i = 0;
        System.out.print("A" + i + " ");

        for (int j = 1; j < n; j++) {
            if (start[j] >= finish[i]) {
                System.out.print("A" + j + " ");
                i = j;
            }
        }
        System.out.println();
    }

    // 0/1 Knapsack
    static int knapsack(int W, int wt[], int val[], int n) {
        int dp[][] = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]);

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    // Longest Common Subsequence
    static int lcs(String X, String Y) {

        int m = X.length();
        int n = Y.length();

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    // Matrix Chain Multiplication
    static int matrixChainOrder(int p[]) {

        int n = p.length;
        int m[][] = new int[n][n];

        for (int L = 2; L < n; L++) {

            for (int i = 1; i < n - L + 1; i++) {

                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    int q = m[i][k] + m[k + 1][j]
                            + p[i - 1] * p[k] * p[j];

                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n - 1];
    }

    public static void main(String[] args) {

        System.out.println("RETAIL INVENTORY OPTIMIZATION SYSTEM");

        // Activity Scheduling
        int start[] = {1, 3, 0, 5, 8, 5};
        int finish[] = {2, 4, 6, 7, 9, 9};

        activitySelection(start, finish, start.length);

        // Knapsack
        int profit[] = {60, 100, 120};
        int storage[] = {10, 20, 30};
        int capacity = 50;

        System.out.println("\nMaximum Profit: "
                + knapsack(capacity, storage, profit, profit.length));

        // LCS
        String demand1 = "ABCDEF";
        String demand2 = "AEBDF";

        System.out.println("\nLCS Length: "
                + lcs(demand1, demand2));

        // Matrix Chain Multiplication
        int matrices[] = {10, 20, 30, 40, 30};

        System.out.println("\nMinimum Matrix Multiplication Cost: "
                + matrixChainOrder(matrices));
    }
}