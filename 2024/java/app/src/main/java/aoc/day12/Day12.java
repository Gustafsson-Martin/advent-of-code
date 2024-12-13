package aoc.day12;

import aoc.Cord;
import aoc.Util;

import java.util.List;
import java.util.Set;

public class Day12 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day12/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    public static int part1(List<String> input) {
        char[][] grid = new char[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }

        Set<Cord> visited = new java.util.HashSet<>();
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                Cord cord = new Cord(x, y);
                if (visited.contains(cord)) {
                    continue;
                }
                int perimeterCost = countPerimeterCost(grid, cord, visited);
                sum += perimeterCost;
            }
        }
        return sum;
    }

    private static int countPerimeterCost(char[][] grid, Cord cord, Set<Cord> visited) {
        List<Cord> toVisit = new java.util.ArrayList<>(List.of(cord));
        int sum = 0;
        int count = 0;
        char target = grid[cord.y][cord.x];
        while (!toVisit.isEmpty()) {
            Cord current = toVisit.removeFirst();
            if (visited.contains(current)) {
                continue;
            }
            count++;
            visited.add(current);
            for (Cord neighbour : getNeighbours(current)) {
                if (visited.contains(neighbour)) {
                    if (grid[neighbour.y][neighbour.x] != grid[cord.y][cord.x]) {
                        sum++;
                    }
                    continue;
                }
                if (!inBounds(grid, neighbour)) {
                    sum++;
                    continue;
                }
                if (grid[neighbour.y][neighbour.x] != grid[cord.y][cord.x]) {
                    sum++;
                    continue;
                }

                toVisit.add(neighbour);
            }
        }
        return sum * count;
    }

    private static List<Cord> getNeighbours(Cord cord) {
        return List.of(
                new Cord(cord.x + 1, cord.y),
                new Cord(cord.x - 1, cord.y),
                new Cord(cord.x, cord.y + 1),
                new Cord(cord.x, cord.y - 1)
        );
    }

    private static boolean inBounds(char[][] grid, Cord cord) {
        return cord.x >= 0 && cord.x < grid[0].length && cord.y >= 0 && cord.y < grid.length;
    }

    public static int part2(List<String> input) {
        char[][] grid = new char[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }

        Set<Cord> visited = new java.util.HashSet<>();
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                Cord cord = new Cord(x, y);
                if (visited.contains(cord)) {
                    continue;
                }
                int perimeterCost = countPerimeterCost2(grid, cord, visited);
                sum += perimeterCost;
            }
        }
        return sum;
    }

    private static int countPerimeterCost2(char[][] grid, Cord cord, Set<Cord> visited) {
        List<Cord> toVisit = new java.util.ArrayList<>(List.of(cord));
        int sum = 0;
        int count = 0;
        char target = grid[cord.y][cord.x];
        while (!toVisit.isEmpty()) {
            Cord current = toVisit.removeFirst();
            if (visited.contains(current)) {
                continue;
            }
            count++;
            visited.add(current);

            Cord neighbourUp = new Cord(current.x, current.y - 1);
            Cord neighbourDown = new Cord(current.x, current.y + 1);
            Cord neighbourLeft = new Cord(current.x - 1, current.y);
            Cord neighbourRight = new Cord(current.x + 1, current.y);
            Cord neighbourUpLeft = new Cord(current.x - 1, current.y - 1);
            Cord neighbourUpRight = new Cord(current.x + 1, current.y - 1);
            Cord neighbourDownLeft = new Cord(current.x - 1, current.y + 1);
            Cord neighbourDownRight = new Cord(current.x + 1, current.y + 1);

            if (shouldAddBorder(grid, current, neighbourDown, neighbourDownLeft, neighbourLeft)) {
                sum++;
            }
            if (shouldAddBorder(grid, current, neighbourUp, neighbourUpRight, neighbourRight)) {
                sum++;
            }
            if (shouldAddBorder(grid, current, neighbourLeft, neighbourDownLeft, neighbourDown)) {
                sum++;
            }
            if (shouldAddBorder(grid, current, neighbourRight, neighbourDownRight, neighbourDown)) {
                sum++;
            }

            for (Cord neighbour : getNeighbours(current)) {
                if (visited.contains(neighbour)) {
                    continue;
                }
                if (!inBounds(grid, neighbour)) {
                    continue;
                }
                if (grid[neighbour.y][neighbour.x] != grid[cord.y][cord.x]) {
                    continue;
                }

                toVisit.add(neighbour);
            }
        }
        System.out.println("target: " + grid[cord.y][cord.x] + " sum: " + sum + " count: " + count);
        return sum * count;
    }

    private static boolean shouldAddBorder(char[][] grid, Cord cord, Cord aCord, Cord bCord, Cord cCord) {
        char value = grid[cord.y][cord.x];
        char aValue = inBounds(grid, aCord) ? grid[aCord.y][aCord.x] : '.';
        char bValue = inBounds(grid, bCord) ? grid[bCord.y][bCord.x] : '.';
        char cValue = inBounds(grid, cCord) ? grid[cCord.y][cCord.x] : '.';

        if (value == cValue) {
            return aValue != value && bValue == value;
        } else {
            return aValue != value;
        }
    }

}
