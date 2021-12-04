from collections import Counter
from typing import Callable

import numpy as np


def binary_to_int(binary: str) -> int:
    return int(binary, base=2)


# -------------
# PART 1
# -------------


def find_gamma_bits(readings: list):
    gamma_bits = ''

    readings_transposed = np.transpose(readings)
    for reading in readings_transposed:
        bit_counts = Counter(reading)
        gamma_bits += bit_counts.most_common(1)[0][0]

    return gamma_bits


def find_epsilon_bits(gamma_bits: str):
    epsilon_bits = ''

    for bit in gamma_bits:
        epsilon_bits += str(int(not bool(int(bit))))

    return epsilon_bits


def part1(readings: list):
    gamma_bits = find_gamma_bits(readings)
    epsilon_bits = find_epsilon_bits(gamma_bits)

    return binary_to_int(gamma_bits) * binary_to_int(epsilon_bits)

# -------------
# PART 2
# -------------


def mcb_criteria(bits: list):
    bit_counts = Counter(bits)
    if len(bit_counts) > 1 and len(set(bit_counts.values())) == 1:
        return '1'
    return sorted(bit_counts.items(), key=lambda count: count[1], reverse=True)[0][0]


def lcb_criteria(bits: list):
    bit_counts = Counter(bits)
    if len(bit_counts) > 1 and len(set(bit_counts.values())) == 1:
        return '0'
    return sorted(bit_counts.items(), key=lambda count: count[1])[0][0]


def find_rating_bits(readings: list, criteria: Callable, position: int = 0):
    if not readings:
        return None
    if len(readings) == 1:
        return readings[0]
    if position >= len(readings[0]):
        return None

    bits = np.transpose(readings)[position]
    bit = criteria(bits)
    filtered_readings = list(filter(lambda b: b[position] == bit, readings))

    return find_rating_bits(filtered_readings, criteria, position + 1)


def part2(readings: list):
    oxygen_generator_rating_bits = find_rating_bits(readings, mcb_criteria)
    co2_scrubber_rating_bits = find_rating_bits(readings, lcb_criteria)

    return binary_to_int(''.join(oxygen_generator_rating_bits)) * binary_to_int(''.join(co2_scrubber_rating_bits))


if __name__ == '__main__':
    with open('input.txt') as f:
        readings = [list(l.strip()) for l in f.readlines()]

    print(part1(readings))
    print(part2(readings))
