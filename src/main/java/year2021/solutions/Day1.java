package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.List;

public class Day1 extends Day {
    public Day1() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int increased = 0;
        int[] values = Day.createIntArray(input);

        for (int i = 1; i < input.size(); i++) {
            if (values[i] > values[i - 1]) {
                increased++;
            }
        }
        return Integer.toString(increased);
    }

    @Override
    public String part2() {
        int increased = 0;

        int[] values = Day.createIntArray(input);

        int previous = values[0] + values[1] + values[2];

        for (int i = 3; i < values.length; i++) {
            int current = values[i] + values[i - 1] + values[i - 2];

            if (current > previous) {
                increased++;
            }

            previous = current;
        }

        return Integer.toString(increased);
    }

    public static void main(String[] args) throws IOException {
        new Day1();
    }
}
