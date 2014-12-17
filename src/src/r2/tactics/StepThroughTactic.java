package r2.tactics;

import battleship.interfaces.Position;
import r2.domain.ITactic;

public class StepThroughTactic implements ITactic {

    @Override
    public boolean isGoodTactic(int[][] map) {
        return true;
    }

    @Override
    public Position getBestPosition(int[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] <= 0) {
                    return new Position(x, y);
                }
            }
        }

        return null;
    }

    @Override
    public void isSuccessfulHit(Position position, int[][] map) {
    }

    @Override
    public void isUnsuccessfulHit(Position position, int[][] map) {
    }
}
