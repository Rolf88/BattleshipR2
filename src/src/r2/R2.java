/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package r2;

import battleship.examples.SystematicShotPlayer;
import battleship.implementations.Battleships;
import battleship.interfaces.BattleshipsPlayer;
import tournament.game.GameInstance;
import tournament.game.GameResult;
import tournament.player.PlayerFactory;

/**
 *
 * @author rolf
 */
public class R2 {
    
    public static PlayerFactory<BattleshipsPlayer> getPlayerFactory(){
    
    return new RandomPlayerFactory();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BattleshipsPlayer player1 = new Ai1();
        BattleshipsPlayer player2 = new SystematicShotPlayer();
        GameInstance<BattleshipsPlayer> game = Battleships.getSingleGameInstance();
        GameResult res = game.run(player1, player2);     
        
        System.out.println("Result: ");
        System.out.println("player1 major (Points for the game): " + res.majorPointsA);
        System.out.println("player2 major (Points for the game): " + res.majorPointsB);
        System.out.println("player1 minor (Rounds won): " + res.minorPointsA);
        System.out.println("player2 minor (Rounds won): " + res.minorPointsB);
//        
//// TODO code application logic here
//        ArrayList<PlayerFactory<BattleshipsPlayer>> playerFactories = new ArrayList<>();
//        for(int i = 0; i < 10; ++i)
//        {
//            playerFactories.add(new RandomPlayerFactory());
//            playerFactories.add(new SystematicShotPlayerFactory());
//            
//        }
//        //Create game factory
//        GameFactory<BattleshipsPlayer> gameFactory = Battleships.getGameFactory();
//        Tournament.run(gameFactory, playerFactories, 8); //Running with 8 threads... 
    }
    
}
