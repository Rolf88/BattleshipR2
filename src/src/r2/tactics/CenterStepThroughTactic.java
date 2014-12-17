package r2.tactics;

import battleship.interfaces.Position;
import r2.domain.ITactic;

public class CenterStepThroughTactic implements ITactic {

    private final int sizeX;
    private final int sizeY;

    private int circles = 0;

    public CenterStepThroughTactic(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean isGoodTactic(int[][] map) {
        double precent = (100 / sizeX) * circles;

        return precent < 30;
    }

    @Override
    public Position getBestPosition(int[][] map) {

        int width_middle = (int) Math.floor(sizeX / 2);
        int height_middle = (int) Math.floor(sizeY / 2);

        return findBestPosition(map, width_middle, height_middle);
    }

    private Position findBestPosition(int[][] map, int centerX, int centerY) {
        for (int y = centerY - circles; y < map.length && y <= centerY + circles; y++) {
            for (int x = centerX - circles; x < map[y].length && x <= centerX + circles; x++) {
                if (map[x][y] <= 0) {
                    return new Position(x, y);
                }
            }
        }

        circles++;

        return findBestPosition(map, centerX, centerY);
    }

    @Override
    public void isSuccessfulHit(Position position, int[][] map) {
        // shots++;
    }

    @Override
    public void isUnsuccessfulHit(Position position, int[][] map) {
        //shots++;
    }

}
