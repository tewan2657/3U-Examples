/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tewan2657
 */
public class MethodsExample {
    //method to say hello 
    public static void sayHello(){
        System.out.println("Hello!");
    }
    public static void addFive(int[] num) {
        num[0] = num[0] +5;
    }
    
    //overloaded method.... has parameters
    //say hello to a specific person
    
    public static void sayHello(String name){
        System.out.println("Hello " + name);
    }
    /**
     * Calculate the volume of a cylinder
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder 
     * @return the volume of the cylinder with the radius and height 
     */
    public static double volumeOfCyclinder(double radius, double height){
        // calculate the volume
        double rSquared = Math.pow(radius, 2);
        double volume = Math.PI*rSquared*height;
          return volume; //sends back an answer
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // calling the sayHello mathod
        sayHello();
        sayHello("bob");
    //Same thing as 
    //  System.out.println("Hello!");
        
        
        //radius = 10, height = 15
        double answer = volumeOfCyclinder(10,15);
        System.out.println(answer);
        
        int[] num = {10,15,25};
        addFive(num);
        System.out.println(num[0]);
        
        
    }
    
    
    
}
