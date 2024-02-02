package edu.uchicago.gerber._08final.mvc.controller;

import edu.uchicago.gerber._08final.mvc.model.*;
import edu.uchicago.gerber._08final.mvc.view.GamePanel;
import  edu.uchicago.gerber._08final.mvc.controller.Game_new;
import  edu.uchicago.gerber._08final.mvc.controller.CommandCenter_new;



import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;


// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

public class Game_new implements Runnable, KeyListener {

    // ===============================================
    // FIELDS
    // ===============================================

    public static final Dimension DIM = new Dimension(1100, 900); //the dimension of the game.
    private final GamePanel gamePanel;
    //this is used throughout many classes.
    public static final Random R = new Random();

    public final static int ANIMATION_DELAY = 40; // milliseconds between frames

    public final static int FRAMES_PER_SECOND = 1000 / ANIMATION_DELAY;

    private final Thread animationThread;


    //key-codes
    private static final int
            PAUSE = 80, // p key
            QUIT = 81, // q key
            LEFT = 37, // rotate left; left arrow
            RIGHT = 39, // rotate right; right arrow
            UP = 38, // thrust; up arrow
            DOWN = 40, //dodge; down arrow
            START = 83, // s key
            FIRE = 32, // space key
            MUTE = 77, // m-key mute
            NUKE = 78, // n-key mute
            HYPER =72; //h-key

    // for possible future use
    // HYPER = 68, 					// D key
    //ALIEN = 65;                // A key
    // SPECIAL = 70; 					// fire special weapon;  F key

    private final Clip soundThrust;
    private final Clip soundBackground;

    // ===============================================
    // ==CONSTRUCTOR
    // ===============================================

    public Game_new() {

        gamePanel = new GamePanel(DIM);
        gamePanel.addKeyListener(this); //Game object implements KeyListener
        soundThrust = Sound.clipForLoopFactory("whitenoise.wav");
        soundBackground = Sound.clipForLoopFactory("music-background.wav");

        //fire up the animation thread
        animationThread = new Thread(this); // pass the animation thread a runnable object, the Game object
        animationThread.setDaemon(true);
        animationThread.start();


    }

    // ===============================================
    // ==METHODS
    // ===============================================

    public static void main(String[] args) {
        //typical Swing application start; we pass EventQueue a Runnable object.
        EventQueue.invokeLater(Game_new::new);
    }

