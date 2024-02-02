package edu.uchicago.gerber._05gui.P10_9;

import javax.swing.*;
import java.awt.*;

public class ItalianFlagComponent extends JComponent {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawItalianFlag(g, 10, 10, 100, Color.BLACK, Color.RED, Color.YELLOW);
        drawItalianFlag(g, 10, 125, 100,Color.RED, Color.WHITE, Color.GREEN);

    }

    void drawItalianFlag(Graphics g, int xLeft, int yTop, int width, Color color1, Color color2, Color color3) {
        g.setColor(color1);
        g.fillRect(xLeft, yTop, width, width * 2 / 9);
        g.setColor(color2);
        g.fillRect(xLeft , yTop+width * 2 / 9, width , width * 2 / 9);
        g.setColor(color3);
        g.fillRect(xLeft, yTop+width * 4 / 9, width, width * 2 / 9);

        //g.drawLine(xLeft + width / 3, yTop, xLeft + width * 2 / 3, yTop);
        //g.drawLine(xLeft + width / 3, yTop + width * 2 / 3, xLeft + width * 2 / 3, yTop + width * 2 / 3);
    }
}
