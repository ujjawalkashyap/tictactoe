public class GamePlayTest{
    RuleEngine ruleEngine;
    AIEngine aiEngine;
    GameEngine gameEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        gameEngine = new GameEngine();
    }
    @Test
    public void play(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[3][2]{{1,0},{1,1},{1,2}};
        int row = 0,col = 0;
        int rowIndex=0;
        while(!ruleEngine.isCompleted(board).isOver()){
            Player human = new Player("O");
            Player computer = new Player("X");
            row = moves[rowIndex++][0];
            col = moves[rowIndex++][1];
            Cell cell = new Cell(row, col);
            Move opponentsMove = new Move(cell, human);
            gameEngine.move(board, opponentsMove);
            System.out.println(board);
            if(!ruleEngine.isCompleted(board).isOver()){
                Move computersMove = aiEngine.suggestMove(board, computer);
                gameEngine.move(board, computersMove);
                System.out.println(board);
            }
        }
        Assert.assertTrue(ruleEngine.getState.isOver());
        Assert.assertTrue(ruleEngine.getState.winner().equals("O"));

    }
}