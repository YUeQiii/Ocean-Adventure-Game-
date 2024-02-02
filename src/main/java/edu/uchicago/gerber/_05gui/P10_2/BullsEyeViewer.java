package edu.uchicago.gerber._05gui.P10_2;

import edu.uchicago.gerber._05gui.P10_2.BullsEyeComponent;

import javax.swing.*;

public class BullsEyeViewer {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BullsEyeComponent bullsEye = new BullsEyeComponent();
        frame.add(bullsEye);

        frame.setVisible(true);
    }
}
