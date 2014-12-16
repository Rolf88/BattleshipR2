package r2;

import r2.domain.IOcean;

public class ChessboardOcean implements IOcean {

    @Override
    public int[][] create(int columns, int rows) {
        int[][] map = new int[columns][rows];

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                boolean isWhite = ((y + x) % 2) == 0;

                if (isWhite) {
                    map[x][y] = -1;
                } else {
                    map[x][y] = 0;
                }
            }
        }

        return map;
    }

}
