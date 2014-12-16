/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2.players;

import r2.*;
import r2.tactics.ChessboardTactic;
import r2.tactics.RandomTactic;
import r2.tactics.AroundTactic;
import r2.domain.IOcean;
import r2.domain.ITactic;

/**
 *
 * @author rolf
 */
public class DefaultBattleshipPlayer extends BaseBattleshipPlayer {

    @Override
    protected ITactic[] getTactics() {
        return new ITactic[]{
            new AroundTactic(this.sizeX, this.sizeY),
            new ChessboardTactic(this.sizeX, this.sizeY, 5),
            new RandomTactic()
        };
    }

    @Override
    protected IOcean getOcean() {
        return new ChessboardOcean();
    }

}
