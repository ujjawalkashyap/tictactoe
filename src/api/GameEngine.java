package src.api;

import src.boards.TicTacToeBoard;
import src.game.*;

public class GameEngine {
    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move, Player player){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard)board;
            board1.setCell(player.symbol(), move.getCell());
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public GameResult isCompleted(Board board){
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
                return new GameResult(true, firstCharacter);
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
                return new GameResult(true, firstCharacter);
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
                return new GameResult(true, firstCharacter);
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
                return new GameResult(true, firstCharacter);
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
                return new GameResult(true, "-");
            }
            else{
                return new GameResult(false, "-");
            }
        }
        else return new GameResult(false, "-");
    }
    public Move suggestMove(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getCell(i,j)==null){
                        return new Move(new Cell(i,j));
                    }
                }
            }
            throw new IllegalStateException();
        }
        else{
            throw new IllegalArgumentException();
        }

    }
}

