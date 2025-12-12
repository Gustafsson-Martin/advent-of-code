from typing import List, Self
from math import sqrt
from collections import Counter

class JunctionBox():
    def __init__(self, x: int, y: int, z: int):
        self.x = x
        self.y = y
        self.z = z
        self.next: Self = None
        self.prev: Self = None

    def distance(self, other: Self) -> float:
        return sqrt((self.x - other.x)**2 + (self.y - other.y)**2 + (self.z - other.z)**2)
    
    def __eq__(self, other: Self) -> bool:
        return self.x == other.x and self.y == other.y and self.z == other.z
    
    def __hash__(self) -> str:
        return hash((self.x, self.y, self.z))
    
    def __repr__(self):
        return f"JunctionBox(x={self.x}, y={self.y}, z={self.z})"
    
    def circuit_contains(self, other: Self) -> bool:
        curr = self
        while curr.prev is not None:
            curr = curr.prev
            if curr == other:
                return True
            
        curr = self
        while curr.next is not None:
            curr = curr.next
            if curr == other:
                return True
            
        return False

    def add_to_circuit(self, other: Self) -> bool:
        curr = self
        while curr.next is not None:
            curr = curr.next
        
        while other.prev is not None:
            other = other.prev

        curr.next = other
        other.prev = curr

    def circuit_head(self) -> Self:
        curr = self
        while curr.prev is not None:
            curr = curr.prev

        return curr
    
    def circuit_size(self) -> int:
        head = self.circuit_head()
        size = 1
        curr = head
        while curr.next is not None:
            curr = curr.next
            size += 1
        return size


def part_one(filename: str, connections: int) -> str|float|int:
    lines = read_lines(filename)
    junctions_boxes: List[JunctionBox] = []
    for line in lines:
        cords = [int(x) for x in line.split(',')]
        junctions_boxes.append(JunctionBox(*cords))
    
    distances = []
    for i in range(len(junctions_boxes)):
        for j in range(i+1, len(junctions_boxes)):
            distance = junctions_boxes[i].distance(junctions_boxes[j])
            distances.append(((i, j), distance))

    distances = sorted(distances, key=lambda x : x[1])

    for (box1_idx, box2_idx), _ in distances[:connections]:
        box1, box2 = junctions_boxes[box1_idx], junctions_boxes[box2_idx]
        if box1.circuit_contains(box2):
            continue
        box1.add_to_circuit(box2)

    circuit_sizes = Counter()
    for box in junctions_boxes:
        circuit_sizes[box.circuit_head()] = box.circuit_size()

    sorted_sizes = sorted(circuit_sizes.values(), reverse=True)

    return sorted_sizes[0] * sorted_sizes[1] * sorted_sizes[2]


def part_two(filename: str, total_boxes: int) -> str|float|int:
    lines = read_lines(filename)
    junctions_boxes: List[JunctionBox] = []
    for line in lines:
        cords = [int(x) for x in line.split(',')]
        junctions_boxes.append(JunctionBox(*cords))
    
    distances = []
    for i in range(len(junctions_boxes)):
        for j in range(i+1, len(junctions_boxes)):
            distance = junctions_boxes[i].distance(junctions_boxes[j])
            distances.append(((i, j), distance))

    distances = sorted(distances, key=lambda x : x[1], reverse=True)

    circuits_joined = 0
    box1, box2 = None, None
    while circuits_joined < total_boxes - 1:
        box1_idx, box2_idx = distances.pop()[0]
        box1, box2 = junctions_boxes[box1_idx], junctions_boxes[box2_idx]
        if box1.circuit_contains(box2):
            continue
        box1.add_to_circuit(box2)
        circuits_joined += 1

    return box1.x * box2.x


def read_lines(filename: str) -> List[str]:
    lines = []
    with open(filename, 'r') as file:
        for line in file:
            lines.append(line.strip())
    return lines


if __name__ == '__main__':
    print(f"Part 1 test: {part_one('test.txt', 10)}")
    print(f"Part 2 test: {part_two('test.txt', 20)}")
    print(f"Part 1: {part_one('input.txt', 1000)}")
    print(f"Part 2: {part_two('input.txt', 1000)}")
