package aoc.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day6 {
    public static void main(String[] args) {
        List<String> input = readInput();

        System.out.println("part 1: " + part1(input));
        System.out.println("part 2: " + part2(input));
    }

    private enum Cell {
        EMPTY,
        WALL,
        PLAYER,
        VISITED,
        OUTSIDE,
    }

    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT;

        private Direction rotate() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }

    private record Cord (int x, int y) {
        @Override
        public int hashCode() {
            return y*1000 + x;
        }

        public Cord next(Direction direction) {
            return switch (direction) {
                case UP -> new Cord(x, y - 1);
                case RIGHT -> new Cord(x + 1, y);
                case DOWN -> new Cord(x, y + 1);
                case LEFT -> new Cord(x - 1, y);
            };
        }
    }

    private static class Guard {
        public Direction direction;
        public Cord cord;

        public Guard(int x, int y, Direction direction) {
            this.direction = direction;
            this.cord = new Cord(x, y);
        }
    }

    private static class MappedArea {
        private final Cell[][] cells;
        private final int width;
        private final int height;
        private Guard guard;

        MappedArea(List<String> input) {
            width = input.getFirst().length();
            height = input.size();
            cells = new Cell[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char c = input.get(y).charAt(x);
                    if (c == '^') {
                        guard = new Guard(x, y, Direction.UP);
                    }
                    cells[y][x] = switch (c) {
                        case '.' -> Cell.EMPTY;
                        case '#' -> Cell.WALL;
                        case '^' -> Cell.PLAYER;
                        default -> throw new IllegalArgumentException("Invalid cell type: " + c);
                    };
                }
            }
        }

        public long countVisited() {
            return Arrays.stream(cells)
                    .flatMap(Arrays::stream)
                    .filter(cell -> cell == Cell.VISITED)
                    .count();
        }

        public boolean guardIsWithinMappedArea() {
            return withinMappedArea(guard.cord);
        }

        public boolean withinMappedArea(Cord cord) {
            return cord.x >= 0 && cord.x < width && cord.y >= 0 && cord.y < height;
        }

        private Cell at(Cord cord) {
            return cells[cord.y][cord.x];
        }

        private void setCell(Cord cord, Cell cell) {
            cells[cord.y][cord.x] = cell;
        }

        public void step() {
            Cord newCord = guard.cord.next(guard.direction);
            if (withinMappedArea(newCord) && at(newCord) == Cell.WALL) {
                guard.direction = guard.direction.rotate();
                return;
            }

            setCell(guard.cord, Cell.VISITED);
            guard.cord = newCord;
            if (guardIsWithinMappedArea()) {
                setCell(guard.cord, Cell.PLAYER);
            }
        }

        public Set<Cord> getVisitedCells() {
            while (guardIsWithinMappedArea()) {
                step();
            }
            Set<Cord> visitedCells = new HashSet<>();
            for (int y = 0; y < cells.length; y++) {
                for (int x = 0; x < cells[0].length; x++) {
                    if (cells[y][x] == Cell.VISITED) {
                        visitedCells.add(new Cord(x, y));
                    }
                }
            }
            return visitedCells;
        }

        public boolean containsLoop() {
            int stepCount = 0;
            while (guardIsWithinMappedArea()) {
                step();
                if (++stepCount > 10000) {
                    return true;
                }
            }
            return false;
        }
    }

    public static List<String> readInput() {
        List<String> lines = new ArrayList<>(List.of());

        URL inputFile = Day6.class.getResource("/aoc/day6/input.txt");
        if (inputFile == null) {
            throw new IllegalArgumentException("input.txt not found!");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.openStream()));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public static long part1(List<String> input) {
        MappedArea mappedArea = new MappedArea(input);
        while (mappedArea.guardIsWithinMappedArea()) {
            mappedArea.step();
        }
        return mappedArea.countVisited();
    }

    public static long part2(List<String> input) {
        int obstructionPositions = 0;
        MappedArea mappedArea = new MappedArea(input);
        Set<Cord> possibleObstaclePlacements = mappedArea.getVisitedCells();
        for (Cord cord : possibleObstaclePlacements) {
            mappedArea = new MappedArea(input);
            mappedArea.setCell(cord, Cell.WALL);
            if (mappedArea.containsLoop()) {
                obstructionPositions++;
            }
        }
        return obstructionPositions;
    }
}
