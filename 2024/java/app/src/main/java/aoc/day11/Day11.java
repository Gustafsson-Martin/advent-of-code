package aoc.day11;

import aoc.Util;

import java.util.Arrays;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day11/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    public static int part1(List<String> input) {
        List<String> stones = Arrays.stream(input.get(0).split(" ")).toList();

        for (int i = 0; i < 25; i++) {
            stones = performIteration(stones);
        }

        return stones.size();
    }

    private static List<String> performIteration(List<String> stones) {
        List<String> newStones = new java.util.ArrayList<>(List.of());
        for (String stone : stones) {
            if (stone.equals("0")) {
                newStones.add("1");
            } else if (stone.length() % 2 == 0) {
                String leftHalf = stone.substring(0, stone.length() / 2);
                String rightHalf = stone.substring(stone.length() / 2);
                newStones.add(leftHalf);
                rightHalf = Long.toString(Long.parseLong(rightHalf));
                newStones.add(rightHalf);
            } else {
                long value = Long.parseLong(stone);
                long newValue = value * 2024L;
                newStones.add(Long.toString(newValue));
            }
        }
        return newStones;
    }


    public static int part2(List<String> input) {
        List<Double> stoneLengths = Arrays.stream(input.get(0).split(" ")).map((str) -> Math.log10(Double.parseDouble(str))).toList();

        for (int i = 0; i < 25; i++) {
            stoneLengths = performNewInteration(stoneLengths);
            //System.out.println(String.join(" ", stoneLengths.stream().map((d) -> Double.toString(d).split("\\.")[0]).toList()));
        }

        return stoneLengths.size();
    }

    private static List<Double> performNewInteration(List<Double> stoneLengths) {
        List<Double> newStones = new java.util.ArrayList<>(List.of());
        for (Double logNumber : stoneLengths) {
            if (logNumber.isInfinite()) {
                newStones.add(0d);
            } else if (Math.floor(logNumber + 1) % 2 == 0) {
                double divisor = Math.pow(10, Math.floor((logNumber + 1) / 2));
                double leftLog = logNumber - Math.log10(divisor) + 0.0000000001;
                double leftNum = Math.floor(Math.pow(10, leftLog));

                double rightNum = Math.round(Math.pow(10, logNumber) - (leftNum * divisor));

                //System.out.println("leftNum: " + leftNum);
                //System.out.println("rightNum: " + rightNum);

                newStones.add(Math.log10(leftNum));
                newStones.add(Math.log10(rightNum));
            } else {
                newStones.add(logNumber + Math.log10(2024d));
            }
        }
        return newStones;
    }
}
