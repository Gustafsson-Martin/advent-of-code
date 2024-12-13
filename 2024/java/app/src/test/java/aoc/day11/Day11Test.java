package aoc.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void part1() {
        var input = """
                125 17
                """.lines().toList();

        assertEquals(55312, Day11.part1(input));
    }

    @Test
    void part2() {
        var input = """
                125 17
                """.lines().toList();

        assertEquals(55312, Day11.part2(input));
    }
}