/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package r2.domain;

import battleship.interfaces.Fleet;
import java.util.List;
import r2.domain.models.ShipPlacement;

/**
 *
 * @author rolf
 */
public interface IShipPlacer {
        
    List<ShipPlacement> placeShips(int sizeX, int sizeY, Fleet fleet);
}
