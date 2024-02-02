package edu.uchicago.gerber._08final.mvc.model;


import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.GameOp;
import edu.uchicago.gerber._08final.mvc.controller.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Shell extends Sprite{
    //radius of a large shell
    private final int LARGE_RADIUS = 80;

    //size determines if the Shell is Large (0), Medium (1), or Small (2)
    public Shell(int size){

        //a size of zero is a big asteroid
        //a size of 1 or 2 is med or small asteroid respectively. See getSize() method.
        if (size == 0) setRadius(LARGE_RADIUS);
        else setRadius(LARGE_RADIUS/(size * 2));

        //Asteroid is FOE
        setTeam(Team.FOE);
        setColor(Color.BLUE);

        //the spin will be either plus or minus 0-9
        setSpin(somePosNegValue(8));
        //random delta-x
        setDeltaX(somePosNegValue(10));
        //random delta-y
        setDeltaY(somePosNegValue(10));

        setCartesians(generateVertices());

    }



    //overloaded constructor, so we can spawn smaller asteroids from an exploding one
    public Shell(Shell astExploded){
        //calls the other constructor: Shell(int size)
        this(astExploded.getSize() + 1);
        setCenter(astExploded.getCenter());
        int newSmallerSize = astExploded.getSize() + 1;
        //random delta-x : inertia + the smaller the asteroid, the faster its possible speed
        setDeltaX(astExploded.getDeltaX() / 1.5 + somePosNegValue( 5 + newSmallerSize * 1));
        //random delta-y : inertia + the smaller the asteroid, the faster its possible speed
        setDeltaY(astExploded.getDeltaY() / 1.5 + somePosNegValue( 5 + newSmallerSize * 1));

    }

    //converts the radius to integer representing the size of the Asteroid:
    //0 = large, 1 = medium, 2 = small
    public int getSize(){
        switch (getRadius()) {
            case LARGE_RADIUS:
                return 0;
            case LARGE_RADIUS / 2:
                return 1;
            case LARGE_RADIUS / 4:
                return 2;
            default:
                return 0;
        }
    }

    private Point[] generateVertices(){
        List <Point> listPoints = new ArrayList<Point>();

// Example points for a simple shell shape
        listPoints.add(new Point(10, 0));
        listPoints.add(new Point(9, 3));
        listPoints.add(new Point(6, 4));
        listPoints.add(new Point(4, 2));
        listPoints.add(new Point(2, 6));
        listPoints.add(new Point(-2, 6));
        listPoints.add(new Point(-4, 2));
        listPoints.add(new Point(-6, 4));
        listPoints.add(new Point(-9, 3));
        listPoints.add(new Point(-10, 0));
        listPoints.add(new Point(-6, -8));
        listPoints.add(new Point(-2, -10));
        listPoints.add(new Point(2, -10));
        listPoints.add(new Point(6, -8));
        listPoints.add(new Point(10, 0));

        return listPoints.toArray(new Point[0]);
    }

    @Override
    public void draw(Graphics g) {
        renderVector(g);
    }

    @Override
    public void remove(LinkedList<Movable> list) {
        super.remove(list);
        spawnSmallerShellsOrDebris(this);
        //give the user some points for destroying the asteroid
        CommandCenter_new.getInstance().setScore(CommandCenter_new.getInstance().getScore() + 10L * (getSize() + 1));
        Sound.playSound("kapow.wav");//change soun d

    }

    private void spawnSmallerShellsOrDebris(Shell originalShell) {

        int size = originalShell.getSize();
        //small shells
        if (size > 1) {
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new WhiteCloudDebris(originalShell), GameOp.Action.ADD);
        }
        //med and large
        else {
            //for large (0) and medium (1) sized Shells only, spawn 2 or 3 smaller shells respectively
            //We can use the existing variable (size) to do this
            size += 2;
            while (size-- > 0) {
                CommandCenter_new.getInstance().getOpsQueue().enqueue(new Shell(originalShell),GameOp.Action.ADD);
            }
        }

    }


}
