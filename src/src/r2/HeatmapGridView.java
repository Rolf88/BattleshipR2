package r2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import r2.domain.models.Heatmap;

public class HeatmapGridView extends JComponent {

    private int[][] arr;

    private int columnWidth;
    private int rowHeight;

    private int rows;
    private int columns;

    private boolean isPaintable = false;
    private Heatmap heatmap;

    @Override
    public void paint(Graphics g) {
        if (!isPaintable) {
            return;
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getSize().width, getSize().height);

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                int currentValue = this.arr[x][y];
                float transparency = ((float)currentValue / (float)heatmap.getHeats());

                             // System.out.println(transparency);
                g.setColor(new Color(0, 0, 0, transparency));
                g.fillRect(x * columnWidth, y * rowHeight, columnWidth, rowHeight);
            }
        }
    }

    void setGrid(Heatmap heatmap) {
        int width = getSize().width;
        int height = getSize().height;

        this.heatmap = heatmap;
        this.arr = heatmap.getResults();

        this.columns = this.arr.length;
        this.rows = this.arr[0].length;

        this.rowHeight = height / this.rows;
        this.columnWidth = width / this.columns;

        this.isPaintable = true;
    }
}
