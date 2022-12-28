package year2021.day9;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution extends Day {
    private int[][] heightmap;
    private boolean[][] isBasin;

    public Solution() throws IOException {
        super();
    }

    @Override
    public void setup() {
        heightmap = new int[input.size()][input.get(0).length()];
        isBasin = new boolean[input.size()][input.get(0).length()];

        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[i].length; j++) {
                heightmap[i][j] = Integer.parseInt(Character.toString(input.get(i).charAt(j)));
            }
        }
    }

    @Override
    public String part1() {
        int sum = 0;

        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                int depth = heightmap[i][j];
                boolean isLower = true;

                try {
                    if (heightmap[i][j + 1] <= depth) {
                        isLower = false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }

                try {
                    if (heightmap[i][j - 1] <= depth) {
                        isLower = false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }

                try {
                    if (heightmap[i - 1][j] <= depth) {
                        isLower = false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }

                try {
                    if (heightmap[i + 1][j] <= depth) {
                        isLower = false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }

                if (isLower) {
                    sum += depth + 1;
                    isBasin[i][j] = true;
                }
            }

        }
        return Integer.toString(sum);
    }

    @Override
    public String part2() {
        List<Integer> basinSizes = new ArrayList<>();
        for (int i = 0; i < isBasin.length; i++) {
            for (int j = 0; j < isBasin[0].length; j++) {
                boolean[][] basin = new boolean[isBasin.length][isBasin[0].length];

                if (isBasin[i][j]) {
                    basin[i][j] = true;
                    basin = calculateBasin(basin, i, j);
                }
                int size = 0;

                for (boolean[] row : basin) {
                    for (boolean partOfBasin : row) {
                        if (partOfBasin) {
                            size++;
                        }
                    }
                }

                basinSizes.add(size);
            }

        }

        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        return Integer.toString(basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2));
    }

    public boolean[][] calculateBasin(boolean[][] input, int i, int j) {
        try {
            if (heightmap[i - 1][j] != 9 && heightmap[i - 1][j] > heightmap[i][j]) {
                input[i - 1][j] = true;
                input = calculateBasin(input, i - 1, j);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            if (heightmap[i + 1][j] != 9 && heightmap[i + 1][j] > heightmap[i][j]) {
                input[i + 1][j] = true;
                input = calculateBasin(input, i + 1, j);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            if (heightmap[i][j - 1] != 9 && heightmap[i][j - 1] > heightmap[i][j]) {
                input[i][j - 1] = true;
                input = calculateBasin(input, i, j - 1);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            if (heightmap[i][j + 1] != 9 && heightmap[i][j + 1] > heightmap[i][j]) {
                input[i][j + 1] = true;
                input = calculateBasin(input, i, j + 1);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        return input;
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
