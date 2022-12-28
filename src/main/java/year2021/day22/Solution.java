package year2021.day22;

import common.Day;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution extends Day {
    public Solution() throws IOException {
        super();
    }

    @Override
    public String part1() {
        int size = 101;
        boolean[][][] cubes = new boolean[size][size][size];
        int poweredOn = 0;

        for (String line : input) {
            String[] parts = line.split(" ");
            boolean state = parts[0].equals("on");

            parts = parts[1].split(",");

            int minX = Integer.parseInt(parts[0].split("=")[1].split("\\.\\.")[0]) + 50;
            int maxX = Integer.parseInt(parts[0].split("=")[1].split("\\.\\.")[1]) + 50;
            int minY = Integer.parseInt(parts[1].split("=")[1].split("\\.\\.")[0]) + 50;
            int maxY = Integer.parseInt(parts[1].split("=")[1].split("\\.\\.")[1]) + 50;
            int minZ = Integer.parseInt(parts[2].split("=")[1].split("\\.\\.")[0]) + 50;
            int maxZ = Integer.parseInt(parts[2].split("=")[1].split("\\.\\.")[1]) + 50;


            if (maxX < 0 || minX > size || maxY < 0 || minY > size || maxZ < 0 || minZ > size) continue;

            minX = Math.max(minX, 0);
            maxX = Math.min(maxX, size - 1);
            minY = Math.max(minY, 0);
            maxY = Math.min(maxY, size - 1);
            minZ = Math.max(minZ, 0);
            maxZ = Math.min(maxZ, size - 1);

            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        if (!state && cubes[x][y][z]) {
                            poweredOn--;
                        } else if (state && !cubes[x][y][z]) {
                            poweredOn++;
                        }

                        cubes[x][y][z] = state;
                    }
                }
            }
        }

        return Integer.toString(poweredOn);
    }

    @Override
    public String part2() {
        Map<Integer, Map<Integer, Map<Integer, Boolean>>> cubes = new HashMap<>();
        long poweredOn = 0;

        for (String line : input) {
            String[] parts = line.split(" ");
            boolean state = parts[0].equals("on");

            parts = parts[1].split(",");

            int minX = Integer.parseInt(parts[0].split("=")[1].split("\\.\\.")[0]) + 50;
            int maxX = Integer.parseInt(parts[0].split("=")[1].split("\\.\\.")[1]) + 50;
            int minY = Integer.parseInt(parts[1].split("=")[1].split("\\.\\.")[0]) + 50;
            int maxY = Integer.parseInt(parts[1].split("=")[1].split("\\.\\.")[1]) + 50;
            int minZ = Integer.parseInt(parts[2].split("=")[1].split("\\.\\.")[0]) + 50;
            int maxZ = Integer.parseInt(parts[2].split("=")[1].split("\\.\\.")[1]) + 50;


            for (int x = minX; x <= maxX; x++) {
                cubes.putIfAbsent(x, new HashMap<>());
                for (int y = minY; y <= maxY; y++) {
                    cubes.get(x).putIfAbsent(y, new HashMap<>());
                    for (int z = minZ; z <= maxZ; z++) {
                        cubes.get(x).get(y).putIfAbsent(z, false);

                        if (!state && cubes.get(x).get(y).get(z)) {
                            poweredOn--;
                        } else if (state && !cubes.get(x).get(y).get(z)) {
                            poweredOn++;
                        }

                        cubes.get(x).get(y).put(z, state);
                    }
                }
            }
        }



        return null;
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
