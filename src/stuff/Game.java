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
public abstract class Game 
{
    Player[] players;
    Player winner;
    
    public abstract void doStuff();
    public abstract boolean isDone();
}
