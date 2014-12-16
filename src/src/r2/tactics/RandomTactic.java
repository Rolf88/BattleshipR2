package r2.tactics;

import battleship.interfaces.Position;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import r2.domain.ITactic;

public class RandomTactic implements ITactic {

    private final Random random = new Random();

    public RandomTactic() {
    }

    @Override
    public boolean isGoodTactic(int[][] map) {
        return true;
    }

    @Override
    public Position getBestPosition(int[][] map) {
        List<Position> positions = new LinkedList<>();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] <= 0) {
                    positions.add(new Position(x, y));
                }
            }
        }

        // Get random position
        int index = this.random.nextInt(positions.size());
        return positions.get(index);
    }

    @Override
    public void isSuccessfulHit(Position position, int[][] map) {
    }

}
