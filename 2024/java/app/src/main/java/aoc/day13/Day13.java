package aoc.day13;

import aoc.Util;

import java.util.List;

public class Day13 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day13/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    private static class Point {
        public long x;
        public long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static long part1(List<String> input) {
        long sum = 0;

        for (int line = 0; line < input.size(); line += 4) {
            String buttonALine = input.get(line);
            String buttonBLine = input.get(line+1);
            String priceLine = input.get(line+2);

            Point buttonA = new Point(0, 0);
            buttonA.x = Long.parseLong(buttonALine.substring(buttonALine.indexOf('+'), buttonALine.indexOf(',')));
            buttonA.y = Long.parseLong(buttonALine.substring(buttonALine.lastIndexOf('+')));

            Point buttonB = new Point(0, 0);
            buttonB.x = Long.parseLong(buttonBLine.substring(buttonBLine.indexOf('+'), buttonBLine.indexOf(',')));
            buttonB.y = Long.parseLong(buttonBLine.substring(buttonBLine.lastIndexOf('+')));

            Point target = new Point(0, 0);
            target.x = Long.parseLong(priceLine.substring(priceLine.indexOf('=')+1, priceLine.indexOf(',')));
            target.y = Long.parseLong(priceLine.substring(priceLine.lastIndexOf('=')+1));

            sum += cheapestWayToWinPrice(target, buttonA, buttonB);

        }
        return sum;
    }

    private static long cheapestWayToWinPrice(Point target, Point buttonA, Point buttonB) {
        long cheapestCost = 10000000;
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100; b++) {
                boolean equalInX = target.x == (a*buttonA.x + b*buttonB.x);
                boolean equalInY = target.y == (a*buttonA.y + b*buttonB.y);
                long cost = a*3 + b;
                if (equalInX && equalInY) {
                    if (cost < cheapestCost) {
                        cheapestCost = cost;
                    }
                }
            }
        }
        if (cheapestCost == 10000000) {
            return 0;
        }
        return cheapestCost;
    }


    public static long part2(List<String> input) {
        long sum = 0;

        for (int line = 0; line < input.size(); line += 4) {
            String buttonALine = input.get(line);
            String buttonBLine = input.get(line+1);
            String priceLine = input.get(line+2);

            Point buttonA = new Point(0, 0);
            buttonA.x = Long.parseLong(buttonALine.substring(buttonALine.indexOf('+'), buttonALine.indexOf(',')));
            buttonA.y = Long.parseLong(buttonALine.substring(buttonALine.lastIndexOf('+')));

            Point buttonB = new Point(0, 0);
            buttonB.x = Long.parseLong(buttonBLine.substring(buttonBLine.indexOf('+'), buttonBLine.indexOf(',')));
            buttonB.y = Long.parseLong(buttonBLine.substring(buttonBLine.lastIndexOf('+')));

            Point target = new Point(0, 0);
            target.x = Long.parseLong(priceLine.substring(priceLine.indexOf('=')+1, priceLine.indexOf(',')));
            target.y = Long.parseLong(priceLine.substring(priceLine.lastIndexOf('=')+1));
            target.x += 10000000000000L;
            target.y += 10000000000000L;

            sum += cheapestWayToWinPrice2(target, buttonA, buttonB);

        }
        return sum;
    }

    private static long cheapestWayToWinPrice2(Point target, Point buttonA, Point buttonB) {
        long cheapestCost = 10000000;
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100; b++) {
                boolean equalInX = target.x == (a*buttonA.x + b*buttonB.x);
                boolean equalInY = target.y == (a*buttonA.y + b*buttonB.y);
                long cost = a*3 + b;
                if (equalInX && equalInY) {
                    if (cost < cheapestCost) {
                        cheapestCost = cost;
                    }
                }
            }
        }
        if (cheapestCost == 10000000) {
            return 0;
        }
        return cheapestCost;
    }

}
