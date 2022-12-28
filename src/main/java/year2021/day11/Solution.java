package year2021.day11;

import common.Day;
import common.Util;

import java.io.IOException;

public class Solution extends Day {
    public Solution() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }

    @Override
    public String part1() {
        int times = 100;
        int flashes = 0;
        int[][] energyLevels = Util.createIntGrid(input);

        for (int i = 0; i < times; i++) {
            flashes += cycle(energyLevels);
        }

        return Integer.toString(flashes);
    }

    @Override
    public String part2() {
        int cycles = 0;
        int[][] energyLevels = Util.createIntGrid(input);

        while (!allSame(energyLevels)) {
            cycle(energyLevels);
            cycles++;
        }

        return Integer.toString(cycles);
    }

    private int cycle(int[][] energyLevels) {
        int flashes = 0;

        for (int j = 0; j < energyLevels.length; j++) {
            for (int k = 0; k < energyLevels[0].length; k++) {
                energyLevels[j][k]++;
            }
        }


        for (int j = 0; j < energyLevels.length; j++) {
            for (int k = 0; k < energyLevels[0].length; k++) {
                if (energyLevels[j][k] > 9) {
                    flashes += increaseEnergyLevels(energyLevels, j, k);
                }
            }
        }

        return flashes;
    }

    private boolean allSame(int[][] energyLevels) {
        int level = energyLevels[0][0];

        for (int[] row : energyLevels) {
            for (int value : row) {
                if (value != level) return false;
            }
        }

        return true;
    }

    private int increaseEnergyLevels(int[][] energyLevels, int x, int y) {
        int flashes = 1;
        energyLevels[x][y] = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    try {
                        if (energyLevels[x + i][y + j] > 0) {
                            energyLevels[x + i][y + j]++;

                            if (energyLevels[x + i][y + j] > 9) {
                                flashes += increaseEnergyLevels(energyLevels, x + i, y + j);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }

        return flashes;
    }
}
