package r2.domain;

import battleship.interfaces.Position;

public interface ITactic {

    boolean isGoodTactic(int[][] map);
    
    Position getBestPosition(int[][] map);

    void isSuccessfulHit(Position position, int[][] map);
}
