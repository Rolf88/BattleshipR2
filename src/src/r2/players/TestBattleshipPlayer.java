/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2.players;

import r2.*;
import r2.domain.IOcean;
import r2.domain.IShipPlacer;
import r2.domain.ITactic;
import r2.shipPlacers.DefaultShipPlacers;
import r2.tactics.RandomTactic;

/**
 *
 * @author rolf
 */
public class TestBattleshipPlayer extends BaseBattleshipPlayer {

    @Override
    protected ITactic[] getTactics() {
        return new ITactic[]{
            //new WallStepThroughTactic(this.sizeX, this.sizeY)
            // new AroundTactic(this.sizeX, this.sizeY),
            // new ChessboardTactic(this.sizeX, this.sizeY, 2),
            //new CircleTactic(this.sizeX, this.sizeY),
            // new CenterStepThroughTactic(this.sizeX, this.sizeY),
            new RandomTactic()
        };
    }

    @Override
    protected IOcean getOcean() {
        return new ChessboardOcean();
    }

    @Override
    protected IShipPlacer getShipPlacer() {
        return new DefaultShipPlacers();
    }
}
