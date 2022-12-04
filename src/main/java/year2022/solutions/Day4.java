package year2022.solutions;

import common.Day;

import java.io.IOException;

public class Day4 extends Day {
    private static final boolean isTest = false;

    public Day4() throws IOException {
        super(isTest);
    }

    private int[][] getPairs(String line) {
        String[] halves = line.split("[,-]");
        int[][] pairs = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                pairs[i][j] = Integer.parseInt(halves[i * 2 + j]);
            }
        }

        return pairs;
    }

    @Override
    public String part1() {
        int containPairs = 0;

        for (String line : input) {
            int[][] pairs = getPairs(line);

            if ((pairs[0][0] <= pairs[1][0] && pairs[0][1] >= pairs[1][1]) ||
                    (pairs[0][0] >= pairs[1][0] && pairs[0][1] <= pairs[1][1])) {
                containPairs++;
            }
        }
        return Integer.toString(containPairs);
    }

    @Override
    public String part2() {
        int overlaps = 0;

        for (String line : input) {
            int[][] pairs = getPairs(line);

            if ((pairs[0][1] >= pairs[1][0] && pairs[0][1] <= pairs[1][1]) ||
                    (pairs[1][1] >= pairs[0][0] && pairs[1][1] <= pairs[0][1])) {
                overlaps++;
            }
        }

        return Integer.toString(overlaps);
    }

    public static void main(String[] args) throws IOException {
        new Day4();
    }
}
