/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Syophan
 */
public class ConnectFourPlayer extends Player
{
    ConnectFour game;
    
    public void setGame(Game _game)
    {   // only game I know how to play
        assert _game instanceof ConnectFour;
        
        this.game = (ConnectFour) _game;   
    }
    
    public void makeMove()
    {
        assert game != null;
        
        List<Move> possibleMoves = getMoves();
        
        
        assert !possibleMoves.isEmpty();
        
        // Add smartness here
        int randomMoveIndex = (int) Math.floor(possibleMoves.size() * Math.random());
        
        Move randomMove = possibleMoves.get(randomMoveIndex);
        
        makeMove(randomMove);
    }
    
    private void makeMove(Move move)
    {
        game.board[move.x][move.y] = this;
    }
    
    private List<Move> getMoves()
    {
        List<Move> res = new ArrayList<Move>();
        
        for(int i = 0; i < game.board.length; i++)
        {
            for(int j = 0; j < game.board[i].length; j++)
            {
                if(game.board[i][j] == null)
                {
                    res.add(new Move(i, j));
                    break;
                }
            }
        }
        
        return res;
    }
    
    private class Move
    {
        private int x;
        private int y;
        
        Move(int _x, int _y)
        {
            x = _x;
            y = _y;
        }
    }
}
