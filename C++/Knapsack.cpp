#include <iostream>
#include <algorithm>
using namespace std;

struct Item {
    int weight, value;
    double ratio;
};

bool compare(Item a, Item b) {
    return a.ratio > b.ratio;
}

double fractionalKnapsack(int capacity, Item items[], int n) {
    for (int i = 0; i < n; i++)
        items[i].ratio = (double)items[i].value / items[i].weight;

    sort(items, items + n, compare);

    double maxValue = 0.0;
    for (int i = 0; i < n; i++) {
        if (capacity >= items[i].weight) {
            maxValue += items[i].value;
            capacity -= items[i].weight;
        } else {
            maxValue += items[i].ratio * capacity;
            break;
        }
    }

    return maxValue;
}

int main() {
    int n, capacity;
    cout << "Enter number of items: ";
    cin >> n;

    Item items[n];

    cout << "Enter weights and values of the items:\n";
    for (int i = 0; i < n; i++) {
        cout << "Item " << i + 1 << " - Weight: ";
        cin >> items[i].weight;
        cout << "Item " << i + 1 << " - Value: ";
        cin >> items[i].value;
    }

    cout << "Enter knapsack capacity: ";
    cin >> capacity;

    double maxProfit = fractionalKnapsack(capacity, items, n);
    cout << "Maximum value that can be obtained: " << maxProfit << endl;

    return 0;
}