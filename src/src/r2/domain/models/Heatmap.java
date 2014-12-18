package r2.domain.models;

public class Heatmap {
    private final int heats;
    private final int[][] results;

    public Heatmap(int heats, int[][] results) {
        this.heats = heats;
        this.results = results;
    }

    public int getHeats() {
        return heats;
    }

    public int[][] getResults() {
        return results;
    }
}
