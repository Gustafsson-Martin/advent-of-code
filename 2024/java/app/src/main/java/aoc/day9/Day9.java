package aoc.day9;

import aoc.Util;

import java.util.List;
import java.util.Optional;

public class Day9 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day9/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    private static class Block {
        public int size;
        public Optional<Integer> id;

        Block(int size, Optional<Integer> id) {
            this.size = size;
            this.id = id;
        }
    }

    public static long part1(List<String> input) {
        List<Block> blocks = parseInput(input);

        int leftIdx = 0;
        int rightIdx = blocks.size() - 1;

        while (leftIdx < rightIdx) {
            Block left = blocks.get(leftIdx);
            while (left.id.isPresent()) {
                leftIdx++;
                left = blocks.get(leftIdx);
            }

            Block right = blocks.get(rightIdx);
            while (right.id.isEmpty() || right.size == 0) {
                rightIdx--;
                right = blocks.get(rightIdx);
            }

            if (right.size >= left.size) {
                right.size -= left.size;
                left.id = right.id;
            } else {
                Block newBlock = new Block(left.size - right.size, Optional.empty());
                blocks.add(leftIdx + 1, newBlock);
                left.size = right.size;
                left.id = right.id;
                right.size = 0;
            }
        }

        return checksum(blocks);
    }



    public static long part2(List<String> input) {
        List<Block> blocks = parseInput(input);

        for (int rightIdx = blocks.size() - 1; rightIdx > 1; rightIdx--) {
            Block right = blocks.get(rightIdx);
            if (right.id.isEmpty()) {
                continue;
            }
            for (int leftIdx = 0; leftIdx < rightIdx; leftIdx++) {
                Block left = blocks.get(leftIdx);
                if (left.id.isPresent()) {
                    continue;
                }

                if (left.size == right.size) {
                    left.id = right.id;
                    right.id = Optional.empty();
                    break;
                } else if (left.size > right.size) {
                    Block newBlock = new Block(left.size - right.size, Optional.empty());
                    blocks.add(leftIdx + 1, newBlock);
                    left.size = right.size;
                    left.id = right.id;
                    right.id = Optional.empty();
                    break;
                }
            }
        }

        return checksum(blocks);
    }

    private static List<Block> parseInput(List<String> input) {
        List<Block> blocks = new java.util.ArrayList<>(List.of());
        int blockId = 0;
        for (int i = 0; i < input.getFirst().length(); i++) {
            Block block = new Block(Character.getNumericValue(input.getFirst().charAt(i)), Optional.empty());
            if (i % 2 == 0) {
                block.id = Optional.of(blockId);
                blockId++;
            }
            blocks.add(block);
        }
        return blocks;
    }

    private static long checksum(List<Block> blocks) {
        long sum = 0;
        long idx = 0;
        for (Block block : blocks) {
            while(block.size > 0) {
                sum += idx * block.id.orElse(0);
                block.size--;
                idx++;
            }
        }

        return sum;
    }
}
