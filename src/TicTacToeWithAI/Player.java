package TicTacToeWithAI;

public abstract class Player implements PlayerInterface{

    protected final char PLAYER_SYMBOL;

    Player(char c) {
        this.PLAYER_SYMBOL = c;
    }

}
