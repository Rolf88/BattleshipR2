package r2.domain.models;

import battleship.interfaces.Position;
import battleship.interfaces.Ship;

public class ShipPlacement {
    private final Position position;
    private final int length;
    private final boolean isVertical;
    private final Ship ship;

    public ShipPlacement(Position position, int length, boolean isVertical, Ship ship) {
        this.position = position;
        this.length = length;
        this.isVertical = isVertical;
        this.ship = ship;
    }

    public Position getPosition() {
        return position;
    }

    public int getLength() {
        return length;
    }

    public boolean isVertical() {
        return isVertical;
    }
    
    public Ship getShip(){
        return this.ship;
    }
}
