package SimpleTicTacToe;

import java.util.Scanner;
import java.util.StringTokenizer;

public class TicTacToe {

    private static Scanner scanner;

    public static void main(String[] args) {
        // write your code here

        scanner = new Scanner(System.in);
        char[][] c = makeGrid();
        System.out.println(gridToString(c));
        char player = 'X';
        for (int i = 9; i --> 0;) {
            int[] coordinates = getCoordinate(c);
            int row = coordinates[0];
            int column = coordinates[1];
            c[row-1][column-1] = player;
            System.out.println(gridToString(c));
            player = player == 'X' ? 'O' : 'X';

            if (isImpossible(c)) {
                System.out.println("Impossible");
                break;
            } else if (isDraw(c)) {
                System.out.println("Draw");
                break;
            } else if (isWinner('X', c)) {
                System.out.println("X wins");
                break;
            } else if (isWinner('O', c)) {
                System.out.println("O wins");
                break;
            }
        }
    }

    private static int[] getCoordinate(char[][] c) {
        int row, column;
        while (true) {
            try {
                System.out.println("Enter the coordinates: ");
                StringTokenizer st = new StringTokenizer(scanner.nextLine());
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
                if (c[row-1][column-1] == 'X' || c[row-1][column-1] == 'O') {
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

    private static boolean isDraw(char[][] c) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (c[i][j]) {
                    case 'X', 'O' -> count++;
                }
            }
        }
        return count == 9 && (isWinner('X', c) == isWinner('O', c));
    }

    private static boolean isImpossible(char[][] c) {
        return checkDoubleStep(c) || (isWinner('X', c) && isWinner('O', c));
    }

    private static boolean isWinner(char player, char[][] c) {
        // vertical
        for (int i = 0; i < 3; i++) {
            if (player == c[0][i] && player == c[1][i] && player == c[2][i]) {
                return true;
            }
        }

        // horizontal
        for (int i = 0; i < 3; i++) {
            if (player == c[i][0] && player == c[i][1] && player == c[i][2]) {
                return true;
            }
        }

        // diagonal
        if (player == c[0][0] && player == c[1][1] && player == c[2][2]) {
            return true;
        } else return player == c[0][2] && player == c[1][1] && player == c[2][0];
    }

    private static boolean checkDoubleStep(char[][] c) {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (c[i][j]) {
                    case 'X' -> xCount++;
                    case 'O' -> oCount++;
                }
            }
        }
        return Math.abs(xCount - oCount) >= 2;
    }

    private static char[][] makeGrid() {
        char[][] c = new char[3][3];
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                c[j][k] = ' ';
            }
        }
        return c;
    }

    private static String gridToString(char[][] c) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int i = 0; i < 3; i++) {
            sb.append("|");
            for (int j = 0; j < 3; j++) {
                sb.append(" ").append(c[i][j]);
            }
            sb.append(" |\n");
        }
        sb.append("---------");
        return sb.toString();
    }

}
