
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tewan2657
 */
public class InputOuputExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // scanner to get user input  
        Scanner input = new Scanner(System.in);
        
       // asking the user to type in thier name  
        System.out.println("Please enter your name");
        
        // scanning in their name into a variable 
        String name2 = input.nextLine();
        
         //String name = "Nishant";
        //saying hello to the user 
        System.out.println("Hello " + name2 );
        
        // ask the user what year they were born 
        // use this to calculate their age 
        System.out.println("What year where you born?");
        int year = input.nextInt();
        //determine their age 
        int age = 2017 - year;
        if(age>0){
        System.out.println("You are " + age + " years old!");
        }else{
            System.out.println("I think you are lieing....");
        }   
        System.out.println(5.0/2.0);
            
//          + - add
//          - - Subtract         
//          * - Multiply
//          / - divide 
//          % - remainder(modulus)  
//          10*(5-3)
//          5/2 = 2 - Java                
//          5.0/2.0 = 2.5         
//          5%2 = 1 - remainder   
            
        }
    }

