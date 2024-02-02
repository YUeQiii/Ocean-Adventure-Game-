package edu.uchicago.gerber._05gui.P10_2;

import javax.swing.*;
import java.awt.*;

public class BullsEyeComponent extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int maxRadius = Math.min(getWidth(), getHeight()) / 2;

        for (int i = 5; i >= 1; i--) {
            if (i % 2 == 0) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.fillOval(centerX - (i * maxRadius / 5), centerY - (i * maxRadius / 5), i * maxRadius / 5 * 2, i * maxRadius / 5 * 2);
        }
    }
}
