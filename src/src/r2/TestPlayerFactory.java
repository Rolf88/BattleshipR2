/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2;

import battleship.interfaces.BattleshipsPlayer;
import r2.players.TestBattleshipPlayer;
import tournament.player.PlayerFactory;

/**
 *
 * @author Tobias Grundtvig
 */
public class TestPlayerFactory implements PlayerFactory<BattleshipsPlayer> {

    private static int nextID = 1;
    private final int id;

    public TestPlayerFactory() {
        id = nextID++;
    }

    @Override
    public BattleshipsPlayer getNewInstance() {
        return new TestBattleshipPlayer();
    }

    @Override
    public String getID() {
        return "R2";
    }

    @Override
    public String getName() {
        return "R2 Test player " + id;
    }

}
