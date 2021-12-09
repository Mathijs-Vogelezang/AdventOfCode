package common;

public class Util {
    public static boolean containsLetters(String toCheck, String checker) {
        for (String character : checker.split("")) {
            if (!toCheck.contains(character)) return false;
        }

        return true;
    }

    public static boolean containsLetters(String toCheck, String checker, int minimum) {
        int amount = 0;

        for (String character : checker.split("")) {
            if (toCheck.contains(character)) amount++;
        }

        return amount >= minimum;
    }
}
