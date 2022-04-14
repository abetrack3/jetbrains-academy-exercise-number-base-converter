package TicTacToeWithAI;

public final class PlayerBotAI extends Player {

    private enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    private final Difficulty level;

    PlayerBotAI(char symbol, String difficulty) {
        super(symbol);
        this.level = Difficulty.valueOf(difficulty.toUpperCase());
    }

    @Override
    public int[] makeMove(GameBoard board) {
        System.out.printf("Making move level \"%s\"%n", this.level.toString().toLowerCase());
        switch (this.level) {
            case EASY -> {
                return makeEasyMove(board);
            }
            case MEDIUM -> {
                return makeMediumMove(board);
            }
        }
        return null;
    }

    public int[] makeEasyMove(GameBoard board) {
        return makeRandomMove(board);
    }

    public int[] makeRandomMove(GameBoard board) {
        int row, col;
        while (true) {
            row = 1 + (int)(Math.random() * 3);
            col = 1 + (int)(Math.random() * 3);
            char symbol = board.get(row, col);
            if (
                    1 <= row && row <= 3 &&
                            1 <= col && col <= 3 &&
                            symbol == ' '
            ) {
                return new int[]{row, col};
            }
        }
    }

    public int[] makeMediumMove(GameBoard board) {
        int[] move;
        char opponentSymbol = super.PLAYER_SYMBOL == 'X' ? 'O' : 'X';
        if ((move = getWinnableMove(board, super.PLAYER_SYMBOL)) != null) {
            return move;
        } else if ((move = getWinnableMove(board, opponentSymbol)) != null) {
            return move;
        } else {
            return makeRandomMove(board);
        }
    }

    private int[] getWinnableMove(GameBoard board, char symbol) {
        int[] move;
        if ((move = getColumnWiseWinnableMove(board, symbol)) != null) {
            return move;
        } else if ((move = getRowWiseWinnableMove(board, symbol)) != null) {
            return move;
        } else if ((move = getDiagonalWiseWinnableMove(board, symbol)) != null) {
            return move;
        } else {
            return null;
        }
    }

    private int[] getColumnWiseWinnableMove(GameBoard board, char symbol) {
        for (int col = 1; col <= 3; col++) {
            int emptyRow = -1, selfCount = 0, opponentCount = 0;
            for (int row = 1; row <= 3; row++) {
                if (board.get(row, col) == symbol) {
                    selfCount++;
                } else if (board.get(row, col) == ' ') {
                    emptyRow = row;
                } else {
                    opponentCount++;
                }
            }
            if (selfCount == 2 && opponentCount == 0) {
                return new int[]{emptyRow, col};
            }
        }
        return null;
    }

    private int[] getRowWiseWinnableMove(GameBoard board, char symbol) {

        for (int row = 1; row <= 3; row++) {
            int emptyColumn = -1, selfCount = 0, opponentCount = 0;
            for (int col = 1; col <= 3; col++) {
                if (board.get(row, col) == symbol) {
                    selfCount++;
                } else if (board.get(row, col) == ' ') {
                    emptyColumn = col;
                } else {
                    opponentCount++;
                }
            }
            if (selfCount == 2 && opponentCount == 0) {
                return new int[]{row, emptyColumn};
            }
        }
        return null;
    }

    private int[] getDiagonalWiseWinnableMove(GameBoard board, char symbol) {

        // diagonal - back slash
        int selfCount = 0,
            opponentCount = 0,
            emptyRow = -1,
            emptyColumn = -1;
        for (int row = 1, col = 1; row <= 3 && col <= 3; row++, col++) {
            if (board.get(row, col) == symbol) {
                selfCount++;
            } else if (board.get(row, col) == ' ') {
                emptyColumn = emptyRow = row;
            } else {
                opponentCount++;
            }
        }
        if (selfCount == 2 && opponentCount == 0) {
            return new int[]{emptyRow, emptyColumn};
        }

        // diagonal - forward slash
        selfCount = 0;
        opponentCount = 0;
        emptyRow = -1;
        emptyColumn = -1;
        for (int row = 1, col = 3; row <= 3 && col >=1; row++, col--) {
            if (board.get(row, col) == symbol) {
                selfCount++;
            } else if (board.get(row, col) == ' ') {
                emptyColumn = col;
                emptyRow = row;
            } else {
                opponentCount++;
            }
        }
        if (selfCount == 2 && opponentCount == 0) {
            return new int[]{emptyRow, emptyColumn};
        }
        return null;
    }
}
