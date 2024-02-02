package edu.uchicago.gerber._08final.mvc.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Minnow extends Sprite {

    public Minnow() {

        setTeam(Team.FLOATER);

        //default values, all of which can be overridden in the extending concrete classes
        setExpiry(250);
        setColor(Color.WHITE);
        setRadius(50);
        //set random DeltaX
        setDeltaX(somePosNegValue(10));
        //set random DeltaY
        setDeltaY(somePosNegValue(10));
        //set random spin
        setSpin(somePosNegValue(10));

        //cartesian points which define the shape of the polygon
        List<Point> listPoints = new ArrayList<>();
        listPoints.add(new Point(4, 0));
        listPoints.add(new Point(2, 1));
        listPoints.add(new Point(-2, 0));
        listPoints.add(new Point(-3, 1));
        listPoints.add(new Point(-3, -1));
        listPoints.add(new Point(-2, 0));
        listPoints.add(new Point(2, -1));
        listPoints.add(new Point(4, 0));
        setCartesians(listPoints.toArray(new Point[0]));

    }

    @Override
    public void draw(Graphics g) {
        renderVector(g);
    }


}
