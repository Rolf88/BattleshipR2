package r2;

import java.awt.Graphics;
import javax.swing.JFrame;

public class Test extends JFrame {

    private int width = 600;
    private int height = 600;

    private GridView grid;

    public Test() {
        this.setSize(this.width, this.height);

        this.grid = new GridView(this.width, this.height);
        add(this.grid);
    }

    public void redrawGrid(int[][] arr) {
        this.grid.setGrid(arr);

        repaint();
        revalidate();
    }
}
