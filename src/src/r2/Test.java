package r2;

import javax.swing.JFrame;
import javax.swing.JList;

public class Test extends JFrame {

    private int width = 600;
    private int height = 600;

    private GridView grid;
    private JList list;

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
