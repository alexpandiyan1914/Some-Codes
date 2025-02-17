#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>

using namespace std;

struct Item {
    int value, weight;
    double ratio;
};

bool compareByValue(const Item &a, const Item &b) {
    return a.value > b.value;
}

bool compareByWeight(const Item &a, const Item &b) {
    return a.weight < b.weight;
}

bool compareByRatio(const Item &a, const Item &b) {
    return a.ratio > b.ratio;
}

double fractionalKnapsack(vector<Item> items, int capacity, string strategy) {
    double totalValue = 0.0;
    vector<pair<int, double>> proportions;

    if (strategy == "profit")
        sort(items.begin(), items.end(), compareByValue);
    else if (strategy == "weight")
        sort(items.begin(), items.end(), compareByWeight);
    else if (strategy == "profit/weight")
        sort(items.begin(), items.end(), compareByRatio);

    for (auto &item : items) {
        if (capacity >= item.weight) {
            capacity -= item.weight;
            totalValue += item.value;
            proportions.push_back({item.value, 1.0});
        } else {
            double fraction = (double)capacity / item.weight;
            totalValue += item.value * fraction;
            proportions.push_back({item.value, fraction});
            break;
        }
    }

    cout << "Based on " << strategy << endl;
    cout << "Items taken (value,fraction): { ";
    for (auto &p : proportions) {
        cout << "(" << p.first << ", " << fixed << setprecision(2) << p.second << ") ";
    }
    cout << "} \nTotal Value: " << fixed << setprecision(2) << totalValue << "\n\n";

    return totalValue;
}

int main() {
    int n, capacity;
    cout << "Enter number of items: ";
    cin >> n;

    vector<Item> items(n);
    cout << "Enter values and weights of the items:\n";
    for (int i = 0; i < n; i++) {
        cin >> items[i].value >> items[i].weight;
        items[i].ratio = (double)items[i].value / items[i].weight;
    }

    cout << "Enter knapsack capacity: ";
    cin >> capacity;

    double value1 = fractionalKnapsack(items, capacity, "profit");
    double value2 = fractionalKnapsack(items, capacity, "weight");
    double value3 = fractionalKnapsack(items, capacity, "profit/weight");

    cout << "Optimal solution is given by ";
    if (value1 >= value2 && value1 >= value3)
        cout << "Profit Strategy \n";
    else if (value2 >= value1 && value2 >= value3)
        cout << "Based on Weight Strategy \n";
    else
        cout << "Based on Profit/Weight Strategy \n";

    return 0;
}