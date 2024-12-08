package aoc.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    void part1() {
        var inputString = """
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
                """;
        var input = inputString.lines().toList();

        assertEquals(14, Day8.part1(input));
    }

    @Test
    void part2() {
        var inputString = """
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
                """;
        var input = inputString.lines().toList();

        assertEquals(34, Day8.part2(input));
    }
}