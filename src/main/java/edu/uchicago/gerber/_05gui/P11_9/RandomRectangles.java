package edu.uchicago.gerber._05gui.P11_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomRectangles {
    private JFrame frame;
    private JTextField textField;
    private DrawPanel drawPanel;
    private int numberOfRectangles;

    public RandomRectangles() {
        frame = new JFrame("Random Rectangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        textField = new JTextField(10);
        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numberOfRectangles = Integer.parseInt(textField.getText());
                    drawPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer.");
                }
            }
        });

        topPanel.add(new JLabel("Enter number of rectangles:"));
        topPanel.add(textField);
        topPanel.add(drawButton);

        drawPanel = new DrawPanel();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class DrawPanel extends JPanel {
        private Random random = new Random();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < numberOfRectangles; i++) {
                int x = random.nextInt(getWidth() - 30);
                int y = random.nextInt(getHeight() - 30);
                g.drawRect(x, y, 30, 30);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RandomRectangles());
    }
}

