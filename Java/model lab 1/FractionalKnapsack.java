import java.util.Scanner;

public class FractionalKnapsack {

    public static double knapsackByProfit(int[] weight, int[] value, int n, int W) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (value[j] < value[j + 1]) {
                    int tempValue = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempValue;

                    int tempWeight = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWeight;
                }
            }
        }

        double totalValue = 0;
        for (int i = 0; i < n; i++) {
            if (W == 0) break;
            double profit = 0;
            if (weight[i] <= W) {
                W -= weight[i];
                profit = value[i];
            } else {
                profit = value[i] * ((double) W / weight[i]);
                W = 0;
            }
            totalValue += profit;
        }
        return totalValue;
    }

    public static double knapsackByWeight(int[] weight, int[] value, int n, int W) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (weight[j] > weight[j + 1]) {
                    int tempWeight = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWeight;

                    int tempValue = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempValue;
                }
            }
        }

        double totalValue = 0;
        for (int i = 0; i < n; i++) {
            if (W == 0) break;
            double profit = 0;
            if (weight[i] <= W) {
                W -= weight[i];
                profit = value[i];
            } else {
                profit = value[i] * ((double) W / weight[i]);
                W = 0;
            }
            totalValue += profit;
        }
        return totalValue;
    }

    public static double knapsackByProfitWeightRatio(int[] weight, int[] value, int n, int W) {
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = (double) value[i] / weight[i];
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ratio[j] < ratio[j + 1]) {
                    double tempRatio = ratio[j];
                    ratio[j] = ratio[j + 1];
                    ratio[j + 1] = tempRatio;

                    int tempWeight = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWeight;

                    int tempValue = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempValue;
                }
            }
        }

        double totalValue = 0;
        for (int i = 0; i < n; i++) {
            if (W == 0) break;
            double profit = 0;
            if (weight[i] <= W) {
                W -= weight[i];
                profit = value[i];
            } else {
                profit = value[i] * ((double) W / weight[i]);
                W = 0;
            }
            totalValue += profit;
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        System.out.print("Enter the capacity of the knapsack: ");
        int W = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value of item " + (i + 1) + ": ");
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        double maxProfit = knapsackByProfit(weight.clone(), value.clone(), n, W);
        double maxWeight = knapsackByWeight(weight.clone(), value.clone(), n, W);
        double maxRatio = knapsackByProfitWeightRatio(weight.clone(), value.clone(), n, W);

        System.out.println("\nMax Value based on Profit: " + maxProfit);
        System.out.println("Max Value based on Weight: " + maxWeight);
        System.out.println("Max Value based on Profit/Weight Ratio: " + maxRatio);

        double maxValue = Math.max(maxProfit, Math.max(maxWeight, maxRatio));
        if (maxValue == maxProfit) {
            System.out.println("Optimal Strategy: Based on Profit");
        } else if (maxValue == maxWeight) {
            System.out.println("Optimal Strategy: Based on Weight");
        } else {
            System.out.println("Optimal Strategy: Based on Profit/Weight Ratio");
        }

        sc.close();
    }
}
