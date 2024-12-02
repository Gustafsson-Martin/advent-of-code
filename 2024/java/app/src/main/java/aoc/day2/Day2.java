package aoc.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    public static void main(String[] args) {
        List<List<Integer>> input = readInput();

        System.out.println("Safe reports: " + countSafeReports(input));
        System.out.println("Safe reports with tolerance: " + countSafeReportsWithTolerance(input));
    }

    public static List<List<Integer>> readInput() {
        List<List<Integer>> lines = new java.util.ArrayList<>(List.of());

        URL inputFile = Day2.class.getResource("/aoc/day2/input.txt");
        if (inputFile == null) {
            throw new IllegalArgumentException("input.txt not found!");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                String[] splitLine = line.split(" +");
                lines.add(Stream.of(splitLine).map(Integer::parseInt).toList());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public static int countSafeReports(List<List<Integer>> listOfReports) {
        int count = 0;
        for (List<Integer> report : listOfReports) {
            if (isSafe(report)) {
                count++;
            }
        }
        return count;
    }

    public static int countSafeReportsWithTolerance(List<List<Integer>> listOfReports) {
        int count = 0;
        for (List<Integer> report : listOfReports) {
            if (isSafeWithTolerance(report)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isSafeWithTolerance(List<Integer> report) {
        if (isSafe(report)) {
            return true;
        }
        for (int i = 0; i < report.size(); i++) {
            int finalI = i;
            List<Integer> newReport = IntStream.range(0, report.size())
                    .filter(j -> j != finalI)
                    .mapToObj(report::get)
                    .toList();
            if (isSafe(newReport)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafe(List<Integer> report) {
       if (report.get(0).equals(report.get(1))) {
           return false;
       }

       boolean ascending = report.get(0) < report.get(1);
       for (int i = 1; i < report.size(); i++) {
          if (!numbersAreSafe(report.get(i - 1), report.get(i), ascending)) {
            return false;
          }
       }

       return true;
    }

    private static boolean numbersAreSafe(int a, int b, boolean ascending) {
        if (ascending) {
            return b - a >= 1 && b - a <= 3;
        } else {
            return a - b >= 1 && a - b <= 3;
        }
    }
}
