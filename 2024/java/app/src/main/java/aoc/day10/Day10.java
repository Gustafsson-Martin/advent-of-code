package aoc.day10;

import aoc.Util;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day10/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }


    private static class Cord {
        public int x;
        public int y;

        Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cord cord = (Cord) o;
            return x == cord.x && y == cord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
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
            sum += trailPeaksAccessibleFrom(grid, start);
        }

        return sum;
    }

    private static int trailPeaksAccessibleFrom(int[][] grid, Cord start) {
        Set<Cord> trailPeaksAccessible = new HashSet<>();

        trailPeaksAccessibleFromRecursive(grid, start, trailPeaksAccessible);

        return trailPeaksAccessible.size();
    }

    private static Set<Cord> trailPeaksAccessibleFromRecursive(int[][] grid, Cord current, Set<Cord> trailPeaksAccessible) {
        if (grid[current.y][current.x] == 9) {
            trailPeaksAccessible.add(current);
            return trailPeaksAccessible;
        }

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

            Cord newCord = new Cord(x, y);
            if (grid[y][x] == grid[current.y][current.x] + 1) {
                trailPeaksAccessibleFromRecursive(grid, newCord, trailPeaksAccessible);
            }
        }

        return trailPeaksAccessible;
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
            sum += pathsToPeakFrom(grid, start);
        }

        return sum;
    }

    private static int pathsToPeakFrom(int[][] grid, Cord current) {
        if (grid[current.y][current.x] == 9) {
            return 1;
        }

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
                sum += pathsToPeakFrom(grid, new Cord(x, y));
            }
        }
        return sum;
    }
}
