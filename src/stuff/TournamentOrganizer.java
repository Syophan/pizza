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
public class TournamentOrganizer
{
    Player[] contestants;
    
    public void playTournament()
    {
        assert contestants != null;
        assert isPowerOf2(contestants.length);
        
        while(contestants.length != 1)
        {
            Player[] nextRoundContestants = new Player[(int) contestants.length / 2];
            for(int i = 0; i < contestants.length; i += 2)
            {
                Game game = new ConnectFour(contestants[i], contestants[i+1]);
                Player winner = playGame(game);
                nextRoundContestants[(int) i / 2] = winner;
            }
            contestants = nextRoundContestants;
        }
    }
    
    /**
     * 
     * @param game The game to be played
     * @param p1 Player 1
     * @param p2 Player 2
     * @return The winner of the game
     */
    private Player playGame(Game game)
    {
        while(!game.isDone())
        {
            game.doStuff();
        }
        return game.winner;
    }
    
    public boolean isPowerOf2(int x)
    {
        return true;
    }
}
