package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 extends Day {
    private static final boolean isTest = false;
    private int maxX = 0;
    private int maxY = 0;

    public Day5() throws IOException {
        super(isTest);
    }

    @Override
    public String part1(List<String> input) {
        ArrayList<LineSegment> segments = (ArrayList<LineSegment>) createLineSegments(input);
        int[][] grid = new int[maxY][maxX];

        for (LineSegment segment : segments) {
            straightLine(grid, segment);
        }

        return Integer.toString(calculateIntersections(grid));
    }

    @Override
    public String part2(List<String> input) {
        ArrayList<LineSegment> segments = (ArrayList<LineSegment>) createLineSegments(input);
        int[][] grid = new int[maxY][maxX];

        for (LineSegment segment : segments) {
            if (segment.x1 - segment.x2 == segment.y1 - segment.y2) {
                int beginX = Math.min(segment.x1, segment.x2);
                int beginY = Math.min(segment.y1, segment.y2);
                int length = Math.abs(segment.x1 - segment.x2);

                for (int i = 0; i <= length; i++) {
                    grid[beginY + i][beginX + i]++;
                }
            } else if (segment.x1 - segment.y2 == segment.x2 - segment.y1) {
                int beginX = Math.max(segment.x1, segment.x2);
                int beginY = Math.min(segment.y1, segment.y2);
                int length = Math.abs(segment.y1 - segment.y2);

                for (int i = 0; i <= length; i++) {
                    grid[beginY + i][beginX - i]++;
                }
            } else straightLine(grid, segment);
        }

        return Integer.toString(calculateIntersections(grid));
    }

    public static void straightLine(int[][] grid, LineSegment segment) {
        if (segment.x1 == segment.x2) {
            int start = Math.min(segment.y1, segment.y2);
            int end = Math.max(segment.y1, segment.y2);

            for (int j = start; j <= end; j++) {
                grid[j][segment.x1]++;
            }
        } else if (segment.y1 == segment.y2) {
            int start = Math.min(segment.x1, segment.x2);
            int end = Math.max(segment.x1, segment.x2);

            for (int j = start; j <= end; j++) {
                grid[segment.y1][j]++;
            }
        }
    }

    public static int calculateIntersections(int[][] grid) {
        int counter = 0;

        for (int[] lines : grid) {
            for (int pos : lines) {
                if (pos >= 2) counter++;
            }
        }

        return counter;
    }

    private class LineSegment {
        int x1;
        int y1;
        int x2;
        int y2;
    }

    public List<LineSegment> createLineSegments(List<String> input) {
        List<LineSegment> segments = new ArrayList<>();

        for (String line : input) {
            LineSegment segment = new LineSegment();
            String[] coordinates = line.split(" -> ");
            String[] xy1 = coordinates[0].split(",");
            String[] xy2 = coordinates[1].split(",");

            segment.x1 = Integer.parseInt(xy1[0]);
            segment.y1 = Integer.parseInt(xy1[1]);
            segment.x2 = Integer.parseInt(xy2[0]);
            segment.y2 = Integer.parseInt(xy2[1]);

            int maxX = Math.max(segment.x1 + 1, segment.x2 + 1);
            int maxY = Math.max(segment.y1 + 1, segment.y2 + 1);
            this.maxX = Math.max(this.maxX, maxX);
            this.maxY = Math.max(this.maxY, maxY);

            segments.add(segment);
        }

        return segments;
    }

    public static void main(String[] args) throws IOException {
        new Day5();
    }
}
