class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        // memo[i][j]: minimum health required, starting from (i, j), leaving the bottom right with positive health
        Integer[][] memo = new Integer[m][n];

        return dp(dungeon, 0, 0, memo);
        // for (int i = 0; i < m; i++)
        //     for (int j = 0; j < n; j++)
        //         System.out.printf("(%d, %d): %d\n", i, j, memo[i][j]);
    }

    private int dp(int[][] dungeon, int i, int j, Integer[][] memo) {
        int m = dungeon.length, n = dungeon[0].length;

        if (i == m - 1 && j ==  n - 1) {
            memo[i][j] = dungeon[i][j] < 0 ? -dungeon[i][j] + 1 : 1;
            return memo[i][j];
        }

        if (memo[i][j] != null)
            return memo[i][j];

        int minHealth = Integer.MAX_VALUE, health = 0;
        // memo[i][j] + dungeon[i + 1][j] or dungeon[i][j + 1] >= memo[i + 1][j] or memo[i][j + 1]
        // down
        if (i + 1 < m) {
            health = Math.max(dp(dungeon, i + 1, j, memo) - dungeon[i][j], 1);
            minHealth = Math.min(minHealth, health);
        }
        // right
        if (j + 1 < n) {
            health = Math.max(dp(dungeon, i, j + 1, memo) - dungeon[i][j], 1);
            minHealth = Math.min(minHealth, health);
        }
        memo[i][j] = minHealth;

        return memo[i][j];
    }
}