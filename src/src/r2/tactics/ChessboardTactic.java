package r2.tactics;

import battleship.interfaces.Position;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import r2.domain.ITactic;

public class ChessboardTactic implements ITactic {

    private final Random random = new Random();

    private final int sizeX;
    private final int sizeY;
    private final int size;

    public ChessboardTactic(int sizeX, int sizeY, int size) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.size = size;
    }

    @Override
    public boolean isGoodTactic(int[][] map) {
        return !getBlackPositions(map).isEmpty();
    }

    @Override
    public Position getBestPosition(int[][] map) {
        List<Position> blacks = getBlackPositions(map);

        // Get random position
        int index = this.random.nextInt(blacks.size());

        return blacks.get(index);

    }

    private List<Position> getBlackPositions(int[][] map) {
        int width_middle = (int) Math.floor(sizeX / 2);
        int height_middle = (int) Math.floor(sizeY / 2);

        List<Position> blacks = new LinkedList<>();

        List<Node>[] grid = new List[4];
        grid[0] = new ArrayList<>();
        grid[1] = new ArrayList<>();
        grid[2] = new ArrayList<>();
        grid[3] = new ArrayList<>();

        int randomIncrement = 1;// + this.random.nextInt(1);

        for (int x = 0; x < map.length; x += randomIncrement) {
            for (int y = 0; y < map[x].length; y += randomIncrement) {
                int item = map[x][y];

                int gridNumber = getGridNumber(x, y, width_middle, height_middle);

                if (item == 0) {
                    grid[gridNumber].add(new Node(gridNumber, x, y, item));
                }
            }
        }

        for (List<Node> nodes : grid) {
            if (nodes.size() > this.size) {
                for (Node node : nodes) {
                    blacks.add(new Position(node.x, node.y));
                }
            }
        }

        return blacks;
    }

    @Override
    public void isSuccessfulHit(Position position, int[][] map) {
        // Do nothing.. ;)
    }

    private int getGridNumber(int x, int y, int width_middle, int height_middle) {
        boolean left = (x >= 0 && x < width_middle);
        boolean top = (y >= 0 && y < height_middle);

        if (top && left) {
            return 0;
        }

        if (top && !left) {
            return 1;
        }

        if (!top && left) {
            return 2;
        }

        return 3;
    }

    private class Node {

        int gridNumber;
        int x;
        int y;
        int value;

        private Node(int gridNumber, int x, int y, int value) {
            this.gridNumber = gridNumber;
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
