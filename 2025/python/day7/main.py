from typing import List
from collections import Counter


def part_one(filename: str) -> str|float|int:
    lines = read_lines(filename)
    beam_set = set()
    for idx, char in enumerate(lines[0]):
        if char == 'S':
            beam_set.add(idx)
    
    split_counter = 0

    for line in lines[1:]:
        for idx, char in enumerate(line):
            if char == '^' and idx in beam_set:
                beam_set.remove(idx)
                beam_set.add(idx-1)
                beam_set.add(idx+1)
                split_counter += 1
    
    return split_counter


def part_two(filename: str) -> str|float|int:
    lines = read_lines(filename)
    beam_counter = Counter()
    for idx, char in enumerate(lines[0]):
        if char == 'S':
            beam_counter[idx] = 1

    for line in lines[1:]:
        for idx, char in enumerate(line):
            beam_count = beam_counter[idx]
            if char == '^' and beam_count > 0:
                beam_counter[idx-1] += beam_count
                beam_counter[idx+1] += beam_count
                beam_counter[idx] -= beam_count
    
    return sum(beam_counter.values())


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
