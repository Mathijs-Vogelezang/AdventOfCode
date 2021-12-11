package common;

import java.util.List;

public class Util {
    public static boolean containsLetters(String toCheck, String checker) {
        for (String character : checker.split("")) {
            if (!toCheck.contains(character)) return false;
        }

        return true;
    }

    public static boolean containsLetters(String toCheck, String checker, int minimum) {
        int amount = 0;

        for (String character : checker.split("")) {
            if (toCheck.contains(character)) amount++;
        }

        return amount >= minimum;
    }

    public static int[][] createIntGrid (List<String> input) {
        int[][] grid = new int[input.size()][input.get(0).length()];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = Integer.parseInt(Character.toString(input.get(i).charAt(j)));
            }
        }

        return grid;
    }

    public static String gridToString(int[][] grid) {
        StringBuilder builder = new StringBuilder();

        for (int[] row : grid) {
            for (int value : row) {
                builder.append(value);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
