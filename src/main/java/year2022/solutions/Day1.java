package year2022.solutions;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends Day {
    private static final boolean isTest = false;

    public Day1() throws IOException {
        super(isTest);
    }


    @Override
    public String part1() {
        int maxCalories = 0;
        int calories = 0;

        for (String line : input) {
            if (!line.isBlank()) {
                calories += Integer.parseInt(line);
            } else {
                maxCalories = Math.max(maxCalories, calories);
                calories = 0;
            }
        }
        maxCalories = Math.max(maxCalories, calories);

        return Integer.toString(maxCalories);
    }

    @Override
    public String part2() {
        List<Integer> elfCalories = new ArrayList<>();
        int calories = 0;

        for (String line : input) {
            if (!line.isBlank()) {
                calories += Integer.parseInt(line);
            } else {
                elfCalories.add(calories);
                calories = 0;
            }
        }
        elfCalories.add(calories);

        Collections.sort(elfCalories);
        Collections.reverse(elfCalories);
        System.out.println(elfCalories);

        int topCalories = 0;

        for (int i = 0; i < 3; i++) {
            topCalories += elfCalories.get(i);
        }

        return Integer.toString(topCalories);
    }

    public static void main(String[] args) throws IOException {
        new Day1();
    }
}
