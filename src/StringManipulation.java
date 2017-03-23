
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tewan2657
 */
public class StringManipulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create a scanner 
        Scanner input = new Scanner(System.in);
        
        while (true) {
            //get a word from the user 
            System.out.println("Please enter a word");
            String word = input.nextLine();
            
            // try to find the position of an a
            // if not found, it is -1
            int aPosition = word.indexOf("a");
            
            // stop the program when END is entered 
            if(word.equals("END")){
                break;
            }

            //convert to lowercase
            word.toLowerCase();

            //fix 1337 (leet) speak
            // chages all 3 to e 
            word = word.replace("3", "e");
            //change all @ to a 
            word = word.replace("@", "a");
            //change all 0 to o 
            word = word.replace("0", "o");

            int length = word.length();
            System.out.println("that word's length is " + length);

            // get the leter at a position 
            char letter = word.charAt(length - 1);
            System.out.println("Character at 0 is " + letter);

            //print out each letter in the word
            //start at position 0, go up to length - 1
            for (int i = 0; i < length; i++) {
                char character = word.charAt(i);

                // print the character 
                System.out.println(character);

                //see if it is a vowel 
                if (character == 'a'
                        || character == 'e'
                        || character == 'i'
                        || character == 'o'
                        || character == 'u') {

                    // breaking up the string where the vowel 
                    String firstHalf = word.substring(0, i);
                    String lastHalf = word.substring(i);

                    //create the translated word
                    String tword = lastHalf + firstHalf + "ay";

                    // show the user the Pig Latin 
                    System.out.println("Your word in Pig latin is " + tword);

                    //stop looking for more vowels
                    break;

                }



            }
        }
    }
}