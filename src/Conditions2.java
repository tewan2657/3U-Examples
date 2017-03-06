
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;
import becker.robots.Thing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tewan2657
 */
public class Conditions2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a city    
        City kw = new City();

        //create a robot
        Robot carl = new Robot(kw, 4, 1, Direction.EAST);

        // create the path 
        new Thing(kw, 4, 2);
        new Thing(kw, 4, 3);
        new Thing(kw, 4, 3);

        new Thing(kw, 3, 3);

        new Thing(kw, 2, 3);
        new Thing(kw, 2, 3);
        new Thing(kw, 2, 3);
        new Thing(kw, 2, 3);

        // turn on the thing labels 
        kw.showThingCounts(true);

        //step onto the first part of the path 
        carl.move();

        // loop
        while (true) {
            // if there are things, pick them all up 
            while (carl.canPickThing()) {
                carl.pickThing();
            }
            // if holding one thing, move
            if (carl.countThingsInBackpack() == 1) {
                //drop things
                carl.putThing();
                //move
                carl.move();
                // if holding 2 things, turn left
            } else if (carl.countThingsInBackpack() == 2) {
                //drop things
                carl.putThing();
                carl.putThing();
                //move
                carl.turnLeft();
                carl.move();

            } else if (carl.countThingsInBackpack() == 3) {
                //drop things
                carl.putThing();
                carl.putThing();
                carl.putThing();
                //move
                carl.turnLeft();
                carl.turnLeft();
                carl.turnLeft();
                carl.move();


                // found 4 things
            }
            // if holding 3 things, turn right
            // if holding 4 things, stop 
        }





    }
}
