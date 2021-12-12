package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Day10 extends Day {
    private static final boolean isTest = true;
    private List<Character> openCharacters;
    private List<Character> closingCharacters;

    public Day10() throws IOException {
        super(isTest);
    }

    @Override
    public void setup() {
        openCharacters = Arrays.asList('(', '[', '{', '<');
        closingCharacters = Arrays.asList(')', ']', '}', '>');
    }

    @Override
    public String part1() {
        Map<Character, Integer> closingScores;
        closingScores = new HashMap<>();
        closingScores.put(')', 3);
        closingScores.put(']', 57);
        closingScores.put('}', 1197);
        closingScores.put('>', 25137);

        int score = 0;

        for (String line : input) {
            List<Character> expected = new ArrayList<>();

            for (char character : line.toCharArray()) {
                int index = openCharacters.indexOf(character);

                if (index >= 0) {
                    expected.add(closingCharacters.get(index));
                } else {
                    if (character != expected.remove(expected.size() - 1)) {
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
        Map<Character, Integer> scores = new HashMap<>();

        scores.put(')', 1);
        scores.put(']', 2);
        scores.put('}', 3);
        scores.put('>', 4);

        List<Long> closingScores = new ArrayList<>();

        for (String line : input) {
            List<Character> expected = new ArrayList<>();
            boolean isIncomplete = true;

            for (char character : line.toCharArray()) {
                int index = openCharacters.indexOf(character);

                if (index >= 0) {
                    expected.add(closingCharacters.get(index));
                } else {
                    if (character != expected.remove(expected.size() - 1)) {
                        isIncomplete = false;
                        break;
                    }
                }
            }

            if (isIncomplete) {
                long score = 0;
                Collections.reverse(expected);

                for (char character : expected) {
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
