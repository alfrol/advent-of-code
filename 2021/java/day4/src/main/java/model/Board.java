package model;

import java.util.Arrays;

public class Board {

    private final int[][] board;
    private final int[][] markedBoard;

    public Board(int[][] board) {
        this.board = Arrays.stream(board)
                .map(int[]::clone)
                .toArray(int[][]::new);
        this.markedBoard = new int[board.length][board.length];

        for (int[] row : this.markedBoard) {
            // -1 means empty value, needed for future checking of complete rows/columns
            Arrays.fill(row, -1);
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "\nboard=" + Arrays.deepToString(board) +
                "\nmarkedBoard=" + Arrays.deepToString(markedBoard) +
                "\n}";
    }

    public void markValue(int value) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == value) {
                    markedBoard[row][col] = value;
                }
            }
        }
    }

    public int getSumOfUnmarkedValues() {
        int sum = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (markedBoard[row][col] == -1) {
                    sum += board[row][col];
                }
            }
        }

        return sum;
    }

    public boolean isWinningBoard() {
        return hasCompleteRow() || hasCompleteColumn();
    }

    public static Board fromString(String boardString) {
        String[] rows = boardString.split("\n");
        int[][] board = new int[rows.length][rows.length];

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            int[] rowOfInts = Arrays.stream(row.split(" "))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .mapToInt(Integer::parseInt)
                    .toArray();
            board[i] = rowOfInts;
        }

        return new Board(board);
    }

    private boolean hasCompleteRow() {
        return Arrays.stream(markedBoard)
                .anyMatch(row -> Arrays.stream(row).allMatch(value -> value != -1));
    }

    private boolean hasCompleteColumn() {
        for (int col = 0; col < markedBoard.length; col++) {
            boolean isComplete = true;

            for (int[] row : markedBoard) {
                if (row[col] == -1) {
                    isComplete = false;
                    break;
                }
            }

            if (isComplete) {
                return true;
            }
        }
        return false;
    }
}
