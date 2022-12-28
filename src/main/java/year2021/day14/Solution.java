package year2021.day14;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Solution extends Day {
    private String template;
    private Map<String, String> rules;

    public Solution() throws IOException {
        super();
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }

    @Override
    public void setup() {
        template = input.get(0);
        rules = new HashMap<>();

        for (int i = 2; i < input.size(); i++) {
            String[] ruleParts = input.get(i).split(" -> ");
            rules.put(ruleParts[0], ruleParts[1]);
        }
    }

    @Override
    public String part1() {
        return Long.toString(calculateDifference(10));
    }

    @Override
    public String part2() {
        return Long.toString(calculateDifference(40));
    }

    private long calculateDifference(int times) {
        Map<String, Long> pairAmounts = new LinkedHashMap<>();
        for (int j = 0; j < template.length() - 1; j++) {
            String pair = template.substring(j, j + 2);
            long amount = pairAmounts.getOrDefault(pair, (long) 0);
            pairAmounts.put(pair, amount + 1);
        }

        for (int i = 0; i < times; i++) {
            Map<String, Long> subAmounts = new LinkedHashMap<>();

            for (Map.Entry<String, Long> entry : pairAmounts.entrySet()) {
                String pair1 = entry.getKey().charAt(0) + rules.get(entry.getKey());
                String pair2 = rules.get(entry.getKey()) + entry.getKey().charAt(1);

                long amount = subAmounts.getOrDefault(pair1, (long) 0);
                subAmounts.put(pair1, entry.getValue() + amount);
                amount = subAmounts.getOrDefault(pair2, (long) 0);
                subAmounts.put(pair2, entry.getValue() + amount);
            }

            pairAmounts = subAmounts;
        }

        Map<Character, Long> letterAmounts = getLetterAmounts(pairAmounts);
        List<Long> amounts = new ArrayList<>(letterAmounts.values());

        Collections.sort(amounts);
        return amounts.get(amounts.size() - 1) - amounts.get(0);
    }

    private Map<Character, Long> getLetterAmounts(Map<String, Long> input) {
        Map<Character, Long> amounts = new HashMap<>();

        for (Map.Entry<String, Long> entry : input.entrySet()) {
            long amount = amounts.getOrDefault(entry.getKey().charAt(0), (long) 0);
            amounts.put(entry.getKey().charAt(0), amount + entry.getValue());
        }

        long amount = amounts.get(template.charAt(template.length() - 1));
        amounts.put(template.charAt(template.length() - 1), amount + 1);

        return amounts;
    }
}
