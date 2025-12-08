from typing import List, Tuple


def part_one(filename: str):
    lines = read_lines(filename)

    rolls_accessible = 0

    matrix = lines
    width = len(matrix[0])
    height = len(matrix)

    for x in range(width):
        for y in range(height):
            if matrix[y][x] != '@':
                continue
            neighbour_counter = 0
            for dx, dy in all_eight_directions():
                if is_inside_matrix(x+dx, y+dy, matrix) and matrix[y+dy][x+dx] == '@':
                    neighbour_counter += 1
            if neighbour_counter < 4:
                rolls_accessible += 1
    
    return rolls_accessible



def part_two(filename: str):
    lines = read_lines(filename)

    rolls_accessible = 0
    previous_rolls_accessible = -1

    matrix = [list(line) for line in lines]
    width = len(matrix[0])
    height = len(matrix)

    while previous_rolls_accessible != rolls_accessible:
        previous_rolls_accessible = rolls_accessible
        for x in range(width):
            for y in range(height):
                if matrix[y][x] != '@':
                    continue
                neighbour_counter = 0
                for dx, dy in all_eight_directions():
                    if is_inside_matrix(x+dx, y+dy, matrix) and matrix[y+dy][x+dx] == '@':
                        neighbour_counter += 1
                if neighbour_counter < 4:
                    rolls_accessible += 1
                    matrix[y][x] = '.'
    
    return rolls_accessible


def is_inside_matrix(x, y, matrix):
    width = len(matrix[0])
    height = len(matrix)
    return x >= 0 and x < width and y >= 0 and y < height


def all_eight_directions() -> List[Tuple]:
    return [
        (-1, -1),
        (0, -1),
        (1, -1),
        (-1, 0),
        (1, 0),
        (-1, 1),
        (0, 1),
        (1, 1)
    ]


def read_lines(filename: str) -> List[str]:
    lines = []
    with open(filename, 'r') as file:
        for line in file:
            lines.append(line.strip())
    return lines


if __name__ == '__main__':
    print(f"Part 1 test: {part_one('test.txt')}")
    print(f"Part 2 test: {part_two('test.txt')}")
    print(f"Part 1: {part_one('input.txt')}")
    print(f"Part 2: {part_two('input.txt')}")
