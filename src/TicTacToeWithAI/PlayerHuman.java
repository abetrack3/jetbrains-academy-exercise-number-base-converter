package TicTacToeWithAI;

import java.util.StringTokenizer;

public final class PlayerHuman extends Player{

    PlayerHuman(char symbol) {
        super(symbol);
    }

    @Override
    public int[] makeMove(GameBoard board) {
        int row, column;
        while (true) {
            try {
                System.out.println("Enter the coordinates: ");
                StringTokenizer st = new StringTokenizer(Input.nextLine());
                row = Integer.parseInt(st.nextToken());
                if (row > 3 || row < 1) {
                    System.out.println("Coordinates should be from 1 to 3");
                    continue;
                }
                column = Integer.parseInt(st.nextToken());
                if (column > 3 || column < 1) {
                    System.out.println("Coordinates should be from 1 to 3");
                    continue;
                }
                char symbol = board.get(row, column);
                if (symbol == 'X' || symbol == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
        return new int[]{row, column};
    }
}
