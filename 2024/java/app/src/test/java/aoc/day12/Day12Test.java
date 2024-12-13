package aoc.day12;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void part1() {
        List<String> input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """.lines().toList();

        assertEquals(140, Day12.part1(input));
    }

    @Test
    void part2() {
        List<String> input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """.lines().toList();

        assertEquals(80, Day12.part2(input));
    }
}