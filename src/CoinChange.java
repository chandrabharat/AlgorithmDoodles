import java.util.ArrayList;

class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] subAmounts = new int[amount  + 1];
        return coinChange(coins, amount, subAmounts);
    }

    public int coinChange(int[] coins, int amount, int[] subAmounts) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (subAmounts[amount] != 0) {
            return subAmounts[amount];
        }
        int minAmountCoins = Integer.MAX_VALUE;
        for (int i = 0; i < amount; i++) {
            int res = coinChange(coins, amount - coins[i], subAmounts);
            if (res > -1 && res < minAmountCoins) {
                minAmountCoins = 1 + res;
            }
        }
        subAmounts[amount] = minAmountCoins != Integer.MAX_VALUE ? minAmountCoins:-1;
        return subAmounts[amount];
    }
}