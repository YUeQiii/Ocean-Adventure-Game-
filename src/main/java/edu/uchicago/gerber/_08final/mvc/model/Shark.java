package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game_new;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shark extends Sprite{
    private Fish fish;

    public static final int SPAWN_SHARK= Game_new.FRAMES_PER_SECOND * 70;

    public Shark(Fish fish){
        super();
        this.fish = fish;

        setTeam(Movable.Team.FOE);



        setExpiry(250);
        setRadius(90);
        setColor(Color.BLACK);

        setCenter(new Point(Game_new.R.nextInt(Game_new.DIM.width),Game_new.R.nextInt(Game_new.DIM.height)));

        setOrientation(Game_new.R.nextInt(360));
        setCartesians(generateVertices());

    }

    private Point[] generateVertices(){

        //List<Point> listPoints = new ArrayList<Point>();

// Example points for a simple shell shape
        ArrayList<Point> pntCs = new ArrayList<Point>();
        pntCs.add(new Point(7,0));
        pntCs.add(new Point(1,3));
        pntCs.add(new Point(0,5));
        pntCs.add(new Point(-2,2));
        pntCs.add(new Point(-9,0));
        pntCs.add(new Point(-10,2));
        pntCs.add(new Point(-10,-2));
        pntCs.add(new Point(-9,0));
        pntCs.add(new Point(-2,-2));
        pntCs.add(new Point(0,-5));
        pntCs.add(new Point(1,-3));
        pntCs.add(new Point(4,-2));
        pntCs.add(new Point(7,0));

        return pntCs.toArray(new Point[0]);
    }

    @Override
    public  void move(){
        super.move();

        //move towards the fish
        int deltaX = fish.getCenter().x - this.getCenter().x;
        int deltaY = fish.getCenter().y - this.getCenter().y;

        double radians = Math.atan2(deltaY,deltaX);
        setDeltaX(Math.cos(radians)*5);
        setDeltaY(Math.sin(radians)*5);
    }

    @Override
    public void draw(Graphics g) {
        renderVector(g);
        /*
        setColor(this.getColor());
        Point[] points = getCartesians();
        Object[] xPoints = Arrays.stream(points).map(Point::getX).toArray();
        Object[] yPoints = Arrays.stream(points).map(Point::getY).toArray();

        int[] xPncs = new int[getCartesians().length];
        int[] yPncs = new int[getCartesians().length];
        int i=0, j=0;
        for(Object xPoint: xPoints){
            xPncs[i] = (Integer)xPoint;
        }
        for(Object yPoint : yPoints){
            yPncs[i] = (Integer)yPoint;
        }
        g.fillPolygon(xPncs,yPncs,getCartesians().length);
        */
    }


}
