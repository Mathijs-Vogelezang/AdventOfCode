package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String oxygen = "";
        String co2 = "";

        for (int i = 0; i < size; i++) {
            int zeros = getZeros(copy, i);
            int ones = copy.size() - zeros;


            for (int j = 0; j < copy.size(); j++) {
                if (ones >= zeros) {
                    if (copy.get(j).charAt(i) == '0') {
                        copy.remove(j);
                        j--;
                    }
                } else {
                    if (copy.get(j).charAt(i) == '1') {
                        copy.remove(j);
                        j--;
                    }
                }

                if (copy.size() == 1) {
                    oxygen = copy.get(0);
                    i = size;
                    break;
                }
            }
        }

        copy = new ArrayList<>(input);

        for (int i = 0; i < size; i++) {
            int zeros = getZeros(copy, i);
            int ones = copy.size() - zeros;

            for (int j = 0; j < copy.size(); j++) {
                if (zeros > ones) {
                    if (copy.get(j).charAt(i) == '0') {
                        copy.remove(j);
                        j--;
                    }
                } else {
                    if (copy.get(j).charAt(i) == '1') {
                        copy.remove(j);
                        j--;
                    }
                }

                if (copy.size() == 1) {
                    co2 = copy.get(0);
                    i = size;
                    break;
                }

            }
        }

        int oxygenRating = Integer.parseInt(oxygen, 2);
        int co2Rating = Integer.parseInt(co2, 2);

        return Integer.toString(oxygenRating * co2Rating);
    }

    public static int getZeros(List<String> input, int position) {
        int zeros = 0;

        for (String line : input) {
            if (line.charAt(position) == '0') {
                zeros++;
            }
        }

        return zeros;
    }

    public static void main(String[] args) throws IOException {
        new Day3();
    }
}
