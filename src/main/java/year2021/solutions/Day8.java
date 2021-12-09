package year2021.solutions;

import common.Day;
import common.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day8 extends Day {
    private static final boolean isTest = false;

    public Day8() throws IOException {
        super(isTest);
    }

    public static void main(String[] args) throws IOException {
        new Day8();
    }

    public static String sortString(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Override
    public String part1() {
        int counter = 0;

        for (String line : input) {
            for (String output : line.split("\\|")[1].trim().split(" ")) {
                switch (output.length()) {
                    case 2, 3, 4, 7 -> counter++;
                }
            }
        }

        return Integer.toString(counter);
    }

    @Override
    public String part2() {
        int sum = 0;

        for (String line : input) {
            String[] parts = line.split(" \\| ");
            Map<String, Integer> mappings = calculateMappings(parts[0].split(" "));

            String[] digits = parts[1].split(" ");

            for (int i = 0; i < digits.length; i++) {
                sum += mappings.get(sortString(digits[i])) * Math.pow(10, 3 - i);
            }
        }

        return Integer.toString(sum);
    }

    public Map<String, Integer> calculateMappings(String[] digits) {
        Map<String, Integer> segmentMap = new HashMap<>();

        for (String digit : digits) {
            switch (digit.length()) {
                case 2 -> segmentMap.put(sortString(digit), 1);
                case 3 -> segmentMap.put(sortString(digit), 7);
                case 4 -> segmentMap.put(sortString(digit), 4);
                case 7 -> segmentMap.put(sortString(digit), 8);
            }
        }

        for (String digit : digits) {
            String sortedLetters = sortString(digit);

            if (segmentMap.containsKey(sortedLetters)) continue;

            if (digit.length() == 6) {
                boolean contains4 = Util.containsLetters(sortedLetters, getKeyFromValue(segmentMap, 4));
                boolean contains7 = Util.containsLetters(sortedLetters, getKeyFromValue(segmentMap, 7));

                if (contains4) {
                    segmentMap.put(sortedLetters, 9);
                } else if (contains7) {
                    segmentMap.put(sortedLetters, 0);
                } else {
                    segmentMap.put(sortedLetters, 6);
                }
            } else if (digit.length() == 5) {
                boolean contains4 = Util.containsLetters(sortedLetters, getKeyFromValue(segmentMap, 4), 3);
                boolean contains7 = Util.containsLetters(sortedLetters, getKeyFromValue(segmentMap, 7));

                if (contains7) {
                    segmentMap.put(sortedLetters, 3);
                } else if (contains4) {
                    segmentMap.put(sortedLetters, 5);
                } else {
                    segmentMap.put(sortedLetters, 2);
                }
            }
        }
        return segmentMap;
    }

    public String getKeyFromValue(Map<String, Integer> map, int value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }

        return null;
    }
}
