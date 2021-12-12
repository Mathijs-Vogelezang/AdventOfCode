package year2021.solutions;

import common.Day;

import java.io.IOException;
import java.util.*;

public class Day12 extends Day {
    private static final boolean isTest = false;
    private Map<String, List<String>> paths;

    public Day12() throws IOException {
        super(isTest);
    }

    public static void main(String[] args) throws IOException {
        new Day12();
    }

    @Override
    public void setup() {
        paths = new HashMap<>();

        for (String path : input) {
            String[] caves = path.split("-");
            paths.putIfAbsent(caves[0], new ArrayList<>());
            paths.get(caves[0]).add(caves[1]);
            paths.putIfAbsent(caves[1], new ArrayList<>());
            paths.get(caves[1]).add(caves[0]);
        }
    }

    @Override
    public String part1() {
        List<String> begin = new ArrayList<>(Collections.singleton("start"));
        Set<List<String>> routes = new HashSet<>();
        calculatePart1("start", begin, begin, routes);
        return Integer.toString(routes.size());
    }

    private void calculatePart1(String start, List<String> visited, List<String> route, Set<List<String>> routes) {
        for (String cave : paths.get(start)) {
            List<String> routeHere = new ArrayList<>(route);
            if (!visited.contains(cave)) {
                List<String> visitedHere = new ArrayList<>(visited);
                if (cave.matches("[a-z]+")) visitedHere.add(cave);
                routeHere.add(cave);

                if (cave.equals("end")) {
                    routes.add(routeHere);
                } else {
                    calculatePart1(cave, visitedHere, routeHere, routes);
                }
            }
        }
    }

    @Override
    public String part2() {
        List<String> begin = new ArrayList<>(Collections.singleton("start"));
        Set<List<String>> routes = new HashSet<>();
        calculatePart2("start", new HashMap<>(), begin, routes);
        return Integer.toString(routes.size());
    }

    private void calculatePart2(String start, Map<String, Integer> visited, List<String> route, Set<List<String>> routes) {
        for (String cave : paths.get(start)) {
            List<String> routeHere = new ArrayList<>(route);
            int timesVisited = visited.getOrDefault(cave, 0);
            if (!cave.equals("start") && (timesVisited == 0 || (timesVisited == 1 && !visited.containsValue(2)))) {
                Map<String, Integer> visitedHere = new HashMap<>(visited);

                if (cave.matches("[a-z]+")) visitedHere.put(cave, timesVisited + 1);
                routeHere.add(cave);

                if (cave.equals("end")) {
                    routes.add(routeHere);
                } else {
                    calculatePart2(cave, visitedHere, routeHere, routes);
                }
            }
        }
    }
}
