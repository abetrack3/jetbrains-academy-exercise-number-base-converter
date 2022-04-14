package TicTacToeWithAI;

public final class Game {

    private Game() {}

    static void run() {
        GameBoard board = new GameBoard();
        System.out.println(board);
        Player playerX = new PlayerHuman('X');
        Player playerY = new PlayerBotAI("Easy", 'O');
        Player currentPlayer = playerX;
        for (int i = 0; i < 9; i++) {
            int[] coordinates = currentPlayer.makeMove(board);
            assert coordinates != null;
            int row = coordinates[0], column = coordinates[1];
            board.set(row, column, currentPlayer.PLAYER_SYMBOL);
            System.out.println(board);
            currentPlayer = currentPlayer == playerY ? playerX : playerY;
            String status = board.gameStatus();
            if (!status.equals("Game not finished")) {
                System.out.println(status);
                return;
            }
        }
    }

}
