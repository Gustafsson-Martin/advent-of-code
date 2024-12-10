package aoc.day10;

import aoc.Util;

import java.util.Arrays;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day10/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    private static final class Cord {
        public int x;
        public int y;

        public Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static long part1(List<String> input) {
        final int width = input.getFirst().length();
        final int height = input.size();

        int[][] grid = new int[height][width];
        List<Cord> trailStarts = new java.util.ArrayList<>(List.of());
        for (int y = 0; y < height; y++) {
            String line = input.get(y);
            for (int x = 0; x < width; x++) {
                int value = Character.getNumericValue(line.charAt(x));
                grid[y][x] = value;
                if (value == 0) {
                    trailStarts.add(new Cord(x, y));
                }
            }
        }

        int sum = 0;
        for (Cord start : trailStarts) {
            sum += findTrail(grid, start, 0);
        }

        return sum;
    }

    private static int trailHeadScore(int[][] grid, Cord trailHead) {

    }

    private static int findTrail(int[][] grid, Cord current, int trailLength) {
        if (grid[current.y][current.x] == 9) {
            return trailLength;
        }

        System.out.println("current: " + current.x + ", " + current.y + " value: " + grid[current.y][current.x] + " trailLength: " + trailLength);

        int sum = 0;
        List<List<Integer>> directions = new java.util.ArrayList<>(List.of(
            List.of(0, 1),
            List.of(0, -1),
            List.of(1, 0),
            List.of(-1, 0)
        ));
        for (List<Integer> direction : directions) {
            int x = current.x + direction.get(0);
            int y = current.y + direction.get(1);
            if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
                continue;
            }

            if (grid[y][x] == grid[current.y][current.x] + 1) {
                sum += findTrail(grid, new Cord(x, y), trailLength + 1);
            }
        }
        return sum;
    }

    public static long part2(List<String> input) {
        return 0;
    }
}
