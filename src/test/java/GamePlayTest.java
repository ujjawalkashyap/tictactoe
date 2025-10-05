import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest{
    RuleEngine ruleEngine;
    AIEngine aiEngine;
    GameEngine gameEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    public void playGame(Board board, int[][] moves){
        int row = 0,col = 0;
        int rowIndex=0;
        while(!ruleEngine.getState(board).isOver()){
            Player human = new Player("O");
            Player computer = new Player("X");
            row = moves[rowIndex][0];
            col = moves[rowIndex][1];
            rowIndex++;
            Cell cell = new Cell(row, col);
            Move opponentsMove = new Move(cell, human);
            gameEngine.move(board, opponentsMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()){
                Move computersMove = aiEngine.suggestMove(board, computer);
                gameEngine.move(board, computersMove);
                System.out.println(board);
            }
        }
    }
    @Test
    public void CheckForRowWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{1,2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));

    }
    //test column complete
    @Test
    public void CheckForColWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,0},{2,0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));

    }
    //test diagonal complete
    @Test
    public void CheckForDiagonalWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,1},{2,2}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));

    }
    //test rev diagonal complete
    @Test
    public void CheckForRevDiagonalWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,2},{1,1},{2,0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));

    }
    //test for computer winning
    @Test
    public void CheckForRevComputerWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{2,0}};
        playGame(board, moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));

    }
}