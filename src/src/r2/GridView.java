package r2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GridView extends JComponent {

    private int[][] arr;

    private int columnWidth;
    private int rowHeight;

    private int rows;
    private int columns;

    private boolean isPaintable = false;

    @Override
    public void paint(Graphics g) {
        if (!isPaintable) {
            return;
        }

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                int currentValue = this.arr[x][y];

                switch (currentValue) {
                    case -1:
                    case 0:
                        g.setColor(Color.WHITE);
                        break;
                    case 1:
                        g.setColor(Color.YELLOW);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        break;
                    case 3:
                        g.setColor(Color.GREEN);
                        break;
                    case 4:
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                    default:
                        g.setColor(Color.BLUE);
                        break;
                }

                g.fillRect(x * columnWidth, y * rowHeight, columnWidth, rowHeight);
            }
        }
    }

    void setGrid(int[][] arr) {
        int width = getSize().width;
        int height = getSize().height;

        this.arr = arr;

        this.columns = this.arr.length;
        this.rows = this.arr[0].length;

        this.rowHeight = height / this.rows;
        this.columnWidth = width / this.columns;

        this.isPaintable = true;
    }
}
