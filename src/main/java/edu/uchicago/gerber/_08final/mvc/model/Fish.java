package edu.uchicago.gerber._08final.mvc.model;

//import edu.uchicago.gerber.oldfinal.CommandCenter;
//import edu.uchicago.gerber.oldfinal.Falcon;
//import edu.uchicago.gerber.oldfinal.Game;
import edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;
import edu.uchicago.gerber._08final.mvc.controller.Game_new;
import edu.uchicago.gerber._08final.mvc.controller.Sound;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Data
public class Fish extends Sprite{
    // ==============================================================
    // FIELDS
    // ==============================================================

    //static fields

    //number of degrees the fish will turn at each animation cycle if the turnState is LEFT or RIGHT
    public final static int TURN_STEP = 8;
    //number of frames that the falcon will be protected after a spawn
    public static final int INITIAL_SPAWN_TIME = 50;
    //number of frames falcon will be protected after consuming a NewShieldFloater
    public static final int MAX_SHIELD = 100;
    public static final int MAX_NUKE = 600;
    public static final int MAX_MULTI_DIRECTION_FIRE = 400;
    public static final int MIN_RADIUS = 30;

    //images states
    public enum ImageState {
        FISH_INVISIBLE, //for pre-spawning

        FISH, //normal fish

        FISH_THR, //normal fish thrusting

        FISH_PRO, //protected fih (pink)

        FISH_PRO_THR, //protected fish (pink) thrusting

        //FISH_DOD, //normal fish dodging (something added)

        //FISH_PRO_DOD protected fish dodging (something added)

        FISH_MULTI_DIR_FIRE, // fish that can fire to multi-direction

        FISH_PRO_MULTI_DIR_FIRE  // fish (green) that can fire to multi-direction

    }

    //instance fields (getters/setters provided by Lombok @Data above)
    private int shield;

    private int nukeMeter;

    private int multiDirectionFireMeter;

    private int invisible;
    private boolean maxSpeedAttained;

    //showLevel is not germane to the Falcon. Rather, it controls whether the level is shown in the middle of the
    // screen. However, given that the Falcon reference is never null, and that a Falcon is a Movable whose move/draw
    // methods are being called every ~40ms, this is a very convenient place to store this variable.
    private int showLevel;
    private boolean thrusting;
    private boolean dodging; //(something added)

    //enum used for turnState field
    public enum TurnState {IDLE, LEFT, RIGHT}
    private Fish.TurnState turnState = Fish.TurnState.IDLE;

    // ==============================================================
    // CONSTRUCTOR
    // ==============================================================

    public Fish() {

        setTeam(Team.FRIEND);

        setRadius(MIN_RADIUS);


        //We use HashMap which has a seek-time of O(1)
        //See the resources directory in the root of this project for pngs.
        //Using enums as keys is safer b/c we know the value exists when we reference the consts later in code.
        Map<Fish.ImageState, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(Fish.ImageState.FISH_INVISIBLE, null );
        rasterMap.put(Fish.ImageState.FISH, loadGraphic("/imgs/fish/fish_purple.png") ); //normal fish
        rasterMap.put(Fish.ImageState.FISH_THR, loadGraphic("/imgs/fish/fish_purple.png") ); //normal ship thrusting
        rasterMap.put(Fish.ImageState.FISH_PRO, loadGraphic("/imgs/fish/fish_pink.png") ); //protected fish (pink)
        rasterMap.put(Fish.ImageState.FISH_PRO_THR, loadGraphic("/imgs/fish/fish_pink.png") ); // protected fish (pink)
        rasterMap.put(ImageState.FISH_MULTI_DIR_FIRE, loadGraphic("/imgs/fish/fish_green.png")); // multi-dir-fire fish (green)
        //make thrusting a series of bubbles following the fish? (something added)

        setRasterMap(rasterMap);
    }

        // ==============================================================
        // METHODS
        // ==============================================================

