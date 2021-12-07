package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day7 extends Day {
    private static final boolean isTest = false;

    public Day7() throws IOException {
        super(isTest);
    }

    @Override
    public String part1(List<String> input) {
        int[] positions = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        int min = Arrays.stream(positions).min().getAsInt();
        int max = Arrays.stream(positions).max().getAsInt();
        int[] fuels = new int[max - min];

        for (int i = min; i < max; i++) {
            int sum = 0;

            for (int position : positions) {
                sum += Math.abs(position - i);
            }

            fuels[i] = sum;
        }

        return Integer.toString(Arrays.stream(fuels).min().getAsInt());
    }

    @Override
    public String part2(List<String> input) {
        int[] positions = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        int min = Arrays.stream(positions).min().getAsInt();
        int max = Arrays.stream(positions).max().getAsInt();
        int[] fuels = new int[max - min];

        for (int i = min; i < max; i++) {
            int sum = 0;

            for (int j = 0; j < positions.length; j++) {
                int difference = Math.abs(positions[j] - i);
                int fuel = 0;

                for (int k = 0; k <= difference; k++) {
                    fuel += k;
                }

                sum += fuel;
            }

            fuels[i] = sum;
        }

        return Integer.toString(Arrays.stream(fuels).min().getAsInt());
    }

    public static void main(String[] args) throws IOException {
        new Day7();
    }
}
