package year2021.solutions;

import common.Day;

import java.io.IOException;

public class Day21 extends Day {
    public Day21() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int position1 = Integer.parseInt(input.get(0).split(": ")[1]);
        int position2 = Integer.parseInt(input.get(1).split(": ")[1]);

        int points1 = 0;
        int points2 = 0;
        int die = 0;

        int dieThrown = 0;

        while (true) {
            int moveForward = 0;

            for (int i = 0; i < 3; i++) {
                die = (die % 100) + 1;
                dieThrown++;
                moveForward += die;
            }

            position1 = (position1 + moveForward - 1) % 10 + 1;
            points1 += position1;

            if (points1 >= 1000) {
                return Integer.toString(points2 * dieThrown);
            }

            moveForward = 0;

            for (int i = 0; i < 3; i++) {
                die = (die % 100) + 1;
                dieThrown++;
                moveForward += die;
            }

            position2 = (position2 + moveForward - 1) % 10 + 1;
            points2 += position2;

            if (points2 >= 1000) {
                return Integer.toString(points1 * dieThrown);
            }

        }
    }

    @Override
    public String part2() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        new Day21();
    }
}
