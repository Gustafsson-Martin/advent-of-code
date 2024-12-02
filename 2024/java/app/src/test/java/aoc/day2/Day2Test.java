package aoc.day2;


import org.junit.jupiter.api.Assertions;

import java.util.List;

class Day2Test {
    @org.junit.jupiter.api.Test
    void countSafeReportsWithTolerance() {
        List<List<Integer>> input = List.of(
                List.of(7, 6, 4, 2, 1),
                List.of(1, 2, 7, 8, 9),
                List.of(9, 7, 6, 2, 1),
                List.of(1, 3, 2, 4, 5),
                List.of(8, 6, 4, 4, 1),
                List.of(1, 3, 6, 7, 9)
        );
        int safeReports = Day2.countSafeReportsWithTolerance(input);
        Assertions.assertEquals(4, safeReports);
    }

    @org.junit.jupiter.api.Test
    void isSafeWithTolerance() {
        Assertions.assertTrue(Day2.isSafeWithTolerance(List.of(7, 6, 4, 2, 1)));
        Assertions.assertFalse(Day2.isSafeWithTolerance(List.of(1, 2, 7, 8, 9)));
        Assertions.assertFalse(Day2.isSafeWithTolerance(List.of(9, 7, 6, 2, 1)));
        Assertions.assertTrue(Day2.isSafeWithTolerance(List.of(1, 3, 2, 4, 5)));
        Assertions.assertTrue(Day2.isSafeWithTolerance(List.of(8, 6, 4, 4, 1)));
        Assertions.assertTrue(Day2.isSafeWithTolerance(List.of(1, 3, 6, 7, 9)));
    }
}