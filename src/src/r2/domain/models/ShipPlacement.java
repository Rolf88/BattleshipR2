package r2.domain.models;

import battleship.interfaces.Position;

public class ShipPlacement {
    private final Position position;
    private final int length;
    private final boolean isVertical;

    public ShipPlacement(Position position, int length, boolean isVertical) {
        this.position = position;
        this.length = length;
        this.isVertical = isVertical;
    }

    public Position getPosition() {
        return position;
    }

    public int getLength() {
        return length;
    }

    public boolean isIsVertical() {
        return isVertical;
    }
}
