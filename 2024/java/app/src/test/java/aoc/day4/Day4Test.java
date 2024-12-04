package aoc.day4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void givenTest() {
        String inputStr = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        String[] rows = inputStr.split("\n");
        List<List<Character>> input = Arrays.stream(rows).map(row -> row.chars().mapToObj(c -> (char) c).toList()).toList();

        assertEquals(18, Day4.part1(input));
    }

    @Test
    void part2() {
        String inputStr = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        String[] rows = inputStr.split("\n");
        List<List<Character>> input = Arrays.stream(rows).map(row -> row.chars().mapToObj(c -> (char) c).toList()).toList();

        assertEquals(9, Day4.part2(input));
    }
}