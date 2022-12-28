package year2021.day1;

import common.Day;
import common.Util;

import java.io.IOException;

public class Solution extends Day {
    public Solution() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int increased = 0;
        int[] values = Util.createIntArray(input);

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

        int[] values = Util.createIntArray(input);

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
        new Solution();
    }
}
