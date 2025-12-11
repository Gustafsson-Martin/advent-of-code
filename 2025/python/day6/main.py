from typing import List




def part_one(filename: str) -> str|float|int:
    lines = read_lines(filename)
    
    problems = []
    for num in lines[0].split():
        problems.append([])

    for line in lines[:-1]:
        for idx, num in enumerate(line.split()):
            problems[idx].append(int(num))
    
    solution_sum = 0

    for problem_idx, operator in enumerate(lines[-1].split()):
        if operator == '*':
            solution = mul(problems[problem_idx])
        else:
            solution = sum(problems[problem_idx])
        solution_sum += solution

    return solution_sum


def mul(lst: List[int]) -> int:
    if len(lst) == 1:
        return lst[0]
    return lst[0] * mul(lst[1:])
    

def part_two(filename: str) -> str|float|int:
    lines = read_lines_raw(filename)
    operator_line = lines[-1]
    number_lines = lines[:-1]

    problems = [[] for _ in operator_line.split()]
    problem_idx = 0
    
    operator = operator_line[0]
    digits = [None for _ in range(len(number_lines))]

    for char_idx in range(len(lines[0])):
        for line_idx, line in enumerate(number_lines):
            digits[line_idx] = line[char_idx]

        num = ''.join(digits).replace(' ', '')
        if num == '':
            problem_idx += 1
        else:
            problems[problem_idx].append(int(num))

    solution_sum = 0

    for problem_idx, operator in enumerate(lines[-1].split()):
        if operator == '*':
            solution = mul(problems[problem_idx])
        else:
            solution = sum(problems[problem_idx])
        solution_sum += solution

    return solution_sum


def read_lines(filename: str) -> List[str]:
    lines = []
    with open(filename, 'r') as file:
        for line in file:
            lines.append(line.strip())
    return lines

def read_lines_raw(filename: str) -> List[str]:
    lines = []
    with open(filename, 'r') as file:
        for line in file:
            lines.append(line.replace('\n', ''))
    return lines


if __name__ == '__main__':
    print(f"Part 1 test: {part_one('test.txt')}")
    print(f"Part 2 test: {part_two('test.txt')}")
    print(f"Part 1: {part_one('input.txt')}")
    print(f"Part 2: {part_two('input.txt')}")
