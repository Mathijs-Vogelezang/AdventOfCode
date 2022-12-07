package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day24 extends Day {
    public Day24() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int[] model = new int[14];
        Arrays.fill(model, 9);

        while (true) {
            Map<String, Integer> memory = new HashMap<>();
            int pointer = 0;

            for (String instruction : input) {
                String[] parts = instruction.split(" ");

                switch (parts[0]) {
                    case "inp" -> {
                        memory.put(parts[1], model[pointer]);
                        pointer++;
                    }
                    case "add" -> {
                        int result;

                        try {
                            result = memory.get(parts[1]) + Integer.parseInt(parts[2]);
                        } catch (NumberFormatException e) {
                            result = memory.get(parts[1]) + memory.get(parts[2]);
                        }

                        memory.put(parts[1], result);
                    }
                    case "mul" -> {
                        int result;

                        try {
                            result = memory.get(parts[1]) * Integer.parseInt(parts[2]);
                        } catch (NumberFormatException e) {
                            result = memory.get(parts[1]) * memory.get(parts[2]);
                        }

                        memory.put(parts[1], result);
                    }
                    case "div" -> {
                        int result;

                        try {
                            result = memory.get(parts[1]) / Integer.parseInt(parts[2]);
                        } catch (NumberFormatException e) {
                            result = memory.get(parts[1]) / memory.get(parts[2]);
                        }

                        memory.put(parts[1], result);
                    }
                    case "mod" -> {
                        int result;

                        try {
                            result = memory.get(parts[1]) % Integer.parseInt(parts[2]);
                        } catch (NumberFormatException e) {
                            result = memory.get(parts[1]) % memory.get(parts[2]);
                        }

                        memory.put(parts[1], result);
                    }
                    case "eql" -> {
                        int result;

                        try {
                            result = memory.get(parts[1]) == Integer.parseInt(parts[2]) ? 1 : 0;
                        } catch (NumberFormatException e) {
                            result = memory.get(parts[1]).equals(memory.get(parts[2])) ? 1 : 0;
                        }

                        memory.put(parts[1], result);
                    }
                }
            }

            if (memory.get("z") == 0) {
                StringBuilder result = new StringBuilder();

                for (int number : model) result.append(number);

                return result.toString();
            }

            for (int i = 13; i >= 0; i--) {
                if (model[i] == 1) {
                    model[i] = 9;

                    if (i == 0) {
                        return "does not work";
                    }
                } else {
                    model[i]--;
                    break;
                }
            }
        }

    }

    @Override
    public String part2() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        new Day24();
    }
}
