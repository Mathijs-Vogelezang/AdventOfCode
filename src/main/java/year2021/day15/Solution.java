package year2021.day15;

import common.Day;
import common.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution extends Day {
    int[][] riskLevels;
    Node[][] nodes;
    Route[][] routes;

    public Solution() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }

    @Override
    public void setup() {
        riskLevels = Util.createIntGrid(input);
    }

    @Override
    public String part1() {
        Map<int[], Integer> unvisited = new HashMap<>();
        Map<int[], Integer> visited = new HashMap<>();
        int size = riskLevels.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unvisited.put(new int[]{i, j}, Integer.MAX_VALUE);
            }
        }

        int[] current = new int[]{0, 0};
        int currentCost = 0;

        while (!unvisited.isEmpty()) {
            unvisited.remove(current);

            try {
                int neighbourCost = riskLevels[current[0] - 1][current[1]];
            } catch (ArrayIndexOutOfBoundsException ignore) {

            }

            try {
                int neighbourCost = riskLevels[current[0]][current[1] - 1];
            } catch (ArrayIndexOutOfBoundsException ignore) {

            }

            try {
                int neighbourCost = riskLevels[current[0] + 1][current[1]];
            } catch (ArrayIndexOutOfBoundsException ignore) {

            }

            try {
                int neighbourCost = riskLevels[current[0]][current[1] + 1];
            } catch (ArrayIndexOutOfBoundsException ignore) {

            }
        }

        return null;
    }

    @Override
    public String part2() {
        return null;
    }

    class Route {
        int cost;
        int[] neighbour;

        Route(int cost, int[] neighbour) {
            this.cost = cost;
            this.neighbour = neighbour;
        }
    }

    class Node {
        Route[][] routes = new Route[riskLevels.length][riskLevels[0].length];

        boolean foundAllRoutes() {
            for (Route[] routeRow : routes) {
                for (Route route : routeRow) {
                    if (route == null) return false;
                }
            }

            return true;
        }
    }
}
