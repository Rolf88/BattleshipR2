package r2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JPanel;
import r2.domain.models.Heatmap;

public class DebugFrame extends JFrame {

    private final int width = 800;
    private final int height = 400;

    private final GridView grid;
    private final GridView grid2;
    private final HeatmapGridView heatmapGrid;

    private Lock lock = new ReentrantLock();

    public DebugFrame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.width, this.height);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        this.grid = new GridView();
        panel.add(this.grid);

        this.heatmapGrid = new HeatmapGridView();
        panel.add(this.heatmapGrid);

        this.grid2 = new GridView();
        panel.add(this.grid2);

        this.setLayout(new BorderLayout());
        this.add(panel);

        revalidate();
    }

    public void redrawPlayerMap(int[][] arr) {
        this.lock.lock();

        try {
            this.grid.setGrid(arr);

            repaint();
        } finally {
            this.lock.unlock();
        }
    }

    void redrawOpponentMap(int[][] arr) {
        this.lock.lock();

        try {
            this.grid2.setGrid(arr);

            repaint();
        } finally {
            this.lock.unlock();
        }
    }

    void redrawHeatmapMap(Heatmap heatmap) {
        this.lock.lock();

        try {
            this.heatmapGrid.setGrid(heatmap);

            repaint();
        } finally {
            this.lock.unlock();
        }
    }
}
