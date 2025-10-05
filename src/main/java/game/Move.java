package game;

public class Move {
    private Cell cell;
    private Player player;
    public Cell getCell(){
        return cell;
    }
    public Player getPlayer(){
        return player;
    }
    public Move(Cell cell, Player player){
        this.cell = cell;
        this.player = player;
    }
}

