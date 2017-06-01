
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

/**
 *
 * @author lamon
 */
public class JumpingExample extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    //Title of the window
    String title = "Jumping Example";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;


    // YOUR GAME VARIABLES WOULD GO HERE
    
    // player position
    Rectangle player = new Rectangle(100, 500, 25, 50);

    int dy = 0; // displacement of y - how much you move up/down each time
    int dx = 0; // displacement of x - how much you move left/right each frame
    double decay = 0.8; // how much the dx should decrease by if not "moving". This gives the decelerating effect
    
    int gravity = 1; // .... Its gravity. It pulls you down....
    boolean inAir = false;  // is the player in the air or not? Prevents "air jumping"
    
    int JUMP_VELOCITY = -15; // how hard the character jumps up. 
    int MAX_Y_VELOCITY = 20; // maximum speed the dy can be
    int MAX_X_VELOCITY = 6; // maximum speed the dx can be
    
    // key variables
    boolean right = false;
    boolean left = false; 
    boolean jump = false;
    
    // block variable - I'll use an array to make this easier!
    Rectangle[] blocks = new Rectangle[5]; // I'll add these in the pre setup section
    
    // GAME VARIABLES END HERE   

    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public JumpingExample(){
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
        // draw a black background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        // draw blocks in gray
        g.setColor(Color.GRAY);
        // use a for loop to go through the array of blocks :)
        for(int i = 0; i < blocks.length; i++){
            g.fillRect(blocks[i].x, blocks[i].y, blocks[i].width, blocks[i].height);
        }
        
        // draw player
        g.setColor(Color.yellow);
        g.fillRect(player.x, player.y, player.width, player.height);
        
        // GAME DRAWING ENDS HERE
    }


    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here

       // create all the different blocks to use in the level
       // They are each in the array
       blocks[0] = new Rectangle(0, 550, 400, 50); // floor
       blocks[1] = new Rectangle(300,500, 50, 50); // block on floor
       blocks[2] = new Rectangle(400, 450, 50, 50); // air block 1
       blocks[3] = new Rectangle(500, 450, 50, 50); // air block 2
       blocks[4] = new Rectangle(600, 450, 50, 50); // air block 3
       
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
            
            // apply gravity!
            dy = dy + gravity; // gravity always pulls down!
            // clamp maximum down force
            if(dy > MAX_Y_VELOCITY){
                dy = MAX_Y_VELOCITY; // biggest positive dy
            }else if (dy < -MAX_Y_VELOCITY){
                dy = -MAX_Y_VELOCITY; // biggest negative dy
            }
            
            // look at keys for left/right movement
            if(right){
                dx = dx +1; // start ramping up my movement
                // cap my max speed
                if(dx > MAX_X_VELOCITY){
                    dx = MAX_X_VELOCITY;
                }
            }else if(left){
                dx = dx - 1; // start ramping up my movement
                // cap my max speed
                if(dx < -MAX_X_VELOCITY){
                    dx = -MAX_X_VELOCITY;
                }
            }else{
                // need to start slowing down
                dx = (int)(dx*decay); // takes a percentage of what dx was... needs to be an int
            }
            
            // is jump being pressed and are you standing on something?
            if(jump && !inAir){
                inAir = false; // I'm going to be jumping... not on the ground :)
                dy = JUMP_VELOCITY; // start moving up!
            }
            
            // apply the forces to x and y
            player.x = player.x + dx;
            player.y = player.y + dy;
            
            // check for any collisions and fix them
            // see the method below
            checkCollisions();
            
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
        public void mousePressed(MouseEvent e){
            
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        
        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e){
            
        }
    }
    
    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter{
        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT){
                right = true;
            }else if(key == KeyEvent.VK_LEFT){
                left = true;
            }else if(key == KeyEvent.VK_SPACE){
                jump = true;
            }
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT){
                right = false;
            }else if(key == KeyEvent.VK_LEFT){
                left = false;
            }else if(key == KeyEvent.VK_SPACE){
                jump = false;
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        JumpingExample game = new JumpingExample();
                
        // starts the game loop
        game.run();
    }
    
    // method the check if there are collisions in the level
    public void checkCollisions(){
        // use a loop to go through each block
        // see if we are hitting any block... if we are not, we should be falling
        boolean colliding = false;
        for(int i = 0; i < blocks.length; i++){
            // if the player is hitting a block at position i
            if(player.intersects(blocks[i])){
                // handle the collision with the block at position i
                handleCollision(i);
                colliding = true;
            }
        }
        
        // if no collision seen, I'm in the air!
        if(!colliding){
            inAir = true;
        }
    }
    
    // method to fix any collisions that happen
    // the position integer is which block it is colliding with in the array of blocks
    // since all of our blocks are "axis-aligned" it is easier
    // we will determine how much of an overlap we have, and fix the smaller one (x or y)
    public void handleCollision(int position){
        // set my overlap as a number - -1 means not set
        int overlapX = -1;
        // player is on the left
        if(player.x <= blocks[position].x){
            // right corner of player subtract left corner of block
            overlapX = player.x + player.width - blocks[position].x;
        }else{
            // right corner of block subtract left corner of player
            overlapX = blocks[position].x + blocks[position].width - player.x;
        }
        
        // do the same but for the y values
        // set my overlap as a number - -1 means not set
        int overlapY = -1;
        // player is above the block
        if(player.y <= blocks[position].y){
            // bottom of player subtract top of block
            overlapY = player.y + player.height - blocks[position].y;
        }else{
            // bottom of block subtract top of player
            overlapX = blocks[position].y + blocks[position].height - player.y;
        }
        
        // now check which overlap is smaller
        // we will correct that one because it will be less obvious!
        
        // fix the x overlapping
        // move the players x position so the no longer hit the block
        // we also fix the dx so that we are no longer changing that
        if(overlapX < overlapY){
            // which side am I on?
            // on the right side
            if(player.x <= blocks[position].x){
                player.x = blocks[position].x - player.width;
            }else{
                player.x = blocks[position].x + blocks[position].width;
            }
            dx = 0; // not moving left or right any more :)
        }else{
            // fixing the y overlap in the same way
            // the difference this time is we have to deal with the dy and not dx
            
            // above the block
            if(player.y <= blocks[position].y){
                // no more y collision
                player.y = blocks[position].y - player.height;
                // I'm on the block so not in the air!
                inAir = false;
            }else{
                // im under the block, just fix the overlap
                player.y = blocks[position].y + blocks[position].height;
            }
            dy = 0; // not moving up or down anymore :)
        }
    }
    
    
}