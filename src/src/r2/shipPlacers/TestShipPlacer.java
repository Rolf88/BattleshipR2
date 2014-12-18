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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import r2.domain.models.ShipPlacement;

/**
 *
 * @author rolf
 */
public class TestShipPlacer extends BaseShipPlacer {

    private final Random rnd = new Random();

    @Override
    public List<ShipPlacement> placeShips(int sizeX, int sizeY, Fleet fleet) {
        int[][] shipMap = new int[sizeX][sizeY];
        List<ShipPlacement> shipPlacements = new ArrayList<>();

        int numberOfVerticals = 0;
        try {
// Set the ships
            for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
                Ship s = fleet.getShip(i);

                ShipPlacement shipPlacement;

                boolean vertical = rnd.nextBoolean();

                Position position = getPlacement(sizeX, sizeY, shipMap, s.size(), vertical);

                if (vertical) {
                    for (int y = position.y; y < position.y + s.size(); y++) {
                        shipMap[position.x][y] = 1;
                    }
                } else {
                    for (int x = position.x; x < position.x + s.size(); x++) {
                        shipMap[x][position.y] = 1;
                    }
                }

                shipPlacement = new ShipPlacement(position, s.size(), vertical, s);

                shipPlacements.add(shipPlacement);
            }
        } catch (Exception ex) {
            return placeShips(sizeX, sizeY, fleet);
        }

        return shipPlacements;
    }

    protected Position getPlacement(int sizeX, int sizeY, int[][] shipMap, int length, boolean isVertical) throws Exception {
        List<Position> availablePlaces = new LinkedList<>();

        if (isVertical) {
            int validCount = 0;
            for (int x = 0; x < shipMap.length; x++) {
                for (int y = 0; y < shipMap[x].length; y++) {

                    if (x > 0 && shipMap[x - 1][y] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (x < sizeX - 1 && shipMap[x + 1][y] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (shipMap[x][y] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y + 1 < sizeY && shipMap[x][y + 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y > 0 && shipMap[x][y - 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y > 0 && x > 0 && shipMap[x - 1][y - 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y > 0 && x < sizeX - 1 && shipMap[x + 1][y - 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y + 1 < sizeY && x > 0 && shipMap[x - 1][y + 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y + 1 < sizeY && x < sizeX - 1 && shipMap[x + 1][y + 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (validCount - 1 == length) {
                        availablePlaces.add(new Position(x, y - validCount));

                        validCount--;
                    } else {
                        validCount++;
                    }
                }
                validCount = 0;
            }
        } else {
            int validCount = 0;
            for (int y = 0; y < sizeY; y++) {
                for (int x = 0; x < sizeX; x++) {
                    if (y > 0 && shipMap[x][y - 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (y < sizeY - 1 && shipMap[x][y + 1] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (shipMap[x][y] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (x + 1 < sizeX && shipMap[x + 1][y] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (x > 0 && shipMap[x - 1][y] != 0) {
                        validCount = 0;
                        continue;
                    }

                    if (validCount - 1 == length) {
                        availablePlaces.add(new Position(x - validCount, y));

                        validCount--;
                    } else {
                        validCount++;
                    }
                }
                validCount = 0;
            }

        }

        if (availablePlaces.isEmpty()) {
            throw new Exception("WHAT??");
        }

        int number = rnd.nextInt(availablePlaces.size());
        return availablePlaces.get(number);
    }
}
