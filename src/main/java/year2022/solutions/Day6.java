package year2022.solutions;

import common.Day;
import common.Util;

import java.io.IOException;

public class Day6 extends Day {
    public Day6() throws IOException {
        super();
    }

    private int findStartOfMessage(String line, int amount) {
        for (int i = amount; i < line.length(); i++) {
            if (Util.uniqueCharacters(line.substring(i - amount, i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String part1() {
        return Integer.toString(findStartOfMessage(input.get(0), 4));
    }

    @Override
    public String part2() {
        return Integer.toString(findStartOfMessage(input.get(0), 14));
    }

    public static void main(String[] args) throws IOException {
        new Day6();
    }
}
