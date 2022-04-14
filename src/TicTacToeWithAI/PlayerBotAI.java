package TicTacToeWithAI;

public final class PlayerBotAI extends Player {

    String difficulty;

    PlayerBotAI(String difficulty, char symbol) {
        super(symbol);
        this.difficulty = difficulty;
    }

    @Override
    public int[] makeMove(GameBoard board) {
        switch (this.difficulty) {
            case "Easy"     -> { return makeEasyMove(board);    }
            case "Medium"   -> { return makeMediumMove(board);  }
        }
        return null;
    }

    public int[] makeEasyMove(GameBoard board) {
        System.out.println("Making move level \"easy\"");
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
        return  null;
    }
}
