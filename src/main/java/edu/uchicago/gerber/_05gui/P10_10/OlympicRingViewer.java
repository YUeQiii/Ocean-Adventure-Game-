package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;


public class OlympicRingViewer  {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OlympicRingComponent component = new OlympicRingComponent();
        frame.add(component);

        frame.setVisible(true);
    }


}
