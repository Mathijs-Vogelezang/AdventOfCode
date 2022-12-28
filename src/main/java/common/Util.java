package common;

import java.util.Arrays;
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

    public static int[] createIntArray(List<String> input) {
        int[] result = new int[input.size()];

        for (int i = 0; i < input.size(); i++) {
            result[i] = Integer.parseInt(input.get(i));
        }

        return result;
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

    public static int countBooleans (boolean[][] input, boolean toCheck) {
        int result = 0;

        for (boolean[] row : input) {
            for (boolean value : row) {
                if (value == toCheck) result++;
            }
        }

        return result;
    }

    public static String gridToString(boolean[][] grid, String forTrue, String forFalse) {
        StringBuilder builder = new StringBuilder();

        for (boolean[] row : grid) {
            for (boolean value : row) {
                if (value) {
                    builder.append(forTrue);
                } else {
                    builder.append(forFalse);
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public static String gridToString (boolean[][] grid) {
        return gridToString(grid, "#", ".");
    }

    public static boolean uniqueCharacters(String line) {
        for (int i = 0; i < line.length(); i++) {
            for (int j = i + 1; j < line.length(); j++) {
                if (line.charAt(i) == line.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] createIntGridFromChars(List<String> input, char offset) {
        int[][] grid = new int[input.size()][input.get(0).length()];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = input.get(i).charAt(j) - offset;
            }
            System.out.println(Arrays.toString(grid[i]));
        }

        return grid;
    }

    public static char[][] createCharGrid(List<String> input) {
        char[][] grid = new char[input.size()][input.get(0).length()];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = input.get(i).charAt(j);
            }
        }
        return grid;
    }
}
