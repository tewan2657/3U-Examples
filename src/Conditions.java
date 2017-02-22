
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;
import becker.robots.Thing;
import becker.robots.Wall;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tewan2657
 */
public class Conditions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create a city
        City kw = new City();
        //create a robot
        Robot carl = new Robot(kw, 1 , 1, Direction.EAST);
        //put up a wall
        new Wall(kw, 1, 5, Direction.EAST);
        
        //place thing
        new Thing(kw, 0, 1);
        
       // an infinite loop  
       while(carl.frontIsClear());{ 
        
       //move and turn left  
       carl.move();  
       //carl.turnLeft();
       
       // if there is something to pick up
       if(carl.canPickThing());
       
       //pick up everything 
       carl.pickThing();
      
       
}      //did carl hit a wall 
       if(!(carl.frontIsClear()) ){
           carl.turnLeft();
           
       }
}
}   
