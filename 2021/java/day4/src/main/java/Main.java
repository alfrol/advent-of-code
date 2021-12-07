import model.Board;
import utils.FileUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String input = FileUtils.readFile(Main.class, "/input.txt").orElseThrow();
        String[] components = input.split("\n", 2);

        LinkedList<Integer> numbers = Arrays.stream(components[0].split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        Board[] boards = Arrays.stream(components[1].trim().split("\n\n"))
                .map(Board::fromString)
                .toArray(Board[]::new);

        Board winningBoard = getWinningBoard(numbers, boards);

        if (winningBoard != null) {
            System.out.println(winningBoard.getSumOfUnmarkedValues() * numbers.getFirst());
        }
    }

    private static Board getWinningBoard(LinkedList<Integer> numbers, Board[] boards) {
        while (!numbers.isEmpty()) {
            int num = numbers.peekFirst();

            for (Board board : boards) {
                board.markValue(num);
                if (board.isWinningBoard()) {
                    return board;
                }
            }

            numbers.removeFirst();
        }
        return null;
    }
}
