package aoc.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        List<String> input = readInput();

        System.out.println("part 1: " + part1(input));
        System.out.println("part 2: " + part2(input));
    }

    public static List<String> readInput() {
        List<String> lines = new java.util.ArrayList<>(List.of());

        URL inputFile = Day7.class.getResource("/aoc/day7/input.txt");
        if (inputFile == null) {
            throw new IllegalArgumentException("input.txt not found!");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public static long part1(List<String> input) {
        long sum = 0;
        for (String line : input) {
            long targetValue = Long.parseLong(line.substring(0, line.indexOf(":")));
            List<Long> numbers = Arrays.stream(line.substring(line.indexOf(":") + 2).split(" ")).map(Long::parseLong).toList();
            if (isValidPart1(targetValue, numbers)) {
                sum += targetValue;
            }
        }
        return sum;
    }

    private static boolean isValidPart1(long targetValue, List<Long> numbers) {
        if (numbers.size() == 1) {
            return numbers.getFirst() == targetValue;
        }

        long addedValue = numbers.getFirst() + numbers.get(1);
        List<Long> newAddList = new java.util.ArrayList<>(List.of(addedValue));
        newAddList.addAll(numbers.subList(2, numbers.size()));

        long multipliedValue = numbers.getFirst() * numbers.get(1);
        List<Long> newMulList = new java.util.ArrayList<>(List.of(multipliedValue));
        newMulList.addAll(numbers.subList(2, numbers.size()));

        return isValidPart1(targetValue, newAddList) || isValidPart1(targetValue, newMulList);
    }

    public static long part2(List<String> input) {
        long sum = 0;
        for (String line : input) {
            long targetValue = Long.parseLong(line.substring(0, line.indexOf(":")));
            List<Long> numbers = Arrays.stream(line.substring(line.indexOf(":") + 2).split(" ")).map(Long::parseLong).toList();
            if (isValidPart2(targetValue, numbers)) {
                sum += targetValue;
            }
        }
        return sum;
    }

    private static boolean isValidPart2(long targetValue, List<Long> numbers) {
        if (numbers.size() == 1) {
            return numbers.getFirst() == targetValue;
        }

        long addedValue = numbers.getFirst() + numbers.get(1);
        List<Long> newAddList = new java.util.ArrayList<>(List.of(addedValue));
        newAddList.addAll(numbers.subList(2, numbers.size()));

        long multipliedValue = numbers.getFirst() * numbers.get(1);
        List<Long> newMulList = new java.util.ArrayList<>(List.of(multipliedValue));
        newMulList.addAll(numbers.subList(2, numbers.size()));

        long concatenatedValue = Long.parseLong(numbers.get(0).toString() + numbers.get(1).toString());
        List<Long> newConcatList = new java.util.ArrayList<>(List.of(concatenatedValue));
        newConcatList.addAll(numbers.subList(2, numbers.size()));

        return isValidPart2(targetValue, newAddList) || isValidPart2(targetValue, newMulList) || isValidPart2(targetValue,newConcatList);
    }

}
