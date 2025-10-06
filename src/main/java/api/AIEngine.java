package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {
    public Move suggestMove(Board board, Player computer){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestion;
            if(isStarting(board1, 4)){
                suggestion = getBasicMove(board1, computer);
            }
            else{
                suggestion = getSmartMove(board1, computer);
            }
            if(suggestion!=null) return suggestion;
            throw new IllegalStateException();
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public boolean isStarting(TicTacToeBoard board, int threshold){
        int count =0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)!=null){
                    count++;
                }
            }
        }
        return count>threshold;
    }
    public Move getBasicMove(TicTacToeBoard board, Player computer){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)!=null){
                    return new Move(new Cell(i,j), computer);
                }
            }
        }
        return null;
    }
    public Move getSmartMove(TicTacToeBoard board, Player computer){
        RuleEngine ruleEngine = new RuleEngine();
        //attacking move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)==null){
                    Move move = new Move(new Cell(i,j), computer);
                    TicTacToeBoard boardcopy = board.copy();
                    boardcopy.move(move);
                    if(ruleEngine.getState(boardcopy).isOver()){
                        return move;
                    }
                }
            }
        }
        //defensive move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)==null){
                    Move move = new Move(new Cell(i,j), computer.flip());
                    TicTacToeBoard boardcopy = board.copy();
                    boardcopy.move(move);
                    if(ruleEngine.getState(boardcopy).isOver()){
                        return new Move(new Cell(i,j), computer);
                    }
                }
            }
        }
        return getBasicMove(board, computer);
    }

}
