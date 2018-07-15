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
public class Stuff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        tournament(4);
    }
    
    
    public static void tournament(int players)
    {
        TournamentOrganizer to = new TournamentOrganizer();
        Player player1 = new ConnectFourPlayer();
        Player player2 = new ConnectFourPlayer();
        Player[] contestants = new Player[] { player1, player2 };
        
        to.contestants = contestants;
        to.playTournament();
    }
}
