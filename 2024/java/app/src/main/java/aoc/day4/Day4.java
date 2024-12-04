package aoc.day4;

import aoc.day2.Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    private static final CharSequence DELIMITER = ".";

    public static void main(String[] args) {
        List<List<Character>> input = readInput();

        System.out.println("part 1: " + part1(input));
        System.out.println("part 2: " + part2(input));
    }

    public static List<List<Character>> readInput() {
        List<List<Character>> lines = new java.util.ArrayList<>(List.of());

        URL inputFile = Day4.class.getResource("/aoc/day4/input.txt");
        if (inputFile == null) {
            throw new IllegalArgumentException("input.txt not found!");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public static long part1(List<List<Character>> input) {
        final int N_COLS = input.getFirst().size();
        final int N_ROWS = input.size();

        String rows = "";
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.getFirst().size(); col++) {
                rows += input.get(row).get(col);
            }
            rows += DELIMITER;
        }

        String columns = "";
        for (int col = 0; col < input.getFirst().size(); col++) {
            for (int row = 0; row < input.size(); row++) {
                columns += input.get(row).get(col);
            }
            columns += DELIMITER;
        }

        String leftDiagonals = "";
        for (int colStart = -input.getFirst().size()+1; colStart < input.getFirst().size(); colStart++) {
            for (int row = 0; row < input.size(); row++) {
                int col = colStart + row;
                if (col >= N_COLS || col < 0) continue;
                leftDiagonals += input.get(row).get(col);
            }
            leftDiagonals += DELIMITER;
        }

        String rightDiagonals = "";
        for (int colStart = 0; colStart < 2 * input.getFirst().size()-1; colStart++) {
            for (int row = 0; row < input.size(); row++) {
                int col = colStart - row;
                if (col >= N_COLS || col < 0) continue;
                rightDiagonals += input.get(row).get(col);
            }
            rightDiagonals += DELIMITER;
        }

        String fullString = rows + columns + leftDiagonals + rightDiagonals;
        Pattern xmasPattern = Pattern.compile("XMAS");
        Pattern backwardsPattern = Pattern.compile("SAMX");

        long xmasMatches = xmasPattern.matcher(fullString).results().count();
        long backwardsMatches = backwardsPattern.matcher(fullString).results().count();

        return xmasMatches + backwardsMatches;
    }

    public static long part2(List<List<Character>> input) {
        final int N_COLS = input.getFirst().size();
        final int N_ROWS = input.size();

        String squares = "";
        for (int middleRow = 1; middleRow < N_ROWS-1; middleRow++) {
            for (int middleCol = 1; middleCol < N_COLS-1; middleCol++) {
                squares += input.get(middleRow-1).get(middleCol-1);
                squares += input.get(middleRow-1).get(middleCol);
                squares += input.get(middleRow-1).get(middleCol+1);
                squares += input.get(middleRow).get(middleCol-1);
                squares += input.get(middleRow).get(middleCol);
                squares += input.get(middleRow).get(middleCol+1);
                squares += input.get(middleRow+1).get(middleCol-1);
                squares += input.get(middleRow+1).get(middleCol);
                squares += input.get(middleRow+1).get(middleCol+1);
                squares += DELIMITER;
            }
        }

        Pattern pattern = Pattern.compile("S[A-Z]S[A-Z]A[A-Z]M[A-Z]M|S[A-Z]M[A-Z]A[A-Z]S[A-Z]M|M[A-Z]S[A-Z]A[A-Z]M[A-Z]S|M[A-Z]M[A-Z]A[A-Z]S[A-Z]S");

        return pattern.matcher(squares).results().count();
    }
}