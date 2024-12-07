package aoc.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    void part1() {
        var inputString = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
        var input = inputString.lines().toList();

        assertEquals(3749, Day7.part1(input));
    }

    @Test
    void part2() {
        var inputString = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
        var input = inputString.lines().toList();

        assertEquals(11387, Day7.part2(input));
    }
}