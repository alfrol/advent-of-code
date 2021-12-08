package ee.alfrol;

import ee.alfrol.model.Board;
import utils.FileUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String input = FileUtils.readFile(Main.class, "/input.txt").orElseThrow();
        String[] components = input.split("\n", 2);

        LinkedList<Integer> numbers = Arrays.stream(components[0].split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
        List<Board> boards = Arrays.stream(components[1].trim().split("\n\n"))
                .map(Board::fromString)
                .collect(Collectors.toList());

        Board winningBoard = getWinningBoard(numbers, boards);

        System.out.println(winningBoard);

        if (winningBoard != null) {
            System.out.println(winningBoard.getSumOfUnmarkedValues() * numbers.getFirst());
        }
    }

    private static Board getWinningBoard(LinkedList<Integer> numbers, List<Board> boards) {
        while (!numbers.isEmpty()) {
            int num = numbers.getFirst();

            for (int i = 0; i < boards.size(); ) {
                Board board = boards.get(i);
                board.markValue(num);
                if (board.isWinningBoard()) {
                    if (boards.size() == 1) {
                        return board;
                    }

                    boards.remove(board);
                } else {
                    i++;
                }
            }

            numbers.removeFirst();
        }
        return null;
    }
}
