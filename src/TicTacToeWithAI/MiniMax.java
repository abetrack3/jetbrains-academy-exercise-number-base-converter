package TicTacToeWithAI;

public class MiniMax {
    
    private int row, col;
    private final char[][] board;
    
    private MiniMax(char[][] board) {
        row = col = -1;
        this.board = board;
    }

    public static int[] getResult(GameBoard board, char symbol) {
        char[][] tempBoard = new char[3][3];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                tempBoard[i - 1][j - 1] = board.get(i, j);
            }
        }
        return new MiniMax(tempBoard).run(symbol);
    }
    
    private int[] run(char symbol) {
        int score = Integer.MIN_VALUE;
        char opponent = symbol == 'X' ? 'O' : 'X';
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = symbol;
                    int tempScore = miniMax(board, false, opponent, 1, symbol);
                    board[row][col] = ' ';
                    if (tempScore > score) {
                        score = tempScore;
                        this.row = row;
                        this.col = col;
                    }
                }
            }
        }
        return new int[]{this.row + 1, this.col + 1};
    }
    
    private int miniMax(char[][] board, boolean minMax, char symbol, int depth, char initialSymbol) {
        switch (GameStatusChecker.checkGameStatus(board)) {
            case "X wins" -> {
                return initialSymbol == 'X' ? 10 - depth : depth - 10;
            }
            case "O wins" -> {
                return initialSymbol == 'O' ? 10 - depth : depth - 10;
            }
            case "Draw" -> {
                return  0;
            }
        }

        int score = minMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char opponent = symbol == 'X' ? 'O' : 'X';

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = symbol;
                    int tempScore = miniMax(board, !minMax, opponent, depth + 1, initialSymbol);
                    board[row][col] = ' ';
                    score = minMax ? Math.max(score, tempScore) : Math.min(score, tempScore);
                }
            }
        }

        return score;
    }
    
}
