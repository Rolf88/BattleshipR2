/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2.shipPlacers;

import battleship.interfaces.Position;
import r2.domain.IShipPlacer;

/**
 *
 * @author rolf
 */
public abstract class BaseShipPlacer implements IShipPlacer {

    protected boolean isValidPlacement(int sizeX, int sizeY, int[][] shipMap, Position position, int length, boolean isVertical) {
        if (isVertical) {
            if (position.y + length > sizeY) {
                return false;
            }
            for (int y = position.y; y < position.y + length; y++) {
                if (shipMap[position.x][y] != 0) {
                    for (int ry = y - 1; ry >= position.y; ry--) {
                        shipMap[position.x][ry] = 0;
                    }
                    return false;
                }
                shipMap[position.x][y] = 1;
            }
            return true;
        }
        if (position.x + length > sizeX) {
            return false;
        }
        for (int x = position.x; x < position.x + length; x++) {
            if (shipMap[x][position.y] != 0) {
                for (int rx = x - 1; rx >= position.x; rx--) {
                    shipMap[rx][position.y] = 0;
                }
                return false;
            }
            shipMap[x][position.y] = 1;
        }
        return true;
    }
}
