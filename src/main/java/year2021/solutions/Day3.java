package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day {
    private static final boolean isTest = false;

    public Day3() throws IOException {
        super(isTest);
    }

    @Override
    public String part1(List<String> input) {
        String gamma = "";
        String epsilon = "";

        int size = input.get(0).length();

        for (int i = 0; i < size; i++) {
            int zeros = getZeros(input, i);
            int ones = input.size() - zeros;

            if (zeros > ones) {
                gamma += '0';
                epsilon += '1';
            } else {
                gamma += '1';
                epsilon += '0';
            }
        }

        int gammaRate = Integer.parseInt(gamma, 2);
        int epsilonRate = Integer.parseInt(epsilon, 2);

        return Integer.toString(gammaRate * epsilonRate);
    }

    @Override
    public String part2(List<String> input) {
        List<String> copy = new ArrayList<>(input);

        int size = copy.get(0).length();
        String oxygen = calculateValue(copy, true);
        copy = new ArrayList<>(input);
        String co2 = calculateValue(copy, false);

        int oxygenRating = Integer.parseInt(oxygen, 2);
        int co2Rating = Integer.parseInt(co2, 2);

        return Integer.toString(oxygenRating * co2Rating);
    }

    public static int getZeros(List<String> input, int position) {
        return (int) input.stream().filter(item -> item.charAt(position) == '0').count();
    }

    public static String calculateValue(List<String> input, boolean mostCommon) {
        int size = input.get(0).length();

        for (int i = 0; i < size; i++) {
            int zeros = getZeros(input, i);
            int ones = input.size() - zeros;

            if (mostCommon) {
                input = filter(input, zeros, ones, i, '1');
            } else {
                input = filter(input, ones, zeros, i, '0');
            }

            if (input.size() == 1) {
                return input.get(0);
            }
        }

        return null;
    }

    public static List<String> filter(List<String> input, int numbers1, int numbers2, int index, char ifEqual) {
        if (numbers1 > numbers2) {
            return input.stream().filter(item -> item.charAt(index) == '0').collect(Collectors.toList());
        } else if (numbers1 == numbers2) {
            return input.stream().filter(item -> item.charAt(index) == ifEqual).collect(Collectors.toList());
        } else {
            return input.stream().filter(item -> item.charAt(index) == '1').collect(Collectors.toList());
        }
    }

    public static void main(String[] args) throws IOException {
        new Day3();
    }
}