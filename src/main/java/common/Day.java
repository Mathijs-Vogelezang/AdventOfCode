package common;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Day {
    private final List<String> lines = new ArrayList<>();

    public Day(boolean isTest) throws IOException {
        String[] date = getClass().getName().split("[.]");
        String year = date[0].replace("year", "");
        String day = date[2].replace("Day", "");

        System.out.printf("Advent of Code %s day %s\n", year, day);

        if (!isTest) {
            File input = new File(String.format("src/main/java/year%s/inputs/day%s.txt", year, day));

            if (!input.exists()) {
                URL url = new URL(String.format("https://adventofcode.com/%s/day/%s/input", year, day));
                String cookie = new Scanner(new File("src/main/java/common/cookie.txt")).nextLine(); // your personal cookie, with which you can get your personal puzzle input
                input.createNewFile();
                getPuzzleInput(url, input, cookie);
            } else {
                Scanner scanner = new Scanner(input);

                while (scanner.hasNextLine()) {
                    lines.add(scanner.nextLine());
                }
            }

            System.out.println("Solution of part A");
            System.out.println(part1(lines));

            System.out.println("\nSolution of part B");
            System.out.println(part2(lines));
        } else {
            File testInput = new File(String.format("src/test/test-inputs/%s/day%s.txt", year, day));
            Scanner scanner = new Scanner(testInput);

            // Format: first two lines are test solutions taken from the website,
            // the rest of the lines are puzzle input

            String[] solutions = new String[2];
            solutions[0] = scanner.nextLine();
            solutions[1] = scanner.nextLine();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            String[] outputs = new String[2];
            outputs[0] = part1(lines);
            outputs[1] = part2(lines);

            System.out.println("\nTest report:");
            for (int i = 0; i < 2; i++) {
                if (outputs[i] == null) {
                    System.out.printf("\tSolution of part %d not yet implemented\n", i + 1);
                } else if (outputs[i].equals(solutions[i])) {
                    System.out.printf("\tThe answer of part %d is %s, this is the correct answer!\n", i + 1, outputs[i]);
                    System.out.println("\t\tYour solution should now work! (hopefully)");
                } else {
                    System.out.printf("\tYour solution of part %d does not work!\n", i + 1);
                    System.out.println("\t\tExpected answer: " + solutions[i]);
                    System.out.println("\t\tYour answer: " + outputs[i]);
                }

                System.out.println();
            }
        }
    }

    private void getPuzzleInput(URL url, File file, String cookie) throws IOException {

        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Cookie", "session=" + cookie);
            InputStream response = connection.getInputStream();

            Scanner scanner = new Scanner(response);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("C");
        }
    }

    public abstract String part1(List<String> input);
    public abstract String part2(List<String> input);
}
