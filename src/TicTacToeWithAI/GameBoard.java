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
        if (isImpossible()) {
            return "Impossible";
        } else if (isDraw()) {
            return "Draw";
        } else if (isWinner('X')) {
            return "X wins";
        } else if (isWinner('O')) {
            return "O wins";
        } else {
            return "Game not finished";
        }
    }

    private boolean isDraw() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case 'X', 'O' -> count++;
                }
            }
        }
        return count == 9 && (isWinner('X') == isWinner('O'));
    }

    private boolean isImpossible() {
        return checkDoubleStep() || (isWinner('X') && isWinner('O'));
    }

    private boolean isWinner(char player) {
        // vertical
        for (int i = 0; i < 3; i++) {
            if (player == board[0][i] && player == board[1][i] && player == board[2][i]) {
                return true;
            }
        }

        // horizontal
        for (int i = 0; i < 3; i++) {
            if (player == board[i][0] && player == board[i][1] && player == board[i][2]) {
                return true;
            }
        }

        // diagonal
        if (player == board[0][0] && player == board[1][1] && player == board[2][2]) {
            return true;
        } else return player == board[0][2] && player == board[1][1] && player == board[2][0];
    }

    private boolean checkDoubleStep() {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case 'X' -> xCount++;
                    case 'O' -> oCount++;
                }
            }
        }
        return Math.abs(xCount - oCount) >= 2;
    }

}
