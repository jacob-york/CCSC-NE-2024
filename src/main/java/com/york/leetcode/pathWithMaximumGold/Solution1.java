package com.york.leetcode.pathWithMaximumGold;

/**
 * <a href="https://leetcode.com/problems/path-with-maximum-gold/">...</a>
 * leetcode: medium
 *
 * @author Jacob York
 */
class Solution1 {
    int maxGold = 0;
    public int getMaximumGold(int[][] grid) {

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 0) continue;
                backTrack(grid, x, y, 0);
            }
        }
        return maxGold;
    }
    private int backTrack(int[][] grid, int x, int y, int startGold) {
        if (y < 0 || y >= grid.length) return 0;
        if (x < 0 || x >= grid[0].length) return 0;
        if (grid[y][x] == 0) return 0;

        int curGold = grid[y][x];
        grid[y][x] = 0;
        int validPaths = 0;
        validPaths += backTrack(grid, x - 1, y, curGold + startGold);
        validPaths += backTrack(grid, x, y - 1, curGold + startGold);
        validPaths += backTrack(grid, x + 1, y, curGold + startGold);
        validPaths += backTrack(grid, x, y + 1, curGold + startGold);
        if (validPaths == 0) maxGold = Math.max(maxGold, curGold + startGold);
        grid[y][x] = curGold;

        return 1;
    }
}
