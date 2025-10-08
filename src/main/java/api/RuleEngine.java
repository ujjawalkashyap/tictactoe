package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameState traverse(Function<Integer, String> traversal){
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j=0;j<3;j++) {
            if (traversal.apply(j)==null||!traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak){
            return new GameState(true,traversal.apply(0));
        }
        return result;
    }
    public GameState outerTraversal(BiFunction<Integer, Integer, String> next){
        GameState result = new GameState(false, "-");
        for(int i=0;i<3;i++){
            final int ii = i;
            GameState traversal = traverse(j->next.apply(ii, j));
            if(traversal.isOver()){
                result = traversal;
                break;
            }
        }
        return result;
    }
    public GameState findDiagStreak(Function<Integer, String> diagFunc){
        return traverse(diagFunc);
    }
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            GameState rowWin = outerTraversal((i,j)->board1.getSymbol(i, j));
            if(rowWin.isOver()) return rowWin;

            GameState colWin = outerTraversal((i,j)->board1.getSymbol(j,i));
            if(colWin.isOver()) return colWin;

            Function<Integer, String> diag = (i)->((TicTacToeBoard) board).getSymbol(i,i);
            Function<Integer, String> revDiag = (i)->((TicTacToeBoard) board).getSymbol(i,2-i);

            GameState diagWin = findDiagStreak(diag);
            if(diagWin.isOver()) return diagWin;

            GameState revDiagWin = findDiagStreak(revDiag);
            if(revDiagWin.isOver()) return revDiagWin;

            int numberOfFilledCells = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getSymbol(i, j)!=null && board1.getSymbol(i, j).equals("-")){
                        numberOfFilledCells++;
                    }
                }
            }
            if(numberOfFilledCells==9){
                return new GameState(true, "-");
            }
            else{
                return new GameState(false, "-");
            }
        }
        else return new GameState(false, "-");
    }
}
