package aoc.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void part1() {
        var input = List.of("2333133121414131402");

        assertEquals(1928, Day9.part1(input));
    }

    @Test
    void part2() {
        var input = List.of("2333133121414131402");

        assertEquals(2858, Day9.part2(input));
    }
}