package aoc.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void part1() {
        var inputString = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
                """;
        var input = inputString.lines().toList();

        assertEquals(41, Day6.part1(input));
    }

    @Test
    void part2() {
        var inputString = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
                """;
        var input = inputString.lines().toList();

        assertEquals(6, Day6.part2(input));
    }

    @Test
    void part2_2() {
        var inputString = """
#.........
.........#
.........#
.#........
^.......#.
                """;
        var input = inputString.lines().toList();

        assertEquals(1, Day6.part2(input));
    }

    @Test
    void part2_3() {
        var inputString = """
....#...#.
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
                """;
        var input = inputString.lines().toList();

        assertEquals(8, Day6.part2(input));
    }
}