    // Game implements runnable, and must have run method
    @Override
    public void run() {

        // lower animation thread's priority, thereby yielding to the 'Event Dispatch Thread' or EDT
        // thread (which handles keystrokes) which listens to keystrokes
        animationThread.setPriority(Thread.MIN_PRIORITY);

        // and get the current time
        long startTime = System.currentTimeMillis();

        // this thread animates the scene
        while (Thread.currentThread() == animationThread) {


            //this call will cause all movables to move() and draw() themselves every ~40ms
            // see GamePanel class for details
            gamePanel.update(gamePanel.getGraphics());

            checkCollisions();
            checkNewLevel();
            checkFloaters();
            //this method will execute add() and remove() callbacks on Movable objects
            processGameOpsQueue();
            //keep track of the frame for development purposes
            CommandCenter_new.getInstance().incrementFrame();

            // surround the sleep() in a try/catch block
            // this simply controls delay time between
            // the frames of the animation
            try {
                // The total amount of time is guaranteed to be at least ANIMATION_DELAY long.  If processing (update)
                // between frames takes longer than ANIMATION_DELAY, then the difference between startTime -
                // System.currentTimeMillis() will be negative, then zero will be the sleep time
                startTime += ANIMATION_DELAY;

                Thread.sleep(Math.max(0,
                        startTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                // do nothing (bury the exception), and just continue, e.g. skip this frame -- no big deal
            }
        } // end while
    } // end run

    private void checkFloaters() {
        //spawnNewWallFloater();
        spawnShieldMinnow();
        spawnNukeMinnow();
        spawnMultiDirectionFireMinnow();
        spawnShark();
    }


    private void checkCollisions() {

        //This has order-of-growth of O(FOES * FRIENDS)
        Point pntFriendCenter, pntFoeCenter;
        int radFriend, radFoe;
        for (Movable movFriend : CommandCenter_new.getInstance().getMovFriends()) {
            for (Movable movFoe : CommandCenter_new.getInstance().getMovFoes()) {

                pntFriendCenter = movFriend.getCenter();
                pntFoeCenter = movFoe.getCenter();
                radFriend = movFriend.getRadius();
                radFoe = movFoe.getRadius();

                //detect collision
                if (pntFriendCenter.distance(pntFoeCenter) < (radFriend + radFoe)) {
                    //enqueue the friend
                    CommandCenter_new.getInstance().getOpsQueue().enqueue(movFriend, GameOp.Action.REMOVE);

                    //enqueue the foe
                    CommandCenter_new.getInstance().getOpsQueue().enqueue(movFoe, GameOp.Action.REMOVE);
                }
            }//end inner for
        }//end outer for

        //check for collisions between fish and floaters. Order of growth of O(FLOATERS)
        Point pntFalCenter = CommandCenter_new.getInstance().getFish().getCenter();
        int radFish = CommandCenter_new.getInstance().getFish().getRadius();

        Point pntFloaterCenter;
        int radFloater;
        for (Movable movFloater : CommandCenter_new.getInstance().getMovFloaters()) {
            pntFloaterCenter = movFloater.getCenter();
            radFloater = movFloater.getRadius();
            //detect collision
            if (pntFalCenter.distance(pntFloaterCenter) < (radFish + radFloater)) {
                //enqueue the floater
                CommandCenter_new.getInstance().getOpsQueue().enqueue(movFloater, GameOp.Action.REMOVE);
            }//end if
        }//end for

    }//end meth


    //This method adds and removes movables to/from their respective linked-lists.
    private void processGameOpsQueue() {

        //deferred mutation: these operations are done AFTER we have completed our collision detection to avoid
        // mutating the movable linkedlists while iterating them above.
        while (!CommandCenter_new.getInstance().getOpsQueue().isEmpty()) {

            GameOp gameOp = CommandCenter_new.getInstance().getOpsQueue().dequeue();

            //given team, determine which linked-list this object will be added-to or removed-from
            LinkedList<Movable> list;
            Movable mov = gameOp.getMovable();
            switch (mov.getTeam()) {
                case FOE:
                    list = CommandCenter_new.getInstance().getMovFoes();
                    break;
                case FRIEND:
                    list = CommandCenter_new.getInstance().getMovFriends();
                    break;
                case FLOATER:
                    list = CommandCenter_new.getInstance().getMovFloaters();
                    break;
                case DEBRIS:
                default:
                    list = CommandCenter_new.getInstance().getMovDebris();
            }

            //pass the appropriate linked-list from above
            //this block will execute the add() or remove() callbacks in the Movable models.
            GameOp.Action action = gameOp.getAction();
            if (action == GameOp.Action.ADD)
                mov.add(list);
            else //REMOVE
                mov.remove(list);

        }//end while
    }


    private void spawnShieldMinnow() {

        if (CommandCenter_new.getInstance().getFrame() % ShieldMinnow.SPAWN_SHIELD_MINNOW == 0) {
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new ShieldMinnow(), GameOp.Action.ADD);
        }
    }

    private void spawnNukeMinnow() {

        if (CommandCenter_new.getInstance().getFrame() % NukeMinnow.SPAWN_NUKE_MINNOW == 0) {
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new NukeMinnow(), GameOp.Action.ADD);
        }
    }

