package year2022.day11;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Solution extends Day {
    public Solution() throws IOException {
    }
    private long commonDivider = 0;

    private List<Monkey> getMonkies() {
        List<Monkey> monkeys = new ArrayList<>();
        commonDivider = 1;

        for (int i = 0; i < input.size(); i += 7) {
            String operation = input.get(i + 2).split("= ")[1];
            int test = Integer.parseInt(input.get(i + 3).split("by ")[1]);
            int ifTrue = Integer.parseInt(input.get(i + 4).split("monkey ")[1]);
            int ifFalse = Integer.parseInt(input.get(i + 5).split("monkey ")[1]);
            commonDivider *= test;
            System.out.println(test);
            System.out.println(i);
            Monkey monkey = new Monkey(operation, test, ifTrue, ifFalse);
            String[] items = input.get(i + 1).split(": ")[1].split(", ");

            for (String item : items) {
                monkey.items.add(Long.parseLong(item));
            }
            monkeys.add(monkey);
        }
        System.out.println(commonDivider);

        return monkeys;
    }

    private long doOperation (long item, String operation) {
        String[] calculation = operation.replace("old", Long.toString(item)).split(" ");
        long part1 = Long.parseLong(calculation[0]);
        long part2 = Long.parseLong(calculation[2]);

        if (calculation[1].equals("+")) {
            return part1 + part2;
        } else {
            return part1 * part2;
        }

    }

    @Override
    public String part1() {
        List<Monkey> monkeys = getMonkies();

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.items.isEmpty()) {
                    long item = monkey.items.remove(0);
                    item = doOperation(item, monkey.operation);
                    item/= 3;
                    int nextMonkey = item % monkey.test == 0 ? monkey.ifTrue : monkey.ifFalse;
                    monkeys.get(nextMonkey).items.add(item);
                    monkey.inspectedItems++;
                }
            }
        }

        monkeys.sort((a, b) -> Long.signum(b.inspectedItems - a.inspectedItems));
        return Long.toString(monkeys.get(0).inspectedItems * monkeys.get(1).inspectedItems);
    }

    @Override
    public String part2() {
        List<Monkey> monkeys = getMonkies();

        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.items.isEmpty()) {
                    long item = monkey.items.remove(0);
                    item = doOperation(item, monkey.operation);
                    int nextMonkey = item % monkey.test == 0 ? monkey.ifTrue : monkey.ifFalse;
                    monkeys.get(nextMonkey).items.add(item % commonDivider);
                    monkey.inspectedItems++;
                }
            }
        }

        monkeys.sort((a, b) -> Long.signum(b.inspectedItems - a.inspectedItems));

        return Long.toString(monkeys.get(0).inspectedItems * monkeys.get(1).inspectedItems);
    }

    public static void main(String[] args) throws IOException {
        isTest = false;
        new Solution();
    }
}