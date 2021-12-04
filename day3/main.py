from collections import Counter

import numpy as np


def find_gamma(bits: np.ndarray):
    gamma_bits = ''

    bits_transposed = np.transpose(bits)
    for reading in bits_transposed:
        bit_counts = Counter(reading)
        gamma_bits += bit_counts.most_common(1)[0][0]

    return gamma_bits


def find_epsilon(gamma_bits: str):
    epsilon_bits = ''

    for bit in gamma_bits:
        epsilon_bits += str(int(not bool(int(bit))))
    
    return epsilon_bits


def part1(bits: np.ndarray):
    gamma_bits = find_gamma(bits)
    epsilon_bits = find_epsilon(gamma_bits)

    return int(gamma_bits, base=2) * int(epsilon_bits, base=2)


if __name__ == '__main__':
    with open('input.txt') as f:
        bits = [np.array(list(l.strip())) for l in f.readlines()]

    print(part1(bits))
