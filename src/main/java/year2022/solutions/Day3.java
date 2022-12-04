package year2022.solutions;

import common.Day;

import java.io.IOException;

public class Day3 extends Day {
    private static final boolean isTest = false;

    public Day3() throws IOException {
        super(isTest);
    }

    @Override
    public String part1() {
        int score = 0;

        for (String line : input) {
            String half1 = line.substring(0, line.length()/2);
            String half2 = line.substring(line.length()/2);
            char common = '0';

            for (int i = 0; i < half1.length(); i++) {
                String itemType = String.valueOf(half1.charAt(i));
                if (half2.contains(itemType)) {
                    common = itemType.charAt(0);
                    break;
                }
            }

            if (Character.isUpperCase(common)) {
                score += common - 38;
            } else {
                score += common - 96;
            }
        }
        return Integer.toString(score);
    }

    @Override
    public String part2() {
        int score = 0;

        for (int i = 0; i < input.size(); i += 3) {
            String line1 = input.get(i);
            String line2 = input.get(i + 1);
            String line3 = input.get(i + 2);
            char common = '0';

            for (int j = 0; j < line1.length(); j++) {
                String itemType = String.valueOf(line1.charAt(j));

                if (line2.contains(itemType) && line3.contains(itemType)) {
                    common = itemType.charAt(0);
                    break;
                }
            }

            if (Character.isUpperCase(common)) {
                score += common - 38;
            } else {
                score += common - 96;
            }
        }

        return Integer.toString(score);
    }

    public static void main(String[] args) throws IOException {
        new Day3();
    }
}