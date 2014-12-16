/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2;

import battleship.interfaces.BattleshipsPlayer;
import r2.players.DefaultBattleshipPlayer;
import tournament.player.PlayerFactory;

/**
 *
 * @author Tobias Grundtvig
 */
public class RandomPlayerFactory implements PlayerFactory<BattleshipsPlayer> {

    private static int nextID = 1;
    private final int id;

    public RandomPlayerFactory() {
        id = nextID++;
    }

    @Override
    public BattleshipsPlayer getNewInstance() {
        return new DefaultBattleshipPlayer();
    }

    @Override
    public String getID() {
        return "R3";
    }

    @Override
    public String getName() {
        return "Default player " + id;
    }

}
