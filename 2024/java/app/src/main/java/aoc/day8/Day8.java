package aoc.day8;

import aoc.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Day8 {
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

    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day8/input.txt");
        
        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    public static long part1(List<String> input) {
        final int width = input.getFirst().length();
        final int height = input.size();

        Map<Character, List<Cord>> antennaPositions = new HashMap<>();
        for (int y = 0; y < height; y++) {
            String line = input.get(y);
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                if (c == '.') continue;

                List<Cord> cords = antennaPositions.getOrDefault(c, new java.util.ArrayList<>(List.of()));
                cords.add(new Cord(x, y));
                antennaPositions.put(c, cords);
            }
        }

        Set<Cord> antinodes = new HashSet<>();
        for (Map.Entry<Character, List<Cord>> entry : antennaPositions.entrySet()) {
            List<Cord> cordList = entry.getValue();
            for (int i = 0; i < cordList.size(); i++) {
                for (int j = i + 1; j < cordList.size(); j++) {
                    Cord cord1 = entry.getValue().get(i);
                    Cord cord2 = entry.getValue().get(j);
                    int dx = cord2.x - cord1.x;
                    int dy = cord2.y - cord1.y;
                    List<Cord> antinodeList = List.of(
                        new Cord(cord1.x - dx, cord1.y - dy),
                        new Cord(cord2.x + dx, cord2.y + dy)
                    );
                    for (Cord antinode : antinodeList) {
                        if (antinode.x < 0 || antinode.x >= width || antinode.y < 0 || antinode.y >= height) {
                            continue;
                        }
                        antinodes.add(antinode);
                    }
                }
            }
        }

        return antinodes.size();
    }

    public static long part2(List<String> input) {
        final int width = input.getFirst().length();
        final int height = input.size();

        Map<Character, List<Cord>> antennaPositions = new HashMap<>();
        for (int y = 0; y < height; y++) {
            String line = input.get(y);
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                if (c == '.') continue;

                List<Cord> cords = antennaPositions.getOrDefault(c, new java.util.ArrayList<>(List.of()));
                cords.add(new Cord(x, y));
                antennaPositions.put(c, cords);
            }
        }

        Set<Cord> antinodes = new HashSet<>();
        for (Map.Entry<Character, List<Cord>> entry : antennaPositions.entrySet()) {
            List<Cord> cordList = entry.getValue();
            for (int i = 0; i < cordList.size(); i++) {
                for (int j = i + 1; j < cordList.size(); j++) {
                    Cord cord1 = entry.getValue().get(i);
                    Cord cord2 = entry.getValue().get(j);
                    int dx = cord2.x - cord1.x;
                    int dy = cord2.y - cord1.y;
                    Cord newCord = new Cord(cord1.x + dx, cord1.y + dy);
                    while (isInside(newCord, width, height)) {
                        antinodes.add(newCord);
                        newCord = new Cord(newCord.x + dx, newCord.y + dy);
                    }
                    newCord = new Cord(cord2.x - dx, cord2.y - dy);
                    while (isInside(newCord, width, height)) {
                        antinodes.add(newCord);
                        newCord = new Cord(newCord.x - dx, newCord.y - dy);
                    }
                }
            }
        }

        return antinodes.size();
    }

    private static boolean isInside(Cord cord, int width, int height) {
        return cord.x >= 0 && cord.x < width && cord.y >= 0 && cord.y < height;
    }
}
