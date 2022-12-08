package year2022.solutions;

import common.Day;
import common.Util;

import java.io.IOException;

public class Day8 extends Day {
    int[][] grid;

    public Day8() throws IOException {
    }

    @Override
    public void setup() {
        grid = Util.createIntGrid(input);
    }

    private boolean isVisible(int x, int y, int[][] grid) {
        int maxHeight = grid[x][y] - 1;
        for (int i = x + 1; i < grid.length; i++) {
            if (grid[i][y] > maxHeight) {
                break;
            } else if (i == grid.length - 1) {
                return true;
            }
        }

        for (int i = x - 1; i >= 0 ; i--) {
            if (grid[i][y] > maxHeight) {
                break;
            } else if (i == 0) {
                return true;
            }
        }

        for (int i = y + 1; i < grid[0].length; i++) {
            if (grid[x][i] > maxHeight) {
                break;
            } else if (i == grid[0].length - 1) {
                return true;
            }
        }

        for (int i = y - 1; i >= 0 ; i--) {
            if (grid[x][i] > maxHeight) {
                break;
            } else if (i == 0) {
                return true;
            }
        }

        return false;
    }

    private int calculateScenicScore(int x, int y, int[][] grid) {
        int height = grid[x][y];
        int totalScore = 1;
        int scenicScore = 0;

        for (int i = x + 1; i < grid.length; i++) {
            scenicScore++;
            if (grid[i][y] >= height) {
                break;
            }
        }
        totalScore *= scenicScore;
        scenicScore = 0;

        for (int i = x - 1; i >= 0 ; i--) {
            scenicScore++;
            if (grid[i][y] >= height) {
                break;
            }
        }
        totalScore *= scenicScore;
        scenicScore = 0;

        for (int i = y + 1; i < grid[0].length; i++) {
            scenicScore++;
            if (grid[x][i] >= height) {
                break;
            }
        }
        totalScore *= scenicScore;
        scenicScore = 0;

        for (int i = y - 1; i >= 0 ; i--) {
            scenicScore++;
            if (grid[x][i] >= height) {
                break;
            }
        }
        totalScore *= scenicScore;

        return totalScore;
    }

    @Override
    public String part1() {
        int visibleTrees = 2 * grid.length + 2 * grid[0].length - 4;

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (isVisible(i, j, grid)) {
                    visibleTrees++;
                }
            }
        }
        return Integer.toString(visibleTrees);
    }

    @Override
    public String part2() {
        int maxScenicScore = 0;

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                maxScenicScore = Math.max(calculateScenicScore(i, j, grid), maxScenicScore);
            }
        }

        return Integer.toString(maxScenicScore);
    }

    public static void main(String[] args) throws IOException {
        isTest = false;
        new Day8();
    }
}
