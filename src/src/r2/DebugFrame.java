package r2;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class DebugFrame extends JFrame {

    private final int width = 800;
    private final int height = 800;

    private final GridView grid;

    public DebugFrame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.width, this.height);
        this.setLayout(new BorderLayout());

        this.grid = new GridView();
        this.add(this.grid);
    }

    public void redrawGrid(int[][] arr) {
        this.grid.setGrid(arr);

        repaint();
        revalidate();
    }
}
