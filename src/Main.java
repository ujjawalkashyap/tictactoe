package src;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");
        while(!ruleEngine.isCompleted(board).isOver()){
            Player human = new Player("O");
            Scanner sc = new Scanner(System.in);
            System.out.println("Make your move!");
            int row = sc.nextInt();
            int col = sc.nextInt();
            Cell cell = new Cell(row, col);
            Move opponentsMove = new Move(cell, human);
            gameEngine.move(board, opponentsMove);
            System.out.println(board);
            Player computer = new Player("X");
            if(!ruleEngine.isCompleted(board).isOver()){
                Move computersMove = aiEngine.suggestMove(board, computer);
                gameEngine.move(board, computersMove);
                System.out.println(board);
            }
        }
        System.out.println("GameResult" + ruleEngine.isCompleted(board));
        System.out.println(board);
    }
}
