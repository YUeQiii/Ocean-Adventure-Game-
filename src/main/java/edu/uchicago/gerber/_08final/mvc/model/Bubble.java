package edu.uchicago.gerber._08final.mvc.model;


import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.GameOp;
import edu.uchicago.gerber._08final.mvc.controller.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Bubble extends Sprite {
    private List<Color> trailColors;
    private int colorIndex = 0;
    private Random rand = new Random();

    public Bubble(Fish fish, int orientationOffset ) {
        trailColors = new ArrayList<>();
        trailColors.add(Color.RED);
        trailColors.add(Color.ORANGE);
        trailColors.add(Color.YELLOW);
        trailColors.add(Color.GREEN);
        trailColors.add(Color.BLUE);



        setTeam(Team.FRIEND);
        setColor(Color.WHITE);


        //a bullet(bubbles) expires after 20 frames.
        setExpiry(20);
        setRadius(8);

        //everything is relative to the fish that fired the bullet
        setCenter(fish.getCenter());

        //set the bubble orientation to the fish orientation
        setOrientation(fish.getOrientation());


        final double FIRE_POWER = 35.0;
        double vectorX =
                Math.cos(Math.toRadians(getOrientation()+orientationOffset)) * FIRE_POWER;
        double vectorY =
                Math.sin(Math.toRadians(getOrientation()+orientationOffset)) * FIRE_POWER;

        //fire force: fish inertia + fire-vector
        setDeltaX(fish.getDeltaX() + vectorX);
        setDeltaY(fish.getDeltaY() + vectorY);

        //we have a reference to the fish passed into the constructor. Let's create some kick-back.
        //fire kick-back on the falcon: inertia - fire-vector / some arbitrary divisor
        final double KICK_BACK_DIVISOR;
        if(orientationOffset ==0){
            KICK_BACK_DIVISOR = 36.0;
        }
        //if the fish is multi-direction firing, we have a larger kick-back
        else {
            KICK_BACK_DIVISOR = 20.0;
        }
        fish.setDeltaX(fish.getDeltaX() - vectorX / KICK_BACK_DIVISOR);
        fish.setDeltaY(fish.getDeltaY() - vectorY / KICK_BACK_DIVISOR);


        //define the points on a cartesian grid
        int radius = 50;
        List<Point> circlePoints = new ArrayList<>();
        int numberOfPoints = 36; // More points for a smoother circle
        for (int i = 0; i < numberOfPoints; i++) {
            double angle = 2 * Math.PI * i / numberOfPoints;
            int x = (int)(radius * Math.cos(angle));
            int y = (int)(radius * Math.sin(angle));
            circlePoints.add(new Point(x, y));
        }
        setCartesians(circlePoints.toArray(new Point[0]));
    }
    @Override
    public void draw(Graphics g) {

        for (int i = 0; i < 5; i++) {
            g.setColor(trailColors.get(colorIndex));
            g.fillOval(getCenter().x - getRadius(), getCenter().y - getRadius(),
                    getRadius() * 2, getRadius() * 2);
        }
        colorIndex = (colorIndex + 1) % trailColors.size();

        renderVector(g);
    }

    @Override
    public void add(LinkedList<Movable> list) {
        super.add(list);
        Sound.playSound("thump.wav");

    }
    @Override
    public void move(){
        super.move();
        if(getExpiry() == 1){
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new Explosion(this ), GameOp.Action.ADD);
        }
    }



}
