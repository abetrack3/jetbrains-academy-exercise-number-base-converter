package TicTacToeWithAI;

import java.util.Scanner;

class Input {

    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static String nextLine() {
        return sc.nextLine();
    }

}
