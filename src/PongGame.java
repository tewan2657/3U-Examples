
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author tewan2657
 */
public class PongGame extends JComponent {

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

       //Constructing Players
    Rectangle player1 = new Rectangle(0, 0, 25, 100);
    Rectangle player2 = new Rectangle(WIDTH - 25, HEIGHT - 100, 25, 100);
    
    //player 1 movement
    boolean downPressed;
    boolean upPressed;
    
    //player 2 movement
    boolean wPressed;
    boolean sPressed;
    
    //ball speed
    int velocityX = 4;
    int velocityY = 4;
    
    //player Score
    int player1Score = 0;   
    int player2Score = 0;

     Font myFont = new Font("Arial", Font.BOLD, 75);       
            
    //constructing a ball 
    Rectangle ball = new Rectangle(WIDTH/2, HEIGHT/2, 25, 25);


    // GAME VARIABLES END HERE   

    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public PongGame(){
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
         // Drawing players
        g.drawRect(player1.x, player1.y, player1.width, player1.height);
        g.drawRect(player2.x, player2.y, player2.width, player2.height);
        
        //drawing ball
        g.drawRect(ball.x, ball.y, ball.width, ball.height);
        
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
        
        //sets font
        g.setFont(myFont);
        //sets color 
        g.setColor(Color.red);
        g.drawString("" + player1Score, WIDTH/2 - 100, 75);
        g.drawString("" + player1Score, WIDTH/2 + 50, 75);

        
        // GAME DRAWING ENDS HERE
    }


    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here

       
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
            
            collisions();
            
            //player 2 movement 
            if(upPressed){     
                player2.y = player2.y - 5;
            }
            if(downPressed){
                player2.y = player2.y + 5;
            }
            //player 1 movement 
            if(wPressed){
             player1.y = player1.y - 5;   
            }
            
            if(sPressed){
                player1.y = player1.y + 5;
            }
            
            //Update x coordinates every second
            ball.x += velocityX;
            //update y coordinates every second 
            ball.y += velocityY;
            
            
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
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                downPressed = true;
            }
              if(e.getKeyCode() == KeyEvent.VK_UP){
                upPressed = true;        
              }
               if(e.getKeyCode() == KeyEvent.VK_W){
                wPressed = true;
               }
                if(e.getKeyCode() == KeyEvent.VK_S){
                sPressed = true;
                }
      }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
              if(e.getKeyCode() == KeyEvent.VK_DOWN){
                downPressed = false;
            }
              if(e.getKeyCode() == KeyEvent.VK_UP){
                upPressed = false;        
              }
               if(e.getKeyCode() == KeyEvent.VK_W){
                wPressed = false;
               }
                if(e.getKeyCode() == KeyEvent.VK_S){
                sPressed = false;
                }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        PongGame game = new PongGame();
                
        // starts the game loop
        game.run();
    }



     public void collisions(){
     // if the ball hit player1
     if(ball.intersects(player1)){
         //inverse the velocity
        velocityX = -velocityX;
}
     //if the ball hit player2
     if(ball.intersects(player2)){
         //inverse the velocity 
         velocityX = - velocityX;
     }
     //top Collision
     if(ball.y <= 0){
         velocityY = 4;
     }
     
     //bottom Collision
     if(ball.y + ball.height >= HEIGHT){
         velocityY = -velocityY;
     }
     
     //player's y does not exceed off to the negatives
     if(player1.y <=0){
         player1.y = 0;
     }
     //bottom part
     if(player1.y + player1.height >= HEIGHT){
         player1.y = player1.y - 5;
     }
     //top part
    if(player2.y <=0){
         player2.y = 0;
     }
    //bottom part
     if(player2.y + player2.height >= HEIGHT){
         player2.y = player2.y - 5;
     }
}
     
}
