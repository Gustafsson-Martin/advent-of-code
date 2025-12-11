from typing import List


def part_one(filename: str) -> str|float|int:
    lines = read_lines(filename)
    
    fresh_ranges = []
    idx = 0
    while lines[idx] != '':
        fresh_ranges.append([int(x) for x in lines[idx].split('-')])
        idx += 1

    idx += 1

    fresh_counter = 0
    while idx < len(lines):
        if is_fresh(int(lines[idx]), fresh_ranges):
            fresh_counter += 1 
        idx += 1        

    return fresh_counter


def is_fresh(ingredient: int, fresh_ranges: List[List[int]]):
    for _min, _max in fresh_ranges:
        if _min <= ingredient <= _max:
            return True
    return False


def part_two(filename: str) -> str|float|int:
    lines = read_lines(filename)
    
    fresh_ranges = []
    idx = 0
    while lines[idx] != '':
        fresh_ranges.append([int(x) for x in lines[idx].split('-')])
        idx += 1

    fresh_ranges_sorted = sorted(fresh_ranges, key=lambda x : x[0]) 

    non_overlap_fresh_ranges = [fresh_ranges_sorted[0]]

    for idx in range(1, len(fresh_ranges_sorted)):
        previous_max = non_overlap_fresh_ranges[-1][1]
        current_min, current_max = fresh_ranges_sorted[idx]
        non_overlap_min = max(previous_max + 1, current_min)
        non_overlap_max = current_max

        if non_overlap_min > non_overlap_max:
            continue

        non_overlap_fresh_ranges.append([non_overlap_min, non_overlap_max])

    fresh_counter = 0

    for _min, _max in non_overlap_fresh_ranges:
        fresh_counter += _max - _min + 1
    
    return fresh_counter


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
