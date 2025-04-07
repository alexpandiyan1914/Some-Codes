10) public class CoinChange {

    public static long getWays(int n, int[] coins) {
        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int amount = coin; amount <= n; amount++) {
                dp[amount] += dp[amount - coin];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 3;
        int[] coins = {8, 3, 1, 2};
        System.out.println("Ways to make change: " + getWays(n, coins));
    }
}