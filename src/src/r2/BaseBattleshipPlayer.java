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
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import r2.domain.IOcean;
import r2.domain.ITactic;

/**
 *
 * @author rolf
 */
public abstract class BaseBattleshipPlayer implements BattleshipsPlayer {

    private final static boolean DEBUG_MODE = false;

    private final static Random rnd = new Random();

    private ChessTacticAnalyzer chessAnalyzer;

    protected int sizeX;
    protected int sizeY;

    private int[][] map;
    private int[][] opponentMap;

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

    @Override
    public void placeShips(Fleet fleet, Board board) {
        this.history = new Stack<>();
        this.chessAnalyzer = new ChessTacticAnalyzer();

        // Save the size of the board
        this.sizeX = board.sizeX();
        this.sizeY = board.sizeY();

        // Setup tactics
        this.tactics = getTactics();

        // Create a map
        this.map = this.getOcean().create(sizeX, sizeY);
        this.opponentMap = this.getOcean().create(sizeX, sizeY);

        // Set the ships
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();

            int x, y;

            if (vertical) {
                x = rnd.nextInt(sizeX);
                y = rnd.nextInt(sizeY - (s.size() - 1));
            } else {
                x = rnd.nextInt(sizeX - (s.size() - 1));
                y = rnd.nextInt(sizeY);
            }

            Position pos = new Position(x, y);

            // Lets draw our ships on the opponent map
            if (vertical) {
                for (int tempY = pos.y; tempY < (pos.y + s.size()); tempY++) {
                    this.opponentMap[pos.x][tempY] = 3;
                }
            } else {
                for (int tempX = pos.x; tempX < (pos.x + s.size()); tempX++) {
                    this.opponentMap[tempX][pos.y] = 3;
                }
            }

            board.placeShip(pos, s, vertical);
        }
    }

    @Override
    public void incoming(Position pos) {
        chessAnalyzer.register(pos.x, pos.y);

        if (this.opponentMap[pos.x][pos.y] == 3) {
            this.opponentMap[pos.x][pos.y] = 2;
        } else {
            this.opponentMap[pos.x][pos.y] = 1;
        }

        if (DEBUG_MODE) {
            this.debugFrame.redrawOpponentMap(this.opponentMap);

            try {
                Thread.sleep(20);
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

        if (DEBUG_MODE) {
            this.debugFrame.redrawPlayerMap(this.map);

            try {
                Thread.sleep(20);
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
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
