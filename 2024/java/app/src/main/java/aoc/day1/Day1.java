package aoc.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        List<List<Integer>> input = readInput();
        List<Integer> left = input.get(0);
        List<Integer> right = input.get(1);

        System.out.println("Distance: " + orderedListDistance(left, right));
        System.out.println("Similarity Score: " + totalSimilarityScore(left, right));
    }

    public static List<List<Integer>> readInput() {
        List<Integer> left = new java.util.ArrayList<>(List.of());
        List<Integer> right = new java.util.ArrayList<>(List.of());

        URL inputFile = Day1.class.getResource("/aoc/day1/input.txt");
        if (inputFile == null) {
            throw new IllegalArgumentException("input.txt not found!");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                String[] splitLine = line.split(" +");
                left.add(Integer.parseInt(splitLine[0]));
                right.add(Integer.parseInt(splitLine[1]));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return List.of(left, right);
    }
    public static int orderedListDistance(List<Integer> left, List<Integer> right) {
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        int distance = 0;
        for (int i = 0; i < left.size(); i++) {
            distance += Math.abs(left.get(i) - right.get(i));
        }
        return distance;
    }

    public static int totalSimilarityScore(List<Integer> left, List<Integer> right) {
        left.sort(Integer::compareTo);
        HashMap<Integer, Integer> rightValueCount = new HashMap<>();
        for (Integer val : right) {
            rightValueCount.put(val, rightValueCount.getOrDefault(val, 0) + 1);
        }

        int score = 0;
        for (Integer val : left) {
            score += val * rightValueCount.getOrDefault(val, 0);
        }
        return score;
    }
}
