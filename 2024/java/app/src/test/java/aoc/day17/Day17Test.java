package aoc.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    @Test
    void part1() {
        var input = """
                Register A: 729
                Register B: 0
                Register C: 0
                                
                Program: 0,1,5,4,3,0""".lines().toList();

        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.part1(input));
    }

    @Test
    void part2() {
    }
}