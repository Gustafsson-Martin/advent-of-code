package aoc.day17;

import aoc.Util;

import java.util.ArrayList;
import java.util.List;

public class Day17 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day17/input.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    private static class Instruction {
        public int opcode;
        public int operand;

        Instruction(int opcode, int operand) {
            this.opcode = opcode;
            this.operand = operand;
        }
    }

    private static class Computer {
        private int A;
        private int B;
        private int C;
        private int instructionPointer;
        private List<Instruction> instructions;
        private List<Integer> output;

        Computer(int A, int B, int C, int instructionPointer, List<Instruction> instructions) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.instructionPointer = instructionPointer;
            this.instructions = instructions;
            this.output = new ArrayList<>();
        }

        public void simulate() {
            while (instructionPointer < instructions.size()) {
                Instruction instruction = instructions.get(instructionPointer);
                instructionPointer++;
                switch (instruction.opcode) {
                    case 0 -> {
                        int value = getComboOperandValue(instruction.operand);
                        A = Math.floorDiv(A, (int) Math.pow(2, value));
                    }
                    case 1 -> {
                        int value = instruction.operand;
                        B = B ^ value;
                    }
                    case 2 -> {
                        int value = getComboOperandValue(instruction.operand);
                        B = value % 8;
                    }
                    case 3 -> {
                        if (A != 0) {
                            instructionPointer = instruction.operand;
                        }
                    }
                    case 4 -> {
                        B = B ^ C;
                    }
                    case 5 -> {
                        int value = getComboOperandValue(instruction.operand);
                        output.add(value % 8);
                    }
                    case 6 -> {
                        int value = getComboOperandValue(instruction.operand);
                        B = Math.floorDiv(A, (int) Math.pow(2, value));
                    }
                    case 7 -> {
                        int value = getComboOperandValue(instruction.operand);
                        C = Math.floorDiv(A, (int) Math.pow(2, value));
                    }
                }
            }
        }

        public String getOutputAsString() {
            return String.join(",", output.stream().map(String::valueOf).toList());
        }

        private int getComboOperandValue(int operand) {
            return switch (operand) {
                case 0,1,2,3 -> operand;
                case 4 -> A;
                case 5 -> B;
                case 6 -> C;
                default -> throw new IllegalArgumentException("Invalid operand: " + operand);
            };
        }
    }

    public static String part1(List<String> input) {
        int A = Integer.parseInt(input.getFirst().split(" ")[2]);
        int B = Integer.parseInt(input.get(1).split(" ")[2]);
        int C = Integer.parseInt(input.get(2).split(" ")[2]);
        int instructionPointer = 0;

        String[] programInputList = input.get(4).split(" ")[1].split(",");
        List<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < programInputList.length; i += 2) {
            instructions.add(new Instruction(Integer.parseInt(programInputList[i]), Integer.parseInt(programInputList[i + 1])));
        }

        Computer computer = new Computer(A, B, C, instructionPointer, instructions);
        computer.simulate();

        return computer.getOutputAsString();
    }

    public static String part2(List<String> input) {
        return "";
    }
}
