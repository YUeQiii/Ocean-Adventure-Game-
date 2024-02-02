package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.GameOp;
import edu.uchicago.gerber._08final.mvc.controller.Game_new;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends  Sprite{
    private double deltaX, deltaY;

    public Explosion(Bubble bubble) {
        setTeam(Team.FRIEND);
        setColor(Color.WHITE);


        //a Explosion expires after 20 frames.
        setExpiry(10);
        setRadius(10);

        setCenter(bubble.getCenter());

        //set the explosion orientation to the bubbles orientation
        setOrientation(bubble.getOrientation());

        deltaX = bubble.getDeltaX();
        deltaY = bubble.getDeltaY();


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
        Color color = new Color(Game_new.R.nextInt(256),Game_new.R.nextInt(256),Game_new.R.nextInt(256));
        g.setColor(color);
        g.fillOval(getCenter().x-getRadius()/2, getCenter().y-getRadius()/2, getRadius(), getRadius());
        renderVector(g);
    }


    @Override
    public void move() {
        super.move();
        if(getExpiry() >0){
            setRadius(getRadius()+10) ;
        }else{
            CommandCenter_new.getInstance().getOpsQueue().enqueue(this, GameOp.Action.REMOVE);

        }
    }

    @Override
    public Team getTeam() {
        return Team.FRIEND;
    }
}
