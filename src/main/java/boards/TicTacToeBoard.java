package boards;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];

    public String getSymbol(int row, int col){
        return cells[row][col];
    }
    public void setCell(String symbol, Cell cell){
        if(cells[cell.getRow()][cell.getCol()]==null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                result.append(cells[i][j]==null?"-":cells[i][j]);
            }
            result.append('\n');
        }
        return result.toString();
    }

    @Override
    public void move(Move move){
        setCell(move.getPlayer().symbol(), move.getCell());
    }

    @Override
    public TicTacToeBoard copy(){
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for(int i=0;i<3;i++){
            System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i],0,3);
        }
        return ticTacToeBoard;
    }
}
