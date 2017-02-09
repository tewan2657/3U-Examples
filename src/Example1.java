
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
public class Example1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creating a new city called kitchener 
      City kitchener = new City();
      
      //creating a robot 
      Robot mike = new Robot(kitchener, 2, 1, Direction.EAST);
     mike.move();
     mike.turnLeft();
     mike.turnLeft();
     mike.turnLeft();
     mike.move();
     mike.turnLeft();
     mike.move();
     //adding objects to the city
     new Thing(kitchener, 3, 3);
     new Wall(kitchener, 3, 3, Direction.EAST);
      mike.move();
    
    }
}
