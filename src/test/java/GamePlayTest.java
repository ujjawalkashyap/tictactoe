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

    public void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves){
        int row, col;
        int rowIndex=0;
        while(!ruleEngine.getState(board).isOver()){
            Player human = new Player("O");
            Player computer = new Player("X");
            row = firstPlayerMoves[rowIndex][0];
            col = firstPlayerMoves[rowIndex][1];
            Cell cell = new Cell(row, col);
            Move opponentsMove = new Move(cell, human);
            gameEngine.move(board, opponentsMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()){
                row = secondPlayerMoves[rowIndex][0];
                col = secondPlayerMoves[rowIndex][1];
                Move computersMove = new Move(new Cell(row, col), computer);
                gameEngine.move(board, computersMove);
                System.out.println(board);
            }
            rowIndex++;
        }
    }
    @Test
    public void CheckForRowWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves = new int[][]{{0,0},{0,1},{1,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());

    }
    //test column complete
    @Test
    public void CheckForColWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,0},{1,0},{2,0}};
        int[][] secondPlayerMoves = new int[][]{{0,1},{0,2},{1,1}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());

    }
    //test diagonal complete
    @Test
    public void CheckForDiagonalWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,0},{1,1},{2,2}};
        int[][] secondPlayerMoves = new int[][]{{0,1},{0,2},{1,0}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());

    }
    //test rev diagonal complete
    @Test
    public void CheckForRevDiagonalWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,2},{1,1},{2,0}};
        int[][] secondPlayerMoves = new int[][]{{0,0},{0,1},{1,0}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());

    }
    //test for second player winning
    @Test
    public void CheckForSecondPlayerWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,0},{0,1},{2,0}};
        int[][] secondPlayerMoves = new int[][]{{1,0},{1,1},{1,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());

    }
}