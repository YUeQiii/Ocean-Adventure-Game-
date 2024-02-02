package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.*;


import java.awt.*;
import java.util.LinkedList;

public class NukeMinnow extends Minnow{

    public static final int SPAWN_NUKE_MINNOW= Game_new.FRAMES_PER_SECOND * 60;
    public NukeMinnow() {
        setColor(Color.ORANGE);
        setExpiry(140);
    }


    @Override
    public void remove(LinkedList<Movable> list) {
        super.remove(list);
        //if getExpiry() > 0, then this remove was the result of a collision, rather than natural mortality
        if (getExpiry() > 0) {
            Sound.playSound("nuke-up.wav");
            CommandCenter_new.getInstance().getFish().setNukeMeter(Fish.MAX_NUKE);
        }

    }

}
