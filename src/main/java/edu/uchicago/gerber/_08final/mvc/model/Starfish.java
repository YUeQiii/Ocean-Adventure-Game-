package edu.uchicago.gerber._08final.mvc.model;


import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.GameOp;
import edu.uchicago.gerber._08final.mvc.controller.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Starfish extends Sprite{

    //radius of a large starfish
    private final int LARGE_RADIUS = 80;

    //size determines if the Starfish is Large (0) or Small (1)
    public Starfish(int size){

        //a size of zero is a big starfish
        //a size of 1 or 2 is med or small starfish respectively. See getSize() method.
        if (size == 0) setRadius(LARGE_RADIUS);
        else setRadius(LARGE_RADIUS/(size * 2));

        //Starfish is FOE
        setTeam(Movable.Team.FOE);
        setColor(Color.YELLOW);

        //the spin will be either plus or minus 0-9
        setSpin(somePosNegValue(8));
        //random delta-x
        setDeltaX(somePosNegValue(10));
        //random delta-y
        setDeltaY(somePosNegValue(10));

        setCartesians(generateVertices());

    }



    //overloaded constructor, so we can spawn smaller Starfishes from an exploding one
    public Starfish(Starfish astExploded){
        //calls the other constructor: Shell(int size)
        this(astExploded.getSize() + 1);
        setCenter(astExploded.getCenter());
        int newSmallerSize = astExploded.getSize() + 1;
        //random delta-x : inertia + the smaller the asteroid, the faster its possible speed
        setDeltaX(astExploded.getDeltaX() / 1.5 + somePosNegValue( 5 + newSmallerSize * 1));
        //random delta-y : inertia + the smaller the asteroid, the faster its possible speed
        setDeltaY(astExploded.getDeltaY() / 1.5 + somePosNegValue( 5 + newSmallerSize * 1));

    }

    //converts the radius to integer representing the size of the Starfish:
    //0 = large, 1 = medium, 2 = small
    public int getSize(){
        switch (getRadius()) {
            case LARGE_RADIUS:
                return 0;
            case LARGE_RADIUS / 2:
                return 1;
            default:
                return 0;
        }
    }

    private Point[] generateVertices(){
        final double PRECISION = 100.0;
        // cartesian points which define the shape of the starfish
        List<Point> listPoints = new ArrayList<>();
        listPoints.add(new Point(0, 5));   // Right point
        listPoints.add(new Point(-1, 1));    // Point between right and bottom-right
        listPoints.add(new Point(-5, 0));    // Bottom-right point
        listPoints.add(new Point(-1, -1));    // Point between bottom-right and bottom-left
        listPoints.add(new Point(-4, -5));   // Bottom-left point
        listPoints.add(new Point(0, -2));   // Point between bottom-left and left
        listPoints.add(new Point(4, -5));  // Left point
        listPoints.add(new Point(1, -1));  // Point between left and top
        listPoints.add(new Point(5, 0));   // Top point
        listPoints.add(new Point(1, 1));   // Point between top and right
        listPoints.add(new Point(0, 5));


        return listPoints.toArray(new Point[0]);
    }

    @Override
    public void draw(Graphics g) {
        renderVector(g);
    }

    @Override
    public void remove(LinkedList<Movable> list) {
        super.remove(list);
        spawnSmallerStarfishesOrDebris(this);
        //give the user some points for destroying the asteroid
        CommandCenter_new.getInstance().setScore(CommandCenter_new.getInstance().getScore() + 10L * (getSize() + 1));
        Sound.playSound("kapow.wav");//change soun d

    }

    private void spawnSmallerStarfishesOrDebris(Starfish originalStarfish) {

        int size = originalStarfish.getSize();
        //med
        if (size > 0) {
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new WhiteCloudDebris(originalStarfish), GameOp.Action.ADD);
            CommandCenter_new.getInstance().getOpsQueue().enqueue((new NukeMinnow()),GameOp.Action.ADD);
        }
        //large
        else {
            //for large (0) sized Starfishes only, spawn 2 or 3 small Starfishes respectively
            //We can use the existing variable (size) to do this
            size += 3;
            while (size-- > 0) {
                CommandCenter_new.getInstance().getOpsQueue().enqueue(new Starfish(originalStarfish),GameOp.Action.ADD);
            }
        }

    }

}
