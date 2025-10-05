package game;

public class GameState {
    boolean isComplete;
    String winner;

    public GameState(boolean isComplete, String winner) {
        this.isComplete = isComplete;
        this.winner = winner;
    }

    public boolean isOver() {
        return isComplete;
    }

    public String getWinner() {
        return winner;
    }
    @Override
    public String toString() {
        return "GameResult{" +
                "isOver=" + isOver() +
                ", winner='" + getWinner() + '\'' +
                '}';
    }
}
