
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lamon
 */
public class AnimationExample extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    //Title of the window
    String title = "My Game";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    // YOUR GAME VARIABLES WOULD GO HERE
    // player variables
    Rectangle player = new Rectangle(WIDTH / 2, HEIGHT / 2, 28, 68);
    char direction = 'r';  // used to determine which direction I'm facing

    // Image variables to use
    // I will load all of them in in the "Pre-Setup" method
    // The arrays are to store all of the animation frames - 6 frames per running animation
    // The others are for images when not moving
    BufferedImage[] runLeft = new BufferedImage[6];
    BufferedImage[] runRight = new BufferedImage[6];
    BufferedImage standLeft;
    BufferedImage standRight;
    
    // variables that change frames
    int frame = 0; // which frame of animation am I drawing
    int frameDelay = 100; // number of milliseconds between frames
    long nextFrame = 0; // what time we next change the frame. i.e. now + delay
    
    
    // control variables
    boolean right = false;
    boolean left = false;

    // GAME VARIABLES END HERE   
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public AnimationExample() {
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();

        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        
        // draw player
        if(left){ // am I moving left?
            // need to line up the pictures properly from my character size
            int imageWidth = runLeft[frame].getWidth();
            int imageDiff = imageWidth - player.width;
            int imageX = player.x - imageDiff/2; // subtract half the difference
            
            // draw the image
            g.drawImage(runLeft[frame], imageX, player.y, null);
        }else if(right){ // am I moving right?
            // need to line up the pictures properly from my character size
            int imageWidth = runRight[frame].getWidth();
            int imageDiff = imageWidth - player.width;
            int imageX = player.x - imageDiff/2; // subtract half the difference
            
            // draw the image
            g.drawImage(runRight[frame], imageX, player.y, null);
        }else{ // am I not moving?
            // which way was I facing?
            if(direction == 'l'){
                g.drawImage(standLeft, player.x, player.y, null);
            }else{
                g.drawImage(standRight, player.x, player.y, null);
            }
        }
        
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here
        
        // load in all images
        standLeft = loadImage("images/crono_left.gif");
        standRight = loadImage("images/crono_right.gif");
        // load in animations
        for(int i = 0; i < 6; i++){
            runRight[i] = loadImage("images/crono_right_run.00" + i + ".gif");
            runLeft[i] = loadImage("images/crono_left_run.00" + i + ".gif");
        }
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if(right){ // moving right
                direction = 'r'; // sets which direction I'm going
                player.x = player.x + 5;
            }else if(left){ // moving left
                direction = 'l'; // sets which direction is should be going
                player.x = player.x - 5;
            }else{
                frame = 0; // not moving so frame resets
                nextFrame = 0; // allowed to change frames right away!
            }
            
            // delay of when to change the frame
            // we check if we are moving AND we check if we have passed the delay to change frames
            if((left || right) && startTime >= nextFrame){
                frame = (frame+1)%6; // add to frame but keep it between 0 and 6
                nextFrame = startTime + frameDelay; // new time: now + delay we wanted 
            }
            
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {

        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT){
                right = true;
            }else if(key == KeyEvent.VK_LEFT){
                left = true;
            }
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT){
                right = false;
            }else if(key == KeyEvent.VK_LEFT){
                left = false;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        AnimationExample game = new AnimationExample();

        // starts the game loop
        game.run();
    }

    // A method used to load in an image
    // The filname is used to pass in the EXACT full name of the image from the src folder
    // i.e.  images/picture.png
    public BufferedImage loadImage(String filename) {
        
        BufferedImage img = null;

        try {
            // use ImageIO to load in an Image
            // ClassLoader is used to go into a folder in the directory and grab the file
            img = ImageIO.read(ClassLoader.getSystemResourceAsStream(filename));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return img;
    }
}
