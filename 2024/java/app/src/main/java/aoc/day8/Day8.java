package aoc.day8;

import aoc.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {
    private static class Cord {
        public int x;
        public int y;

        Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day8/input.txt");
        
        System.out.println("Part 1: " + part1(input));
    }

    public static long part1(List<String> input) {
        final int width = input.getFirst().length();
        final int height = input.size();

        Map<Integer, List<Cord>> antennaPositions = new HashMap<>();

    }
}
