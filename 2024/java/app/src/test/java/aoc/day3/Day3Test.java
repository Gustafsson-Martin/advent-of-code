package aoc.day3;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
        @org.junit.jupiter.api.Test
        void sumMultiplications() {
            String input = "mul(2,3)mul(3,4)mul(111,111)";
            int result = Day3.sumMultiplications(input);
            assertEquals(12339, result);
        }

        @org.junit.jupiter.api.Test
        void sumMultiplications2() {
            String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
            int result = Day3.sumMultiplications(input);
            assertEquals(161, result);
        }

        @org.junit.jupiter.api.Test
        void sumMultiplicationsPart2() {
            String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
            int result = Day3.sumMultiplicationsPart2(input);
            assertEquals(48, result);
        }
}