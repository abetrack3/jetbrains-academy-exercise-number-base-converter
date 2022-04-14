package TicTacToeWithAI;

public final class Game {

    private Game() {}

    static void run() {
        System.out.println("Input command: ");
        for (String s = Input.nextLine(); !s.equals("exit") ; s = Input.nextLine()) {
            String[] commands = s.split(" ");
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
        Player player1 = user1.equals("user") ? new PlayerHuman('X') : new PlayerBotAI("Easy", 'X');
        Player player2 = user2.equals("easy") ? new PlayerBotAI("Easy", 'O') : new PlayerHuman('O');
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

}
