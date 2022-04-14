package TicTacToeWithAI;

import java.util.Arrays;

public class GameBoard {

    private final char[][] board;

    GameBoard() {
        board = createBoard();
    }

    private char[][] createBoard() {
        int row = 3, column = 3;
        char emptySpace = ' ';
        char[][] board = new char[row][column];
        for (char[] boardRow : board) {
            Arrays.fill(boardRow, emptySpace);
        }
        return board;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int i = 0; i < 3; i++) {
            sb.append("|");
            for (int j = 0; j < 3; j++) {
                sb.append(" ").append(board[i][j]);
            }
            sb.append(" |\n");
        }
        sb.append("---------");
        return sb.toString();
    }

    public char get(int i, int j) {
        return board[i - 1][j - 1];
    }

    public void set(int i, int j, char x) {
        board[i - 1][j - 1] = x;
    }

    public String gameStatus() {
        return GameStatusChecker.checkGameStatus(board);
    }

}
