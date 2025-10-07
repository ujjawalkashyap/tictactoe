package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameState findStreak(BiFunction<Integer, Integer, String> next){
        for(int i=0;i<3;i++){
            boolean possibleStreak = true;
            for(int j=0;j<3;j++){
                if(next.apply(i,j)==null||!next.apply(i,0).equals(next.apply(i,j))){
                    possibleStreak = false;
                    break;
                }
            }
            if(possibleStreak) {
                return new GameState(true, next.apply(i,0));
            }
        }
        return null;
    }
    public GameState findDiagStreak(Function<Integer, String> diagFunc){
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (diagFunc.apply(j)==null||!diagFunc.apply(j).equals(diagFunc.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak){
            return new GameState(true,diagFunc.apply(0));
        }
        return null;
    }
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            GameState rowWin = findStreak((i,j)->board1.getSymbol(i, j));
            if(rowWin!=null) return rowWin;

            GameState colWin = findStreak((i,j)->board1.getSymbol(j,i));
            if(colWin!=null) return colWin;

            Function<Integer, String> diag = (i)->((TicTacToeBoard) board).getSymbol(i,i);
            Function<Integer, String> revDiag = (i)->((TicTacToeBoard) board).getSymbol(i,2-i);

            GameState diagWin = findDiagStreak(diag);
            if(diagWin!=null) return diagWin;

            GameState revDiagWin = findDiagStreak(revDiag);
            if(revDiagWin!=null) return revDiagWin;

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
