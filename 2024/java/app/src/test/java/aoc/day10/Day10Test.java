package aoc.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void part1() {
        var input = """
0123
1234
8765
9876
                """.lines().toList();

        assertEquals(36, Day10.part1(input));
    }

    @Test
    void part2() {
    }
}