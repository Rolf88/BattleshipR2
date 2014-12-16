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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rolf
 */
public class Ai1 implements BattleshipsPlayer {

    private final static Random rnd = new Random();

    private int nextX;
    private int nextY;

    private int sizeX;
    private int sizeY;
    private int[][] map;

    private final List<Position> history = new LinkedList<>();
    private final List<Position> scheduled = new LinkedList<>();

    private ArrayList<Pos> emptyfields;

    private final Test test;

    public Ai1() {
        test = new Test();
        test.setVisible(true);
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
        // Save the size of the board
        this.sizeX = board.sizeX();
        this.sizeY = board.sizeY();

        // Create a map
        this.map = new int[this.sizeX][this.sizeY];
        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
                this.map[x][y] = 0;
            }
        }

        emptyfields = new ArrayList<>();
        for (int y = 0; y < board.sizeY(); ++y) {
            for (int x = 0; x < board.sizeX(); ++x) {

                emptyfields.add(new Pos(x, y));
            }
        }

        nextX = 0;
        nextY = 0;

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
//        Position shot = new Position(nextX, nextY);
//        ++nextX;
//        if (nextX >= sizeX) {
//            nextX = 0;
//            ++nextY;
//            if (nextY >= sizeY) {
//                nextY = 0;
//            }
//        }
//        return shot;
        if (!this.scheduled.isEmpty()) {
            Position position = this.scheduled.get(0);
            this.scheduled.remove(position);

            return position;
        }

        int index = rnd.nextInt(emptyfields.size());
        Pos p = emptyfields.get(index);
        emptyfields.remove(index);

        Position position = new Position(p.getX(), p.getY());

        this.history.add(position);

        return position;
    }

    int i;

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {

        Position position = this.history.get(this.history.size() - 1);

        this.map[position.x][position.y] = 1;

        if (hit == true) {
            this.map[position.x][position.y] = 2;

            scheduleFirePosition(position.x, position.y - 1);
            scheduleFirePosition(position.x, position.y + 1);
            scheduleFirePosition(position.x - 1, position.y);
            scheduleFirePosition(position.x + 1, position.y);
        }

        this.test.redrawGrid(this.map);
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ai1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void scheduleFirePosition(int x, int y) {
        boolean isValidHeight = (y >= 0 && y <= this.sizeY);
        boolean isValidWidth = (x >= 0 && x <= this.sizeX);

        if (isValidHeight && isValidWidth && this.map[x][y] == 0) {
            Position upper = new Position(x, y);
            this.scheduled.add(upper);
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
        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ai1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
