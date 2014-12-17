package r2.tactics;

import battleship.interfaces.Position;
import r2.domain.ITactic;

public class CenterStepSkipThroughTactic implements ITactic {

    private final int sizeX;
    private final int sizeY;
    private final int skipEvery;

    private int circles = 0;

    public CenterStepSkipThroughTactic(int sizeX, int sizeY, int skipEvery) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.skipEvery = skipEvery;
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

        int i = 0;
        return findBestPosition(i, map, width_middle, height_middle);
    }

    private Position findBestPosition(int i, int[][] map, int centerX, int centerY) {
        for (int y = centerY - circles; y < map.length && y <= centerY + circles; y++) {
            for (int x = centerX - circles; x < map[y].length && x <= centerX + circles; x++) {
                if ((i % skipEvery) == 0 && map[x][y] <= 0) {
                    return new Position(x, y);
                }

                i++;
            }
        }

        circles++;

        return findBestPosition(i, map, centerX, centerY);
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
