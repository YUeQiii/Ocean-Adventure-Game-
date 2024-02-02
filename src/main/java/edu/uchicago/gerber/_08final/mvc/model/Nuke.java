package edu.uchicago.gerber._08final.mvc.model;


import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.Sound;
import lombok.Data;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

@Data
public class Nuke extends Sprite{

    private static final int EXPIRE = 60;
    private int nukeState = 0;
    private Random rand = new Random();

    // Additional attributes for explosion effects
    private Color[] explosionColors = { Color.ORANGE,Color.RED, Color.YELLOW};


    public Nuke(Fish fish) {
        setCenter(fish.getCenter());
        setColor(Color.YELLOW);
        setExpiry(EXPIRE);
        setRadius(0);
        setTeam(Team.FRIEND);

        final double FIRE_POWER = 11.0;
        double vectorX =
                Math.cos(Math.toRadians(fish.getOrientation())) * FIRE_POWER;
        double vectorY =
                Math.sin(Math.toRadians(fish.getOrientation())) * FIRE_POWER;

        //fire force: fish inertia + fire-vector
        setDeltaX(fish.getDeltaX() + vectorX);
        setDeltaY(fish.getDeltaY() + vectorY);

    }


    @Override
    public void draw(Graphics g) {
        if(nukeState >= 1 && nukeState<=3){
            //Draw an expanding explosion with changing colors
            for (int i =0; i < getRadius();i++) {
                g.setColor(explosionColors[i % explosionColors.length]);
                g.drawOval(getCenter().x - i, getCenter().y - i, i * 2, i * 2);
            }
        }
        else{
            //Regular drawing for other states
            g.setColor((getColor()));
            g.drawOval(getCenter().x - getRadius(), getCenter().y - getRadius(), getRadius() * 2, getRadius() * 2);
        }
    }



    @Override
    public void move() {
        super.move();
        if (getExpiry() % (EXPIRE/6) == 0) {
            nukeState++;
            //Play different sounds for different explosion stages
            if (nukeState == 1){
                Sound.playSound("nuke_start.wav");
            }else if (nukeState ==3){
                Sound.playSound("nuke_peak.wav");
            }
        }
        switch (nukeState) {
            //travelling
            case 0:
                setRadius(2);
                break;
            //exploding
            case 1:
            case 2:
            case 3:
                setRadius(getRadius() + 4);
                break;
            //imploding
            case 4:
            case 5:
            default:
                setRadius(getRadius() - 10);
                break;


        }

    }
    @Override
    public void add(LinkedList<Movable> list) {
        if (CommandCenter_new.getInstance().getFish().getNukeMeter() > 0){
            list.add(this);
            Sound.playSound("nuke.wav");
            CommandCenter_new.getInstance().getFish().setNukeMeter(0);
        }
    }

    @Override
    public void remove(LinkedList<Movable> list) {
        //only remove upon natural mortality (see expire() of Sprite), otherwise a Nuke is invincible
        if (getExpiry() == 0) list.remove(this);
    }
}
