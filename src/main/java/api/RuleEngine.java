package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

public class RuleEngine {
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            boolean rowComplete = true;
            String firstCharacter = "-";
            for(int i=0;i<3;i++){
                firstCharacter = board1.getCell(i,0);
                rowComplete = firstCharacter!=null;
                if(firstCharacter!=null){
                    for(int j=0;j<3;j++){
                        if(!firstCharacter.equals(board1.getCell(i,j))){
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if(rowComplete)
                    break;
            }

            if(rowComplete){
                return new GameState(true, firstCharacter);
            }
            boolean colComplete =true;
            for(int i=0;i<3;i++){
                firstCharacter = board1.getCell(0, i);
                colComplete = firstCharacter!=null;
                if(firstCharacter!=null){
                    for(int j=1;j<3;j++){
                        if(!firstCharacter.equals(board1.getCell(j,i))){
                            colComplete = false;
                            break;
                        }
                    }
                }
                if(colComplete)
                    break;
            }
            if(colComplete){
                return new GameState(true, firstCharacter);
            }

            firstCharacter = board1.getCell(0,0);
            boolean DiagonalComplete = firstCharacter!=null;
            if(firstCharacter!=null){
                for(int j=1;j<3;j++){
                    if(!firstCharacter.equals(board1.getCell(j,j))){
                        DiagonalComplete = false;
                        break;
                    }
                }
            }
            if(DiagonalComplete){
                return new GameState(true, firstCharacter);
            }
            firstCharacter = board1.getCell(0, 2);
            boolean revDiagonalComplete = firstCharacter!=null;
            if(firstCharacter!=null) {
                for (int j = 1; j < 3; j++) {
                    if (!firstCharacter.equals(board1.getCell(j, 2 - j))) {
                        revDiagonalComplete = false;
                        break;
                    }
                }
            }
            if(revDiagonalComplete){
                return new GameState(true, firstCharacter);
            }

            int numberOfFilledCells = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getCell(i, j)!=null && board1.getCell(i, j).equals("-")){
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
