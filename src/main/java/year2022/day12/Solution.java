package year2022.day12;

import common.Day;
import common.Util;

import java.io.IOException;
import java.util.*;

public class Solution extends Day {
    private Square[][] squares;
    private Square startSquare;
    private Square endSquare;

    public Solution() throws IOException {
    }

    @Override
    public void setup() {
        char[][] grid = Util.createCharGrid(input);
        squares = new Square[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Square newSquare = new Square();
                newSquare.elevation = grid[i][j];
                newSquare.xCoords = i;
                newSquare.yCoords = j;
                squares[i][j] = newSquare;
            }
        }

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {

                if (squares[i][j].elevation == 'S') {
                    startSquare = squares[i][j];
                    squares[i][j].elevation = 'a';
                } else if (squares[i][j].elevation == 'E') {
                    endSquare = squares[i][j];
                    squares[i][j].elevation = 'z';
                }

                int[] xOffset = new int[]{-1, 0, 1, 0};
                int[] yOffset = new int[]{0, -1, 0, 1};

                for (int k = 0; k < 4; k++) {
                    int x = xOffset[k];
                    int y = yOffset[k];

                    try {
                        Square neighbour = squares[i + x][j + y];
                        if (neighbour.elevation - squares[i][j].elevation <= 1) {
                            squares[i][j].neighbours.add(neighbour);
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {

                    }
                }
            }
        }
    }

    private int manhattanDistance(Square square1, Square square2) {
        return Math.abs(square1.xCoords - square2.xCoords) + Math.abs(square1.yCoords - square2.yCoords);
    }

    private List<Square> reconstructPath(Map<Square, Square> cameFrom, Square current) {
        List<Square> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        return path;
    }

    private List<Square> findPath (Square start, Square goal) {
        Set<Square> openSet = new HashSet<>();
        openSet.add(start);
        Map<Square, Square> cameFrom = new HashMap<>();
        Map<Square, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        Map<Square, Integer> fScore = new HashMap<>();
        fScore.put(start, manhattanDistance(start, goal));

        while (!openSet.isEmpty()) {
            Square current = openSet.stream().min(Comparator.comparing(square -> fScore.getOrDefault(square, Integer.MAX_VALUE))).get();

            if (current == goal) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);

            for (Square neighbour : current.neighbours) {
                int tentativeGScore = gScore.get(current) + 1;

                if (tentativeGScore < gScore.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbour, current);
                    gScore.put(neighbour, tentativeGScore);
                    fScore.put(neighbour, tentativeGScore + manhattanDistance(neighbour, goal));

                    openSet.add(neighbour);
                }
            }
        }

        return null;
    }

    @Override
    public String part1() {
        return Integer.toString(findPath(startSquare, endSquare).size() + 1);
    }

    @Override
    public String part2() {
        int minimumSteps = Integer.MAX_VALUE;

        for (Square[] square : squares) {
            for (int j = 0; j < squares[0].length; j++) {
                if (square[j].elevation == 'a') {
                    List<Square> path = findPath(square[j], endSquare);
                    if (path != null) {
                        minimumSteps = Math.min(minimumSteps, path.size() + 1);
                    }
                }
            }
        }

        return Integer.toString(minimumSteps);
    }

    public static void main(String[] args) throws IOException {
        isTest = true;
        new Solution();
    }
}