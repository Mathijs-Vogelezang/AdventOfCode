package year2021.day2;

import common.Day;

import java.io.IOException;

public class Solution extends Day {
    public Solution() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int depth = 0;
        int horizontal = 0;

        for (String command : input) {
            String[] commandParts = command.split(" ");
            String action = commandParts[0];
            int amount = Integer.parseInt(commandParts[1]);

            switch (action) {
                case "forward" -> horizontal += amount;
                case "down" -> depth += amount;
                case "up" -> depth -= amount;
            }
        }

        return Integer.toString(depth * horizontal);
    }

    @Override
    public String part2() {
        int depth = 0;
        int horizontal = 0;
        int aim = 0;

        for (String command : input) {
            String[] commandParts = command.split(" ");
            String action = commandParts[0];
            int amount = Integer.parseInt(commandParts[1]);

            switch (action) {
                case "forward" -> {
                    horizontal += amount;
                    depth += aim * amount;
                }
                case "down" -> aim += amount;
                case "up" -> aim -= amount;
            }
        }

        return Integer.toString(depth * horizontal);
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
