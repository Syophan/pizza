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
    int tilesPlaced;
    
    public ConnectFour(Player player1, Player player2)
    {   // Make a new game
        board = new ConnectFourPlayer[7][6];
        this.player1 = player1;
        this.player2 = player2;
        
        turn = player1; // player 1 starts, lel
        tilesPlaced = 0;
        done = false;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = null;
            }
        }
        
        player1.setGame(this);
        player2.setGame(this);
    }
    
    public void doStuff()
    {
        assert !done;
        
        // Ask player whose turn it is to make a move
        turn.makeMove();
        tilesPlaced++;
        printBoard();
        
        // Other person's turn
        turn = turn == player1 ? player2 : player1;
        
        checkIfGameHasEnded();
    }
    
    public boolean isDone()
    {
        return done;
    }
    
    private void checkIfGameHasEnded()
    {
        if(tilesPlaced == 42)
        {
            this.done = true;
            winner = (ConnectFourPlayer) player2;
        }
        
        if(tilesPlaced > 6) // game needs at least 7 tiles to conclude
        {
            // Check rows
            for(int i = 0; i < board.length; i++)
            {
                checkLine(board, i, 0, 0, 1, 7, 6);
            }
            
            // Check columns
            for(int i = 0; i < board[0].length; i++)
            {
                checkLine(board, 0, i, 1, 0, 7, 6);
            }
            
            // Check diagonals
            // Starting points are: 
            // For +1/+1 -> [0][2], [0,1], [0,0], [1,0], [2,0], [3,0]
            checkLine(board, 0, 2, 1, 1, 7, 6);
            checkLine(board, 0, 1, 1, 1, 7, 6);
            checkLine(board, 0, 0, 1, 1, 7, 6);
            checkLine(board, 1, 0, 1, 1, 7, 6);
            checkLine(board, 2, 0, 1, 1, 7, 6);
            checkLine(board, 3, 0, 1, 1, 7, 6);
            // For -1/+1 -> [6][2], [6,1], [6,0], [5,0], [4,0], [3,0]
            checkLine(board, 6, 2, -1, 1, 7, 6);
            checkLine(board, 6, 1, -1, 1, 7, 6);
            checkLine(board, 6, 0, -1, 1, 7, 6);
            checkLine(board, 5, 0, -1, 1, 7, 6);
            checkLine(board, 4, 0, -1, 1, 7, 6);
            checkLine(board, 3, 0, -1, 1, 7, 6);
        }
    }
    
    private boolean checkLine(ConnectFourPlayer[][] board, int x, int y, int xOffset, int yOffset, int xLimit, int yLimit)
    {   // dumbass check method that should be replaced by something smart
        int tilesInSuccession = 0;
        ConnectFourPlayer lastOwner = null;
        while(0 <= x && x < xLimit && 0 <= y && y < yLimit)
        {
            ConnectFourPlayer newOwner = board[x][y];
            if(newOwner != null && newOwner == lastOwner)
            {
                if(++tilesInSuccession == 4)
                {
                    winner = lastOwner;
                    done = true;
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
    
    private void printBoard()
    {
        System.out.println("Board after move " + tilesPlaced);
        System.out.println();
        for(int i = 5; i >= 0; i--)
        {
            String row = "|";
            for(int j = 0; j < 7; j++)
            {
                if(board[j][i] == null)
                {
                    row += "  ";
                }
                else if(board[j][i] == player1)
                {
                    row += " X";
                }
                else
                {
                    row += " O";
                }
            }
            row += " |";
            System.out.println(row);
        }
        System.out.println("---------------");
    }
}
