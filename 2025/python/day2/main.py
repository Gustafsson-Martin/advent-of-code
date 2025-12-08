


def part_one():
    line = None
    with open('input.txt', 'r') as file:
        line = file.readline()
    ranges = line.split(",")

    invalid_id_sum = 0

    for id_range in ranges:
        low, high = id_range.split("-")
        low, high = int(low), int(high)
        for id in range(low, high+1):
            id_str = str(id)
            if len(id_str) % 2 != 0:
                continue
            left, right = id_str[:len(id_str)//2], id_str[len(id_str)//2:]
            if left == right:
                invalid_id_sum += id

    print(invalid_id_sum)

def part_two():
    line = None
    with open('input.txt', 'r') as file:
        line = file.readline()
    ranges = line.split(",")

    invalid_id_sum = 0

    for id_range in ranges:
        low, high = id_range.split("-")
        low, high = int(low), int(high)
        for id in range(low, high+1):
            if is_invalid_part_two(id):
                invalid_id_sum += id

    print(invalid_id_sum)


def is_invalid_part_two(id):
    id_str = str(id)
    for sequence_size in range(1, len(id_str)//2 + 1):
        if len(id_str) % sequence_size != 0:
            continue
        sequence = id_str[:sequence_size]
        repetitions = len(id_str) // sequence_size
        sequence_repeated = sequence * repetitions
        if id_str == sequence_repeated:
            return True

    return False


if __name__ == '__main__':
    part_one()
    part_two()