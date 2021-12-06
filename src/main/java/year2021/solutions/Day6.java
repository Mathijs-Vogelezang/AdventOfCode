package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Day6 extends Day {
    private static final boolean isTest = false;

    public Day6() throws IOException {
        super(isTest);
    }

    @Override
    public String part1(List<String> input) {
        return Long.toString(calculateFish(80, input.get(0)));
    }

    @Override
    public String part2(List<String> input) {
        return Long.toString(calculateFish(256, input.get(0)));
    }

    private long calculateFish(int days, String input) {
        long[] daysAndFishes = new long[9];

        for (String amountOfDays : input.split(",")) {
            daysAndFishes[Integer.parseInt(amountOfDays)]++;
        }

        for (int i = 0; i < days; i++) {
            long zeroDays = daysAndFishes[0];

            for (int j = 1; j < daysAndFishes.length; j++) {
                daysAndFishes[j - 1] = daysAndFishes[j];
            }

            daysAndFishes[8] = zeroDays;
            daysAndFishes[6] += zeroDays;
        }

        return Arrays.stream(daysAndFishes).sum();
    }

    public static void main(String[] args) throws IOException {
        new Day6();
    }
}
