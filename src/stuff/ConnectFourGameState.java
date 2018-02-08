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
public class ConnectFourGameState extends GameState
{
    ConnectFourPlayer[][] board;
    ConnectFourPlayer turn;
    int tilesPlaced; // maintain amount of tiles placed on board
    
    private ConnectFourGameState()
    {
        
    }
    
    public static ConnectFourGameState newGame()
    {
        ConnectFourGameState newGame = new ConnectFourGameState();
        newGame.board = new ConnectFourPlayer[7][6];
        newGame.turn = ConnectFourPlayer.RED;
        return newGame;
    }
}
