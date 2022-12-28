package common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DayGenerator {
    private static final String template = "package year%d.day%d;\n" +
            "\n" +
            "import common.Day;\n" +
            "\n" +
            "import java.io.IOException;\n" +
            "\n" +
            "public class Solution extends Day {\n" +
            "    public Solution() throws IOException {\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public String part1() {\n" +
            "        return null;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public String part2() {\n" +
            "        return null;\n" +
            "    }\n" +
            "\n" +
            "    public static void main(String[] args) throws IOException {\n" +
            "        isTest = true;\n" +
            "        new Solution();\n" +
            "    }\n" +
            "}";

    public static void main(String[] args) throws IOException {
        Calendar today = new GregorianCalendar();
        int year = today.get(Calendar.YEAR);
        int day = today.get(Calendar.DATE);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the day for which you want to create new files, or leave blank for today");
        String input = scanner.nextLine();

        if (!input.isBlank()) {
            try {
                day = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered, using today");
            }
        }

        String newSolution = String.format(template, year, day);
        String directoryPath = String.format("src/main/java/year%d/day%d/", year, day);

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directoryPath + "Solution.java");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);
        writer.write(newSolution);
        writer.close();

        file = new File(directoryPath + "test_input.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println("Created directory and corresponding files for day " + day);
    }
}
