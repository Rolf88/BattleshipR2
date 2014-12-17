/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2.shipPlacers;

import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import r2.domain.IShipPlacer;
import r2.domain.models.ShipPlacement;

/**
 *
 * @author rolf
 */
public class DefaultShipPlacers extends BaseShipPlacer {

    private final Random rnd = new Random();

    @Override
    public List<ShipPlacement> placeShips(int sizeX, int sizeY, Fleet fleet) {
        int[][] shipMap = new int[sizeX][sizeY];
        List<ShipPlacement> shipPlacements = new ArrayList<>();

        // Set the ships
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);

            ShipPlacement shipPlacement;

            do {
                boolean vertical = rnd.nextBoolean();

                int x, y;

                if (vertical) {
                    x = rnd.nextInt(sizeX);
                    y = rnd.nextInt(sizeY - (s.size() - 1));
                } else {
                    x = rnd.nextInt(sizeX - (s.size() - 1));
                    y = rnd.nextInt(sizeY);
                }

                shipPlacement = new ShipPlacement(new Position(x, y), s.size(), vertical, s);
            } while (!isValidPlacement(sizeX, sizeY, shipMap, shipPlacement.getPosition(), shipPlacement.getLength(), shipPlacement.isVertical()));

            shipPlacements.add(shipPlacement);
        }

        return shipPlacements;
    }
}
