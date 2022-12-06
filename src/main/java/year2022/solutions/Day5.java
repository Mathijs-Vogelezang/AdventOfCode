package year2022.solutions;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Day5 extends Day {
    private static final boolean isTest = false;

    public Day5() throws IOException {
        super(isTest);
    }

    private Stack<Character>[] getStacks() {
        String previousLine = "";
        int size = 0;
        int counter = 0;

        for (String line : input) {
            if (line.isBlank()) {
                String[] split = previousLine.trim().split(" ");
                size = Integer.parseInt(split[split.length - 1]);
                break;
            } else {
                previousLine = line;
                counter++;
            }
        }

        Stack<Character>[] stacks = new Stack[size];
        for (int i = 0; i < size; i++) {
            stacks[i] = new Stack<>();
        }

        for (int i = counter - 2; i >= 0 ; i--) {
            String line = input.get(i);

            for (int j = 0; j < size; j++) {
                int position = j * 4 + 1;
                if (position <= line.length() && line.charAt(position) != ' ') {
                    stacks[j].add(line.charAt(position));
                }
            }
        }

        return stacks;
    }

    private String getResult(Stack<Character>[] stacks) {
        int size = stacks.length;

        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = stacks[i].pop();
        }

        return new String(result);
    }

    @Override
    public String part1() {
        Stack<Character>[] stacks = getStacks();
        int startIndex = input.indexOf("") + 1;

        for (int i = startIndex; i < input.size(); i++) {
            String[] command = input.get(i).split(" ");

            int amount = Integer.parseInt(command[1]);
            int start = Integer.parseInt(command[3]) - 1;
            int end = Integer.parseInt(command[5]) - 1;

            for (int j = 0; j < amount; j++) {
                stacks[end].add(stacks[start].pop());
            }
        }

        int size = stacks.length;

        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = stacks[i].pop();
        }

        return new String(result);
    }

    @Override
    public String part2() {
        Stack<Character>[] stacks = getStacks();
        int startIndex = input.indexOf("") + 1;

        for (int i = startIndex; i < input.size(); i++) {
            String[] command = input.get(i).split(" ");

            int amount = Integer.parseInt(command[1]);
            int start = Integer.parseInt(command[3]) - 1;
            int end = Integer.parseInt(command[5]) - 1;
            Stack<Character> temp = new Stack<>();

            for (int j = 0; j < amount; j++) {
                temp.add(stacks[start].pop());
            }

            for (int j = 0; j < amount; j++) {
                stacks[end].add(temp.pop());
            }
        }

        int size = stacks.length;

        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = stacks[i].pop();
        }

        return new String(result);
    }

    public static void main(String[] args) throws IOException {
        new Day5();
    }
}