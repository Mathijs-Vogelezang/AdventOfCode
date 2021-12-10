package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Day10 extends Day {
    private static final boolean isTest = false;
    private List<String> openCharacters;
    private List<String> closingCharacters;
    private Map<String, Integer> closingScores;

    public Day10() throws IOException {
        super(isTest);
    }

    @Override
    public void setup() {
        openCharacters = Arrays.asList("(", "[", "{", "<");
        closingCharacters = Arrays.asList(")", "]", "}", ">");

        closingScores = new HashMap<>();
        closingScores.put(")", 3);
        closingScores.put("]", 57);
        closingScores.put("}", 1197);
        closingScores.put(">", 25137);
    }

    @Override
    public String part1() {
        int score = 0;

        for (String line : input) {
            List<String> expected = new ArrayList<>();

            for (String character : line.split("")) {
                int index = openCharacters.indexOf(character);

                if (index >= 0) {
                    expected.add(closingCharacters.get(index));
                } else {
                    if (!character.equals(expected.remove(expected.size() - 1))) {
                        score += closingScores.get(character);
                        break;
                    }
                }
            }
        }
        return Integer.toString(score);
    }

    @Override
    public String part2() {
        Map<String, Integer> scores = new HashMap<>();

        scores.put(")", 1);
        scores.put("]", 2);
        scores.put("}", 3);
        scores.put(">", 4);

        List<Long> closingScores = new ArrayList<>();

        for (String line : input) {
            List<String> expected = new ArrayList<>();
            boolean isIncomplete = true;

            for (String character : line.split("")) {
                int index = openCharacters.indexOf(character);

                if (index >= 0) {
                    expected.add(closingCharacters.get(index));
                } else {
                    if (!character.equals(expected.remove(expected.size() - 1))) {
                        isIncomplete = false;
                        break;
                    }
                }
            }

            if (isIncomplete) {
                long score = 0;
                Collections.reverse(expected);

                for (String character : expected) {
                    score *= 5;
                    score += scores.get(character);
                }

                closingScores.add(score);
            }
        }

        Collections.sort(closingScores);
        return Long.toString(closingScores.get(closingScores.size() / 2));
    }

    public static void main(String[] args) throws IOException {
        new Day10();
    }
}
