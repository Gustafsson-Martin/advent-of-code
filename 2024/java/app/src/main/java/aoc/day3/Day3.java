package aoc.day3;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3 {
    public static void main(String[] args) {
        String input = readInput();

        System.out.println("Sum multiplications: " + sumMultiplications(input));
        System.out.println("Sum multiplications part 2: " + sumMultiplicationsPart2(input));
    }

    public static String readInput() {
        URL inputFile = Day3.class.getResource("/aoc/day3/input.txt");
        try {
            return Files.readString(Paths.get(inputFile.getFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int sumMultiplications(String input) {
        int sum = 0;
        for (int i = 0; i < input.length(); i++){
            char chr = input.charAt(i);
            if (chr != ')') continue;

            String str = input.substring(input.lastIndexOf('m', i), i);
            if (isMulCommand(str)) {
                sum += performMulCommand(str);
            }
        }

        return sum;
    }

    public static int sumMultiplicationsPart2(String input) {
        int sum = 0;
        boolean enabled = true;
        for (int i = 0; i < input.length(); i++){
            char chr = input.charAt(i);
            if (chr != ')') continue;

            if (isDontCommand(input.substring(i-6, i))) {
                enabled = false;
            } else if (isDoCommand(input.substring(i-3, i))) {
                enabled = true;
            } else if (enabled && isMulCommand(input.substring(input.lastIndexOf('m', i), i))) {
                sum += performMulCommand(input.substring(input.lastIndexOf('m', i), i));
            }
        }

        return sum;
    }

    private static boolean isDoCommand(String substring) {
        return substring.equals("do(");
    }

    private static boolean isDontCommand(String substring) {
        return substring.equals("don't(");
    }

    private static int performMulCommand(String substring) {
        String[] split = substring.split(",");
        return Integer.parseInt(split[0].substring(4)) * Integer.parseInt(split[1]);
    }

    private static boolean isMulCommand(String substring) {
        String[] split = substring.split(",");
        if (split.length != 2 || !split[0].startsWith("mul(")) {
            return false;
        }

        for (int i = 4; i < split[0].length(); i++) {
            if (split[0].charAt(i) < '0' || split[0].charAt(i) > '9') {
                return false;
            }
        }
        for (int i = 0; i < split[1].length(); i++) {
            if (split[1].charAt(i) < '0' || split[1].charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
