package year2022.solutions;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Day9 extends Day {
    public Day9() throws IOException {
    }

    private boolean touching(int[] head, int[] tail) {
        return Math.abs(head[0] - tail[0]) <= 1 && Math.abs(head[1] - tail[1]) <= 1;
    }

    private void updateHead(int[] head, String direction) {
        switch (direction) {
            case "L" -> head[0]--;
            case "R" -> head[0]++;
            case "U" -> head[1]++;
            case "D" -> head[1]--;
        }
    }

    private void updatePosition(int[] head, int[] tail) {
        int diffX = head[0] - tail[0];
        int diffY = head[1] - tail[1];

        if (diffX > 1) diffX--;
        if (diffX < -1) diffX++;
        if (diffY > 1) diffY--;
        if (diffY < -1) diffY++;
        tail[0] += diffX;
        tail[1] += diffY;
    }

    public int hashCode(int[] knot) {
        return knot[0] * 1000 + knot[1]; // 1000 is just a random higher number, because otherwise it didn't work cause I didn't know the grid size before
    }

    @Override
    public String part1() {
        int[] head = new int[]{0, 0};
        int[] tail = new int[]{0, 0};
        Set<Integer> visitedPositions = new HashSet<>();
        visitedPositions.add(hashCode(tail));

        for (String line : input) {
            String[] split = line.split(" ");
            int amount = Integer.parseInt(split[1]);

            for (int i = 0; i < amount; i++) {
                updateHead(head, split[0]);

                if (!touching(head, tail)) {
                    updatePosition(head, tail);
                    visitedPositions.add(hashCode(tail));
                }
            }
        }
        return Integer.toString(visitedPositions.size());
    }

    @Override
    public String part2() {
        int[][] knots = new int[10][2];
        Set<Integer> visitedPositions = new HashSet<>();
        visitedPositions.add(hashCode(knots[9]));

        for (String line : input) {
            String[] split = line.split(" ");
            int amount = Integer.parseInt(split[1]);

            for (int i = 0; i < amount; i++) {
                updateHead(knots[0], split[0]);

                for (int j = 1; j < knots.length; j++) {
                    if (!touching(knots[j - 1], knots[j])) {
                        updatePosition(knots[j - 1], knots[j]);
                    }
                }
                visitedPositions.add(hashCode(knots[9]));
            }
        }
        return Integer.toString(visitedPositions.size());
    }

    public static void main(String[] args) throws IOException {
        isTest = false;
        new Day9();
    }
}