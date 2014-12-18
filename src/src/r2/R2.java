/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2;

import battleship.examples.RandomPlayerFactory;
import battleship.examples.SystematicShotPlayerFactory;
import battleship.implementations.Battleships;
import battleship.interfaces.BattleshipsPlayer;
import java.util.ArrayList;
import tournament.Tournament;
import tournament.game.GameFactory;
import tournament.player.PlayerFactory;

/**
 *
 * @author rolf
 */
public class R2 {

    public static PlayerFactory<BattleshipsPlayer> getPlayerFactory() {

        return new DefaultPlayerFactory();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create player list
        ArrayList<PlayerFactory<BattleshipsPlayer>> playerFactories = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            playerFactories.add(new RandomPlayerFactory());
            playerFactories.add(new SystematicShotPlayerFactory());
            playerFactories.add(new DefaultPlayerFactory());
            playerFactories.add(new TestPlayerFactory());
        }
        //Create game factory
        GameFactory<BattleshipsPlayer> gameFactory = Battleships.getGameFactory();
        Tournament.run(gameFactory, playerFactories, 8); //Running with 8 threads...
    }

}
