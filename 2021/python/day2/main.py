COMMAND_UNITS = {
    'forward': (1, 0),
    'down': (0, 1),
    'up': (0, -1)
}


def part1(commands):
    position = [0, 0]

    for cmd, value in commands:
        position[0] += COMMAND_UNITS[cmd][0] * value
        position[1] += COMMAND_UNITS[cmd][1] * value
    
    return position[0] * position[1]


def part2(commands):
    position = [0, 0]
    aim = 0

    for cmd, value in commands:
        if cmd == 'up':
            aim -= value
        elif cmd == 'down':
            aim += value
        else:
            position[0] += value
            position[1] += aim * value
    
    return position[0] * position[1]


if __name__ == '__main__':
    with open('input.txt') as f:
        commands = []
        for line in f.readlines():
            cmd, value = line.strip().split()
            commands.append((cmd, int(value)))
    
    print(part1(commands))
    print(part2(commands))
