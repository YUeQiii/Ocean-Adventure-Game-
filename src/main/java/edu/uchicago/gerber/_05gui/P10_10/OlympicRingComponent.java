package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

public class OlympicRingComponent extends JComponent{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2 = (Graphics2D) g;

        drawRing(g, 100, 150, Color.BLUE);
        drawRing(g, 200, 150, Color.BLACK);
        drawRing(g, 300, 150, Color.RED);
        drawRing(g, 150, 200, Color.YELLOW);
        drawRing(g, 250, 200, Color.GREEN);
    }

    private static void drawRing(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.drawOval(x, y, 100, 100);
    }


}
