/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r2;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Board;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author rolf
 */
public class Ai1 implements BattleshipsPlayer {

    private final static boolean DEBUG_MODE = false;

    private final static Random rnd = new Random();

    private int sizeX;
    private int sizeY;
    private int[][] map;

    private Stack<Position> history;
    private Queue<Position> scheduled;
    private List<Position> possiblePositions;

    private DebugFrame debugFrame;

    public Ai1() {
        if (DEBUG_MODE) {
            this.debugFrame = new DebugFrame();
        }
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
        this.history = new Stack<>();
        this.scheduled = new LinkedList<>();
        this.possiblePositions = new LinkedList<>();

        // Save the size of the board
        this.sizeX = board.sizeX();
        this.sizeY = board.sizeY();

        // Create a map
        this.map = new int[this.sizeX][this.sizeY];

        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
                boolean isWhite = ((y + x) % 2) == 0;

                if (isWhite) {
                    this.map[x][y] = -1;
                } else {
                    this.map[x][y] = 0;
                }

                this.possiblePositions.add(new Position(x, y));
            }
        }

        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;

            if (vertical) {
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                int x = rnd.nextInt(sizeX - (s.size() - 1));
                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);

            }

            board.placeShip(pos, s, vertical);
        }
    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        if (!this.scheduled.isEmpty()) {
            Position position;

            do {
                position = this.scheduled.poll();
            } while (this.history.contains(position));

            if (position != null) {
                return getEnsuredPosition(position);
            }
        }

        List<Position> positions = getBestPositions();

        Position position;
        do {
            int index = rnd.nextInt(positions.size());
            position = positions.get(index);
        } while (this.history.contains(position));

        return getEnsuredPosition(position);
    }

    private List<Position> getBestPositions() {
        List<Position> positions = new LinkedList<>();

        for (Position position : this.possiblePositions) {
            if (this.map[position.x][position.y] == 0) {
                positions.add(position);
            }
        }

        return positions;
    }

    private Position getEnsuredPosition(Position position) {
        this.possiblePositions.remove(position);
        this.history.add(position);

        return position;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        Position position = this.history.lastElement();

        if (this.map[position.x][position.y] <= 0) {
            this.map[position.x][position.y] = 1;
        }

        if (hit) {
            this.map[position.x][position.y] = 2;

            // Lets us fire around the current hit position
            scheduleFirePosition(position.x, position.y - 1);
            scheduleFirePosition(position.x - 1, position.y);
            scheduleFirePosition(position.x, position.y + 1);
            scheduleFirePosition(position.x + 1, position.y);
        }

        if (DEBUG_MODE) {
            this.debugFrame.redrawGrid(this.map);

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ai1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void scheduleFirePosition(int x, int y) {
        boolean isValidHeight = (y >= 0 && y <= this.sizeY + 1);
        boolean isValidWidth = (x >= 0 && x <= this.sizeX + 1);

        if (isValidHeight && isValidWidth && this.map[x][y] <= 0) {
            this.scheduled.offer(new Position(x, y));
        }
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {

    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
