package src;
import src.Board;
import src.GameResult;
import src.Move;
import src.Player;
import src.TicTacToeBoard;

public class Main {
    public static void main(String[] args){
        System.out.println("test");
        System.out.println("test2");
    }

    public Board start(){
        return new Board();
    }

    public void move(Board board, Move move, Player player){

    }

    public GameResult isCompleted(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            boolean rowComplete = true;
            String firstCharacter = "-";
            for(int i=0;i<3;i++){
                rowComplete = true;
                firstCharacter = board1.cells[i][0];
                for(int j=0;j<3;j++){
                    if(!board1.cells[i][j].equals(firstCharacter)){
                        rowComplete = false;
                        break;
                    }
                }
            }

            if(rowComplete){
                return new GameResult(true, firstCharacter);
            }
            boolean colComplete =true;
            for(int i=0;i<3;i++){
                colComplete = true;
                firstCharacter = board1.cells[0][i];
                for(int j=1;j<3;j++){
                    if(!board1.cells[j][i].equals(firstCharacter)){
                        colComplete = false;
                        break;
                    }
                }
            }
            if(colComplete){
                return new GameResult(true, firstCharacter);
            }
            boolean DiagonalComplete = true;
            firstCharacter = board1.cells[0][0];
            for(int j=1;j<3;j++){
                if(!board1.cells[j][j].equals(firstCharacter)){
                    DiagonalComplete = false;
                    break;
                }
            }
            if(DiagonalComplete){
                return new GameResult(true, firstCharacter);
            }
            boolean revDiagonalComplete = true;
            firstCharacter = board1.cells[0][2];
            for(int j=1;j<3;j++){
                if(!board1.cells[j][2-j].equals(firstCharacter)){
                    revDiagonalComplete = false;
                    break;
                }
            }
            if(revDiagonalComplete){
                return new GameResult(true, firstCharacter);
            }

            int numberOfFilledCells = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.cells[i][j].equals("-")){
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
}

