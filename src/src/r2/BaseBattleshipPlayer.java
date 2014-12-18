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
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import r2.domain.IOcean;
import r2.domain.IShipPlacer;
import r2.domain.ITactic;
import r2.domain.models.Heatmap;
import r2.domain.models.ShipPlacement;

/**
 *
 * @author rolf
 */
public abstract class BaseBattleshipPlayer implements BattleshipsPlayer {

    private final static boolean DEBUG_MODE = true;
    private final static int DEBUG_SLEEP_TIME = 5;
    private final static int DEBUG_INTERVAL = 20;
    private static int number = 0;

    private final HeatmapCalculator heatmapCalculator = new HeatmapCalculator();

    protected int sizeX;
    protected int sizeY;

    private int[][] map;
    private int[][] hitMap;

    private ITactic[] tactics;
    private Stack<Position> history;

    private DebugFrame debugFrame;

    public BaseBattleshipPlayer() {
        if (DEBUG_MODE) {
            this.debugFrame = new DebugFrame();
        }
    }

    protected abstract ITactic[] getTactics();

    protected abstract IOcean getOcean();

    protected abstract IShipPlacer getShipPlacer();

    @Override
    public void placeShips(Fleet fleet, Board board) {
        this.history = new Stack<>();

        // Save the size of the board
        this.sizeX = board.sizeX();
        this.sizeY = board.sizeY();

        // Setup tactics
        this.tactics = getTactics();

        // Create a map
        this.map = this.getOcean().create(sizeX, sizeY);
        this.hitMap = new int[this.sizeX][this.sizeY];

        // Place the ships
        int[][] shipMap = new int[this.sizeX][this.sizeY];
        int i = 1;
        for (ShipPlacement shipPlacement : this.getShipPlacer().placeShips(sizeX, sizeY, fleet)) {
            board.placeShip(shipPlacement.getPosition(), shipPlacement.getShip(), shipPlacement.isVertical());

            Position position = shipPlacement.getPosition();
            if (shipPlacement.isVertical()) {
                for (int y = position.y; y < position.y + shipPlacement.getLength(); y++) {
                    shipMap[position.x][y] = i;
                }
            } else {
                for (int x = position.x; x < position.x + shipPlacement.getLength(); x++) {
                    shipMap[x][position.y] = i;
                }
            }
            i++;
        }

        this.debugFrame.redrawOpponentMap(shipMap);
    }

    @Override
    public void incoming(Position pos) {

        if (DEBUG_MODE && (number % DEBUG_INTERVAL) == 0) {
            try {
                Thread.sleep(DEBUG_SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BaseBattleshipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        Position position = null;

        for (ITactic tactic : tactics) {
            if (tactic.isGoodTactic(this.map)) {
                position = tactic.getBestPosition(this.map);
                break;
            }
        }

        this.history.add(position);

        return position;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        Position position = this.history.lastElement();

        if (hit) {
            this.map[position.x][position.y] = 2;
            
            for (ITactic tactic : this.tactics) {
                tactic.isSuccessfulHit(position, this.map);
            }
        } else {
            if (this.map[position.x][position.y] <= 0) {
                this.map[position.x][position.y] = 1;
            }

            for (ITactic tactic : this.tactics) {
                tactic.isUnsuccessfulHit(position, this.map);
            }
        }

        if (DEBUG_MODE && (number % DEBUG_INTERVAL) == 0) {
            this.debugFrame.redrawPlayerMap(this.map);

            try {
                Thread.sleep(DEBUG_SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BaseBattleshipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        Heatmap heatmap = heatmapCalculator.calculateHeatmap(this.sizeX, this.sizeY, this.hitMap);

        if (DEBUG_MODE && (number % DEBUG_INTERVAL) == 0) {
            try {
                Thread.sleep(DEBUG_SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BaseBattleshipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        number++;
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
