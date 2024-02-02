package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.Game_new;

import edu.uchicago.gerber._08final.mvc.controller.Sound;

import java.awt.*;
import java.util.LinkedList;

public class ShieldMinnow extends Minnow {

    //spawn every 25 seconds
    public static final int SPAWN_SHIELD_MINNOW = Game_new.FRAMES_PER_SECOND * 20;
    public ShieldMinnow() {
        setColor(Color.PINK);
        setExpiry(300);
    }

    @Override
    public void remove(LinkedList<Movable> list) {
        super.remove(list);
        //if getExpiry() > 0, then this remove was the result of a collision, rather than natural mortality
        if (getExpiry() > 0) {
            Sound.playSound("shieldup.wav");
            CommandCenter_new.getInstance().getFish().setShield(Fish.MAX_SHIELD);
        }

    }
}
