import java.util.*;

public class CoinChangeGreedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of coin types: ");
        int n = sc.nextInt();
        int[] coins = new int[n];

        System.out.println("Enter coin denominations:");
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        System.out.print("Enter the amount: ");
        int amount = sc.nextInt();

        Arrays.sort(coins);
        System.out.println("Coins used:");

        for (int i = n - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                System.out.print(coins[i] + " ");
                amount -= coins[i];
            }
        }

        if (amount > 0) {
            System.out.println("\nRemaining amount cannot be changed with given coins.");
        }
    }
}