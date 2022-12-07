package year2022.solutions;

import common.Day;

import java.io.IOException;

public class Day2 extends Day {
    public Day2() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int totalScore = 0;

        for (String line : input) {
            int move = ((int) line.charAt(2) - 88);
            int opponentMove = ((int) line.charAt(0) - 65);
            int outcome = Math.floorMod(move - opponentMove, 3);
            totalScore += move + 1;

            if (outcome == 1) { //player wins
                totalScore += 6;
            } else if (outcome == 0) { // draw
                totalScore += 3;
            }
        }

        return Integer.toString(totalScore);
    }

    @Override
    public String part2() {
        int totalScore = 0;

        for (String line : input) {
            int opponentMove = ((int) line.charAt(0) - 65);
            int strategy = ((int) line.charAt(2) - 88) - 1;
            int move = Math.floorMod(opponentMove + strategy, 3);

            int outcome = Math.floorMod(move - opponentMove, 3);
            totalScore += move + 1;

            if (outcome == 1) { //player wins
                totalScore += 6;
            } else if (outcome == 0) { // draw
                totalScore += 3;
            }
        }

        return Integer.toString(totalScore);
    }

    public static void main(String[] args) throws IOException {
        new Day2();
    }
}
