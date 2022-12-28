package year2021.day4;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution extends Day {
    public Solution() throws IOException {
        super();
    }

    public static int[] getNumbers(String numbers, String splitter) {
        String[] numberArray = numbers.split(splitter);

        if (numberArray[0].equals("")) {
            System.arraycopy(numberArray, 1, numberArray, 0, 5);
        }

        return Arrays.stream(numberArray).mapToInt(Integer::parseInt).toArray();
    }

    public static List<int[][]> createBingoCards(List<String> input) {
        List<int[][]> result = new ArrayList<>();

        for (int i = 0; i < input.size(); i += 6) {
            int[][] bingoCard = new int[5][5];

            for (int j = 0; j < 5; j++) {
                bingoCard[j] = getNumbers(input.get(i + j), " +");
            }

            result.add(bingoCard);
        }

        return result;
    }

    public static int calculateResult(int[][] bingoCard, List<Integer> numbers) {
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!numbers.contains(bingoCard[i][j])) {
                    sum += bingoCard[i][j];
                }
            }
        }

        return sum * numbers.get(numbers.size() - 1);
    }

    public static boolean hasBingo(int[][] bingoCard, List<Integer> drawnNumbers) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!drawnNumbers.contains(bingoCard[i][j])) {
                    break;
                } else if (j == 4) {
                    return true;
                }
            }

            for (int j = 0; j < 5; j++) {
                if (!drawnNumbers.contains(bingoCard[j][i])) {
                    break;
                } else if (j == 4) {
                    return true;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }

    @Override
    public String part1() {
        int[] numbers = getNumbers(input.get(0), ",");
        List<int[][]> bingoCards = createBingoCards(input.subList(2, input.size()));

        List<Integer> drawnNumbers = new ArrayList<>();

        for (int number : numbers) {
            drawnNumbers.add(number);
            for (int[][] bingoCard : bingoCards) {
                if (hasBingo(bingoCard, drawnNumbers)) {
                    return Integer.toString(calculateResult(bingoCard, drawnNumbers));
                }
            }
        }

        return null;
    }

    @Override
    public String part2() {
        int[] numbers = getNumbers(input.get(0), ",");
        List<int[][]> bingoCards = createBingoCards(input.subList(2, input.size()));

        List<Integer> drawnNumbers = new ArrayList<>();

        List<int[][]> previousBingos = new ArrayList<>();
        int[][] lastWon = null;
        int lastRound = 0;

        for (int i = 0; i < numbers.length; i++) {
            drawnNumbers.add(numbers[i]);
            for (int[][] bingoCard : bingoCards) {
                if (hasBingo(bingoCard, drawnNumbers) && !previousBingos.contains(bingoCard)) {
                    lastWon = bingoCard;
                    previousBingos.add(bingoCard);
                    lastRound = i;
                }
            }
        }

        return Integer.toString(calculateResult(lastWon, drawnNumbers.subList(0, lastRound + 1)));
    }
}
