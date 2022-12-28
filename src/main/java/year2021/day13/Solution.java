package year2021.day13;

import common.Day;
import common.Util;

import java.io.IOException;
import java.util.*;

public class Solution extends Day {
    private boolean[][] grid;
    private List<Instruction> instructions;

    public Solution() throws IOException {
        super();
    }

    @Override
    public void setup() {
        List<int[]> coordinates = new ArrayList<>();

        int maxX = 0;
        int maxY = 0;
        int i;

        for (i = 0; !input.get(i).isEmpty(); i++) {
            String[] line = input.get(i).split(",");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);

            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;

            coordinates.add(new int[]{x, y});
        }

        grid = new boolean[maxY + 1][maxX + 1];

        for (int[] coordinate : coordinates) {
            grid[coordinate[1]][coordinate[0]] = true;
        }

        instructions = new ArrayList<>();
        i++;

        for (; i < input.size(); i++) {
            String[] instruction = input.get(i).split(" ")[2].split("=");
            instructions.add(new Instruction(instruction[0], Integer.parseInt(instruction[1])));
        }
    }

    @Override
    public String part1() {
        return Integer.toString(Util.countBooleans(calculateResult(true), true));
    }

    @Override
    public String part2() {
        boolean[][] grid = calculateResult(false);
        System.out.println(Util.gridToString(grid, "\u001B[43m  \u001B[0m", "  "));

        return null;
    }

    private boolean[][] calculateResult(boolean isPart1) {
        boolean[][] grid = this.grid;

        for (Instruction instruction : instructions) {
            int sizeX = grid[0].length;
            int sizeY = grid.length;
            int offset = instruction.position;
            boolean[][] newGrid;

            if (instruction.direction.equals("x")) {
                sizeX = Math.max(offset, sizeX - offset - 1);

                newGrid = new boolean[sizeY][sizeX];

                for (int i = 1; i < grid[0].length - offset; i++) {
                    for (int j = 0; j < sizeY; j++) {
                        newGrid[j][offset - i] = grid[j][offset - i];

                        if (grid[j][offset + i]) {
                            newGrid[j][offset - i] = true;
                        }
                    }
                }

            } else {
                sizeY = Math.max(offset, sizeY - offset - 1);

                newGrid = new boolean[sizeY][sizeX];

                for (int i = 1; i < grid.length - offset; i++) {
                    for (int j = 0; j < sizeX; j++) {
                        newGrid[offset - i][j] = grid[offset - i][j];

                        if (grid[offset + i][j]) {
                            newGrid[offset - i][j] = true;
                        }
                    }
                }

            }
            grid = newGrid;
            if (isPart1) break;
        }

        return grid;
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }

    private class Instruction {
        String direction;
        int position;

        Instruction(String direction, int position) {
            this.direction = direction;
            this.position = position;
        }
    }
}
