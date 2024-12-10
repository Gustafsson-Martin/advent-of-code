package aoc.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void part1() {
        var input = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
                """.lines().toList();

        assertEquals(36, Day10.part1(input));
    }

    @Test
    void part2() {
        var input = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
                """.lines().toList();

        assertEquals(81, Day10.part2(input));
    }
}