
operations = {
    'L': lambda x, y : x - y,
    'R': lambda x, y : x + y
}

def part_one():
    dial_value = 50
    zero_counter = 0
    with open('input.txt', 'r') as file:
        for line in file:
            value = int(line[1:])
            operation = operations[line[0]]

            dial_value = operation(dial_value, value) % 100

            if dial_value == 0:
                zero_counter += 1
    print(zero_counter)


def part_two():
    dial_value = 50
    zero_counter = 0
    with open('input.txt', 'r') as file:
        for line in file:
            value = int(line[1:])
            operation = operations[line[0]]
            for _ in range(value):
                dial_value = operation(dial_value, 1) % 100

                if dial_value == 0:
                    zero_counter += 1
    print(zero_counter)



if __name__ == '__main__':
    part_one()
    part_two()