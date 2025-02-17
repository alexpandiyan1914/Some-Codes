#include <iostream>

using namespace std;

void fractionalKnapsack(int n, int weights[], int values[], int capacity) {
    // Arrays to store value/weight ratios and indices
    double ratio[n];
    int index[n];
    
    // Calculate value/weight ratios and initialize indices
    for (int i = 0; i < n; i++) {
        ratio[i] = (double)values[i] / weights[i];
        index[i] = i;
    }

    // Sort items based on value/weight ratio using simple bubble sort
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (ratio[j] < ratio[j + 1]) {
                // Swap ratios
                double tempRatio = ratio[j];
                ratio[j] = ratio[j + 1];
                ratio[j + 1] = tempRatio;

                // Swap indices
                int tempIndex = index[j];
                index[j] = index[j + 1];
                index[j + 1] = tempIndex;
            }
        }
    }

    double totalValue = 0.0;
    for (int i = 0; i < n; i++) {
        int idx = index[i];
        if (capacity >= weights[idx]) {
            capacity -= weights[idx];
            totalValue += values[idx];
        } else {
            totalValue += values[idx] * ((double)capacity / weights[idx]);
            break;
        }
    }

    cout << "Maximum value in Knapsack = " << totalValue << endl;
}

int main() {
    int n, capacity;
    cout << "Enter number of items: ";
    cin >> n;

    int weights[n], values[n];
    cout << "Enter weights and values for each item:\n";
    for (int i = 0; i < n; i++) {
        cin >> weights[i] >> values[i];
    }

    cout << "Enter knapsack capacity: ";
    cin >> capacity;

    fractionalKnapsack(n, weights, values, capacity);

    return 0;
}