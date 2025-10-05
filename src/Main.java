package src;

import src.api.GameEngine;
import src.game.Board;
import src.game.Cell;
import src.game.Move;
import src.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        while(!gameEngine.isCompleted(board).isOver()){
            Player opponent = new Player("O");
            Scanner sc = new Scanner(System.in);
            System.out.println("Make your move!");
            int row = sc.nextInt();
            int col = sc.nextInt();
            Cell cell = new Cell(row, col);
            Move opponentsMove = new Move(cell);
            gameEngine.move(board, opponentsMove, opponent);
            System.out.println(board);
            Player computer = new Player("X");
            if(!gameEngine.isCompleted(board).isOver()){
                Move computersMove = gameEngine.suggestMove(board);
                gameEngine.move(board, computersMove, computer);
                System.out.println(board);
            }
        }
        System.out.println("GameResult" + gameEngine.isCompleted(board));
        System.out.println(board);
    }
}
