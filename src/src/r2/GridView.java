package r2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GridView extends JComponent {

    int width, height;

    private int[][] arr;

    GridView(int w, int h) {
        setSize(width = w, height = h);
    }

    @Override
    public void paint(Graphics g) {
        width = getSize().width;
        height = getSize().height;

        int htOfRow = 40;
        int wdOfRow = 40;

        if (this.arr != null) {
            for (int x = 0; x < arr.length; x++) {
                for (int y = 0; y < arr[x].length; y++) {
                    if (this.arr[x][y] == 1) {
                        g.setColor(Color.YELLOW);
                    }else if (this.arr[x][y] == 2) {
                        g.setColor(Color.RED);
                    }else{
                        g.setColor(Color.BLACK);
                    }

                    g.fillRect(x * wdOfRow, y * htOfRow, wdOfRow, htOfRow);
                }
            }
        }

    }

    void setGrid(int[][] arr) {
        this.arr = arr;
    }
}