    private void spawnMultiDirectionFireMinnow(){
        if (CommandCenter_new.getInstance().getFrame() % MultiDirectionFireMinnow.SPAWN_MultiDirectionFire_MINNOW == 0) {
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new MultiDirectionFireMinnow(), GameOp.Action.ADD);
        }
    }

    private void spawnShark(){
        if(CommandCenter_new.getInstance().getFrame() % Shark.SPAWN_SHARK ==0){
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new Shark(CommandCenter_new.getInstance().getFish()), GameOp.Action.ADD);
        }
    }






    //this method spawns new Large (0) Asteroids
    /*
    private void spawnBigAsteroids(int num) {
        while (num-- > 0) {
            //Asteroids with size of zero are big
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new Asteroid(0), GameOp.Action.ADD);

        }
    }
    */

    private void spawnBigShells(int num){
        while(num-- >0){
            //Shells with size of zero are big
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new Shell(0), GameOp.Action.ADD);
        }
    }

    private void spawnBigStarfishes(int num){
        while(num-- >0){
            //Starfishes with size of zero are big
            CommandCenter_new.getInstance().getOpsQueue().enqueue(new Starfish(0), GameOp.Action.ADD);
        }
    }

    private boolean isLevelClear() {
        //if there are no more Asteroids on the screen
        //boolean asteroidFree = true;
        boolean shellFree = true;
        boolean starFishFree = true;
        for (Movable movFoe : CommandCenter_new.getInstance().getMovFoes()) {

            if (movFoe instanceof Shell) {
                shellFree = false;
                break;
            }
            if (movFoe instanceof Starfish) {
                starFishFree  = false;
                break;
            }

        }
        //return asteroidFree;
        return shellFree && starFishFree;
    }

    private void checkNewLevel() {

        if (isLevelClear()) {
            //currentLevel will be zero at beginning of game
            int level = CommandCenter_new.getInstance().getLevel();
            //award some points for having cleared the previous level
            CommandCenter_new.getInstance().setScore(CommandCenter_new.getInstance().getScore() + (10_000L * level));
            //bump the level up
            level = level + 1;
            CommandCenter_new.getInstance().setLevel(level);
            //spawn some big new Shells
            spawnBigShells(level);
            //spawn some big new Starfishes
            spawnBigStarfishes(level);
            //make falcon invincible momentarily in case new asteroids spawn on top of him, and give player
            //time to adjust to new asteroids in game space.
            CommandCenter_new.getInstance().getFish().setShield(Fish.INITIAL_SPAWN_TIME);
            //show "Level X" in middle of screen
            CommandCenter_new.getInstance().getFish().setShowLevel(Fish.INITIAL_SPAWN_TIME);

        }
    }


    // Varargs for stopping looping-music-clips
    private static void stopLoopingSounds(Clip... clpClips) {
        Arrays.stream(clpClips).forEach(clip -> clip.stop());
    }

    // ===============================================
    // KEYLISTENER METHODS
    // ===============================================

    @Override
    public void keyPressed(KeyEvent e) {
        Fish fish = CommandCenter_new.getInstance().getFish();
        int keyCode = e.getKeyCode();

        if (keyCode == START && CommandCenter_new.getInstance().isGameOver()) {
            CommandCenter_new.getInstance().initGame();
            return;
        }


        switch (keyCode) {
            case PAUSE:
                CommandCenter_new.getInstance().setPaused(!CommandCenter_new.getInstance().isPaused());
                if (CommandCenter_new.getInstance().isPaused()) stopLoopingSounds(soundBackground, soundThrust);
                break;
            case QUIT:
                System.exit(0);
                break;
            case UP:
                fish.setThrusting(true);
                soundThrust.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            case DOWN:
                fish.setDodging(true);
                soundThrust.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            case LEFT:
                fish.setTurnState(Fish.TurnState.LEFT);
                break;
            case RIGHT:
                fish.setTurnState(Fish.TurnState.RIGHT);
                break;


            //add HEAD and BACK

            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Fish fish = CommandCenter_new.getInstance().getFish();
        int keyCode = e.getKeyCode();
        //show the key-code in the console
        //System.out.println(keyCode);

        switch (keyCode) {
            case FIRE:
                if(fish.getMultiDirectionFireMeter()>0){
                    CommandCenter_new.getInstance().getOpsQueue().enqueue(new Bubble(fish,0), GameOp.Action.ADD);
                    CommandCenter_new.getInstance().getOpsQueue().enqueue(new Bubble(fish,30), GameOp.Action.ADD);
                    CommandCenter_new.getInstance().getOpsQueue().enqueue(new Bubble(fish,329), GameOp.Action.ADD);
                    break;
                }
                else{
                    CommandCenter_new.getInstance().getOpsQueue().enqueue(new Bubble(fish,0), GameOp.Action.ADD);
                    break;
                }

            case NUKE:
                CommandCenter_new.getInstance().getOpsQueue().enqueue(new Nuke(fish), GameOp.Action.ADD);
                break;
            //write another case that can
            //releasing either the LEFT or RIGHT arrow key will set the TurnState to IDLE
            case LEFT:
            case RIGHT:
                fish.setTurnState(Fish.TurnState.IDLE);
                break;
            case UP:
                fish.setThrusting(false);
                soundThrust.stop();
                break;
            case DOWN:
                fish.setDodging(false);
                break;
            case MUTE:
                CommandCenter_new.getInstance().setMuted(!CommandCenter_new.getInstance().isMuted());
                //get a reference to
                if (!CommandCenter_new.getInstance().isMuted()) {
                    stopLoopingSounds(soundBackground);
                } else {
                    soundBackground.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;

            case HYPER:
                //get a reference to the fish and set its center to some random point on the screen
                Point newRandomPoint = new Point(Game_new.R.nextInt(Game_new.DIM.width),Game_new.R.nextInt(Game_new.DIM.height));
                CommandCenter_new.getInstance().getFish().setCenter(newRandomPoint);
                break;

            default:
                break;
        }

    }

    @Override
    // does nothing, but we need it b/c of KeyListener contract
    public void keyTyped(KeyEvent e) {
    }

}


