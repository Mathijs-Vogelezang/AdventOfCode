package year2022.solutions;

import common.Day;

import java.io.IOException;
import java.util.Iterator;

public class Day10 extends Day {
    public Day10() throws IOException {
    }

    @Override
    public String part1() {
        int register = 1;
        int signalStrength = 0;
        int clock = 0;
        int nextClock = 0;

        Iterator<String> iterator = input.listIterator();
        String[] command = iterator.next().split(" ");
        nextClock+= command[0].equals("addx") ? 2 : 1;

        while (iterator.hasNext()) {
            if ((clock - 20) % 40 == 0) {
                signalStrength += register * clock;
            }

            if (clock == nextClock) {
                if (command[0].equals("addx")) {
                    register += Integer.parseInt(command[1]);
                    nextClock += 2;
                } else {
                    nextClock++;
                }

                command = iterator.next().split(" ");
            }
            clock++;
        }

        return Integer.toString(signalStrength);
    }

    @Override
    public String part2() {
        int register = 1;
        int signalStrength = 0;
        int clock = 0;
        int nextClock = 0;
        char[][] screen = new char[6][40];

        Iterator<String> iterator = input.listIterator();
        String[] command = iterator.next().split(" ");
        nextClock+= command[0].equals("addx") ? 2 : 1;

        while (iterator.hasNext()) {


            if (clock == nextClock) {
                if (command[0].equals("addx")) {
                    register += Integer.parseInt(command[1]);
                    nextClock += 2;
                } else {
                    nextClock++;
                }

                command = iterator.next().split(" ");
            }

            int row = clock / 40;
            int column = clock % 40;
//            System.out.println(row + " " + clock);

            if (row < 6) {
//                System.out.println(register);
//                System.out.println(clock + " " + column + " " + row);
                screen[row][column] = Math.abs(register - column) <= 1 ? '#' : ' ';
            }
            clock++;
        }

        for (char[] row : screen) {
            System.out.println(new String(row));
        }
        return Integer.toString(signalStrength);
    }

    public static void main(String[] args) throws IOException {
        isTest = false;
        new Day10();
    }
}