package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day7 extends Day {
    private static final boolean isTest = false;
    private int[] positions;
    int min;
    int max;
    int[] fuels;

    public Day7() throws IOException {
        super(isTest);
    }

    @Override
    public void setup() {
        positions = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        min = Arrays.stream(positions).min().getAsInt();
        max = Arrays.stream(positions).max().getAsInt();
        fuels = new int[max - min];
    }

    @Override
    public String part1() {
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
    public String part2() {
        for (int i = min; i < max; i++) {
            int sum = 0;

            for (int position : positions) {
                int difference = Math.abs(position - i);
                sum += (difference*(difference + 1))/2;
            }

            fuels[i] = sum;
        }

        return Integer.toString(Arrays.stream(fuels).min().getAsInt());
    }

    public static void main(String[] args) throws IOException {
        new Day7();
    }
}
