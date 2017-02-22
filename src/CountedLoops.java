
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;
import becker.robots.RobotSE;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tewan2657
 */
public class CountedLoops {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create new city
        City kw = new City();
        
        //create a robot
        RobotSE carl = new RobotSE(kw, 1, 1, Direction.EAST);
       
        // make a counter 
        int counter = 0;
        //move 5 times using a condition
        while(counter <5){
            //move
            carl.move();
            //increase the counter!
            counter = counter + 1;
            //counter++; <- shortcut to add 1
            
        }
        carl.turnAround();
        
        //using a for loop to count
        // for(define a variable; condition to loop; increase variable)
        for(int i = 0; i < 5; i = i + 1){
            carl.move();
        }
        // COMPOUND CONDITIONS
        //&& <- AND statement - both must be true 
        //|| <- OR statement - one of them must be true 
        if(carl.frontIsClear()){
            if(carl.countThingsInBackpack() == 5){
            // do something 
            
            }
            
            if(carl.frontIsClear() && carl.countThingsInBackpack() ==5){
            
                   
        }
            if (carl.frontIsClear()){
                //do something
            }else if(carl.countThingsInBackpack()==5){
                // do same thing as above if statment
                
            }
            if(carl.frontIsClear() || carl.countThingsInBackpack() == 5){
                //do something 
            }
        
    }
}
}

