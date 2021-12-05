def part1(measurements):
    increases = 0
    for i in range(len(measurements) - 1):
        if measurements[i + 1] > measurements[i]:
            increases += 1

    return increases


def part2(measurements):
    windows = [sum(measurements[i:i+3]) for i in range(len(measurements) - 2)]
    return part1(windows)


if __name__ == '__main__':
    with open('input.txt') as f:
        measurements = [int(m.strip()) for m in f.readlines()]
    
    print(part1(measurements))
    print(part2(measurements))
