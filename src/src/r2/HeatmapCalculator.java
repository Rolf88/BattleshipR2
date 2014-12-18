package r2;

import java.util.LinkedList;
import java.util.List;
import r2.domain.models.Heatmap;

public class HeatmapCalculator {

    private final List<Integer[][]> heats = new LinkedList<>();

    public Heatmap calculateHeatmap(int sizeX, int sizeY, int[][] map) {
        Integer[][] subMap = new Integer[sizeX][sizeY];

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                subMap[x][y] = map[x][y];
            }
        }

        heats.add(subMap);

        // How many heats have there been?
        int numberOfHeats = heats.size();

        // Calculate the results
        int[][] heatmap = new int[sizeX][sizeY];
        for (Integer[][] prevMap : heats) {
            for (int y = 0; y < prevMap.length; y++) {
                for (int x = 0; x < prevMap[y].length; x++) {
                    heatmap[x][y] += prevMap[x][y];
                }
            }
        }

        return new Heatmap(numberOfHeats, heatmap);
    }
}
