package edu.uchicago.gerber._05gui.P10_5;

import javax.swing.JFrame;

public class HouseViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HouseComponent house = new HouseComponent();
        frame.add(house);

        frame.setVisible(true);
    }
}



