/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

/**
 *
 * @author Syophan
 */
public class ConnectFour extends Game
{
    ConnectFourPlayer[][] board; // maybe just ints.... idk man
    Player turn; // probably a bad idea
    ConnectFourPlayer winner;
    Player player1, player2;
    boolean done;
    ConnectFourGameState gameState;
    
    public ConnectFour(Player player1, Player player2)
    {   // Make a new game
        board = new ConnectFourPlayer[7][6];
        this.player1 = player1;
        this.player2 = player2;
        
        turn = player1; // player 1 starts, lel
        done = false;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = null;
            }
        }
    }
    
    public void doStuff()
    {
        assert !done;
        
        // Ask player whose turn it is to make a move
        turn.makeMove(this.gameState);
        
        // Other person's turn
        turn = turn == player1 ? player2 : player1;
        
        checkIfGameHasEnded();
    }
    
    public void isDone()
    {
        return done;
    }
    
    private void checkIfGameHasEnded()
    {
        if(gameState.tilesPlaced == 42)
        {
            this.done = true;
        }
        
        if(gameState.tilesPlaced > 6) // game needs at least 7 tiles to conclude
        {
            // Check rows
            for(int i = 0; i < gameState.board.length; i++)
            {
                checkLine(gameState.board, i, 0, 0, 1, 7, 6);
            }
            
            // Check columns
            for(int i = 0; i < gameState.board[0].length; i++)
            {
                checkLine(gameState.board, 0, i, 1, 0, 7, 6);
            }
            
            // Check diagonals
            // Starting points are: 
            // For +1/+1 -> [0][2], [0,1], [0,0], [1,0], [2,0], [3,0]
            checkLine(gameState.board, 0, 2, 1, 1, 7, 6);
            checkLine(gameState.board, 0, 1, 1, 1, 7, 6);
            checkLine(gameState.board, 0, 0, 1, 1, 7, 6);
            checkLine(gameState.board, 1, 0, 1, 1, 7, 6);
            checkLine(gameState.board, 2, 0, 1, 1, 7, 6);
            checkLine(gameState.board, 3, 0, 1, 1, 7, 6);
            // For -1/+1 -> [6][2], [6,1], [6,0], [5,0], [4,0], [3,0]
            checkLine(gameState.board, 6, 2, -1, 1, 7, 6);
            checkLine(gameState.board, 6, 1, -1, 1, 7, 6);
            checkLine(gameState.board, 6, 0, -1, 1, 7, 6);
            checkLine(gameState.board, 5, 0, -1, 1, 7, 6);
            checkLine(gameState.board, 4, 0, -1, 1, 7, 6);
            checkLine(gameState.board, 3, 0, -1, 1, 7, 6);
        }
    }
    
    private boolean checkLine(ConnectFourPlayer[][] board, int x, int y, int xOffset, int yOffset, int xLimit, int yLimit)
    {   // dumbass check method that should be replaced by something smart
        int tilesInSuccession = 0;
        ConnectFourPlayer lastOwner = null;
        while(0 <= x && x < xLimit && 0 <= y && y < yLimit)
        {
            ConnectFourPlayer newOwner = gameState.board[x][y];
            if(newOwner != null && newOwner == lastOwner)
            {
                if(++tilesInSuccession == 4)
                {
                    winner = lastOwner;
                    return true;
                }
            }
            else
            {
                tilesInSuccession = 1;
                lastOwner = newOwner;
            }
            x += xOffset;
            y += yOffset;
        }
        return false;
    }
}
