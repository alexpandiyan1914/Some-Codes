#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
void greedyMinCoins(vector<int>& coins, int sum) {
    sort(coins.rbegin(), coins.rend()); 
    vector<int> result;
    int count = 0;
    for (int coin : coins) {
        while (sum >= coin) { 
            sum -= coin;
            result.push_back(coin);
            count++;
        }
    }
    if (sum != 0) {
        cout << "Greedy approach failed: No solution possible.\n";
        return;
    }
    cout << "Min. No. of coins (Greedy) = " << count << endl;
    cout << "Coins used = ";
    for (int c : result) {
        cout << c << " ";
    }
    cout << endl;
}
int main() {
    int n, sum;
    cout << "Enter number of coins: ";
    cin >> n;
    vector<int> coins(n);
    cout << "Enter the coin denominations: ";
    for (int i = 0; i < n; i++) {
        cin >> coins[i];
    }
    cout << "Enter the target sum: ";
    cin >> sum;
    greedyMinCoins(coins, sum);
    return 0;
}
