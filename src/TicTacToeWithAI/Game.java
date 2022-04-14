package TicTacToeWithAI;

public final class Game {

    private static final char SYMBOL_PLAYER1 = 'X';
    private static final char SYMBOL_PLAYER2 = 'O';

    private Game() {}

    static void run() {
        System.out.println("Input command: ");
        for (String s = Input.nextLine(); !s.equals("exit") ; s = Input.nextLine()) {
            String[] commands = s.split(" ");

            // replace with regex matching
            if (commands.length != 3) {
                System.out.println("Bad parameters!");
            } else {
                runInstance(commands[1], commands[2]);
            }

            System.out.println("Input command: ");
        }
    }

    private static void runInstance(String user1, String user2) {
        GameBoard board = new GameBoard();
        System.out.println(board);
        Player player1 = createPlayer(SYMBOL_PLAYER1, user1);
        Player player2 = createPlayer(SYMBOL_PLAYER2, user2);
        Player currentPlayer = player1;
        for (int i = 0; i < 9; i++) {
            int[] coordinates = currentPlayer.makeMove(board);
            assert coordinates != null;
            int row = coordinates[0], column = coordinates[1];
            board.set(row, column, currentPlayer.PLAYER_SYMBOL);
            System.out.println(board);
            currentPlayer = currentPlayer == player2 ? player1 : player2;
            String status = board.gameStatus();
            if (!status.equals("Game not finished")) {
                System.out.println(status);
                return;
            }
        }
    }

    private static Player createPlayer(char symbol, String playerType) {
        if (playerType.equals("user")) {
            return new PlayerHuman(symbol);
        } else {
            return new PlayerBotAI(symbol, playerType);
        }
    }

}
