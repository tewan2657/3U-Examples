
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tewan2657
 */
public class ArrayExamples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creating a dcanner for user input
        Scanner input = new Scanner(System.in);
        
        //create a list of 5 numbers
        double[] marks = new double[5];
        
        // scan in some marks
        System.out.println("Please enter in 5 marks");
        
//        // THIS IS A BAD WAY TO DO THIS......
//        // put a number in the first position 
//        marks[0] = input.nextDouble();
//        //put a number in the second position 
//        marks[1] = input.nextDouble();
//        //so on and so forth....
//        marks[3] = input.nextDouble();
//        marks[4] = input.nextDouble();
//        marks[5] = input.nextDouble();

        //BETTER WAY TO THIS - FOR LOOPS!
        for (int i = 0; i < marks.length; i++) {
            marks[i] = input.nextDouble();
        }
        
        System.out.println("the numbes in your list are ");
        for (int i = 0; i < marks.length; i++) {
            System.out.println(marks[i]);
        }
        //EXTRA - enchanced for loop 
        for(double num:marks){
            System.out.println(num);
        }
        
        // use a for loop to determin the sum 
        // start with an accumulator 
        double sum = 0;
        // for loop to go through each number 
        for (int i = 0; i < marks.length; i++) {
            // add whatever number I'm on to sum
          sum = sum + marks[i];
        }
        System.out.println("");
        System.out.println("The sum is " + sum);
        
        //calculate average
        double average = sum/marks.length;
        System.out.println("The average is " + average);
    }
}
