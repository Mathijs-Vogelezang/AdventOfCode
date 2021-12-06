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
        List<Integer> days = createIntegerList(input.get(0));

        return Long.toString(calculateFish(80, days));
    }

    @Override
    public String part2(List<String> input) {
        List<Integer> days = createIntegerList(input.get(0));

        return Long.toString(calculateFish(256, days));
    }

    private long calculateFish(int days, List<Integer> input) {
        long[] daysAndFishes = new long[9];

        for (Integer amountOfDays : input) {
            daysAndFishes[amountOfDays]++;
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

    public static List<Integer> createIntegerList(String input) {
        String[] numbers = input.split(",");
        List<Integer> result = new ArrayList<>();

        for (String number: numbers) {
            result.add(Integer.parseInt(number));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        new Day6();
    }
}
