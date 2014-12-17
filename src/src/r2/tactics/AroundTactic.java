package r2.tactics;

import battleship.interfaces.Position;
import java.util.LinkedList;
import java.util.Queue;
import r2.domain.ITactic;

public class AroundTactic implements ITactic {

    private final Queue<Position> scheduled = new LinkedList<>();
    private final int sizeX;
    private final int sizeY;

    public AroundTactic(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    @Override
    public boolean isGoodTactic(int[][] map) {
        return !this.scheduled.isEmpty();
    }

    @Override
    public Position getBestPosition(int[][] map) {
        return this.scheduled.poll();
    }

    @Override
    public void isSuccessfulHit(Position position, int[][] map) {
        // Lets us fire around the current hit position
        scheduleFirePosition(position.x, position.y - 1, map);
        scheduleFirePosition(position.x - 1, position.y, map);
        scheduleFirePosition(position.x, position.y + 1, map);
        scheduleFirePosition(position.x + 1, position.y, map);
    }

    private void scheduleFirePosition(int x, int y, int[][] map) { 
        boolean isValidHeight = (y >= 0 && y <= this.sizeY + 1);
        boolean isValidWidth = (x >= 0 && x <= this.sizeX + 1);

        if (isValidHeight && isValidWidth && map[x][y] <= 0) {
            this.scheduled.offer(new Position(x, y));
        }
    }

    @Override
    public void isUnsuccessfulHit(Position position, int[][] map) {
    }
}