    @Override
    public void move() {
        super.move();

        if (invisible > 0) invisible--;
        if (shield > 0) shield--;
        if (nukeMeter > 0) nukeMeter--;
        if (multiDirectionFireMeter >0) multiDirectionFireMeter--;
        //The fish is a convenient place to decrement the showLevel variable as the falcon
        //move() method is being called every frame (~40ms); and the falcon reference is never null.
        if (showLevel > 0) showLevel--;

        final double THRUST = 0.85;
        final int MAX_TRUST_VELOCITY = 20;

        final double DODGE =0.75;
        final int MAX_DODGE_VELOCITY = 18;

        //apply some thrust vectors using trig.
        if (thrusting) {
            double vectorX = Math.cos(Math.toRadians(getOrientation()))
                    * THRUST;
            double vectorY = Math.sin(Math.toRadians(getOrientation()))
                    * THRUST;

            //Absolute velocity is the hypotenuse of deltaX and deltaY
            int absVelocity =
                    (int) Math.sqrt(Math.pow(getDeltaX()+ vectorX, 2) + Math.pow(getDeltaY() + vectorY, 2));

            //only accelerate (or adjust radius) if we are below the maximum trusting absVelocity.
            if (absVelocity < MAX_TRUST_VELOCITY){
                //accelerate
                setDeltaX(getDeltaX() + vectorX);
                setDeltaY(getDeltaY() + vectorY);
                //Make the fish radius bigger when the absolute velocity increases, thereby increasing difficulty when not
                // protected, and allowing player to use the shield offensively when protected.
                setRadius(MIN_RADIUS + absVelocity / 3);
                maxSpeedAttained = false;
            } else {
                //at max speed, you will lose steerage if you attempt to accelerate in the same general direction
                //show WARNING message to player using this flag (see drawFalconStatus() of GamePanel class)
                maxSpeedAttained = true;
            }

        }

        if(dodging) {
            double vectorX = Math.cos(Math.toRadians(getOrientation()))
                    * DODGE;
            double vectorY = Math.sin(Math.toRadians(getOrientation()))
                    * DODGE;

            //Absolute velocity is the hypotenuse of deltaX and deltaY
            int absVelocity =
                    (int) Math.sqrt(Math.pow(getDeltaX() - vectorX, 2) + Math.pow(getDeltaY() - vectorY, 2));


            //only accelerate if we are below the maximum dodging absVelocity, or we are moving forward
            if (absVelocity < MAX_DODGE_VELOCITY || (getDeltaX()>=0 && getDeltaY()>=0)){
                //accelerate
                setDeltaX(getDeltaX() - vectorX);
                setDeltaY(getDeltaY() - vectorY);
                //Make the fish radius smaller when the fish is dodging
                setRadius(MIN_RADIUS - absVelocity / 3);
                maxSpeedAttained = false;
            } else {
                //at max speed, you will lose steerage if you attempt to dodge in the same general direction
                //show WARNING message to player using this flag (see drawFalconStatus() of GamePanel class)
                maxSpeedAttained = true;
            }
        }


        //adjust the orientation given turnState
        int adjustOr = getOrientation();
        switch (turnState){
            case LEFT:
                adjustOr = getOrientation() <= 0 ? 360 - TURN_STEP : getOrientation() - TURN_STEP;
                break;
            case RIGHT:
                adjustOr = getOrientation() >= 360 ? TURN_STEP : getOrientation() + TURN_STEP;
                break;
            case IDLE:
            default:
                //do nothing
        }
        setOrientation(adjustOr);

    }

    //Since the superclass Spite does not provide an
    // implementation for draw() (contract method from Movable) ,we inherit that contract debt, and therefore must
    // provide an implementation. This is a raster and vector (see drawShield below) implementation of draw().
    @Override
    public void draw(Graphics g) {

        //set local image-state
        Fish.ImageState imageState;
        if (invisible > 0){
            imageState = Fish.ImageState.FISH_INVISIBLE;
        }
        else if (shield > 0){
            //imageState = thrusting ? Falcon.ImageState.FALCON_PRO_THR : Falcon.ImageState.FALCON_PRO;
            imageState = ImageState.FISH_PRO;
            //you can also combine vector elements and raster elements
            drawShield(g);
        }
        else if (multiDirectionFireMeter >0){
            imageState = ImageState.FISH_MULTI_DIR_FIRE;
        }
        else { //not protected
            imageState = thrusting ? Fish.ImageState.FISH_THR : Fish.ImageState.FISH;
        }

        //down-cast (widen the aperture of) the graphics object to gain access to methods of Graphics2D
        //and render the raster image according to the image-state
        renderRaster((Graphics2D) g, getRasterMap().get(imageState));

    }

    private void drawShield(Graphics g){
        g.setColor(Color.PINK);
        g.drawOval(getCenter().x - getRadius(), getCenter().y - getRadius(), getRadius() *2, getRadius() *2);
    }

    @Override
    public void remove(LinkedList<Movable> list) {
        //The fish is never actually removed from the game-space; instead we decrement numFishes
        //only execute the decrementFishNumAndSpawn() method if shield is down.
        if ( shield == 0)  decrementFishNumAndSpawn();
    }

    public void decrementFishNumAndSpawn(){

        CommandCenter_new.getInstance().setNumFishes(CommandCenter_new.getInstance().getNumFishes() -1);
        if (CommandCenter_new.getInstance().isGameOver()) return;
        Sound.playSound("shipspawn.wav");
        setShield(Fish.INITIAL_SPAWN_TIME);
        setInvisible(Fish.INITIAL_SPAWN_TIME/4);
        //put fish in the middle of the game-space
        setCenter(new Point(Game_new.DIM.width / 2, Game_new.DIM.height / 2));
        //random number between 0-360 in steps of TURN_STEP
        setOrientation(Game_new.R.nextInt(360 / Fish.TURN_STEP) * Fish.TURN_STEP);
        setDeltaX(0);//only keypress can trigger the fish to move
        setDeltaY(0); //only keypress can trigger the fish to move
        setRadius(Fish.MIN_RADIUS);
        setMaxSpeedAttained(false);
        setNukeMeter(0);
    }





}


