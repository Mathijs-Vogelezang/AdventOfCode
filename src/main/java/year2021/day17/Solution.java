package year2021.day17;

import common.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution extends Day {
    public Solution() throws IOException {
    }

    public int[] getArea() {
        String[] line = input.get(0).replace("target area: x=", "").replace("..", " ")
                .replace(", y=", " ").split(" ");
        System.out.println(Arrays.toString(line));

        int[] area = new int[4];

        for (int i = 0; i < 4; i++) {
            area[i] = Integer.parseInt(line[i]);
        }
        return area;
    }

    @Override
    public String part1() {
        int[] area = getArea();

        int minX = area[0];
        int maxX = area[1];
        int minY = area[3];
        int maxY = area[2];

        int minShootX = (int) Math.ceil((Math.sqrt(minX) - 1)/2);
        Map<Integer, Integer> shootAmountsX = new HashMap<>();

        for (int i = minShootX; i < maxX; i++) {
            int counter = 0;
            int total = 0;
            for (int j = i; j > 0 ; j--) {
                counter++;
                total += j;

                if (total >= minX && total <= maxX) {
                    shootAmountsX.put(counter, i);
                }
            }
        }

//        System.out.println(shootAmountsX);

        int yShoot = Math.abs(maxY) - 1;
        int maxHeight = (yShoot * (yShoot + 1)) / 2;


        return Integer.toString(maxHeight);
    }

    @Override
    public String part2() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        isTest = false;
        new Solution();
    }
}
