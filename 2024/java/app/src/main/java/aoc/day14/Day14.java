package aoc.day14;

import aoc.Cord;
import aoc.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        List<String> input = Util.parseInputResource("/aoc/day14/input.txt");

        System.out.println("Part 1: " + part1(input, 101, 103));
        System.out.println("Part 2: " + part2(input, 101, 103));
    }

    private static class Robot {
        public Cord position;
        public Cord velocity;

        Robot(int x, int y, int dx, int dy) {
            this.position = new Cord(x, y);
            this.velocity = new Cord(dx, dy);
        }

        @Override
        public String toString() {
            return "Robot{position=" + position + ", velocity=" + velocity + "}";
        }
    }

    public static long part1(List<String> input, int width, int height) {
        List<Robot> robots = new java.util.ArrayList<>(List.of());

        for (String line : input) {
            int x = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(",")));
            int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));
            int dx = Integer.parseInt(line.substring(line.lastIndexOf("=") + 1, line.lastIndexOf(",")));
            int dy = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));
            if (dx < 0) {
                dx = dx + width;
            }
            if (dy < 0) {
                dy = dy + height;
            }

            robots.add(new Robot(x, y, dx, dy));
        }

        for (Robot robot : robots) {
            int newX = (robot.position.x + 100 * robot.velocity.x) % width;
            int newY = (robot.position.y + 100 * robot.velocity.y) % height;
            robot.position = new Cord(newX, newY);
        }

        List<Integer> quadrantCount = new ArrayList<>(List.of(0, 0, 0, 0));
        for (Robot robot : robots) {
            if (robot.position.x < width / 2 && robot.position.y < height / 2) {
                quadrantCount.set(0, quadrantCount.getFirst() + 1);
            } else if (robot.position.x > width / 2 && robot.position.y < height / 2) {
                quadrantCount.set(1, quadrantCount.get(1) + 1);
            } else if (robot.position.x < width / 2 && robot.position.y > height / 2) {
                quadrantCount.set(2, quadrantCount.get(2) + 1);
            } else if (robot.position.x > width / 2 && robot.position.y > height / 2) {
                quadrantCount.set(3, quadrantCount.get(3) + 1);
            }
        }

        return quadrantCount.stream().reduce(1, (a, b) -> a * b);
    }

    private static class DrawRect extends JPanel {
        private final int RECT_SIZE = 10;
        private final List<Robot> robots;
        private final int width;
        private final int height;

        public DrawRect(List<Robot> robots, int width, int height) {
            this.robots = robots;
            this.width = width;
            this.height = height;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Cord position = new Cord(x, y);
                    if (robots.stream().anyMatch((robot) -> robot.position.equals(position))) {
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(RECT_SIZE*x, RECT_SIZE*y, RECT_SIZE, RECT_SIZE);
                }
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension( 1000, 1000);
        }
    }

    public static long part2(List<String> input, int width, int height) {
        List<Robot> robots = new java.util.ArrayList<>(List.of());

        for (String line : input) {
            int x = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(",")));
            int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));
            int dx = Integer.parseInt(line.substring(line.lastIndexOf("=") + 1, line.lastIndexOf(",")));
            int dy = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));
            if (dx < 0) {
                dx = dx + width;
            }
            if (dy < 0) {
                dy = dy + height;
            }

            robots.add(new Robot(x, y, dx, dy));
        }

        DrawRect mainPanel = new DrawRect(robots, width, height);
        JFrame frame = new JFrame("DrawRect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        JPanel buttonPanel = new JPanel();
        JButton nextButton = new JButton("Next Iteration");
        buttonPanel.add(nextButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        for (Robot robot : robots) {
            int newX = (robot.position.x + 9 * robot.velocity.x) % width;
            int newY = (robot.position.y + 9 * robot.velocity.y) % height;
            robot.position = new Cord(newX, newY);
        }

        nextButton.addActionListener(new ActionListener() {
            private int secondsPassed = 9;

            @Override
            public void actionPerformed(ActionEvent e) {
                for (Robot robot : robots) {
                    int newX = (robot.position.x + 101 * robot.velocity.x) % width;
                    int newY = (robot.position.y + 101 * robot.velocity.y) % height;
                    robot.position = new Cord(newX, newY);
                }

                secondsPassed += 101;
                System.out.println("Seconds passed: " + secondsPassed);
                System.out.println("----------");

                mainPanel.repaint();
            }
        });

        return 0;
    }
}
