package edu.uchicago.gerber._05gui.P10_5;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

public class HouseComponent extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        drawHouse(g, 50, 150, 200, 150,Color.RED);  // First house
        drawHouse(g, 300, 100, 150, 100,Color.BLUE); // Smaller house
        drawHouse(g, 500, 200, 250, 200, Color.GREEN); // Larger house
    }

    private void drawHouse(Graphics g, int x, int y, int width, int height,Color color) {
        g.setColor(color);

        // House base
        g.fillRect(x, y, width, height);

        // Reset color for the roof, door, and window
        g.setColor(Color.BLACK);

        // Triangular roof (spire)
        g.drawLine(x, y, x + width / 2, y - height / 2);
        g.drawLine(x + width, y, x + width / 2, y - height / 2);

        // Door on the left side
        g.drawRect(x + width/8, y + 2*height/3, width/4, height/3);

        // Window on the right side
        g.drawRect(x + 5*width/8, y + height/4, width/4, height/3);
    }
}
