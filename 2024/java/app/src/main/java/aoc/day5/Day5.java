package aoc.day5;

import aoc.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day5/input.txt");

        for (String line : input) {
            System.out.println(line);
        }
    }

    public static int part1(List<String> input) {
        Map<Integer, List<Integer>> rules = new HashMap<>();
        List<List<Integer>> updates = new java.util.ArrayList<>(List.of());
        int lineIndex = 0;
        for (; lineIndex < input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            if (line.isEmpty()) {
                break;
            }

            String[] split = line.split("\\|");
            int page = Integer.parseInt(split[0]);
            List<Integer> pagesBefore = new java.util.ArrayList<>(List.of());
            pagesBefore.addAll(rules.getOrDefault(page, new java.util.ArrayList<>(List.of())));
            pagesBefore.add(Integer.parseInt(split[1]));
            rules.put(page, pagesBefore);
        }

        for (lineIndex = lineIndex + 1; lineIndex < input.size(); lineIndex++) {
            String line = input.get(lineIndex);

            updates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
        }

        return 0;
    }

    public static int solvePart1(Map<Integer, List<Integer>> rules, List<List<Integer>> updates) {
        return 0;
    }

    public static int part2(List<String> input) {
        return 0;
    }
}
