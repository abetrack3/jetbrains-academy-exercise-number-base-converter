package TicTacToeWithAI;

public class GameStatusChecker {

    static String checkGameStatus(char[][] board) {
        if (isImpossible(board)) {
            return "Impossible";
        } else if (isDraw(board)) {
            return "Draw";
        } else if (isWinner(board, 'X')) {
            return "X wins";
        } else if (isWinner(board, 'O')) {
            return "O wins";
        } else {
            return "Game not finished";
        }
    }

    static boolean isDraw(char[][] board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case 'X', 'O' -> count++;
                }
            }
        }
        return count == 9 && (isWinner(board, 'X') == isWinner(board, 'O'));
    }

    static boolean isImpossible(char[][] board) {
        return checkDoubleStep(board) || (isWinner(board, 'X') && isWinner(board, 'O'));
    }

    static boolean isWinner(char[][] board, char player) {
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

    static boolean checkDoubleStep(char[][] board) {
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
