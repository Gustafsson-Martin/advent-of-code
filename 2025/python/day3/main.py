from typing import List


def part_one(filename: str):
    lines = read_lines(filename)
    total_battery_sum = 0
    for line in lines:
        first_battery_idx = -1
        first_battery_value = -1
        for idx in range(0, len(line) - 1):
            value = int(line[idx])
            if value > first_battery_value:
                first_battery_value = value
                first_battery_idx = idx

        second_battery_value = -1
        for idx in range(first_battery_idx+1, len(line)):
            value = int(line[idx])
            if value > second_battery_value:
                second_battery_value = value
    
        total_battery_sum += 10*first_battery_value + second_battery_value

    return total_battery_sum
        
        


def part_two(filename: str):
    lines = read_lines(filename)
    total_battery_sum = 0
    for line in lines:
        battery_indicies = []
        battery_values = []

        for i in range(12):
            prev_low = battery_indicies[-1] if battery_indicies else -1
            low = prev_low + 1
            battery_indicies.append(low)
            battery_values.append(int(line[low]))
            
            remaining_batteries_to_turn_on = 11 - i
            high = len(line) - remaining_batteries_to_turn_on

            for idx in range(low, high):
                value = int(line[idx])
                if value > battery_values[i]:
                    battery_values[i] = value
                    battery_indicies[i] = idx

            total_battery_sum += battery_values[i] * pow(10, 11-i)

    return total_battery_sum


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
