package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.Game_new;
import edu.uchicago.gerber._08final.mvc.controller.Sound;


import java.awt.*;
import java.util.LinkedList;

public class MultiDirectionFireMinnow extends Minnow{

    public static final int SPAWN_MultiDirectionFire_MINNOW= Game_new.FRAMES_PER_SECOND * 30;
    public MultiDirectionFireMinnow() {
        setColor(Color.GREEN);
        setExpiry(1000);
    }

    @Override
    public void remove(LinkedList<Movable> list) {
        super.remove(list);
        //if getExpiry() > 0, then this remove was the result of a collision, rather than natural mortality
        if (getExpiry() > 0) {
            Sound.playSound("nuke-up.wav");

            CommandCenter_new.getInstance().getFish().setMultiDirectionFireMeter(Fish.MAX_MULTI_DIRECTION_FIRE);
            //Turning on multi-directional attack mode causes the shield to disappear
            CommandCenter_new.getInstance().getFish().setShield(0);
        }

    }
}
